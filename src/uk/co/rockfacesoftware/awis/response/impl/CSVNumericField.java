/*
 * @author rockfacesoft
 */
package uk.co.rockfacesoftware.awis.response.impl;

import uk.co.rockfacesoftware.awis.response.structure.CSVField;

public class CSVNumericField extends CSVField {
	
	
	public CSVNumericField(String key){
		super(key);
	}

	@Override
	public void set(String value) {
		this.value = value;
	}

	@Override
	public String getValue() {
		if(value!=null){
			return value.replace(",", "");
		}
		return "";
	}

	@Override
	public String getRawValue() {
		return value;
	}
	
}
