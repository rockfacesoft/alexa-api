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
public class TopSitesCity extends BasicAlexaSchema {

	public TopSitesCity() {
		super();
		schemaName = "TopSitesCity";
		tag = "aws:TopSites";
		createSchema();
	}

	@Override
	public void createSchema() {
		SchemaInstruction listInstruction = new AlexaSchemaInstruction("City",
				FEATURE_CHILD);
		listInstruction.add(new AlexaSchemaInstruction("CityName",
				FEATURE_TEXT, TYPE_STRING));
		listInstruction.add(new AlexaSchemaInstruction("CityCode",
				FEATURE_TEXT, TYPE_STRING));
		listInstruction.add(new AlexaSchemaInstruction("TotalSites",
				FEATURE_TEXT, TYPE_LONG));

		SchemaInstruction sites = new AlexaSchemaInstruction("Sites",
				FEATURE_CHILD);
		SchemaInstruction site = new AlexaSchemaInstruction("Site",
				FEATURE_LIST);

		site.add(new AlexaSchemaInstruction("DataUrl", FEATURE_TEXT,
				TYPE_STRING));

		SchemaInstruction city = new AlexaSchemaInstruction("City",
				FEATURE_CHILD);
		city.add(new AlexaSchemaInstruction("Rank", FEATURE_TEXT, TYPE_LONG));

		SchemaInstruction reach = new AlexaSchemaInstruction("Reach",
				FEATURE_CHILD);

		reach.add(new SchemaInstruction("ReachPerMillion", "aws:PerMillion",
				FEATURE_TEXT, TYPE_FLOAT));
		city.add(reach);

		SchemaInstruction pageViews = new AlexaSchemaInstruction("PageViews",
				FEATURE_CHILD);

		pageViews.add(new SchemaInstruction("PageViewsPerMillion",
				"aws:PerMillion", FEATURE_TEXT, TYPE_FLOAT));
		pageViews.add(new SchemaInstruction("PageViewsPerUser", "aws:PerUser",
				FEATURE_TEXT, TYPE_FLOAT, true));
		city.add(pageViews);
		site.add(city);
		sites.add(site);
		listInstruction.add(sites);

		schemaInstructions.add(listInstruction);
	}

	@Override
	public String[] getKeys() {
		return new String[] { "CityName", "CityCode", "TotalSites", "DataUrl",
				"Rank", "ReachPerMillion", "PageViewsPerMillion",
				"PageViewsPerUser" };
	}

	@Override
	public boolean isList() {
		return true;
	}
	
	@Override
	public int[] getTypes() {
		return new int[]{TYPE_STRING, TYPE_STRING, TYPE_LONG, TYPE_STRING, TYPE_LONG, TYPE_FLOAT, TYPE_FLOAT, TYPE_FLOAT};
	}

}
