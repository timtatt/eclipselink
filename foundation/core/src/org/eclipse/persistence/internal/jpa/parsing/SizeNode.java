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
import org.eclipse.persistence.exceptions.JPQLException;

/**
 * INTERNAL
 * <p><b>Purpose</b>: Represent a SIZE function
 * <p><b>Responsibilities</b>:<ul>
 * <li> Generate the correct expression for SIZE
 * </ul>
 */
public class SizeNode extends ArithmeticFunctionNode {

    /**
     * Return a new SizeNode.
     */
    public SizeNode() {
        super();
    }

    /**
     * INTERNAL
     * Validate node and calculate its type.
     */
    public void validate(ParseTreeContext context) {
        if (left != null) {
            left.validate(context);
        }
        TypeHelper typeHelper = context.getTypeHelper();
        setType(typeHelper.getIntType());
    }

    /**
     * INTERNAL
     * Generate the TopLink expression for this node
     */
    public Expression generateExpression(GenerationContext context) {
        DotNode dotNode = (DotNode)getLeft();
        Node prefix = dotNode.getLeft();
        String variableName = ((AttributeNode)dotNode.getRight()).getAttributeName();

        // check whether variable denotes a collection valued field
        if (!dotNode.endsWithCollectionField(context)) {
            throw JPQLException.invalidSizeArgument(
                context.getParseTreeContext().getQueryInfo(), 
                getLine(), getColumn(), variableName);
        }
        Expression whereClause = prefix.generateExpression(context);
        whereClause = whereClause.size(variableName);
        return whereClause;
    }

}
