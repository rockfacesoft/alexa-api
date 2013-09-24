/*
 * @author rockfacesoft
 */
package uk.co.rockfacesoftware.awis.response.impl;

import uk.co.rockfacesoftware.awis.response.structure.CSVField;

public class CSVBooleanField extends CSVField {
	
	
	public CSVBooleanField(String key){
		super(key);
	}
	
	/**
	 * Converts string obtained from xml to the appropriate CSV boolean data
	 * type
	 * 
	 * @return The CSV formatted String value of the data
	 */

	@Override
	public String getValue() {
		if(value!=null){
			value = value.toLowerCase().trim();
			if (value.equals("yes") || value.equals("true") || value.equals("y")
					|| value.equals("t") || value.equals("1")) {
				return "TRUE";
			}
			if (value.equals("no") || value.equals("false") || value.equals("n")
					|| value.equals("f") || value.equals("0")) {
				return "FALSE";
			}
		}
		return "";
	}

	@Override
	public String getRawValue() {
		return value;
	}

	
}
