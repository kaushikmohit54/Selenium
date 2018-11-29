package automation.practice;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.assertthat.selenium_shutterbug.core.Shutterbug;

import io.github.bonigarcia.wdm.ChromeDriverManager;

public class selePratice {
	public WebDriver driver;
	
	
	@Test
	public void ShutterbugScreesnshot() {
		ChromeDriverManager.getInstance().setup();
		driver=new ChromeDriver();
		driver.get("https://www.google.com/");
		Shutterbug.shootPage(driver).withName("SampleScreenshot").save();
		
		
		
	}

}
