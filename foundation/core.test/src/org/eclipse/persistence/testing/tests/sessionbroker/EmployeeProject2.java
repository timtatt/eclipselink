/*******************************************************************************
 * Copyright (c) 1998, 2007 Oracle. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0, which accompanies this distribution
 * and is available at http://www.eclipse.org/legal/epl-v10.html.
 *
 * Contributors:
 *     Oracle - initial API and implementation from Oracle TopLink
 ******************************************************************************/  
package org.eclipse.persistence.testing.tests.sessionbroker;

import java.util.Vector;

import org.eclipse.persistence.descriptors.RelationalDescriptor;


/**
 * TopLink generated Project class. 
 * <b>WARNING</b>: This code was generated by an automated tool.
 * Any changes will be lost when the code is re-generated
 */
public class EmployeeProject2 extends org.eclipse.persistence.sessions.Project {

    /**
     * <b>WARNING</b>: This code was generated by an automated tool.
     * Any changes will be lost when the code is re-generated
     */
    public EmployeeProject2() {
        applyPROJECT();
        applyLOGIN();
        buildAddressDescriptor();
        buildLargeProjectDescriptor();
        buildPhoneNumberDescriptor();
        buildProjectDescriptor();
        buildSmallProjectDescriptor();
    }

    /**
     * TopLink generated method. 
     * <b>WARNING</b>: This code was generated by an automated tool.
     * Any changes will be lost when the code is re-generated
     */
    protected void applyLOGIN() {
        org.eclipse.persistence.sessions.DatabaseLogin login = new org.eclipse.persistence.sessions.DatabaseLogin();

        login.setDriverClassName("sun.jdbc.odbc.JdbcOdbcDriver");
        login.setConnectionString("jdbc:odbc:MSACCESS");
        login.setPlatformClassName("org.eclipse.persistence.platform.database.AccessPlatform");
        setLogin(login);
    }

    /**
     * TopLink generated method. 
     * <b>WARNING</b>: This code was generated by an automated tool.
     * Any changes will be lost when the code is re-generated
     */
    protected void applyPROJECT() {
        setName("Employee");
    }

