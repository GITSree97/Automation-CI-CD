package letsshopwebapplication.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import letsshopwebapplication.resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener{
	//call the method getReportObject() by marking it as static so that we can call it without creating object
     ExtentReports extent = ExtentReporterNG.getReportObject();
     ExtentTest test;
	@Override
	public void onTestStart(ITestResult result)
	{
		//before running any test create an entry of the test in extent reports
		//result variable stores which test is going to be executed
		//result.getMethod().getMethodName()- will fetch the method name which is going to be executed 
		//extent.createTest- set entry in extent reports for that particular test
		
		test = extent.createTest(result.getMethod().getMethodName());
	
	}
	
	@Override
	public void onTestSuccess(ITestResult result)
	{
		test.log(Status.PASS, "Test Passed");
	}
	@Override
	public void onTestFailure(ITestResult result)
	{
		//getThrowable()-gives the error message and with that error message, test will fail
		test.fail(result.getThrowable());
		//Take screenshot- call the method getScreenshot by passing the test case name
		
		
		try {
			driver= (WebDriver)result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		
		}
		
		String filepath = null;
		try {
			filepath = getScreenshot(result.getMethod().getMethodName(),driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//Attach it to the reports
		
		test.addScreenCaptureFromPath(filepath, result.getMethod().getMethodName());
		
	}
	
	@Override
	public void onFinish(ITestContext context)
	{	
		extent.flush();
	}
	
}
