/*
 * @author rockfacesoft
 */
package uk.co.rockfacesoftware.awis.response.structure;

public interface Field<T> {

	public void set(String value);
	public T getValue();
	public String getKey();
	public String getRawValue();
}
