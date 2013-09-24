/*
 * @author rockfacesoft
 */
package uk.co.rockfacesoftware.awis.query;

import uk.co.rockfacesoftware.awis.query.structure.Query;
import uk.co.rockfacesoftware.awis.query.structure.QueryParameters;
import uk.co.rockfacesoftware.awis.tools.HTTPCharacterConversion;

/**
 * Specialised TrafficHistoryQuery QueryParameters
 */
public class TrafficHistoryQuery extends QueryParameters {
	String url = null;
	int range = 31;
	String start = null;
	final String ACTION = "TrafficHistory";
	final String RESPONSE_GROUP = "History";

	public TrafficHistoryQuery(String url, int range, String start,
			String accessKey, String secretAccessKey) {
		this.url = url;
		this.range = range;
		this.start = start;
		query = new Query(ACTION, accessKey, secretAccessKey);
	}

	@Override
	public void specialiseQuery() {
		queryParams.put("Url", formatUrl());
		queryParams.put("ResponseGroup", RESPONSE_GROUP);
		queryParams.put("Range", range != 31 ? "" + range : "");
		queryParams.put("Start", start != null ? start : "");
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
		query.setQueryString(specialisedQuery);
	}

	/**
	 * converts url into HTTP safe String version
	 * 
	 * @return HTTP safe string version of url
	 */
	private String formatUrl() {
		String safeUrl = HTTPCharacterConversion.convert(url);
		return safeUrl;
	}

}
