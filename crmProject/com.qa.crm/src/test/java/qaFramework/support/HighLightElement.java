package qaFramework.support;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class HighLightElement{

	/**
	 * @param driver
	 * @param element
	 */
	public void highLightElement(WebDriver driver, WebElement element)
	{
		JavascriptExecutor js=(JavascriptExecutor)driver; 
        //We can change the Border colour and Background colour by changing the colour codes
		js.executeScript("arguments[0].setAttribute('style', 'background: White; border: 4px solid red;');", element);
      
	    //Removing the colour attributes.
		js.executeScript("arguments[0].setAttribute('style','background: grey; border: solid 4px grey');", element); 

	}

	
}


