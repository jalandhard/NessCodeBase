package com.GA.BNB;

import java.util.List;
import java.io.IOException;
import java.lang.reflect.Method;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.wp.genericLib.ConfigFileReader;
import com.wp.genericLib.Constant;
import com.wp.genericLib.Driver;
import com.wp.genericLib.Link;
import com.wp.pageObjectRepo.GlobalFunctions;

public class BNB_HubPage extends Driver {
	//Initialize object using page factory method 
	ConfigFileReader configFileReader;

	public Logger APPLICATION_LOGS = LogManager.getLogger("AppLogger");

	//Test Data
	String url=Constant.BNB_HubPage;
	String desiredURL = Constant.BNB_HubPage;
	String newspapaerSignUpLink = Constant.BNB_NewspaperSignUpLink;
	////String diyuLink = Constant.diyuLogoLink;

	//banner
	private By banner = By.xpath("//div[@id='desktop-nav-banner']//a");
	//Hero container
	private By heroImage = By.xpath("//a[@class='pure-u-sm-2-5 hero-image']//div[@class='image-tout-container']"); //html/body/main/section[3]/div[1]/a/div
	private By heroTitle = By.xpath("//h3[@class='entry-title']//a");    //h3[@class='entry-title'] /html/body/main/section[3]/div[1]/div/div/h3

	//5*1 featured posts module - Main Container
	private By feauredPostLinks_5by1Modules = By.xpath("//ul[@class='featured-posts']//li[@class='featured-container']//a");
	private By feauredPostImgs_5by1Modules = By.xpath("//ul[@class='featured-posts']//div[@class='image-tout-container']//img");

	//4*5 featured posts module - Main Container
	//4*5 featured posts module - Main Container
	private By featuredLinks_4by2 = By.xpath("//section[3]//div[2]//div//div//a");
	//private By feauredPostLinks_4by2Modules = By.xpath("//div[2]//div//div//a//div[2]//h4");
	private By feauredPostLinks_4by2Modules = By.xpath("//div[@class='single-recipe']/a[1]");
	private By feauredPostImgs_4by2Modules = By.xpath("//div[@class='single-recipe']/a/div[1]/img[1]");

	//5*1 featured posts module - Main Container
	private By feauredPostLinks_4by1Modules = By.xpath("//section[4]//h4");
	private By feauredPostImgs_4by1Modules = By.xpath("//section[4]//img");

	//Newsletter sign up container
	private By newsletterSignupInputField =   By.xpath("//div[@class='col-2 newsletter-signup']//input");
	private By newsletterSignupButton =By.xpath("//div[@class='col-2 newsletter-signup']//a");
	
	private By newsletterText=By.xpath("//div[@class='col-2 newsletter-signup']/h2");



	//featured_container
	private By featuredContainer5_1 = By.xpath("//ul[@class='featured-posts']//li//a");
	//pagination
	private By pagination = By.xpath("//div[@class='pagination']");
	private By nextPage = By.xpath("//a[@class='next page-numbers']");
	private By prevPage = By.xpath("//a[@class='prev page-numbers']");
	private Link nextPageLink = new Link(driver, nextPage, "https://www.constructionprotips.com/industry-news/page/2/");
	private Link prevPageLink = new Link(driver, prevPage, "https://www.constructionprotips.com/industry-news/"); 

	//DIYU logo
	private By universityLocator = By.xpath("//div[@class='nl-container']//div[@class='diyu-img']");
	private Link diyuLogoLink = new Link(driver, universityLocator, "");

