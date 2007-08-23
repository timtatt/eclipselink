/*******************************************************************************
 * Copyright (c) 1998, 2007 Oracle. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0, which accompanies this distribution
 * and is available at http://www.eclipse.org/legal/epl-v10.html.
 *
 * Contributors:
 *     Oracle - initial API and implementation from Oracle TopLink
 ******************************************************************************/  
package org.eclipse.persistence.testing.models.jpa.xml.inheritance.listeners;

import org.eclipse.persistence.testing.models.jpa.xml.inheritance.Bus;

/**
 * A listener for the Bus entity.
 * 
 * It implements the following annotations:
 * - PreRemove
 * - PostRemove
 * - PreUpdate
 * - PostUpdate
 * 
 * It overrides the following annotations:
 * - PrePersist from ListenerSuperclass
 * - PostPersist from FueledVehicleListener
 * 
 * It inherits the following annotations:
 * - PostLoad from Vehicle.
 */
public class BusListener extends ListenerSuperclass {
    public static int PRE_PERSIST_COUNT = 0;
    public static int POST_PERSIST_COUNT = 0;
    public static int PRE_REMOVE_COUNT = 0;
    public static int POST_REMOVE_COUNT = 0;
    public static int PRE_UPDATE_COUNT = 0;
    public static int POST_UPDATE_COUNT = 0;

	public void prePersist(Object bus) {
        PRE_PERSIST_COUNT++;
        ((Bus) bus).addPrePersistCalledListener(this.getClass());
	}
    
	public void postPersist(Object bus) {
        POST_PERSIST_COUNT++;
        ((Bus) bus).addPostPersistCalledListener(this.getClass());
	}

	public void preRemove(Object bus) {
        PRE_REMOVE_COUNT++;
	}

	public void postRemove(Object bus) {
        POST_REMOVE_COUNT++;
	}

	public void preUpdate(Object bus) {
        PRE_UPDATE_COUNT++;
	}

	public void postUpdate(Object bus) {
        POST_UPDATE_COUNT++;
	}
}
