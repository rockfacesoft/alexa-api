/*
 * @author rockfacesoft
 */
package uk.co.rockfacesoftware.awis.schema.impl;

import uk.co.rockfacesoftware.awis.schema.structure.AlexaSchemaInstruction;
import uk.co.rockfacesoftware.awis.schema.structure.BasicAlexaSchema;
import uk.co.rockfacesoftware.awis.schema.structure.SchemaInstruction;

/**
 * 
 * Holds a list of instructions for the parser identifying the appropriate
 * elements of the XML document
 */
public class UrlInfoContact extends BasicAlexaSchema {

	public UrlInfoContact() {
		super();
		schemaName = "UrlInfoContact";
		tag = "aws:ContactInfo";
		createSchema();
	}

	@Override
	public void createSchema() {
		schemaInstructions.add(new AlexaSchemaInstruction("DataUrl",
				FEATURE_TEXT, TYPE_STRING));
		schemaInstructions.add(new AlexaSchemaInstruction("DataUrl",
				FEATURE_ATTRIBUTE_VALUE, new String[] { "type" }, TYPE_STRING));
		schemaInstructions.add(new AlexaSchemaInstruction("PhoneNumbers",
				FEATURE_TEXT, TYPE_STRING));
		schemaInstructions.add(new AlexaSchemaInstruction("OwnerName",
				FEATURE_TEXT, TYPE_STRING));
		schemaInstructions.add(new AlexaSchemaInstruction("Email",
				FEATURE_TEXT, TYPE_STRING));

		SchemaInstruction address = new AlexaSchemaInstruction(
				"PhysicalAddress", FEATURE_CHILD);
		address.add(new AlexaSchemaInstruction("Streets", FEATURE_TEXT,
				TYPE_STRING));
		address.add(new AlexaSchemaInstruction("City", FEATURE_TEXT,
				TYPE_STRING));
		address.add(new AlexaSchemaInstruction("State", FEATURE_TEXT,
				TYPE_STRING));
		address.add(new AlexaSchemaInstruction("PostalCode", FEATURE_TEXT,
				TYPE_STRING));
		address.add(new AlexaSchemaInstruction("Country", FEATURE_TEXT,
				TYPE_STRING));
		schemaInstructions.add(address);
		schemaInstructions.add(new AlexaSchemaInstruction("CompanyStockTicker",
				FEATURE_TEXT, TYPE_STRING, true));
	}

	@Override
	public String[] getKeys() {
		return new String[] { "DataUrl", "DataUrl_type", "PhoneNumbers",
				"OwnerName", "Email", "StreetAddress", "City", "State",
				"PostalCode", "Country", "CompanyStockTicker" };
	}
	
	@Override
	public int[] getTypes() {
		return new int[]{TYPE_STRING, TYPE_STRING, TYPE_STRING, TYPE_STRING, TYPE_STRING, TYPE_STRING, TYPE_STRING, TYPE_STRING, TYPE_STRING, TYPE_STRING, TYPE_STRING};
	}

}
