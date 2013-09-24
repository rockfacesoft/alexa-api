/*
 * @author rockfacesoft
 */
package com.rockfacesoftware.awis.response.impl;

import java.util.ArrayList;
import java.util.List;

import com.rockfacesoftware.awis.response.structure.Response;

/***
 * 
 * Holds resulting list of Strings from NoParser
 */
public class BasicResponse implements Response<String> {
	private List<String> responses;

	public BasicResponse() {

	}

	@Override
	public void addData(String data) {
		if (!hasData()) {
			responses = new ArrayList<String>();
		}
		responses.add(data);
	}

	@Override
	public void addData(List<String> data) {
		if (!hasData()) {
			responses = new ArrayList<String>();
		}
		responses.addAll(data);
	}

	@Override
	public List<String> getData() {
		return responses;
	}

	@Override
	public boolean hasData() {
		return responses != null;
	}

	@Override
	public void clear() {
		if (hasData()) {
			responses.clear();
		}
	}

}
