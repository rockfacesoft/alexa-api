/*
 * @author rockfacesoft
 */
package com.rockfacesoftware.awis.response.impl;

import java.util.ArrayList;
import java.util.List;

import com.rockfacesoftware.awis.response.structure.Response;

/**
 * 
 * Holds a list of ResponseTables (multiple ResponseGroups generate 1 or more
 * tables each)
 */
public class StructuredResponse implements Response<ResultTable> {
	private List<ResultTable> responses;

	@Override
	public List<ResultTable> getData() {
		return responses;
	}

	@Override
	public boolean hasData() {
		return responses != null;
	}

	@Override
	public void clear() {
		if (hasData()) {
			for (ResultTable table : responses) {
				table.clear();
			}
			responses.clear();
		}
	}

	@Override
	public void addData(ResultTable data) {
		if (!hasData()) {
			responses = new ArrayList<ResultTable>();
		}
		responses.add(data);
	}

	@Override
	public void addData(List<ResultTable> data) {
		if (!hasData()) {
			responses = new ArrayList<ResultTable>();
		}
		responses.addAll(data);
	}

}
