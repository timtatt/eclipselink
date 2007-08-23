/*******************************************************************************
 * Copyright (c) 1998, 2007 Oracle. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0, which accompanies this distribution
 * and is available at http://www.eclipse.org/legal/epl-v10.html.
 *
 * Contributors:
 *     Oracle - initial API and implementation from Oracle TopLink
 ******************************************************************************/  
package org.eclipse.persistence.testing.tests.insurance;

import org.eclipse.persistence.testing.framework.*;
import org.eclipse.persistence.tools.schemaframework.PopulationManager;
import org.eclipse.persistence.internal.helper.Helper;
import org.eclipse.persistence.testing.models.insurance.*;

/**
 * This model tests reading/writing/deleting through using the insurance demo.
 */
public class InsuranceBasicTestModel extends TestModel {

    /**
     * Return the JUnit suite to allow JUnit runner to find it.
     * Unfortunately JUnit only allows suite methods to be static,
     * so it is not possible to generically do this.
     */
    public static junit.framework.TestSuite suite() {
        return new InsuranceBasicTestModel();
    }

    public InsuranceBasicTestModel() {
        setDescription("This model tests reading/writing/deleting using the insurance demo.");
    }

    public void addRequiredSystems() {
        addRequiredSystem(new InsuranceSystem());

    }

    public void addTests() {
        addTest(getReadObjectTestSuite());
        addTest(getReadAllTestSuite());
        addTest(getInsertObjectTestSuite());
        addTest(getUpdateObjectTestSuite());
        addTest(getDeleteObjectTestSuite());
    }

    /**
     * Return an example policy holder instance.
     */
    public static PolicyHolder buildExamplePolicyHolder() {
        PolicyHolder holder = new PolicyHolder();

        holder.setFirstName("Bobby");
        holder.setLastName("Jones");
        holder.setMale();
        holder.setSsn(12345);
        holder.setBirthDate(Helper.dateFromString("1950/04/30"));
        holder.setOccupation("Software Engineer");

        holder.setAddress(Address.example1());

        return holder;
    }

    public static TestSuite getDeleteObjectTestSuite() {
        TestSuite suite = new TestSuite();
        suite.setName("InsuranceDeleteObjectTestSuite");
        suite.setDescription("This suite tests the deletion of each object in the insurance demo.");

        Class holderClass = PolicyHolder.class;
        PopulationManager manager = PopulationManager.getDefaultManager();

        suite.addTest(new DeleteObjectTest(manager.getObject(holderClass, "example1")));
        suite.addTest(new DeleteObjectTest(manager.getObject(holderClass, "example2")));
        suite.addTest(new DeleteObjectTest(manager.getObject(holderClass, "example3")));
        suite.addTest(new DeleteObjectTest(manager.getObject(holderClass, "example4")));

        return suite;

    }

    public static TestSuite getInsertObjectTestSuite() {
        TestSuite suite = new TestSuite();
        suite.setName("InsuranceInsertObjectTestSuite");
        suite.setDescription("This suite tests the insertion of each object in the insurance demo.");

        suite.addTest(new InsertObjectTest(buildExamplePolicyHolder()));

        return suite;
    }

    public static TestSuite getReadAllTestSuite() {
        TestSuite suite = new TestSuite();
        suite.setName("InsuranceReadAllTestSuite");
        suite.setDescription("This suite tests the reading of all the objects of each class in the insurance demo.");

        PopulationManager manager = PopulationManager.getDefaultManager();

        suite.addTest(new ReadAllTest(PolicyHolder.class, 4));
        suite.addTest(new ReadAllTest(Policy.class, 5));
        suite.addTest(new ReadAllTest(Claim.class, 7));

        return suite;
    }

    public static TestSuite getReadObjectTestSuite() {
        TestSuite suite = new TestSuite();
        suite.setName("InsuranceReadObjectTestSuite");
        suite.setDescription("This suite test the reading of each object in the insurance demo.");

        Class holderClass = PolicyHolder.class;
        PopulationManager manager = PopulationManager.getDefaultManager();

        suite.addTest(new ReadObjectTest(manager.getObject(holderClass, "example1")));
        suite.addTest(new ReadObjectTest(manager.getObject(holderClass, "example2")));
        suite.addTest(new ReadObjectTest(manager.getObject(holderClass, "example3")));
        suite.addTest(new ReadObjectTest(manager.getObject(holderClass, "example4")));

        return suite;
    }

    public static TestSuite getUpdateObjectTestSuite() {
        TestSuite suite = new TestSuite();
        suite.setName("InsuranceUpdateObjectTestSuite");
        suite.setDescription("This suite tests the updating of each object in the insurance demo.");

        Class holderClass = PolicyHolder.class;
        PopulationManager manager = PopulationManager.getDefaultManager();

        suite.addTest(new WriteObjectTest(manager.getObject(holderClass, "example1")));
        suite.addTest(new UnitOfWorkBasicUpdateObjectTest(manager.getObject(holderClass, "example1")));
        suite.addTest(new UnitOfWorkBasicUpdateObjectTest(manager.getObject(holderClass, "example2")));
        suite.addTest(new UnitOfWorkBasicUpdateObjectTest(manager.getObject(holderClass, "example3")));
        suite.addTest(new UnitOfWorkBasicUpdateObjectTest(manager.getObject(holderClass, "example4")));

        return suite;
    }
}
