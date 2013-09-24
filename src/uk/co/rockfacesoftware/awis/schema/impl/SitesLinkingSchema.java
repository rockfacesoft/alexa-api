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
public class SitesLinkingSchema extends BasicAlexaSchema {

	public SitesLinkingSchema() {
		super();
		schemaName = "SitesLinkingIn";
		tag = "aws:SitesLinkingIn";
		createSchema();
	}

	@Override
	public void createSchema() {
		// create instructions
		SchemaInstruction site = new AlexaSchemaInstruction("Site",
				FEATURE_LIST);
		site.add(new AlexaSchemaInstruction("Url", FEATURE_TEXT, TYPE_STRING));
		site.add(new AlexaSchemaInstruction("Title", FEATURE_TEXT, TYPE_STRING, true));
		schemaInstructions.add(site);
	}

	@Override
	public String[] getKeys() {
		return new String[] { "Url", "Title" };
	}

	@Override
	public boolean isList() {
		return true;
	}
	@Override
	public int[] getTypes() {
		return new int[]{TYPE_STRING, TYPE_STRING};
	}

}
