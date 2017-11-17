package listener;

//extend TestListenerAdapter class or implement ITestListener
//Can be implemented on Class/ Suite level

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestNGListener implements ITestListener{

	public void onTestStart(ITestResult result) {
	
		System.out.println("TestNG Listener - Starting Test : "+result.getName());	
	}

	public void onTestSuccess(ITestResult result) {
		
		System.out.println("TestNG Listener - Test successfully completed : "+result.getName());
	}

	public void onTestFailure(ITestResult result) {
	
		System.out.println("TestNG Listener - Test failed : "+result.getName());	
	}

	public void onTestSkipped(ITestResult result) {
	
		System.out.println("TestNG Listener - Test skipped : "+result.getName());
	}


	public void onStart(ITestContext context) {	
	}
	public void onFinish(ITestContext context) {		
	}
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {	
	}

}
