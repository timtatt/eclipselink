/*******************************************************************************
 * Copyright (c) 1998, 2007 Oracle. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0, which accompanies this distribution
 * and is available at http://www.eclipse.org/legal/epl-v10.html.
 *
 * Contributors:
 *     Oracle - initial API and implementation from Oracle TopLink
 ******************************************************************************/  
package utilities;

import java.util.Comparator;

public class RenameValue {
	private String sourceValue;

	private String replaceValue;

	public RenameValue(String source, String replace) {
		this.sourceValue = source;
		this.replaceValue = replace;
	}

	public String getSourceValue() {
		return this.sourceValue;
	}

	public String getReplaceValue() {
		return this.replaceValue;
	}

	public String replace(String str, boolean changeMade) {
		int srcLen = getSourceValue().length();
		int replaceLen = getReplaceValue().length();
		String newStr = str;

		int pos  = newStr.indexOf(getSourceValue());
		int lastPos = pos;

		while (pos >= 0) {
			String firstPart;
			String lastPart;

			firstPart = newStr.substring(0, pos);
			lastPart = newStr.substring(pos + srcLen, newStr.length());
			newStr = firstPart + getReplaceValue() + lastPart;
			lastPos = pos + replaceLen;
			pos = newStr.indexOf(getSourceValue(), lastPos);
			changeMade = true;
		}

		return newStr;
	}
	
	public String toString() {
		return "REPLACE> " + getSourceValue() + " -> " + getReplaceValue();
	}

	protected static Comparator<RenameValue> renameValueComparator() {
		return new Comparator<RenameValue>() {

			public int compare(RenameValue rv1, RenameValue rv2) {
				return rv2.getSourceValue().length()
						- rv1.getSourceValue().length();
			}

		};
	}
}