/*******************************************************************************
 * Copyright (c) 1998, 2007 Oracle. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0, which accompanies this distribution
 * and is available at http://www.eclipse.org/legal/epl-v10.html.
 *
 * Contributors:
 *     Oracle - initial API and implementation from Oracle TopLink
 ******************************************************************************/  
package org.eclipse.persistence.transaction;

import org.eclipse.persistence.exceptions.TransactionException;
import org.eclipse.persistence.internal.sessions.AbstractSession;
import org.eclipse.persistence.internal.sessions.UnitOfWorkImpl;
import org.eclipse.persistence.logging.*;
import org.eclipse.persistence.sessions.SessionProfiler;

/**
 * <p>
 * <b>Purpose</b>: Abstract Synchronization Listener class
 *
 * <b>Description</b>: This abstract class is paired with the
 * AbstractTransactionController class. It contains most of the implementation
 * logic to handle callback notifications from an external transaction
 * manager to ensure consistency between the global transaction and the
 * TopLink unit of work. It does not assume any particular specification
 * or interface, but can be called by any implementation subclass.
 *
 * @see AbstractTransactionController
 */
public abstract class AbstractSynchronizationListener {

    /**
     * The external txn controller that is intimate with the transaction manager
     * and knows how to do things like rolling back transactions, etc.
     */
    protected AbstractTransactionController controller;

    /**
     * The parent of the uow.
     */
    protected AbstractSession session;

    /**
     * The unit of work associated with the global txn that this listener is
     * bound to.
     */
    protected UnitOfWorkImpl unitOfWork;

    /**
     * The global transaction object.
     */
    protected Object transaction;

    /**
     * INTERNAL:
     */
    public AbstractSynchronizationListener() {
        super();
    }

    /**
     * INTERNAL:
     */
    protected AbstractSynchronizationListener(UnitOfWorkImpl unitOfWork, AbstractSession session, Object transaction, AbstractTransactionController controller) {
        this.session = session;
        this.unitOfWork = unitOfWork;
        this.transaction = transaction;
        this.controller = controller;
    }

    /**
     * INTERNAL:
     * This method performs the logic that occurs at transaction
     * completion time. This includes issuing the SQL, etc.
     * This method executes within the transaction context of the caller of
     * transaction.commit(), or in the case of container-managed transactions,
     * in the context of the method for which the Container started the transaction.
     */
    public void beforeCompletion() {
        UnitOfWorkImpl uow = getUnitOfWork();
        try {
            Object status = getTransactionController().getTransactionStatus();
            getTransactionController().logTxStateTrace(uow, "TX_beforeCompletion", status);
            //CR# 3452053 
            session.startOperationProfile(SessionProfiler.JtsBeforeCompletion);

            // If the uow is not active then somebody somewhere messed up 
            if (!uow.isActive()) {
                throw TransactionException.inactiveUnitOfWork(uow);
            }

            // Bail out if we don't think we should actually issue the SQL
            if (!getTransactionController().canIssueSQLToDatabase_impl(status)) {
                return;
            }

            // Must force concurrency mgrs active thread if in nested transaction
            if (getSession().isInTransaction()) {
                getSession().getTransactionMutex().setActiveThread(Thread.currentThread());
            }

            // Send the SQL to the DB
            uow.issueSQLbeforeCompletion();

            // Fix up our merge state in the unit of work and the session
            uow.setPendingMerge();

        } catch (RuntimeException exception) {
            // Something went wrong (probably while sending SQL to the database).
            uow.log(new SessionLogEntry(uow, SessionLog.WARNING, SessionLog.TRANSACTION, exception));
            // Handle the exception according to transaction manager requirements
            handleException(exception);
        } finally {
            session.endOperationProfile(SessionProfiler.JtsBeforeCompletion);
        }
    }

    /**
     * INTERNAL:
     * The method performs the logic that should be executed after the transaction
     * has been completed. The status passed in indicates whether the transaction
     * was committed or rolled back. This status flag may be different for different
     * implementations.
     * This method executes without a transaction context.
     *
     * @param status The status code of the transaction completion.
     */
    public void afterCompletion(Object status) {
        UnitOfWorkImpl uow = getUnitOfWork();
        try {
            // Log the fact that we got invoked
            getTransactionController().logTxStateTrace(uow, "TX_afterCompletion", status);
            //Cr#3452053
            session.startOperationProfile(SessionProfiler.JtsAfterCompletion);
            // The uow should still be active even in rollback case
            if (!uow.isActive()) {
                throw TransactionException.inactiveUnitOfWork(uow);
            }

            // Only do merge if txn was committed
            if (getTransactionController().canMergeUnitOfWork_impl(status)) {
                uow.afterTransaction(true, true);// committed=true; externalTxn=true
                if (uow.isMergePending()) {
                    // uow in PENDING_MERGE state, merge clones
                    uow.mergeClonesAfterCompletion();
                }
            } else {
                uow.afterTransaction(false, true);// committed=false; externalTxn=true
            }
        } catch (RuntimeException rtEx) {
            // First log the exception so it gets seen
            uow.log(new SessionLogEntry(uow, SessionLog.WARNING, SessionLog.TRANSACTION, rtEx));
            // Rethrow it just for fun (app servers tend to ignore them at this stage)
            throw rtEx;
        } finally {
            session.endOperationProfile(SessionProfiler.JtsAfterCompletion);
        }

        // Clean up by releasing the uow and client session
        if (uow.shouldResumeUnitOfWorkOnTransactionCompletion() && getTransactionController().canMergeUnitOfWork_impl(status)){
            uow.synchronizeAndResume();
            uow.setSynchronized(false);
        }else{
            uow.release();
            // Release the session explicitly
            if (getSession().isClientSession()) {
                getSession().release();
            }
        }
        getTransactionController().removeUnitOfWork(getTransaction());
        setUnitOfWork(null);
        setTransaction(null);
        setSession(null);
    }

    /**
     * INTERNAL:
     * Do the appropriate thing for when an exception occurs during SQL issuance.
     * The default thing to do is to simply mark the transaction to be rolled back,
     * for those transaction managers that support this, and rethrow the exception.
     * We hope that the exception will do the trick for those that do not allow
     * marking rollback.
     *
     * This method may optionally be overridden by concrete subclass implementations.
     * Different transaction manager vendors may have different reactions to exceptions
     * that get signalled during the commit phase of synchronization.
     */
    public void handleException(RuntimeException exception) {
        // Don't do this just yet, since some may not be able to handle it
        //	getTransactionController().markTransactionForRollback();
        throw exception;
    }

    protected AbstractTransactionController getTransactionController() {
        return controller;
    }

    protected void setTransactionController(AbstractTransactionController newController) {
        controller = newController;
    }

    protected Object getTransaction() {
        return transaction;
    }

    protected void setTransaction(Object transaction) {
        this.transaction = transaction;
    }

    protected AbstractSession getSession() {
        return session;
    }

    protected void setSession(AbstractSession session) {
        this.session = session;
    }

    protected UnitOfWorkImpl getUnitOfWork() {
        return unitOfWork;
    }

    protected void setUnitOfWork(UnitOfWorkImpl unitOfWork) {
        this.unitOfWork = unitOfWork;
    }
}