package com.GA.ActivePlugins;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.wp.genericLib.ConfigFileReader;
import com.wp.genericLib.Constant;
import com.wp.genericLib.Driver;
import com.wp.pageObjectRepo.GlobalFunctions;

public class BHC_VerifyActivePlugins extends Driver {
	//Initialize object using page factory method 
	ConfigFileReader configFileReader = new ConfigFileReader();

	public Logger APPLICATION_LOGS = LogManager.getLogger("AppLogger");
	String environment=configFileReader.getEnvironment();
	//Test Data
	String url= Constant.BHC_HomePage+"wp-json/plugins/v1/inactive/"; 
	String propertyFilePath="";

	//Plugin API locators
	private By apiLocator= By.xpath("//pre");

	@BeforeClass
	public void navigateToRequiredURL() throws Exception{
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Step:1 Navigate required URL  
		double doublerand = Math.random(); 
		url= url+"?a="+doublerand;
		globalPage.navigateToRequiredSite(url);
		Thread.sleep(5000);

		//Test Data:Get old plugins details from text file
		if(environment.equalsIgnoreCase("production")) {
			propertyFilePath = Paths.get(System.getProperty("user.dir"), "src", "test", "resources", "configs", "plugins_BHC.txt").toString();
		}
		else if (environment.equalsIgnoreCase("staging")) {
			propertyFilePath = Paths.get(System.getProperty("user.dir"), "src", "test", "resources", "configs", "plugins_preprod_BHC.txt").toString();
		}
	}

	@BeforeMethod
	public void nameBefore(Method method)
	{
		System.out.println("********************************************************************************************");
		System.out.println("Test : " + method.getName() + " execution started ..................."); 

	}

	@Test
	public void verify_BHC_ActivePlugins() throws IOException, InterruptedException {
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		List<String> expPlugins = Collections.emptyList();
		try {
			expPlugins = Files.readAllLines(Paths.get(propertyFilePath), StandardCharsets.UTF_8);
		}
		catch (IOException e) { 
			// TODO Auto-generated catch block e.printStackTrace(); 
			System.out.println("Not able read the text file");
		}

		//Get the Active plugins from API
		String pluginsFromAPI= driver.findElement(apiLocator).getText();
		List<String> actualPlugins= globalPage.getActivePluginsFromAPI(pluginsFromAPI);

		//Verify Active Plugins with expected Plugins 
		globalPage.verifyActivePugins(expPlugins, actualPlugins, url);
	}

	@Test(dependsOnMethods = {"verify_BHC_ActivePlugins"}, alwaysRun = true)
	public void capturePluginDetails() throws IOException, InterruptedException, InvalidFormatException {
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);
		//Get the Active plugins from API
		String pluginsFromAPI= driver.findElement(apiLocator).getText();
		List<String> actualPlugins= globalPage.getActivePluginsFromAPI(pluginsFromAPI);
		FileWriter writer = new FileWriter(propertyFilePath); 
		for(String str: actualPlugins) {
			writer.write(str + System.lineSeparator());
		}
		writer.close();

	}
}
