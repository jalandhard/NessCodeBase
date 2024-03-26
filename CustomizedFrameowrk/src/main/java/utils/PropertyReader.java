package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {
	
	static Properties properties;
	static CustomLogging logger = new CustomLogging();
	
	public PropertyReader() {
		loadAllProperties();
	}
	
	private void loadAllProperties() {
		System.out.println("Inside Loading Properties Method!!!!!");
		properties = new Properties();
		
		try {
			String fileName = System.getProperty("user.dir")+"/src/main/resources/prod_config.propperties";
			properties.load(new FileInputStream(fileName));
		} catch (IOException e) {
			throw new RuntimeException("Not able to read the properties file");
		}
	}
	
	public static String readItem(String propertyName) {
		logger.info("Trying to read data from property file for property name : " + propertyName);
		return properties.getProperty(propertyName);
	}

}
