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
public class TrafficHistorySchema extends BasicAlexaSchema {

	public TrafficHistorySchema() {
		super();
		schemaName = "TrafficHistory";
		tag = "aws:TrafficHistory";
		createSchema();
	}

	@Override
	public void createSchema() {
		// create instructions
		schemaInstructions.add(new AlexaSchemaInstruction("Range",
				FEATURE_TEXT, TYPE_INT));
		schemaInstructions.add(new AlexaSchemaInstruction("Site", FEATURE_TEXT,
				TYPE_STRING));
		schemaInstructions.add(new AlexaSchemaInstruction("Start",
				FEATURE_TEXT, TYPE_STRING));

		SchemaInstruction history = new AlexaSchemaInstruction(
				"HistoricalData", FEATURE_CHILD);

		SchemaInstruction data = new AlexaSchemaInstruction("Data",
				FEATURE_LIST);
		data.add(new AlexaSchemaInstruction("Date", FEATURE_TEXT, TYPE_STRING));

		SchemaInstruction pageViews = new SchemaInstruction("PageViews",
				"aws:PageViews", FEATURE_CHILD);
		pageViews.add(new SchemaInstruction("PageViewsPerMill",
				"aws:PerMillion", FEATURE_TEXT, TYPE_FLOAT));
		pageViews.add(new SchemaInstruction("PageViewsPerUser", "aws:PerUser",
				FEATURE_TEXT, TYPE_FLOAT));

		data.add(pageViews);
		data.add(new AlexaSchemaInstruction("Rank", FEATURE_TEXT, TYPE_LONG));

		SchemaInstruction reach = new SchemaInstruction("Reach", "aws:Reach",
				FEATURE_CHILD);
		reach.add(new SchemaInstruction("ReachPerMill", "aws:PerMillion",
				FEATURE_TEXT, TYPE_FLOAT,true));

		data.add(reach);
		history.add(data);
		schemaInstructions.add(history);

	}

	@Override
	public String[] getKeys() {
		return new String[] { "Range", "Site", "Start", "Date",
				"PageViewsPerMill", "PageViewsPerUser", "Rank", "ReachPerMill" };
	}

	@Override
	public boolean isList() {
		return true;
	}
	
	@Override
	public int[] getTypes() {
		return new int[]{TYPE_INT, TYPE_STRING, TYPE_STRING, TYPE_STRING, TYPE_FLOAT, TYPE_FLOAT, TYPE_LONG, TYPE_FLOAT};
	}
}
