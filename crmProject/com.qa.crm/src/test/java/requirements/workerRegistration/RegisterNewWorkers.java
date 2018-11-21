package requirements.workerRegistration;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import configuration.Configuration;
import data.LoginData;
import pages.Login;
import qaFramework.support.ExtentManager;

public class RegisterNewWorkers extends Configuration{

	public ExtentReports rep = ExtentManager.getInstance();
	public  ExtentTest test;
	Login login;
	LoginData loginData;

	
	
	
	ExtentTest logger;
	WebDriver driver;
	

	@Test
	public void login_crm() throws Exception{
		
		test = rep.startTest("login","Validation of login to crm");
		loginData=new LoginData();
		login=new Login(driver);
		login.login(LoginData.USER_NAME,LoginData.PASSWORD);
		//password
		
 
		
 
		
		
		
	
	}

	

}
