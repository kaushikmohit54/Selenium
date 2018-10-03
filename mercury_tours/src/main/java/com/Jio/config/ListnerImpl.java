package com.Jio.config;

import org.testng.ITestContext;
import org.testng.ITestListener;

import org.testng.ITestResult;




public class ListnerImpl implements ITestListener


{

	public void onTestStart(ITestResult result)
	{
		System.out.println("===========================================================");
		System.out.println("Test Case Execution Started,Test Name is:\t"+result.getName());
		System.out.println("===========================================================");
		
	}

	public void onTestSuccess(ITestResult result) 
	{
		System.out.println("===========================================================");
		System.out.println("Test Case Executed Sucesfully,Test Name is:\t"+result.getName());
		System.out.println("===========================================================");
	}

	public void onTestFailure(ITestResult result) 
	{
		System.out.println("===========================================================");
		System.out.println("Test Case Execution Got Failed,Test Name is:\t"+result.getName());
		System.out.println("===========================================================");
		
	}

	public void onTestSkipped(ITestResult result)
	{
		System.out.println("===========================================================");
		System.out.println("Test Case is Skipped,Test Name is:\t"+result.getName());
		System.out.println("===========================================================");
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result)
	{
	
		
	}

	public void onStart(ITestContext context)
	{
	
		
	}

	public void onFinish(ITestContext context) 
	{
	
		
	}

	

}
