/*******************************************************************************
 * Copyright (c) 1998, 2007 Oracle. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0, which accompanies this distribution
 * and is available at http://www.eclipse.org/legal/epl-v10.html.
 *
 * Contributors:
 *     Oracle - initial API and implementation from Oracle TopLink
 ******************************************************************************/  
package org.eclipse.persistence.testing.tests.sessionsxml;

import org.eclipse.persistence.sessions.coordination.TransportManager;
import org.eclipse.persistence.sessions.coordination.corba.sun.SunCORBATransportManager;
import org.eclipse.persistence.sessions.DatabaseSession;
import org.eclipse.persistence.testing.framework.AutoVerifyTestCase;
import org.eclipse.persistence.testing.framework.TestErrorException;
import org.eclipse.persistence.internal.sessions.factories.XMLSessionConfigLoader;
import org.eclipse.persistence.sessions.factories.SessionManager;


/**
 * Tests a basic session xml file that is built and validated against the
 * XML Schema
 * 
 * @author Guy Pelletier
 * @version 1.0
 * @date July 27, 2004
 */
public class SessionsXMLSchemaSunCORBATransportConfigTest extends AutoVerifyTestCase {
    DatabaseSession m_session;

    public SessionsXMLSchemaSunCORBATransportConfigTest() {
        setDescription("Test loading of a basic session xml that uses the SunCorbaTransportManagerConfig");
    }

    public void reset() {
        if (m_session != null) {
            if (m_session.isConnected()) {
                m_session.logout();
            }

            SessionManager.getManager().getSessions().remove(m_session);
            m_session = null;
        }
    }

    public void test() {
        XMLSessionConfigLoader loader = new XMLSessionConfigLoader("org/eclipse/persistence/testing/models/sessionsxml/XMLSchemaSession.xml");

        // don't log in the session
            m_session = (DatabaseSession)SessionManager.getManager().getSession(loader, "SunCorbaTransportManager", getClass().getClassLoader(), false, true); // refresh the session  
    }

    protected void verify() {
        if (m_session == null) {
            throw new TestErrorException("The session read in was null");
        }

        TransportManager tm = m_session.getCommandManager().getTransportManager();

        if (!(tm instanceof SunCORBATransportManager)) {
            throw new TestErrorException("Transport manager was the wrong type");
        }
    }
}
