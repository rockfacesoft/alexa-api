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
public class TopSitesListCountries extends BasicAlexaSchema {

	public TopSitesListCountries() {
		super();
		schemaName = "ListCountries";
		tag = "aws:TopSites";
		createSchema();
	}

	@Override
	public void createSchema() {
		SchemaInstruction countriesInstruction = new AlexaSchemaInstruction(
				"Countries", FEATURE_CHILD);
		SchemaInstruction country = new AlexaSchemaInstruction("Country",
				FEATURE_LIST);
		country.add(new AlexaSchemaInstruction("Name", FEATURE_TEXT,
				TYPE_STRING));
		country.add(new AlexaSchemaInstruction("Code", FEATURE_TEXT,
				TYPE_STRING));
		country.add(new AlexaSchemaInstruction("TotalSites", FEATURE_TEXT,
				TYPE_LONG));
		country.add(new AlexaSchemaInstruction("PageViews", FEATURE_TEXT,
				TYPE_FLOAT));
		country.add(new AlexaSchemaInstruction("Users", FEATURE_TEXT,
				TYPE_FLOAT, true));
		countriesInstruction.add(country);

		schemaInstructions.add(countriesInstruction);
	}

	@Override
	public String[] getKeys() {
		return new String[] { "Name", "Code", "TotalSites", "PageViews",
				"Users" };
	}

	@Override
	public boolean isList() {
		return true;
	}
	
	@Override
	public int[] getTypes() {
		return new int[]{TYPE_STRING, TYPE_STRING, TYPE_LONG, TYPE_FLOAT, TYPE_FLOAT};
	}

}
