package com.Jio.TestPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.Jio.BaseClass.ConsumerBaseClass;



public class ConsumerLoginPage extends ConsumerBaseClass
{

	@FindBy(xpath="//input[@id='j_id__ctru12:r1:0:s1:ot189::content']")
	WebElement UserName;
	
	@FindBy(xpath="//input[@id='j_id__ctru12:r1:0:s1:it587::content']")
	WebElement Password;
	
	@FindBy(xpath="//button[@id='j_id__ctru12:r1:0:s1:cb1']")
	WebElement LoginButton;
	
	@FindBy(xpath="//img[@id='j_id__ctru12:pt_gil2::icon']")
	WebElement Logo;
	
	public ConsumerLoginPage() 
	{
	PageFactory.initElements(driver, this);	
	}
	
	public boolean LogoPresent()
	{
		return Logo.isDisplayed();
		
	}
	public String ConsumerPortalGetTittle()
	{
		return driver.getTitle();
		
	}
	public void ConsumerLogin()
	{
		UserName.sendKeys("9821557811");
		Password.sendKeys("Manish42#");
		LoginButton.click();
		
	}
	
	/*public DashBoardPage DashboardPage()
	{
		return new DashBoardPage();
		
	}*/
	
	
	
	
	
}
