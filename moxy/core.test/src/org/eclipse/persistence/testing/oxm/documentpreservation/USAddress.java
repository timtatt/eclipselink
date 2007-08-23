/*******************************************************************************
 * Copyright (c) 1998, 2007 Oracle. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0, which accompanies this distribution
 * and is available at http://www.eclipse.org/legal/epl-v10.html.
 *
 * Contributors:
 *     Oracle - initial API and implementation from Oracle TopLink
 ******************************************************************************/  
package org.eclipse.persistence.testing.oxm.documentpreservation;

/**
 *  @version $Header: USAddress.java 11-nov-2003.17:02:38 mmacivor Exp $
 *  @author  mmacivor
 *  @since   release specific (what release of product did this appear in)
 */

public class USAddress extends Address 
{
	public String state;
	public String zipCode;
	
	public String getState() 
	{
		return state;
	}
	public String getZipCode() 
	{
		return zipCode;
	}
	
	public void setState(String state) 
	{
		this.state = state;
	}
	public void setZipCode(String zipCode) 
	{
		this.zipCode = zipCode;
	}
}