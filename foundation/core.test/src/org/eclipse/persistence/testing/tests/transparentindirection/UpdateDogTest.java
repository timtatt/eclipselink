/*******************************************************************************
 * Copyright (c) 1998, 2007 Oracle. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0, which accompanies this distribution
 * and is available at http://www.eclipse.org/legal/epl-v10.html.
 *
 * Contributors:
 *     Oracle - initial API and implementation from Oracle TopLink
 ******************************************************************************/  
package org.eclipse.persistence.testing.tests.transparentindirection;

import org.eclipse.persistence.expressions.*;
import org.eclipse.persistence.sessions.*;
import org.eclipse.persistence.testing.framework.*;
import org.eclipse.persistence.testing.models.transparentindirection.*;

public class UpdateDogTest extends TransactionalTestCase {
    public Dog theDog;
    public SalesRep newRep;

    public void test() {
        Session session = getSession();
        UnitOfWork uow = session.acquireUnitOfWork();
        theDog = (Dog)uow.readObject(Dog.class);
        ExpressionBuilder eb = new ExpressionBuilder();
        newRep = (SalesRep)uow.readObject(SalesRep.class, eb.get("name").notEqual(theDog.getOwner().getKey()));
        theDog.setOwner(newRep);
        try {
            uow.commit();
        } catch (org.eclipse.persistence.exceptions.DatabaseException ex) {
            throw new TestErrorException(ex.toString());
        }
    }

    public void verify() {
        Session session = getSession();
        session.getIdentityMapAccessor().initializeIdentityMap(Dog.class);
        theDog = (Dog)session.readObject(Dog.class);
        if (newRep.getKey() != theDog.getOwner().getKey()) {
            throw new TestErrorException("The Dog object was not properly updated!");
        }
    }
}