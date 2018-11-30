package automation.practice;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.ChromeDriverManager;

public class HeadlessBrowsers {
	
	@Test
	public void ffheadless() {
		FirefoxBinary fb=new FirefoxBinary();
		fb.addCommandLineOptions("--headless");
		System.setProperty("webdriver.gecko.driver", "D:\\GitRepo_Automation\\automation_project\\automation.practice\\Browsers\\geckodriver.exe");
		FirefoxOptions foo=new FirefoxOptions();
		foo.setBinary(fb);
		WebDriver driver=new FirefoxDriver(foo);
		driver.get("http://www.google.com");
		System.out.println(driver.getTitle());
		
	}
	@Test
	public void chromeheadless() {
		ChromeDriverManager.getInstance().setup();
	    ChromeOptions chromeOptions = new ChromeOptions();

	    chromeOptions.addArguments("--headless");
	    ChromeDriver d = new ChromeDriver(chromeOptions);
		d.get("http://www.google.com");
		System.out.println(d.getTitle());
		
	}
	

}
