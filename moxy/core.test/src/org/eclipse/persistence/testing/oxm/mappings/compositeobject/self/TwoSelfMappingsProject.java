/*******************************************************************************
 * Copyright (c) 1998, 2007 Oracle. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0, which accompanies this distribution
 * and is available at http://www.eclipse.org/legal/epl-v10.html.
 *
 * Contributors:
 *     Oracle - initial API and implementation from Oracle TopLink
 ******************************************************************************/  
package org.eclipse.persistence.testing.oxm.mappings.compositeobject.self;

import org.eclipse.persistence.oxm.XMLDescriptor;
import org.eclipse.persistence.oxm.mappings.XMLCompositeObjectMapping;
import org.eclipse.persistence.oxm.mappings.XMLDirectMapping;

public class TwoSelfMappingsProject extends EmployeeProject {
    public TwoSelfMappingsProject() {
        super();
        addDescriptor(getAddressDescriptor());
    }

    protected XMLDescriptor getEmployeeDescriptor() {
        XMLDescriptor descriptor = super.getEmployeeDescriptor();

        XMLCompositeObjectMapping addressMapping = new XMLCompositeObjectMapping();
        addressMapping.setAttributeName("address");
        addressMapping.setXPath(".");
        addressMapping.setReferenceClass(Address.class);
        descriptor.addMapping(addressMapping);

        return descriptor;
    }

    private XMLDescriptor getAddressDescriptor() {
        XMLDescriptor descriptor = new XMLDescriptor();
        descriptor.setJavaClass(Address.class);

        XMLDirectMapping streetMapping = new XMLDirectMapping();
        streetMapping.setAttributeName("street");
        streetMapping.setXPath("@street");
        descriptor.addMapping(streetMapping);

        XMLDirectMapping cityMapping = new XMLDirectMapping();
        cityMapping.setAttributeName("city");
        cityMapping.setXPath("city/text()");
        descriptor.addMapping(cityMapping);

        return descriptor;
    }
}