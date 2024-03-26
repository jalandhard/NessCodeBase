package com.GA.RD;

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

public class RD_VideoPage extends Driver{
	//Initialize object using page factory method 
	ConfigFileReader configFileReader;
	public Logger APPLICATION_LOGS = LogManager.getLogger("AppLogger");

	//Test Data
	String url=Constant.RD_VideoHubPage;

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
	public void verify_VideoPlayerContainer_RD_VideoPage() throws IOException, InterruptedException {

		
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


	@Test(dependsOnMethods = {"verify_VideoPlayerContainer_RD_VideoPage"}, alwaysRun = true)
	public void verify_VideoFeaturedPlaylistLinks_RD_VideoPage() throws IOException, InterruptedException {
		
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);
	//Step 2 Verify Amazon Header Bidder is Running
		globalPage.verifyIsVideoLinksWorking(video_PlayList, url);

	}


	@Test(dependsOnMethods = {"verify_VideoFeaturedPlaylistLinks_RD_VideoPage"}, alwaysRun = true)
	public void verify_VideoPlaylistThumbnailImages_RD_VideoPage() throws IOException, InterruptedException {

	//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);
		//Test Step
				//String video_List  = "Video Player List";
				//Step 2 Verify Amazon Header Bidder is Running
				//globalPage.verifyIsVideoImgLinksWorking(video_ThumbNail1, url);
				globalPage.verifyPostsImages_Clickable(videoLinks, url);
	}

	@Test(dependsOnMethods = {"verify_VideoPlaylistThumbnailImages_RD_VideoPage"}, alwaysRun = true)
	public void verify_VideoPlaylistSliders_RD_VideoPage() throws IOException, InterruptedException {
		
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step
				
				String leftBtn = "Left Arrow";
				String rightBtn = "Right Arrow";
				String dots = "Dots";
				//Step 2 Verify Amazon Header Bidder is Running
				List<WebElement> links = driver.findElements(owlNavigation);
				int count = links.size();
				System.out.println("Total no of owl navigation in video page are: " + count);
				
				if(count != 0){
					 //System.out.println("Element present");
					// globalPage.verifyBrowseByList(nativoAdsContainer, url);
						
				for (int i = 0; i < count; i++) {
					System.out.println(i);
					List<WebElement> lists = driver.findElements(owlNavigation);
					//WebElement list = lists.get(i);
				//driver.findElement((By) list);
				globalPage.scrollToElement(owlNavigation);   	
				globalPage.IsElementExist(url, prevArrowBtn, leftBtn);
			
				//driver.findElement(prevArrowBtn).click();
				globalPage.IsElementExist(url, nextArrowBtn, rightBtn);
				
			//	driver.findElement(nextArrowBtn).click();
				globalPage.IsElementExist(url, dotsBtn, dots);
			
			}
				}

	else
	{
		//System.out.println("total no of links are: " + count );
		Assert.fail("Test case : FAILED, video playlist sliders are not available");
	}
}

	@Test(dependsOnMethods = {"verify_VideoPlaylistSliders_RD_VideoPage"}, alwaysRun = true)
	public void verify_PlaylistPreviousArrowBtn_RD_VideoPage() throws IOException, InterruptedException {
	
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step
		String leftBtn = "Left Arrow";
		List<WebElement> links = driver.findElements(prevArrowBtn);
		int count = links.size();
		System.out.println("Total no of prev navigation in video page are: " + count);
		
		if(count != 0){
		/*int links = driver.findElements(prevArrowBtn).size();*/
		//System.out.println("Total no of prev navigation in video page are: " + links);
		/*globalPage.verifyPostsImages_Clickable(prevArrowBtn, url);
			}
		else
		{
			Assert.fail("Test case : FAILED, video prev navigation are not available");
		}*/
	
		for (int i = 0; i < count; i++) {
			System.out.println(i);
			List<WebElement> lists = driver.findElements(prevArrowBtn);
			WebElement element = lists.get(i);
				//Step 2 Verify Amazon Header Bidder is Running

				if (element.isDisplayed() && element.isEnabled() == true) {
					globalPage.click(element);
					//element.click();
					APPLICATION_LOGS.debug("prev arrow is clickable on site :"+url);
					System.out.println("prev arrow is clickable on site :"+url);
					System.out.println("Test case : PASSED !!");
				}
				else{
					APPLICATION_LOGS.debug("prev arrow is not clickable on site :"+url);
					System.out.println("prev arrow is not clickable on site :"+url);
					WebDriverCommonLib.getScreenShot(driver, "Error in isElementClickable");
					assertTrue(element.isDisplayed() && element.isEnabled()==true,"prev arrow is not clickable on site :"+url);
					System.out.println("Test case : FAILED !!");
				}
				//Step 2 Verify Amazon Header Bidder is Running
				//globalPage.isElementClickable(url, prevArrowBtn, leftBtn);
				Thread.sleep(3000);
		}
		}
	}

