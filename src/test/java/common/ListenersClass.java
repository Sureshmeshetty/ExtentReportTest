package common;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ListenersClass extends BaseClass implements ITestListener {
	public void onTestStart(ITestResult result) {
		logger = extent.startTest(result.getName());
		ExtentLogging.logPassExtent("\""+result.getName()+"\" Testcase is Started");
	}

	public void onTestSuccess(ITestResult result) {
		ExtentLogging.logPassExtent("<b><span style='color:blue;'>\""+result.getName()+"\"</span></b> Testcase is Ended");
	}

	public void onTestFailure(ITestResult result) {
		ExtentLogging.logFailExtent(result.getThrowable().getMessage(),result.getName());
		ExtentLogging.logFailExtent("Testcase Failed: <b><span style='color:red;'>\""+result.getName()+"\"</span></b>");
	}

	public void onTestSkipped(ITestResult result) {
		ExtentLogging.logSkipExtent("Testcase Skipped: <b><span style='color:orange;'>\""+result.getName()+"\"</span></b>");
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	}

	public void onStart(ITestContext context) {
	}

	public void onFinish(ITestContext context) {
	}
}