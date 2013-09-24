/*
 * @author rockfacesoft
 */
package uk.co.rockfacesoftware.awis.query.structure;

import java.net.MalformedURLException;
import java.net.URL;

public class Query {
	private final String HASH_ALGORITHM = "HmacSHA256";
	private final String SIGNATURE_VERSION = "2";
	private String serviceHost = "awis.amazonaws.com";
	private final String DATEFORMAT_AWS = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
	private String queryString = null;
	private String accessKey = null;
	private String secretAccessKey = null;
	private String action = null;

	public Query() {

	}

	public Query(String action, String accessKey, String secretAccessKey) {
		this.action = action;
		this.accessKey = accessKey;
		this.secretAccessKey = secretAccessKey;
	}

	/**
	 * Change default service host to that of ats.amazonaws.com to handle
	 * requests to Alexa Top Sites
	 */
	public Query(String action, String accessKey, String secretAccessKey,
			String serviceHost) {
		this.action = action;
		this.accessKey = accessKey;
		this.secretAccessKey = secretAccessKey;
		this.serviceHost = serviceHost;
	}

	public String getQueryString() {
		return queryString;
	}

	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}

	public URL getQueryUrl() {
		URL queryUrl = null;
		try {
			queryUrl = new URL(queryString);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return queryUrl;
	}

	public String getSignatureVersion() {
		return SIGNATURE_VERSION;
	}

	public String getAction() {
		return action;
	}

	public String getAccessKey() {
		return accessKey;
	}

	public String getDateFormat() {
		return DATEFORMAT_AWS;
	}

	public String getServiceHost() {
		return serviceHost;
	}

	public String getSecretAccessKey() {
		return secretAccessKey;
	}

	public String getHashAlgorithm() {
		return HASH_ALGORITHM;
	}

}
