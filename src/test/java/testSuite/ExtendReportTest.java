package testSuite;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import common.BaseClass;
import common.ListenersClass;

@Listeners(ListenersClass.class)
public class ExtendReportTest extends BaseClass{

	@Test
	public void passTest(){
		//extent.startTest("TestCaseName", "Description")
		//TestCaseName – Name of the test
		//Description – Description of the test
		//Starting test
		//logger = extent.startTest("passTest");
		logPassExtent("This is My Test case");
		Assert.assertTrue(true);
		//To generate the log when the test case is passed
		logPassExtent("Test Case Passed is passTest");
	}

	@Test
	public void failTest(){
		//My intention is to fail this method
		//If this method fails, then it goes to the @AfterMethod and calls the getScreenshot method and captures a screenshot and place it in the extent reports
		//logger = extent.startTest("failTest");
		System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\src\\test\\resources\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		logPassExtent("Chrome Driver is launched");
		driver.get("https://chromedriver.chromium.org/downloads");
		String currentURL = driver.getTitle();
		Assert.assertEquals(currentURL, "NoTitle");
		logPassExtent("Test Case (failTest) Status is passed");
	}

	@Test
	public void skipTest(){
		//logger = extent.startTest("skipTest");
		throw new SkipException("Skipping - This is not ready for testing ");
	}

	@AfterMethod
	public void getResult(ITestResult result) throws Exception{
		extent.endTest(logger);
		//driver.close();

	}

	@Test
	public void testApp() throws Exception {

		driver.get("http://automate-apps.com/select-multiple-options-from-a-drop-down-list/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement selectList = driver.findElement(By.xpath("//select[@name='cars']"));
		Select select = new Select(selectList);
		select.selectByVisibleText("Maruti");
		select.selectByVisibleText("Honda");
		logPassExtent("Multi Selection","Multi_Selection");
	}

	@AfterTest
	public void endReport(){
		// writing everything to document
		//flush() - to write or update test information to your report. 
		extent.flush();
		//Call close() at the very end of your session to clear all resources. 
		//If any of your test ended abruptly causing any side-affects (not all logs sent to ExtentReports, information missing), this method will ensure that the test is still appended to the report with a warning message.
		//You should call close() only once, at the very end (in @AfterSuite for example) as it closes the underlying stream. 
		//Once this method is called, calling any Extent method will throw an error.
		//close() - To close all the operation
		extent.close();
		driver.quit();
	}


}