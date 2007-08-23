/*******************************************************************************
 * Copyright (c) 1998, 2007 Oracle. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0, which accompanies this distribution
 * and is available at http://www.eclipse.org/legal/epl-v10.html.
 *
 * Contributors:
 *     Oracle - initial API and implementation from Oracle TopLink
 ******************************************************************************/  
package org.eclipse.persistence.testing.models.jpa.inheritance;

import org.eclipse.persistence.tools.schemaframework.*;

/**
 * This class was generated by the TopLink table creator generator.
 * It stores the meta-data (tables) that define the database schema.
 * @see org.eclipse.persistence.sessions.factories.TableCreatorClassGenerator
 */
public class InheritanceTableCreator extends org.eclipse.persistence.tools.schemaframework.TableCreator {
    public InheritanceTableCreator() {
        setName("EJB3InheritanceProject");

        addTableDefinition(buildBICYCLETable());
        addTableDefinition(buildBOATTable());
        addTableDefinition(buildBUSTable());
        addTableDefinition(buildCOMPANYTable());
        addTableDefinition(buildFUEL_VEHTable());
        addTableDefinition(buildNONFUEL_VEHTable());
        addTableDefinition(buildSPORTSCARTable());
        addTableDefinition(buildVEHICLETable());
        addTableDefinition(buildPERSONTable());
        addTableDefinition(buildENGINEERTable());
        addTableDefinition(buildTIREINFOTable());
        addTableDefinition(buildOFFROADTIREINFOTable());
        addTableDefinition(buildMUDTIREINFOTable());
        addTableDefinition(buildROCKTIREINFOTable());
//        addTableDefinition(buildVEH_SEQTable());
        addTableDefinition(buildAAATable());
        addTableDefinition(buildBBBTable());
        addTableDefinition(buildCCCTable());
        addTableDefinition(buildCOMPUTERTable());
        addTableDefinition(buildDESKTOPTable());
        addTableDefinition(buildENGINEER_DESKTOPTable());
        addTableDefinition(buildLAPTOPTable());
        addTableDefinition(buildENGINEER_LAPTOPTable());
    }

    public TableDefinition buildBICYCLETable() {
        TableDefinition table = new TableDefinition();
        table.setName("CMP3_BICYCLE");

        FieldDefinition fieldID = new FieldDefinition();
        fieldID.setName("ID");
        fieldID.setTypeName("NUMBER");
        fieldID.setSize(15);
        fieldID.setSubSize(0);
        fieldID.setIsPrimaryKey(true);
        fieldID.setIsIdentity(false);
        fieldID.setUnique(false);
        fieldID.setShouldAllowNull(false);
        fieldID.setForeignKeyFieldName("CMP3_NONFUEL_VEH.ID");
        table.addField(fieldID);

        FieldDefinition fieldDESCRIP = new FieldDefinition();
        fieldDESCRIP.setName("DESCRIP");
        fieldDESCRIP.setTypeName("VARCHAR2");
        fieldDESCRIP.setSize(10);
        fieldDESCRIP.setSubSize(0);
        fieldDESCRIP.setIsPrimaryKey(false);
        fieldDESCRIP.setIsIdentity(false);
        fieldDESCRIP.setUnique(false);
        fieldDESCRIP.setShouldAllowNull(true);
        table.addField(fieldDESCRIP);

        return table;
    }

    public TableDefinition buildBOATTable() {
        TableDefinition table = new TableDefinition();
        table.setName("CMP3_BOAT");

        FieldDefinition fieldID = new FieldDefinition();
        fieldID.setName("BOAT_ID");
        fieldID.setTypeName("NUMBER");
        fieldID.setSize(15);
        fieldID.setSubSize(0);
        fieldID.setIsPrimaryKey(true);
        fieldID.setIsIdentity(false);
        fieldID.setUnique(false);
        fieldID.setShouldAllowNull(false);
        fieldID.setForeignKeyFieldName("CMP3_NONFUEL_VEH.ID");
        table.addField(fieldID);

        FieldDefinition fieldMODEL = new FieldDefinition();
        fieldMODEL.setName("MODEL");
        fieldMODEL.setTypeName("VARCHAR2");
        fieldMODEL.setSize(10);
        fieldMODEL.setSubSize(0);
        fieldMODEL.setIsPrimaryKey(false);
        fieldMODEL.setIsIdentity(false);
        fieldMODEL.setUnique(false);
        fieldMODEL.setShouldAllowNull(true);
        table.addField(fieldMODEL);

        return table;
    }

    public TableDefinition buildBUSTable() {
        TableDefinition table = new TableDefinition();
        table.setName("CMP3_BUS");

        FieldDefinition fieldID = new FieldDefinition();
        fieldID.setName("BUS_ID");
        fieldID.setTypeName("NUMBER");
        fieldID.setSize(15);
        fieldID.setSubSize(0);
        fieldID.setIsPrimaryKey(true);
        fieldID.setIsIdentity(false);
        fieldID.setUnique(false);
        fieldID.setShouldAllowNull(false);
        fieldID.setForeignKeyFieldName("CMP3_FUEL_VEH.ID");
        table.addField(fieldID);

        FieldDefinition fieldDRIVER_ID = new FieldDefinition();
        fieldDRIVER_ID.setName("DRIVER_ID");
        fieldDRIVER_ID.setTypeName("NUMBER");
        fieldDRIVER_ID.setSize(15);
        fieldDRIVER_ID.setSubSize(0);
        fieldDRIVER_ID.setIsPrimaryKey(false);
        fieldDRIVER_ID.setIsIdentity(false);
        fieldDRIVER_ID.setUnique(false);
        fieldDRIVER_ID.setShouldAllowNull(true);
        table.addField(fieldDRIVER_ID);

        ForeignKeyConstraint foreignKeyBUS_PERSON = new ForeignKeyConstraint();
        foreignKeyBUS_PERSON.setName("BUSDRIVER_ID");
        foreignKeyBUS_PERSON.setTargetTable("CMP3_PERSON");
        foreignKeyBUS_PERSON.addSourceField("DRIVER_ID");
        foreignKeyBUS_PERSON.addTargetField("ID");
        table.addForeignKeyConstraint(foreignKeyBUS_PERSON);

        return table;
    }

