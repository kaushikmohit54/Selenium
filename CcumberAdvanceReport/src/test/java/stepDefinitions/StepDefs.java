package stepDefinitions;



import java.io.File;
import java.io.IOException;

import java.util.Date;


import java.util.Properties;


import org.apache.commons.io.FileUtils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

import org.openqa.selenium.chrome.ChromeDriver;


import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StepDefs {
	

	public WebDriver driver;
	public Properties props;
	
	
	
	@Before()
	public void browserInitialize() throws IOException {
	
	   
		System.setProperty("webdriver.chrome.driver", "chromedriver/chromedriver.exe");
		/*Map<String, Object> chromePreferences = new Hashtable<String, Object>();
		 Below two chrome preference settings will disable popup dialog when download file.
		chromePreferences.put("profile.default_content_settings.popups", 0);
		chromePreferences.put("download.prompt_for_download", "false");
		
		 Set file save to directory. 
		chromePreferences.put("download.default_directory", "C:\\WorkSpace");

		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.setExperimentalOption("prefs", chromePreferences);

		DesiredCapabilities cap = DesiredCapabilities.chrome();
		cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		cap.setCapability(ChromeOptions.CAPABILITY, chromeOptions);*/
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		

	}
	
	

	public void takeScreenShot(){
        // fileName of the screenshot
        Date d=new Date();
        String screenshotFile=d.toString().replace(":", "_").replace(" ", "_")+".png";
        // store screenshot in that file
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
               FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")+"//Fail_Screenshot//"+screenshotFile));
        } catch (IOException e) {
               // TODO Auto-generated catcsh block
               e.printStackTrace();
        }
        //put screenshot file in reports
       
        
        
 }
	//login to the application
	
	@Given("^User is on Home Page$")
	public void user_is_on_Home_Page() throws Throwable {
		System.out.println("user is on home page");
	   
	}

	@When("^User Navigate to LogIn Page$")
	public void user_Navigate_to_LogIn_Page() throws Throwable {
		System.out.println("user is on Login page");
	}

	@When("^User enters UserName and Password$")
	public void user_enters_UserName_and_Password() throws Throwable {
		System.out.println("user enter user name and password");
	    
	}

	@Then("^Message displayed Login Successfully$")
	public void message_displayed_Login_Successfully() throws Throwable {
		System.out.println("user sucessfully login");
	  
	}

	@When("^User LogOut from the Application$")
	public void user_LogOut_from_the_Application() throws Throwable {
		System.out.println("user sucessfully logout");
	}

	@Then("^Message displayed LogOut Successfully$")
	public void message_displayed_LogOut_Successfully() throws Throwable {
		System.out.println("user sucessfully meg logout");
	    
	}




	
		@After()
	public void embedScreenshot(Scenario scenario) throws IOException {
		if (scenario.isFailed()) {
			try {
				final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
				scenario.embed(screenshot, "image/png");
			} catch (WebDriverException wde) {
				System.err.println(wde.getMessage());
			} catch (ClassCastException cce) {
				cce.printStackTrace();
			}
		}
				driver.quit();
	}

}