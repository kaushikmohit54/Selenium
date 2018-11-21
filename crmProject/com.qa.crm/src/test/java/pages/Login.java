package pages;

import org.openqa.selenium.WebDriver;
import data.UrlData;
import qaFramework.pageObject.PageObject;
import locators.Login.locators;

public class Login extends PageObject{
	UrlData url = new UrlData();

	public Login(WebDriver driver) throws Exception {
		super(driver);
	}

	public Login login(String userName, String password) throws Exception{
		enterUserName(userName);
		enterPassword(password);
		clickOnLogin();
		return this;
	}

	public Login enterUserName(String userName) throws Exception{
		sendKeys(locators.USER_NAME, "id", userName);
		return this;
	} 

	public Login enterPassword(String password) throws Exception{
		sendKeys(locators.PASSWORD, "id", password);
		return this;
	}

	public Login clickOnLogin() throws Exception{
		click(locators.LOGIN_BUTTON, "xpath");
		waitForElementAppearance(locators.LOGIN_HOME, "xpath");
		return this;
	}

}
