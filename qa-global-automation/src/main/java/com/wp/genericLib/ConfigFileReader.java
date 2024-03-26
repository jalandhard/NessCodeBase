package com.wp.genericLib;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;

/**
 * @author maheshgouda  
 *
 */
public final class ConfigFileReader {
	private  Properties properties;
	private final String propertyFilePath = Paths.get(System.getProperty("user.dir"), "src", "test", "resources", "configs", "configuration.properties").toString();

	public ConfigFileReader(){
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(propertyFilePath));
			properties = new Properties();
			try {
				properties.load(reader);
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
		}		
	}

	public String getDriverPath(){
		String driverPath = properties.getProperty("url");
		if(driverPath!= null) return driverPath;
		else throw new RuntimeException("driverPath not specified in the Configuration.properties file.");		
	}

	public String getBrowserName(){
		String driverPath = properties.getProperty("browser");
		if(driverPath!= null) return driverPath;
		else throw new RuntimeException("BrowserName not specified in the Configuration.properties file.");		
	}

	public String getcreditCardNumber(){
		String creditCardNumber = properties.getProperty("creditCardNumber");
		if(creditCardNumber!= null) return creditCardNumber;
		else throw new RuntimeException("creditCardNumber not specified in the Configuration.properties file.");		
	}
	
	public String getCMS_Username(){
		String creditCardNumber = properties.getProperty("CMS_username");
		if(creditCardNumber!= null) return creditCardNumber;
		else throw new RuntimeException("CMS_username not specified in the Configuration.properties file.");		
	}
	
	public String getCMS_Password(){
		String creditCardNumber = properties.getProperty("CMS_Password");
		if(creditCardNumber!= null) return creditCardNumber;
		else throw new RuntimeException("CMS_Password not specified in the Configuration.properties file.");		
	}
	
	public String getEnvironment(){
		String environment = properties.getProperty("environment");
		if(environment!= null) return environment;
		else throw new RuntimeException("Environment not specified in the Configuration.properties file.");		
	}


}