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
public class UrlInfoRankByCountry extends BasicAlexaSchema {

	public UrlInfoRankByCountry() {
		super();
		schemaName = "UrlInfoRankByCountry";
		tag = "aws:TrafficData";
		createSchema();
	}

	@Override
	public void createSchema() {
		schemaInstructions.add(new AlexaSchemaInstruction("DataUrl",
				FEATURE_TEXT, TYPE_STRING));
		schemaInstructions.add(new AlexaSchemaInstruction("DataUrl",
				FEATURE_ATTRIBUTE_VALUE, new String[] { "type" }, TYPE_STRING));
		SchemaInstruction rankCountry = new AlexaSchemaInstruction(
				"RankByCountry", FEATURE_CHILD);
		SchemaInstruction countryDetails = new AlexaSchemaInstruction(
				"Country", FEATURE_LIST, new String[] { "Code" }, TYPE_STRING);

		countryDetails.add(new AlexaSchemaInstruction("Rank", FEATURE_TEXT,
				TYPE_LONG));

		SchemaInstruction contrib = new AlexaSchemaInstruction("Contribution",
				FEATURE_CHILD);

		contrib.add(new AlexaSchemaInstruction("PageViews", FEATURE_TEXT,
				TYPE_FLOAT));
		contrib.add(new AlexaSchemaInstruction("Users", FEATURE_TEXT,
				TYPE_FLOAT, true));

		countryDetails.add(contrib);
		rankCountry.add(countryDetails);
		schemaInstructions.add(rankCountry);
	}

	@Override
	public String[] getKeys() {
		return new String[] { "DataUrl", "DataUrl_type", "Country_Code",
				"Rank", "PageViews", "Users" };
	}

	@Override
	public boolean isList() {
		return true;
	}
	
	@Override
	public int[] getTypes() {
		return new int[]{TYPE_STRING, TYPE_STRING, TYPE_STRING, TYPE_LONG, TYPE_FLOAT, TYPE_FLOAT};
	}
}
