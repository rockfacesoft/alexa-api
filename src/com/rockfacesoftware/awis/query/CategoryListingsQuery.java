/*
 * @author rockfacesoft
 */
package com.rockfacesoftware.awis.query;

import com.rockfacesoftware.awis.query.structure.Query;
import com.rockfacesoftware.awis.query.structure.QueryParameters;
import com.rockfacesoftware.awis.tools.HTTPCharacterConversion;

/**
 * Specialised CategoryListingsQuery QueryParameters
 */
public class CategoryListingsQuery extends QueryParameters {
	long start = 1;
	int count = 20;
	String path = null;
	String sortBy = "Popularity";
	boolean descriptions = true;
	boolean recursive = false;
	final String ACTION = "CategoryListings";
	final String RESPONSE_GROUP = "Listings";

	public CategoryListingsQuery(String path, long start, int count,
			String sortBy, boolean descriptions, boolean recursive,
			String accessKey, String secretAccessKey) {
		this.start = start;
		this.count = count;
		this.path = path;
		if (sortBy != null) {
			this.sortBy = sortBy;
		}
		this.descriptions = descriptions;
		this.recursive = recursive;

		query = new Query(ACTION, accessKey, secretAccessKey);
	}

	@Override
	public void specialiseQuery() {
		queryParams.put("ResponseGroup", RESPONSE_GROUP);
		queryParams.put("Path", formatPath());
		queryParams.put("Start", start + "");
		queryParams.put("Count", count + "");
		queryParams.put("SortBy", sortBy + "");
		queryParams.put("Descriptions", descriptions ? "True" : "False");
		queryParams.put("Recursive", recursive ? "True" : "False");

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
	 * converts path into HTTP safe String version
	 * 
	 * @return HTTP safe string version of path
	 */
	private String formatPath() {
		String safeUrl = HTTPCharacterConversion.convert(path);
		return safeUrl;
	}

}
