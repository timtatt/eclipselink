/*******************************************************************************
 * Copyright (c) 1998, 2007 Oracle. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0, which accompanies this distribution
 * and is available at http://www.eclipse.org/legal/epl-v10.html.
 *
 * Contributors:
 *     Oracle - initial API and implementation from Oracle TopLink
 ******************************************************************************/  
package org.eclipse.persistence.testing.tests.workbenchintegration;

import org.eclipse.persistence.descriptors.ClassDescriptor;
import org.eclipse.persistence.testing.models.employee.domain.Employee;


public class BuildDescriptorIsForInterfaceTrueTest extends ProjectClassGeneratorResultFileTest {
    ClassDescriptor descriptorToModify;

    public BuildDescriptorIsForInterfaceTrueTest() {
        super(new org.eclipse.persistence.testing.models.employee.relational.EmployeeProject(), 
              "descriptor.descriptorIsForInterface();");
        setDescription("Test buildDescriptor() -> descriptorIsForInterface()");
    }

    protected void setup() {
        getSession().getIdentityMapAccessor().initializeAllIdentityMaps();
        descriptorToModify = (ClassDescriptor)project.getDescriptors().get(Employee.class);
        descriptorToModify.descriptorIsForInterface();
    }
}
