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
public class UrlInfoRelatedLinks extends BasicAlexaSchema {

	public UrlInfoRelatedLinks() {
		super();
		schemaName = "UrlInfoRelatedLinks";
		tag = "aws:Related";
		createSchema();
	}

	@Override
	public void createSchema() {
		schemaInstructions.add(new AlexaSchemaInstruction("DataUrl",
				FEATURE_TEXT, TYPE_STRING));
		schemaInstructions.add(new AlexaSchemaInstruction("DataUrl",
				FEATURE_ATTRIBUTE_VALUE, new String[] { "type" }, TYPE_STRING));
		SchemaInstruction links = new AlexaSchemaInstruction("RelatedLinks",
				FEATURE_CHILD);
		SchemaInstruction link = new AlexaSchemaInstruction("RelatedLink",
				FEATURE_LIST);
		link.add(new AlexaSchemaInstruction("NavigableUrl", FEATURE_TEXT,
				TYPE_STRING));
		link.add(new SchemaInstruction("RelatedDataUrl", "aws:DataUrl",
				FEATURE_TEXT, TYPE_STRING));
		link.add(new SchemaInstruction("RelatedDataUrl", "aws:DataUrl",
				FEATURE_ATTRIBUTE_VALUE, new String[] { "type" }, TYPE_STRING));
		link.add(new AlexaSchemaInstruction("Title", FEATURE_TEXT, TYPE_STRING, true));
		links.add(link);
		schemaInstructions.add(links);
	}

	@Override
	public String[] getKeys() {
		return new String[] { "DataUrl", "DataUrl_type", "NavigableUrl",
				"RelatedDataUrl", "RelatedDataUrl_type", "Title" };
	}

	@Override
	public boolean isList() {
		return true;
	}
	
	@Override
	public int[] getTypes() {
		return new int[]{TYPE_STRING, TYPE_STRING, TYPE_STRING, TYPE_STRING, TYPE_STRING, TYPE_STRING};
	}

}
