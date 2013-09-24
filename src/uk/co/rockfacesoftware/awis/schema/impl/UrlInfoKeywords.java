/*
 * @author rockfacesoft
 */
package uk.co.rockfacesoftware.awis.schema.impl;

import uk.co.rockfacesoftware.awis.schema.structure.AlexaSchemaInstruction;
import uk.co.rockfacesoftware.awis.schema.structure.BasicAlexaSchema;
import uk.co.rockfacesoftware.awis.schema.structure.SchemaInstruction;

/**
 * 
 * Holds a list of instructions for the parser identifying the appropriate
 * elements of the XML document
 */
public class UrlInfoKeywords extends BasicAlexaSchema {

	public UrlInfoKeywords() {
		super();
		schemaName = "UrlInfoKeywords";
		tag = "aws:ContentData";
		createSchema();
	}

	@Override
	public void createSchema() {
		schemaInstructions.add(new AlexaSchemaInstruction("DataUrl",
				FEATURE_TEXT, TYPE_STRING));
		schemaInstructions.add(new AlexaSchemaInstruction("DataUrl",
				FEATURE_ATTRIBUTE_VALUE, new String[] { "type" }, TYPE_STRING));
		SchemaInstruction keywords = new AlexaSchemaInstruction("Keywords",
				FEATURE_CHILD);
		keywords.add(new AlexaSchemaInstruction("Keyword", FEATURE_TEXT,
				TYPE_STRING, true));
		schemaInstructions.add(keywords);
	}

	@Override
	public String[] getKeys() {
		return new String[] { "DataUrl", "DataUrl_type", "Keyword" };
	}
	
	@Override
	public int[] getTypes() {
		return new int[]{TYPE_STRING, TYPE_STRING, TYPE_STRING};
	}
}
