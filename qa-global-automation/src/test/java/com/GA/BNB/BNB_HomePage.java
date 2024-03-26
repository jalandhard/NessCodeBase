package com.GA.BNB;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.wp.genericLib.ConfigFileReader;
import com.wp.genericLib.Constant;
import com.wp.genericLib.Driver;
import com.wp.pageObjectRepo.GlobalFunctions;

public class BNB_HomePage extends Driver {
	//Initialize object using page factory method 
	ConfigFileReader configFileReader;

	public Logger APPLICATION_LOGS = LogManager.getLogger("AppLogger");
	

	//Test Data
	String url=Constant.BNB_HomePage;

	private By banner = By.xpath("//div[@id='desktop-nav-banner']//a");

	//hero container locators
	private By heroText = By.xpath("//div[@class='hero-text']//a");  //a[1]/h2[1]");
	private By heroImage=By.xpath("//div[@class='hero-image']"); ////img[@class='initial loading']

	//spotlight container - 1big3small
	private By spotlightHeading = By.xpath("//h2[contains(text(),'Spotlight')]");
	private By SpotlightContainer = By.xpath("//div[@class='spotlight']//div[@class='one-big-three-small']//li//h4//a");  //div[@class='one-big-three-small'][1]//div//h4//a"); //div[@class='one-big-three-small'][1]//div//h4//a);  ////div[6]/ul[1]
	/*private By SpotlightBigContainer = By.xpath("//div[@class='content']//div[3]//h4[@class='fancy']//a");   //div[@class='content']//div[1]//li[@class='hp-large-post']//h4/a");
	private By SpotlightSmallContainer = By.xpath("//div[@class='content']//div[3]//div[@class='text-container']/h4//a");  //div[@class='content']//div[1]//li[@class='hp-three-small-posts']//h4/a");
	*///video container
	/*private By VideoContainer = By.xpath("//div[@class='hp-videos']//div[@class='text-container']//h4");     //div[@class='hp-videos']//ul
	private By videos = By.xpath("//div[@class='hp-videos']/ul//div[@class='video-container']"); 
	// more videos link
	private By moreVideosLink = By.className("hp-more-btn");
	// nativo ad
	private By nativoAdsContainer = By.xpath("//div[@class='hp-native-ad']//a");
	private By nativoAds = By.xpath("//div[@class='hp-native-ad']");
*/	//First Custom Container - 2 big and 3 Small modules
	private By firstCustContainer = By.xpath("//div[@class='category-5']//div[@class='two-big-three-small']//h4//a"); //div[@class='two-big-three-small']//h4//a");
	private By firstCustomHeading = By.xpath("//div[@class='category-5']//h2//a"); //h2[2]/a[1]");
	private By firstCust_MoreBtn = By.xpath("//div[@class='two-big-three-small']/a[@class='hp-more-btn' and 1]");

