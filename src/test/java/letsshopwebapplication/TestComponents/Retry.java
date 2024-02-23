package letsshopwebapplication.TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer{

	int count = 0;
	int maxtry = 1;//how many times test to should re run
	
	//If the test fails, control will come here and check whether the test has to rerun or not
	//retry method will keep on executing as long as it returns true. 
	//once it returns false, the failed test case won't rerun.
	
	@Override
	public boolean retry(ITestResult result) {
		// TODO Auto-generated method stub
		if(count<maxtry)
			
		{
			count++;
			return true;
		}
		return false;
	}

}
