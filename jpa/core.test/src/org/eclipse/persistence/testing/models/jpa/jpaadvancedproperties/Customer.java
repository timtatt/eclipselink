/*******************************************************************************
 * Copyright (c) 1998, 2007 Oracle. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0, which accompanies this distribution
 * and is available at http://www.eclipse.org/legal/epl-v10.html.
 *
 * Contributors:
 *     Oracle - initial API and implementation from Oracle TopLink
 ******************************************************************************/  
package org.eclipse.persistence.testing.models.jpa.jpaadvancedproperties;

import java.util.Vector;
import java.util.HashSet;
import java.util.Collection;
import javax.persistence.*;
import static javax.persistence.GenerationType.*;
import static javax.persistence.CascadeType.*;

public class Customer implements java.io.Serializable{
	private Integer customerId;
	private int version;
	private String city;
	private String name;
	private Collection<Order> orders = new HashSet<Order>();
    private Collection<Customer> controlledCustomers = new HashSet<Customer>();

	public Customer() {}

    public Integer getCustomerId() { 
        return customerId; 
    }
    
    public void setCustomerId(Integer id) { 
        this.customerId = id; 
    }

	public int getVersion() { 
        return version; 
    }
    
	protected void setVersion(int version) {
		this.version = version;
	}

	public String getCity() {
        return city; 
    }
    
    public void setCity(String aCity) { 
        this.city = aCity; 
    }

    public String getName() { 
        return name; 
    }
    
    public void setName(String aName) { 
        this.name = aName; 
    }

    public Collection getOrders() { 
        return orders; 
    }
    
    public void setOrders(Collection<Order> newValue) { 
        this.orders = newValue; 
    }

    public void addOrder(Order anOrder) {
        getOrders().add(anOrder);
		anOrder.setCustomer(this);
    }

    public void removeOrder(Order anOrder) {
        getOrders().remove(anOrder);
    }
    
    public Collection<Customer> getCCustomers() {
        if (controlledCustomers == null) {
            return new Vector();
        }
        
        return controlledCustomers;
    }
    
    public void setCCustomers(Collection<Customer> controlledCustomers) {
        this.controlledCustomers = controlledCustomers;
    }
    
    public void addCCustomer(Customer controlledCustomer) {
        getCCustomers().add(controlledCustomer);
    }
}
