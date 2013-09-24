/*
 * @author rockfacesoft
 */
package com.rockfacesoftware.awis;

import java.net.URL;

import com.rockfacesoftware.awis.output.impl.CSVOutput;
import com.rockfacesoftware.awis.output.impl.XMLOutput;
import com.rockfacesoftware.awis.query.CategoryBrowseQuery;
import com.rockfacesoftware.awis.query.CategoryListingsQuery;
import com.rockfacesoftware.awis.query.SitesLinkingInQuery;
import com.rockfacesoftware.awis.query.TopSitesQuery;
import com.rockfacesoftware.awis.query.TrafficHistoryQuery;
import com.rockfacesoftware.awis.query.UrlInfoQuery;
import com.rockfacesoftware.awis.query.structure.QueryConstructor;
import com.rockfacesoftware.awis.query.structure.QueryParameters;
import com.rockfacesoftware.awis.response.impl.NoParser;
import com.rockfacesoftware.awis.response.impl.StructuredSchemaParser;
import com.rockfacesoftware.awis.schema.impl.CategoryBrowseSchema;
import com.rockfacesoftware.awis.schema.impl.CategoryListingSchema;
import com.rockfacesoftware.awis.schema.impl.SitesLinkingSchema;
import com.rockfacesoftware.awis.schema.impl.TopSitesSchemaGroup;
import com.rockfacesoftware.awis.schema.impl.TrafficHistorySchema;
import com.rockfacesoftware.awis.schema.impl.UrlInfoSchemaGroup;
import com.rockfacesoftware.awis.schema.structure.AlexaSchema;
import com.rockfacesoftware.awis.schema.structure.AlexaSchemaGroup;

public class AlexaRequestController {

	private int outType;
	private String accessKey;
	private String secretAccessKey;
	public static final int XML = 0;
	public static final int CSV = 1;

	private StructuredSchemaParser parser = null;
	private NoParser noParser = null;
	private XMLOutput xmlOut = null;
	private CSVOutput csvOut = null;

	/**
	 * 
	 * @param outType AlexaRequestController.XML / AlexaRequestController.CSV
	 * @param filePrefix i.e. c:\\alexa\\out\\myrequest
	 * @param accessKey	AWSAccessKeyId 	A string, distributed by Amazon when you sign up to be a developer, that uniquely identifies the caller
	 * @param secretAccessKey AWSSecretAccessKeyId 
	 */
	public AlexaRequestController(int outType, String filePrefix,
			String accessKey, String secretAccessKey) {
		this.outType = outType;
		this.accessKey = accessKey;
		this.secretAccessKey = secretAccessKey;
		switch (outType) {
		case XML:
			noParser = new NoParser();
			xmlOut = new XMLOutput(filePrefix);
			break;
		case CSV:
			parser = new StructuredSchemaParser();
			csvOut = new CSVOutput(filePrefix);
			break;
		default:
			noParser = new NoParser();
			xmlOut = new XMLOutput(filePrefix);
			break;
		}
	}

	/**
	 *  Response Group	Data Returned
	 *  
	 *	AdultContent	Whether the site is likely to contain adult content ('yes' or 'no')
	 *	Categories		Up to 3 DMOZ (Open Directory) categories the site belongs to
	 *	ContactInfo		Contact information for the site owner or registrar
	 *	Keywords		Keywords relevant to site content
	 *	Language		Content language code and character-encoding (note that this may not match the language or character encoding of any given page on the website because the languange and character set returned are those of the majority of pages)
	 *	LinksInCount	A count of links pointing in to this site
	 *	OwnedDomains	Other domains owned by the same owner as this site
	 *	Rank			The Alexa three month average traffic rank
	 *	RankByCity		Percentage of viewers, page views, and traffic rank broken out by city
	 *	RankByCountry	Percentage of viewers, page views, and traffic rank broken out by country
	 *	RelatedLinks	Up to 11 related links
	 *	SiteData		Title, description, and date the site was created
	 *	Speed			Median load time and percent of known sites that are slower
	 *	UsageStats		Usage statistics such as reach and page views
	 *	
	 *	Related			Up to 11 related links and up to 3 DMOZ categories (equivalent to ResponseGroup=RelatedLinks,Categories)
	 *	TrafficData		Traffic rank and usage statistics (equivalent to ResponseGroup=Rank,UsageStats)
	 *	ContentData		Information about the site's content (equivalent to ResponseGroup=SiteData,AdultContent,Popups,Speed,Language)
	 *
	 * @param urls array of urls/domains that you wish to query
	 * @param responseGroups array of UrlInfo response groups 
	 */
	public void UrlInfo(String[] urls, String[] responseGroups) {
		QueryConstructor construtor = new QueryConstructor();
		AlexaSchemaGroup sGroup = new UrlInfoSchemaGroup(responseGroups);
		for (String url : urls) {
			System.out.println("Starting '"+url+"'");
			QueryParameters q = new UrlInfoQuery(url, responseGroups,
					accessKey, secretAccessKey);
			construtor.construct(q);
			for (AlexaSchema schema : sGroup.getSchemas()) {
				schema.createSchema();
				parseAndSave(q.getQueryUrl(), schema);
			}
			if(parser!=null){
				parser.clear();
			}
			if(noParser!=null){
				noParser.clear();
			}
		}
	}

