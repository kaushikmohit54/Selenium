package configuration;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.InternetExplorerDriverManager;
import qaFramework.support.ExtentManager;
import qaFramework.support.ReadPropertyFiles;

public class Configuration {
	private Properties envPropertiesDetails;
	private String URL;
	public static WebDriver driver;
	protected String TCID;
	protected String TC_DESCRIPTION;
	private String browser;
	
	public SoftAssert softAssert;
	
	public ExtentReports rep = ExtentManager.getInstance();
	public  ExtentTest test;
	

	@BeforeClass
	public void setup() throws Exception{
		ReadPropertyFiles objReadPropertyFile = new ReadPropertyFiles();
		envPropertiesDetails = objReadPropertyFile.ReadEnvironment();
		URL = envPropertiesDetails.getProperty("RAM_ENV");
		browser = envPropertiesDetails.getProperty("Browser");

		switch(browser) {
		case "Chrome":
			ChromeDriverManager.getInstance().setup();
			DesiredCapabilities chromeCapabilities = DesiredCapabilities.chrome();
			ChromeOptions options = new ChromeOptions();

			String downloadFilepath = ".\\src\\test";
			HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
			chromePrefs.put("profile.default_content_settings.popups", 0);
			chromePrefs.put("download.default_directory", downloadFilepath);
			chromePrefs.put("download.prompt_for_download", true);
			HashMap<String, Object> chromeOptionsMap = new HashMap<String, Object>();
			options.setExperimentalOption("prefs", chromePrefs);
			options.addArguments("--test-type");
			options.addArguments("chrome.switches","--disable-extensions");
			chromeCapabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptionsMap);
			chromeCapabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			chromeCapabilities.setCapability(ChromeOptions.CAPABILITY, options);
			driver = new ChromeDriver(options);
			driver.manage().window().maximize();
			driver.get(URL);
			break;

		case "iexplore":
			InternetExplorerDriverManager.getInstance().setup();
			InternetExplorerOptions ieOptions = new InternetExplorerOptions();
			ieOptions.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
			ieOptions.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, true);
			ieOptions.setCapability("requireWindowFocus", true);
			ieOptions.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
			ieOptions.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			ieOptions.setCapability("ignoreZoomSetting", true);
			ieOptions.setCapability("nativeEvents",false);
			driver = new InternetExplorerDriver(ieOptions);
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();
			driver.get(URL);
			break;}
		}

	@BeforeMethod
	public void init() {

		softAssert = new SoftAssert();

	}
		
	@AfterMethod
	public void quit() {
		try {
			softAssert.assertAll();
		} catch (Error e) {
			test.log(LogStatus.FAIL, e.getMessage());
			takeScreenShot();
		}
		if (rep != null) {
			rep.endTest(test);
			rep.flush();
		}

		if (driver != null)
			driver.quit();

	}
			
			/*if(ITestResult.FAILURE==result.getStatus())
			{
			try{
				TakesScreenshot ts = (TakesScreenshot)driver;
				File source=ts.getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(source, new File("./src/test/resources/Screenshots/"+result.getName()+".png"));
			}catch (Exception e) {
				
			}
			driver.manage().deleteAllCookies();
			extent.flush();
		}*/
		
		public void takeScreenShot(){
			// fileName of the screenshot
			Date d=new Date();
			String screenshotFile=d.toString().replace(":", "_").replace(" ", "_")+".png";
			// store screenshot in that file
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			try {
				FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")+"//screenshots//"+screenshotFile));
			} catch (IOException e) {
				// TODO Auto-generated catcsh block
				e.printStackTrace();
			}
			//put screenshot file in reports
			test.log(LogStatus.INFO,"Screenshot-> "+ test.addScreenCapture(System.getProperty("user.dir")+"//screenshots//"+screenshotFile));
		}

			/*@AfterClass
			public void closeBrowser() throws Exception{
				driver.close();
				driver.quit();
			}*/
}
