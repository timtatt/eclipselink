/*******************************************************************************
 * Copyright (c) 1998, 2007 Oracle. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0, which accompanies this distribution
 * and is available at http://www.eclipse.org/legal/epl-v10.html.
 *
 * Contributors:
 *     Oracle - initial API and implementation from Oracle TopLink
 ******************************************************************************/  
package org.eclipse.persistence.testing.tests.expressions;

import org.eclipse.persistence.expressions.*;

public class ReadAllOuterJoinExpressionTest extends ReadAllExpressionTest {

    public ReadAllOuterJoinExpressionTest(Class referenceClass, int originalObjectsSize) {
        super(referenceClass, originalObjectsSize);
    }

    public void checkOuterJoinSupport() {
        // Most have some level of support, even dbase.
    }

    public void reset() throws Exception {
        super.reset();
        rollbackTransaction();
        getSession().getIdentityMapAccessor().initializeAllIdentityMaps();
    }

    protected void setup() {
        super.setup();
        // Make one of the employees have no address.
        beginTransaction();
        org.eclipse.persistence.sessions.UnitOfWork uow = getSession().acquireUnitOfWork();
        org.eclipse.persistence.testing.models.employee.domain.Employee employee = (org.eclipse.persistence.testing.models.employee.domain.Employee)uow.readObject(org.eclipse.persistence.testing.models.employee.domain.Employee.class, new ExpressionBuilder().get("firstName").like("Bob%"));
        employee.setAddress(null);
        uow.commit();
        getSession().getIdentityMapAccessor().initializeAllIdentityMaps();
        checkOuterJoinSupport();
    }
}