package common;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class BaseClass {
	public static WebDriver driver;
	public static ExtentReports extent;
	public static ExtentTest logger;
	
	@BeforeSuite
	public void startReport(){
		//ExtentReports(String filePath,Boolean replaceExisting) 
		//filepath - path of the file, in .htm or .html format - path where your report needs to generate. 
		//replaceExisting - Setting to overwrite (TRUE) the existing file or append to it
		//True (default): the file will be replaced with brand new markup, and all existing data will be lost. Use this option to create a brand new report
		//False: existing data will remain, new tests will be appended to the existing report. If the the supplied path does not exist, a new file will be created.
		extent = new ExtentReports (System.getProperty("user.dir") +"/AutomationReport/STMExtentReport.html", true);
		extent
		.addSystemInfo("Application Name","Test Application")
		.addSystemInfo("User Name", System.getProperty("user.name"))
		.addSystemInfo("Host Name", "Automation Testing")
		.addSystemInfo("Environment", "QA");
		//loading the external xml file (i.e., extent-config.xml) which was placed under the test >> resources directory
		//You could find the xml file below. Create xml file in your project and copy past the code mentioned below
		extent.loadConfig(new File(System.getProperty("user.dir")+"\\src\\test\\resources\\extent-config.xml"));
	}
	
	public void logPassExtent(String message)
	{
		logger.log(LogStatus.PASS,message);
	}
	
	public void logPassExtent(String message,String title) throws IOException
	{
		logPassExtent(message);
		String screenshotPath = getScreenshot(driver, title);
		logPassExtent(logger.addScreenCapture(screenshotPath));
	}
	
	public void logSkipExtent(String message)
	{
		logger.log(LogStatus.SKIP,message);
	}
	
	public void logFailExtent(String message)
	{
		logger.log(LogStatus.FAIL,message);
	}
	
	public void logFailExtent(String message,String title) throws IOException
	{
		logFailExtent(message);
		String screenshotPath = getScreenshot(driver, title);
		logFailExtent(logger.addScreenCapture(screenshotPath));
	}

	public static String getScreenshot(WebDriver driver, String screenshotName) throws IOException{
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		//after execution, you could see a folder "FailedTestsScreenshots" under src folder
		String destination = System.getProperty("user.dir") + "/FailedTestsScreenshots/"+screenshotName+dateName+".png";
		File finalDestination = new File(destination);

		FileUtils.copyFile(source, finalDestination);
		return destination;
	}
}
