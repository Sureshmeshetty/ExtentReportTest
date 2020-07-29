package common;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class UserActions extends BaseClass {
	
	static WebDriverWait wait = new WebDriverWait(driver, Integer.parseInt(_prop.getPropValue("SHORT_WAIT")));
	
	public static boolean waitforElementToBeVisible(WebElement element)
	{
		try {
			wait.until(ExpectedConditions.visibilityOf(element));
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	public static boolean waitforElementToBeVisible(By locator)
	{
		try {
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	public static boolean waitforElementToBeClickable(WebElement element)
	{
		String locator = "By."+element.toString().split("->")[1].replace("]", "");
		try {
			if(waitforElementToBeVisible(element))
			{
				ExtentLogging.logPassExtent("Element Found With \""+locator.toString()+"\" Expression");
				wait.until(ExpectedConditions.elementToBeClickable(element));
				ExtentLogging.logPassExtent("Element is Clickable With \""+locator+"\" Expression" );
				return true;
			}
			else
				Assert.fail("Element <b><span style='color:red;'>NOT FOUND</span></b> With \""+locator+"\" Expression");
		}
		catch(Exception e)
		{
			Assert.fail("Element is <b><span style='color:red;'>NOT CLICKABLE</span></b> With \""+locator+"\" Expression");
		}
		
		return false;
	}
	
	public static boolean waitforElementToBeClickable(By locator)
	{
		try {
			if(waitforElementToBeVisible(locator))
			{
				ExtentLogging.logPassExtent("Element Found With \""+locator.toString()+"\" Expression");
				wait.until(ExpectedConditions.elementToBeClickable(locator));
				ExtentLogging.logPassExtent("Element is Clickable With \""+locator.toString()+"\" Expression" );
				return true;
			}
			else
				Assert.fail("Element <b><span style='color:red;'>NOT FOUND</span></b> With \""+locator.toString()+"\" Expression");
		}
		catch(Exception e)
		{
			Assert.fail("Element With \""+locator.toString()+"\" Expression is <b><span style='color:red;'>NOT CLICKABLE</span></b>");
		}
		return false;
	}
	
	public static WebElement findElement(By locator) {
		WebElement element = null;
		if(waitforElementToBeClickable(locator))
			return driver.findElement(locator);
		else
			Assert.fail("Element <b><span style='color:red;'>NOT FOUND</span></b> With: \""+locator.toString()+"\" Expression");
		return element;
	}
	
	public static List<WebElement> findElements(By locator) {
		List<WebElement> elements = null;
		if(waitforElementToBeVisible(locator))
		{
			ExtentLogging.logPassExtent("Element Found With \""+locator.toString()+"\" Expression");
			return driver.findElements(locator);
		}
		else
			Assert.fail("Element <b><span style='color:red;'>NOT FOUND</span></b> With \""+locator.toString()+"\" Expression");
		return elements;
	}
	
	public static void safeClick(By locator)
	{
		findElement(locator).click();
	}
	
	public static void safeClick(WebElement element)
	{
		if(waitforElementToBeClickable(element))
			element.click();
	}
	
	public static void safeEdit(By locator,String value)
	{
		findElement(locator).sendKeys(value);
	}
	
	public static void safeEdit(WebElement element,String value)
	{
		if(waitforElementToBeClickable(element))
			element.sendKeys(value);
	}

}