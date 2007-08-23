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
import org.eclipse.persistence.queries.*;
import org.eclipse.persistence.expressions.*;

public class ComplexStringInTest extends org.eclipse.persistence.testing.tests.jpql.JPQLTestCase {
    public void setup() {
        Employee emp1;
        Employee emp2;
        Employee emp3;
        emp1 = (Employee)getSomeEmployees().firstElement();
        emp2 = (Employee)getSomeEmployees().elementAt(1);
        emp3 = (Employee)getSomeEmployees().elementAt(2);

        Vector employees = new Vector();
        Vector fnVector = new Vector();
        fnVector.add(emp1.getFirstName());
        fnVector.add(emp2.getFirstName());
        fnVector.add(emp3.getFirstName());

        ReadAllQuery raq = new ReadAllQuery();
        raq.setReferenceClass(Employee.class);
        ExpressionBuilder eb = new ExpressionBuilder();
        Expression whereClause = eb.get("firstName").in(fnVector);
        raq.setSelectionCriteria(whereClause);
        employees = (Vector)getSession().executeQuery(raq);

        String ejbqlString = "SELECT OBJECT(emp) FROM Employee emp WHERE emp.firstName IN (";
        ejbqlString = ejbqlString + "\"" + emp1.getFirstName() + "\"" + ", ";
        ejbqlString = ejbqlString + "\"" + emp2.getFirstName() + "\"" + ", ";
        ejbqlString = ejbqlString + "\"" + emp3.getFirstName() + "\"";
        ejbqlString = ejbqlString + ")";

        setEjbqlString(ejbqlString);

        setOriginalOject(emp1);

        super.setup();
    }
}