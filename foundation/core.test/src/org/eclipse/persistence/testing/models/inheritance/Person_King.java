/*******************************************************************************
 * Copyright (c) 1998, 2007 Oracle. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0, which accompanies this distribution
 * and is available at http://www.eclipse.org/legal/epl-v10.html.
 *
 * Contributors:
 *     Oracle - initial API and implementation from Oracle TopLink
 ******************************************************************************/  
package org.eclipse.persistence.testing.models.inheritance;

public class Person_King {
    private int id;
    private String name;

    public Person_King() {
        super();
    }

    public static Person_King exp1() {
        Person_King p1 = new Person_King();
        p1.setId(1);
        p1.setName("Jack");
        return p1;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int theId) {
        id = theId;
    }

    public void setName(String theName) {
        name = theName;

    }
}