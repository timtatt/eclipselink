/*
 * Copyright (c) 2011, 2021 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0,
 * or the Eclipse Distribution License v. 1.0 which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: EPL-2.0 OR BSD-3-Clause
 */

// Contributors:
//     Gordon Yorke - Initial development
//
package org.eclipse.persistence.internal.jpa.querydef;

import jakarta.persistence.criteria.Expression;

/**
 * <p>
 * <b>Purpose</b>: Represents a Selection in the Criteria API implementation hierarchy.
 * <p>
 * <b>Description</b>: An InternalSelection has the EclipseLink expression representation of the
 * Criteria API expressions.  A special interface was created because Subqueries can be selections but are not in the
 * ExpressionImpl hierarchy
 *
 * @see jakarta.persistence.criteria Expression
 *
 * @author gyorke
 * @since EclipseLink 1.2
 */
public interface InternalSelection {

    void findRootAndParameters(CommonAbstractCriteriaImpl criteriaQuery);

    org.eclipse.persistence.expressions.Expression getCurrentNode();

    boolean isFrom();
    boolean isRoot();
    boolean isConstructor();

    // Shortcut to return current expression node
    static org.eclipse.persistence.expressions.Expression currentNode(Expression<?> expression) {
        return ((InternalSelection)expression).getCurrentNode();
    }

}
