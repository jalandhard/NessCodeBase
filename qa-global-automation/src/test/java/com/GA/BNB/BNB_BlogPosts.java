package com.GA.BNB;

import java.io.IOException;
import java.lang.reflect.Method;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.wp.genericLib.ConfigFileReader;
import com.wp.genericLib.Constant;
import com.wp.genericLib.Driver;
import com.wp.pageObjectRepo.GlobalFunctions;

public class BNB_BlogPosts extends Driver {
	//Initialize object using page factory method 
	ConfigFileReader configFileReader;

	public Logger APPLICATION_LOGS = LogManager.getLogger("AppLogger");

	//Test Data
	String url=Constant.BNB_BlogPosts;
	String newspapaerSignUpLink = Constant.BNB_NewspaperSignUpLink;

	private By featuredImg = By.xpath("//div[@class='featured-image']");
	private By socialShareLinks = By.xpath("//div[@class='social-share social-menu-desktop']//ul[@class='pure-menu-list social-menu']//a");

	private By newsletterSignupButton =By.xpath("//div[@class='col-2 newsletter-signup']//a");//By.xpath("//div[@id='ads-container-single']//button[@id='subscribe']"); //By.xpath("//button[@class='at-element-click-tracking']");  

	private By newsletterSignupInputField =   By.xpath("//div[@class='in-content-nl-container']//input");//By.xpath("//div[@class='nf-field-element']//input[@type='email']"); //By.xpath("//div[@class='nl-container']//input[@id='email']");//By.xpath("//div[@id='ads-container-single']//input[@id='email']"); //By.xpath("//div[@class='col-2 newsletter-signup']//input[@name='EmailAddress']");  //By.xpath("//div[@id='ads-container-single']//input[@id='email']");
	private By banner = By.xpath("//div[@id='desktop-nav-banner']//a");

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
	public void verify_BreadCrumb_BNB_BlogPostsPage() throws IOException, InterruptedException {
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Step 2 Verify Amazon Header Bidder is Running
		globalPage.verifyIsBreadCrumbAvailable_BNB(Constant.breadcrumbLocator, url);
	}
	
	@Test(dependsOnMethods = {"verify_BreadCrumb_BNB_BlogPostsPage"}, alwaysRun = true)
	public void verify_Banner_BNB_BlogPosts() throws IOException, InterruptedException{

		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);
		//Test Step
		globalPage.verifyIsBannerAvailable(banner, url);
	}

	@Test(dependsOnMethods = {"verify_BreadCrumb_BNB_BlogPostsPage"}, alwaysRun = true)
	public void verifyTitle_BNB_BlogPostsPage() throws IOException, InterruptedException {
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);
		String element = "Title";
		//Test Step
		//Step 2 Verify Amazon Header Bidder is Running
		globalPage.verifyIsElementAvailable(Constant.articleTitleLocator, url, element);
	}

	@Test(dependsOnMethods = {"verifyTitle_BNB_BlogPostsPage"}, alwaysRun = true)
	public void verifyLineTest_BNB_BlogPostsPage() throws InterruptedException, IOException{
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step
		//Step 2 Verify Amazon Header Bidder is Running
		globalPage.isLineExist(url, Constant.avatarLocator, Constant.authorLocator);
	}


	@Test(dependsOnMethods = {"verifyLineTest_BNB_BlogPostsPage"}, alwaysRun = true)
	public void verifyDekTest_BNB_BlogPostsPage() throws IOException, InterruptedException {

		String dekTest = "Dek";
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step
		//Step 2 Verify Amazon Header Bidder is Running
		globalPage.IsElementExist(url, Constant.dekLocator, dekTest);
	}

	@Test(dependsOnMethods = {"verifyDekTest_BNB_BlogPostsPage"}, alwaysRun = true)
	public void verifyTaboolaSidebarTest_BNB_BlogPostsPage() throws InterruptedException, IOException {
		String taboolaText = "Taboola Sidebar ";
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step
		//Step 2 Verify Amazon Header Bidder is Running
		globalPage.IsElementExist(url, Constant.dekLocator, taboolaText);
	}

	@Test(dependsOnMethods = {"verifyTaboolaSidebarTest_BNB_BlogPostsPage"}, alwaysRun = true)
	public void verify_SidebarAdWrapper_BNB_BlogPostsPage() throws InterruptedException, IOException {
		String taboolaText = "Sidebar Ad wrapper ";
		//Object Initializations
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step
		//Step 2 Verify Amazon Header Bidder is Running
		globalPage.IsElementExist(url, Constant.sidebarClass, taboolaText);
	}
	@Test(dependsOnMethods = {"verify_SidebarAdWrapper_BNB_BlogPostsPage"}, alwaysRun = true)
	public void verifySocialShareLinks_BNB_BlogPostsPage() throws IOException, InterruptedException
	{
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step
		//Step 2 Verify Amazon Header Bidder is Running
		globalPage.socialShareNavigation(Constant.dekLocator, socialShareLinks, url);
	}

	@Test(dependsOnMethods = {"verifySocialShareLinks_BNB_BlogPostsPage"}, alwaysRun = true)
	public void newsletterSignUpTest_BNB_BlogPostsPage() throws IOException, InterruptedException
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