	//Second Custom Container - 1 big and 3 Small modules
	private By secCustContainer = By.xpath("//div[@class='category-6']//h4//a");  //(//div[@class='one-big-three-small'])[2]//div//h4/a");//By.xpath("//div[@class='content']/div[9]//h4//a"); //  //div[12]/ul[1]
	/*private By secCustBigContainer = By.xpath("(//div[@class='one-big-three-small'])[2]//div[@class='hp-white-out']/h4/a");
	private By secCustSmallContainer = By.xpath("(//div[@class='one-big-three-small'])[2]//div[@class='text-container']/h4/a");
*/
	private By secCustomHeading = By.xpath("//div[@class='category-6']//h2//a"); //h2[3]//a");
	private By secCust_MoreBtn = By.xpath("//div[@class='one-big-three-small']/a[@class='hp-more-btn' and 1]");

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
		System.out.println("Test : " + method.getName() + " execution started ..................");       
	}

	@Test
	public void verify_Banner_BNB_HomePage() throws IOException, InterruptedException{

		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);
		//Test Step
		globalPage.verifyIsBannerAvailable(banner, url);
	}

	@Test()
	public void verify_HeroContainerPost_LinkWorking_BNB_HomePage() throws IOException, InterruptedException {

		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step
		String element = "Hero container Post Link";

		//globalPage.verifyIsElementWorking(heroText, url, element);
		globalPage.verifyPostsImages_Clickable(heroText, url);
		globalPage.verifyLinkStatus(heroText, url, element);
	}


	@Test(dependsOnMethods = {"verify_HeroContainerPost_LinkWorking_BNB_HomePage"}, alwaysRun = true)
	public void verify_HeroContainerPost_ImageWorking_BNB_HomePage() throws IOException, InterruptedException {

		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);
		String element = "Hero Container Image Link";
		globalPage.verifyPostsImages_Clickable(heroImage, url);
	}

	@Test(dependsOnMethods = {"verify_HeroContainerPost_ImageWorking_BNB_HomePage"}, alwaysRun = true)
	public void verify_SpotLightSection_Posts_BNB_HomePage() throws IOException, InterruptedException
	{
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);
		List<WebElement> links = driver.findElements(SpotlightContainer);
		int link = links.size();
		if(link != 0)
		{
			System.out.println("Total no of posts in Spotlight section are: " + link);
			String element = "spotlight";
			globalPage.scrollToElement(spotlightHeading);
			globalPage.IsElementExist(url, spotlightHeading, element);
			/*globalPage.verifyBrowseByList(SpotlightSmallContainer, url);
			globalPage.verifyBrowseByList(SpotlightBigContainer, url);*/
			
			globalPage.verifyPostsImages_Clickable(SpotlightContainer, url);
			globalPage.verifyBrowseByList(SpotlightContainer, url);
			
			/*globalPage.verifyPostsImages_Clickable(SpotlightBigContainer, url);
			globalPage.verifyBrowseByList(SpotlightBigContainer, url);*/
			if(link == 4)
			{
				System.out.println("Total no of posts in Spotlight Section are: " + links);
				System.out.println("Test case : PASSED");
			}
			else
			{
				assertTrue((link == 4),"Test case : FAILED, Total no of posts in Spotlight Section are: " + links);
			}
		}
		else
		{
			Assert.fail("Test case : FAILED, Total no of posts in spotlight are: " + link);


		}
	}

	/*@Test(dependsOnMethods = {"verify_SpotLightSection_Posts_BNB_HomePage"}, alwaysRun = true)
	public void verify_VideoContainerAvailable_BNB_HomePage() throws IOException, InterruptedException {

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


	@Test(dependsOnMethods = {"verify_VideoContainerAvailable_BNB_HomePage"}, alwaysRun = true)
	public void verify_VideosRunning_BNB_HomePage() throws IOException, InterruptedException {
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		globalPage.verifyIsVideoRunning(videos, url);
	}



	@Test(dependsOnMethods = {"verify_VideosRunning_BNB_HomePage"}, alwaysRun = true)
	public void verify_WatchMoreVideosLink_BNB_HomePage() throws IOException, InterruptedException {

		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step
		String element = "Watch More videos link";

		//Step 2 Verify Amazon Header Bidder is Running
		globalPage.verifyIsElementWorking(moreVideosLink, url, element);
	}


	@Test(dependsOnMethods = {"verify_WatchMoreVideosLink_BNB_HomePage"}, alwaysRun = true)
	public void verify_NativeSponsor_Container_BNB_HomePage() throws IOException, InterruptedException {

		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		String element = "Nativo Ads container" ;

		//verify nativo ad container available         		   
		globalPage.verifyIsElementAvailable(nativoAds, url, element); 
	}

	@Test(dependsOnMethods = {"verify_NativeSponsor_Container_BNB_HomePage"}, alwaysRun = true)
	public void verify_NativeSponsor_PostWorking_BNB_HomePage() throws IOException, InterruptedException {

		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		List<WebElement> links = driver.findElements(nativoAdsContainer);
		int count = links.size();
		System.out.println("Total no of posts in NativoAds Container are: " + count);

		if(count != 0){
			//System.out.println("Element present");
			globalPage.verifyBrowseByList(nativoAdsContainer, url);
		}
		else
			System.out.println("total no of links are: " + count );
		Assert.fail("Test case : FAILED, No posts available in native sponsor module");


	}*/


	@Test(dependsOnMethods = {"verify_SpotLightSection_Posts_BNB_HomePage"}, alwaysRun = true)
	public void verify_2Big3Small_Container_Heading_BNB_HomePage() throws IOException, InterruptedException {

		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);
		String element = "First Custom Category heading ";
		//globalPage.verifyIsElementWorking(firstCustomHeading, url, element);
		globalPage.verifyPostsImages_Clickable(firstCustomHeading, url);
		globalPage.verifyLinkStatus(firstCustomHeading, url, element);
	}

	@Test(dependsOnMethods = {"verify_2Big3Small_Container_Heading_BNB_HomePage"}, alwaysRun = true)
	public void verify_2Big3SmallPosts_Available_BNB_HomePage() throws IOException, InterruptedException
	{
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);
		List<WebElement> links = driver.findElements(firstCustContainer);
		int count = links.size();
		System.out.println("Total no of posts in 2Big3Small Container module are: " + count);
		//globalPage.verifyBrowseByList(firstCustContainer, url);
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

	@Test(dependsOnMethods = {"verify_2Big3SmallPosts_Available_BNB_HomePage"}, alwaysRun = true)
	public void verify_2Big3SmallContainer_MoreButtons_Available_BNB_HomePage()throws IOException, InterruptedException{
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);
		String element = "More options of first custom module ";
		//globalPage.verifyIsElementWorking(firstCust_MoreBtn, url, element);
		globalPage.verifyPostsImages_Clickable(firstCust_MoreBtn, url);
		globalPage.verifyLinkStatus(firstCust_MoreBtn, url, element);
	}

	@Test(dependsOnMethods = {"verify_2Big3SmallContainer_MoreButtons_Available_BNB_HomePage"}, alwaysRun = true)
	public void verify_1Big3SmallContainer_Heading_BNB_HomePage() throws IOException, InterruptedException {
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);
		String element = "Second Custom Category heading ";
		//globalPage.verifyIsElementWorking(secCustomHeading, url, element);
		globalPage.verifyPostsImages_Clickable(secCustomHeading, url);
		globalPage.verifyLinkStatus(secCustomHeading, url, element);
	}

	@Test(dependsOnMethods = {"verify_1Big3SmallContainer_Heading_BNB_HomePage"}, alwaysRun = true)
	public void verify_1Big3SmallPosts_Available_BNB_HomePage() throws IOException, InterruptedException
	{
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);
		List<WebElement> links = driver.findElements(secCustContainer);
		int count = links.size();
		System.out.println("Total no of posts in 1Big3Small custom container are: " + count);
		String element = "Second Custom Category heading ";
		globalPage.IsElementExist(url, secCustomHeading, element);
		/*globalPage.verifyBrowseByList(secCustSmallContainer, url);
		globalPage.verifyBrowseByList(secCustBigContainer, url);*/
		globalPage.verifyPostsImages_Clickable(secCustContainer, url);
		globalPage.verifyBrowseByList(secCustContainer, url);
		/*globalPage.verifyPostsImages_Clickable(secCustBigContainer, url);
		globalPage.verifyBrowseByList(secCustBigContainer, url);*/
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

	@Test(dependsOnMethods = {"verify_1Big3SmallPosts_Available_BNB_HomePage"}, alwaysRun = true)
	public void verify_1Big3SmallContainer__MoreButtons_Availble_BNB_HomePage()throws IOException, InterruptedException{
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
