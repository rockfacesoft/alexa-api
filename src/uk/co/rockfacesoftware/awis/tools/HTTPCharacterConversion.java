/*
 * @author rockfacesoft
 */
package uk.co.rockfacesoftware.awis.tools;

/**
 * 
 * Simple character conversion into HTTP safe version NB: conversion of
 * diacritic vowels cause the Alexa query to fail and so are currently ignored
 */
public class HTTPCharacterConversion {

	// private static String [] conversion =
	// {"%25","%7B","%7C","%7D","%7E","%7F","%80","%7B","%7C","%7D","%7E","%7F","%80","%81","%82","%83","%84","%85","%86","%87","%88","%89","%8A","%8B","%8C","%8D","%8E","%8F","%90","%91","%92","%93","%94","%95","%96","%97","%98","%99","%9A","%9B","%9C","%9D","%9E","%9F","%A0","%A1","%A2","%A3","%A4","%A5","%A6","%A7","%A8","%A9","%AA","%AB","%AC","%AD","%AE","%AF","%B0","%B1","%B2","%B3","%B4","%B5","%B6","%B7","%B8","%B9","%BA","%BB","%BC","%BD","%BE","%BF","%C0","%C1","%C2","%C3","%C4","%C5","%C6","%C7","%C8","%C9","%CA","%CB","%CC","%CD","%CE","%CF","%D0","%D1","%D2","%D3","%D4","%D5","%D6","%D7","%D8","%D9","%DA","%DB","%DC","%DD","%DE","%DF","%E0","%E1","%E2","%E3","%E4","%E5","%E6","%E7","%E8","%E9","%EA","%EB","%EC","%ED","%EE","%EF","%F0","%F1","%F2","%F3","%F4","%F5","%F6","%F7","%F8","%F9","%FA","%FB","%FC","%FD","%FE","%FF","%5C","%5D","%5E","%5F","%60","%21","%22","%23","%24","%26","%27","%28","%29","%2A","%2B","%2C","%2D","%2E","%2F","%3A","%3B","%3C","%3D","%3E","%3F","%40"};
	// private static String [] symbols =
	// {"%","{","|","}","~","","`","{","|","}","~"," ","`","","‚","ƒ","„","…","†","‡","ˆ","‰","Š","‹","Œ","","Ž","","","‘","’","“","”","•","–","—","˜","™","š","›","œ","","ž","Ÿ"," ","¡","¢","£","¤","¥","¦","§","¨","©","ª","«","¬","­","®","¯","°","±","²","³","´","µ","¶","·","¸","¹","º","»","¼","½","¾","¿","À","Á","Â","Ã","Ä","Å","Æ","Ç","È","É","Ê","Ë","Ì","Í","Î","Ï","Ð","Ñ","Ò","Ó","Ô","Õ","Ö","×","Ø","Ù","Ú","Û","Ü","Ý","Þ","ß","à","á","â","ã","ä","å","æ","ç","è","é","ê","ë","ì","í","î","ï","ð","ñ","ò","ó","ô","õ","ö","÷","ø","ù","ú","û","ü","ý","þ","ÿ","\\","]","^","_","`","!","\"","#","$","&","'","(",")","*","+",",","-",".","/",":",";","<","=",">","?","@"};
	private static String[] symbols = { "%", "{", "|", "}", "`", "{", "|", "}",
			"`", "", "‚", "ƒ", "„", "…", "†", "‡", "ˆ", "‰", "‹", "Œ", "",
			"", "", "‘", "’", "“", "”", "•", "–", "—", "˜", "™", "›", "œ",
			"", "Ÿ", " ", "¡", "¢", "£", "¤", "¥", "¦", "§", "¨", "©", "ª",
			"«", "¬", "­", "®", "¯", "°", "±", "²", "³", "´", "µ", "¶", "·",
			"¸", "¹", "º", "»", "¼", "½", "¾", "¿", "Æ", "×", "Þ", "ß", "æ",
			"÷", "þ", "\\", "]", "^", "`", "!", "\"", "#", "$", "&", "'", "(",
			")", "*", "+", ",", "/", ":", ";", "<", "=", ">", "?", "@", " " };
	private static String[] conversion = { "%25", "%7B", "%7C", "%7D", "%80",
			"%7B", "%7C", "%7D", "%80", "%81", "%82", "%83", "%84", "%85",
			"%86", "%87", "%88", "%89", "%8B", "%8C", "%8D", "%8F", "%90",
			"%91", "%92", "%93", "%94", "%95", "%96", "%97", "%98", "%99",
			"%9B", "%9C", "%9D", "%9F", "%A0", "%A1", "%A2", "%A3", "%A4",
			"%A5", "%A6", "%A7", "%A8", "%A9", "%AA", "%AB", "%AC", "%AD",
			"%AE", "%AF", "%B0", "%B1", "%B2", "%B3", "%B4", "%B5", "%B6",
			"%B7", "%B8", "%B9", "%BA", "%BB", "%BC", "%BD", "%BE", "%BF",
			"%C6", "%D7", "%DE", "%DF", "%E6", "%F7", "%FE", "%5C", "%5D",
			"%5E", "%60", "%21", "%22", "%23", "%24", "%26", "%27", "%28",
			"%29", "%2A", "%2B", "%2C", "%2F", "%3A", "%3B", "%3C", "%3D",
			"%3E", "%3F", "%40", "%20" };

	public static String convert(String string) {
		int len = symbols.length;
		for (int i = 0; i < len; i++) {
			string = string.replace(symbols[i], conversion[i]);
		}
		return string;
	}
}
