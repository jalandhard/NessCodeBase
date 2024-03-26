package com.GA.FHM;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.lang.reflect.Method;
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

public class FHM_LegacyProjectDetailPage extends Driver{
	//Initialize object using page factory method 
	ConfigFileReader configFileReader;
	public Logger APPLICATION_LOGS = LogManager.getLogger("AppLogger");

	//Test Data
	String url=Constant.FHM_LegacyProjectDetailPage;
	String newspaperSignUpLink = Constant.FHM_NewspaperSignUpLink;
	//String diyuLink = Constant.diyuLogoLink;


	private By title= By.xpath("//div[@class='pure-u-1 pure-u-md-14-24 type-project']/h1");
	private By dek= By.className("dek");

	private By socialShareLinks = By.xpath("//section[@class='social-menu-desktop pure-u-lg-2-24']//a");
	//intro
	private By introImg = By.xpath("//div[@class='intro']/div[@class='image']");
	private By introParagraph = By.xpath("//div[@class='intro']/div[@class='details']");

	//entry content
	private By entryContent = By.className("content");
	private By entrySlideshow = By.xpath("//section[@class='slideshow']");

	//recirc module
	private By similarProjects = By.className("recirc-module");

	//entry footer
	private By entryFooter = By.xpath("//div[@class='non-contextual-video']");


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


	/*@Test()
	public void verify_BreadCrumb_FHM_LegacyProjectDetailPage() throws IOException, InterruptedException {

		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step
		//Step 2 Verify Amazon Header Bidder is Running
		globalPage.verifyIsBreadCrumbAvailable(Constant.breadcrumbLocator, url);
		//driver.navigate().to(url);

	}*/

	@Test//(dependsOnMethods = {"verify_BreadCrumb_FHM_LegacyProjectDetailPage"}, alwaysRun = true)
	public void verify_Title_FHM_LegacyProjectDetailPage() throws IOException, InterruptedException {

		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		String element = "Title";
		//Test Step
		//Step 2 Verify Amazon Header Bidder is Running
		globalPage.verifyIsElementAvailable(title, url, element);


	}

	@Test(dependsOnMethods = {"verify_Title_FHM_LegacyProjectDetailPage"}, alwaysRun = true)
	public void verify_Dek_FHM_LegacyProjectDetailPage() throws IOException, InterruptedException {
		String dekTest = "Dek";
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step
		globalPage.verifyIsElementAvailable(dek, url, dekTest);
	}

	@Test(dependsOnMethods = {"verify_Dek_FHM_LegacyProjectDetailPage"}, alwaysRun = true)
	public void verify_introImg_FHM_LegacyProjectDetailPage() throws IOException, InterruptedException {
		String dekTest = "Introduction Image ";
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step
		globalPage.verifyIsElementAvailable(introImg, url, dekTest);
	}

	@Test(dependsOnMethods = {"verify_introImg_FHM_LegacyProjectDetailPage"}, alwaysRun = true)
	public void verify_introParagraph_FHM_LegacyProjectDetailPage() throws IOException, InterruptedException {
		String element = "Intro paragraph";
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step
		globalPage.verifyIsElementAvailable(introParagraph, url, element);
	}

	@Test(dependsOnMethods = {"verify_introParagraph_FHM_LegacyProjectDetailPage"}, alwaysRun = true)
	public void verify_entryContent_FHM_LegacyProjectDetailPage() throws IOException, InterruptedException {
		String element = "Entry content";
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step
		globalPage.verifyIsElementAvailable(entryContent, url, element);
	}

	@Test(dependsOnMethods = {"verify_entryContent_FHM_LegacyProjectDetailPage"}, alwaysRun = true)
	public void verify_SimilarProjects_FHM_ProjectDetailPage() throws IOException, InterruptedException
	{
		//Object Initialization
		String element = "similar Projects panel ";
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step
		//Step 2 Verify Amazon Header Bidder is Running
		globalPage.verifyIsElementAvailable(similarProjects, url, element);
	}

	@Test(dependsOnMethods = {"verify_SimilarProjects_FHM_ProjectDetailPage"}, alwaysRun = true)
	public void verify_entryFooter_FHM_LegacyProjectDetailPage() throws IOException, InterruptedException {
		String element = "Entry footer";
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step
		globalPage.verifyIsElementAvailable(entryFooter, url, element);
	}

	@Test(dependsOnMethods = {"verify_entryFooter_FHM_LegacyProjectDetailPage"}, alwaysRun = true)
	public void verify_entrySlideshow_FHM_LegacyProjectDetailPage() throws IOException, InterruptedException {
		String element = "Entry slideshow";
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step
		globalPage.verifyIsElementAvailable(entrySlideshow, url, element);
	}

	@Test(dependsOnMethods = {"verify_entrySlideshow_FHM_LegacyProjectDetailPage"}, alwaysRun = true)
	public void verifySocialShareLinks_FHM_LegacyProjectDetailPage() throws IOException, InterruptedException
	{
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step
		globalPage.scrollToElement(entrySlideshow);
		globalPage.socialShareNavigation(entrySlideshow, socialShareLinks, url);
	}
	
	/*//Social media tests
	//Social media icons open new tabs when opened
	//Find better way to determine if we are on the brands page or a 404 page
	
	@Test(dependsOnMethods = {"verify_NewsletterSignUpPage_FooterSection"}, alwaysRun = true)
	public void verifyFacebookLink_FooterSection() throws IOException, InterruptedException {
		String facebookLink = "facebook.com";

		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step
		//Step 2 Verify Amazon Header Bidder is Running
		globalPage.Test(url, facebookIconLocator, facebookIconLocator,facebookLink );
	}


	@Test(dependsOnMethods = {"verifyFacebookLink_FooterSection"}, alwaysRun = true)
	public void verify_InstagramLink_FooterSection() throws InterruptedException, IOException {

		String instaLink = "instagram.com";
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step
		//Step 2 Verify Amazon Header Bidder is Running
		globalPage.Test(url, instagramIconLocator, instagramIconLocator,instaLink );	

	}


	@Test(dependsOnMethods = {"verify_InstagramLink_FooterSection"}, alwaysRun = true)
	public void verifyTwitterLink_FooterSection() throws InterruptedException, IOException {

		String twittertLink = "twitter.com";
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step
		//Step 2 Verify Amazon Header Bidder is Running
		globalPage.Test(url, twitterIconLocator, twitterIconLocator,twittertLink );	

	}
	
	@Test(dependsOnMethods = {"verify_InstagramLink_FooterSection"}, alwaysRun = true)
	public void verifyYouTubeLink_FooterSection() throws InterruptedException, IOException {

		String utubeLink = "pinterest.com";
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step
		//Step 2 Verify Amazon Header Bidder is Running
		globalPage.Test(url, youtubeIconLocator, youtubeIconLocator,utubeLink );	

	}
	
	@Test(dependsOnMethods = {"verify_InstagramLink_FooterSection"}, alwaysRun = true)
	public void verifyPinteresttLink_FooterSection() throws InterruptedException, IOException {

		String pinterestLink = "pinterest.com";
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step
		//Step 2 Verify Amazon Header Bidder is Running
		globalPage.Test(url, pinterestIconLocator, pinterestIconLocator,pinterestLink );	

	}
	

		//End social media tests
*/

	@AfterMethod
	public void nameAfter(Method method)
	{
		System.out.println("Test : " + method.getName() + " execution completed .................. \n");       
		System.out.println("********************************************************************************************");
	}


}
