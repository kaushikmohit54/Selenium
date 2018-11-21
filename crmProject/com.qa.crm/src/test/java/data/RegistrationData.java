package data;

import qaFramework.support.ReadDataFromXMLFile;

public class RegistrationData {
	
	public RegistrationData() throws Exception{
		
	}

	ReadDataFromXMLFile readAndWriteXml =  new ReadDataFromXMLFile();
	static String fileName = "TestDataXml",	
	              time_sec = ""+System.currentTimeMillis();
	
	public static String policeReport                   = "PoliceReport";
	public static String generalReport                  = "GeneralReport";
	
	public String passport_number                       = "PASS"+time_sec;
	
    public static String NAME                           = "AutoWorker",
                         DATE_OF_BIRTH                  = "01/08/1989",
			             CONTACT_NUMBER                 = "9535308944",
			             BANGLADESH_COUNTRY             = "BANGLADESH",
			             ISSUE_DATE                     = "11/06/2013",
			             EXPAIRY_DATE                   = "05/09/2025",
			             INDUSTRY_CONSTRUCTION			= "Construction"; 

	
}