    /**
     * TopLink generated method. 
     * <b>WARNING</b>: This code was generated by an automated tool.
     * Any changes will be lost when the code is re-generated
     */
    protected void buildAddressDescriptor() {
        org.eclipse.persistence.descriptors.RelationalDescriptor descriptor = 
            new org.eclipse.persistence.descriptors.RelationalDescriptor();


        // SECTION: DESCRIPTOR
        descriptor.setJavaClass(org.eclipse.persistence.testing.models.employee.domain.Address.class);
        Vector vector = new Vector();
        vector.addElement("ADDRESS");
        descriptor.setTableNames(vector);
        descriptor.addPrimaryKeyFieldName("ADDRESS.ADDRESS_ID");

        // SECTION: PROPERTIES
        descriptor.setIdentityMapClass(org.eclipse.persistence.internal.identitymaps.FullIdentityMap.class);
        descriptor.setExistenceChecking("Check cache");
        descriptor.setIdentityMapSize(100);
        descriptor.setSequenceNumberName("ADDR_SEQ");
        descriptor.setSequenceNumberFieldName("ADDRESS_ID");

        // SECTION: DIRECTTOFIELDMAPPING
        org.eclipse.persistence.mappings.DirectToFieldMapping directtofieldmapping = 
            new org.eclipse.persistence.mappings.DirectToFieldMapping();
        directtofieldmapping.setAttributeName("city");
        directtofieldmapping.setIsReadOnly(false);
        directtofieldmapping.setFieldName("ADDRESS.CITY");
        descriptor.addMapping(directtofieldmapping);

        // SECTION: DIRECTTOFIELDMAPPING
        org.eclipse.persistence.mappings.DirectToFieldMapping directtofieldmapping1 = 
            new org.eclipse.persistence.mappings.DirectToFieldMapping();
        directtofieldmapping1.setAttributeName("country");
        directtofieldmapping1.setIsReadOnly(false);
        directtofieldmapping1.setFieldName("ADDRESS.COUNTRY");
        descriptor.addMapping(directtofieldmapping1);

        // SECTION: DIRECTTOFIELDMAPPING
        org.eclipse.persistence.mappings.DirectToFieldMapping directtofieldmapping2 = 
            new org.eclipse.persistence.mappings.DirectToFieldMapping();
        directtofieldmapping2.setAttributeName("id");
        directtofieldmapping2.setIsReadOnly(false);
        directtofieldmapping2.setFieldName("ADDRESS.ADDRESS_ID");
        descriptor.addMapping(directtofieldmapping2);

        // SECTION: DIRECTTOFIELDMAPPING
        org.eclipse.persistence.mappings.DirectToFieldMapping directtofieldmapping3 = 
            new org.eclipse.persistence.mappings.DirectToFieldMapping();
        directtofieldmapping3.setAttributeName("postalCode");
        directtofieldmapping3.setIsReadOnly(false);
        directtofieldmapping3.setFieldName("ADDRESS.P_CODE");
        descriptor.addMapping(directtofieldmapping3);

        // SECTION: DIRECTTOFIELDMAPPING
        org.eclipse.persistence.mappings.DirectToFieldMapping directtofieldmapping4 = 
            new org.eclipse.persistence.mappings.DirectToFieldMapping();
        directtofieldmapping4.setAttributeName("province");
        directtofieldmapping4.setIsReadOnly(false);
        directtofieldmapping4.setFieldName("ADDRESS.PROVINCE");
        descriptor.addMapping(directtofieldmapping4);

        // SECTION: DIRECTTOFIELDMAPPING
        org.eclipse.persistence.mappings.DirectToFieldMapping directtofieldmapping5 = 
            new org.eclipse.persistence.mappings.DirectToFieldMapping();
        directtofieldmapping5.setAttributeName("street");
        directtofieldmapping5.setIsReadOnly(false);
        directtofieldmapping5.setFieldName("ADDRESS.STREET");
        descriptor.addMapping(directtofieldmapping5);
        addDescriptor(descriptor);
    }

    /**
     * TopLink generated method. 
     * <b>WARNING</b>: This code was generated by an automated tool.
     * Any changes will be lost when the code is re-generated
     */
    protected void buildLargeProjectDescriptor() {
        org.eclipse.persistence.descriptors.RelationalDescriptor descriptor = 
            new org.eclipse.persistence.descriptors.RelationalDescriptor();


        // SECTION: DESCRIPTOR
        descriptor.setJavaClass(org.eclipse.persistence.testing.models.employee.domain.LargeProject.class);
        Vector vector = new Vector();
        vector.addElement("LPROJECT");
        descriptor.setTableNames(vector);
        descriptor.addPrimaryKeyFieldName("LPROJECT.PROJ_ID");
        descriptor.getInheritancePolicy().setParentClass(org.eclipse.persistence.testing.models.employee.domain.Project.class);

        // SECTION: PROPERTIES
        descriptor.setIdentityMapClass(org.eclipse.persistence.internal.identitymaps.FullIdentityMap.class);
        descriptor.setExistenceChecking("Check cache");
        descriptor.setIdentityMapSize(100);

        // SECTION: DIRECTTOFIELDMAPPING
        org.eclipse.persistence.mappings.DirectToFieldMapping directtofieldmapping = 
            new org.eclipse.persistence.mappings.DirectToFieldMapping();
        directtofieldmapping.setAttributeName("budget");
        directtofieldmapping.setIsReadOnly(false);
        directtofieldmapping.setFieldName("LPROJECT.BUDGET");
        descriptor.addMapping(directtofieldmapping);

        // SECTION: DIRECTTOFIELDMAPPING
        org.eclipse.persistence.mappings.DirectToFieldMapping directtofieldmapping1 = 
            new org.eclipse.persistence.mappings.DirectToFieldMapping();
        directtofieldmapping1.setAttributeName("milestoneVersion");
        directtofieldmapping1.setIsReadOnly(false);
        directtofieldmapping1.setFieldName("LPROJECT.MILESTONE");
        descriptor.addMapping(directtofieldmapping1);
        addDescriptor(descriptor);
    }

