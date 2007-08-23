/*******************************************************************************
 * Copyright (c) 1998, 2007 Oracle. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0, which accompanies this distribution
 * and is available at http://www.eclipse.org/legal/epl-v10.html.
 *
 * Contributors:
 *     Oracle - initial API and implementation from Oracle TopLink
 ******************************************************************************/  
package org.eclipse.persistence.testing.tests.queries;

import org.eclipse.persistence.tools.schemaframework.*;

public class Account {
    public String accountNumber;
    public org.eclipse.persistence.indirection.ValueHolderInterface state = new org.eclipse.persistence.indirection.ValueHolder();
    public org.eclipse.persistence.indirection.ValueHolderInterface billingCountry = new org.eclipse.persistence.indirection.ValueHolder();

    /**
     * This method was created in VisualAge.
     */
    public Account() {
    }

    /**
     * TopLink generated method.
     * <b>WARNING</b>: This code was generated by an automated tool.
     * Any changes will be lost when the code is re-generated
     */
    public static TableDefinition buildACCOUNTTable() {
        org.eclipse.persistence.tools.schemaframework.TableDefinition tabledefinition = new org.eclipse.persistence.tools.schemaframework.TableDefinition();

        // SECTION: TABLE
        tabledefinition.setName("ACCOUNT");

        // SECTION: FIELD
        org.eclipse.persistence.tools.schemaframework.FieldDefinition field = new org.eclipse.persistence.tools.schemaframework.FieldDefinition();
        field.setName("ACCOUNT_NUMBER");
        field.setTypeName("VARCHAR");
        field.setShouldAllowNull(false);
        field.setIsPrimaryKey(true);
        field.setUnique(false);
        field.setIsIdentity(false);
        tabledefinition.addField(field);

        // SECTION: FIELD
        org.eclipse.persistence.tools.schemaframework.FieldDefinition field1 = new org.eclipse.persistence.tools.schemaframework.FieldDefinition();
        field1.setName("BILLING_COUNTRY");
        field1.setTypeName("VARCHAR");
        field1.setShouldAllowNull(true);
        field1.setIsPrimaryKey(false);
        field1.setUnique(false);
        field1.setIsIdentity(false);
        tabledefinition.addField(field1);

        // SECTION: FIELD
        org.eclipse.persistence.tools.schemaframework.FieldDefinition field2 = new org.eclipse.persistence.tools.schemaframework.FieldDefinition();
        field2.setName("STATE_NAME");
        field2.setTypeName("VARCHAR");
        field2.setShouldAllowNull(true);
        field2.setIsPrimaryKey(false);
        field2.setUnique(false);
        field2.setIsIdentity(false);
        tabledefinition.addField(field2);

        // SECTION: FIELD
        org.eclipse.persistence.tools.schemaframework.FieldDefinition field3 = new org.eclipse.persistence.tools.schemaframework.FieldDefinition();
        field3.setName("STATE_COUNTRY");
        field3.setTypeName("VARCHAR");
        field3.setShouldAllowNull(true);
        field3.setIsPrimaryKey(false);
        field3.setUnique(false);
        field3.setIsIdentity(false);
        tabledefinition.addField(field3);

        return tabledefinition;
    }

    /**
     * This method was created in VisualAge.
     */
    public static Account example1() {
        Account account1 = new Account();
        account1.accountNumber = "12345";
        account1.setBillingCountry(Country.greece);
        account1.setState(State.alberta);

        return account1;

    }

    /**
     * This method was created in VisualAge.
     */
    public static Account example2() {
        Account account2 = new Account();
        account2.accountNumber = "ABDCE";
        account2.setBillingCountry(Country.usa);
        account2.setState(State.athens);

        return account2;

    }

    /**
     * This method was created in VisualAge.
     */
    public static Account example3() {
        Account account3 = new Account();
        account3.accountNumber = "123AB";
        account3.setBillingCountry(Country.canada);
        account3.setState(State.alabama);

        return account3;

    }

    /**
     * This method was created in VisualAge.
     */
    public static Account example4() {
        Account account4 = new Account();
        account4.accountNumber = "54321";
        account4.setBillingCountry(Country.greece);
        account4.setState(State.alberta);

        return account4;

    }

    /**
     * This method was created in VisualAge.
     */
    public static Account example5() {
        Account account5 = new Account();
        account5.accountNumber = "EDCEA";
        account5.setBillingCountry(Country.usa);
        account5.setState(State.alabama);

        return account5;

    }

    /**
     * This method was created in VisualAge.
     */
    public static Account example6() {
        Account account6 = new Account();
        account6.accountNumber = "36H72";
        account6.setBillingCountry(Country.canada);
        account6.setState(State.athens);

        return account6;

    }

    public Country getBillingCountry() {
        return (Country)billingCountry.getValue();
    }

    public State getState() {
        return (State)state.getValue();
    }

    public void setAccountNumber(String value) {
        accountNumber = value;
    }

    public void setAccountNumber(State value) {
        state.setValue(value);
    }

    public void setBillingCountry(Country value) {
        billingCountry.setValue(value);
    }

    public void setState(State value) {
        state.setValue(value);
    }
}