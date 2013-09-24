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
public class UrlInfoContributingSubDomains extends BasicAlexaSchema {

	public UrlInfoContributingSubDomains() {
		super();
		schemaName = "UrlInfoContribuingSubDomains";
		tag = "aws:Alexa";
		createSchema();
	}

	@Override
	public void createSchema() {
		
		SchemaInstruction trafficData = new AlexaSchemaInstruction(
				"TrafficData", FEATURE_CHILD);
		trafficData.add(new AlexaSchemaInstruction("DataUrl",
				FEATURE_TEXT, TYPE_STRING));
		trafficData.add(new AlexaSchemaInstruction("DataUrl",
				FEATURE_ATTRIBUTE_VALUE, new String[] { "type" }, TYPE_STRING));
		SchemaInstruction contributing = new AlexaSchemaInstruction(
				"ContributingSubdomains", FEATURE_CHILD);

		SchemaInstruction contribute = new AlexaSchemaInstruction(
				"ContributingSubdomain", FEATURE_LIST);
		contribute.add(new SchemaInstruction("ContributingDataUrl",
				"aws:DataUrl", FEATURE_TEXT, TYPE_STRING));
		SchemaInstruction timeRange = new AlexaSchemaInstruction("TimeRange",
				FEATURE_CHILD);
		timeRange.add(new SchemaInstruction("TimeRangeMonths", "aws:Months",
				FEATURE_TEXT, TYPE_INT));
		contribute.add(timeRange);
		SchemaInstruction reach = new AlexaSchemaInstruction("Reach",
				FEATURE_CHILD);
		reach.add(new SchemaInstruction("ReachPercentage", "aws:Percentage",
				FEATURE_TEXT, TYPE_FLOAT));
		contribute.add(reach);
		SchemaInstruction pageViews = new AlexaSchemaInstruction("PageViews",
				FEATURE_CHILD);
		pageViews.add(new SchemaInstruction("PageViewsPercentage",
				"aws:Percentage", FEATURE_TEXT, TYPE_FLOAT));
		pageViews.add(new SchemaInstruction("PageViewsPerUser", "aws:PerUser",
				FEATURE_TEXT, TYPE_FLOAT, true));
		
		contribute.add(pageViews);

		contributing.add(contribute);
		trafficData.add(contributing);
		schemaInstructions.add(trafficData);
	}

	@Override
	public String[] getKeys() {
		return new String[] { "DataUrl", "DataUrl_type", "ContributingDataUrl",
				"TimeRangeMonths", "ReachPercentage", "PageViewsPerUser",
				"PageViewsPercentage" };
	}

	@Override
	public boolean isList() {
		return true;
	}
	
	@Override
	public int[] getTypes() {
		return new int[]{TYPE_STRING, TYPE_STRING, TYPE_STRING, TYPE_INT, TYPE_FLOAT, TYPE_FLOAT, TYPE_FLOAT};
	}

}
