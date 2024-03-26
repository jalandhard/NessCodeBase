package com.GA.SRD;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
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

public class SRD_ArticlePage extends Driver{
	//Initialize object using page factory method 
	ConfigFileReader configFileReader;
	public Logger APPLICATION_LOGS = LogManager.getLogger("AppLogger");

	//Test Data
	String url=Constant.SRD_ArticlePage;
	String newspapaerSignUpLink = Constant.SRD_NewspaperSignUpLink;
	
	private By featuredImg = By.xpath("//div[@class='featured-image']");
	private By socialShareLinks = By.xpath("//div[@class='social-share social-menu-desktop']//ul[@class='pure-menu-list social-menu']//a");

	private By newsletterSignupButton =By.xpath("//div[@id='ads-container-single']//button[@id='subscribe']");  //By.xpath("//button[@class='at-element-click-tracking']"); //// 

	private By newsletterSignupInputField =   By.xpath("//div[@id='ads-container-single']//input[@id='email']"); //By.xpath("//div[@class='col-2 newsletter-signup']//input[@name='EmailAddress']");  //By.xpath("//div[@id='ads-container-single']//input[@id='email']");

	
	private By cookieconsoleCloseBtn=By.xpath("//a[@id='cn-accept-cookie']");

	@BeforeClass
	public void navigateToRequiredURL() throws Exception{
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Step:1 Navigate required URL
		globalPage.navigateToRequiredSite(url);
		try{
			WebElement cookieConsolemsg = driver.findElement(cookieconsoleCloseBtn);
			//WebDriverWait wait1 = new WebDriverWait(driver, 10);
			if(cookieConsolemsg.isDisplayed())
			{
				cookieConsolemsg.click();
				System.out.println("Cookie Accepted.. !!");
			}
   		}
		catch(NoSuchElementException e)
		{
			System.out.println("Cookie console message not found");
		}
		catch(ElementNotInteractableException e)
		{
			System.out.println("element not interactable exception found");
		}
	}	
	@BeforeMethod
	public void nameBefore(Method method)
	{
		System.out.println("********************************************************************************************");
	    System.out.println("Test : " + method.getName() + " execution started ...................");      
	    
	}
	@Test //(dependsOnMethods = {"verify_Banner_SRD_HubPage"}, alwaysRun = true)
	public void verify_BreadCrumb_SRD_ArticlePage() throws IOException, InterruptedException {
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);
		List<WebElement> allLinks = driver.findElements(Constant.breadcrumbLocator);
		String breadcrumbText = allLinks.get(allLinks.size()-1).getText();
		String expectedBreadcrumbUrl = Constant.SRD_HomePage + "/" +breadcrumbText;
		//Test Step
		//Step 2 Verify Amazon Header Bidder is Running
		globalPage.verifyIsBreadCrumbAvailable(Constant.breadcrumbLocator, url);
	}

	@Test(dependsOnMethods = {"verify_BreadCrumb_SRD_ArticlePage"}, alwaysRun = true)
	public void verifyTitle_SRD_ArticlePage() throws IOException, InterruptedException {
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);
		String element = "Title";
		//Test Step
		//Step 2 Verify Amazon Header Bidder is Running
		globalPage.verifyIsElementAvailable(Constant.articleTitleLocator, url, element);
	}

	@Test(dependsOnMethods = {"verifyTitle_SRD_ArticlePage"}, alwaysRun = true)
	public void verifyLineTest_SRD_ArticlePage() throws InterruptedException, IOException{
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step
		//Step 2 Verify Amazon Header Bidder is Running
		globalPage.isLineExist(url, Constant.avatarLocator, Constant.authorLocator);
	}
	
	
	 /*@Test(dependsOnMethods = {"verifyLineTest_SRD_ArticlePage"}, alwaysRun = true)
	public void verifyNavigate_PostAuthorPageFrom_SRD_ArticlePage() throws InterruptedException, IOException{
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);
		String element = "Post Author";
		
		//Test Step
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
		if(desiredUrl.contains(Constant.SRD_authorPage)==true && flag ==false){
			APPLICATION_LOGS.debug(element +" is available and working successfully on site :"+url+" After clicking " + element+ ", user navigates to :  "+desiredUrl);
			System.out.println(element +" is available and working successfully on site :"+url);
			System.out.println("Test case : PASSED !!");
		}
		else{
			APPLICATION_LOGS.debug(element +" is not available and working successfully on site :"+url+" After clicking " + element+ ", user navigates to :  "+desiredUrl);
			System.out.println(element +" is not available and working successfully on site :"+url);
			WebDriverCommonLib.getScreenShot(driver, "Error in verifyIsheroImageAvailable");
			assertTrue(desiredUrl.contains(Constant.SRD_authorPage),element +" is not available and working successfully on site :"+url);
			System.out.println("Test case : FAILED !!");
		}
	}
	*/
	
	@Test(dependsOnMethods = {"verifyLineTest_SRD_ArticlePage"}, alwaysRun = true)
	public void verifyDekTest_SRD_ArticlePage() throws IOException, InterruptedException {

		String dekTest = "Dek";
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step
		//Step 2 Verify Amazon Header Bidder is Running
		globalPage.IsElementExist(url, Constant.dekLocator, dekTest);
	}
		
	@Test(dependsOnMethods = {"verifyDekTest_SRD_ArticlePage"}, alwaysRun = true)
	public void verifyTaboolaSidebarTest_SRD_ArticlePage() throws InterruptedException, IOException {
		String taboolaText = "Taboola Sidebar ";
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step
		//Step 2 Verify Amazon Header Bidder is Running
		globalPage.IsElementExist(url, Constant.dekLocator, taboolaText);
	}
	
	@Test(dependsOnMethods = {"verifyTaboolaSidebarTest_SRD_ArticlePage"}, alwaysRun = true)
	public void verify_SidebarAdWrapper_SRD_ArticlePage() throws InterruptedException, IOException {
		String taboolaText = "Sidebar Ad wrapper ";
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step
		//Step 2 Verify Amazon Header Bidder is Running
		globalPage.IsElementExist(url, Constant.sidebarClass, taboolaText);
	}
	@Test(dependsOnMethods = {"verify_SidebarAdWrapper_SRD_ArticlePage"}, alwaysRun = true)
	public void verifySocialShareLinks_SRD_ArticlePage() throws IOException, InterruptedException
	{
			//Object Initialization
				GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

				//Test Step
				//Step 2 Verify Amazon Header Bidder is Running
				globalPage.socialShareNavigation(Constant.dekLocator, socialShareLinks, url);
	}

	/*@Test(dependsOnMethods = {"verifySocialShareLinks_SRD_ArticlePage"}, alwaysRun = true)
	public void newsletterSignUpTest_SRD_ArticlePage() throws IOException, InterruptedException
	{
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step
		globalPage.verifyNewsletterSignUp(url, newsletterSignupInputField, newsletterSignupButton, newspapaerSignUpLink );

	}*/

	/*@Test(dependsOnMethods = {"verifySocialShareLinks_SRD_ArticlePage"}, alwaysRun = true)
	public void verifyFacebookLink_SRD_ArticlePage() throws IOException, InterruptedException {
		String facebookLink = "facebook.com";

		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step
		//Step 2 Verify Amazon Header Bidder is Running
		globalPage.Test(url, Constant.dekLocator, Constant.facebookIconLocator,facebookLink );
	}


	@Test(dependsOnMethods = {"verifyFacebookLink_SRD_ArticlePage"}, alwaysRun = true)
	public void verifyTwitterLink_SRD_ArticlePage() throws InterruptedException, IOException {

		String twitterLink = "twitter.com";
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step
		//Step 2 Verify Amazon Header Bidder is Running
		globalPage.Test(url, Constant.dekLocator, Constant.twitterIconLocator,twitterLink );	

	}

	@Test(dependsOnMethods = {"verifyTwitterLink_SRD_ArticlePage"}, alwaysRun = true)
	public void verifyPinteresttLink_SRD_ArticlePage() throws InterruptedException, IOException {

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
