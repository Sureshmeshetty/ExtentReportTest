package testSuite;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import common.BaseClass;
import common.ExtentLogging;
import common.ListenersClass;

@Listeners(ListenersClass.class)
public class ExtendReportTest extends BaseClass {

	@Test
	public void passTest() {
		ExtentLogging.logPassExtent("This is My Test case");
		Assert.assertTrue(true);
		// To generate the log when the test case is passed
		ExtentLogging.logPassExtent("Test Case Passed is passTest");
	}

	/**
	 * My intention is to fail this method If this method fails, then it goes it
	 * will call ListenersClass.onTestFailure() Calls the BaseClass.getScreenshot()
	 * method and captures a screenshot and place it in the extent reports
	 */
	@Test
	public void failTest() {
		ExtentLogging.logPassExtent("Chrome Driver is launched");
		driver.get("https://chromedriver.chromium.org/downloads");
		String currentURL = driver.getTitle();
		Assert.assertEquals(currentURL, "NoTitle");
		ExtentLogging.logPassExtent("Test Case (failTest) Status is passed");
	}

	@Test
	public void skipTest() {
		throw new SkipException("Skipping - This is not ready for testing ");
	}

	@Test
	public void testApp() throws Exception {

		driver.navigate().to("http://automate-apps.com/select-multiple-options-from-a-drop-down-list/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement selectList = driver.findElement(By.xpath("//select[@name='cars']"));
		Select select = new Select(selectList);
		select.selectByVisibleText("Maruti");
		select.selectByVisibleText("Honda");
		ExtentLogging.logPassExtent("Multi Selection", "Multi_Selection");
	}
}