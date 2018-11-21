package pages;

import org.openqa.selenium.WebDriver;
import data.UrlData;
import qaFramework.pageObject.PageObject;
import locators.Login.locators;

public class Logout extends PageObject{
	UrlData url = new UrlData();

	public Logout(WebDriver driver) throws Exception {
		super(driver);
	}

	

}
