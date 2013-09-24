/*
 * @author rockfacesoft
 */
package uk.co.rockfacesoftware.awis.query.structure;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.SignatureException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;
import java.util.TreeMap;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Encoder;

/**
 * 
 * Prepares parameters for query
 */
public abstract class QueryParameters {
	protected Query query = new Query();

	protected Map<String, String> queryParams = new TreeMap<String, String>();

	/**
	 * setup basic parameters for query
	 */
	public void createBasicQuery() {
		try {
			String timestamp = getTimestampFromLocalTime(Calendar.getInstance()
					.getTime());

			queryParams.put("Action", query.getAction());
			queryParams.put("AWSAccessKeyId", query.getAccessKey());
			queryParams.put("SignatureMethod", query.getHashAlgorithm());
			queryParams.put("SignatureVersion", query.getSignatureVersion());
			queryParams.put("Timestamp", URLEncoder.encode(timestamp, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * add additional parameters to the queryParams;
	 */
	abstract public void specialiseQuery();

	/**
	 * Creates the request uri
	 */
	public void clearParameters() {
		queryParams.clear();
	}

	/**
	 * sign query and set it to queryString
	 */
	public void finalizeQuery() {
		try {
			String signature = null;
			String toSign = "GET\n" + query.getServiceHost() + "\n/\n"
					+ query.getQueryString();

			signature = generateSignature(toSign);

			String uri = "http://" + query.getServiceHost() + "/?"
					+ query.getQueryString() + "&Signature="
					+ URLEncoder.encode(signature, "UTF-8");
			System.out.println("Making request to:");
			System.out.println(uri + "\n");
			query.setQueryString(uri);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (SignatureException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Generates a timestamp for use with AWS request signing
	 * 
	 * @param date
	 *            current date
	 * @return timestamp
	 */

	public String getTimestampFromLocalTime(Date date) {
		SimpleDateFormat format = new SimpleDateFormat(query.getDateFormat());
		format.setTimeZone(TimeZone.getTimeZone("GMT"));
		return format.format(date);
	}

	/**
	 * Computes RFC 2104-compliant HMAC signature.
	 * 
	 * @param data
	 *            The data to be signed.
	 * @return The base64-encoded RFC 2104-compliant HMAC signature.
	 * @throws java.security.SignatureException
	 *             when signature generation fails
	 */
	private String generateSignature(String toSign)
			throws java.security.SignatureException {
		String result;
		try {
			// get an hmac key from the raw key bytes
			SecretKeySpec signingKey = new SecretKeySpec(query
					.getSecretAccessKey().getBytes(), query.getHashAlgorithm());
			// get a mac instance and initialize with the signing key
			Mac mac = Mac.getInstance(query.getHashAlgorithm());
			mac.init(signingKey);

			// compute the hmac on input data bytes
			byte[] rawHmac = mac.doFinal(toSign.getBytes());

			// base64-encode the hmac
			result = new BASE64Encoder().encode(rawHmac);

		} catch (Exception e) {
			throw new SignatureException("Failed to generate HMAC : "
					+ e.getMessage());
		}
		return result;
	}

	public URL getQueryUrl() {
		return query.getQueryUrl();
	}

}
