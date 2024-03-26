package com.GA.FHM;

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

public class FHM_ArticlePage extends Driver{
	//Initialize object using page factory method 
	ConfigFileReader configFileReader;
	public Logger APPLICATION_LOGS = LogManager.getLogger("AppLogger");

	//Test Data
	String url=Constant.FHM_ArticlePage;
	String newspapaerSignUpLink = Constant.FHM_NewspaperSignUpLink;
	
	private By featuredImg = By.xpath("//div[@class='featured-image']");
	private By socialShareLinks = By.xpath("//div[@class='social-share social-menu-desktop']//ul[@class='pure-menu-list social-menu']//a");

	private By newsletterSignupButton =By.xpath("//div[@class='col-2 newsletter-signup']//a"); //By.xpath("//div[@id='ads-container-single']//button[@id='subscribe']"); // 

	private By newsletterSignupInputField =   By.xpath("//div[@id='ads-container-single']//input[@id='email']"); //By.xpath("//div[@class='col-2 newsletter-signup']//input[@name='EmailAddress']");  //By.xpath("//div[@id='ads-container-single']//input[@id='email']");

	
	@BeforeClass
	public void navigateToRequiredURL() throws Exception{
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Step:1 Navigate required URL
		globalPage.navigateToRequiredSite(url);	
		/*Thread.sleep(2000);
		WebElement jwVideo = driver.findElement(By.xpath("//div[@class='jw-media jw-reset']"));
		if(jwVideo.isDisplayed())
		globalPage.jwVideoClose();*/

	}	
	@BeforeMethod
	public void nameBefore(Method method) throws InterruptedException
	{
		System.out.println("********************************************************************************************");
	    System.out.println("Test : " + method.getName() + " execution started ...................");    
	    
	}
	
