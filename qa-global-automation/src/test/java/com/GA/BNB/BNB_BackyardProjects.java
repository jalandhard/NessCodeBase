package com.GA.BNB;

import java.io.IOException;
import java.lang.reflect.Method;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.wp.genericLib.ConfigFileReader;
import com.wp.genericLib.Constant;
import com.wp.genericLib.Driver;
import com.wp.pageObjectRepo.GlobalFunctions;

public class BNB_BackyardProjects extends Driver {
	//Initialize object using page factory method 
	ConfigFileReader configFileReader;

	public Logger APPLICATION_LOGS = LogManager.getLogger("AppLogger");

	//Test Data
	String url=Constant.BNB_BackyardProjects;
	String newspapaerSignUpLink = Constant.BNB_NewspaperSignUpLink;

	private By featuredImg = By.xpath("//div[@class='featured-image']");
	private By socialShareLinks = By.xpath("//div[@class='social-share social-menu-desktop']//ul[@class='pure-menu-list social-menu']//a");
	
	private By newsletterSignupInputField =   By.xpath("//div[@class='col-2 newsletter-signup']//input");
	private By newsletterSignupButton =By.xpath("//div[@class='col-2 newsletter-signup']//a");
	private By materialsReq = By.className("materials");
	private By projectSteps = By.className("step-content");


	@BeforeClass
	public void navigateToRequiredURL() throws Exception{
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Step:1 Navigate required URL
		globalPage.navigateToRequiredSite(url);	


	}	
	@BeforeMethod
	public void nameBefore(Method method)
	{
		System.out.println("********************************************************************************************");
		System.out.println("Test : " + method.getName() + " execution started ...................");      

	}
	
	@Test
	public void verify_Banner_BNB_BackyardProjects() throws IOException, InterruptedException{

		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);
		//Test Step
		globalPage.verifyIsBannerAvailable(By.xpath("//div[@id='desktop-nav-banner']//a"), url);
	}

	@Test
	public void verify_BreadCrumb_BNB_BackyardProjects() throws IOException, InterruptedException {
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Step 2 Verify Amazon Header Bidder is Running
		globalPage.verifyIsBreadCrumbAvailable(Constant.breadcrumbLocator, url);
	}

	@Test(dependsOnMethods = {"verify_BreadCrumb_BNB_BackyardProjects"}, alwaysRun = true)
	public void verifyTitle_BNB_BackyardProjects() throws IOException, InterruptedException {
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);
		String element = "Title";
		//Test Step
		//Step 2 Verify Amazon Header Bidder is Running
		globalPage.verifyIsElementAvailable(Constant.articleTitleLocator, url, element);
	}

	@Test(dependsOnMethods = {"verifyTitle_BNB_BackyardProjects"}, alwaysRun = true)
	public void verifyLineTest_BNB_BackyardProjects() throws InterruptedException, IOException{
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step
		//Step 2 Verify Amazon Header Bidder is Running
		globalPage.isLineExist(url, Constant.avatarLocator, Constant.authorLocator);
	}


	@Test(dependsOnMethods = {"verifyLineTest_BNB_BackyardProjects"}, alwaysRun = true)
	public void verifyDekTest_BNB_BackyardProjects() throws IOException, InterruptedException {

		String dekTest = "Dek";
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step
		//Step 2 Verify Amazon Header Bidder is Running
		globalPage.IsElementExist(url, Constant.dekLocator, dekTest);
	}

	@Test(dependsOnMethods = {"verifyDekTest_BNB_BackyardProjects"}, alwaysRun = true)
	public void verifyTaboolaSidebarTest_BNB_BackyardProjects() throws InterruptedException, IOException {
		String taboolaText = "Taboola Sidebar ";
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step
		//Step 2 Verify Amazon Header Bidder is Running
		globalPage.IsElementExist(url, Constant.dekLocator, taboolaText);
	}

	@Test(dependsOnMethods = {"verifyTaboolaSidebarTest_BNB_BackyardProjects"}, alwaysRun = true)
	public void verify_SidebarAdWrapper_BNB_BackyardProjects() throws InterruptedException, IOException {
		String taboolaText = "Sidebar Ad wrapper ";
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step
		//Step 2 Verify Amazon Header Bidder is Running
		globalPage.IsElementExist(url, Constant.sidebarClass, taboolaText);
	}
	@Test(dependsOnMethods = {"verify_SidebarAdWrapper_BNB_BackyardProjects"}, alwaysRun = true)
	public void verifySocialShareLinks_BNB_BackyardProjects() throws IOException, InterruptedException
	{
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step
		//Step 2 Verify Amazon Header Bidder is Running
		globalPage.socialShareNavigation(Constant.dekLocator, socialShareLinks, url);
	}
	
	@Test(dependsOnMethods = {"verifySocialShareLinks_BNB_BackyardProjects"}, alwaysRun = true)
	public void verify_MaterialsRequired_BNB_BackyardProjects() throws InterruptedException, IOException {

		String element = "Materials Required panel  ";
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step
		//Step 2 Verify Amazon Header Bidder is Running
		globalPage.verifyIsElementAvailable(materialsReq, url, element);
	}

	@Test(dependsOnMethods = {"verify_MaterialsRequired_BNB_BackyardProjects"}, alwaysRun = true)
	public void verify_ToolsAndInstructionsContainer_BNB_BackyardProjects() throws InterruptedException, IOException {

		String element = "Project Step By step Container ";
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step
		//Step 2 Verify Amazon Header Bidder is Running
		globalPage.verifyIsElementAvailable(projectSteps, url, element);

	}

	@Test(dependsOnMethods = {"verify_ToolsAndInstructionsContainer_BNB_BackyardProjects"}, alwaysRun = true)
	public void newsletterSignUpTest_BNB_BackyardProjects() throws IOException, InterruptedException
	{
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);


		//Test Step
		globalPage.verifyNewsletterSignUp(url, newsletterSignupInputField, newsletterSignupButton, newspapaerSignUpLink );

	}	

	@AfterMethod
	public void nameAfter(Method method)
	{
		System.out.println("Test : " + method.getName() + " execution completed .................. \n");   
		System.out.println("********************************************************************************************");
	}
}