    /**
     * TopLink generated method. 
     * <b>WARNING</b>: This code was generated by an automated tool.
     * Any changes will be lost when the code is re-generated
     */
    protected void buildPhoneNumberDescriptor() {
        RelationalDescriptor descriptor = new RelationalDescriptor();


        // SECTION: DESCRIPTOR
        descriptor.setJavaClass(org.eclipse.persistence.testing.models.employee.domain.PhoneNumber.class);
        Vector vector = new Vector();
        vector.addElement("PHONE");
        descriptor.setTableNames(vector);
        descriptor.addPrimaryKeyFieldName("PHONE.EMP_ID");
        descriptor.addPrimaryKeyFieldName("PHONE.TYPE");

        // SECTION: PROPERTIES
        descriptor.setIdentityMapClass(org.eclipse.persistence.internal.identitymaps.FullIdentityMap.class);
        descriptor.setExistenceChecking("Check cache");
        descriptor.setIdentityMapSize(100);

        // SECTION: DIRECTTOFIELDMAPPING
        org.eclipse.persistence.mappings.DirectToFieldMapping directtofieldmapping = 
            new org.eclipse.persistence.mappings.DirectToFieldMapping();
        directtofieldmapping.setAttributeName("areaCode");
        directtofieldmapping.setIsReadOnly(false);
        directtofieldmapping.setFieldName("PHONE.AREA_CODE");
        descriptor.addMapping(directtofieldmapping);

        // SECTION: DIRECTTOFIELDMAPPING
        org.eclipse.persistence.mappings.DirectToFieldMapping directtofieldmapping1 = 
            new org.eclipse.persistence.mappings.DirectToFieldMapping();
        directtofieldmapping1.setAttributeName("number");
        directtofieldmapping1.setIsReadOnly(false);
        directtofieldmapping1.setFieldName("PHONE.P_NUMBER");
        descriptor.addMapping(directtofieldmapping1);

        // SECTION: DIRECTTOFIELDMAPPING
        org.eclipse.persistence.mappings.DirectToFieldMapping directtofieldmapping2 = 
            new org.eclipse.persistence.mappings.DirectToFieldMapping();
        directtofieldmapping2.setAttributeName("type");
        directtofieldmapping2.setIsReadOnly(false);
        directtofieldmapping2.setFieldName("PHONE.TYPE");
        descriptor.addMapping(directtofieldmapping2);

        // SECTION: ONETOONEMAPPING
        org.eclipse.persistence.mappings.OneToOneMapping onetoonemapping = new org.eclipse.persistence.mappings.OneToOneMapping();
        onetoonemapping.setAttributeName("owner");
        onetoonemapping.setIsReadOnly(false);
        onetoonemapping.setUsesIndirection(true);
        onetoonemapping.setReferenceClass(org.eclipse.persistence.testing.models.employee.domain.Employee.class);
        onetoonemapping.setIsPrivateOwned(false);
        onetoonemapping.addForeignKeyFieldName("PHONE.EMP_ID", "EMPLOYEE.EMP_ID");
        descriptor.addMapping(onetoonemapping);

        org.eclipse.persistence.testing.models.employee.relational.EmployeeSystem.modifyPhoneDescriptor(descriptor);
        addDescriptor(descriptor);
    }

