package locators;

public class Registration {
	
	public static class locators{
	
	public static String REGISTER_NEW_WORKER       					    = "//button[contains(text(),' Register New Worker')]",
                         REGISTRATION                 					= "//li/a/label[@class='glyphicon glyphicon-pencil']/parent::a[contains(text(),'Registration')]",
                         REGISTRATION_PAGE            					= "//li/i/label[contains(text(),'Mandatory field')]/parent::i/parent::li/parent::ol/li/i[(text()='Passport validity must be at least 19 months from Worker Registration at Bio-Medical')]",
                         NAME                         					= "name",
                         GENDER_MALE                  					= "//input[@value='M']",
                         DATE_OF_BIRTH                					= "#dobPckr",
                         CONTACT_NUMBER              				 	= "#phoneNo",
                         PASSPORT_NUMBER              					= "#passportNo",
                         RE_ENTER_PASSPORT            					= "#passportNoRep",
                         DOCUMENT_COUNTRY             					= "//div/div/label[text()='Document Country Issue']/parent::div/select",
                         PASSPORT_ISSUE_DATE          					= "#dateIssuePckr",
                         PASSPORT_EXPIRY_DATE         					= "#dateExpiryPckr",
                         OTHER_MEDICAL_EXAMINATION    					= "#radioTH",
                         INDUSTRY                     					= "//div/label[text()='Industry']/parent::div/div[@id='indtry']/select",
                         POLICE_REPORT                					= "//div/label[text()='Police Report']/parent::div//input[@id='uploadPDFVoid']/parent::div/span/button",
                         GENERAL_REPORT                                 = "//div/label[text()='General']/parent::div//input[@id='uploadPDFVoid']/parent::div/span/button",
                         CREATE                       					= "//input[@value='Create']",
                         WORKER_PROFILE_SUCCESS                         = "//div[text()='Worker Profile is successfully created.']",
                         SUCCESS_OK                                     = "//div[@id='message_modal']//a[text()='OK']",
                         ACKNOWLEDGE_CHECKBOX                           = "#wrkrRegDeclaration1";
                        
	}

}
