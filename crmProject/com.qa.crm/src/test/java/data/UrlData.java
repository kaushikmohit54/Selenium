package data;

import qaFramework.support.ReadDataFromXMLFile;

public class UrlData {
	
	public UrlData() throws Exception{
	}
	
	ReadDataFromXMLFile readAndWriteXml =  new ReadDataFromXMLFile();
	static String fileName = "UrlsXml";		  	      
	
	public String RAMDV_URL2                       		 = readAndWriteXml.read("ramwURL2", fileName);
	public String RAMDV_URL3                       	     = readAndWriteXml.read("ramwURL3", fileName);
	public String webMailURL                       		 = readAndWriteXml.read("webMailURL", fileName);
				 
}
