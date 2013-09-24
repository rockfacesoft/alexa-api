/*
 * @author rockfacesoft
 */
package uk.co.rockfacesoftware.awis.response.impl;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import uk.co.rockfacesoftware.awis.output.impl.CSVWriter;
import uk.co.rockfacesoftware.awis.schema.structure.AlexaSchema;
import uk.co.rockfacesoftware.awis.schema.structure.SchemaInstruction;



/**
 * 
 * Converts XML into tabular form
 */
public class AlexaXMLReader2 {
	ResultTable table;
	CSVWriter writer = new CSVWriter("c:\\Vin\\out.txt");

	public AlexaXMLReader2(Document doc, AlexaSchema schema) {
		SchemaInstruction[] instructions = schema.getSchema();
		String tag = schema.getTag();
		List<Element> elements = getElements(doc.getElementsByTagName(tag));
		table = new ResultTable(schema.getName(), schema.getKeys(), schema.getTypes());
		writer.writeLine(table.getName()+" "+elements.size()+" elements, numRows "+table.getRows().size(), true);
		System.out.println(table.getName()+" "+elements.size()+" elements, numRows "+table.getRows().size());
		int a = elements.size();
		
		for (int i = 0; i < a; i++) {
			Element element = elements.get(i);
			parse(element, instructions);
		}
		System.out.println(table.rowNumber+" rows in table");
		writer.writeLine(table.rowNumber+" rows in table", true);
		for(ResultRow row : table.getRows()){
			writer.writeLine(row.toString(), true);
		}
		System.out.println(table.rowNumber+" rows in table");
	}
	
	public void parse(Element element, SchemaInstruction[] instructions) {
		int numInstruct = instructions.length;
		ResultRow row = table.newRow();
		writer.writeLine(numInstruct+" instructions", true);
		for(int j = 0; j < numInstruct; j++){
			SchemaInstruction instruction = instructions[j];
			
			int feature = instruction.getFeature();
			String tag = instruction.getTag();
			String[] attributeNames = instruction.getAttributenames();
			String key = instruction.getKey();
			writer.writeLine("tag: "+tag+", key :"+key, true);
			switch (feature) {
			case AlexaSchema.FEATURE_TEXT:
				String text = getText(element, tag);
				if (text != null) {
					writer.writeLine("key: "+key+" text :"+text, true);
					row.set(key, text);
					writer.writeLine("row: "+row.toString(), true);
				}
				break;
			case AlexaSchema.FEATURE_ATTRIBUTE_VALUE:
				if (attributeNames != null) {
					int len = attributeNames.length;
					for (int i = 0; i < len; i++) {
						text = getAttributeValue(element, tag,
								attributeNames[i]);
						writer.writeLine(i+") key: "+key+" text :"+text, true);
						row.set(key + "_" + attributeNames[i], text);
						writer.writeLine(i+") row: "+row.toString(), true);
					}
					
				}
				break;
			case AlexaSchema.FEATURE_CHILD:
				 List<Element> elements = getElements(element, tag);
				 int b = elements.size();
				 writer.writeLine("CHILD b = "+b, true);
				 
				 if(b==1){
					 
					Element elem = elements.get(0);
						if (attributeNames != null) {
							int len = attributeNames.length;
							for (int i = 0; i < len; i++) {
								text = getAttributeValue(elem, attributeNames[i]);
								row.set(key + "_"+ attributeNames[i], text);
							}
						}
						
						 row = parse(elem, instruction.getInstructions(), row);
						
					}
					elements.clear();
				
				break;
			case AlexaSchema.FEATURE_LIST:
				elements = getElements(element, tag);
				writer.writeLine(" FEATURE_LIST found :"+elements.size(), true);
				int c = elements.size();
				 for(int m  = 0; m < c; m++){
					Element elem = elements.get(m);
					if (attributeNames != null) {
						int len = attributeNames.length;
						for (int i = 0; i < len; i++) {
							text = getAttributeValue(elem, attributeNames[i]);
							row.set(key + "_"+ attributeNames[i], text);
						}
					}
					parse(elem, instruction.getInstructions(), row.clone());
					
				}
				elements.clear();
			}
			if(instruction.isLast()){
				table.addRow(row);
			}
		}
	}

	
	private ResultRow parse(Element element, SchemaInstruction[]instructions, ResultRow row) {
		
		int rlen = instructions.length;
		for(int j = 0; j < rlen; j++){
			
			SchemaInstruction instruction = instructions[j];
			int feature = instruction.getFeature();
			String tag = instruction.getTag();
			String[] attributeNames = instruction.getAttributenames();
			String key = instruction.getKey();
			writer.writeLine("looking for key: "+key+", tag: "+tag, true);
			switch (feature) {
			case AlexaSchema.FEATURE_TEXT:
				String text = getText(element, tag);
				if (text != null) {
					writer.writeLine("key: "+key+" text :"+text, true);
					row.set(key, text);
					writer.writeLine("row: "+row.toString(), true);
				}
				break;
			case AlexaSchema.FEATURE_ATTRIBUTE_VALUE:
				if (attributeNames != null) {
					int len = attributeNames.length;
					for (int i = 0; i < len; i++) {
						text = getAttributeValue(element, tag,
								attributeNames[i]);
						writer.writeLine(i+") key: "+key+" text :"+text, true);
						row.set(key + "_" + attributeNames[i], text);
						writer.writeLine(i+") row: "+row.toString(), true);
					}
				}
				break;
			case AlexaSchema.FEATURE_CHILD:
				 List<Element> elements = getElements(element, tag);
				 int d = elements.size();
					for(int m  = 0; m < d; m++){
					
					Element elem=elements.get(m);
					
						if (attributeNames != null) {
							int len = attributeNames.length;
							for (int i = 0; i < len; i++) {
								text = getAttributeValue(elem, attributeNames[i]);
								row.set(key + "_"+ attributeNames[i], text);
							}
						}
						row = parse(elem, instruction.getInstructions(), row.clone());
					}
					elements.clear();
				
				break;
			case AlexaSchema.FEATURE_LIST:
				elements = getElements(element, tag);
				
				int e = elements.size();
				for(int m  = 0; m < e; m++){
				
				Element elem=elements.get(m);
					if (attributeNames != null) {
						int len = attributeNames.length;
						for (int i = 0; i < len; i++) {
							text = getAttributeValue(elem, attributeNames[i]);
							row.set(key + "_"+ attributeNames[i], text);
						}
					}
					parse(elem, instruction.getInstructions(), row.clone());
				}
				elements.clear();
			}
			if(instruction.isLast()){
				table.addRow(row);
			}
		}
		return row;
	}
	
	
	
