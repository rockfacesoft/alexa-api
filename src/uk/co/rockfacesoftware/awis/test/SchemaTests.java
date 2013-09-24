package uk.co.rockfacesoftware.awis.test;
import static org.junit.Assert.*;

import org.junit.Test;

import uk.co.rockfacesoftware.awis.schema.impl.CategoryBrowseSchema;
import uk.co.rockfacesoftware.awis.schema.impl.CategoryListingSchema;
import uk.co.rockfacesoftware.awis.schema.impl.SitesLinkingSchema;
import uk.co.rockfacesoftware.awis.schema.impl.TopSitesSchemaGroup;
import uk.co.rockfacesoftware.awis.schema.impl.TrafficHistorySchema;
import uk.co.rockfacesoftware.awis.schema.impl.UrlInfoSchemaGroup;
import uk.co.rockfacesoftware.awis.schema.structure.AlexaSchema;

public class SchemaTests {

	@Test
	public void test() {
		CategoryBrowseSchema cbSchema = new CategoryBrowseSchema();
		CategoryListingSchema clSchema = new CategoryListingSchema();
		SitesLinkingSchema slSchema = new SitesLinkingSchema();
		TrafficHistorySchema thSchema =  new TrafficHistorySchema();
		TopSitesSchemaGroup tsGroup =  new TopSitesSchemaGroup("Country");
		UrlInfoSchemaGroup uiGroup =  new UrlInfoSchemaGroup(new String[] {"AdultContent","Categories","ContactInfo","Keywords","Language","LinksInCount","OwnedDomains","Rank","RankByCity","RankByCountry","RelatedLinks","SiteData","Speed","UsageStats"});
		assertTrue(cbSchema.getKeys().length==cbSchema.getTypes().length);
		assertTrue(clSchema.getKeys().length==clSchema.getTypes().length);
		assertTrue(slSchema.getKeys().length==slSchema.getTypes().length);
		assertTrue(thSchema.getKeys().length==thSchema.getTypes().length);
		
		for(AlexaSchema schema:tsGroup.getSchemas()){
			assertTrue(schema.getKeys().length==schema.getTypes().length);
		}
		
		for(AlexaSchema schema:uiGroup.getSchemas()){
			assertTrue(schema.getKeys().length==schema.getTypes().length);
		}
		
		
	}

}
