/*******************************************************************************
 * Copyright (c) 1998, 2007 Oracle. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0, which accompanies this distribution
 * and is available at http://www.eclipse.org/legal/epl-v10.html.
 *
 * Contributors:
 *     Oracle - initial API and implementation from Oracle TopLink
 ******************************************************************************/  
package org.eclipse.persistence.testing.tests.weaving;

// J2SE imports
import java.util.*;

// TopLink Testing Framework
import org.eclipse.persistence.testing.framework.*;

public class RelationshipWeavingTestModel extends TestModel {
	
    public RelationshipWeavingTestModel () {
        setDescription("Tests weaved Relationship model - ValueHolders, events for collections");
    }
    
    public void addTests() {
		junit.framework.TestSuite testsuite = (junit.framework.TestSuite)RelationshipWeaverTestSuite.suite();
		for (Enumeration e = testsuite.tests(); e.hasMoreElements();) {
			junit.framework.TestCase testcase =	(junit.framework.TestCase)e.nextElement();
			addTest(new JUnitTestCase(testcase));
		}
    }

}