/*
 * @author rockfacesoft
 */
package uk.co.rockfacesoftware.awis.schema.structure;


public interface AlexaSchema {
	public final static int TYPE_STRING = 0;
	public final static int TYPE_INT = 1;
	public final static int TYPE_FLOAT = 2;
	public final static int TYPE_LONG = 3;
	public final static int TYPE_BOOLEAN = 4;
	public final static int TYPE_LIST = 5;
	public static final int FEATURE_TEXT = 0;
	public static final int FEATURE_ATTRIBUTE_VALUE = 1;
	public static final int FEATURE_CHILD = 2;
	//public static final int FEATURE_CHILDREN = 3;
	public static final int FEATURE_LIST = 4;

	public void createSchema();

	public SchemaInstruction[] getSchema();

	public String getName();

	public String getTag();

	public void clear();

	public String[] getKeys();

	public boolean isList();
	
	public int[]getTypes();

}
