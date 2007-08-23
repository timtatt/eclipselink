/*******************************************************************************
 * Copyright (c) 1998, 2007 Oracle. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0, which accompanies this distribution
 * and is available at http://www.eclipse.org/legal/epl-v10.html.
 *
 * Contributors:
 *     Oracle - initial API and implementation from Oracle TopLink
 ******************************************************************************/
package org.eclipse.persistence.testing.models.jpa.advanced;

import javax.persistence.*;

import static javax.persistence.GenerationType.*;
import static javax.persistence.CascadeType.*;
import static javax.persistence.FetchType.*;

@Entity
public class Man {
    private Integer id;
    private PartnerLink partnerLink;

	public Man() {}
    
    @Id
    @GeneratedValue(strategy=SEQUENCE, generator="MAN_SEQUENCE_GENERATOR")
	@SequenceGenerator(name="MAN_SEQUENCE_GENERATOR", sequenceName="MAN_SEQ")
	public Integer getId() { 
        return id; 
    }
    
    @OneToOne(mappedBy="man")
	public PartnerLink getPartnerLink() { 
        return partnerLink; 
    }
    
	public void setId(Integer id) { 
        this.id = id; 
    }
    
    public void setPartnerLink(PartnerLink partnerLink) { 
        this.partnerLink = partnerLink; 
    }
}
