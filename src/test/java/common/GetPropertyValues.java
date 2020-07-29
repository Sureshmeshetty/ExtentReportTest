package common;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

public class GetPropertyValues extends BaseClass{

	public String propFileName = null;
	Properties prop = new Properties();

	public String getPropValue(String key) {
		try {
			if (key != null) {
				propFileName = "testing.properties";
				InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
				if (inputStream == null) {
					throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
				}
				prop.load(inputStream);
				return prop.getProperty(key);
			} else {
				ExtentLogging.logFailExtent("Config Error: One of the config parameter/property is given null");
			}
		} catch (Exception e) {
			ExtentLogging.logFailExtent("Config Error: Check the given Config property values - Either the key doesn't exist or mistyped");
			System.exit(0);
		}
		prop.clear();
		return null;
	}

	public List<String> getAllKeys()
	{
		try {
			propFileName = "testing.properties";
			InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
			if (inputStream == null) {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}
			prop.load(inputStream);
			return prop.keySet().stream() 
                    .map(String::valueOf) 
                    .collect(Collectors.toList());
		} catch (Exception e) {
			System.exit(0);
		}
		prop.clear();
		return null;
	}
	
	public Map<String,String> getAllKeyValues()
	{
		
		try {
			propFileName = "testing.properties";
			InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
			if (inputStream == null) {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}
			prop.load(inputStream);
			
			Map<String, String> map = new HashMap<>();
			prop.forEach( (key, value) -> {map.put( (String)key, (String)value );} );
			return map;
		} catch (Exception e) {
			System.exit(0);
		}
		prop.clear();
		return null;
	}
}