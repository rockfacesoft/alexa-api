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
public class TopSitesListCities extends BasicAlexaSchema {

	public TopSitesListCities() {
		super();
		schemaName = "ListCities";
		tag = "aws:TopSites";
		createSchema();
	}

	@Override
	public void createSchema() {
		SchemaInstruction citiesInstruction = new AlexaSchemaInstruction(
				"Cities", FEATURE_CHILD);
		SchemaInstruction city = new AlexaSchemaInstruction("City",
				FEATURE_LIST);
		city.add(new AlexaSchemaInstruction("Name", FEATURE_TEXT, TYPE_STRING));
		city.add(new AlexaSchemaInstruction("Code", FEATURE_TEXT, TYPE_STRING));
		city.add(new AlexaSchemaInstruction("TotalSites", FEATURE_TEXT,
				TYPE_LONG));
		city.add(new AlexaSchemaInstruction("PageViews", FEATURE_TEXT,
				TYPE_FLOAT));
		city.add(new AlexaSchemaInstruction("Users", FEATURE_TEXT, TYPE_FLOAT, true));
		citiesInstruction.add(city);

		schemaInstructions.add(citiesInstruction);
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
