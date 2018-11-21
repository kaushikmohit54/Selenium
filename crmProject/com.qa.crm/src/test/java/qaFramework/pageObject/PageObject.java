package qaFramework.pageObject;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import java.io.File;
import java.util.List;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import configuration.Configuration;
import qaFramework.support.HighLightElement;
import qaFramework.support.ReadPropertyFiles;

public class PageObject {
	public static WebDriver driver;
	static String pageLoadStatus = null;
	public static final int WAIT_TIME_LONGDURATION = 60;
	public static final int WAIT_TIME_SHORTDURATION = 15;
	public static final int WAIT_TIME = 40;

	ReadPropertyFiles objReadEnvironmentProperty = new ReadPropertyFiles();
	Properties envPropertyDetails = objReadEnvironmentProperty.ReadEnvironment();
	HighLightElement highLightElement = new HighLightElement();
	String getElementsHighLight = envPropertyDetails.getProperty("highLightElement");


	public PageObject(WebDriver webdriver) throws Exception{
		PageObject.driver = Configuration.driver;
	}

	public enum type {
		xpath, css, id, name, tagName, linktext,className;
	}

	public static By getWebDriverBy(String elementName, String locatorStrategy)
			throws Exception {
		By webdriverby = null;

		switch (type.valueOf(locatorStrategy)) {
		case xpath:
			webdriverby = By.xpath(elementName);
			break;
		case css:
			webdriverby = By.cssSelector(elementName);
			break;
		case id:
			webdriverby = By.id(elementName);
			break;
		case name: 
			webdriverby = By.name(elementName);
			break;
		case tagName:
			webdriverby = By.tagName(elementName);
			break;
		case linktext:
			webdriverby = By.linkText(elementName);
			break;
		case className:
			webdriverby = By.className(elementName);
		}
		return webdriverby;
	}
	
	public WebElement getElement(String elementName, String locatorStrategy)throws Exception{
		WebElement element = driver.findElement(getWebDriverBy(elementName, locatorStrategy));
	 /* if(getElementsHighLight.equalsIgnoreCase("YES")){
		highLightElement.highLightElement(driver, element);}*/
		return element;
	}
	
	public List<WebElement> getElementLists(String elementName, String locatorStrategy) throws Exception {
		  return driver.findElements(getWebDriverBy(elementName, locatorStrategy));
		 }
	/************** Selenium Functions*********************/
	
	public void sendKeys(String elementName, String locatorStrategy, String keys) throws Exception {
		
		waitForElementIsVisible(elementName, locatorStrategy);
		getElement(elementName, locatorStrategy).clear();
		getElement(elementName, locatorStrategy).sendKeys(keys);
	}
	
	public String getText(String elementName, String locatorStrategy) throws Exception {
		String text = getElement(elementName, locatorStrategy).getText();
		return text;
	}
	public String getAttributeValue(String elementName, String locatorStrategy) throws Exception {
		String text = getElement(elementName, locatorStrategy).getAttribute("value");
		return text;
	}

	/************** Browser Navigations*********************/

	public void navigateForward() throws Exception{
		driver.navigate().forward();
	}
	public void navigateBack() throws Exception{
		driver.navigate().back();
	}
	public void browserRefresh() throws Exception{
		driver.navigate().refresh();
	}

	/************** SWITCH WINDOWS and FRAMES Functions*******/

	//Switch to Child Window
	public void switchToChildWindow() throws Exception{
		for(String winHandle : driver.getWindowHandles()){
			driver.switchTo().window(winHandle);
		}
	}
	
	//Switch to previous window
		public void switchToDefaultWindow(String winHandleBefore ) throws Exception{
			driver.switchTo().window(winHandleBefore);
		}
	//Switch to Parent/Default Window or Parent/Default Frames
	public void switchToParentWindow() throws Exception{
		driver.switchTo().defaultContent();
	}

	//Switch to Frame By ID 
	public void switchToFrameById(String frameId) throws Exception{
		driver.switchTo().frame(frameId);
	}

	//Switch to Frame By Index 
	public void switchToFrameUsingIndex(int frameIndex) throws Exception{
		driver.switchTo().frame(frameIndex);
	}

	/************** Scroll Functions*********************/

	//This function scroll the page till the element is found
	public void scrollToElement(String elementName, String locatorStrategy) throws Exception{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", getElement( elementName, locatorStrategy));
	}

