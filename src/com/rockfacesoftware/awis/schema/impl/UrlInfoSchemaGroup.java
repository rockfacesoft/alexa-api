/*
 * @author rockfacesoft
 */
package com.rockfacesoftware.awis.schema.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.rockfacesoftware.awis.schema.structure.AlexaSchema;
import com.rockfacesoftware.awis.schema.structure.AlexaSchemaGroup;

/**
 * 
 * Handles multiple schemas generated from chosen ResponseGroups
 */
public class UrlInfoSchemaGroup implements AlexaSchemaGroup {
	private List<AlexaSchema> schemas = null;

	private int[] relatedResIds = null;
	private int[] contentResIds = null;
	private int[] trafficResIds = null;
	private int[] contactResIds = null;

	final static int CATEGORIES = 1;
	final static int RELATEDLINKS = 2;
	final static int RELATED = 3;
	final static int ADULT = 11;
	final static int KEYWORDS = 12;
	final static int LANGUAGE = 13;
	final static int LINKSINCOUNT = 14;
	final static int OWNEDDOMAINS = 15;
	final static int SITEDATA = 16;
	final static int SPEED = 17;
	final static int CONTENTDATA = 18;
	final static int RANK = 21;
	final static int RANKBYCITY = 22;
	final static int RANKBYCOUNTRY = 23;
	final static int USAGESTATS = 24;
	final static int TRAFFICDATA = 25;
	final static int CONTACTINFO = 31;

	public UrlInfoSchemaGroup(String[] responseGroups) {
		schemas = new ArrayList<AlexaSchema>();
		getRelatedResponseIds(responseGroups);
		getContentResponseIds(responseGroups);
		getTrafficResponseIds(responseGroups);
		getContactResponseIds(responseGroups);
		createSchemas();
		System.out.println(schemas.size()+" SchemaGroups");
	}

	@Override
	public void createSchemas() {
		if (relatedResIds != null) {
			createRelatedSchema();
		}
		if (contentResIds != null) {
			createContentSchema();
		}
		if (trafficResIds != null) {
			createTrafficSchema();
		}
		if (contactResIds != null) {
			schemas.add(new UrlInfoContact());
		}
	}

	private void createTrafficSchema() {

		int len = trafficResIds.length;
		for (int i = 0; i < len; i++) {
			switch (trafficResIds[i]) {
			case RANK:
				schemas.add(new UrlInfoRank());
				break;
			case RANKBYCITY:
				schemas.add(new UrlInfoRankByCity());
				break;
			case RANKBYCOUNTRY:
				schemas.add(new UrlInfoRankByCountry());
				break;
			case USAGESTATS:
				schemas.add(new UrlInfoUsageStats());
				schemas.add(new UrlInfoContributingSubDomains());
				break;
			case TRAFFICDATA:
				// equivalent to ResponseGroup=Rank,UsageStats
				schemas.add(new UrlInfoRank());
				schemas.add(new UrlInfoUsageStats());
				schemas.add(new UrlInfoContributingSubDomains());

				break;
			}
		}
	}

	private void createContentSchema() {
		int len = contentResIds.length;
		for (int i = 0; i < len; i++) {
			switch (contentResIds[i]) {
			case ADULT:
				schemas.add(new UrlInfoAdult());
				break;
			case KEYWORDS:
				schemas.add(new UrlInfoKeywords());
				break;
			case LINKSINCOUNT:
				schemas.add(new UrlInfoLinksInCount());
				break;
			case LANGUAGE:
				schemas.add(new UrlInfoLanguage());
				break;
			case OWNEDDOMAINS:
				schemas.add(new UrlInfoOwnedDomains());
				break;
			case SITEDATA:
				schemas.add(new UrlInfoSiteData());
				break;
			case SPEED:
				schemas.add(new UrlInfoSpeed());
				break;
			case CONTENTDATA:
				// equivalent to
				// ResponseGroup=SiteData,AdultContent,Speed,Language
				schemas.add(new UrlInfoSiteData());
				schemas.add(new UrlInfoAdult());
				schemas.add(new UrlInfoSpeed());
				schemas.add(new UrlInfoLanguage());
				break;
			}
		}

	}

	private void createRelatedSchema() {
		int len = relatedResIds.length;
		for (int i = 0; i < len; i++) {
			switch (relatedResIds[i]) {
			case CATEGORIES:
				schemas.add(new UrlInfoCategories());
				break;
			case RELATEDLINKS:
				schemas.add(new UrlInfoRelatedLinks());
				break;
			case RELATED:
				// equivalent to ResponseGroup=RelatedLinks,Categories
				schemas.add(new UrlInfoRelatedLinks());
				schemas.add(new UrlInfoCategories());
				break;
			}
		}

	}

