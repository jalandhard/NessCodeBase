package com.wp.pageObjectRepo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.wp.genericLib.ConfigFileReader;
import com.wp.genericLib.WebDriverCommonLib;




public class ConfigTables {
	WebDriver driver;
	ConfigFileReader configFileReader;

	public Logger APPLICATION_LOGS = LogManager.getLogger("AppLogger");
	String linkCode="";
	String functionalitySummary="";


	public ConfigTables(WebDriver driver){ 
		this.driver=driver; 
	}
	//List of locators in BookingDetails Page 



	//Common methods for RMS3.0 Home page
	public void loginRMSApp() throws IOException, InterruptedException{
		configFileReader= new ConfigFileReader();
		String url= configFileReader.getDriverPath();		
		System.out.println("url: "+url);
		APPLICATION_LOGS.debug("url: "+url);
		driver.get(url);
		WebDriverCommonLib.waitForPageToBeLoad(driver);

		//Microsoft Login 
		WebDriverCommonLib.waitForIdPresent(driver, "i0116");
		driver.findElement(By.id("i0116")).sendKeys("mpatil@rdigest.com");
		driver.findElement(By.id("idSIButton9")).click();
		WebDriverCommonLib.waitForIdPresent(driver, "i0118");
		driver.findElement(By.id("i0118")).sendKeys("Winter@2019");
		driver.findElement(By.id("idSIButton9")).click();
		WebDriverCommonLib.waitForIdPresent(driver, "idSIButton9");
		driver.findElement(By.id("idSIButton9")).click();
		WebDriverCommonLib.waitForPageToBeLoad(driver);


		//Verify RMS3.0 logo
		WebDriverCommonLib.waitForXpathPresent(driver,"//a[@class='navbar-brand']");
		Thread.sleep(1000);


	}

	public void navigateToRMSHomePage() throws IOException{
		driver.navigate().refresh();
		try{
			WebDriverCommonLib.waitForXpathPresent(driver, "//a[@class='navbar-brand']");
			//driver.findElement(By.xpath("//a[@class='navbar-brand']")).sendKeys(Keys.F5);		
		}
		catch(Exception e){
			WebDriverCommonLib.getScreenShot(driver, "Error in navigateToRMSHomePage");
			Assert.assertEquals(false, true, "The RMS 3.0 home page not loaded");
		}

		WebDriverCommonLib.waitForPageToBeLoad(driver);


	}
	public void openRequiredConfigTable(String mainTableName, String subTableName) throws IOException{
		//Click on Config Table link
		WebDriverCommonLib.waitForXpathPresent(driver,"//a[@href='/ConfigurationTables']");
		driver.findElement(By.xpath("//a[@href='/ConfigurationTables']")).click();
		WebDriverCommonLib.waitForPageToBeLoad(driver);


		driver.findElement(By.id("dropdownConfigLink")).click();
		WebDriverCommonLib.waitForXpathPresent(driver,"//div[@class='dropdown-menu show']");
		try{
			driver.findElement(By.xpath("//a[text()='"+mainTableName+"']")).click();
			WebDriverCommonLib.waitForPageToBeLoad(driver);
		}
		catch(Exception e){
			WebDriverCommonLib.getScreenShot(driver, "Error in openRequiredConfigTable");	
			Assert.assertEquals(false, true, "The "+mainTableName+ " table is not loaded in UI ");

		}
		if(mainTableName.equals(subTableName)){
			System.out.println("mainTableName and subTableName is same ");
			APPLICATION_LOGS.debug("mainTableName and subTableName is same ");
		}
		else{
			try{
				WebDriverCommonLib.waitForXpathPresent(driver,"//a[text()='"+subTableName+"']");
				driver.findElement(By.xpath("//a[text()='"+subTableName+"']")).click();
				WebDriverCommonLib.waitForPageToBeLoad(driver);
			}
			catch(Exception e){
				WebDriverCommonLib.getScreenShot(driver, "Error in openRequiredConfigTable");	
				Assert.assertEquals(false, true, "The "+subTableName+ " table is not loaded in UI ");

			}
		}
	}

	public void openRequiredConfigTable(String mainTableName){
		//Click on Config Table link
		WebDriverCommonLib.waitForXpathPresent(driver,"//a[@href='/ConfigurationTables']");
		driver.findElement(By.xpath("//a[@href='/ConfigurationTables']")).click();
		WebDriverCommonLib.waitForPageToBeLoad(driver);


		driver.findElement(By.id("dropdownConfigLink")).click();
		WebDriverCommonLib.waitForXpathPresent(driver,"//div[@class='dropdown-menu show']");
		driver.findElement(By.xpath("//a[text()='"+mainTableName+"']")).click();
		WebDriverCommonLib.waitForPageToBeLoad(driver);

	}