	//This function scroll the web page till end.
	public void scrollToEndOfPage() throws Exception{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	/*** Alerts, Confirmation and Pop-Up Functions**********/

	//Read alert text
	public String getAlertText() throws Exception{
		String myalert = driver.switchTo().alert().getText();
		return myalert;
	}

	//Accept confirmation
	public void acceptConfirmation() throws Exception{
		driver.switchTo().alert().accept();
	}

	//Dismiss confirmation 
	public void dismissConfirmation() throws Exception{
		driver.switchTo().alert().dismiss();
	}

	//Enter Text in Prompt pop-up 
	public void enterTextInPopUp(String text) throws Exception{
		driver.switchTo().alert().sendKeys(text);
	}

	/************** Click Functions  *********************/

	public void click(String elementName, String locatorStrategy) throws Exception {
		waitForElementToBeClickable(elementName, locatorStrategy);
		getElement(elementName, locatorStrategy).click();
	}
	

	//action click and right click
	public void clickByActions(String elementName, String locatorStrategy) throws Exception{
		Actions actions = new Actions(driver);
		Action action = actions.contextClick(getElement( elementName, locatorStrategy)).build();
		action.perform();
	}
	//click by JavaScript
	public void clickByJS(String elementName, String locatorStrategy) throws Exception{
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", getElement( elementName, locatorStrategy));
	}

	//click by EnterKey
	public void clickByEnterKey(String elementName, String locatorStrategy) throws Exception{
		getElement( elementName, locatorStrategy).sendKeys(Keys.ENTER);
	}

	//click by EnterKey by Actions
	public void clickEnterByActions() throws Exception {
		Actions action = new Actions(driver);
		action.keyUp(Keys.ENTER);
	}

	//double click by actions
	public void doubleClickByActions(String elementName, String locatorStrategy) throws Exception{
		Actions action = new Actions(driver);
		action.doubleClick(getElement( elementName, locatorStrategy));
		action.perform();
	}


	/*************DropDown Functions********************/

	//select the dropdown using "select by visible text"
	public void selectVisibleText(String elementName, String locatorStrategy,String VisibleText)throws Exception{
		Select selObj=new Select(getElement( elementName, locatorStrategy));
		selObj.selectByVisibleText(VisibleText);
		
	}
	//Deselect the selected text
	public  void deselectByText(String elementName, String locatorStrategy,String Value) throws Exception{
		Select listbox = new Select(getElement( elementName, locatorStrategy));
		listbox.deselectByVisibleText(Value);
	}

	//select the dropdown using "select by index"
	public  void selectIndexValue(String elementName, String locatorStrategy,int IndexValue) throws Exception{
		Select selObj=new Select(getElement( elementName, locatorStrategy));
		selObj.selectByIndex(IndexValue);
	}
	//deselect the selected index value
	public  void deselectIndexValue( String elementName, String locatorStrategy,int IndexValue) throws Exception{
		Select listbox = new Select(getElement( elementName, locatorStrategy));
		listbox.deselectByIndex(IndexValue);
	}

	//select the dropdown using "select by value"
	public void selectOptionValue(String elementName, String locatorStrategy,String Value) throws Exception{
		Select selObj=new Select(getElement( elementName, locatorStrategy));
		selObj.selectByValue(Value);
	}
	//Deselect by Value
	public void deselectOptionValue(String elementName, String locatorStrategy,String Value) throws Exception{
		Select listbox = new Select(getElement( elementName, locatorStrategy));
		listbox.deselectByValue(Value);
	}
	//Deselect all values
	public void deselectAll(String elementName, String locatorStrategy) throws Exception{
		Select listbox = new Select(getElement( elementName, locatorStrategy));
		listbox.deselectAll();
	}
	
	
	//To get dropdown values
	 public List<WebElement> getDropdownOptions(String elementName, String locatorStrategy) throws Exception{
	  Select se = new Select( getElement(elementName, locatorStrategy));
	  List<WebElement> options =  se.getOptions();
	  return options ;
	 }


	/**************Mouse Over Functions  *********************/

	//Mouseover by Actions
	public void mouseOverByActions(String elementName, String locatorStrategy) throws Exception{
		Actions actObj=new Actions(driver);
		actObj.moveToElement(getElement( elementName, locatorStrategy)).build().perform();
	}

	//MouseOver by JavaScript-
	public void mouseOverByJS(String elementName, String locatorStrategy) throws Exception{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].onmouseover()", getElement( elementName, locatorStrategy)); 
	}

	/**************Drag&Drop Functions*********************/

