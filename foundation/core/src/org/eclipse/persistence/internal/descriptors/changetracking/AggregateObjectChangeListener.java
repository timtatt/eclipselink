/*******************************************************************************
 * Copyright (c) 1998, 2007 Oracle. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0, which accompanies this distribution
 * and is available at http://www.eclipse.org/legal/epl-v10.html.
 *
 * Contributors:
 *     Oracle - initial API and implementation from Oracle TopLink
 ******************************************************************************/  
package org.eclipse.persistence.internal.descriptors.changetracking;

import java.beans.*;

/**
 * <p>
 * <b>Purpose</b>: Define a listener for object change tracking.
 * <p>
 * <b>Description</b>: Listener is notified on a PropertyChangeEvent from the object it belongs to.
 * <p>
 * <b>Responsibilities</b>: Set the flag to true when there is any change in the object.
 * <ul>
 * </ul>
 */
public class AggregateObjectChangeListener extends ObjectChangeListener {
    protected ObjectChangeListener parentListener;
    protected String parentAttributeName;

    /**
     * INTERNAL:
     * Create a ObjectChangeListener
     */
    public AggregateObjectChangeListener(ObjectChangeListener parentListener, String parentAttribute) {
        super();
        this.parentListener = parentListener;
        this.parentAttributeName = parentAttribute;
    }

    /**
     * PUBLIC:
     * This method turns marks the object as changed.
     */
    public void internalPropertyChange(PropertyChangeEvent evt) {
        hasChanges = true;
        this.parentListener.internalPropertyChange(new PropertyChangeEvent(evt.getSource(), this.parentAttributeName, evt.getOldValue(), evt.getNewValue()));
    }

}