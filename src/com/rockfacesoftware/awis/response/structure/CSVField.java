/*
 * @author rockfacesoft
 */
package com.rockfacesoftware.awis.response.structure;

public abstract class CSVField implements Field<Object> {
	String key;
	protected String value = null;
	
	public CSVField(String key){
		this.key = key;
	}
	
	@Override
	public void set(String value) {
		this.value = value;
	}

	@Override
	public Object getValue() {
		return value;
	}

	@Override
	public String getKey() {
		return key;
	}
	
}