	private void getRelatedResponseIds(String[] responseGroups) {
		Set<Integer> ids = new HashSet<Integer>();
		int count = 0;
		for (String res : responseGroups) {
			System.out.println(res);
			if (res.equals("Categories")) {
				ids.add(CATEGORIES);
				count = ids.size();
			}
			if (res.equals("RelatedLinks")) {
				ids.add(RELATEDLINKS);
				count = ids.size();
			}
			if (res.equals("Related")) {
				ids.clear();
				relatedResIds = new int[] { RELATED };
				count = 0;
				break;
			}
		}
		if (count == 2) {
			// use group call instead
			ids.clear();
			relatedResIds = new int[] { RELATED };
			count = 0;
		}
		if (count > 0) {
			int[] resIds = new int[count];
			Iterator<Integer> iterator = ids.iterator();
			for (int i = 0; i < count; i++) {
				
				if (iterator.hasNext()) {
					resIds[i] = iterator.next();
				} else {
					System.out.println("none left");
				}
			}
			ids.clear();
			// sort the array into ascending order
			Arrays.sort(resIds);
			relatedResIds = resIds;
		}
	}

	private void getContentResponseIds(String[] responseGroups) {
		Set<Integer> ids = new HashSet<Integer>();
		int count = 0;

		boolean content = false;
		for (String res : responseGroups) {
			if (res.equals("AdultContent")) {
				ids.add(ADULT);
				count = ids.size();
			}
			if (res.equals("Keywords")) {
				ids.add(KEYWORDS);
				count = ids.size();
			}
			if (res.equals("Language")) {
				ids.add(LANGUAGE);
				count = ids.size();
			}
			if (res.equals("LinksInCount")) {
				ids.add(LINKSINCOUNT);
				count = ids.size();
			}
			if (res.equals("OwnedDomains")) {
				ids.add(OWNEDDOMAINS);
				count = ids.size();
			}
			if (res.equals("SiteData")) {
				ids.add(SITEDATA);
				count = ids.size();
			}
			if (res.equals("Speed")) {
				ids.add(SPEED);
				count = ids.size();
			}
			if (res.equals("ContentData")) {
				// equivalent to
				// ResponseGroup=SiteData,AdultContent,Popups,Speed,Language
				ids.add(CONTENTDATA);
				content = true;
				count = ids.size();
			}

		}
		if (content) {
			// use group call instead
			ids.remove(ADULT);
			ids.remove(SPEED);
			ids.remove(LANGUAGE);
			count = ids.size();
		}

		if (count > 0) {
			int[] resIds = new int[count];
			Iterator<Integer> iterator = ids.iterator();
			for (int i = 0; i < count; i++) {
				resIds[i] = iterator.next();
			}
			ids.clear();
			// sort the array into ascending order
			Arrays.sort(resIds);
			contentResIds = resIds;
		}
	}

	private void getContactResponseIds(String[] responseGroups) {
		for (String res : responseGroups) {
			if (res.equals("ContactInfo")) {
				contactResIds = new int[] { CONTACTINFO };
			}
		}
	}

	private void getTrafficResponseIds(String[] responseGroups) {
		Set<Integer> ids = new HashSet<Integer>();
		int count = 0;

		boolean traffic = false;
		for (String res : responseGroups) {
			if (res.equals("Rank")) {
				ids.add(RANK);
				count = ids.size();
			}
			if (res.equals("RankByCity")) {
				ids.add(RANKBYCITY);
				count = ids.size();
			}
			if (res.equals("RankByCountry")) {
				ids.add(RANKBYCOUNTRY);
				count = ids.size();
			}
			if (res.equals("UsageStats")) {
				ids.add(USAGESTATS);
				count = ids.size();
			}
			if (res.equals("TrafficData")) {
				ids.add(TRAFFICDATA);
				traffic = true;
				count = ids.size();
			}
		}
		if (traffic) {
			// equivalent to ResponseGroup=Rank,UsageStats use group call
			// instead
			ids.remove(RANK);
			ids.remove(USAGESTATS);
			count = ids.size();
		}

		if (count > 0) {
			int[] resIds = new int[count];
			Iterator<Integer> iterator = ids.iterator();
			for (int i = 0; i < count; i++) {
				resIds[i] = iterator.next();
			}
			ids.clear();
			// sort the array into ascending order
			Arrays.sort(resIds);
			trafficResIds = resIds;
		}
	}

	@Override
	public List<AlexaSchema> getSchemas() {
		return schemas;
	}

	@Override
	public void clear() {
		for (AlexaSchema schema : schemas) {
			schema.clear();
		}
		schemas.clear();
	}

}
