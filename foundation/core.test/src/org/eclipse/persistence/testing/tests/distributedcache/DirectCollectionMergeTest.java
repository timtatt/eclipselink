/*******************************************************************************
 * Copyright (c) 1998, 2007 Oracle. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0, which accompanies this distribution
 * and is available at http://www.eclipse.org/legal/epl-v10.html.
 *
 * Contributors:
 *     Oracle - initial API and implementation from Oracle TopLink
 ******************************************************************************/  
package org.eclipse.persistence.testing.tests.distributedcache;

import org.eclipse.persistence.testing.models.employee.domain.Employee;
import org.eclipse.persistence.sessions.UnitOfWork;

public class DirectCollectionMergeTest extends DistributedCacheMergeTest {
    public DirectCollectionMergeTest() {
        super();
    }

    protected void modifyCollection(UnitOfWork uow, Object objectToModify) {
        ((Employee)objectToModify).addResponsibility((String)newItemForCollection());
    }

    protected int getCollectionSize(Object rootObject) {
        return ((Employee)rootObject).getResponsibilitiesList().size();
    }
/*
    protected Object buildOriginalObject() {
        //return "one more thing to do";
    }
		*/
		
		 protected Object buildOriginalObject() {
        Employee emp = new Employee();
        emp.setFirstName("Sally");
        emp.setLastName("Hamilton");
        emp.setFemale();
        return emp;
    }

    protected Object newItemForCollection() {
      return "one more thing to do";       
    }
}