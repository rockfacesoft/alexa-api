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
public class CategoryBrowseSchema extends BasicAlexaSchema {

	public CategoryBrowseSchema() {
		super();
		schemaName = "CategoryBrowse";
		tag = "aws:CategoryBrowse";
		createSchema();
	}

	@Override
	public void createSchema() {
		// create instructions
		SchemaInstruction categories = new AlexaSchemaInstruction("Categories",
				FEATURE_CHILD);
		SchemaInstruction category = new AlexaSchemaInstruction("Category",
				FEATURE_LIST);
		category.add(new AlexaSchemaInstruction("Path", FEATURE_TEXT,
				TYPE_STRING));
		category.add(new AlexaSchemaInstruction("Title", FEATURE_TEXT,
				TYPE_STRING));
		category.add(new AlexaSchemaInstruction("SubCategoryCount",
				FEATURE_TEXT, TYPE_LONG));
		category.add(new AlexaSchemaInstruction("TotalListingCount",
				FEATURE_TEXT, TYPE_LONG));
		category.add(new AlexaSchemaInstruction("Description", FEATURE_TEXT,
				TYPE_STRING, true));

		categories.add(category);
		schemaInstructions.add(categories);

	}

	@Override
	public String[] getKeys() {
		return new String[] { "Path", "Title", "SubCategoryCount",
				"TotalListingCount", "Description" };
	}

	@Override
	public boolean isList() {
		return true;
	}
	
	@Override
	public int[] getTypes() {
		return new int[]{TYPE_STRING, TYPE_STRING, TYPE_LONG, TYPE_LONG, TYPE_STRING};
	}

}
