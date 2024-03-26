package com.GA.SRD;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Iterator;
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
import org.testng.Assert;
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

public class SRD_HomePage extends Driver{

	//Initialize object using page factory method 
	ConfigFileReader configFileReader;
	public Logger APPLICATION_LOGS = LogManager.getLogger("AppLogger");

	//Test Data
	String url=Constant.SRD_HomePage;

	private By banner = By.xpath("//div[@id='desktop-nav-banner']//a");

	//hero container locators
	/*private By heroText = By.xpath("//a[1]/h2[1]");
	private By heroImage=By.xpath("//div[@class='hero-image']"); ////img[@class='initial loading']

	*///spotlight container - 1big3small
	private By spotlightHeading = By.xpath("//h2[@class='hp-section-heading spotlight-5']");
	private By SpotlightContainer = By.xpath("//div[@class='spotlight']//div[@class='one-big-three-small']//li//h4//a"); //div[@class='content']//div[1]//ul//h4//a");  ////div[6]/ul[1]  //div[@class='content']//div[3]//h4//a
	/*private By SpotlightBigContainer = By.xpath("//div[@class='content']//div[1]//li[@class='hp-large-post']//h4//a");
	private By SpotlightSmallContainer = By.xpath("//div[@class='content']/div[1]//li[@class='hp-three-small-posts']//h4//a");
	*///video container
	private By VideoContainer = By.xpath("//div[@class='hp-videos']//div[@class='text-container']//h4");     //div[@class='hp-videos']//ul
	private By videos = By.xpath("//div[@class='hp-videos']/ul//div[@class='video-container']"); 
	// more videos link
	private By moreVideosLink = By.className("hp-more-btn");
	// nativo ad
	private By nativoAdsContainer = By.xpath("//div[@class='hp-native-ad']//a");
	private By nativoAds = By.xpath("//div[@class='hp-native-ad']");
	//First Custom Container - 2 big and 3 Small modules
	private By firstCustContainer = By.xpath("//div[@class='category-5']//div[@class='two-big-three-small']//h4//a");  //div[@class='two-big-three-small']//h4//a");
	private By firstCustomHeading = By.xpath("//div[@class='category-5']//h2//a");  //h2[2]/a[1]");
	private By firstCust_MoreBtn = By.xpath("//div[@class='two-big-three-small']/a[@class='hp-more-btn' and 1]");

