package testSuite;

import java.util.List;
import java.util.Map;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import common.BaseClass;
import common.ExtentLogging;
import common.GetPropertyValues;
import common.ListenersClass;

@Listeners(ListenersClass.class)
public class PropertiesTest extends BaseClass {
	GetPropertyValues prop = new GetPropertyValues();
	@Test
	public void printOnlyValues() {
		List<String> keys= prop.getAllKeys();
		keys.forEach(value -> 	ExtentLogging.logPassExtent(prop.getPropValues(value)));
	}
	
	@Test
	public void printKeyValues() {
		Map<String,String> map = prop.getAllKeyValues();
		map.forEach((key,value) -> ExtentLogging.logPassExtent(key+" :"+value) );
	}
	
	@Test
	public void printOnlyKeys() {
		List<String> keys= prop.getAllKeys();
		keys.forEach(key -> ExtentLogging.logPassExtent(key));
	}
}
