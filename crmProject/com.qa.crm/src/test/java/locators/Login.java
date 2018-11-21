package locators;

import org.openqa.selenium.By;

public class Login {
	
	public static class locators{
		
	public static String URL                      = "https://accounts.zoho.com/signin?servicename=AaaServer&serviceurl=%2Fu%2Fh%23home",
	                     USER_NAME                = "lid",
            			 PASSWORD                 = "pwd",
            			 LOGIN_BUTTON             = "//text()[.='Sign In']/ancestor::div[1]",
						 LOGIN_HOME					="//img[@id='ztb-profile-image']";
            			
	}    			
}

