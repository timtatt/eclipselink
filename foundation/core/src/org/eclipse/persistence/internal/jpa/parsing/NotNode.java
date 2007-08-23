/*******************************************************************************
 * Copyright (c) 1998, 2007 Oracle. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0, which accompanies this distribution
 * and is available at http://www.eclipse.org/legal/epl-v10.html.
 *
 * Contributors:
 *     Oracle - initial API and implementation from Oracle TopLink
 ******************************************************************************/  
package org.eclipse.persistence.internal.jpa.parsing;

import org.eclipse.persistence.expressions.*;

/**
 * INTERNAL
 * <p><b>Purpose</b>: Represent a NOT
 * <p><b>Responsibilities</b>:<ul>
 * <li> Generate the correct expression for a NOT
 * </ul>
 *    @author Jon Driscoll and Joel Lucuik
 *    @since TopLink 4.0
 */
public class NotNode extends LogicalOperatorNode {

    /**
     * Return a new NotNode.
     */
    public NotNode() {
        super();
    }

    /**
     * INTERNAL
     * Validate node and calculate its type.
     */
    public void validate(ParseTreeContext context) {
        TypeHelper typeHelper = context.getTypeHelper();
        if (left != null) {
            left.validate(context);
            left.validateParameter(context, typeHelper.getBooleanType());
        }
        setType(typeHelper.getBooleanType());
    }

    /**
     * INTERNAL
     * Return a TopLink expression by calling generateExpression on the right node and adding .not()
     * to the returned expression
     */
    public Expression generateExpression(GenerationContext context) {
        return getLeft().generateExpression(context).not();
    }

    /**
     * INTERNAL
     * Is this node a Not node
     */
    public boolean isNotNode() {
        return true;
    }
}
