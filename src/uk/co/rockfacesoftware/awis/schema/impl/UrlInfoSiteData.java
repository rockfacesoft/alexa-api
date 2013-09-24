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
public class UrlInfoSiteData extends BasicAlexaSchema {

	public UrlInfoSiteData() {
		super();
		schemaName = "UrlInfoSiteData";
		tag = "aws:ContentData";
		createSchema();
	}

	@Override
	public void createSchema() {
		schemaInstructions.add(new AlexaSchemaInstruction("DataUrl",
				FEATURE_TEXT, TYPE_STRING));
		schemaInstructions.add(new AlexaSchemaInstruction("DataUrl",
				FEATURE_ATTRIBUTE_VALUE, new String[] { "type" }, TYPE_STRING));
		SchemaInstruction siteData = new AlexaSchemaInstruction("SiteData",
				FEATURE_CHILD);
		siteData.add(new AlexaSchemaInstruction("Title", FEATURE_TEXT,
				TYPE_STRING));
		siteData.add(new AlexaSchemaInstruction("Description", FEATURE_TEXT,
				TYPE_STRING));
		siteData.add(new AlexaSchemaInstruction("OnlineSince", FEATURE_TEXT,
				TYPE_STRING, true));
		schemaInstructions.add(siteData);
	}

	@Override
	public String[] getKeys() {
		return new String[] { "DataUrl", "DataUrl_type", "Title",
				"Description", "OnlineSince" };
	}
	
	@Override
	public int[] getTypes() {
		return new int[]{TYPE_STRING, TYPE_STRING, TYPE_STRING, TYPE_STRING, TYPE_STRING};
	}

}
