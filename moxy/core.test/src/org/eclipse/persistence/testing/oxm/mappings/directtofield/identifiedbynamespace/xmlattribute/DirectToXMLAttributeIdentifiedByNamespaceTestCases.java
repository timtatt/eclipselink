/*******************************************************************************
 * Copyright (c) 1998, 2007 Oracle. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0, which accompanies this distribution
 * and is available at http://www.eclipse.org/legal/epl-v10.html.
 *
 * Contributors:
 *     Oracle - initial API and implementation from Oracle TopLink
 ******************************************************************************/  
package org.eclipse.persistence.testing.oxm.mappings.directtofield.identifiedbynamespace.xmlattribute;

import org.eclipse.persistence.testing.oxm.mappings.XMLMappingTestCases;
import org.eclipse.persistence.testing.oxm.mappings.directtofield.Employee;

public class DirectToXMLAttributeIdentifiedByNamespaceTestCases extends XMLMappingTestCases {

  private final static String XML_RESOURCE = "org/eclipse/persistence/testing/oxm/mappings/directtofield/identifiedbynamespace/xmlattribute/DirectToXMLAttributeIdentifiedByNamespace.xml";
  private final static String CONTROL_FIRST_NAME = "Jane";
  private final static String CONTROL_LAST_NAME = "Doe";

  public DirectToXMLAttributeIdentifiedByNamespaceTestCases(String name) throws Exception {
    super(name);
    setControlDocument(XML_RESOURCE);
		setProject(new DirectToXMLAttributeIdentifiedByNamespaceProject());
	}

  protected Object getControlObject() {
    Employee employee = new Employee();
    employee.setFirstName(CONTROL_FIRST_NAME);
    employee.setLastName(CONTROL_LAST_NAME);
    return employee;
  }

}
