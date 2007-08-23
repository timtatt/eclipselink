/*******************************************************************************
 * Copyright (c) 1998, 2007 Oracle. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0, which accompanies this distribution
 * and is available at http://www.eclipse.org/legal/epl-v10.html.
 *
 * Contributors:
 *     Oracle - initial API and implementation from Oracle TopLink
 ******************************************************************************/  
package deprecated.services.mbean;

import org.eclipse.persistence.internal.sessions.AbstractSession;
import deprecated.services.RuntimeServices;

/**
 * <p>
 * <b>Purpose</b>: Provide a dynamic interface into the TopLink Session.
 * <p>
 * <b>Description</b>: This class is ment to provide a framework for gaining access to configuration
 * of the TopLink Session during runtime.  It will provide the basis for developement
 * of a JMX service and possibly other frameworks.
 * <ul>
 * <li>
 * </ul>
 *
 * @deprecated Will be replaced by a server-specific equivalent for deprecated.services.oc4j.Oc4jRuntimeServices
 * @see deprecated.services.oc4j.Oc4jRuntimeServices
 */
public class MBeanRuntimeServices extends RuntimeServices implements MBeanRuntimeServicesMBean {
    public MBeanRuntimeServices(AbstractSession session) {
        super(session);
    }
}