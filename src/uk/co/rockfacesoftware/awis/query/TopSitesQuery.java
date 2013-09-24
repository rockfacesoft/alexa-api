/*
 * @author rockfacesoft
 */
package uk.co.rockfacesoftware.awis.query;

import uk.co.rockfacesoftware.awis.query.structure.Query;
import uk.co.rockfacesoftware.awis.query.structure.QueryParameters;

/**
 * Specialised TopSitesQuery QueryParameters
 */
public class TopSitesQuery extends QueryParameters {
	String countryCode = null;
	String cityCode = null;
	String url = null;
	long start = 1;
	int count = 100;
	String responseGroup = null;
	final String ACTION = "TopSites";
	final String SERVICE_HOST = "ats.amazonaws.com";

	public TopSitesQuery(String countryCode, String cityCode, long start,
			int count, String responseGroup, String accessKey,
			String secretAccessKey) {
		this.countryCode = countryCode;
		this.cityCode = cityCode;
		this.start = start;
		this.count = count;
		this.responseGroup = responseGroup;
		query = new Query(ACTION, accessKey, secretAccessKey, SERVICE_HOST);
	}

	@Override
	public void specialiseQuery() {
		queryParams.put("ResponseGroup", responseGroup);
		if (start != -1) {
			queryParams.put("Start", start + "");
		}
		if (count != -1) {
			queryParams.put("Count", count + "");
		}
		if (countryCode != null) {
			queryParams.put("CountryCode", countryCode);
		}
		if (cityCode != null) {
			queryParams.put("CityCode", cityCode);
		}
		String specialisedQuery = "";
		boolean first = true;
		for (String name : queryParams.keySet()) {
			if (first) {
				first = false;
			} else {
				specialisedQuery += "&";
			}
			specialisedQuery += name + "=" + queryParams.get(name);
		}
		System.out.println("'" + specialisedQuery + "'");
		query.setQueryString(specialisedQuery);
	}


}
