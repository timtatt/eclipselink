/*******************************************************************************
 * Copyright (c) 1998, 2007 Oracle. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0, which accompanies this distribution
 * and is available at http://www.eclipse.org/legal/epl-v10.html.
 *
 * Contributors:
 *     Oracle - initial API and implementation from Oracle TopLink
 ******************************************************************************/  
package org.eclipse.persistence.testing.tests.jpql;

import java.util.*;
import org.eclipse.persistence.testing.models.employee.domain.*;

class AbstractSchemaIdentifierEqualsTest extends JPQLParameterTestCase {
    public void setup() {
        Employee emp;
        emp = (Employee)getSomeEmployees().firstElement();

        String ejbqlString = "SELECT OBJECT(emp) FROM Employee emp WHERE emp = ?1";

        setEjbqlString(ejbqlString);
        setOriginalOject(emp);

        Vector parameters = new Vector();
        parameters.add(emp);
        setArguments(parameters);

        Vector myArgumentNames = new Vector();
        myArgumentNames.add("1");
        setArgumentNames(myArgumentNames);

        super.setup();
    }
}