	private By browseBy = By.xpath("//div[@class='tax-list']//a");

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
	public void verify_Banner_BNB_HubPage() throws IOException, InterruptedException{

		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);
		//Test Step
		globalPage.verifyIsBannerAvailable(banner, url);
	}
	@Test(dependsOnMethods = {"verify_Banner_BNB_HubPage"}, alwaysRun = true)
	public void verify_BreadCrumb_BNB_HubPage() throws IOException, InterruptedException {
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		String element = "Breadcrumb";
		globalPage.verifyIsElementAvailable(Constant.breadcrumbLocator, url, element);
		//globalPage.verifyIsBreadCrumbAvailable(Constant.breadcrumbLocator, url);
	}

	@Test(dependsOnMethods = {"verify_BreadCrumb_BNB_HubPage"}, alwaysRun = true)
	public void verify_Title_BNB_HubPage() throws IOException, InterruptedException {
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		String element = "Title";
		//Test Step
		globalPage.verifyIsElementAvailable(Constant.titleLocator, url, element);
	}

	@Test(dependsOnMethods = {"verify_Title_BNB_HubPage"}, alwaysRun = true)
	public void verify_ArchiveDescription_BNB_HubPage() throws IOException, InterruptedException {
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		String element = "Archive Description";
		//Test Step
		globalPage.verifyIsElementAvailable(Constant.descriptionLocator, url, element);
	}

	@Test(dependsOnMethods = {"verify_ArchiveDescription_BNB_HubPage"}, alwaysRun = true)
	public void verify_BrowseByList_BNB_HubPage() throws IOException, InterruptedException {
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step
		int links = driver.findElements(browseBy).size();
		System.out.println("Total no of links in Browse By module are: " + links);
		//globalPage.verifyBrowseByList_BNB(browseBy, url);
		globalPage.verifyPostsImages_Clickable(browseBy, url);
		globalPage.verifyBrowseByList(browseBy, url);
	}

	@Test(dependsOnMethods = {"verify_BrowseByList_BNB_HubPage"}, alwaysRun = true)
	public void verify_heroContainer_ImageWorking_BNB_HubPage() throws IOException, InterruptedException {
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step
		String element = "Hero Image";
		globalPage.verifyPostsImages_Clickable_BNB(Constant.heroImage, url);
		//Step 2 Verify Amazon Header Bidder is Running
		//globalPage.verifyIsElementWorking(Constant.heroImage, url, element);
	}

	@Test(dependsOnMethods = {"verify_heroContainer_ImageWorking_BNB_HubPage"}, alwaysRun = true)
	public void verify_heroContainer_LinkWorking_BNB_HubPage() throws IOException, InterruptedException {
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step
		String element = "Hero Link";
		//globalPage.verifyIsElementWorking_BNB(Constant.heroTitle, url, element);
		globalPage.verifyPostsImages_Clickable(heroTitle, url);
		globalPage.verifyLinkStatus(heroTitle, url, element);
	}

	@Test(dependsOnMethods = {"verify_heroContainer_LinkWorking_BNB_HubPage"}, alwaysRun = true)
	public void verify_5By1_featuredPostsLinks_BNB_HubPage() throws IOException, InterruptedException
	{
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		List<WebElement> links = driver.findElements(feauredPostLinks_5by1Modules);
        int count = links.size();
		System.out.println("Total no of posts in 5By1 Container module are: " + count);
		//globalPage.verifyBrowseByList_BNB(feauredPostLinks_5by1Modules, url);
		globalPage.verifyPostsImages_Clickable(feauredPostLinks_5by1Modules, url); //Constant.heroImage
		globalPage.verifyBrowseByList(feauredPostLinks_5by1Modules, url);
	}

	@Test(dependsOnMethods = {"verify_5By1_featuredPostsLinks_BNB_HubPage"}, alwaysRun = true)
	public void verify_5By1_featuredPostsImgs_BNB_HubPage() throws IOException, InterruptedException
	{
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		List<WebElement> links = driver.findElements(feauredPostImgs_5by1Modules);
        int count = links.size();
		System.out.println("Total no of images in 5By1 Container module are: " + count);
		//globalPage.verifyBrowseByList(feauredPostImgs_5by1Modules, url);
		globalPage.verifyPostsImages_Clickable_BNB(feauredPostImgs_5by1Modules, url);

	}

	@Test(dependsOnMethods = {"verify_5By1_featuredPostsImgs_BNB_HubPage"}, alwaysRun = true)
	public void verify_4By5_featuredPostsLinks_BNB_HubPage() throws IOException, InterruptedException
	{
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);
		String element = "featured links ";
		List<WebElement> links = driver.findElements(feauredPostLinks_4by2Modules);
        int count = links.size();
		System.out.println("Total no of posts in 4By5 Container module are: " + count);
		//globalPage.IsElementExist(url, feauredPostLinks_4by2Modules, element); // .verifyBrowseByList_BNB(feauredPostLinks_4by2Modules, url);
		globalPage.verifyPostsImages_Clickable_BNB(feauredPostLinks_4by2Modules, url); //Constant.heroImage
		globalPage.verifyBrowseByList(feauredPostLinks_4by2Modules, url);
	}

	@Test(dependsOnMethods = {"verify_4By5_featuredPostsLinks_BNB_HubPage"}, alwaysRun = true)
	public void verify_4By5_featuredPostsImgs_BNB_HubPage() throws IOException, InterruptedException
	{
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		List<WebElement> links = driver.findElements(feauredPostImgs_4by2Modules);
        int count = links.size();
		System.out.println("Total no of images in 4By5 Container module are: " + count);
		//globalPage.verifyBrowseByList(feauredPostImgs_4by2Modules, url);
		globalPage.verifyPostsImages_Clickable_BNB(feauredPostImgs_4by2Modules, url);
	}

	@Test(dependsOnMethods = {"verify_4By5_featuredPostsImgs_BNB_HubPage"}, alwaysRun = true)
	public void verify_NewsletterSignUpPage_BNB_HubPage() throws IOException, InterruptedException
	{
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);
		((JavascriptExecutor) driver).executeScript("window.scrollTo(document.body.scrollHeight, 0)");

		/*Actions act = new Actions(driver);
		WebElement ele = driver.findElement(newsletterText);
		act.moveToElement(ele);*/

		//Test Step
		globalPage.verifyNewsletterSignUp(url, newsletterSignupInputField, newsletterSignupButton, newspapaerSignUpLink );
	}

	@Test(dependsOnMethods = {"verify_NewsletterSignUpPage_BNB_HubPage"}, alwaysRun = true)
	public void verify_Pagination_BNB_HubPage() throws IOException, InterruptedException
	{
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		globalPage.scrollToElement(pagination); //featured module container
		globalPage.verifyPagination(url, nextPage, prevPage);
	}

	@AfterMethod
	public void nameAfter(Method method)
	{
		System.out.println("Test : " + method.getName() + " execution completed .................. \n"); 
		System.out.println("********************************************************************************************");
	}
}
