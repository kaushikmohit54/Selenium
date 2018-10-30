package com.Jio.BaseClass;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;

import com.Jio.config.TakeScreenshot;
import com.Jio.config.WebElementListener;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ConsumerBaseClass
{

	public static WebDriver driver = null; 
	public static Properties prop;
	public static EventFiringWebDriver e_driver;
	public static WebDriverEventListener eventListener;
	public static Logger logger;
	public static ExtentTest logger1;
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	static String nodeURL;

	@BeforeSuite

	public void ReportSetup() throws IOException 
	{
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") +"/test-output/STMExtentReport1.html");
		extent = new ExtentReports ();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Application Name", "Consumer Portal");
		extent.setSystemInfo("User Name", "Mohit Kaushik");
		extent.setSystemInfo("Envirnoment", "Pre-Prod");

		htmlReporter.config().setDocumentTitle("Consumer Portal Automation Testing Report");
		htmlReporter.config().setReportName("Consumer Portal Automation Testing");
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlReporter.config().setTheme(Theme.STANDARD);	
	}


	public ConsumerBaseClass()
	{
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+ "/src/main/java/com/"
					+ "/Jio/config/Conf.properties");
			prop.load(ip);
			logger=Logger.getLogger("ConsumerPortal");
			PropertyConfigurator.configure(".\\src\\main\\java\\com\\Jio\\config\\log4j.properties");
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}


	public void initialization() throws MalformedURLException
	{
		String browserName = prop.getProperty("browser");

		if(browserName.equals("Chrome")){
			System.setProperty("webdriver.chrome.driver","D:\\GitRepo_Automation\\automation_project\\mercury_tours\\Browsers\\chromedriver.exe");	
			driver = new ChromeDriver(); 
		}
		else if(browserName.equals("FireFox")){
			System.setProperty("webdriver.gecko.driver","D:\\GitRepo_Automation\\automation_project\\mercury_tours\\Browsers\\geckodriver.exe");	
			driver = new FirefoxDriver(); 
		}
		else if (browserName.equals("Grid")) {
			nodeURL = "http://10.50.88.59:4444/wd/hub";
			DesiredCapabilities capability = DesiredCapabilities.chrome();
			capability.setBrowserName("chrome");
			capability.setPlatform(Platform.WINDOWS);
			driver = new RemoteWebDriver(new URL(nodeURL), capability);
		}
		else if (browserName.equals("Headless")) {
			//Set the path of the phantomjs.exe file in the properties
			System.setProperty("phantomjs.binary.path","D:\\phantomjs-2.1.1-windows\\phantomjs-2.1.1-windows\\bin\\phantomjs.exe");

			// To declare and initialize PhantomJSDriver
			driver = new PhantomJSDriver();
		}

		e_driver = new EventFiringWebDriver(driver);
		// Now create object of EventListerHandler to register it with EventFiringWebDriver
		eventListener = new WebElementListener();
		e_driver.register(eventListener);
		driver = e_driver;

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		driver.get(prop.getProperty("url"));

	}

	@AfterMethod
	public void getResult(ITestResult result) throws Exception
	{
		if(result.getStatus() == ITestResult.FAILURE)
		{
			//MarkupHelper is used to display the output in different colors
			logger1.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
			logger1.log(Status.FAIL, MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));

			//To capture screenshot path and store the path of the screenshot in the string "screenshotPath"
			//We do pass the path captured by this mehtod in to the extent reports using "logger.addScreenCapture" method. 

			
			String screenshotPath = TakeScreenshot.captuerScreenshot(driver, result.getName());
			//To add it in the extent report 

			logger1.fail("Test Case Failed Snapshot is below " + logger1.addScreenCaptureFromPath(screenshotPath));


		}
		else if(result.getStatus() == ITestResult.SKIP){
			
			logger1.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " - Test Case Skipped", ExtentColor.ORANGE)); 
		} 
		else if(result.getStatus() == ITestResult.SUCCESS)
		{
			logger1.log(Status.PASS, MarkupHelper.createLabel(result.getName()+" Test Case PASSED", ExtentColor.GREEN));
		}

	}
	@AfterTest

	public void Exit() {

		driver.quit();
	}


	@AfterSuite
	public void endReport()
	{
		extent.flush();
	}




}