    /**
     * TopLink generated method. 
     * <b>WARNING</b>: This code was generated by an automated tool.
     * Any changes will be lost when the code is re-generated
     */
    protected void buildProjectDescriptor() {
        org.eclipse.persistence.descriptors.RelationalDescriptor descriptor = 
            new org.eclipse.persistence.descriptors.RelationalDescriptor();


        // SECTION: DESCRIPTOR
        descriptor.setJavaClass(org.eclipse.persistence.testing.models.employee.domain.Project.class);
        Vector vector = new Vector();
        vector.addElement("PROJECT");
        descriptor.setTableNames(vector);
        descriptor.addPrimaryKeyFieldName("PROJECT.PROJ_ID");

        // SECTION: PROPERTIES
        descriptor.setIdentityMapClass(org.eclipse.persistence.internal.identitymaps.FullIdentityMap.class);
        descriptor.setSequenceNumberName("PROJ_SEQ");
        descriptor.setSequenceNumberFieldName("PROJ_ID");
        descriptor.getInheritancePolicy().setClassIndicatorFieldName("PROJ_TYPE");
        descriptor.useVersionLocking("VERSION");
        descriptor.setExistenceChecking("Check cache");
        descriptor.setIdentityMapSize(100);
        descriptor.getInheritancePolicy().addClassIndicator(org.eclipse.persistence.testing.models.employee.domain.LargeProject.class, 
                                                            "L");
        descriptor.getInheritancePolicy().addClassIndicator(org.eclipse.persistence.testing.models.employee.domain.SmallProject.class, 
                                                            "S");

        // SECTION: DIRECTTOFIELDMAPPING
        org.eclipse.persistence.mappings.DirectToFieldMapping directtofieldmapping = 
            new org.eclipse.persistence.mappings.DirectToFieldMapping();
        directtofieldmapping.setAttributeName("description");
        directtofieldmapping.setIsReadOnly(false);
        directtofieldmapping.setFieldName("PROJECT.DESCRIP");
        descriptor.addMapping(directtofieldmapping);

        // SECTION: DIRECTTOFIELDMAPPING
        org.eclipse.persistence.mappings.DirectToFieldMapping directtofieldmapping1 = 
            new org.eclipse.persistence.mappings.DirectToFieldMapping();
        directtofieldmapping1.setAttributeName("id");
        directtofieldmapping1.setIsReadOnly(false);
        directtofieldmapping1.setFieldName("PROJECT.PROJ_ID");
        descriptor.addMapping(directtofieldmapping1);

        // SECTION: DIRECTTOFIELDMAPPING
        org.eclipse.persistence.mappings.DirectToFieldMapping directtofieldmapping2 = 
            new org.eclipse.persistence.mappings.DirectToFieldMapping();
        directtofieldmapping2.setAttributeName("name");
        directtofieldmapping2.setIsReadOnly(false);
        directtofieldmapping2.setFieldName("PROJECT.PROJ_NAME");
        descriptor.addMapping(directtofieldmapping2);

        // SECTION: ONETOONEMAPPING
        org.eclipse.persistence.mappings.OneToOneMapping onetoonemapping = new org.eclipse.persistence.mappings.OneToOneMapping();
        onetoonemapping.setAttributeName("teamLeader");
        onetoonemapping.setIsReadOnly(false);
        onetoonemapping.setUsesIndirection(true);
        onetoonemapping.setReferenceClass(org.eclipse.persistence.testing.models.employee.domain.Employee.class);
        onetoonemapping.setIsPrivateOwned(false);
        onetoonemapping.addForeignKeyFieldName("PROJECT.LEADER_ID", "EMPLOYEE.EMP_ID");
        descriptor.addMapping(onetoonemapping);
        addDescriptor(descriptor);
    }

    /**
     * TopLink generated method. 
     * <b>WARNING</b>: This code was generated by an automated tool.
     * Any changes will be lost when the code is re-generated
     */
    protected void buildSmallProjectDescriptor() {
        org.eclipse.persistence.descriptors.RelationalDescriptor descriptor = 
            new org.eclipse.persistence.descriptors.RelationalDescriptor();


        // SECTION: DESCRIPTOR
        descriptor.setJavaClass(org.eclipse.persistence.testing.models.employee.domain.SmallProject.class);
        descriptor.getInheritancePolicy().setParentClass(org.eclipse.persistence.testing.models.employee.domain.Project.class);

        // SECTION: PROPERTIES
        descriptor.setIdentityMapClass(org.eclipse.persistence.internal.identitymaps.FullIdentityMap.class);
        descriptor.setExistenceChecking("Check cache");
        descriptor.setIdentityMapSize(100);
        addDescriptor(descriptor);
    }
}
