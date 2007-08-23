/*******************************************************************************
 * Copyright (c) 1998, 2007 Oracle. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0, which accompanies this distribution
 * and is available at http://www.eclipse.org/legal/epl-v10.html.
 *
 * Contributors:
 *     Oracle - initial API and implementation from Oracle TopLink
 ******************************************************************************/  
package org.eclipse.persistence.internal.sessions.factories.model.log;


/**
 * INTERNAL:
 */
public abstract class LogConfig {
    private LoggingOptionsConfig m_loggingOptions;

    public LogConfig() {
    }

    public void setLoggingOptions(LoggingOptionsConfig loggingOptions) {
        m_loggingOptions = loggingOptions;
    }

    public LoggingOptionsConfig getLoggingOptions() {
        return m_loggingOptions;
    }
}