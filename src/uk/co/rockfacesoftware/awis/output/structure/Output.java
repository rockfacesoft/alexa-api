/*
 * @author rockfacesoft
 */
package uk.co.rockfacesoftware.awis.output.structure;

public interface Output<R> {
	public void save(R response);

	public void setSchemaName(String schemaName);

}