	@Test //(dependsOnMethods = {"verify_Banner_FHM_HubPage"}, alwaysRun = true)
	public void verify_BreadCrumb_FHM_ArticlePage() throws IOException, InterruptedException {
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);
		List<WebElement> allLinks = driver.findElements(Constant.breadcrumbLocator);
		String breadcrumbText = allLinks.get(allLinks.size()-1).getText();
		String expectedBreadcrumbUrl = Constant.FHM_HomePage + "/" +breadcrumbText;
		//Test Step
		//Step 2 Verify Amazon Header Bidder is Running
		globalPage.verifyIsBreadCrumbAvailable(Constant.breadcrumbLocator, url);
	}

	@Test(dependsOnMethods = {"verify_BreadCrumb_FHM_ArticlePage"}, alwaysRun = true)
	public void verifyTitle_FHM_ArticlePage() throws IOException, InterruptedException {
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);
		String element = "Title";
		//Test Step
		//Step 2 Verify Amazon Header Bidder is Running
		globalPage.verifyIsElementAvailable(Constant.articleTitleLocator, url, element);
	}

	@Test(dependsOnMethods = {"verifyTitle_FHM_ArticlePage"}, alwaysRun = true)
	public void verifyLineTest_FHM_ArticlePage() throws InterruptedException, IOException{
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step
		//Step 2 Verify Amazon Header Bidder is Running
		globalPage.isLineExist(url, Constant.avatarLocator, Constant.authorLocator);
	}
	
	@Test(dependsOnMethods = {"verifyLineTest_FHM_ArticlePage"}, alwaysRun = true)
	public void verifyNavigate_PostAuthorPageFrom_FHM_ArticlePage() throws InterruptedException, IOException{
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);
		String element = "Post Author";

		globalPage.scrollToElement(Constant.articleTitleLocator);
		//WebElement ele = driver.findElement(Constant.authorLocator);
		globalPage.verifyPostsImages_Clickable(Constant.authorLocator, url);
		globalPage.verifyLinkStatus(Constant.authorLocator, url, element);
		/*//Test Step
		globalPage.scrollToElement(Constant.articleTitleLocator);
		WebElement ele = driver.findElement(Constant.authorLocator);
		globalPage.click(ele);
		
		//globalPage.verifyIsLinkWorking(Constant.authorLocator, element);
		WebDriverCommonLib.waitForPageToBeLoad(driver);
		Thread.sleep(5000);

		String desiredUrl = driver.getCurrentUrl();
		System.out.println("After click: " + desiredUrl);
		String Pagetype ="Page not found";
		boolean flag = driver.getPageSource().contains(Pagetype);
		
		driver.navigate().back();
		
		//Verify the Expected Key-Value is present in Page Source or not			
		if(desiredUrl.contains(Constant.FHM_authorPage)==true && flag ==false){
			APPLICATION_LOGS.debug(element +" is available and working successfully on site :"+url+" After clicking " + element+ ", user navigates to :  "+desiredUrl);
			System.out.println(element +" is available and working successfully on site :"+url);
			System.out.println("Test case : PASSED !!");
		}
		else{
			APPLICATION_LOGS.debug(element +" is not available and working successfully on site :"+url+" After clicking " + element+ ", user navigates to :  "+desiredUrl);
			System.out.println(element +" is not available and working successfully on site :"+url);
			WebDriverCommonLib.getScreenShot(driver, "Error in verifyIsheroImageAvailable");
			assertTrue(desiredUrl.contains(Constant.FHM_authorPage),element +" is not available and working successfully on site :"+url);
			System.out.println("Test case : FAILED !!");
		}*/
	}
	
	@Test(dependsOnMethods = {"verifyNavigate_PostAuthorPageFrom_FHM_ArticlePage"}, alwaysRun = true)
	public void verifyDekTest_FHM_ArticlePage() throws IOException, InterruptedException {

		String dekTest = "Dek";
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step
		//Step 2 Verify Amazon Header Bidder is Running
		globalPage.IsElementExist(url, Constant.dekLocator, dekTest);
	}
		
	@Test(dependsOnMethods = {"verifyDekTest_FHM_ArticlePage"}, alwaysRun = true)
	public void verifyTaboolaSidebarTest_FHM_ArticlePage() throws InterruptedException, IOException {
		String taboolaText = "Taboola Sidebar ";
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step
		//Step 2 Verify Amazon Header Bidder is Running
		globalPage.IsElementExist(url, Constant.dekLocator, taboolaText);
	}
	
	@Test(dependsOnMethods = {"verifyTaboolaSidebarTest_FHM_ArticlePage"}, alwaysRun = true)
	public void verify_SidebarAdWrapper_FHM_ArticlePage() throws InterruptedException, IOException {
		String taboolaText = "Sidebar Ad wrapper ";
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step
		//Step 2 Verify Amazon Header Bidder is Running
		globalPage.IsElementExist(url, Constant.sidebarClass, taboolaText);
	}
	@Test(dependsOnMethods = {"verify_SidebarAdWrapper_FHM_ArticlePage"}, alwaysRun = true)
	public void verifySocialShareLinks_FHM_ArticlePage() throws IOException, InterruptedException
	{
			//Object Initialization
				GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

				//Test Step
				//Step 2 Verify Amazon Header Bidder is Running
				globalPage.socialShareNavigation(Constant.dekLocator, socialShareLinks, url);
	}

	@Test(dependsOnMethods = {"verifySocialShareLinks_FHM_ArticlePage"}, alwaysRun = true)
	public void newsletterSignUpTest_FHM_ArticlePage() throws IOException, InterruptedException
	{
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step
		globalPage.verifyNewsletterSignUp(url, newsletterSignupInputField, newsletterSignupButton, newspapaerSignUpLink );

	}

	/*@Test(dependsOnMethods = {"verifySocialShareLinks_FHM_ArticlePage"}, alwaysRun = true)
	public void verifyFacebookLink_FHM_ArticlePage() throws IOException, InterruptedException {
		String facebookLink = "facebook.com";

		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step
		//Step 2 Verify Amazon Header Bidder is Running
		globalPage.Test(url, Constant.dekLocator, Constant.facebookIconLocator,facebookLink );
	}


	@Test(dependsOnMethods = {"verifyFacebookLink_FHM_ArticlePage"}, alwaysRun = true)
	public void verifyTwitterLink_FHM_ArticlePage() throws InterruptedException, IOException {

		String twitterLink = "twitter.com";
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step
		//Step 2 Verify Amazon Header Bidder is Running
		globalPage.Test(url, Constant.dekLocator, Constant.twitterIconLocator,twitterLink );	

	}

	@Test(dependsOnMethods = {"verifyTwitterLink_FHM_ArticlePage"}, alwaysRun = true)
	public void verifyPinteresttLink_FHM_ArticlePage() throws InterruptedException, IOException {

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