	public void verifyDefaultcolumnsInConfigTable(List<String> expectedColumn) throws IOException{

		System.out.println("Expected Column from Test Data:"+ expectedColumn);
		APPLICATION_LOGS.debug("Expected Column from Test Data:"+ expectedColumn);
		List<WebElement> actualColoumnlist= driver.findElements(By.xpath("//th[@class='k-header k-with-icon']"));
		List<String> actual = new ArrayList<String>(); 


		for (int i=0;i<=actualColoumnlist.size()-1;i++){
			boolean status=	actualColoumnlist.get(i).getAttribute("style").isEmpty();

			if(status==true){
				String defaultColumn= actualColoumnlist.get(i).getText();			
				actual.add(defaultColumn);

			}

		}
		System.out.println("Actual Column from UI is:"+ actual);
		APPLICATION_LOGS.debug("Actual Column from UI is:"+ actual);
		if(expectedColumn.equals(actual)){
			System.out.println("The actual Column is match with expected columns ");
			APPLICATION_LOGS.debug("The actual Column is match with expected columns");
		}
		else{
			WebDriverCommonLib.getScreenShot(driver, "Column_Missmatch");
			System.out.println("The actual Column is not matched with expected columns. Expected column= "+expectedColumn+" And actual column= "+actual);
			APPLICATION_LOGS.debug("The actual Column is not matched with expected columns. Expected column= "+expectedColumn+" And actual column= "+actual);
			Assert.assertEquals(expectedColumn, actual, "The actual Column is not matched with expected columns. Expected column= "+expectedColumn+" And actual column= "+actual);
		}
	}

	public void VerifyAddFunctionalityForLinkCodesTable(String linkCode, String functionalitySummary ) throws InterruptedException{
		//Test Data:		
		this.linkCode= linkCode;		
		this.functionalitySummary=functionalitySummary;

		//Click on Add New Button
		driver.findElement(By.xpath("//button[text()='Add New']")).click();
		WebDriverCommonLib.waitForIdPresent(driver, "IngredientLinkCodeDescription");
		//Enter text in Link Code Field
		driver.findElement(By.id("IngredientLinkCodeDescription")).sendKeys(linkCode);
		//Enter text in FunctionalitySummary field
		driver.findElement(By.id("FunctionalitySummary")).sendKeys(functionalitySummary);
		//Click on Update button
		driver.findElement(By.xpath("//a[@class='k-button k-button-icontext k-primary k-grid-update']")).click();
		//Verify success message
		boolean successMsg=false;
		try{
			successMsg= driver.findElement(By.xpath("//h3[text()='Link Code added successfully']")).isDisplayed();
		}
		catch(Exception e){
			APPLICATION_LOGS.debug("Link Code added successfully message not displayed");
			System.out.println("Link Code added successfully message not displayed");
		}
		if (successMsg==true){
			APPLICATION_LOGS.debug("Link Code added successfully message displayed");
			System.out.println("Link Code added successfully message displayed");
		}
		else{
			APPLICATION_LOGS.debug("Link Code added successfully message not displayed");
			System.out.println("Link Code added successfully message not displayed");
			Assert.assertEquals(successMsg, false, "Link Code added successfully message not displayed");
		}


	}

	public void verifySearchFunctionalityForLinkCodesTable(String linkCode){
		//Enter Link Code text in to Global Search field
		driver.findElement(By.id("searchBox")).clear();
		driver.findElement(By.id("searchBox")).sendKeys(linkCode);
		//Verify the text is searched or not
		try{
			WebDriverCommonLib.waitForTextPresent(driver, linkCode);
		}
		catch(Exception e){
			Assert.assertEquals(true, false, "Search Link Code not working");

		}
		APPLICATION_LOGS.debug("Link code searched successfully");
		System.out.println("Link code searched successfully");


	}

	public void verifyEditFunctionalityForLinkCodesTable(String linkCode, String linkCodeEdit, String functionalitySummary, String functionalitySummaryEdit) throws InterruptedException{
		//Click on Edit button
		Thread.sleep(5000);
		driver.findElement(By.xpath("//a[@class='k-button k-button-icontext k-grid-edit']")).click();
		WebDriverCommonLib.waitForIdPresent(driver, "IngredientLinkCodeDescription");

		//Perform Edit for Link Code and functionality Summary fields
		driver.findElement(By.id("IngredientLinkCodeDescription")).clear();
		Thread.sleep(1000);
		driver.findElement(By.id("IngredientLinkCodeDescription")).sendKeys(linkCodeEdit);
		Thread.sleep(2000);
		driver.findElement(By.id("FunctionalitySummary")).clear();
		Thread.sleep(1000);
		driver.findElement(By.id("FunctionalitySummary")).sendKeys(linkCodeEdit);
		//Click on Update Button
		driver.findElement(By.xpath("//a[@class='k-button k-button-icontext k-primary k-grid-update']")).click();

		//Verify success message
		boolean successMsg=false;
		try{
			successMsg= driver.findElement(By.xpath("//h3[text()='Link Code updated successfully']")).isDisplayed();
		}
		catch(Exception e){
			APPLICATION_LOGS.debug("Link Code updated successfully message not displayed");
			System.out.println("Link Code updated successfully message not displayed");
		}
		if (successMsg==true){
			APPLICATION_LOGS.debug("Link Code updated successfully message displayed");
			System.out.println("Link Code updated successfully message displayed");
		}
		else{
			APPLICATION_LOGS.debug("Link Code updated successfully message not displayed");
			System.out.println("Link Code updated successfully message not displayed");
			Assert.assertEquals(successMsg, false, "Link Code updated successfully message not displayed");
		}
		//Verify the updated value can be search in to grid
		verifySearchFunctionalityForLinkCodesTable(linkCodeEdit);
		Thread.sleep(5000);


	}


}


