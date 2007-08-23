/*******************************************************************************
 * Copyright (c) 1998, 2007 Oracle. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0, which accompanies this distribution
 * and is available at http://www.eclipse.org/legal/epl-v10.html.
 *
 * Contributors:
 *     Oracle - initial API and implementation from Oracle TopLink
 ******************************************************************************/  
package org.eclipse.persistence.testing.tests.validation;

/**
 * This class is used to test an object without a descriptor in Unit of Work
 */
public class UOWWithoutDescriptorTest extends ExceptionTest {
    public void setup() {
        this.expectedException = org.eclipse.persistence.exceptions.ValidationException.missingDescriptor(null);
    }

    public void test() {
        try {
            org.eclipse.persistence.sessions.UnitOfWork uow = getSession().acquireUnitOfWork();
            uow.registerObject(new String());
            uow.commit();

        } catch (org.eclipse.persistence.exceptions.EclipseLinkException exception) {
            caughtException = exception;
        }
    }
}
