package com.GA.RDC;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
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

public class RDC_VideoPage extends Driver{
	//Initialize object using page factory method 
	ConfigFileReader configFileReader;
	public Logger APPLICATION_LOGS = LogManager.getLogger("AppLogger");

	//Test Data
	String url=Constant.RDC_VideoHubPage;

	private By videoContainer = By.xpath("//div[@class='player-container']");
	private By videoStage = By.xpath("//div[@class='player-stage']");
	private By videoPlayList =By.xpath("//div[@id='the-playlist']");
	
	private By video_PlayList =By.xpath("//div[@id='the-playlist']//div//h4");
	//private By video_ThumbNail =By.xpath("//img[@class='lazy-loaded']");
	private By video_ThumbNail1 =By.xpath("//div[@id='the-playlist']//div[@class='item']//img");
	private By videoLinks=By.xpath("//div[@id='the-playlist']//h4");
	
	private By owlNavigation = By.xpath("//div[@class='owl-nav']"); //div[8]/div[2]/div[1]/div[@class='owl-nav']");      
	private By prevArrowBtn = By.xpath("//div[@class='owl-prev']"); //div[8]/div[2]/div[1]/div[2]/div[@class='owl-prev']");   
	private By nextArrowBtn = By.xpath("//div[@class='owl-next']"); //div[8]/div[2]/div[1]/div[2]/div[@class='owl-next']");   
	private By dotsBtn = By.xpath("//div[@class='owl-dots']"); //div[8]/div[2]/div[1]/div[@class='owl-dots']");   

	//body.archive.post-type-archive.post-type-archive-video.wp-custom-logo.hfeed.no-sidebar:nth-child(2) div.contain:nth-child(7) div:nth-child(1) div.player-container div.playlist div.item:nth-child(1) > h4:nth-child(3)

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
	public void verify_VideoPlayerContainer_RDC_VideoPage() throws IOException, InterruptedException {

		
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step
		String element = "Video Player Container";
		String video_Stage = "Video Player Stage";
		String Video_List  = "Video Player List";

		//Step 2 Verify Amazon Header Bidder is Running
		globalPage.verifyIsVideoAvailable(videoContainer, url, element);
		globalPage.verifyIsVideoAvailable(videoStage, url, video_Stage);
		globalPage.verifyIsVideoAvailable(videoPlayList, url, Video_List);
	}


	@Test(dependsOnMethods = {"verify_VideoPlayerContainer_RDC_VideoPage"}, alwaysRun = true)
	public void verify_VideoFeaturedPlaylistLinks_RDC_VideoPage() throws IOException, InterruptedException {
		
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);
	//Step 2 Verify Amazon Header Bidder is Running
		globalPage.verifyIsVideoLinksWorking(video_PlayList, url);

	}


	@Test(dependsOnMethods = {"verify_VideoFeaturedPlaylistLinks_RDC_VideoPage"}, alwaysRun = true)
	public void verify_VideoPlaylistThumbnailImages_RDC_VideoPage() throws IOException, InterruptedException {

	//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);
		//Test Step
				//String video_List  = "Video Player List";
				//Step 2 Verify Amazon Header Bidder is Running
				//globalPage.verifyIsVideoImgLinksWorking(video_ThumbNail1, url);
				globalPage.verifyPostsImages_Clickable(videoLinks, url);
	}

	
	@AfterMethod
	public void nameAfter(Method method)
	{
	    System.out.println("Test : " + method.getName() + " execution completed .................... \n");    
	    System.out.println("********************************************************************************************");
	}
}