	public void dragAndDropByActions(WebElement fromElement, WebElement ToElement ){
		Actions builder = new Actions(driver);
		Action dragAndDrop = builder.clickAndHold(fromElement)
				.moveToElement(ToElement).release(ToElement).build();
		dragAndDrop.perform();
	}
	public void dragAndDropByJS(WebElement fromElement, WebElement ToElement ){
		String xto=Integer.toString(ToElement.getLocation().x);
		String yto=Integer.toString(ToElement.getLocation().y);
		((JavascriptExecutor)driver).executeScript("function simulate(f,c,d,e){var b,a=null;for(b in eventMatchers)if(eventMatchers[b].test(c)){a=b;break}if(!a)return!1;document.createEvent?(b=document.createEvent(a),a==\"HTMLEvents\"?b.initEvent(c,!0,!0):b.initMouseEvent(c,!0,!0,document.defaultView,0,d,e,d,e,!1,!1,!1,!1,0,null),f.dispatchEvent(b)):(a=document.createEventObject(),a.detail=0,a.screenX=d,a.screenY=e,a.clientX=d,a.clientY=e,a.ctrlKey=!1,a.altKey=!1,a.shiftKey=!1,a.metaKey=!1,a.button=1,f.fireEvent(\"on\"+c,a));return!0} var eventMatchers={HTMLEvents:/^(?:load|unload|abort|error|select|change|submit|reset|focus|blur|resize|scroll)$/,MouseEvents:/^(?:click|dblclick|mouse(?:down|up|over|move|out))$/}; " +
				"simulate(arguments[0],\"mousedown\",0,0); simulate(arguments[0],\"mousemove\",arguments[1],arguments[2]); simulate(arguments[0],\"mouseup\",arguments[1],arguments[2]); ",
				fromElement,xto,yto);
	}
	public void dragAndDropFilesByJS(String elementName, String locatorStrategy,File filePath ) throws Exception{
		WebElement ToElement = getElement( elementName, locatorStrategy);
		if(!filePath.exists())
			throw new WebDriverException("File not found: " + filePath.toString());

		WebDriver driver = ((RemoteWebElement)ToElement).getWrappedDriver();
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		WebDriverWait wait = new WebDriverWait(driver, 30);

		String JS_DROP_FILE =
				"var target = arguments[0]," +
						"    offsetX = arguments[1]," +
						"    offsetY = arguments[2]," +
						"    document = target.ownerDocument || document," +
						"    window = document.defaultView || window;" +
						"" +
						"var input = document.createElement('INPUT');" +
						"input.type = 'file';" +
						"input.style.display = 'none';" +
						"input.onchange = function () {" +
						"  var rect = target.getBoundingClientRect()," +
						"      x = rect.left + (offsetX || (rect.width >> 1))," +
						"      y = rect.top + (offsetY || (rect.height >> 1))," +
						"      dataTransfer = { files: this.files };" +
						"" +
						"  ['dragenter', 'dragover', 'drop'].forEach(function (name) {" +
						"    var evt = document.createEvent('MouseEvent');" +
						"    evt.initMouseEvent(name, !0, !0, window, 0, 0, 0, x, y, !1, !1, !1, !1, 0, null);" +
						"    evt.dataTransfer = dataTransfer;" +
						"    target.dispatchEvent(evt);" +
						"  });" +
						"" +
						"  setTimeout(function () { document.body.removeChild(input); }, 25);" +
						"};" +
						"document.body.appendChild(input);" +
						"return input;";

		WebElement input =  (WebElement)jse.executeScript(JS_DROP_FILE, ToElement);
		input.sendKeys(filePath.getAbsoluteFile().toString());
		wait.until(ExpectedConditions.stalenessOf(input));
	}

	/**************Upload/Download Files *********************/

	
	//To Enable field
	public void enableDataField(String id) throws Exception{
	  waitForPageLoad();
	  ((JavascriptExecutor)driver).executeScript("document.getElementById('"+id+"').removeAttribute('disabled')");
	 }
	
	public void uploadFiles(String elementName, String locatorStrategy,String filepath ) throws Exception{
		WebElement element =  getElement( elementName, locatorStrategy);
		element.sendKeys(filepath);
	}
	
	public static void waitForFileToCompleteDownload(File strFile) throws InterruptedException {
		int intCnt = 0;
		boolean blnFound = false;
		while (!blnFound && intCnt < WAIT_TIME_SHORTDURATION) {
			File downloadFile = new File(strFile + ".part");
			File downloadCorrFile = new File(strFile + "");
			if (!downloadFile.exists() && downloadCorrFile.exists()) {
				blnFound = true;
			}
			intCnt++;
		}
	}

	/**************Wait Functions *********************/
	//wait for text present 

