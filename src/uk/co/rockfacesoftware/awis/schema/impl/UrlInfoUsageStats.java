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
public class UrlInfoUsageStats extends BasicAlexaSchema {

	public UrlInfoUsageStats() {
		super();
		schemaName = "UrlInfoUsageStats";
		tag = "aws:TrafficData";
		createSchema();
	}

	@Override
	public void createSchema() {
		schemaInstructions.add(new AlexaSchemaInstruction("DataUrl",
				FEATURE_TEXT, TYPE_STRING));
		schemaInstructions.add(new AlexaSchemaInstruction("DataUrl",
				FEATURE_ATTRIBUTE_VALUE, new String[] { "type" }, TYPE_STRING));
		SchemaInstruction usageStats = new AlexaSchemaInstruction(
				"UsageStatistics", FEATURE_CHILD);

		SchemaInstruction usageStat = new AlexaSchemaInstruction(
				"UsageStatistic", FEATURE_LIST);

		SchemaInstruction timeRange = new AlexaSchemaInstruction("TimeRange",
				FEATURE_CHILD);
		timeRange.add(new SchemaInstruction("TimeRangeMonths", "aws:Months",
				FEATURE_TEXT, TYPE_INT));
		timeRange.add(new SchemaInstruction("TimeRangeDays", "aws:Days",
				FEATURE_TEXT, TYPE_INT));
		usageStat.add(timeRange);
		SchemaInstruction rank = new AlexaSchemaInstruction("Rank",
				FEATURE_CHILD);
		rank.add(new SchemaInstruction("Rank", "aws:Value", FEATURE_TEXT,
				TYPE_LONG));
		rank.add(new SchemaInstruction("RankDelta", "aws:Delta", FEATURE_TEXT,
				TYPE_LONG));
		usageStat.add(rank);
		SchemaInstruction reach = new AlexaSchemaInstruction("Reach",
				FEATURE_CHILD);
		SchemaInstruction reachRank = new AlexaSchemaInstruction("Rank",
				FEATURE_CHILD);
		reachRank.add(new SchemaInstruction("ReachRank", "aws:Value",
				FEATURE_TEXT, TYPE_LONG));
		reachRank.add(new SchemaInstruction("ReachRankDelta", "aws:Delta",
				FEATURE_TEXT, TYPE_LONG));
		reach.add(reachRank);

		SchemaInstruction perMill = new AlexaSchemaInstruction("PerMillion",
				FEATURE_CHILD);
		perMill.add(new SchemaInstruction("ReachPerMillion", "aws:Value",
				FEATURE_TEXT, TYPE_FLOAT));
		perMill.add(new SchemaInstruction("ReachPerMillionDelta", "aws:Delta",
				FEATURE_TEXT, TYPE_FLOAT));
		reach.add(perMill);
		usageStat.add(reach);

		SchemaInstruction pageViews = new AlexaSchemaInstruction("PageViews",
				FEATURE_CHILD);
		SchemaInstruction pVperMill = new AlexaSchemaInstruction("PerMillion",
				FEATURE_CHILD);
		pVperMill.add(new SchemaInstruction("PageViewsPerMillion", "aws:Value",
				FEATURE_TEXT, TYPE_FLOAT));
		pVperMill.add(new SchemaInstruction("PageViewsPerMillionDelta",
				"aws:Delta", FEATURE_TEXT, TYPE_FLOAT));
		pageViews.add(pVperMill);
		SchemaInstruction pVrank = new AlexaSchemaInstruction("Rank",
				FEATURE_CHILD);
		pVrank.add(new SchemaInstruction("PageViewsRank", "aws:Value",
				FEATURE_TEXT, TYPE_LONG));
		pVrank.add(new SchemaInstruction("PageViewsRankDelta", "aws:Delta",
				FEATURE_TEXT, TYPE_LONG));
		pageViews.add(pVrank);

		SchemaInstruction perUser = new AlexaSchemaInstruction("PerUser",
				FEATURE_CHILD);
		perUser.add(new SchemaInstruction("PageViewsPerUser", "aws:Value",
				FEATURE_TEXT, TYPE_FLOAT));
		perUser.add(new SchemaInstruction("PageViewsPerUserDelta", "aws:Delta",
				FEATURE_TEXT, TYPE_FLOAT, true));
		pageViews.add(perUser);
		usageStat.add(pageViews);

		usageStats.add(usageStat);
		schemaInstructions.add(usageStats);
	}

	@Override
	public String[] getKeys() {
		return new String[] { "DataUrl", "DataUrl_type", "TimeRangeMonths",
				"TimeRangeDays", "Rank", "RankDelta", "ReachRank",
				"ReachRankDelta", "ReachPerMillion", "ReachPerMillionDelta",
				"PageViewsPerMillion", "PageViewsPerMillionDelta",
				"PageViewsRank", "PageViewsRankDelta", "PageViewsPerUser",
				"PageViewsPerUserDelta" };
	}

	@Override
	public boolean isList() {
		return true;
	}
	
	@Override
	public int[] getTypes() {
		return new int[]{TYPE_STRING, TYPE_STRING, TYPE_INT, TYPE_INT, TYPE_LONG, TYPE_LONG, TYPE_LONG, TYPE_LONG, TYPE_FLOAT, TYPE_FLOAT, TYPE_FLOAT, TYPE_FLOAT, TYPE_LONG, TYPE_LONG, TYPE_FLOAT, TYPE_FLOAT};
	}

}
