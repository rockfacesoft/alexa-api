/*
 * @author rockfacesoft
 */
package com.rockfacesoftware.awis.schema.structure;

import java.util.List;

public interface AlexaSchemaGroup {

	public void createSchemas();

	public List<AlexaSchema> getSchemas();

	public void clear();
}
