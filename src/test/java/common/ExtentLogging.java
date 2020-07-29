package common;

import java.io.IOException;

import com.relevantcodes.extentreports.LogStatus;

public class ExtentLogging extends BaseClass {

	public static void logPassExtent(String message)
	{
		logger.log(LogStatus.PASS,message);
	}
	
	public static void logInfoExtent(String message)
	{
		logger.log(LogStatus.INFO,message);
	}
	
	public static void logPassExtent(String message,String title) throws IOException
	{
		
		logPassExtent(message);
		String screenshotPath = Util.getScreenshot(driver, title);
		logPassExtent(logger.addScreenCapture(screenshotPath));
	}
	
	public static void logSkipExtent(String message)
	{
		logger.log(LogStatus.SKIP,message);
	}
	
	public static void logFailExtent(String message)
	{
		logger.log(LogStatus.FAIL,message);
	}
	
	public static void logFailExtent(String message,String title) throws IOException
	{
		logFailExtent(message);
		String screenshotPath = Util.getScreenshot(driver, title);
		logFailExtent(logger.addScreenCapture(screenshotPath));
	}

}
