/*
 * @author rockfacesoft
 */
package uk.co.rockfacesoftware.awis.query;

import uk.co.rockfacesoftware.awis.query.structure.Query;
import uk.co.rockfacesoftware.awis.query.structure.QueryParameters;
import uk.co.rockfacesoftware.awis.tools.HTTPCharacterConversion;

/**
 * Specialised UrlInfoQuery QueryParameters
 */
public class UrlInfoQuery extends QueryParameters {
	String url = null;
	String[] responseGroups = null;
	final String ACTION = "UrlInfo";

	public UrlInfoQuery(String url, String[] responseGroups, String accessKey,
			String secretAccessKey) {
		this.url = url;
		this.responseGroups = responseGroups;
		query = new Query(ACTION, accessKey, secretAccessKey);
	}

	@Override
	public void specialiseQuery() {
		queryParams.put("Url", formatUrl());
		queryParams.put("ResponseGroup", getResponseGroupString());

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

	/**
	 * converts responseGroups String array into HTTP safe string
	 * 
	 * @return HTTP safe string version of responseGroups
	 */
	private String getResponseGroupString() {
		String responseGroupString = "";
		int size = responseGroups.length;
		for (int i = 0; i < size; i++) {
			responseGroupString += responseGroups[i]
					+ (i + 1 < size ? "%2C" : "");
		}
		return responseGroupString;
	}

}