	/**
	 *  Response Group	Data Returned
	 *  
	 *	Country			Top sites for a given country
	 *	City			Top sites for a given city
	 *	ListCountries	Countries that have top sites
	 *	ListCities		Cities that have top sites
     *
	 * @param countryCodes array of Valid country code (a list of country codes is available from ListCountries ResponseGroup). A global list of top sites is returned by default. (null if not required)
	 * @param cityCodes array of Valid city code (a list of city codes is available from ListCities ResponseGroup). A global list of top sites is returned by default. (null if not required)
	 * @param start Number of result at which to start. Used for paging through results. Default value is '1'.
	 * @param totalCount Number of results (maximum) per page to return. Default value is '100'.
	 * @param responseGroup TopSites response group 
	 */
	public void TopSites(String[] countryCodes, String[] cityCodes, long start,
			long totalCount, String responseGroup) {
		QueryConstructor construtor = new QueryConstructor();
		AlexaSchemaGroup sGroup = new TopSitesSchemaGroup(responseGroup);
		if (countryCodes != null) {
			for (String countryCode : countryCodes) {
				
				long position = start;
				while (position < totalCount) {
					System.out.println("Starting CountryCode: "+countryCode+", "+position+"/"+totalCount);
					int count = (int) (totalCount - position > 20 ? 20
							: totalCount - position);
					QueryParameters q = new TopSitesQuery(countryCode, null,
							position, count, responseGroup, accessKey,
							secretAccessKey);
					construtor.construct(q);
					for (AlexaSchema schema : sGroup.getSchemas()) {
						schema.createSchema();
						parseAndSave(q.getQueryUrl(), schema);
						position += count;
					}
					if(parser!=null){
						parser.clear();
					}
					if(noParser!=null){
						noParser.clear();
					}
				}
			}
		} else if (cityCodes != null) {
			for (String cityCode : cityCodes) {
				long position = start;
				while (position < totalCount) {
					System.out.println("Starting CityCode: "+cityCode+", "+position+"/"+totalCount);
					int count = (int) (totalCount - position > 20 ? 20
							: totalCount - position);
					QueryParameters q = new TopSitesQuery(null, cityCode,
							position, count, responseGroup, accessKey,
							secretAccessKey);
					construtor.construct(q);
					for (AlexaSchema schema : sGroup.getSchemas()) {
						schema.createSchema();
						parseAndSave(q.getQueryUrl(), schema);
						position += count;
					}
					if(parser!=null){
						parser.clear();
					}
					if(noParser!=null){
						noParser.clear();
					}
				}
			}
		} else {
			long position = start;
			while (position < totalCount) {
				System.out.println("Starting "+position+"/"+totalCount);
				int count = (int) (totalCount - position > 20 ? 20 : totalCount
						- position);
				QueryParameters q = new TopSitesQuery(null, null, position,
						count, responseGroup, accessKey, secretAccessKey);
				construtor.construct(q);
				for (AlexaSchema schema : sGroup.getSchemas()) {
					schema.createSchema();
					parseAndSave(q.getQueryUrl(), schema);
					position += count;
				}
				if(parser!=null){
					parser.clear();
				}
				if(noParser!=null){
					noParser.clear();
				}
			}
		}
	}

	/**
	 * 
	 * @param urls array of urls/domains that you wish to query
	 * @param range Number of days to return. Note that the response document may contain fewer results than this maximum if data is not available. Default value is '31'. Maximum value is '31'
	 * @param start Start date for results. The first start available date is 20070801 (August 1, 2007).
	 */
	public void TrafficHistory(String[] urls, int range, String start) {
		QueryConstructor construtor = new QueryConstructor();
		AlexaSchema schema = new TrafficHistorySchema();
		for (String url : urls) {
			System.out.println("Starting '"+url+"'");
			QueryParameters q = new TrafficHistoryQuery(url, range, start,
					accessKey, secretAccessKey);
			construtor.construct(q);
			schema.createSchema();
			parseAndSave(q.getQueryUrl(), schema);
			if(parser!=null){
				parser.clear();
			}
			if(noParser!=null){
				noParser.clear();
			}
		}
	}