	@Test(dependsOnMethods = {"verify_PlaylistDots_RD_VideoPage"}, alwaysRun = true)
	public void verify_PlaylistNextArrowBtn_RD_VideoPage() throws IOException, InterruptedException {
		
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step
				String rightBtn = "Right Arrow";
				List<WebElement> links = driver.findElements(nextArrowBtn);
				int count = links.size();
				System.out.println("Total no of next navigation in video page are: " + count);
				
				if(count != 0){
				//int links = driver.findElements(nextArrowBtn).size();
				//System.out.println("Total no of next navigation in video page are: " + links);

				for (int i = 0; i < count; i++) {
					System.out.println(i);
					List<WebElement> lists = driver.findElements(nextArrowBtn);
					WebElement element = lists.get(i);
						//Step 2 Verify Amazon Header Bidder is Running
					if (element.isDisplayed() && element.isEnabled() == true) {
						globalPage.click(element);
						Thread.sleep(2000);
						//element.click();
						APPLICATION_LOGS.debug("Right arrow is clickable on site :"+url);
						System.out.println("Right arrow is clickable on site :"+url);
						System.out.println("Test case : PASSED !!");
					}
					else{
						APPLICATION_LOGS.debug("Right arrow is not clickable on site :"+url);
						System.out.println("Right arrow is not clickable on site :"+url);
						WebDriverCommonLib.getScreenShot(driver, "Error in isElementClickable");
						assertTrue(element.isDisplayed() && element.isEnabled()==true,"Right arrow is not clickable on site :"+url);
						System.out.println("Test case : FAILED !!");
					}
					//Step 2 Verify Amazon Header Bidder is Running
					//globalPage.isElementClickable(url, prevArrowBtn, leftBtn);
					
			}
				}
				else
				{
					Assert.fail("Test case : FAILED, video next navigation are not available");
				}
	}

	@Test(dependsOnMethods = {"verify_PlaylistPreviousArrowBtn_RD_VideoPage"}, alwaysRun = true)
	public void verify_PlaylistDots_RD_VideoPage() throws IOException, InterruptedException {
		
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step
		String dots = "Dots";
		List<WebElement> links = driver.findElements(dotsBtn);
		int count = links.size();
		System.out.println("Total no of dots button in video page are: " + count);
		
		if(count != 0){
		
		for (int i = 0; i < count; i++) {
			System.out.println(i);
			List<WebElement> lists = driver.findElements(dotsBtn);
			WebElement element = lists.get(i);
			//Step 2 Verify Amazon Header Bidder is Running
		if (element.isDisplayed() && element.isEnabled() == true) {
			globalPage.click(element);
			Thread.sleep(2000);
			//element.click();
			APPLICATION_LOGS.debug("dots are clickable on site :"+url);
			System.out.println("Dots are clickable on site :"+url);
			System.out.println("Test case : PASSED !!");
		}
		else{
			APPLICATION_LOGS.debug("Dots are not clickable on site :"+url);
			System.out.println("Dots are not clickable on site :"+url);
			WebDriverCommonLib.getScreenShot(driver, "Error in isElementClickable");
			assertTrue(element.isDisplayed() && element.isEnabled()==true,"Dots are not clickable on site :"+url);
			System.out.println("Test case : FAILED !!");
			}
		}
		}
		else
		{
			Assert.fail("Test case : FAILED, video dots are not available");
		}
			
	}
	@AfterMethod
	public void nameAfter(Method method)
	{
	    System.out.println("Test : " + method.getName() + " execution completed .................... \n");    
	    System.out.println("********************************************************************************************");
	}
}


