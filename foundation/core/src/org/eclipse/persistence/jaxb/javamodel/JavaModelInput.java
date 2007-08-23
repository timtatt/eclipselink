/*******************************************************************************
 * Copyright (c) 1998, 2007 Oracle. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0, which accompanies this distribution
 * and is available at http://www.eclipse.org/legal/epl-v10.html.
 *
 * Contributors:
 *     Oracle - initial API and implementation from Oracle TopLink
 ******************************************************************************/  
package org.eclipse.persistence.jaxb.javamodel;

/**
 * INTERNAL:
 * <p><b>Purpose:</b>Provide a pluggable method for implementations of the 
 * TopLink JAXB 2.0 Java model to be used with the TopLinkJAXB20Generator.
 * 
 * <p><b>Responsibilities:</b>
 * <ul>
 * <li>Return an array of JavaClass objects to be used by the generator</li>
 * <li>Return the JavaModel to be used during generation</li>
 * </ul>
 * 
 * @since Oracle TopLink 11.1.1.0.0
 * @see org.eclipse.persistence.jaxb20.javamodel.JavaClass 
 * @see org.eclipse.persistence.jaxb20.javamodel.JavaModel 
 */
public interface JavaModelInput {
    public JavaClass[] getJavaClasses();
    public JavaModel getJavaModel();
}