	//Second Custom Container - 1 big and 3 Small modules
	private By secCustContainer = By.xpath("//div[@class='category-6']//h4//a"); //div[@class='content']/div[7]//h4//a"); //  //div[12]/ul[1]
	/*private By secCustBigContainer = By.xpath("//div[@class='content']//div[7]//h4[@class='fancy']//a"); //changing 9 to7
	private By secCustSmallContainer = By.xpath("//div[@class='content']//div[7]//div[@class='text-container']/h4//a");

	private By content2Ad = By.xpath("//iframe[@id='google_ads_iframe_/6178/srd_desktop/homepage/content_2_0']");
	*/private By secCustomHeading = By.xpath("//div[@class='category-6']//h2//a"); //h2[3]//a");
	private By secCust_MoreBtn = By.xpath("//div[@class='one-big-three-small']/a[@class='hp-more-btn' and 1]");
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
		System.out.println("Test : " + method.getName() + " execution started ..................");       
	}


	@Test
	public void verify_SpotLightSection_Posts_SRD_HomePage() throws IOException, InterruptedException
	{
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);
		List<WebElement> links = driver.findElements(SpotlightContainer);
		int link = links.size();
		System.out.println("Total no of posts in Spotlight section are: " + link);
		
		//globalPage.scrollToElement(spotlightHeading);
		globalPage.verifyBrowseByList(SpotlightContainer, url);
		/*globalPage.verifyBrowseByList(SpotlightSmallContainer, url);
		globalPage.verifyBrowseByList(SpotlightBigContainer, url);*/
		if(link == 4)
		{
			System.out.println("Total no of posts in Spotlight Section are: " + link);
			System.out.println("Test case : PASSED");
		}
		else
		{
			Assert.fail("Test case : FAILED, Total no of posts in Spotlight Section are: " + link);
		}
	}

	@Test(dependsOnMethods = {"verify_SpotLightSection_Posts_SRD_HomePage"}, alwaysRun = true)
	public void verify_VideoContainerAvailable_SRD_HomePage() throws IOException, InterruptedException {

		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);
		String element = "Videos in container" ;
		//verify videos container available         		   
		globalPage.verifyIsElementAvailable(VideoContainer, url, element); 
		//Test Step
		try
		{            		   
			globalPage.scrollToElement(VideoContainer); //featured module container
		}
		catch(Exception e){
			System.out.println("The Hide/Show console not present");
			APPLICATION_LOGS.info("Video Module not present");
		}

		List<WebElement> links= driver.findElements(videos);
		int count = links.size();
		System.out.println("Total no of videos in video container are:  " + count);
	}


	@Test(dependsOnMethods = {"verify_VideoContainerAvailable_SRD_HomePage"}, alwaysRun = true)
	public void verify_VideosRunning_SRD_HomePage() throws IOException, InterruptedException {
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);
		globalPage.verifyIsVideoRunning(videos, url);
	}



	@Test(dependsOnMethods = {"verify_VideosRunning_SRD_HomePage"}, alwaysRun = true)
	public void verify_WatchMoreVideosLink_SRD_HomePage() throws IOException, InterruptedException {

		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);
		String element = "Watch More videos link";
		//globalPage.verifyIsElementWorking(moreVideosLink, url, element);
		globalPage.verifyPostsImages_Clickable(moreVideosLink, url);
		globalPage.verifyLinkStatus(moreVideosLink, url, element);
	}


	/*@Test(dependsOnMethods = {"verify_WatchMoreVideosLink_SRD_HomePage"}, alwaysRun = true)
	public void verify_NativeSponsor_Container_SRD_HomePage() throws IOException, InterruptedException {

		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);
		String element = "Nativo Ads container" ;
		globalPage.verifyIsElementAvailable(nativoAds, url, element); 
	}

	@Test(dependsOnMethods = {"verify_NativeSponsor_Container_SRD_HomePage"}, alwaysRun = true)
	public void verify_NativeSponsor_PostWorking_SRD_HomePage() throws IOException, InterruptedException {

		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);
		List<WebElement> links = driver.findElements(nativoAdsContainer);
		int count = links.size();
		System.out.println("Total no of posts in NativoAds Container are: " + count);
		
		if(count != 0){
			 // globalPage.verifyBrowseByList(nativoAdsContainer, url);
			  globalPage.verifyPostsImages_Clickable(nativoAdsContainer, url);
			  globalPage.verifyBrowseByList(nativoAdsContainer, url);
			}
			else
			{
				Assert.fail("Test case : FAILED, No posts available in native sponsor module");
			}
	}*/

	@Test(dependsOnMethods = {"verify_WatchMoreVideosLink_SRD_HomePage"}, alwaysRun = true)
	public void verify_2Big3Small_Container_Heading_SRD_HomePage() throws IOException, InterruptedException {

		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);
		String element = "First Custom Category heading ";
		try{
		globalPage.closeBounceExchange();
		}
		catch(Exception e){
			System.out.println("Bounce exchange popup not visible");
		}
		//globalPage.verifyIsElementWorking(firstCustomHeading, url, element);
		globalPage.verifyPostsImages_Clickable(firstCustomHeading, url);
		globalPage.verifyLinkStatus(firstCustomHeading, url, element);
	}

	@Test(dependsOnMethods = {"verify_2Big3Small_Container_Heading_SRD_HomePage"}, alwaysRun = true)
	public void verify_2Big3SmallPosts_Available_SRD_HomePage() throws IOException, InterruptedException
	{
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);
		List<WebElement> links = driver.findElements(firstCustContainer);
		int count = links.size();
		System.out.println("Total no of posts in 2Big3Small Container module are: " + count);
		globalPage.verifyPostsImages_Clickable(firstCustContainer, url);
		globalPage.verifyBrowseByList(firstCustContainer, url);
		if(count == 5)
		{
			System.out.println("Total no of posts in 2Big3Small Container module are: " + count);
			System.out.println("Test case : PASSED");
		}
		else
		{
			Assert.fail("Test case : FAILED, Total no of posts in 2Big3Small Container are: " + count);
		}
	}

	@Test(dependsOnMethods = {"verify_2Big3SmallPosts_Available_SRD_HomePage"}, alwaysRun = true)
	public void verify_2Big3SmallContainer_MoreButtons_Available_SRD_HomePage()throws IOException, InterruptedException{

		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);
		String element = "More options of first custom module ";
		//globalPage.verifyIsElementWorking(firstCust_MoreBtn, url, element);
		globalPage.verifyPostsImages_Clickable(firstCust_MoreBtn, url);
		globalPage.verifyLinkStatus(firstCust_MoreBtn, url, element);
	}


	@Test(dependsOnMethods = {"verify_2Big3SmallContainer_MoreButtons_Available_SRD_HomePage"}, alwaysRun = true)
	public void verify_1Big3SmallContainer_Heading_SRD_HomePage() throws IOException, InterruptedException {

		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);
		String element = "Second Custom Category heading ";
		//globalPage.scrollToElement(content2Ad);
		//globalPage.verifyIsElementWorking_CA(secCustomHeading, url, element);
		globalPage.verifyPostsImages_Clickable(secCustomHeading, url);
		globalPage.verifyLinkStatus(secCustomHeading, url, element);

	}

	@Test(dependsOnMethods = {"verify_1Big3SmallContainer_Heading_SRD_HomePage"}, alwaysRun = true)
	public void verify_1Big3SmallPosts_Available_SRD_HomePage() throws IOException, InterruptedException
	{
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);
		List<WebElement> links = driver.findElements(secCustContainer);
		int count = links.size();
		System.out.println("Total no of posts in 1Big3Small custom container are: " + count);
		String element = "Second Custom Category  ";
		globalPage.scrollToElement(secCustomHeading);
		globalPage.verifyPostsImages_Clickable(secCustContainer, url);
		/*globalPage.verifyBrowseByList(secCustSmallContainer, url);
		globalPage.verifyBrowseByList(secCustBigContainer, url);*/
		//globalPage.verifyBrowseByList(secCustContainer, url);
		if(count == 4)
		{
			System.out.println("Total no of posts in 1Big3Small Container module are: " + count);
			System.out.println("Test case : PASSED");
		}
		else
		{
			Assert.fail("Test case : FAILED, Total no of posts in 1Big3Small Container are: " + count);
		}
	}

	@Test(dependsOnMethods = {"verify_1Big3SmallPosts_Available_SRD_HomePage"}, alwaysRun = true)
	public void verify_1Big3SmallContainer__MoreButtons_Availble_SRD_HomePage()throws IOException, InterruptedException{

		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);
		String element = "More options of second custom module ";
		//globalPage.verifyIsElementWorking(secCust_MoreBtn, url, element);
		globalPage.verifyPostsImages_Clickable(secCust_MoreBtn, url);
		globalPage.verifyLinkStatus(secCust_MoreBtn, url, element);
	}
	
	@AfterMethod
	public void nameAfter(Method method)
	{
		System.out.println("Test : " + method.getName() + " execution completed .................... \n");       
		System.out.println("********************************************************************************************");
	}

}


