/*
 * @author rockfacesoft
 */
package uk.co.rockfacesoftware.awis.query;

import uk.co.rockfacesoftware.awis.query.structure.Query;
import uk.co.rockfacesoftware.awis.query.structure.QueryParameters;
import uk.co.rockfacesoftware.awis.tools.HTTPCharacterConversion;

/**
 * Specialised SitesLinkingInQuery QueryParameters
 */
public class SitesLinkingInQuery extends QueryParameters {
	String url = null;
	long start = 1;
	int count = 20;
	final String ACTION = "SitesLinkingIn";
	final String RESPONSE_GROUP = "SitesLinkingIn";

	public SitesLinkingInQuery(String url, long start, int count,
			String accessKey, String secretAccessKey) {
		this.url = url;
		this.start = start;
		this.count = count;
		query = new Query(ACTION, accessKey, secretAccessKey);
	}

	@Override
	public void specialiseQuery() {
		queryParams.put("ResponseGroup", RESPONSE_GROUP);
		queryParams.put("Url", formatUrl());
		queryParams.put("Start", start + "");
		queryParams.put("Count", count + "");

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
