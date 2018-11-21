package pages;

import org.openqa.selenium.WebDriver;
import qaFramework.pageObject.PageObject;

import locators.Home.locators;

import locators.Registration;

public class Home extends PageObject {

	public Home(WebDriver driver) throws Exception {
		super(driver);
	}

	

	public Home clickOnZOHOAccounts() throws Exception{
		waitForElementIsVisible(locators.ZOHO_ACCOUNTS, "xpath");
		clickByJS(locators.ZOHO_ACCOUNTS, "xpath");
		return this;
	}
	public Home clickOnCRM() throws Exception{
		waitForElementIsVisible(locators.CRM, "xpath");
		clickByJS(locators.CRM, "xpath");
		return this;
	}

	public Home CRM_HOMEPAGE() throws Exception{
		clickOnZOHOAccounts();
		CRM_HOMEPAGE();
		return this;
	}

	
	
	
}
