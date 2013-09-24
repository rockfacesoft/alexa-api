/*
 * @author rockfacesoft
 */
package uk.co.rockfacesoftware.awis.schema.structure;

public class SchemaKey {

	String key;
	int dataType;

	public SchemaKey(String key, int dataType) {
		this.key = key;
		this.dataType = dataType;
	}

	public String getKey() {
		return key;
	}

	public int getDataType() {
		return dataType;
	}
}
