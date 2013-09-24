/*
 * @author rockfacesoft
 */
package uk.co.rockfacesoftware.awis.schema.structure;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class BasicAlexaSchema implements AlexaSchema {
	protected List<SchemaInstruction> schemaInstructions = null;
	protected String schemaName;
	protected String tag;

	public BasicAlexaSchema() {
		schemaInstructions = new CopyOnWriteArrayList<SchemaInstruction>();
	}

	@Override
	public void createSchema() {

	}

	@Override
	public SchemaInstruction[] getSchema() {
		int size = schemaInstructions.size();
		SchemaInstruction instructArray[] = new SchemaInstruction[size];
		for(int i = 0; i < size; i++){
			instructArray[i]=schemaInstructions.get(i);
		}
		return instructArray;
	}

	@Override
	public String getName() {
		return schemaName;
	}

	@Override
	public String getTag() {
		return tag;
	}

	@Override
	public void clear() {
		schemaInstructions.clear();
	}

	@Override
	public String[] getKeys() {
		return null;
	}

	@Override
	public boolean isList() {
		return false;
	}

	@Override
	public int[] getTypes() {
		return null;
	}

	

}
