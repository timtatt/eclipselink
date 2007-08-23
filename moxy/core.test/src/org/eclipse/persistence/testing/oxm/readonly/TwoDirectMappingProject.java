/*******************************************************************************
 * Copyright (c) 1998, 2007 Oracle. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0, which accompanies this distribution
 * and is available at http://www.eclipse.org/legal/epl-v10.html.
 *
 * Contributors:
 *     Oracle - initial API and implementation from Oracle TopLink
 ******************************************************************************/  
package org.eclipse.persistence.testing.oxm.readonly;

import org.eclipse.persistence.sessions.Project;
import org.eclipse.persistence.oxm.*;
import org.eclipse.persistence.oxm.mappings.XMLDirectMapping;


public class TwoDirectMappingProject extends Project 
{
	public TwoDirectMappingProject() 
	{
		super();
		addEmployeeDescriptor();
	}
	
	public void addEmployeeDescriptor() 
	{
		XMLDescriptor descriptor = new XMLDescriptor();
		descriptor.setDefaultRootElement("employee");
		descriptor.setJavaClass(Employee.class);
		
		XMLDirectMapping firstNameMapping = new XMLDirectMapping();
		firstNameMapping.setAttributeName("firstName");
		firstNameMapping.setXPath("first-name/text()");
		firstNameMapping.readOnly();
		descriptor.addMapping(firstNameMapping);
		
		XMLDirectMapping firstNameMapping2 = new XMLDirectMapping();
		firstNameMapping2.setAttributeName("firstName2");
		firstNameMapping2.setXPath("first-name/text()");
		descriptor.addMapping(firstNameMapping2);
		
		this.addDescriptor(descriptor);
	}
}