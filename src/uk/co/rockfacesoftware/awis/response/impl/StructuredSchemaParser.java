/*
 * @author rockdfacesoft
 */
package uk.co.rockfacesoftware.awis.response.impl;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import uk.co.rockfacesoftware.awis.response.structure.Parser;
import uk.co.rockfacesoftware.awis.schema.structure.AlexaSchema;


/**
 * 
 * Parses the resulting XML into tabular format in preparation to output to CSV
 */
public class StructuredSchemaParser implements Parser<StructuredResponse> {
	AlexaXMLReader2 reader = null;
	private static int TIMEOUT_VALUE = 30000;
	private AlexaSchema schema;
	private URL url;
	private Document doc = null;
	private StructuredResponse response;

	public StructuredSchemaParser() {
	}

	public void setSchema(AlexaSchema schema) {
		this.schema = schema;
	}

	/**
	 * @param query
	 *            url
	 */
	@Override
	public void setUrl(URL url) {
		this.url = url;
	}

	/**
	 * parse the resulting document obtained from the query into a Structured
	 * Response in accordance with the AlexaSchema that has been set
	 */
	@Override
	public void parse() {

		if (response == null) {
			response = new StructuredResponse();
		}

		if (doc == null) {
			buildDocument();
		}

		reader = new AlexaXMLReader2(doc, schema);
		ResultTable table = reader.getResult();
		response.addData(table);

	}

	/**
	 * @return list of ResponseTables in the form of StructuredResponse
	 */
	@Override
	public StructuredResponse getResponse() {
		System.out.println(response.getData().size()+" responses");
		return response;
	}

	/**
	 * send request and build the resulting XML document
	 */
	@Override
	public void buildDocument() {
		HttpURLConnection con = null;
		try {
			con = (HttpURLConnection) url.openConnection();
			con.setConnectTimeout(TIMEOUT_VALUE);
			con.setReadTimeout(TIMEOUT_VALUE);
		} catch (IOException e) {
			e.printStackTrace();
		}

		int responseCode = 0;
		try {
			responseCode = con.getResponseCode();

			if (responseCode < 400) {
				URLConnection conn;
				conn = url.openConnection();
				InputStream in = conn.getInputStream();
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory
						.newInstance();
				DocumentBuilder dBuilder;
				dBuilder = dbFactory.newDocumentBuilder();

				doc = dBuilder.parse(in);
				doc.getDocumentElement().normalize();
			} else {
				System.out
						.println("Error http connection failed, responsecode = "
								+ responseCode);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
	}
	
	public void clear(){
		response.clear();
		doc = null;
	}
}