    public TableDefinition buildROCKTIREINFOTable() {
        TableDefinition table = new TableDefinition();
        table.setName("CMP3_ROCK_TIRE");

        FieldDefinition fieldID = new FieldDefinition();
        fieldID.setName("ID");
        fieldID.setTypeName("NUMBER");
        fieldID.setSize(15);
        fieldID.setSubSize(0);
        fieldID.setIsPrimaryKey(true);
        fieldID.setIsIdentity(false);
        fieldID.setUnique(false);
        fieldID.setShouldAllowNull(false);
        fieldID.setForeignKeyFieldName("CMP3_OFFROAD_TIRE.ID");
        table.addField(fieldID);

        FieldDefinition fieldGRIP = new FieldDefinition();
        fieldGRIP.setName("GRIP");
        fieldGRIP.setTypeName("NUMBER");
        fieldGRIP.setSize(15);
        fieldGRIP.setSubSize(0);
        fieldGRIP.setIsPrimaryKey(false);
        fieldGRIP.setIsIdentity(false);
        fieldGRIP.setUnique(false);
        fieldGRIP.setShouldAllowNull(true);
        table.addField(fieldGRIP);

        return table;
    }
    
    public TableDefinition buildSPORTSCARTable() {
        TableDefinition table = new TableDefinition();
        table.setName("CMP3_SPORTS_CAR");

        FieldDefinition fieldID = new FieldDefinition();
        fieldID.setName("ID");
        fieldID.setTypeName("NUMBER");
        fieldID.setSize(15);
        fieldID.setSubSize(0);
        fieldID.setIsPrimaryKey(true);
        fieldID.setIsIdentity(false);
        fieldID.setUnique(false);
        fieldID.setShouldAllowNull(false);
        fieldID.setForeignKeyFieldName("CMP3_FUEL_VEH.ID");
        table.addField(fieldID);

        FieldDefinition fieldFUEL_CAP = new FieldDefinition();
        fieldFUEL_CAP.setName("MAX_SPEED");
        fieldFUEL_CAP.setTypeName("NUMBER");
        fieldFUEL_CAP.setSize(10);
        fieldFUEL_CAP.setSubSize(0);
        fieldFUEL_CAP.setIsPrimaryKey(false);
        fieldFUEL_CAP.setIsIdentity(false);
        fieldFUEL_CAP.setUnique(false);
        fieldFUEL_CAP.setShouldAllowNull(true);
        table.addField(fieldFUEL_CAP);

        return table;
    }

    public TableDefinition buildCOMPANYTable() {
        TableDefinition table = new TableDefinition();
        table.setName("CMP3_COMPANY");

        FieldDefinition fieldID = new FieldDefinition();
        fieldID.setName("ID");
        fieldID.setTypeName("NUMBER");
        fieldID.setSize(15);
        fieldID.setSubSize(0);
        fieldID.setIsPrimaryKey(true);
        fieldID.setIsIdentity(false);
        fieldID.setUnique(false);
        fieldID.setShouldAllowNull(false);
        table.addField(fieldID);

        FieldDefinition fieldNAME = new FieldDefinition();
        fieldNAME.setName("NAME");
        fieldNAME.setTypeName("VARCHAR2");
        fieldNAME.setSize(100);
        fieldNAME.setSubSize(0);
        fieldNAME.setIsPrimaryKey(false);
        fieldNAME.setIsIdentity(false);
        fieldNAME.setUnique(false);
        fieldNAME.setShouldAllowNull(false);
        table.addField(fieldNAME);

        return table;
    }
        
