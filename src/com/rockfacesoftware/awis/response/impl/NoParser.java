/*
 * @author rockfacesoft
 */
package com.rockfacesoftware.awis.response.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import com.rockfacesoftware.awis.response.structure.Parser;

/**
 * 
 * Converts XML document into a list of strings held in BasicResponse
 */
public class NoParser implements Parser<BasicResponse> {
	AlexaXMLReader2 reader = null;
	private static int TIMEOUT_VALUE = 30000;
	private URL url;
	BasicResponse response;

	public NoParser() {
	}

	@Override
	public void setUrl(URL url) {
		this.url = url;
	}

	@Override
	public void parse() {
		if (response == null) {
			response = new BasicResponse();
		}
		buildDocument();
	}

	@Override
	public BasicResponse getResponse() {
		return response;
	}

	/**
	 * send request and build the resulting XML document
	 */
	@Override
	public void buildDocument() {
		HttpURLConnection con = null;
		try {
			con = (HttpURLConnection) url.openConnection();
			con.setConnectTimeout(TIMEOUT_VALUE);
			con.setReadTimeout(TIMEOUT_VALUE);
		} catch (IOException e) {
			e.printStackTrace();
		}

		int responseCode = 0;
		try {
			responseCode = con.getResponseCode();

			if (responseCode < 400) {
				URLConnection conn;
				conn = url.openConnection();
				inputStreamToString(conn.getInputStream());

			} else {
				System.out
						.println("Error http connection failed, responsecode = "
								+ responseCode);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void inputStreamToString(InputStream in) {
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
		String line;
		try {
			br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		response.addData(sb.toString());
	}

	public void clear() {
		response.clear();
	}
}
