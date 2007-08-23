/*******************************************************************************
 * Copyright (c) 1998, 2007 Oracle. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0, which accompanies this distribution
 * and is available at http://www.eclipse.org/legal/epl-v10.html.
 *
 * Contributors:
 *     Oracle - initial API and implementation from Oracle TopLink
 ******************************************************************************/  
package org.eclipse.persistence.testing.oxm.xmlbinder;

import org.eclipse.persistence.testing.oxm.xmlbinder.anymappingtests.XMLBinderAnyCollectionTestCases;
import org.eclipse.persistence.testing.oxm.xmlbinder.basictests.XMLBinderBasicTestCases;
import org.eclipse.persistence.testing.oxm.xmlbinder.anymappingtests.XMLBinderAnyObjectTestCases;
import org.eclipse.persistence.testing.oxm.xmlbinder.keybasedmappingtests.XMLBinderKeyBasedMappingTests;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 *  @version $Header: DocumentPreservationTestSuite.java 06-jun-2005.13:43:48 dmahar Exp $
 *  @author  mmacivor
 *  @since   release specific (what release of product did this appear in)
 */
public class XMLBinderTestSuite extends TestCase {
    public XMLBinderTestSuite(String name) {
        super(name);
    }

    public static void main(String[] args) {
        junit.textui.TestRunner.main(new String[] { "-c", "org.eclipse.persistence.testing.oxm.xmlbinder.XMLBinderTestSuite" });
    }

    public static Test suite() {
        TestSuite suite = new TestSuite("Document Preservation Test Suite");
        suite.addTestSuite(XMLBinderBasicTestCases.class);
        suite.addTestSuite(XMLBinderAnyCollectionTestCases.class);
        suite.addTestSuite(XMLBinderAnyObjectTestCases.class);
        suite.addTestSuite(XMLBinderKeyBasedMappingTests.class);
        return suite;
    }
}
