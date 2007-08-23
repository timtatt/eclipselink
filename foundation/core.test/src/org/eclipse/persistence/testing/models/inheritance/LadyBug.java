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

public class LadyBug {
    protected Integer lb_ID;
    protected Integer lb_numberOfSpots;

    public Integer getLb_ID() {
        return lb_ID;
    }

    public Integer getLb_numberOfSpots() {
        return lb_numberOfSpots;
    }

    public void setLb_ID(Integer param1) {
        lb_ID = param1;
    }

    public void setLb_numberOfSpots(Integer param1) {
        lb_numberOfSpots = param1;
    }
}