package pages;

import java.util.ArrayList;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import qaFramework.pageObject.PageObject;

public class Shared extends PageObject{

	public Shared(WebDriver webdriver) throws Exception {
		super(webdriver);
	}
	public Shared openNewTab() throws Exception{
		((JavascriptExecutor)driver).executeScript("window.open()");
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));	
		return this;
	}

	public Shared switchToTab(int tabCount) throws Exception{
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(tabCount));	
		return this;
	}

	public Shared refershBrowser() throws Exception{
		driver.navigate().refresh();
		return this;
	}

	public Shared waitForPageLoad(WebDriver driver) {
		ExpectedCondition<Boolean> pageLoadCondition = new
				ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
			}
		};
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(pageLoadCondition);
		return this;
	}

}
