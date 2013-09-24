/*
 * @author rockfacesoft
 */
package uk.co.rockfacesoftware.awis.schema.structure;

import java.util.ArrayList;
import java.util.List;

public class SchemaInstruction {

	List<SchemaInstruction> instructions;
	String tag;
	String key;
	int feature;
	String[] attributeNames;
	int type;
	protected boolean last = false;

	public SchemaInstruction(String key, String tag, int feature, int type) {
		this.tag = tag;
		this.feature = feature;
		this.key = key;
		this.type = type;
	}
	
	public SchemaInstruction(String key, String tag, int feature, int type, boolean last) {
		this.tag = tag;
		this.feature = feature;
		this.key = key;
		this.type = type;
		this.last = last;
	}

	public SchemaInstruction(String key, String tag, int feature) {
		this.tag = tag;
		this.feature = feature;
		this.key = key;
	}
	
	public SchemaInstruction(String key, String tag, int feature, boolean last) {
		this.tag = tag;
		this.feature = feature;
		this.key = key;
		this.last = last;
	}

	public SchemaInstruction(String key, int feature, String[] attributeNames,
			int type) {
		this.key = key;
		this.tag = "aws:" + key;
		this.feature = feature;
		this.attributeNames = attributeNames;
		this.type = type;
	}

	public SchemaInstruction(String key, String tag, int feature,
			String[] attributeNames, int type) {
		this.tag = tag;
		this.feature = feature;
		this.attributeNames = attributeNames;
		this.key = key;
		this.type = type;
	}

	public SchemaInstruction(String key, String tag, int feature,
			String[] attributeNames, List<SchemaInstruction> instructions,
			int type) {
		this.tag = tag;
		this.feature = feature;
		this.attributeNames = attributeNames;
		this.instructions = instructions;
		this.key = key;
		this.type = type;
	}

	public String getTag() {
		return tag;
	}

	public String[] getAttributenames() {
		return attributeNames;
	}

	public int getFeature() {
		return feature;
	}

	public SchemaInstruction[] getInstructions() {
		int size = instructions.size();
		SchemaInstruction instructArray[] = new SchemaInstruction[size];
		for(int i = 0; i < size; i++){
			instructArray[i]=instructions.get(i);
		}
		return instructArray;
	}

	public void add(SchemaInstruction instruction) {
		if (instructions == null) {
			instructions = new ArrayList<SchemaInstruction>();
		}
		this.instructions.add(instruction);
	}

	public String getKey() {
		return key;
	}

	public int getType() {
		return type;
	}
	
	public void lastInstruction(){
		last = true;
	}
	
	public boolean isLast(){
		return last;
	}

}
