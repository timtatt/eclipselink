/*******************************************************************************
 * Copyright (c) 1998, 2007 Oracle. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0, which accompanies this distribution
 * and is available at http://www.eclipse.org/legal/epl-v10.html.
 *
 * Contributors:
 *     Oracle - initial API and implementation from Oracle TopLink
 ******************************************************************************/  
package org.eclipse.persistence.testing.tests.workbenchintegration;

import org.eclipse.persistence.sessions.Project;
import org.eclipse.persistence.testing.framework.TestErrorException;
import org.eclipse.persistence.sessions.factories.ProjectClassGenerator;
import org.eclipse.persistence.sessions.factories.XMLProjectReader;
import org.eclipse.persistence.sessions.factories.XMLProjectWriter;

/**
 *  @version $Header: WorkbenchIntegrationSystemHelper.java 31-jul-2007.11:40:26 gpelleti Exp $
 *  @author  gpelleti
 *  @since   11g
 */
public class WorkbenchIntegrationSystemHelper {

    /**
     * For the given project, generate the class file, compile it and set
     * it to be the project.
     */
    public static Project buildProjectClass(Project project, String filename) {
        ProjectClassGenerator generator = new ProjectClassGenerator(project, filename, filename + ".java");
        generator.generate();

        try {
            String[] source = { filename + ".java" };
            int result = new com.sun.tools.javac.Main().compile(source);
            if (result != 0) {
                throw new TestErrorException("Project class generation compile failed.");
            }
            Class projectClass = Class.forName(filename);
            return (Project) projectClass.newInstance();
        } catch (Exception exception) {
            throw new RuntimeException(exception.toString());
        }
    }
    
    /**
     * For the given project, generate the project xml and read it back in.
     */
    public static Project buildProjectXML(Project project, String filename) {
        return buildProjectXML(project, filename, project.getClass().getClassLoader());
    }
    
    /**
     * For the given project, generate the project xml and read it back in.
     */
    public static Project buildProjectXML(Project project, String filename, ClassLoader loader) {
        XMLProjectWriter.write(filename + ".xml", project);
        return XMLProjectReader.read(filename + ".xml", loader);
    }
}
