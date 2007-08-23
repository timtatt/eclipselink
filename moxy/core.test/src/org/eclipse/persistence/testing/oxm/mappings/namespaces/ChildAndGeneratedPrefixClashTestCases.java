/*******************************************************************************
 * Copyright (c) 1998, 2007 Oracle. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0, which accompanies this distribution
 * and is available at http://www.eclipse.org/legal/epl-v10.html.
 *
 * Contributors:
 *     Oracle - initial API and implementation from Oracle TopLink
 ******************************************************************************/  
package org.eclipse.persistence.testing.oxm.mappings.namespaces;

import java.util.ArrayList;
import java.util.List;
import org.eclipse.persistence.oxm.XMLLogin;
import org.eclipse.persistence.oxm.XMLRoot;
import org.eclipse.persistence.testing.oxm.mappings.XMLMappingTestCases;

public class ChildAndGeneratedPrefixClashTestCases extends XMLMappingTestCases {
    private final static String XML_RESOURCE = "org/eclipse/persistence/testing/oxm/mappings/namespaces/ChildAndGeneratedConflict.xml";

    public ChildAndGeneratedPrefixClashTestCases(String name) throws Exception {
        super(name);
        setControlDocument(XML_RESOURCE);
        org.eclipse.persistence.sessions.Project p =new ChildAndGeneratedPrefixClashProject();
        XMLLogin login = new XMLLogin();
        login.setEqualNamespaceResolvers(false);
        p.setDatasourceLogin(login);
        setProject(p);
        //xmlMarshaller.setShouldWriteExtraNamespaces(true);
    }

    protected Object getControlObject() {
        Root theRoot = new Root();
                
        Company company = new Company();
        company.setCompanyName("theCompany1");
        
                                          
        List depts = new ArrayList();
        Department dept1 = new Department();
        dept1.setDeptName("dept1");
        
        
        List teams = new ArrayList();
        Team team1 = new Team();
        team1.setTeamName("team1");
        //XMLRoot teamXMLRoot = new XMLRoot();
       // teamXMLRoot.setObject(team1);
        //teamXMLRoot.setLocalName("teamXMLRoot");
        //teamXMLRoot.setNamespaceURI("http://www.oracle.com/teamNS0");
        //teams.add(teamXMLRoot);
        teams.add(team1);
        dept1.setTeams(teams);
        
        XMLRoot deptXMLRoot = new XMLRoot();
        deptXMLRoot.setObject(dept1);
        deptXMLRoot.setLocalName("deptXMLRoot");
        deptXMLRoot.setNamespaceURI("http://www.oracle.com/deptNS");
        
        depts.add(deptXMLRoot);
        company.setDepartments(depts);
        
        List companies = new ArrayList();
        XMLRoot companyXMLRoot = new XMLRoot();
        companyXMLRoot.setObject(company);
        companyXMLRoot.setLocalName("companyXMLRoot");
        companyXMLRoot.setNamespaceURI("http://www.oracle.com/companyNS");
        companies.add(companyXMLRoot);
        
        theRoot.setCompanies(companies);
        
        return theRoot;
    }
    
     public static void main(String[] args) {
        String[] arguments = { "-c", "org.eclipse.persistence.testing.oxm.mappings.namespaces.ChildAndGeneratedPrefixClashTestCases" };
        junit.textui.TestRunner.main(arguments);
    }
}