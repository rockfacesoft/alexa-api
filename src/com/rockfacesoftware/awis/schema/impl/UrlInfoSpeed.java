/*
 * @author rockfacesoft
 */
package com.rockfacesoftware.awis.schema.impl;

import com.rockfacesoftware.awis.schema.structure.AlexaSchemaInstruction;
import com.rockfacesoftware.awis.schema.structure.BasicAlexaSchema;
import com.rockfacesoftware.awis.schema.structure.SchemaInstruction;

/**
 * 
 * Holds a list of instructions for the parser identifying the appropriate
 * elements of the XML document
 */
public class UrlInfoSpeed extends BasicAlexaSchema {

	public UrlInfoSpeed() {
		super();
		schemaName = "UrlInfoSpeed";
		tag = "aws:ContentData";
		createSchema();
	}

	@Override
	public void createSchema() {
		schemaInstructions.add(new AlexaSchemaInstruction("DataUrl",
				FEATURE_TEXT, TYPE_STRING));
		schemaInstructions.add(new AlexaSchemaInstruction("DataUrl",
				FEATURE_ATTRIBUTE_VALUE, new String[] { "type" }, TYPE_STRING));
		SchemaInstruction speed = new AlexaSchemaInstruction("Speed",
				FEATURE_CHILD);
		speed.add(new AlexaSchemaInstruction("MedianLoadTime", FEATURE_TEXT,
				TYPE_LONG));
		speed.add(new AlexaSchemaInstruction("Percentile", FEATURE_TEXT,
				TYPE_INT, true));
		schemaInstructions.add(speed);
	}

	@Override
	public String[] getKeys() {
		return new String[] { "DataUrl", "DataUrl_type", "MedianLoadTime",
				"Percentile" };
	}
	
	@Override
	public int[] getTypes() {
		return new int[]{TYPE_STRING, TYPE_STRING, TYPE_LONG, TYPE_INT};
	}

}
