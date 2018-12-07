package automation.practice;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import io.github.bonigarcia.wdm.ChromeDriverManager;

public class BaseTest {
	public WebDriver driver;
	
	@BeforeTest
	public void configuration() {

		ChromeDriverManager.getInstance().setup();
		ChromeDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.google.com");
		
		
	}

	@AfterTest
	public void close() {
	 driver.manage().deleteAllCookies();
     driver.quit();
		
		
	}

}
