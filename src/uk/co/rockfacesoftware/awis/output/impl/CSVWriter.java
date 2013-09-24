/*
 * @author rockfacesoft
 */
package uk.co.rockfacesoftware.awis.output.impl;

import java.io.FileWriter;
import java.io.IOException;

/**
 * 
 * writes the CSV formatted line to file
 */
public class CSVWriter {

	String filePath;

	public CSVWriter(String filePath) {
		this.filePath = filePath;
	}

	public void writeLine(String line, boolean append) {
		try {
			FileWriter writer = new FileWriter(filePath, append);
			writer.append(new String(line.getBytes(), "UTF-8"));
			writer.append("\r\n");
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