	private List<Element> getElements(NodeList nodes) {
		List<Element> elements = new ArrayList<Element>();
		int len = nodes.getLength();
		for (int i = 0; i < len; i++) {
			Node node = nodes.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				elements.add((Element) node);
			}
		}

		return elements;
	}

	private List<Element> getElements(Element element, String tag) {
		NodeList nodes = element.getChildNodes();
		List<Element> elements = new ArrayList<Element>();
		int len = nodes.getLength();
		for (int i = 0; i < len; i++) {
			Node node = nodes.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				if (node.getNodeName().equals(tag)) {
					elements.add((Element) node);
				}
			}
		}
		return elements;
	}

	private String getText(Element element, String tag) {
		if (element != null) {
			List<Element> elements = getElements(element, tag);
			if (elements.size() == 1) {
				return removeHtml(elements.get(0).getTextContent());
			}

		}
		return "";
	}

	private String getAttributeValue(Element element, String attrName) {
		if (element != null) {
			return element.getAttribute(attrName);
		}
		return "";
	}

	private String getAttributeValue(Element element, String tag,
			String attrName) {
		if (element != null) {
			List<Element> elements = getElements(element, tag);
			if (elements.size() == 1) {
				return elements.get(0).getAttribute(attrName);
			}
		}
		return "";
	}

	private String removeHtml(String string) {
		string = string.replace("<li>", "\r\n\t");
		string = string.replace("  ", " ");
		string = string.replace("<a href=\"", "");
		string = string.replace("</a>", "");
		string = string.replaceAll("\\<+\\/?\\w+\\>+", "");
		string = string.replace("\">", "");
		return string;
	}

	public ResultTable getResult() {
		return table;
	}
}