    public TableDefinition buildFUEL_VEHTable() {
        TableDefinition table = new TableDefinition();
        table.setName("CMP3_FUEL_VEH");

        FieldDefinition fieldID = new FieldDefinition();
        fieldID.setName("ID");
        fieldID.setTypeName("NUMBER");
        fieldID.setSize(15);
        fieldID.setSubSize(0);
        fieldID.setIsPrimaryKey(true);
        fieldID.setIsIdentity(false);
        fieldID.setUnique(false);
        fieldID.setShouldAllowNull(false);
        fieldID.setForeignKeyFieldName("CMP3_VEHICLE.ID");
        table.addField(fieldID);

        FieldDefinition fieldDESCRIP = new FieldDefinition();
        fieldDESCRIP.setName("DESCRIP");
        fieldDESCRIP.setTypeName("VARCHAR2");
        fieldDESCRIP.setSize(30);
        fieldDESCRIP.setSubSize(0);
        fieldDESCRIP.setIsPrimaryKey(false);
        fieldDESCRIP.setIsIdentity(false);
        fieldDESCRIP.setUnique(false);
        fieldDESCRIP.setShouldAllowNull(true);
        table.addField(fieldDESCRIP);

        FieldDefinition fieldFUEL_CAP = new FieldDefinition();
        fieldFUEL_CAP.setName("FUEL_CAP");
        fieldFUEL_CAP.setTypeName("NUMBER");
        fieldFUEL_CAP.setSize(10);
        fieldFUEL_CAP.setSubSize(0);
        fieldFUEL_CAP.setIsPrimaryKey(false);
        fieldFUEL_CAP.setIsIdentity(false);
        fieldFUEL_CAP.setUnique(false);
        fieldFUEL_CAP.setShouldAllowNull(true);
        table.addField(fieldFUEL_CAP);

        FieldDefinition fieldFUEL_TYP = new FieldDefinition();
        fieldFUEL_TYP.setName("FUEL_TYP");
        fieldFUEL_TYP.setTypeName("VARCHAR2");
        fieldFUEL_TYP.setSize(30);
        fieldFUEL_TYP.setSubSize(0);
        fieldFUEL_TYP.setIsPrimaryKey(false);
        fieldFUEL_TYP.setIsIdentity(false);
        fieldFUEL_TYP.setUnique(false);
        fieldFUEL_TYP.setShouldAllowNull(true);
        table.addField(fieldFUEL_TYP);
        
        FieldDefinition fieldCOLOUR = new FieldDefinition();
        fieldCOLOUR.setName("COLOUR");
        fieldCOLOUR.setTypeName("VARCHAR2");
        fieldCOLOUR.setSize(20);
        fieldCOLOUR.setSubSize(0);
        fieldCOLOUR.setIsPrimaryKey(false);
        fieldCOLOUR.setIsIdentity(false);
        fieldCOLOUR.setUnique(false);
        fieldCOLOUR.setShouldAllowNull(true);
        table.addField(fieldCOLOUR);

        return table;
    }

    public TableDefinition buildMUDTIREINFOTable() {
        TableDefinition table = new TableDefinition();
        table.setName("CMP3_MUD_TIRE");

        FieldDefinition fieldID = new FieldDefinition();
        fieldID.setName("ID");
        fieldID.setTypeName("NUMBER");
        fieldID.setSize(15);
        fieldID.setSubSize(0);
        fieldID.setIsPrimaryKey(true);
        fieldID.setIsIdentity(false);
        fieldID.setUnique(false);
        fieldID.setShouldAllowNull(false);
        fieldID.setForeignKeyFieldName("CMP3_OFFROAD_TIRE.ID");
        table.addField(fieldID);

        FieldDefinition fieldTREAD = new FieldDefinition();
        fieldTREAD.setName("TREAD_DEPTH");
        fieldTREAD.setTypeName("NUMBER");
        fieldTREAD.setSize(15);
        fieldTREAD.setSubSize(0);
        fieldTREAD.setIsPrimaryKey(false);
        fieldTREAD.setIsIdentity(false);
        fieldTREAD.setUnique(false);
        fieldTREAD.setShouldAllowNull(true);
        table.addField(fieldTREAD);
        
        FieldDefinition fieldRATING = new FieldDefinition();
        fieldRATING.setName("RATING");
        fieldRATING.setTypeName("VARCHAR2");
        fieldRATING.setSize(20);
        fieldRATING.setSubSize(0);
        fieldRATING.setIsPrimaryKey(false);
        fieldRATING.setIsIdentity(false);
        fieldRATING.setUnique(false);
        fieldRATING.setShouldAllowNull(true);
        table.addField(fieldRATING);
        
        FieldDefinition fieldCOMMENTS = new FieldDefinition();
        fieldCOMMENTS.setName("COMMENTS");
        fieldCOMMENTS.setTypeName("VARCHAR2");
        fieldCOMMENTS.setSize(100);
        fieldCOMMENTS.setSubSize(0);
        fieldCOMMENTS.setIsPrimaryKey(false);
        fieldCOMMENTS.setIsIdentity(false);
        fieldCOMMENTS.setUnique(false);
        fieldCOMMENTS.setShouldAllowNull(true);
        table.addField(fieldCOMMENTS);

        return table;
    }
    
    public TableDefinition buildNONFUEL_VEHTable() {
        TableDefinition table = new TableDefinition();
        table.setName("CMP3_NONFUEL_VEH");

        FieldDefinition fieldID = new FieldDefinition();
        fieldID.setName("ID");
        fieldID.setTypeName("NUMBER");
        fieldID.setSize(15);
        fieldID.setSubSize(0);
        fieldID.setIsPrimaryKey(true);
        fieldID.setIsIdentity(false);
        fieldID.setUnique(false);
        fieldID.setShouldAllowNull(false);
        fieldID.setForeignKeyFieldName("CMP3_VEHICLE.ID");
        table.addField(fieldID);
        
        FieldDefinition fieldCOLOR = new FieldDefinition();
        fieldCOLOR.setName("COLOR");
        fieldCOLOR.setTypeName("VARCHAR2");
        fieldCOLOR.setSize(20);
        fieldCOLOR.setSubSize(0);
        fieldCOLOR.setIsPrimaryKey(false);
        fieldCOLOR.setIsIdentity(false);
        fieldCOLOR.setUnique(false);
        fieldCOLOR.setShouldAllowNull(true);
        table.addField(fieldCOLOR);
        
        return table;
    }
    
