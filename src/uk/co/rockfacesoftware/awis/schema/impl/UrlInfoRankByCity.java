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
public class UrlInfoRankByCity extends BasicAlexaSchema {

	public UrlInfoRankByCity() {
		super();
		schemaName = "UrlInfoRankByCity";
		tag = "aws:TrafficData";
		createSchema();
	}

	@Override
	public void createSchema() {
		schemaInstructions.add(new AlexaSchemaInstruction("DataUrl",
				FEATURE_TEXT, TYPE_STRING));
		schemaInstructions.add(new AlexaSchemaInstruction("DataUrl",
				FEATURE_ATTRIBUTE_VALUE, new String[] { "type" }, TYPE_STRING));
		SchemaInstruction rankCity = new AlexaSchemaInstruction("RankByCity",
				FEATURE_CHILD);
		SchemaInstruction cityDetails = new AlexaSchemaInstruction("City",
				FEATURE_LIST, new String[] { "Code", "Name" }, TYPE_STRING);
		cityDetails.add(new AlexaSchemaInstruction("Rank", FEATURE_TEXT,
				TYPE_LONG));

		SchemaInstruction contrib = new AlexaSchemaInstruction("Contribution",
				FEATURE_CHILD);

		contrib.add(new AlexaSchemaInstruction("PageViews", FEATURE_TEXT,
				TYPE_FLOAT));
		contrib.add(new AlexaSchemaInstruction("Users", FEATURE_TEXT,
				TYPE_FLOAT));

		SchemaInstruction perUser = new AlexaSchemaInstruction("PerUser",
				FEATURE_CHILD);
		perUser.add(new SchemaInstruction("AvePageViewsPerUser",
				"aws:AveragePageViews", FEATURE_TEXT, TYPE_FLOAT, true));
		contrib.add(perUser);
		cityDetails.add(contrib);
		rankCity.add(cityDetails);
		schemaInstructions.add(rankCity);
	}

	@Override
	public String[] getKeys() {
		return new String[] { "DataUrl", "DataUrl_type", "City_Code",
				"City_Name", "Rank", "PageViews", "Users",
				"AvePageViewsPerUser" };
	}

	@Override
	public boolean isList() {
		return true;
	}
	
	@Override
	public int[] getTypes() {
		return new int[]{TYPE_STRING, TYPE_STRING, TYPE_STRING, TYPE_STRING, TYPE_LONG, TYPE_FLOAT, TYPE_FLOAT, TYPE_FLOAT};
	}

}
