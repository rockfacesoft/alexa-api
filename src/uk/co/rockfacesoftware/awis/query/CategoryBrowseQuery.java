/*
 * @author rockfacesoft
 */
package uk.co.rockfacesoftware.awis.query;

import uk.co.rockfacesoftware.awis.query.structure.Query;
import uk.co.rockfacesoftware.awis.query.structure.QueryParameters;
import uk.co.rockfacesoftware.awis.tools.HTTPCharacterConversion;

/**
 * Specialised CategoryBrowseQuery QueryParameters
 */
public class CategoryBrowseQuery extends QueryParameters {

	String[] responseGroups = null;
	final String ACTION = "CategoryBrowse";
	private String path = null;
	private boolean descriptions = true;

	public CategoryBrowseQuery(String path, boolean descriptions,
			String[] responseGroups, String accessKey, String secretAccessKey) {
		this.path = path;
		this.descriptions = descriptions;
		this.responseGroups = responseGroups;
		query = new Query(ACTION, accessKey, secretAccessKey);
	}

	@Override
	public void specialiseQuery() {
		queryParams.put("Path", formatPath());
		queryParams.put("ResponseGroup", getResponseGroupString());
		queryParams.put("Descriptions", descriptions ? "True" : "False");

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

	/**
	 * converts path into HTTP safe String version
	 * 
	 * @return HTTP safe string version of path
	 */
	private String formatPath() {
		String safeUrl = HTTPCharacterConversion.convert(path);
		return safeUrl;
	}

}
