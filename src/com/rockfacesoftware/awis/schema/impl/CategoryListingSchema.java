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
public class CategoryListingSchema extends BasicAlexaSchema {

	public CategoryListingSchema() {
		super();
		schemaName = "CategoryListings";
		tag = "aws:CategoryListings";
		createSchema();
	}

	@Override
	public void createSchema() {
		// create instructions
		// create parent
		SchemaInstruction listings = new AlexaSchemaInstruction("Listings",
				FEATURE_CHILD);
		schemaInstructions.add(new AlexaSchemaInstruction("RecursiveCount",
				FEATURE_TEXT, TYPE_LONG));
		schemaInstructions.add(new AlexaSchemaInstruction("Count",
				FEATURE_TEXT, TYPE_LONG));

		SchemaInstruction listingInstruction = new AlexaSchemaInstruction(
				"Listing", FEATURE_LIST);
		listingInstruction.add(new AlexaSchemaInstruction("DataUrl",
				FEATURE_TEXT, TYPE_STRING));
		listingInstruction.add(new AlexaSchemaInstruction("DataUrl",
				FEATURE_ATTRIBUTE_VALUE, new String[] { "type" }, TYPE_STRING));
		listingInstruction.add(new AlexaSchemaInstruction("Title",
				FEATURE_TEXT, TYPE_STRING));
		listingInstruction.add(new AlexaSchemaInstruction("PopularityRank",
				FEATURE_TEXT, TYPE_LONG));
		listingInstruction.add(new AlexaSchemaInstruction("AverageReview",
				FEATURE_TEXT, TYPE_FLOAT));
		listingInstruction.add(new AlexaSchemaInstruction("Description",
				FEATURE_TEXT, TYPE_STRING, true));

		
		listings.add(listingInstruction);
		schemaInstructions.add(listings);
	}

	@Override
	public String[] getKeys() {
		return new String[] { "RecursiveCount", "Count", "DataUrl",
				"DataUrl_type", "Title", "PopularityRank", "AverageReview",
				"Description" };
	}

	@Override
	public boolean isList() {
		return true;
	}
	
	@Override
	public int[] getTypes() {
		return new int[]{TYPE_LONG, TYPE_LONG, TYPE_STRING, TYPE_STRING, TYPE_STRING, TYPE_LONG, TYPE_FLOAT, TYPE_STRING};
	}

}
