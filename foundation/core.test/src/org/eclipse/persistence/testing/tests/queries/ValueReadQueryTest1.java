/*******************************************************************************
 * Copyright (c) 1998, 2007 Oracle. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0, which accompanies this distribution
 * and is available at http://www.eclipse.org/legal/epl-v10.html.
 *
 * Contributors:
 *     Oracle - initial API and implementation from Oracle TopLink
 ******************************************************************************/  
package org.eclipse.persistence.testing.tests.queries;

import java.util.*;

import org.eclipse.persistence.testing.framework.*;
import org.eclipse.persistence.queries.*;

/**
 * Test value read queries with various container policies.
 */
public class ValueReadQueryTest1 extends TestCase {
    int stackValue;

    public ValueReadQueryTest1() {
        setDescription("This tests value read queries with various container policies.");
    }

    public ValueReadQuery buildNewQuery() {
        return new ValueReadQuery("select count(*) from EMPLOYEE");
    }

    public void test() {
        this.testStackContainerPolicy();
        this.testCursoredStreamPolicy();
    }

    /**
     * assume the stack has already been populated by the previous test
     */
    public void testCursoredStreamPolicy() {

        // maybe there is some way to have this query always return 1...
        ValueReadQuery sizeQuery = new ValueReadQuery("select count(*) from EMPLOYEE");

        ValueReadQuery query = this.buildNewQuery();
        query.useCursoredStream(1, 1, sizeQuery);

        int streamValue = ((Number)getSession().executeQuery(query)).intValue();
        // if we get here, we must not have generated a ClassCastException

        if (streamValue != stackValue) {
            throw new TestErrorException("stream does not match stack - " + "expected: " + stackValue + 
                                         " actual: " + streamValue);
        }
    }

    public void testStackContainerPolicy() {
        ValueReadQuery query = this.buildNewQuery();
        query.useCollectionClass(Stack.class);

        stackValue = ((Number)getSession().executeQuery(query)).intValue();
        // if we get here, we must not have generated a ClassCastException
        if (stackValue == 0) {
            throw new TestErrorException("missing data");
        }
    }
}
