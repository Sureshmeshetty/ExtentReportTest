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
		Util.logPassExtent(result.getName()+" Test Ended");
	}

	public void onTestFailure(ITestResult result) {
		try {
			Util.logFailExtent("Test Case Failed is "+result.getName());
			Util.logFailExtent("Test Case Failed is "+result.getThrowable().getMessage(),result.getName());
		}
		catch (IOException e) {
			Util.logFailExtent("Unable to capture the Screenshot");
		}
	}

	public void onTestSkipped(ITestResult result) {
		Util.logSkipExtent("Test Case Skipped is "+result.getName());
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	}

	public void onStart(ITestContext context) {
	}

	public void onFinish(ITestContext context) {
	}
}