package com.jio.Testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.Jio.BaseClass.ConsumerBaseClass;
import com.Jio.TestPages.DashBoardPage;



public class DashBoardTest extends ConsumerBaseClass
{

	DashBoardPage Dash;
	
	public DashBoardTest()
	{
		super();
	}
	
	@BeforeTest 
    public void DashBoardIntialize()
    {
		Dash=new DashBoardPage();
	}
	
	@Test(priority=4,description="Tittle Validation for Dashboard Page of Consumer Portal")
	public void DashBoardPageTittleValidation() throws InterruptedException 
	{
		logger1 = extent.createTest("Tittle Validation for Dashboard Page of Consumer Portal");
		Thread.sleep(5000);
		String Tittle=Dash.DashBoardTittleTest();
		Assert.assertEquals(Tittle, "Dashboard | JioMoney","Tittle of Consumer Portal Dashboard Page Not Matched");
		
	}
	
	@Test(priority=5,description="Second Tittle Validation for Dashboard Page of Consumer Portal")
	public void SecondDashBoardPageTittleValidation() 
	{
		logger1 = extent.createTest("Second Tittle Validation for Dashboard Page of Consumer Portal");
		String Tittle=Dash.DashBoardTittleValidation();
		Assert.assertEquals(Tittle, "Dashboard","Second Tittle of Consumer Portal Dashboard Page Not Matched");
		
	}
	
	@Test(priority=6,description="Account Balance Text Validation on Dashboard Page of Consumer Portal")
	public void AccountBalanceTextValidation() 
	{
		logger1 = extent.createTest("Account Balance Text Validation on Dashboard Page of Consumer Portal");
		String Tittle=Dash.AccountBalance();
		Assert.assertEquals(Tittle, "Your JioMoney account balance is 15783.03","Account Balance Text Validation");
		
	}
	
	@Test(priority=7,description="Last 5 Transcation text Validation for Dashboard Page of Consumer Portal")
	public void Last5TranscationTextValidation() 
	{
		logger1 = extent.createTest("Last 5 Transcation text Validation for Dashboard Page of Consumer Portal");
		String Tittle=Dash.LastFiveTranscation();
		Assert.assertEquals(Tittle, "Last 5 transaction in 3 months","Account Balance Text Validation");
		
	}
	
	@Test(priority=8,description="Click On Transcation History Page")
	public void MoveToTranscationHistoryPage() 
	{
		logger1 = extent.createTest("Click On Transcation History Page");
		Dash.ClickOnLogout();
		//Dash.ClickOnTranscationHistory();
				
	}
	
	



}