    public TableDefinition buildENGINEERTable() {
        TableDefinition table = new TableDefinition();
        table.setName("CMP3_ENGINEER");

        FieldDefinition fieldID = new FieldDefinition();
        fieldID.setName("ID");
        fieldID.setTypeName("NUMBER");
        fieldID.setSize(15);
        fieldID.setSubSize(0);
        fieldID.setIsPrimaryKey(true);
        fieldID.setIsIdentity(false);
        fieldID.setUnique(false);
        fieldID.setShouldAllowNull(false);
        fieldID.setForeignKeyFieldName("CMP3_PERSON.ID");
        table.addField(fieldID);

        FieldDefinition fieldTITLE = new FieldDefinition();
        fieldTITLE.setName("TITLE");
        fieldTITLE.setTypeName("VARCHAR2");
        fieldTITLE.setSize(26);
        fieldTITLE.setSubSize(0);
        fieldTITLE.setIsPrimaryKey(false);
        fieldTITLE.setIsIdentity(false);
        fieldTITLE.setUnique(false);
        fieldTITLE.setShouldAllowNull(true);
        table.addField(fieldTITLE);

        FieldDefinition fieldCOMPANY_ID = new FieldDefinition();
        fieldCOMPANY_ID.setName("COMPANY_ID");
        fieldCOMPANY_ID.setTypeName("NUMBER");
        fieldCOMPANY_ID.setSize(15);
        fieldCOMPANY_ID.setSubSize(0);
        fieldCOMPANY_ID.setIsPrimaryKey(false);
        fieldCOMPANY_ID.setIsIdentity(false);
        fieldCOMPANY_ID.setUnique(false);
        fieldCOMPANY_ID.setShouldAllowNull(true);
        fieldCOMPANY_ID.setForeignKeyFieldName("CMP3_COMPANY.ID");
        table.addField(fieldCOMPANY_ID);
        
        return table;
    }

    public TableDefinition buildOFFROADTIREINFOTable() {
        TableDefinition table = new TableDefinition();
        table.setName("CMP3_OFFROAD_TIRE");

        FieldDefinition fieldID = new FieldDefinition();
        fieldID.setName("ID");
        fieldID.setTypeName("NUMBER");
        fieldID.setSize(15);
        fieldID.setSubSize(0);
        fieldID.setIsPrimaryKey(true);
        fieldID.setIsIdentity(false);
        fieldID.setUnique(false);
        fieldID.setShouldAllowNull(false);
        fieldID.setForeignKeyFieldName("CMP3_TIRE.ID");
        table.addField(fieldID);

        FieldDefinition fieldNAME = new FieldDefinition();
        fieldNAME.setName("NAME");
        fieldNAME.setTypeName("VARCHAR2");
        fieldNAME.setSize(25);
        fieldNAME.setSubSize(0);
        fieldNAME.setIsPrimaryKey(false);
        fieldNAME.setIsIdentity(false);
        fieldNAME.setUnique(false);
        fieldNAME.setShouldAllowNull(true);
        table.addField(fieldNAME);
        
        FieldDefinition fieldCODE = new FieldDefinition();
        fieldCODE.setName("CODE");
        fieldCODE.setTypeName("VARCHAR2");
        fieldCODE.setSize(20);
        fieldCODE.setSubSize(0);
        fieldCODE.setIsPrimaryKey(false);
        fieldCODE.setIsIdentity(false);
        fieldCODE.setUnique(false);
        fieldCODE.setShouldAllowNull(true);
        table.addField(fieldCODE);

        FieldDefinition fieldDTYPE = new FieldDefinition();
        fieldDTYPE.setName("DTYPE");
        fieldDTYPE.setTypeName("VARCHAR2");
        fieldDTYPE.setSize(15);
        fieldDTYPE.setSubSize(0);
        fieldDTYPE.setIsPrimaryKey(false);
        fieldDTYPE.setIsIdentity(false);
        fieldDTYPE.setUnique(false);
        fieldDTYPE.setShouldAllowNull(true);
        table.addField(fieldDTYPE);

        return table;
    }
    
