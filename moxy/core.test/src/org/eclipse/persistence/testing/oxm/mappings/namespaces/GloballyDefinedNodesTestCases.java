/*******************************************************************************
 * Copyright (c) 1998, 2007 Oracle. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0, which accompanies this distribution
 * and is available at http://www.eclipse.org/legal/epl-v10.html.
 *
 * Contributors:
 *     Oracle - initial API and implementation from Oracle TopLink
 ******************************************************************************/  
package org.eclipse.persistence.testing.oxm.mappings.namespaces;

import org.eclipse.persistence.testing.oxm.mappings.XMLMappingTestCases;

public class GloballyDefinedNodesTestCases extends XMLMappingTestCases {

  private final static String XML_RESOURCE = "org/eclipse/persistence/testing/oxm/mappings/namespaces/GloballyDefinedNodes.xml";

  private final static int CONTROL_ID = 123;
	private final static String CONTROL_NAME = "Jane Doe";

	public GloballyDefinedNodesTestCases(String name) throws Exception {
		super(name);
    setControlDocument(XML_RESOURCE);
    setProject(new GloballyDefinedNodesProject());
	}

  protected Object getControlObject() {
    Employee employee = new Employee();
    employee.setId(CONTROL_ID);
		employee.setName(CONTROL_NAME);
    return employee;
  }
	
}
