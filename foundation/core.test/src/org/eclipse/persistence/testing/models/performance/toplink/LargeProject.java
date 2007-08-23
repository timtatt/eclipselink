/*******************************************************************************
 * Copyright (c) 1998, 2007 Oracle. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0, which accompanies this distribution
 * and is available at http://www.eclipse.org/legal/epl-v10.html.
 *
 * Contributors:
 *     Oracle - initial API and implementation from Oracle TopLink
 ******************************************************************************/  
package org.eclipse.persistence.testing.models.performance.toplink;

import java.io.*;
import java.sql.Timestamp;

/**
 * <b>Purpose</b>: Larger scale projects within the Employee Demo
 * <p><b>Description</b>: LargeProject is a concrete subclass of Project. It is instantiated for Projects with type = 'L'. The additional
 * information (budget, & milestoneVersion) are mapped from the LPROJECT table.
 * @see Project
 */
public class LargeProject extends Project {
    public double budget;
    public Timestamp milestoneVersion;

    public LargeProject() {
        this.budget = 0.0;
    }

    public double getBudget() {
        return budget;
    }

    public Timestamp getMilestoneVersion() {
        return milestoneVersion;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public void setMilestoneVersion(Timestamp milestoneVersion) {
        this.milestoneVersion = milestoneVersion;
    }

    /**
     * Print the project's data.
     */
    public String toString() {
        StringWriter writer = new StringWriter();

        writer.write("Large Project: ");
        writer.write(getName());
        writer.write(" ");
        writer.write(getDescription());
        writer.write(" " + getBudget());
        writer.write(" ");
        writer.write(String.valueOf(getMilestoneVersion()));
        return writer.toString();
    }
}