    public TableDefinition buildPERSONTable() {
        TableDefinition table = new TableDefinition();
        table.setName("CMP3_PERSON");

        FieldDefinition fieldID = new FieldDefinition();
        fieldID.setName("ID");
        fieldID.setTypeName("NUMBER");
        fieldID.setSize(15);
        fieldID.setSubSize(0);
        fieldID.setIsPrimaryKey(true);
        fieldID.setIsIdentity(false);
        fieldID.setUnique(false);
        fieldID.setShouldAllowNull(false);
        table.addField(fieldID);

        FieldDefinition fieldNAME = new FieldDefinition();
        fieldNAME.setName("NAME");
        fieldNAME.setTypeName("VARCHAR2");
        fieldNAME.setSize(20);
        fieldNAME.setSubSize(0);
        fieldNAME.setIsPrimaryKey(false);
        fieldNAME.setIsIdentity(false);
        fieldNAME.setUnique(false);
        fieldNAME.setShouldAllowNull(true);
        table.addField(fieldNAME);

        FieldDefinition fieldC_TYPE = new FieldDefinition();
        fieldC_TYPE.setName("DTYPE");
        fieldC_TYPE.setTypeName("VARCHAR2");
        fieldC_TYPE.setSize(100);
        fieldC_TYPE.setSubSize(0);
        fieldC_TYPE.setIsPrimaryKey(false);
        fieldC_TYPE.setIsIdentity(false);
        fieldC_TYPE.setUnique(false);
        fieldC_TYPE.setShouldAllowNull(true);
        table.addField(fieldC_TYPE);

        FieldDefinition fieldREP_ID = new FieldDefinition();
        fieldREP_ID.setName("REP_ID");
        fieldREP_ID.setTypeName("NUMBER");
        fieldREP_ID.setSize(15);
        fieldREP_ID.setSubSize(0);
        fieldREP_ID.setIsPrimaryKey(false);
        fieldREP_ID.setIsIdentity(false);
        fieldREP_ID.setUnique(false);
        fieldREP_ID.setShouldAllowNull(true);
        table.addField(fieldREP_ID);

        FieldDefinition fieldFRIEND_ID = new FieldDefinition();
        fieldFRIEND_ID.setName("FRIEND_ID");
        fieldFRIEND_ID.setTypeName("NUMBER");
        fieldFRIEND_ID.setSize(15);
        fieldFRIEND_ID.setSubSize(0);
        fieldFRIEND_ID.setIsPrimaryKey(false);
        fieldFRIEND_ID.setIsIdentity(false);
        fieldFRIEND_ID.setUnique(false);
        fieldFRIEND_ID.setShouldAllowNull(true);
        table.addField(fieldFRIEND_ID);

        FieldDefinition fieldCAR_ID = new FieldDefinition();
        fieldCAR_ID.setName("CAR_ID");
        fieldCAR_ID.setTypeName("NUMBER");
        fieldCAR_ID.setSize(15);
        fieldCAR_ID.setSubSize(0);
        fieldCAR_ID.setIsPrimaryKey(false);
        fieldCAR_ID.setIsIdentity(false);
        fieldCAR_ID.setUnique(false);
        fieldCAR_ID.setShouldAllowNull(true);
        table.addField(fieldCAR_ID);

        ForeignKeyConstraint foreignKeyPERSON_CAR = new ForeignKeyConstraint();
        foreignKeyPERSON_CAR.setName("PERSON_CAR");
        foreignKeyPERSON_CAR.setTargetTable("CMP3_VEHICLE");
        foreignKeyPERSON_CAR.addSourceField("CAR_ID");
        foreignKeyPERSON_CAR.addTargetField("ID");
        table.addForeignKeyConstraint(foreignKeyPERSON_CAR);

        ForeignKeyConstraint foreignKeyPERSON_ENGINEER = new ForeignKeyConstraint();
        foreignKeyPERSON_ENGINEER.setName("PERSON_ENGINEER");
        foreignKeyPERSON_ENGINEER.setTargetTable("CMP3_ENGINEER");
        foreignKeyPERSON_ENGINEER.addSourceField("FRIEND_ID");
        foreignKeyPERSON_ENGINEER.addTargetField("ID");
        table.addForeignKeyConstraint(foreignKeyPERSON_ENGINEER);

        ForeignKeyConstraint foreignKeyPERSON_REP = new ForeignKeyConstraint();
        foreignKeyPERSON_REP.setName("PERSON_LAWYER");
        foreignKeyPERSON_REP.setTargetTable("CMP3_PERSON");
        foreignKeyPERSON_REP.addSourceField("REP_ID");
        foreignKeyPERSON_REP.addTargetField("ID");
        table.addForeignKeyConstraint(foreignKeyPERSON_REP);

        return table;
    }

    public TableDefinition buildVEHICLETable() {
        TableDefinition table = new TableDefinition();
        table.setName("CMP3_VEHICLE");

        FieldDefinition fieldID = new FieldDefinition();
        fieldID.setName("ID");
        fieldID.setTypeName("NUMBER");
        fieldID.setSize(15);
        fieldID.setSubSize(0);
        fieldID.setIsPrimaryKey(true);
        fieldID.setIsIdentity(false);
        fieldID.setUnique(false);
        fieldID.setShouldAllowNull(false);
        table.addField(fieldID);

        FieldDefinition fieldTYPE = new FieldDefinition();
        fieldTYPE.setName("VEH_TYPE");
        fieldTYPE.setTypeName("VARCHAR2");
        fieldTYPE.setSize(15);
        fieldTYPE.setSubSize(0);
        fieldTYPE.setIsPrimaryKey(false);
        fieldTYPE.setIsIdentity(false);
        fieldTYPE.setUnique(false);
        fieldTYPE.setShouldAllowNull(true);
        table.addField(fieldTYPE);

        FieldDefinition fieldCAPACITY = new FieldDefinition();
        fieldCAPACITY.setName("CAPACITY");
        fieldCAPACITY.setTypeName("NUMBER");
        fieldCAPACITY.setSize(10);
        fieldCAPACITY.setSubSize(0);
        fieldCAPACITY.setIsPrimaryKey(false);
        fieldCAPACITY.setIsIdentity(false);
        fieldCAPACITY.setUnique(false);
        fieldCAPACITY.setShouldAllowNull(true);
        table.addField(fieldCAPACITY);

        FieldDefinition fieldOWNER_ID = new FieldDefinition();
        fieldOWNER_ID.setName("OWNER_ID");
        fieldOWNER_ID.setTypeName("NUMBER");
        fieldOWNER_ID.setSize(15);
        fieldOWNER_ID.setSubSize(0);
        fieldOWNER_ID.setIsPrimaryKey(false);
        fieldOWNER_ID.setIsIdentity(false);
        fieldOWNER_ID.setUnique(false);
        fieldOWNER_ID.setShouldAllowNull(true);
        table.addField(fieldOWNER_ID);

        ForeignKeyConstraint foreignKeyVEHICLE_COMPANY = new ForeignKeyConstraint();
        foreignKeyVEHICLE_COMPANY.setName("VEHICLE_COMPANY_FK");
        foreignKeyVEHICLE_COMPANY.setTargetTable("CMP3_COMPANY");
        foreignKeyVEHICLE_COMPANY.addSourceField("OWNER_ID");
        foreignKeyVEHICLE_COMPANY.addTargetField("ID");
        table.addForeignKeyConstraint(foreignKeyVEHICLE_COMPANY);

        return table;
    }
    
