/*******************************************************************************
 * Copyright (c) 1998, 2007 Oracle. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0, which accompanies this distribution
 * and is available at http://www.eclipse.org/legal/epl-v10.html.
 *
 * Contributors:
 *     Oracle - initial API and implementation from Oracle TopLink
 ******************************************************************************/  
package org.eclipse.persistence.testing.tests.jpa.complexaggregate;

import org.eclipse.persistence.sessions.DatabaseSession;
import org.eclipse.persistence.testing.tests.jpa.CMP3TestModel;
import org.eclipse.persistence.testing.models.jpa.complexaggregate.ComplexAggregateTableCreator;

/**
 * <p><b>Purpose</b>: To collect the tests that will test specifics of our
 * EJB3.0 implementation through the use of the EntityContainer.  In order for
 * this test model to work correctly the EntityContainer must be initialized
 * thought the comandline agent.
 */
public class CMP3ComplexAggregateTestModel extends CMP3TestModel {
    public void setup() {
        super.setup();
        new ComplexAggregateTableCreator().replaceTables(getServerSession());
    }

    public void addTests() {
        addTest(new AggregatePrimaryKeyTest());
        addTest(new AggregatePrimaryKeyOrderByTest());
        addTest(new NestedAggregateTest());
    }
}