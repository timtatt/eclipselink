/*******************************************************************************
 * Copyright (c) 1998, 2007 Oracle. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0, which accompanies this distribution
 * and is available at http://www.eclipse.org/legal/epl-v10.html.
 *
 * Contributors:
 *     Oracle - initial API and implementation from Oracle TopLink
 ******************************************************************************/  
package org.eclipse.persistence.testing.tests.validation;

import java.util.Map;

import org.eclipse.persistence.indirection.ValueHolder;


public class Employee {
    public int id;
    public String name;
    public String gender;
    public ValueHolder address;
    public Map phoneNumbers;

    public Employee() {
        super();
    }

    public ValueHolder getAddress() {
        return address;
    }

    public int getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setAddress(ValueHolder address) {
        this.address = address;
    }

    public void setID(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