    public TableDefinition buildTIREINFOTable() {
        TableDefinition table = new TableDefinition();
        table.setName("CMP3_TIRE");

        FieldDefinition fieldID = new FieldDefinition();
        fieldID.setName("ID");
        fieldID.setTypeName("NUMBER");
        fieldID.setSize(15);
        fieldID.setSubSize(0);
        fieldID.setIsPrimaryKey(true);
        fieldID.setIsIdentity(false);
        fieldID.setUnique(false);
        fieldID.setShouldAllowNull(false);
        table.addField(fieldID);

        fieldID = new FieldDefinition();
        fieldID.setName("PRESSURE");
        fieldID.setTypeName("NUMBER");
        fieldID.setSize(15);
        fieldID.setSubSize(0);
        fieldID.setIsPrimaryKey(false);
        fieldID.setIsIdentity(false);
        fieldID.setUnique(false);
        fieldID.setShouldAllowNull(true);
        table.addField(fieldID);

        fieldID = new FieldDefinition();
        fieldID.setName("SPEEDRATING");
        fieldID.setTypeName("NUMBER");
        fieldID.setSize(15);
        fieldID.setSubSize(0);
        fieldID.setIsPrimaryKey(false);
        fieldID.setIsIdentity(false);
        fieldID.setUnique(false);
        fieldID.setShouldAllowNull(true);
        table.addField(fieldID);

        FieldDefinition fieldTYPE = new FieldDefinition();
        fieldTYPE.setName("TIRE_TYPE");
        fieldTYPE.setTypeName("VARCHAR2");
        fieldTYPE.setSize(15);
        fieldTYPE.setSubSize(0);
        fieldTYPE.setIsPrimaryKey(false);
        fieldTYPE.setIsIdentity(false);
        fieldTYPE.setUnique(false);
        fieldTYPE.setShouldAllowNull(true);
        table.addField(fieldTYPE);

        return table;
    }

    public TableDefinition buildVEH_SEQTable() {
        TableDefinition table = new TableDefinition();
        table.setName("CMP3_INHERITANCE_SEQ");

        FieldDefinition fieldSEQ_COUNT = new FieldDefinition();
        fieldSEQ_COUNT.setName("SEQ_COUNT");
        fieldSEQ_COUNT.setTypeName("NUMBER");
        fieldSEQ_COUNT.setSize(15);
        fieldSEQ_COUNT.setSubSize(0);
        fieldSEQ_COUNT.setIsPrimaryKey(false);
        fieldSEQ_COUNT.setIsIdentity(false);
        fieldSEQ_COUNT.setUnique(false);
        fieldSEQ_COUNT.setShouldAllowNull(false);
        table.addField(fieldSEQ_COUNT);

        FieldDefinition fieldSEQ_NAME = new FieldDefinition();
        fieldSEQ_NAME.setName("SEQ_NAME");
        fieldSEQ_NAME.setTypeName("VARCHAR2");
        fieldSEQ_NAME.setSize(80);
        fieldSEQ_NAME.setSubSize(0);
        fieldSEQ_NAME.setIsPrimaryKey(true);
        fieldSEQ_NAME.setIsIdentity(false);
        fieldSEQ_NAME.setUnique(false);
        fieldSEQ_NAME.setShouldAllowNull(false);
        table.addField(fieldSEQ_NAME);

        return table;
    }

    public TableDefinition buildAAATable() {
        TableDefinition table = new TableDefinition();
        table.setName("CMP3_AAA");

        FieldDefinition fieldID = new FieldDefinition();
        fieldID.setName("ID");
        fieldID.setTypeName("NUMBER");
        fieldID.setSize(15);
        fieldID.setSubSize(0);
        fieldID.setIsPrimaryKey(true);
        fieldID.setIsIdentity(false);
        fieldID.setUnique(false);
        fieldID.setShouldAllowNull(false);
        table.addField(fieldID);

        FieldDefinition fieldDTYPES = new FieldDefinition();
        fieldDTYPES.setName("DTYPES");
        fieldDTYPES.setTypeName("VARCHAR");
        fieldDTYPES.setSize(2);
        fieldDTYPES.setIsPrimaryKey(false);
        fieldDTYPES.setIsIdentity(false);
        fieldDTYPES.setUnique(false);
        fieldDTYPES.setShouldAllowNull(true);
        table.addField(fieldDTYPES);

        FieldDefinition fieldSTRINGDATA = new FieldDefinition();
        fieldSTRINGDATA.setName("FOO");
        fieldSTRINGDATA.setTypeName("VARCHAR");
        fieldSTRINGDATA.setSize(30);
        fieldSTRINGDATA.setIsPrimaryKey(false);
        fieldSTRINGDATA.setIsIdentity(false);
        fieldSTRINGDATA.setUnique(false);
        fieldSTRINGDATA.setShouldAllowNull(true);
        table.addField(fieldSTRINGDATA);

        return table;
    }

    public TableDefinition buildBBBTable() {
        TableDefinition table = new TableDefinition();
        table.setName("CMP3_BBB");

        FieldDefinition fieldID = new FieldDefinition();
        fieldID.setName("ID");
        fieldID.setTypeName("NUMBER");
        fieldID.setSize(15);
        fieldID.setSubSize(0);
        fieldID.setIsPrimaryKey(true);
        fieldID.setIsIdentity(false);
        fieldID.setUnique(false);
        fieldID.setShouldAllowNull(false);
        fieldID.setForeignKeyFieldName("CMP3_AAA.ID");
        table.addField(fieldID);

        FieldDefinition fieldSTRINGDATA = new FieldDefinition();
        fieldSTRINGDATA.setName("BAR");
        fieldSTRINGDATA.setTypeName("VARCHAR");
        fieldSTRINGDATA.setSize(30);
        fieldSTRINGDATA.setIsPrimaryKey(false);
        fieldSTRINGDATA.setIsIdentity(false);
        fieldSTRINGDATA.setUnique(false);
        fieldSTRINGDATA.setShouldAllowNull(true);
        table.addField(fieldSTRINGDATA);

        return table;
    }

