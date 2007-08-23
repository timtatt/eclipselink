/*******************************************************************************
 * Copyright (c) 1998, 2007 Oracle. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0, which accompanies this distribution
 * and is available at http://www.eclipse.org/legal/epl-v10.html.
 *
 * Contributors:
 *     Oracle - initial API and implementation from Oracle TopLink
 ******************************************************************************/  
package org.eclipse.persistence.testing.oxm.mappings.simpletypes.childcollection;

// TopLink imports
import org.eclipse.persistence.oxm.XMLDescriptor;
import org.eclipse.persistence.oxm.mappings.XMLCompositeCollectionMapping;
import org.eclipse.persistence.oxm.mappings.XMLCompositeObjectMapping;
import org.eclipse.persistence.oxm.mappings.XMLDirectMapping;
import org.eclipse.persistence.sessions.Project;

public class EmployeeProject extends Project {

	public EmployeeProject() {
		super();
		this.addDescriptor(getEmployeeDescriptor());
		this.addDescriptor(getPhoneDescriptor());
	}

	XMLDescriptor getEmployeeDescriptor() {
		XMLDescriptor xmlDescriptor = new XMLDescriptor();
		xmlDescriptor.setJavaClass(Employee.class);
		xmlDescriptor.setDefaultRootElement("employee");

		XMLDirectMapping mapping = new XMLDirectMapping();
		mapping.setAttributeName("name");
		mapping.setXPath("name/text()");
		xmlDescriptor.addMapping(mapping);

		XMLCompositeCollectionMapping cmapping = new XMLCompositeCollectionMapping();
		cmapping.setAttributeName("phones");
		cmapping.setReferenceClass(Phone.class);
		cmapping.useCollectionClass(java.util.Vector.class);
		cmapping.setXPath("phone-no");
		xmlDescriptor.addMapping(cmapping);

		return xmlDescriptor;
	}

	XMLDescriptor getPhoneDescriptor() {
		XMLDescriptor xmlDescriptor = new XMLDescriptor();
		xmlDescriptor.setJavaClass(Phone.class);
		
		XMLDirectMapping mapping = new XMLDirectMapping();
		mapping.setAttributeName("number");
		mapping.setXPath("text()");
		xmlDescriptor.addMapping(mapping);
		
		return xmlDescriptor;
	}
}
