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

/**
 * INTERNAL
 * <p><b>Purpose</b>: Represent a double literal in EJBQL
 * <p><b>Responsibilities</b>:<ul>
 * <li> Generate the correct expression for the double literal
 * </ul>
 *    @author Jon Driscoll and Joel Lucuik
 *    @since TopLink 4.0
 */
public class DoubleLiteralNode extends LiteralNode {

    /** */
    public DoubleLiteralNode() {
        super();
    }

    public DoubleLiteralNode(Double newDouble) {
        super();
        setLiteral(newDouble);
    }

    /**
     * INTERNAL
     * Validate the current node and calculate its type.
     */
    public void validate(ParseTreeContext context) {
        TypeHelper typeHelper = context.getTypeHelper();
        setType(typeHelper.getDoubleType());
    }
}
