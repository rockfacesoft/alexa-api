package uk.co.rockfacesoftware.example;

import uk.co.rockfacesoftware.awis.AlexaRequestController;

public class Example {

	public static void main(String[] args){
		String filePrefix = "c:\\yourfolder\\out";
		String accessKeyId = "your_access_key";
		String secretAccessKey = "your_secret_access";
		AlexaRequestController control = new AlexaRequestController(AlexaRequestController.CSV, filePrefix,accessKeyId, secretAccessKey);
		
		control.UrlInfo(new String[]{"amazon.com","ebay.com"}, new String[]{"UsageStats"});
		
	}
}
