package utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConfigFileReader {
	private  Properties properties;
	private final String propertyFilePath = Paths.get(System.getProperty("user.dir"), "src", "test", "resources", "configs", "config.properties").toString();
	public Logger logger = LogManager.getLogger("AppLogger");

	public ConfigFileReader(){
		BufferedReader reader;
		try {
			logger.info("Reading configurations from path : {}",propertyFilePath);
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
	
	public String getBrowserName(){
		String browserName = properties.getProperty("browser");
		if(browserName!= null) 
			return browserName;
		else 
			throw new RuntimeException("BrowserName not specified in the config.properties file.");		
	}
	
	public String getOSName() {
		String driverName = properties.getProperty("os_environment");
		if(driverName!=null)
			return driverName;
		else
			throw new RuntimeException("OS Environment not configured in config.properties file");
	}
	
	public String getLoginUrl() {
		String loginUrl = properties.getProperty("login_url");
		if(loginUrl!= null) 
			return loginUrl;
		else 
			throw new RuntimeException("Login Url not configured in the config.properties file.");
	}
	
	public String getMysqlDriverName() {
		String driverName = properties.getProperty("mysql_driver_name");
		if(driverName!=null)
			return driverName;
		else
			throw new RuntimeException("Mysql Driver Name not configured in config.properties file");
	}
	
	public String getMysqlConnectionURL() {
		String connectionURL = properties.getProperty("mysql_connection_url");
		if(connectionURL != null)
			return connectionURL;
		else
			throw new RuntimeException("Mysql Connection URL not configured in config.properties file");
	}
	
	public String getMysqlUserName() {
		String userName = properties.getProperty("mysql_user_name");
		if(userName != null)
			return userName;
		else
			throw new RuntimeException("Mysql User Name not configured in config.properties file");
	}
	
	public String getMysqlPassword() {
		String password = properties.getProperty("mysql_password");
		if(password != null)
			return password;
		else
			throw new RuntimeException("Mysql Password not configured in config.properties file");
	}

	public String getLoginUserName() {
		String userName = properties.getProperty("login_user_name");
		if(userName != null)
			return userName;
		else
			throw new RuntimeException("Login User Name not configured in config.properties file");
	}
	
	public String getLoginPassword() {
		String password = properties.getProperty("login_password");
		if(password != null)
			return password;
		else
			throw new RuntimeException("Login Password not configured in config.properties file");
	}
	
	public String getSelectionDatesPageHeading() {
		String headerText = properties.getProperty("selection_dates_header_text");
		if(headerText!=null)
			return headerText;
		else
			throw new RuntimeException("Selection Dates Header Text not configured in config.properties file");
	}
	
	public String getSelectionsPageHeading() {
		String headerText = properties.getProperty("selections_header_text");
		if(headerText!=null)
			return headerText;
		else
			throw new RuntimeException("Selection Dates Header Text not configured in config.properties file");
	}
	
	public String getSelectionDatesNewAdditionHeading() {
		String headerText = properties.getProperty("selectionDates.new.header");
		if(headerText!=null)
			return headerText;
		else
			throw new RuntimeException("Selection Dates New Header Text not configured in config.properties file");
	}
	
	public ArrayList<String> getMatIconHeaderData() {
		String headerText = properties.getProperty("mat.icon.data");
		if(headerText!=null) {
			String[] headerArray = headerText.split(",");
			ArrayList<String> listOfHeaders = new ArrayList<>();
			for(String str : headerArray)
				listOfHeaders.add(str);
			return listOfHeaders;
		}
		else
			throw new RuntimeException("Selection Dates New Header Text not configured in config.properties file");
	}
	
	
}
