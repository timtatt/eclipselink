/*******************************************************************************
 * Copyright (c) 1998, 2007 Oracle. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0, which accompanies this distribution
 * and is available at http://www.eclipse.org/legal/epl-v10.html.
 *
 * Contributors:
 *     Oracle - initial API and implementation from Oracle TopLink
 ******************************************************************************/  
package org.eclipse.persistence.sessions.changesets;

/**
 * <p>
 * <b>Purpose</b>: Define the Public interface for the Aggregate Change Record.
 * <p>
 * <b>Description</b>: This interface is meant to clarify the public protocol into TopLink.
 */
public interface AggregateChangeRecord extends ChangeRecord {

    /**
     * ADVANCED:
     * This method is used to return the ObjectChangeSet representing the changed Aggregate.
     * @return org.eclipse.persistence.CahngeSets.ObjectChanges
     */
    public ObjectChangeSet getChangedObject();
}