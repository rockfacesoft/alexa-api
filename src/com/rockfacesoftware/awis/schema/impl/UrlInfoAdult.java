/*
 * @author rockfacesoft
 */
package com.rockfacesoftware.awis.schema.impl;

import com.rockfacesoftware.awis.schema.structure.AlexaSchemaInstruction;
import com.rockfacesoftware.awis.schema.structure.BasicAlexaSchema;

/**
 * 
 * Holds a list of instructions for the parser identifying the appropriate
 * elements of the XML document
 */
public class UrlInfoAdult extends BasicAlexaSchema {

	public UrlInfoAdult() {
		super();
		schemaName = "UrlInfoAdult";
		tag = "aws:ContentData";
		createSchema();
	}

	@Override
	public void createSchema() {
		schemaInstructions.add(new AlexaSchemaInstruction("DataUrl",
				FEATURE_TEXT, TYPE_STRING));
		schemaInstructions.add(new AlexaSchemaInstruction("DataUrl",
				FEATURE_ATTRIBUTE_VALUE, new String[] { "type" }, TYPE_STRING));
		schemaInstructions.add(new AlexaSchemaInstruction("AdultContent",
				FEATURE_TEXT, TYPE_BOOLEAN,true));
	}

	@Override
	public String[] getKeys() {
		return new String[] { "DataUrl", "DataUrl_type", "AdultContent" };
	}
	
	@Override
	public int[] getTypes() {
		return new int[]{TYPE_STRING, TYPE_STRING, TYPE_BOOLEAN};
	}

}
