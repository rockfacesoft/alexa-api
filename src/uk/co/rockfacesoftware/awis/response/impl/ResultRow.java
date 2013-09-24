/*
 * @author rockfacesoft
 */
package uk.co.rockfacesoftware.awis.response.impl;


import uk.co.rockfacesoftware.awis.response.structure.CSVField;
import uk.co.rockfacesoftware.awis.schema.structure.AlexaSchema;

public class ResultRow {

	private CSVField[] fields;
	private String[]keys;
	private int[]types;
	
	public ResultRow(String[]keys, int[]types){
		this.keys = keys;
		this.types = types;
		int len = keys.length;
		fields = new CSVField[len];
		for(int i = 0; i < len; i++){
			CSVField field = null;
			switch(types[i]){
			case AlexaSchema.TYPE_BOOLEAN:
				field = new CSVBooleanField(keys[i]);
				fields[i] = field;
				break;
			case AlexaSchema.TYPE_STRING:
				field = new CSVStringField(keys[i]);
				fields[i] = field;
				break;
			default:
				field = new CSVNumericField(keys[i]);
				fields[i] = field;
				break;
			}
		}
	}
	
	public ResultRow(CSVField[] fields){
		this.fields = fields;
	}
	/**
	 * set a specific field in the row based on the key
	 */
	public void set(String key, String value){
		boolean found = false;
		int len = keys.length;
		for(int i = 0; i < len; i++){
			if(fields[i].getKey().equals(key)){
				fields[i].set(value);
				found = true;
				break;
			}
		}
		if(!found){
			System.out.println("Failed to match "+key);
		}
	}
	
	public void clear(){
		fields = null;
	}
	
	@Override
	public String toString() {
		String string = "";
		for (CSVField field : fields) {
			string += "{" + field.getKey() + ":" + field.getRawValue() + "},";
		}

		return string;
	}
	

	public CSVField[] getFields() {
		return fields;
	}
	
	/**
	 * generate a unique key to avoid duplication
	 */
	public String getUnique() {
		String string = "";
		for (CSVField field : fields) {
			string += field.getValue();
		}
		return string;
	}
	
	/**
	 * clone a partially completed row when multiple child data
	 * is found during recursive call 
	 */
	public ResultRow clone(){
		int len = keys.length;
		
		ResultRow newRow = new ResultRow(keys, types);
		for(int i = 0; i < len; i++){
			newRow.set(fields[i].getKey(), fields[i].getRawValue());
		}
		return newRow;
	}

	public CSVField get(String key) {
		for(CSVField field:fields){
			if(field.getKey().equals(key)){
				return field;
			}
		}
		return null;
	}

}
