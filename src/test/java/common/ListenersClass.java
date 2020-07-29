package common;
import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ListenersClass extends BaseClass implements ITestListener {
	public void onTestStart(ITestResult result) {
		logger = extent.startTest(result.getName());
	}

	public void onTestSuccess(ITestResult result) {
		ExtentLogging.logPassExtent(result.getName()+" Test Ended");
	}

	public void onTestFailure(ITestResult result) {
		try {
			ExtentLogging.logFailExtent("Test Case Failed is "+result.getName());
			ExtentLogging.logFailExtent("Test Case Failed is "+result.getThrowable().getMessage(),result.getName());
		}
		catch (IOException e) {
			ExtentLogging.logFailExtent("Unable to capture the Screenshot");
		}
	}

	public void onTestSkipped(ITestResult result) {
		ExtentLogging.logSkipExtent("Test Case Skipped is "+result.getName());
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	}

	public void onStart(ITestContext context) {
	}

	public void onFinish(ITestContext context) {
	}
}