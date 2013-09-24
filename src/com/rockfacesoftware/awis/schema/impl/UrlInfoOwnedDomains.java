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
public class UrlInfoOwnedDomains extends BasicAlexaSchema {

	public UrlInfoOwnedDomains() {
		super();
		schemaName = "UrlInfoOwnedDomains";
		tag = "aws:ContentData";
		createSchema();
	}

	@Override
	public void createSchema() {
		schemaInstructions.add(new AlexaSchemaInstruction("DataUrl",
				FEATURE_TEXT, TYPE_STRING));
		schemaInstructions.add(new AlexaSchemaInstruction("DataUrl",
				FEATURE_ATTRIBUTE_VALUE, new String[] { "type" }, TYPE_STRING));
		SchemaInstruction ownedDomains = new AlexaSchemaInstruction(
				"OwnedDomains", FEATURE_CHILD);
		SchemaInstruction ownedDomain = new AlexaSchemaInstruction(
				"OwnedDomain", FEATURE_LIST);
		ownedDomain.add(new AlexaSchemaInstruction("Domain", FEATURE_TEXT,
				TYPE_STRING));
		ownedDomain.add(new AlexaSchemaInstruction("Title", FEATURE_TEXT,
				TYPE_STRING, true));
		ownedDomains.add(ownedDomain);
		schemaInstructions.add(ownedDomains);
	}

	@Override
	public String[] getKeys() {
		return new String[] { "DataUrl", "DataUrl_type", "Domain", "Title" };
	}

	@Override
	public boolean isList() {
		return true;
	}
	
	@Override
	public int[] getTypes() {
		return new int[]{TYPE_STRING, TYPE_STRING, TYPE_STRING, TYPE_STRING};
	}

}
