/*
 * @author rockfacesoft
 */
package com.rockfacesoftware.awis.output.impl;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

import com.rockfacesoftware.awis.output.structure.Output;
import com.rockfacesoftware.awis.response.impl.BasicResponse;

/**
 * Saves the BasicResponse to CSV
 */
public class XMLOutput implements Output<BasicResponse> {

	String fileNamePrefix;
	long timeMillis = 0;
	int fileNumber = 0;
	String schemaName;

	public XMLOutput(String fileNamePrefix) {
		this.fileNamePrefix = fileNamePrefix;
		timeMillis = Calendar.getInstance().getTimeInMillis();
	}

	@Override
	public void setSchemaName(String schemaName) {
		this.schemaName = schemaName;
	}

	/**
	 * saves the result to CSV
	 * 
	 * @Param result the list of raw XML String data obtained from the request
	 */
	@Override
	public void save(BasicResponse result) {
		for (String response : result.getData()) {

			try {
				FileWriter writer = new FileWriter(buildFilePath(), false);
				writer.append(new String(response.getBytes(), "UTF-8"));
				writer.flush();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			fileNumber++;
		}
		result.clear();
	}

	private String buildFilePath() {
		return fileNamePrefix + "_" + schemaName + "_" + fileNumber + "_"
				+ timeMillis + ".xml";
	}

}
