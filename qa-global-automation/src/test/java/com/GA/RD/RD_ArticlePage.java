package com.GA.RD;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

//import com.globalAutomation.misc.Link;
import com.wp.genericLib.ConfigFileReader;
import com.wp.genericLib.Constant;
import com.wp.genericLib.Driver;
import com.wp.genericLib.ExcelLibrary;
import com.wp.genericLib.Link;
import com.wp.genericLib.WebDriverCommonLib;
import com.wp.pageObjectRepo.GlobalFunctions;

public class RD_ArticlePage extends Driver{
	//Initialize object using page factory method 
	ConfigFileReader configFileReader;
	public Logger APPLICATION_LOGS = LogManager.getLogger("AppLogger");

	//Test Data
	String url=Constant.RD_ArticlePage;
	String newspapaerSignUpLink = Constant.RD_NewspaperSignUpLink;
	
	private By featuredImg = By.xpath("//div[@class='featured-image']");
	private By socialShareLinks = By.xpath("//div[@class='social-share social-menu-desktop']//ul[@class='pure-menu-list social-menu']//a");

	private By newsletterSignupButton =By.xpath("//div[@class='col-2 newsletter-signup']//a");   //div[@class='col-2 newsletter-signup']//button[@type='submit']");//By.xpath("//div[@id='ads-container-single']//button[@id='subscribe']"); //By.xpath("//button[@class='at-element-click-tracking']");  

	private By newsletterSignupInputField =   By.xpath("//div[@class='nf-field-element']//input[@type='email']"); //By.xpath("//div[@class='nl-container']//input[@id='email']");//By.xpath("//div[@id='ads-container-single']//input[@id='email']"); //By.xpath("//div[@class='col-2 newsletter-signup']//input[@name='EmailAddress']");  //By.xpath("//div[@id='ads-container-single']//input[@id='email']");

	
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
	/*@Test //(dependsOnMethods = {"verify_Banner_RD_HubPage"}, alwaysRun = true)
	public void verify_BreadCrumb_RD_ArticlePage() throws IOException, InterruptedException {
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);
		List<WebElement> allLinks = driver.findElements(Constant.breadcrumbLocator);
		String breadcrumbText = allLinks.get(allLinks.size()-1).getText();
		globalPage.verifyIsBreadCrumbAvailable(Constant.breadcrumbLocator, url);
	}
*/
	@Test//(dependsOnMethods = {"verify_BreadCrumb_RD_ArticlePage"}, alwaysRun = true)
	public void verifyTitle_RD_ArticlePage() throws IOException, InterruptedException {
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);
		String element = "Title";
		globalPage.verifyIsElementAvailable(Constant.articleTitleLocator, url, element);
	}

	@Test(dependsOnMethods = {"verifyTitle_RD_ArticlePage"}, alwaysRun = true)
	public void verifyLineTest_RD_ArticlePage() throws InterruptedException, IOException{
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);
		globalPage.isLineExist(url, Constant.avatarLocator, Constant.authorLocator);
	}
	
	
	@Test(dependsOnMethods = {"verifyLineTest_RD_ArticlePage"}, alwaysRun = true)
	public void verifyDekTest_RD_ArticlePage() throws IOException, InterruptedException {

		String dekTest = "Dek";
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);
		globalPage.IsElementExist(url, Constant.dekLocator, dekTest);
	}
		
	@Test(dependsOnMethods = {"verifyDekTest_RD_ArticlePage"}, alwaysRun = true)
	public void verifyTaboolaSidebarTest_RD_ArticlePage() throws InterruptedException, IOException {
		String taboolaText = "Taboola Sidebar ";
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);
		globalPage.IsElementExist(url, Constant.dekLocator, taboolaText);
	}
	
	@Test(dependsOnMethods = {"verifyTaboolaSidebarTest_RD_ArticlePage"}, alwaysRun = true)
	public void verify_SidebarAdWrapper_RD_ArticlePage() throws InterruptedException, IOException {
		String taboolaText = "Sidebar Ad wrapper ";
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);
		globalPage.IsElementExist(url, Constant.sidebarClass, taboolaText);
	}
	@Test(dependsOnMethods = {"verify_SidebarAdWrapper_RD_ArticlePage"}, alwaysRun = true)
	public void verifySocialShareLinks_RD_ArticlePage() throws IOException, InterruptedException
	{
			//Object Initialization
				GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);
				globalPage.socialShareNavigation(Constant.dekLocator, socialShareLinks, url);
	}

	@Test(dependsOnMethods = {"verifySocialShareLinks_RD_ArticlePage"}, alwaysRun = true)
	public void newsletterSignUpTest_RD_ArticlePage() throws IOException, InterruptedException
	{
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);
		globalPage.verifyNewsletterSignUp(url, newsletterSignupInputField, newsletterSignupButton, newspapaerSignUpLink );

	}

	/*@Test(dependsOnMethods = {"verifySocialShareLinks_RD_ArticlePage"}, alwaysRun = true)
	public void verifyFacebookLink_RD_ArticlePage() throws IOException, InterruptedException {
		String facebookLink = "facebook.com";

		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step
		//Step 2 Verify Amazon Header Bidder is Running
		globalPage.Test(url, Constant.dekLocator, Constant.facebookIconLocator,facebookLink );
	}


	@Test(dependsOnMethods = {"verifyFacebookLink_RD_ArticlePage"}, alwaysRun = true)
	public void verifyTwitterLink_RD_ArticlePage() throws InterruptedException, IOException {

		String twitterLink = "twitter.com";
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step
		//Step 2 Verify Amazon Header Bidder is Running
		globalPage.Test(url, Constant.dekLocator, Constant.twitterIconLocator,twitterLink );	

	}

	@Test(dependsOnMethods = {"verifyTwitterLink_RD_ArticlePage"}, alwaysRun = true)
	public void verifyPinteresttLink_RD_ArticlePage() throws InterruptedException, IOException {

		String pinterestLink = "pinterest.com";
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step
		//Step 2 Verify Amazon Header Bidder is Running
		globalPage.Test(url, Constant.dekLocator, Constant.pinterestIconLocator,pinterestLink );	

	}
*/

	@AfterMethod
	public void nameAfter(Method method)
	{
	    System.out.println("Test : " + method.getName() + " execution completed .................. \n");   
	    System.out.println("********************************************************************************************");
	}






}
