package Login;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class Configuration {
	WebDriver driver;
	@BeforeTest
	public void openBrowser() {
		
		System.setProperty("webdriver.chrome.driver", "D:\\GitRepo_Automation\\automation_project\\mercury_tours\\Browsers\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
		
	}
	@AfterTest
	public void closeBrowser() {
		
		driver.quit();
		
	}

}
