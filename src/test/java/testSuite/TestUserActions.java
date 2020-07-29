package testSuite;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import common.BaseClass;
import common.ListenersClass;
import common.UserActions;

@Listeners(ListenersClass.class)
public class TestUserActions extends BaseClass{

	@Test
	public void EditTest()
	{
		driver.get("https://commonag-isihelpdesk-dit.nj.adp.com/revadm/strong/theme.faces");
		//WebElement element = UserActions.findElement(By.id("login-form_username"));
		UserActions.safeEdit(By.id("login-form_username"),"Suresh");
	}
	
	@Test
	public void clickTest()
	{
		driver.get("https://commonag-isihelpdesk-dit.nj.adp.com/revadm/strong/theme.faces");
		WebElement element = UserActions.findElement(By.id("verifUseridBtn"));
		UserActions.safeClick(element);
	}
}
