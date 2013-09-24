/*
 * @author rockfacesoft
 */
package uk.co.rockfacesoftware.awis.response.impl;

import uk.co.rockfacesoftware.awis.response.structure.CSVField;

public class CSVStringField extends CSVField {
	
	public CSVStringField(String key){
		super(key);
	}
	
	/**
	 * Prepares the String for CSV
	 * 
	 * @return The CSV formatted String value of the data
	 */
	@Override
	public String getValue() {
		if(value!=null){
			return value.replace("\\\"", "\"").replace("\"", "\\\"")
					.replace("&nbsp;", " ").replaceAll("\\s+", " ");
			}
		return "";
	}

	@Override
	public String getRawValue() {
		return value;
	}
	
}