    public TableDefinition buildCCCTable() {
        TableDefinition table = new TableDefinition();
        table.setName("CMP3_CCC");

        FieldDefinition fieldID = new FieldDefinition();
        fieldID.setName("ID");
        fieldID.setTypeName("NUMBER");
        fieldID.setSize(15);
        fieldID.setSubSize(0);
        fieldID.setIsPrimaryKey(true);
        fieldID.setIsIdentity(false);
        fieldID.setUnique(false);
        fieldID.setShouldAllowNull(false);
        fieldID.setForeignKeyFieldName("CMP3_BBB.ID");
        table.addField(fieldID);

        FieldDefinition fieldSTRINGDATA = new FieldDefinition();
        fieldSTRINGDATA.setName("XYZ");
        fieldSTRINGDATA.setTypeName("VARCHAR");
        fieldSTRINGDATA.setSize(30);
        fieldSTRINGDATA.setIsPrimaryKey(false);
        fieldSTRINGDATA.setIsIdentity(false);
        fieldSTRINGDATA.setUnique(false);
        fieldSTRINGDATA.setShouldAllowNull(true);
        table.addField(fieldSTRINGDATA);

        return table;
    }

    public TableDefinition buildCOMPUTERTable() {
        TableDefinition table = new TableDefinition();
        table.setName("CMP3_COMPUTER");

        FieldDefinition fieldMFR = new FieldDefinition();
        fieldMFR.setName("MFR");
        fieldMFR.setTypeName("VARCHAR");
        fieldMFR.setSize(30);
        fieldMFR.setIsPrimaryKey(true);
        fieldMFR.setIsIdentity(false);
        fieldMFR.setUnique(false);
        fieldMFR.setShouldAllowNull(false);
        table.addField(fieldMFR);

        FieldDefinition fieldSNO = new FieldDefinition();
        fieldSNO.setName("SNO");
        fieldSNO.setTypeName("NUMBER");
        fieldSNO.setSize(10);
        fieldSNO.setSubSize(0);
        fieldSNO.setIsPrimaryKey(true);
        fieldSNO.setIsIdentity(false);
        fieldSNO.setUnique(false);
        fieldSNO.setShouldAllowNull(false);
        table.addField(fieldSNO);

        FieldDefinition fieldDTYPE = new FieldDefinition();
        fieldDTYPE.setName("DTYPE");
        fieldDTYPE.setTypeName("VARCHAR");
        fieldDTYPE.setSize(20);
        fieldDTYPE.setIsPrimaryKey(false);
        fieldDTYPE.setIsIdentity(false);
        fieldDTYPE.setUnique(false);
        fieldDTYPE.setShouldAllowNull(true);
        table.addField(fieldDTYPE);

        return table;
    }

    public TableDefinition buildDESKTOPTable() {
        TableDefinition table = new TableDefinition();
        table.setName("CMP3_DESKTOP");

        FieldDefinition fieldMFR = new FieldDefinition();
        fieldMFR.setName("DT_MFR");
        fieldMFR.setTypeName("VARCHAR");
        fieldMFR.setSize(30);
        fieldMFR.setIsPrimaryKey(true);
        fieldMFR.setIsIdentity(false);
        fieldMFR.setUnique(false);
        fieldMFR.setShouldAllowNull(false);
        table.addField(fieldMFR);

        FieldDefinition fieldSNO = new FieldDefinition();
        fieldSNO.setName("DT_SNO");
        fieldSNO.setTypeName("NUMBER");
        fieldSNO.setSize(10);
        fieldSNO.setSubSize(0);
        fieldSNO.setIsPrimaryKey(true);
        fieldSNO.setIsIdentity(false);
        fieldSNO.setUnique(false);
        fieldSNO.setShouldAllowNull(false);
        table.addField(fieldSNO);

        ForeignKeyConstraint fkConstraint = new ForeignKeyConstraint();
        fkConstraint.setName("CMP3_DESKTOP_FK");
        fkConstraint.addSourceField("DT_MFR");
        fkConstraint.addSourceField("DT_SNO");
        fkConstraint.setTargetTable("CMP3_COMPUTER");
        fkConstraint.addTargetField("MFR");
        fkConstraint.addTargetField("SNO");
        table.addForeignKeyConstraint(fkConstraint);
        
        return table;
    }
    