	/**
	 * 
	 * @param urls array of urls/domains that you wish to query
	 * @param start Number of result at which to start. Used for paging through results. Default value is '0.'
	 * @param totalCount Maximum number of results per page to return. Note that the response document may contain fewer results than this maximum. Default value is '10'
	 */
	public void SitesLinkingIn(String[] urls, long start, long totalCount) {
		QueryConstructor construtor = new QueryConstructor();
		AlexaSchema schema = new SitesLinkingSchema();
		for (String url : urls) {
			long position = start;
			while (position < totalCount) {
				System.out.println("Starting '"+url+"' "+position+"/"+totalCount);
				int count = (int) (totalCount - position > 20 ? 20 : totalCount
						- position);
				QueryParameters q = new SitesLinkingInQuery(url, position,
						count, accessKey, secretAccessKey);
				construtor.construct(q);
				schema.createSchema();
				parseAndSave(q.getQueryUrl(), schema);
				position += count;
				if(parser!=null){
					parser.clear();
				}
				if(noParser!=null){
					noParser.clear();
				}
			}
		}
	}

	/**
	 * 	Response Group		Data Returned
	 *	Categories			All sub-categories within the specified category path
	 *	RelatedCategories	Categories that are related to the specified category path
	 *	LanguageCategories	Language categories in which the specified category path is available
	 *	LetterBars			"Letter Bars" (A, B, C, etc.) for categories that contain them
	 * 
	 * @param paths array of category paths you wish to query i.e. {"Top/Arts", "Top/Business/Automotive"} a list of paths is available from DMOZ.org 
	 * @param responseGroups array of CategoryBrowse response groups 
	 * @param descriptions Whether to return descriptions with categories: (True | False)
	 */
	public void CategoryBrowse(String[] paths, String[] responseGroups,
			boolean descriptions) {
		QueryConstructor construtor = new QueryConstructor();
		AlexaSchema schema = new CategoryBrowseSchema();
		for (String path : paths) {
			System.out.println("Starting '"+path+"'");
			QueryParameters q = new CategoryBrowseQuery(path, descriptions,
					responseGroups, accessKey, secretAccessKey);
			construtor.construct(q);
			schema.createSchema();
			parseAndSave(q.getQueryUrl(), schema);
			if(parser!=null){
				parser.clear();
			}
			if(noParser!=null){
				noParser.clear();
			}
		}
	}

	/**
	 * 
	 * @param paths array of category paths you wish to query i.e. {"Top/Arts", "Top/Business/Automotive"} a list of paths is available from DMOZ.org
	 * @param start 1-based index of result at which to start. Note: An empty document will be returned if this value exceeds the total number of available results.
	 * @param totalCount Number of results to return for this request, beginning from specified Start number
	 * @param sortBy How to sort the results returned by this service: ( Popularity | Title | AverageReview )
	 * @param descriptions Whether to return descriptions with categories: (True | False)
	 * @param recursive Whether to return listings for the current category only, or for the current category plus all subcategories: (True | False)
	 */
	public void CategoryListings(String[] paths, long start, long totalCount,
			String sortBy, boolean descriptions, boolean recursive) {
		QueryConstructor construtor = new QueryConstructor();
		AlexaSchema schema = new CategoryListingSchema();

		for (String path : paths) {
			long position = start;

			while (position < totalCount) {
				System.out.println("Starting '"+path+"' "+position+"/"+totalCount);
				int count = (int) (totalCount - position > 20 ? 20 : totalCount
						- position);
				QueryParameters q = new CategoryListingsQuery(path, position,
						count, sortBy, descriptions, recursive, accessKey,
						secretAccessKey);
				construtor.construct(q);
				schema.createSchema();
				parseAndSave(q.getQueryUrl(), schema);
				position += count;
				if(parser!=null){
					parser.clear();
				}
				if(noParser!=null){
					noParser.clear();
				}
			}

		}
	}

	private void parseAndSave(URL url, AlexaSchema schema) {
		switch (outType) {
		case XML:
			noParser.setUrl(url);
			noParser.parse();
			xmlOut.setSchemaName(schema.getName());
			xmlOut.save(noParser.getResponse());
			break;
		case CSV:
			parser.setUrl(url);
			parser.setSchema(schema);
			parser.parse();
			System.out.println(schema.getName());
			csvOut.setSchemaName(schema.getName());
			csvOut.save(parser.getResponse());
			
			break;
		default:
			noParser.setUrl(url);
			noParser.parse();
			xmlOut.setSchemaName(schema.getName());
			xmlOut.save(noParser.getResponse());
			break;
		}
	}
}
