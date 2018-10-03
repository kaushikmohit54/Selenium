package com.jio.Testcases;

import java.net.MalformedURLException;

import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.Jio.BaseClass.ConsumerBaseClass;
import com.Jio.TestPages.ConsumerLoginPage;



public class ConsumerLoginPageTest extends ConsumerBaseClass
{
	ConsumerLoginPage Con;
	
	@BeforeSuite
	public void setup() throws MalformedURLException
	{
		initialization();
		Con=new ConsumerLoginPage();
	}
	
	public ConsumerLoginPageTest()
	{

		super();
	}
	
	@Test(priority=1,description="Validation of Logo On Consumer Portal Home Page")
	public void ConsumerLogoTest()
	{
		logger1 = extent.createTest("Validation of Logo On Consumer Portal Home Page");
		boolean LogTest=Con.LogoPresent();
		Assert.assertTrue(LogTest , "Logo is not present in Consumer Portal");
	}
	
	@Test(priority=2,description="Validation of Tittle On Consumer Portal Home Page")
	public void ConsumerPortalTittle()
	{
		logger1 = extent.createTest("Validation of Tittle On Consumer Portal Home Page");
		String Tittle=Con.ConsumerPortalGetTittle();
		Assert.assertEquals(Tittle, "Login | JioMoney","Consumer Page Tittle Validation Failed");
		logger.info("Tittle Validated Sucesfully on Consumer Portal Home Page");
	}
	
	@Test(priority=3,description="Login Validation for Consumer Portal")
	public void ConsumerLoginPortalTest()
	{
		logger1 = extent.createTest("Login Validation for Consumer Portal");
		Con.ConsumerLogin();
		logger.info("Consumer Login Validation is Sucesfull");
		//Con.DashboardPage();
			
	}
	
	
	
	
	
	
	
}
