/*
 * @author rockfacesoft
 */
package uk.co.rockfacesoftware.awis.schema.impl;

import uk.co.rockfacesoftware.awis.schema.structure.AlexaSchemaInstruction;
import uk.co.rockfacesoftware.awis.schema.structure.BasicAlexaSchema;

/**
 * 
 * Holds a list of instructions for the parser identifying the appropriate
 * elements of the XML document
 */
public class UrlInfoLinksInCount extends BasicAlexaSchema {

	public UrlInfoLinksInCount() {
		super();
		schemaName = "UrlInfoLinksInCount";
		tag = "aws:Alexa";
		createSchema();
	}

	@Override
	public void createSchema() {
		AlexaSchemaInstruction linksIn = new AlexaSchemaInstruction("ContentData", FEATURE_CHILD);
		linksIn.add(new AlexaSchemaInstruction("DataUrl",
				FEATURE_TEXT, TYPE_STRING));
		linksIn.add(new AlexaSchemaInstruction("DataUrl",
				FEATURE_ATTRIBUTE_VALUE, new String[] { "type" }, TYPE_STRING));
		linksIn.add(new AlexaSchemaInstruction("LinksInCount",
				FEATURE_TEXT, TYPE_LONG, true));
		schemaInstructions.add(linksIn);
	}

	@Override
	public String[] getKeys() {
		return new String[] { "DataUrl", "DataUrl_type", "LinksInCount" };
	}
	
	@Override
	public int[] getTypes() {
		return new int[]{TYPE_STRING, TYPE_STRING, TYPE_LONG};
	}

}
