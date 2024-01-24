/*
 * Copyright (c) 1998, 2024 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0,
 * or the Eclipse Distribution License v. 1.0 which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: EPL-2.0 OR BSD-3-Clause
 */

// Contributors:
// October 30, 2012
package org.eclipse.persistence.testing.jaxb.annotations.xmltransformation;

import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

import org.eclipse.persistence.oxm.annotations.XmlTransformation;

@XmlRootElement
@XmlType(propOrder={"id","anotherID"})
public class SimpleRoot {
    public int id;

    @XmlTransformation
    public int getAnotherID(){
        return 5;
    }

    public boolean equals(Object obj){
        if(obj instanceof SimpleRoot){
            SimpleRoot compareObj = (SimpleRoot)obj;
            return id == compareObj.id;
        }
        return false;
    }
}
