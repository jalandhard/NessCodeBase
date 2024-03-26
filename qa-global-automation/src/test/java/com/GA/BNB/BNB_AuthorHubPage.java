package com.GA.BNB;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;


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
import com.wp.genericLib.WebDriverCommonLib;
import com.wp.pageObjectRepo.GlobalFunctions;

public class BNB_AuthorHubPage extends Driver {
	//Initialize object using page factory method 
	ConfigFileReader configFileReader;

	public Logger APPLICATION_LOGS = LogManager.getLogger("AppLogger");

	//Test Data
	String url=Constant.BNB_AuthorHub;
	String desiredURL = Constant.BNB_AuthorHub;
	String newspapaerSignUpLink = Constant.BNB_NewspaperSignUpLink;
	////String diyuLink = Constant.diyuLogoLink;

	private By banner = By.xpath("//div[@id='desktop-nav-banner']//a");

	private By authorDetails = By.xpath("//div[@class='author-header']//div");
	private By authorName= By.xpath("//div[@class='name-url']");
	private By authorPhoto = By.xpath("//div[@class='author-photo']");
	//hero container locators
	private By heroLink = By.xpath("//div[@class='hero-text']//a");
	private By heroImage=By.xpath("//a[@class='pure-u-sm-2-5 hero-image']"); ////img[@class='initial loading']
	private By heroExcerpt = By.xpath("//div[@class='hero-excerpt']");
	//Hero container
	//private By heroImage = By.xpath("//a[@class='pure-u-sm-2-5 hero-image']//div[@class='image-tout-container']"); //html/body/main/section[3]/div[1]/a/div
	//private By heroTitle = By.xpath("//h3[@class='entry-title']");    //h3[@class='entry-title'] /html/body/main/section[3]/div[1]/div/div/h3

	//5*1 featured posts module - Main Container
	private By feauredPostLinks_5by1Modules = By.xpath("//ul[@class='featured-posts']//li[@class='featured-container']//a[1]");
	private By feauredPostImgs_5by1Modules = By.xpath("//ul[@class='featured-posts']//div[@class='image-tout-container']//img");

	//4*2 featured posts module - Main Container
	private By feauredPostLinks_4by5Modules = By.xpath("//div[@class='single-recipe']//a[1]");  //div[@class='single-recipe']/a/div/h4");
	private By feauredPostImgs_4by5Modules = By.xpath("//div[@class='single-recipe']/a/div/img");
	private By feauredPostExcerpts_4by5Modules = By.xpath("//div[@class='single-recipe']//div[@class='recipe-excerpt']");

	//Newsletter sign up container
	private By newsletterText = By.xpath("//div[@class='col-2 newsletter-signup']/h2");
	private By newsletterSignupInputField =   By.xpath("//div[@class='col-2 newsletter-signup']//input");
	private By newsletterSignupButton =By.xpath("//div[@class='col-2 newsletter-signup']//a");

	//pagination
	private By pagination = By.xpath("//div[@class='pagination']");
	private By nextPage = By.xpath("//a[@class='next page-numbers']");
	private By prevPage = By.xpath("//a[@class='prev page-numbers']"); //prev page-numbers


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


	@Test()
	public void verify_Banner_BNB_AuthorPage() throws IOException, InterruptedException {

		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step
		String element = "Banner";

		//Step 2 Verify Amazon Header Bidder is Running
		globalPage.verifyIsBannerAvailable(banner, url);  //(banner, url, element);
	}

	@Test(dependsOnMethods = {"verify_Banner_BNB_AuthorPage"}, alwaysRun = true)
	public void verify_AuthorDetails_BNB_AuthorPage() throws IOException, InterruptedException {

		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step
		String element = "author name";
		String ele = "Author photo ";

		//Step 2 Verify Amazon Header Bidder is Running
		globalPage.verifyIsElementAvailable(authorPhoto, url, ele);
		globalPage.verifyIsElementAvailable(authorName, url, element);
	}

