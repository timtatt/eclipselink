/*******************************************************************************
 * Copyright (c) 1998, 2007 Oracle. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0, which accompanies this distribution
 * and is available at http://www.eclipse.org/legal/epl-v10.html.
 *
 * Contributors:
 *     Oracle - initial API and implementation from Oracle TopLink
 ******************************************************************************/  
package org.eclipse.persistence.testing.jaxb.xmlvalue;

import javax.xml.bind.annotation.*;

@XmlRootElement(name="phone")
@XmlType(name="phone-number")
public class PhoneNumberWithAtts {

    @XmlValue
    public String number;
        
    @XmlAttribute(name="area-code")
    public String areaCode;
    
    public boolean equals(Object o) {
        if(!(o instanceof PhoneNumberWithAtts) || o == null) {
            return false;
        } else {
            return ((PhoneNumberWithAtts)o).number.equals(this.number) && ((PhoneNumberWithAtts)o).areaCode.equals(this.areaCode);
        }
    }
    
    public String toString() {
        return "EMPLOYEE< (" + areaCode + ") " + number + ">";
    }    
}