package common;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class BaseClass {
	public static WebDriver driver;
	public static ExtentReports extent;
	public static ExtentTest logger;
	public static GetPropertyValues _prop = new GetPropertyValues();
	//public static String methodName = null;
	
	/**
	 * <b>ExtentReports(String filePath,Boolean replaceExisting)</b> </br>
	 * 		<b>filepath</b> - path of the file, in .htm or .html format - path where your report needs to generate. </br>
	 * 		<b>replaceExisting</b> - Setting to overwrite (TRUE) the existing file or append to it </br>
	 * 			True (default): the file will be replaced with brand new markup, and all existing data will be lost. Use this option to create a brand new report </br>
	 * 			False: existing data will remain, new tests will be appended to the existing report. If the the supplied path does not exist, a new file will be created. </br>
	 * 
	 * <b>extent.loadConfig()</b> </br>
	 * 		loading the external xml file (i.e., extent-config.xml) which was placed under the test >> resources directory </br>
	 * 		You could find the xml file below. Create xml file in your project and copy past the code mentioned below </br>
	 */
	@BeforeSuite
	public void startReport(){
		System.out.println("Extent Report Location: "+System.getProperty("user.dir") +"/AutomationReport/ExtentReport.html");
		extent = new ExtentReports (System.getProperty("user.dir") +"/AutomationReport/ExtentReport.html", true);
		extent
		.addSystemInfo("Application Name","Test Application")
		.addSystemInfo("User Name", System.getProperty("user.name"))
		.addSystemInfo("Host Name", "Automation Testing")
		.addSystemInfo("Environment", "QA");
		extent.loadConfig(new File(System.getProperty("user.dir")+"\\src\\test\\resources\\extent-config.xml"));
		
		driver = InitializeDriver();
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(_prop.getPropValue("SHORT_WAIT")), TimeUnit.SECONDS);
	}
	
	/**
	 * <b>options.setPageLoadStrategy(PageLoadStrategy.NORMAL);</b> // NONE,EAGER >> Defines the page load strategy </br>
	 * <b>options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});</b> // To disable the message "Chrome is being controlled by automated test software" notification </br>
	 * <b>options.addArguments("start-maximized");</b> // To Maximize the browser window </br>
	 * <b>options.addArguments("--headless");</b> //To Run without launching the browser </br>
	 * <b>System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+".\\chromedriver.exe");</b> // Setting the driver path </br>
	 * <b>System.setProperty("webdriver.chrome.silentOutput", "true");</b> // To print the unnecessary lines
	 */
	
	public WebDriver InitializeDriver() {
		String browserName = _prop.getPropValue("BROWSER_NAME");
		if(browserName.equals("Chrome"))
		{
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
			options.addArguments("start-maximized");
			System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\src\\test\\resources\\chromedriver.exe");
			System.setProperty("webdriver.chrome.silentOutput", "true");
			driver =  new ChromeDriver(options);
		}
		else if(browserName.equals("FireFox")) {
			
		}
		else if(browserName.equals("IE")) {
			
		}
		else if(browserName.equals("Opera")) {
			
		}
		
		return driver;
	}
	
	@AfterMethod
	public void endTest()
	{
		extent.endTest(logger);
	}
	/**
	 * <b>extent.flush();</b></br>
	 * 		writing everything to document</br>
	 * 		<b>flush()</b> - to write or update test information to your report.</br>
	 * <b>extent.flush();</b></br>
	 * 		Call <b>close()</b> at the very end of your session to clear all resources.</br>
	 * 		If any of your test ended abruptly causing any side-affects (not all logs sent to ExtentReports, information missing), this method will ensure that the test is still appended to the report with a warning message.</br>
	 * 		You should call close() only once, at the very end (in @AfterSuite for example) as it closes the underlying stream.</br>
	 * 		Once this method is called, calling any Extent method will throw an error.</br>
	 * 		<b>close()</b> - To close all the operation</br>
	 */
	@AfterTest
	public void endReport(){
		extent.flush();
		extent.close();
		driver.quit();
	}
}
