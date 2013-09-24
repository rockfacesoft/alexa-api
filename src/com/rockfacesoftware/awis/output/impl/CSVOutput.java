/*
 * @author rockfacesoft
 */
package com.rockfacesoftware.awis.output.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;
import java.util.Scanner;

import com.rockfacesoftware.awis.output.structure.Output;
import com.rockfacesoftware.awis.response.impl.ResultRow;
import com.rockfacesoftware.awis.response.impl.ResultTable;
import com.rockfacesoftware.awis.response.impl.StructuredResponse;
import com.rockfacesoftware.awis.response.structure.CSVField;
import com.rockfacesoftware.awis.schema.structure.AlexaSchema;

/**
 * Saves the StructuredResponse to CSV
 */
public class CSVOutput implements Output<StructuredResponse> {
	String schemaName;
	String fileNamePrefix;
	final static int MAX_LINES = 50000;
	int fileNumber = 0;
	CSVWriter writer = null;
	AlexaSchema schema = null;
	long timeMillis;

	public CSVOutput(String fileNamePrefix) {
		this.fileNamePrefix = fileNamePrefix;
		timeMillis = Calendar.getInstance().getTimeInMillis();
		
	}

	public void setSchema(AlexaSchema schema) {
		this.schema = schema;
	}

	/**
	 * Check file exists. Creates a new file with appropriate header if
	 * MAX_LINES exceeded.
	 * 
	 * @Param tableName the name of the current table of the structured response
	 *        being handled.
	 * @Param headerLine the CSV formatted header line for the table
	 */
	private void setWriter(String tablename, String headerLine) {
		String path = buildFilePath(tablename);
		File file = new File(path);
		writer = new CSVWriter(path);
		if (file.exists()) {
			// get number of lines
			int lineCount = 0;
			FileReader reader = null;
			BufferedReader bufferedReader = null;
			try {
				reader = new FileReader(file);
				bufferedReader = new BufferedReader(reader);
				Scanner scanner = new Scanner(bufferedReader);
				while (scanner.hasNextLine()) {
					scanner.nextLine();
					lineCount++;
				}
				if (lineCount > MAX_LINES) {
					fileNumber++;
					path = buildFilePath(tablename);
					file = new File(path);
					writer = new CSVWriter(path);
					writer.writeLine(headerLine, false);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			writer.writeLine(headerLine, false);
		}
	}

	/**
	 * saves the result to CSV
	 * 
	 * @Param result the list of tables obtained from the request
	 */
	@Override
	public void save(StructuredResponse result) {
		for (ResultTable table : result.getData()) {
			// create new writer
			String keys[] = table.getKeys();
			String headerLine = createHeaderLine(keys);
			System.out.println(table.getName()+" writing table");
			setWriter(table.getName(), headerLine);

			// build lines and save to the csv
			int numKeys = keys.length;
			int commas = numKeys - 1;
			
			if (table.getRows() == null) {
				continue;
			}
			System.out.println(table.getRows().size()+" rows in table");
			for (ResultRow row : table.getRows()) {
				String line = "";
				
				for(int i = 0; i < numKeys; i++){
					CSVField field = row.get(keys[i]);
					if(field!=null){
						line += field.getValue();
						
						if (i < commas) {
							line += ",";
						}
					}else{
					System.out.println("Key '"+keys[i]+" not found in row \n"+row.toString());
					}
				}
				
				writer.writeLine(line, true);
				
			}
			table.clear();
		}
		result.clear();
	}

	@Override
	public void setSchemaName(String schemaName) {
		this.schemaName = schemaName;
	}

	private String buildFilePath(String key) {
		return fileNamePrefix + "_" + schemaName + "_" + key + "_" + fileNumber
				+ "_" +timeMillis+ ".csv";
	}

	private String createHeaderLine(String[] keys) {
		String headerLine = "";
		int len = keys.length;
		for (int i = 0; i < len; i++) {
			headerLine += "\"" + keys[i] + "\"" + (i + 1 < len ? "," : "");
		}
		return headerLine;
	}

}
