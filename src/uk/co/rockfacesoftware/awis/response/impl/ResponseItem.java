/*
 * @author rockfacesoft
 */
package uk.co.rockfacesoftware.awis.response.impl;

public class ResponseItem {

	String key;
	String value;
	int type;

	public ResponseItem(String key, String value, int type) {
		this.key = key;
		this.value = value;
		this.type = type;
	}

	public String getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}

	public int getType() {
		return type;
	}
}
