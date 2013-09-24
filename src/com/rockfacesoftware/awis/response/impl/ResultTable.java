/*
 * @author rockfacesoft
 */
package com.rockfacesoftware.awis.response.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/**
 * Holder for XML result from query
 * in a tabular format. The Structure
 * is based on the current AlexaSchema 
 *
 */
public class ResultTable {
	
	List<ResultRow>rows = new ArrayList<ResultRow>();
	String[] keys;
	int[] types;
	String name;
	int position;
	int rowNumber = 0;
	Set<String> unique = new HashSet<String>();
	
	public ResultTable(String name, String[] keys, int[] types){
		this.name = name;
		this.keys = keys;
		this.types = types;
	}
	/**
	 * @return  a new row with appropriate CSVFields
	 */
	public ResultRow newRow(){
		return new ResultRow(keys,types);
	}
	
	public void addRow(ResultRow row) {

		if (!hasData()) {
			rows = new ArrayList<ResultRow>();
		}
		// check if row does not already exist in data
		unique.add(row.getUnique());
		
		if (rowNumber < unique.size()) {
			// add row if doesn't already exist
			rows.add(row);
			rowNumber = unique.size();
		}
	}

	public void addAll(List<ResultRow> rows) {
		if (!hasData()) {
			this.rows = new ArrayList<ResultRow>();
		}
		for(ResultRow row:rows){
			//String uniqueStr = row.getUnique();
			//System.out.println(unique.size()+" unique: "+uniqueStr);
			unique.add(row.getUnique());
			
			if (rowNumber < unique.size()) {
				this.rows.add(row);
				rowNumber = unique.size();
			}
		}
	}

	public boolean hasData() {
		return rows != null;
	}

	public List<ResultRow> getRows() {
		return rows;
	}

	public String[] getKeys() {
		return keys;
	}

	public void clear() {
		if (hasData()) {
			for (ResultRow row : rows) {
				row.clear();
			}
			unique.clear();
			rows.clear();
		}
	}

	public String getName() {
		return name;
	}
}