    // Engineer-Desktop many-to-many relationship table
    public TableDefinition buildENGINEER_DESKTOPTable() {
        TableDefinition table = new TableDefinition();

        table.setName("CMP3_ENGINEER_DESKTOP");

        FieldDefinition fieldEngineerId = new FieldDefinition();
        fieldEngineerId.setName("ENGINEER_ID");
        fieldEngineerId.setTypeName("NUMBER");
        fieldEngineerId.setSize(15);
        fieldEngineerId.setSubSize(0);
        fieldEngineerId.setShouldAllowNull(false);
        fieldEngineerId.setIsPrimaryKey(true);
        fieldEngineerId.setUnique(false);
        fieldEngineerId.setIsIdentity(false);
        fieldEngineerId.setForeignKeyFieldName("CMP3_ENGINEER.ID");
        table.addField(fieldEngineerId);
    
        FieldDefinition fieldDesktopMFR = new FieldDefinition();
        fieldDesktopMFR.setName("DESKTOP_MFR");
        fieldDesktopMFR.setTypeName("VARCHAR");
        fieldDesktopMFR.setSize(30);
        fieldDesktopMFR.setIsPrimaryKey(true);
        fieldDesktopMFR.setIsIdentity(false);
        fieldDesktopMFR.setUnique(false);
        fieldDesktopMFR.setShouldAllowNull(false);
        table.addField(fieldDesktopMFR);

        FieldDefinition fieldDesktopSNO = new FieldDefinition();
        fieldDesktopSNO.setName("DESKTOP_SNO");
        fieldDesktopSNO.setTypeName("NUMBER");
        fieldDesktopSNO.setSize(10);
        fieldDesktopSNO.setSubSize(0);
        fieldDesktopSNO.setIsPrimaryKey(true);
        fieldDesktopSNO.setIsIdentity(false);
        fieldDesktopSNO.setUnique(false);
        fieldDesktopSNO.setShouldAllowNull(false);
        table.addField(fieldDesktopSNO);
        
        return table;
    }
    
    public TableDefinition buildLAPTOPTable() {
        TableDefinition table = new TableDefinition();
        table.setName("CMP3_LAPTOP");

        FieldDefinition fieldMFR = new FieldDefinition();
        fieldMFR.setName("MFR");
        fieldMFR.setTypeName("VARCHAR");
        fieldMFR.setSize(30);
        fieldMFR.setIsPrimaryKey(true);
        fieldMFR.setIsIdentity(false);
        fieldMFR.setUnique(false);
        fieldMFR.setShouldAllowNull(false);
        table.addField(fieldMFR);

        FieldDefinition fieldSNO = new FieldDefinition();
        fieldSNO.setName("SNO");
        fieldSNO.setTypeName("NUMBER");
        fieldSNO.setSize(10);
        fieldSNO.setSubSize(0);
        fieldSNO.setIsPrimaryKey(true);
        fieldSNO.setIsIdentity(false);
        fieldSNO.setUnique(false);
        fieldSNO.setShouldAllowNull(false);
        table.addField(fieldSNO);

        // FOREIGN KEY (MFR, SNO) REFERENCES CMP3_COMPUTER (MFR, SNO) 
        ForeignKeyConstraint fkConstraint = new ForeignKeyConstraint();
        fkConstraint.setName("CMP3_LAPTOP_FK1");
        fkConstraint.addSourceField("MFR");
        fkConstraint.addSourceField("SNO");
        fkConstraint.setTargetTable("CMP3_COMPUTER");
        fkConstraint.addTargetField("MFR");
        fkConstraint.addTargetField("SNO");
        
        table.addForeignKeyConstraint(fkConstraint);
        return table;
    }

    // Engineer-Laptop many-to-many relationship table
    public TableDefinition buildENGINEER_LAPTOPTable() {
        TableDefinition table = new TableDefinition();

        table.setName("CMP3_ENGINEER_LAPTOP");

        FieldDefinition fieldEngineerId = new FieldDefinition();
        fieldEngineerId.setName("ENGINEER_ID");
        fieldEngineerId.setTypeName("NUMBER");
        fieldEngineerId.setSize(15);
        fieldEngineerId.setSubSize(0);
        fieldEngineerId.setShouldAllowNull(false);
        fieldEngineerId.setIsPrimaryKey(true);
        fieldEngineerId.setUnique(false);
        fieldEngineerId.setIsIdentity(false);
        fieldEngineerId.setForeignKeyFieldName("CMP3_ENGINEER.ID");
        table.addField(fieldEngineerId);
    
        FieldDefinition fieldLaptopMFR = new FieldDefinition();
        fieldLaptopMFR.setName("LAPTOP_MFR");
        fieldLaptopMFR.setTypeName("VARCHAR");
        fieldLaptopMFR.setSize(30);
        fieldLaptopMFR.setIsPrimaryKey(true);
        fieldLaptopMFR.setIsIdentity(false);
        fieldLaptopMFR.setUnique(false);
        fieldLaptopMFR.setShouldAllowNull(false);
        table.addField(fieldLaptopMFR);

        FieldDefinition fieldLaptopSNO = new FieldDefinition();
        fieldLaptopSNO.setName("LAPTOP_SNO");
        fieldLaptopSNO.setTypeName("NUMBER");
        fieldLaptopSNO.setSize(10);
        fieldLaptopSNO.setSubSize(0);
        fieldLaptopSNO.setIsPrimaryKey(true);
        fieldLaptopSNO.setIsIdentity(false);
        fieldLaptopSNO.setUnique(false);
        fieldLaptopSNO.setShouldAllowNull(false);
        table.addField(fieldLaptopSNO);

        // FOREIGN KEY (LAPTOP_MFR, LAPTOP_SNO) REFERENCES CMP3_LAPTOP (MFR, SNO) 
        ForeignKeyConstraint fkConstraint = new ForeignKeyConstraint();
        fkConstraint.setName("CMP3_ENGINEER_LAPTOP_FK1");
        fkConstraint.addSourceField("LAPTOP_MFR");
        fkConstraint.addSourceField("LAPTOP_SNO");
        fkConstraint.setTargetTable("CMP3_LAPTOP");
        fkConstraint.addTargetField("MFR");
        fkConstraint.addTargetField("SNO");
        
        table.addForeignKeyConstraint(fkConstraint);
        return table;
    }
}
