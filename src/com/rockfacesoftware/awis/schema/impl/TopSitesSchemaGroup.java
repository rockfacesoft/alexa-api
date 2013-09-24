/*
 * @author rockfacesoft
 */
package com.rockfacesoftware.awis.schema.impl;

import java.util.ArrayList;
import java.util.List;

import com.rockfacesoftware.awis.schema.structure.AlexaSchema;
import com.rockfacesoftware.awis.schema.structure.AlexaSchemaGroup;

/**
 * 
 * Handles multiple schemas generated from chosen ResponseGroups
 */
public class TopSitesSchemaGroup implements AlexaSchemaGroup {
	private List<AlexaSchema> schemas = null;

	private int ResId = -1;

	final static int CITY = 1;
	final static int COUNTRY = 2;
	final static int LIST_CITIES = 3;
	final static int LIST_COUNTRIES = 4;

	public TopSitesSchemaGroup(String responseGroup) {
		schemas = new ArrayList<AlexaSchema>();
		getResponseId(responseGroup);
		if (ResId != -1) {
			createSchemas();
		}
	}

	@Override
	public void createSchemas() {
		switch (ResId) {
		case COUNTRY:
			schemas.add(new TopSitesCountry());
			break;
		case CITY:
			schemas.add(new TopSitesCity());
			break;
		case LIST_CITIES:
			schemas.add(new TopSitesListCities());
			break;
		case LIST_COUNTRIES:
			schemas.add(new TopSitesListCountries());
			break;
		}
	}

	public void getResponseId(String responseGroup) {
		
		if (responseGroup.equals("Country")) {
			ResId=COUNTRY;
		}
		if (responseGroup.equals("City")) {
			ResId=CITY;
		}
		if (responseGroup.equals("ListCities")) {
			ResId=LIST_CITIES;
		}
		if (responseGroup.equals("ListCountries")) {
			ResId=LIST_COUNTRIES;
		}
	}

	@Override
	public List<AlexaSchema> getSchemas() {
		return schemas;
	}

	@Override
	public void clear() {
		for (AlexaSchema schema : schemas) {
			schema.clear();
		}
		schemas.clear();
	}

}
