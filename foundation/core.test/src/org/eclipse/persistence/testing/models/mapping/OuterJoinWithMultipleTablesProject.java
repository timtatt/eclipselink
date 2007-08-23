/*******************************************************************************
 * Copyright (c) 1998, 2007 Oracle. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0, which accompanies this distribution
 * and is available at http://www.eclipse.org/legal/epl-v10.html.
 *
 * Contributors:
 *     Oracle - initial API and implementation from Oracle TopLink
 ******************************************************************************/  
package org.eclipse.persistence.testing.models.mapping;

import java.util.*;

/**
 * TopLink generated Project class.
 * <b>WARNING</b>: This code was generated by an automated tool.
 * Any changes will be lost when the code is re-generated
 */
public class OuterJoinWithMultipleTablesProject extends org.eclipse.persistence.sessions.Project {

    /**
     * <b>WARNING</b>: This code was generated by an automated tool.
     * Any changes will be lost when the code is re-generated
     */
    public OuterJoinWithMultipleTablesProject() {
        applyPROJECT();
        buildCourseDescriptor();
        buildStudentDescriptor();
    }

    /**
     * TopLink generated method.
     * <b>WARNING</b>: This code was generated by an automated tool.
     * Any changes will be lost when the code is re-generated
     */
    protected void applyPROJECT() {
        setName("OuterJoinProject");
    }

    /**
     * TopLink generated method.
     * <b>WARNING</b>: This code was generated by an automated tool.
     * Any changes will be lost when the code is re-generated
     */
    protected void buildCourseDescriptor() {
        org.eclipse.persistence.descriptors.RelationalDescriptor descriptor = new org.eclipse.persistence.descriptors.RelationalDescriptor();

        // SECTION: DESCRIPTOR
        descriptor.setJavaClass(org.eclipse.persistence.testing.models.mapping.Course.class);
        Vector vector = new Vector();
        vector.addElement("STUDENT2");
        descriptor.setTableNames(vector);

        // SECTION: PROPERTIES
        descriptor.setIdentityMapClass(org.eclipse.persistence.internal.identitymaps.FullIdentityMap.class);
        descriptor.setExistenceChecking("Check cache");
        descriptor.setIdentityMapSize(100);
        descriptor.descriptorIsAggregate();

        // SECTION: COPY POLICY
        descriptor.createCopyPolicy("constructor");

        // SECTION: INSTANTIATION POLICY
        descriptor.createInstantiationPolicy("constructor");

        // SECTION: DIRECTTOFIELDMAPPING
        org.eclipse.persistence.mappings.DirectToFieldMapping directtofieldmapping = new org.eclipse.persistence.mappings.DirectToFieldMapping();
        directtofieldmapping.setAttributeName("course_ID");
        directtofieldmapping.setIsReadOnly(false);
        directtofieldmapping.setGetMethodName("getCourse_ID");
        directtofieldmapping.setSetMethodName("setCourse_ID");
        directtofieldmapping.setFieldName("STUDENT2.COURSE_ID");
        descriptor.addMapping(directtofieldmapping);

        // SECTION: DIRECTTOFIELDMAPPING
        org.eclipse.persistence.mappings.DirectToFieldMapping directtofieldmapping1 = new org.eclipse.persistence.mappings.DirectToFieldMapping();
        directtofieldmapping1.setAttributeName("courseName");
        directtofieldmapping1.setIsReadOnly(false);
        directtofieldmapping1.setGetMethodName("getCourseName");
        directtofieldmapping1.setSetMethodName("setCourseName");
        directtofieldmapping1.setFieldName("STUDENT2.COURSENAME");
        descriptor.addMapping(directtofieldmapping1);

        // SECTION: DIRECTTOFIELDMAPPING
        org.eclipse.persistence.mappings.DirectToFieldMapping directtofieldmapping2 = new org.eclipse.persistence.mappings.DirectToFieldMapping();
        directtofieldmapping2.setAttributeName("prof");
        directtofieldmapping2.setIsReadOnly(false);
        directtofieldmapping2.setGetMethodName("getProf");
        directtofieldmapping2.setSetMethodName("setProf");
        directtofieldmapping2.setFieldName("STUDENT2.PROF");
        descriptor.addMapping(directtofieldmapping2);
        addDescriptor(descriptor);
    }

    /**
     * TopLink generated method.
     * <b>WARNING</b>: This code was generated by an automated tool.
     * Any changes will be lost when the code is re-generated
     */
    protected void buildStudentDescriptor() {
        org.eclipse.persistence.descriptors.RelationalDescriptor descriptor = new org.eclipse.persistence.descriptors.RelationalDescriptor();

        // SECTION: DESCRIPTOR
        descriptor.setJavaClass(org.eclipse.persistence.testing.models.mapping.Student.class);
        Vector vector = new Vector();
        vector.addElement("STUDENT");
        vector.addElement("STUDENT2");
        descriptor.setTableNames(vector);
        descriptor.addPrimaryKeyFieldName("STUDENT.ST_ID");

        // SECTION: PROPERTIES
        descriptor.setIdentityMapClass(org.eclipse.persistence.internal.identitymaps.FullIdentityMap.class);
        descriptor.setExistenceChecking("Check cache");
        descriptor.setIdentityMapSize(100);

        // SECTION: COPY POLICY
        descriptor.createCopyPolicy("constructor");

        // SECTION: INSTANTIATION POLICY
        descriptor.createInstantiationPolicy("constructor");

        // SECTION: AGGREGATEOBJECTMAPPING
        org.eclipse.persistence.mappings.AggregateObjectMapping aggregateobjectmapping = new org.eclipse.persistence.mappings.AggregateObjectMapping();
        aggregateobjectmapping.setAttributeName("course");
        aggregateobjectmapping.setIsReadOnly(false);
        aggregateobjectmapping.setGetMethodName("getCourse");
        aggregateobjectmapping.setSetMethodName("setCourse");
        aggregateobjectmapping.setReferenceClass(org.eclipse.persistence.testing.models.mapping.Course.class);
        aggregateobjectmapping.setIsNullAllowed(true);
        descriptor.addMapping(aggregateobjectmapping);

        // SECTION: DIRECTTOFIELDMAPPING
        org.eclipse.persistence.mappings.DirectToFieldMapping directtofieldmapping = new org.eclipse.persistence.mappings.DirectToFieldMapping();
        directtofieldmapping.setAttributeName("st_ID");
        directtofieldmapping.setIsReadOnly(false);
        directtofieldmapping.setGetMethodName("getSt_ID");
        directtofieldmapping.setSetMethodName("setSt_ID");
        directtofieldmapping.setFieldName("STUDENT.ST_ID");
        descriptor.addMapping(directtofieldmapping);

        org.eclipse.persistence.testing.models.mapping.Student.addToDescriptor(descriptor);
        addDescriptor(descriptor);
    }
}