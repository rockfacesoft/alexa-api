/*
 * @author rockfacesoft
 */
package uk.co.rockfacesoftware.awis.schema.structure;

public class AlexaSchemaInstruction extends SchemaInstruction {

	public AlexaSchemaInstruction(String key, int feature, int type) {
		super(key, "aws:" + key, feature, type);
	}
	
	public AlexaSchemaInstruction(String key, int feature, int type, boolean last) {
		super(key, "aws:" + key, feature, type);
		this.last = last;
	}

	public AlexaSchemaInstruction(String key, int feature,
			String[] attributeNames, int type) {
		super(key, "aws:" + key, feature, attributeNames, type);
	}
	
	public AlexaSchemaInstruction(String key, int feature,
			String[] attributeNames, int type, boolean last) {
		super(key, "aws:" + key, feature, attributeNames, type);
		this.last = last;
	}

	public AlexaSchemaInstruction(String key, int feature) {
		super(key, "aws:" + key, feature);
	}
	
	public AlexaSchemaInstruction(String key, int feature, boolean last) {
		super(key, "aws:" + key, feature);
		this.last = last;
	}

}
