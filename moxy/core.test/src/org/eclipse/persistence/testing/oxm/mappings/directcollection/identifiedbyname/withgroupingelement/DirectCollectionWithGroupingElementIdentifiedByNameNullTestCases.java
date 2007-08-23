/*******************************************************************************
 * Copyright (c) 1998, 2007 Oracle. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0, which accompanies this distribution
 * and is available at http://www.eclipse.org/legal/epl-v10.html.
 *
 * Contributors:
 *     Oracle - initial API and implementation from Oracle TopLink
 ******************************************************************************/  
package org.eclipse.persistence.testing.oxm.mappings.directcollection.identifiedbyname.withgroupingelement;

import java.util.Vector;

import org.eclipse.persistence.testing.oxm.mappings.XMLMappingTestCases;
import org.eclipse.persistence.testing.oxm.mappings.directcollection.Employee;

public class DirectCollectionWithGroupingElementIdentifiedByNameNullTestCases extends XMLMappingTestCases 
{
	private final static String XML_RESOURCE = "org/eclipse/persistence/testing/oxm/mappings/directcollection/identifiedbyname/withgroupingelement/DirectCollectionWithGroupingElementNull.xml";
  private final static int CONTROL_ID = 123;

	public DirectCollectionWithGroupingElementIdentifiedByNameNullTestCases(String name) throws Exception
	{
		super(name);
    setControlDocument(XML_RESOURCE);
		setProject(new DirectCollectionWithGroupingElementIdentifiedByNameProject());
	}
	
	protected Object getControlObject() {
		Vector responsibilities = null;

    Employee employee = new Employee();
    employee.setID(CONTROL_ID);
		employee.setResponsibilities(responsibilities);
    return employee;
  }
	/*
	 * Nulls are read in as empty collections.
	 */
	public Object getReadControlObject() {
    Employee employee = new Employee();
    employee.setID(CONTROL_ID);
		employee.setResponsibilities(new Vector());
    return employee;
		
	}
}