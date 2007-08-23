/*******************************************************************************
 * Copyright (c) 1998, 2007 Oracle. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0, which accompanies this distribution
 * and is available at http://www.eclipse.org/legal/epl-v10.html.
 *
 * Contributors:
 *     Oracle - initial API and implementation from Oracle TopLink
 ******************************************************************************/  
package org.eclipse.persistence.internal.localization;


/**
 *  <p>
 * <b>Purpose</b>: This is for any trace related messages
 *
 * @author: Shannon Chen
 * @since OracleAS TopLink 10<i>g</i> (9.0.4)
 */
public class TraceLocalization extends EclipseLinkLocalization {
    public static String buildMessage(String key, Object[] arguments) {
        return buildMessage("TraceLocalization", key, arguments);
    }

    public static String buildMessage(String key) {
        return buildMessage(key, (Object[])null);
    }
}