	public boolean waitForTextPresent(String elementName, String locatorStrategy,String text) throws Exception{
		WebElement element = getElement( elementName, locatorStrategy);
		boolean condition = true;
		try{
			WebDriverWait wait = new WebDriverWait(driver, WAIT_TIME);
			wait.until(ExpectedConditions.textToBePresentInElement(element, text));
		}
		catch (Exception ex){
			condition = false;
			throw ex;
		}
		return condition;
	}

	//wait for text present in Value
	public boolean waitForTextPresentByValue(String elementName, String locatorStrategy,String text) throws Exception{
		WebElement element = getElement( elementName, locatorStrategy);
		boolean condition = true;
		try{
			WebDriverWait wait = new WebDriverWait(driver, WAIT_TIME);
			wait.until(ExpectedConditions.textToBePresentInElementValue(element, text));
		}
		catch (Exception ex){
			condition = false;
			throw ex;
		}
		return condition;
	}
	//Expectation for checking an element is visible and enabled
	 public void waitForElementToBeClickable(String elementName, String locatorStrategy)throws Exception {
	  WebDriverWait wait = new WebDriverWait(driver, WAIT_TIME);
	  wait.until(ExpectedConditions.elementToBeClickable(getElement(elementName,locatorStrategy)));
	 }

	//Expectation for checking an alert pop-up is present
	public void waitForAlert()throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, WAIT_TIME);
		wait.until(ExpectedConditions.alertIsPresent());
	}

	//An expectation for checking WebElement with given locator has attribute which contains specific value
	public WebElement waitForAttributeValue(String elementName, String locatorStrategy,String attribute, String value)throws Exception{
		WebElement element = getElement(elementName,locatorStrategy);
		WebDriverWait wait = new WebDriverWait(driver, WAIT_TIME);
		wait.until(ExpectedConditions.attributeContains(element,attribute,value));
		return element;
	}

	//An expectation for checking if the given element is selected.
	public WebElement waitForElementIsSelected(String elementName, String locatorStrategy)throws Exception{
		WebElement element = getElement(elementName,locatorStrategy);
		WebDriverWait wait = new WebDriverWait(driver, WAIT_TIME);
		wait.until(ExpectedConditions.elementToBeSelected(element));
		return element;
	}

	//An expectation for checking that an element, known to be present on the DOM of a page, is visible
	public WebElement waitForElementIsVisible(String elementName, String locatorStrategy)throws Exception{
		WebElement element = getElement(elementName,locatorStrategy);
		WebDriverWait wait = new WebDriverWait(driver, WAIT_TIME);
		wait.until(ExpectedConditions.visibilityOf(element) );
		return element;
	}

	//An expectation for checking whether the given frame is available to switch to.
	public WebElement waitForFrameToSwitch(String elementName, String locatorStrategy)throws Exception{
		WebElement element = getElement(elementName,locatorStrategy);
		WebDriverWait wait = new WebDriverWait(driver, WAIT_TIME);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt( element));
		return element;
	}

	//An expectation for checking the element to be invisible
	public WebElement waitForInvisibleOfElement(String elementName, String locatorStrategy)throws Exception{
		WebElement element = getElement(elementName,locatorStrategy);
		WebDriverWait wait = new WebDriverWait(driver, WAIT_TIME);
		wait.until(ExpectedConditions.invisibilityOf(element));
		return element;
	}
	
	//An expectation for checking the page load
	public static void waitForPageLoad() {
		try {
			ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver webdriver) {
					return ((JavascriptExecutor) webdriver).executeScript("return document.readyState")
							.equals("complete");
				}
			};
			WebDriverWait wait = new WebDriverWait(Configuration.driver,WAIT_TIME_LONGDURATION );
			wait.until(pageLoadCondition);
		} catch (WebDriverException we) {
			System.out.println(we.toString());
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	
	public static void waitForTextToBePresent(String elementName, String locatorStrategy, String strText) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, WAIT_TIME);
			wait.until(ExpectedConditions.textToBePresentInElementLocated(getWebDriverBy(elementName,locatorStrategy), strText));
		} catch (WebDriverException we) {
			System.out.println(we.toString());
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	// Checks is element present returns boolean value
	public boolean isElementPresent(String elementName, String locatorStrategy) {
		try {
			if (driver.findElements(getWebDriverBy(elementName,locatorStrategy)).size()!= 0) {
				return true;

			} else {
				return false;
			}
		} catch (WebDriverException e) {
			return false;
		} catch (Exception e) {
			return false;
		}
	}
		
	public void waitForAllElementVisible(String elementName, String locatorStrategy) {
		try {
			new WebDriverWait(driver, WAIT_TIME_LONGDURATION)
					.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getWebDriverBy(elementName,locatorStrategy)));
		} catch (WebDriverException we) {
			System.out.println(we.toString());
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
	
	public void waitForElementAppearance(String elementName, String locatorStrategy) throws Exception {
		new WebDriverWait(driver, WAIT_TIME_LONGDURATION).until(ExpectedConditions.presenceOfElementLocated(getWebDriverBy(elementName,locatorStrategy)));
	}
	
	
	/**************Verification Functions *********************/
	
		public  boolean verifySelectedOption(String elementName, String locatorStrategy)throws Exception {
			try {
				assertTrue(getElement(elementName,locatorStrategy).isSelected());
			} catch (StaleElementReferenceException se) {
				waitForPageLoad();
				assertTrue(getElement(elementName,locatorStrategy).isSelected());
			}
			return false;
		}
		public void verifyTitle(String strValue,String errorMessage) throws Exception {
			   String titleName = driver.getTitle();
			   assertEquals(strValue, titleName, errorMessage);
			  }
		
		public  void verifyText(String elementName, String locatorStrategy, String strValue, String errorMessage) throws Exception {
			assertEquals(strValue, getElement(elementName,locatorStrategy).getText().trim(),errorMessage);
		}
		
		public  void verifyTextIsPresent(String elementName, String locatorStrategy, String errorMessage) throws Exception {
			assertTrue(isElementPresent(elementName,locatorStrategy),errorMessage);
		}
		
		public  void verifyTextIsNotPresent(String elementName, String locatorStrategy, String errorMessage) throws Exception {
			   assertFalse(isElementPresent(elementName,locatorStrategy),errorMessage);
			  }
		
		public void verifyInputValue(String elementName, String locatorStrategy, String strValue,String errorMessage) throws Exception {
			assertEquals(strValue, getAttributeValue(elementName,locatorStrategy).trim(),errorMessage);
		}
		
		public void verifySelectedValue(String elementName, String locatorStrategy, String strValue)throws Exception {
			String strLabel = "";
			waitForPageLoad();
			try {
				WebElement option = new Select(getElement(elementName,locatorStrategy)).getFirstSelectedOption();
				strLabel = option.getText();

			} catch (StaleElementReferenceException se) {
				waitForPageLoad();
				WebElement option = new Select(getElement(elementName,locatorStrategy)).getFirstSelectedOption();
				strLabel = option.getText();
			} catch (NullPointerException ne) {
				waitForPageLoad();
				WebElement option = new Select(getElement(elementName,locatorStrategy)).getFirstSelectedOption();
				strLabel = option.getText();
			} catch (WebDriverException we) {
				waitForPageLoad();
				WebElement option = new Select(getElement(elementName,locatorStrategy)).getFirstSelectedOption();
				strLabel = option.getText();
			}
			strLabel = strLabel.trim();
			assertEquals(strValue, strLabel);
		}

		public void verifyNoValueSelected(String elementName, String locatorStrategy) throws Exception{
			int intSize = 1;
			waitForPageLoad();
			try {
				List<WebElement> option = new Select(getElement(elementName,locatorStrategy)).getAllSelectedOptions();
				intSize = option.size();
				assertEquals(intSize, 0);
			} catch (StaleElementReferenceException se) {
				List<WebElement> option = new Select(getElement(elementName,locatorStrategy)).getAllSelectedOptions();
				intSize = option.size();
				assertEquals(intSize, 0);
			} catch (NullPointerException ne) {
				List<WebElement> option = new Select(getElement(elementName,locatorStrategy)).getAllSelectedOptions();
				intSize = option.size();
				assertEquals(intSize, 0);
			} catch (WebDriverException we) {
				List<WebElement> option = new Select(getElement(elementName,locatorStrategy)).getAllSelectedOptions();
				intSize = option.size();
				assertEquals(intSize, 0);
			}
			assertEquals(intSize, 0);
		}
		
		public  void verifyTextIsNotDisplayed(String elementName, String locatorStrategy, String errorMessage) throws Exception {
			   assertFalse(getElement(elementName,locatorStrategy).isDisplayed(),errorMessage);
			  }
		
		public  boolean verifySelectedOptionIsDeselected(String elementName, String locatorStrategy)throws Exception {
			  try {
			   assertFalse(getElement(elementName,locatorStrategy).isSelected());
			  } catch (StaleElementReferenceException se) {
			   waitForPageLoad();
			   assertFalse(getElement(elementName,locatorStrategy).isSelected());
			  }
			  return true;
		}


}
