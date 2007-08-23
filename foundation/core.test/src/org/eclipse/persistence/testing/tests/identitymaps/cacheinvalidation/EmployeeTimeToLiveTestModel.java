/*******************************************************************************
 * Copyright (c) 1998, 2007 Oracle. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0, which accompanies this distribution
 * and is available at http://www.eclipse.org/legal/epl-v10.html.
 *
 * Contributors:
 *     Oracle - initial API and implementation from Oracle TopLink
 ******************************************************************************/  
package org.eclipse.persistence.testing.tests.identitymaps.cacheinvalidation;

import org.eclipse.persistence.testing.models.employee.domain.*;
import org.eclipse.persistence.descriptors.invalidation.*;

public class EmployeeTimeToLiveTestModel extends EmployeeCacheExpiryTestModel {
    public EmployeeTimeToLiveTestModel() {
        setDescription("Test CRUD operations on the Employee model with non expiring Time To Live Cache Expiry.");
        setName("Employee Time To Live Expiry Test Model");
    }

    public void setup() {
        super.setup();
        getSession().getDescriptor(Employee.class).setCacheInvalidationPolicy(new TimeToLiveCacheInvalidationPolicy(10000000));
        getSession().getDescriptor(Address.class).setCacheInvalidationPolicy(new TimeToLiveCacheInvalidationPolicy(10000000));
        getSession().getDescriptor(Project.class).setCacheInvalidationPolicy(new TimeToLiveCacheInvalidationPolicy(10000000));
        getSession().getDescriptor(PhoneNumber.class).setCacheInvalidationPolicy(new TimeToLiveCacheInvalidationPolicy(10000000));
        getSession().getDescriptor(LargeProject.class).setCacheInvalidationPolicy(new TimeToLiveCacheInvalidationPolicy(10000000));
        getSession().getDescriptor(SmallProject.class).setCacheInvalidationPolicy(new TimeToLiveCacheInvalidationPolicy(10000000));
    }
}
