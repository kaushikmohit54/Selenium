package com.Jio.TestPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.Jio.BaseClass.ConsumerBaseClass;



public class DashBoardPage extends ConsumerBaseClass
{

	@FindBy(xpath="//div[contains(text(),'Dashboard')]")
	WebElement DashboardTittle;

	@FindBy(xpath="//div[contains(text(),'Your JioMoney account balance is ')]")
	WebElement AccountBalance;

	@FindBy(xpath="//div[contains(text(),'Last 5 transaction in 3 months')]")
	WebElement LastFive;
	
	@FindBy(xpath="//div[@id='pt1:pgl0img']//div")
	WebElement Logout;
	
	@FindBy(xpath="//a[@id='pt1:pt_gl29']")
	WebElement TranscationHistory;
	
	public DashBoardPage()
	{
		PageFactory.initElements(driver, this);

	}
	
	public String DashBoardTittleTest()
	{
		return driver.getTitle();
		
	}
	
	public String DashBoardTittleValidation()
	{
		return DashboardTittle.getText();
	}
	
	public String AccountBalance()
	{
		return AccountBalance.getText();
	}

	public String LastFiveTranscation()
	{
		return LastFive.getText();
	}
	
	public void ClickOnLogout()
	{
		Logout.click();
	}
	
	/*public TranscationHistoryPage ClickOnTranscationHistory()
	{
		TranscationHistory.click();
		return new TranscationHistoryPage();
	}*/

	







}
