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
public class UrlInfoCategories extends BasicAlexaSchema {

	public UrlInfoCategories() {
		super();
		schemaName = "UrlInfoCategories";
		tag = "aws:Related";
		createSchema();
	}

	@Override
	public void createSchema() {
		schemaInstructions.add(new AlexaSchemaInstruction("DataUrl",
				FEATURE_TEXT, TYPE_STRING));
		schemaInstructions.add(new AlexaSchemaInstruction("DataUrl",
				FEATURE_ATTRIBUTE_VALUE, new String[] { "type" }, TYPE_STRING));
		SchemaInstruction categories = new AlexaSchemaInstruction("Categories",
				FEATURE_CHILD);
		SchemaInstruction categoryData = new AlexaSchemaInstruction(
				"CategoryData", FEATURE_LIST);
		categoryData.add(new AlexaSchemaInstruction("Title", FEATURE_TEXT,
				TYPE_STRING));
		categoryData.add(new AlexaSchemaInstruction("AbsolutePath",
				FEATURE_TEXT, TYPE_STRING, true));
		categories.add(categoryData);
		schemaInstructions.add(categories);
	}

	@Override
	public String[] getKeys() {
		return new String[] { "DataUrl", "DataUrl_type", "Title",
				"AbsolutePath" };
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