	@Test(dependsOnMethods = {"verify_AuthorDetails_BNB_AuthorPage"}, alwaysRun = true)
	public void verify_HeroContainer_LinkWorking_BNB_AuthorPage() throws IOException, InterruptedException {
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);
		//Test Step
		String element = "Hero Container Link";
		//Step 2 Verify Amazon Header Bidder is Running
		//globalPage.verifyIsElementWorking(heroLink, url, element);
		globalPage.verifyPostsImages_Clickable(heroLink, url);
		globalPage.verifyLinkStatus(heroLink, url, element);
	}

	@Test(dependsOnMethods = {"verify_HeroContainer_LinkWorking_BNB_AuthorPage"}, alwaysRun = true)
	public void verify_HeroContainer_ImageWorking_BNB_AuthorPage() throws IOException, InterruptedException {
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);
		//Test Step
		String element = "Hero Container Image ";
		globalPage.verifyPostsImages_Clickable_BNB(heroImage, url);
		//Step 2 Verify Amazon Header Bidder is Running
		//globalPage.verifyIsElementWorking(heroImage, url, element);
	}

	@Test(dependsOnMethods = {"verify_HeroContainer_ImageWorking_BNB_AuthorPage"}, alwaysRun = true)
	public void verify_HeroContainer_Excerpt_BNB_AuthorPage() throws IOException, InterruptedException {
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);
		//Test Step
		String element = "Hero Container Excerpt";
		globalPage.verifyPostsImages_Clickable(heroExcerpt, url);
		//Step 2 Verify Amazon Header Bidder is Running
		//globalPage.verifyIsElementWorking(heroExcerpt, url, element);
	}


	@Test(dependsOnMethods = {"verify_HeroContainer_Excerpt_BNB_AuthorPage"}, alwaysRun = true)
	public void verify_5By1_featuredPostsLinks_BNB_AuthorPage() throws IOException, InterruptedException
	{
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		List<WebElement> links = driver.findElements(feauredPostLinks_5by1Modules);
        int count = links.size();
        System.out.println("Total no of posts in 5By1 Container module are: " + count);
	//	globalPage.verifyBrowseByList_BNB(feauredPostLinks_5by1Modules, url);
		globalPage.verifyPostsImages_Clickable(feauredPostLinks_5by1Modules, url);
		globalPage.verifyBrowseByList(feauredPostLinks_5by1Modules, url);
	}

	@Test(dependsOnMethods = {"verify_5By1_featuredPostsLinks_BNB_AuthorPage"}, alwaysRun = true)
	public void verify_5By1_featuredPostsImgs_BNB_AuthorPage() throws IOException, InterruptedException
	{
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		List<WebElement> links = driver.findElements(feauredPostImgs_5by1Modules);
        int count = links.size();
		System.out.println("Total no of images in 5By1 Container module are: " + count);
		//globalPage.verifyBrowseByList(feauredPostImgs_5by1Modules, url);
		globalPage.verifyPostsImages_Clickable_BNB(feauredPostImgs_5by1Modules, url);
	}

	@Test(dependsOnMethods = {"verify_5By1_featuredPostsImgs_BNB_AuthorPage"}, alwaysRun = true)
	public void verifyFirst_4By5_featuredPostsLinks_BNB_AuthorPage() throws IOException, InterruptedException
	{
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);
		List<WebElement> links = driver.findElements(feauredPostLinks_4by5Modules);
        int count = links.size();
		System.out.println("Total no of posts in 4By5 Container module are: " + count);
		//globalPage.verifyBrowseByList_BNB(feauredPostLinks_4by5Modules, url);
		globalPage.verifyPostsImages_Clickable(feauredPostLinks_4by5Modules, url);
		globalPage.verifyBrowseByList(feauredPostLinks_4by5Modules, url);
	}

	@Test(dependsOnMethods = {"verifyFirst_4By5_featuredPostsLinks_BNB_AuthorPage"}, alwaysRun = true)
	public void verifyFirst_4By5_featuredPostsImgs_BNB_AuthorPage() throws IOException, InterruptedException
	{
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);
		List<WebElement> links = driver.findElements(feauredPostImgs_4by5Modules);
        int count = links.size();
		System.out.println("Total no of images in 4By5 Container module are: " + count);
		//globalPage.verifyBrowseByList(feauredPostImgs_4by2Modules, url);
		globalPage.verifyPostsImages_Clickable_BNB(feauredPostImgs_4by5Modules, url);
	}

	@Test(dependsOnMethods = {"verifyFirst_4By5_featuredPostsImgs_BNB_AuthorPage"}, alwaysRun = true)
	public void verifyFirst_4By5_featuredPostsExcerpts_BNB_AuthorPage() throws IOException, InterruptedException
	{
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);
		String element = "4By5 Post excerpts ";
		List<WebElement> links = driver.findElements(feauredPostExcerpts_4by5Modules);
        int count = links.size();
		System.out.println("Total no of excerpts in 4By5 Container module are: " + count);
		//globalPage.verifyIsElementAvailable(feauredPostExcerpts_4by2Modules, url, element);
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//section[6]//div[@class='pure-g recipes']//div[1]//div[1]//h4[1]")));
		//globalPage.verifyBrowseByList(feauredPostExcerpts_4by2Modules, url);
		globalPage.verifyPostsImages_Clickable_BNB(feauredPostExcerpts_4by5Modules, url);
	}

	@Test(dependsOnMethods = {"verifyFirst_4By5_featuredPostsExcerpts_BNB_AuthorPage"}, alwaysRun = true)
	public void verify_NewsletterSignUpPage_BNB_AuthorPage() throws IOException, InterruptedException
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

	@Test(dependsOnMethods = {"verify_NewsletterSignUpPage_BNB_AuthorPage"}, alwaysRun = true)
	public void verify_Pagination_BNB_AuthorPage() throws IOException, InterruptedException
	{
		///Object Initialization
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
