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
import org.openqa.selenium.interactions.Action;
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

public class FHM_AuthorHubPage extends Driver{
	//Initialize object using page factory method 
	ConfigFileReader configFileReader;
	public Logger APPLICATION_LOGS = LogManager.getLogger("AppLogger");

	//Test Data
	String url=Constant.FHM_AuthorHub;
	String desiredURL = Constant.FHM_AuthorHub;
	String newspapaerSignUpLink = Constant.FHM_NewspaperSignUpLink;
	//String diyuLink = Constant.diyuLogoLink;


	private By banner = By.xpath("//div[@id='desktop-nav-banner']//a");  //h4");


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
	private By feauredPostLinks_5by1Modules = By.xpath("//ul[@class='featured-posts']//li[@class='featured-container']//a");
	private By feauredPostImgs_5by1Modules = By.xpath("//ul[@class='featured-posts']//div[@class='image-tout-container']//img[1]");

	//4*2 featured posts module - Main Container
	private By feauredPostLinks_4by2Modules = By.xpath("//section[6]//div[@class='pure-g recipes']//a");
	private By feauredPostImgs_4by2Modules = By.xpath("//section[6]//div//img[1]");
	private By feauredPostExcerpts_4by2Modules = By.xpath("//section[6]//div[@class='recipe-excerpt']");

	//4*2 featured posts module - Main Container
	private By sec_featuredPostLinks_4by2Modules = By.xpath("//section[8]//div[@class='pure-g recipes']//a");
	private By sec_featuredPostImgs_4by2Modules = By.xpath("//section[8]//div//img[1]");
	private By sec_featuredPostExcerpts_4by2Modules = By.xpath("//section[8]//div[@class='recipe-excerpt']");

	//4*1 featured posts module - Main Container
	private By feauredPostLinks_4by1Modules = By.xpath("//section[9]//div[@class='pure-g recipes']//a");
	private By feauredPostImgs_4by1Modules = By.xpath("//section[9]//div//img[1]");
	private By feauredPostExcerpts_4by1Modules = By.xpath("//section[9]//div[@class='recipe-excerpt']");

	//Newsletter sign up container
	private By newsletterText = By.xpath("//div[@class='nl-container']//div[@class='newsletter']");
	private By newsletterSignupInputField= By.xpath("//div[@class='nl-container']//input[@id='email']");
	private By newsletterSignupButtonLocator= By.xpath("//div[@class='col-2 newsletter-signup']//form");

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
	public void verify_Banner_FHM_AuthorPage() throws IOException, InterruptedException {

		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step
		String element = "Banner";

		//Step 2 Verify Amazon Header Bidder is Running
		globalPage.verifyIsBannerAvailable(banner, url);  //(banner, url, element);
	}

	@Test(dependsOnMethods = {"verify_Banner_FHM_AuthorPage"}, alwaysRun = true)
	public void verify_AuthorDetails_FHM_AuthorPage() throws IOException, InterruptedException {

		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step
		String element = "author name";
		String ele = "Author photo ";

		//Step 2 Verify Amazon Header Bidder is Running
		globalPage.verifyIsElementAvailable(authorPhoto, url, ele);
		globalPage.verifyIsElementAvailable(authorName, url, element);
	}

	@Test(dependsOnMethods = {"verify_AuthorDetails_FHM_AuthorPage"}, alwaysRun = true)
	public void verify_HeroContainer_LinkWorking_FHM_AuthorPage() throws IOException, InterruptedException {

		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step
		String element = "Hero Container Link";

		//Step 2 Verify Amazon Header Bidder is Running
		//globalPage.verifyIsElementWorking(heroLink, url, element);
		globalPage.verifyPostsImages_Clickable(heroLink, url);
		globalPage.verifyLinkStatus(heroLink, url, element);
	}

	@Test(dependsOnMethods = {"verify_HeroContainer_LinkWorking_FHM_AuthorPage"}, alwaysRun = true)
	public void verify_HeroContainer_ImageWorking_FHM_AuthorPage() throws IOException, InterruptedException {

		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step
		String element = "Hero Container Image ";
		globalPage.verifyPostsImages_Clickable(heroImage, url);
		//Step 2 Verify Amazon Header Bidder is Running
		//globalPage.verifyIsElementWorking(heroImage, url, element);
	}

	/*@Test(dependsOnMethods = {"verify_HeroContainer_ImageWorking_FHM_AuthorPage"}, alwaysRun = true)
	public void verify_HeroContainer_Excerpt_FHM_AuthorPage() throws IOException, InterruptedException {

		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step
		String element = "Hero Container Excerpt";
		globalPage.verifyPostsImages_Clickable(heroExcerpt, url);
		//Step 2 Verify Amazon Header Bidder is Running
		//globalPage.verifyIsElementWorking(heroExcerpt, url, element);
	}*/


	@Test(dependsOnMethods = {"verify_HeroContainer_ImageWorking_FHM_AuthorPage"}, alwaysRun = true)
	public void verify_5By1_featuredPostsLinks_FHM_AuthorPage() throws IOException, InterruptedException
	{
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);
		
		int links = driver.findElements(feauredPostLinks_5by1Modules).size();
		System.out.println("Total no of posts in 5By1 Container module are: " + links);
		globalPage.verifyPostsImages_Clickable(feauredPostLinks_5by1Modules, url);
		globalPage.verifyBrowseByList(feauredPostLinks_5by1Modules, url);
		
	}

	@Test(dependsOnMethods = {"verify_5By1_featuredPostsLinks_FHM_AuthorPage"}, alwaysRun = true)
	public void verify_5By1_featuredPostsImgs_FHM_AuthorPage() throws IOException, InterruptedException
	{
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		int links = driver.findElements(feauredPostImgs_5by1Modules).size();
		System.out.println("Total no of images in 5By1 Container module are: " + links);
		//globalPage.verifyBrowseByList(feauredPostImgs_5by1Modules, url);
		globalPage.verifyPostsImages_Clickable(feauredPostImgs_5by1Modules, url);
	}

	@Test(dependsOnMethods = {"verify_5By1_featuredPostsImgs_FHM_AuthorPage"}, alwaysRun = true)
	public void verifyFirst_4By2_featuredPostsLinks_FHM_AuthorPage() throws IOException, InterruptedException
	{
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		int links = driver.findElements(feauredPostLinks_4by2Modules).size();
		System.out.println("Total no of posts in 4By2 Container module are: " + links);
		globalPage.verifyPostsImages_Clickable(feauredPostLinks_4by2Modules, url);
		globalPage.verifyBrowseByList(feauredPostLinks_4by2Modules, url);
	}

	@Test(dependsOnMethods = {"verifyFirst_4By2_featuredPostsLinks_FHM_AuthorPage"}, alwaysRun = true)
	public void verifyFirst_4By2_featuredPostsImgs_FHM_AuthorPage() throws IOException, InterruptedException
	{
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		int links = driver.findElements(feauredPostImgs_4by2Modules).size();
		System.out.println("Total no of images in 4By2 Container module are: " + links);
		//globalPage.verifyBrowseByList(feauredPostImgs_4by2Modules, url);
		globalPage.verifyPostsImages_Clickable(feauredPostImgs_4by2Modules, url);
	}

	@Test(dependsOnMethods = {"verifyFirst_4By2_featuredPostsLinks_FHM_AuthorPage"}, alwaysRun = true)
	public void verifyFirst_4By2_featuredPostsExcerpts_FHM_AuthorPage() throws IOException, InterruptedException
	{
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);
		String element = "4By2 Post excerpts ";
		int links = driver.findElements(feauredPostExcerpts_4by2Modules).size();
		System.out.println("Total no of excerpts in 4By2 Container module are: " + links);
		//globalPage.verifyIsElementAvailable(feauredPostExcerpts_4by2Modules, url, element);
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//section[6]//div[@class='pure-g recipes']//div[1]//div[1]//h4[1]")));
		//globalPage.verifyBrowseByList(feauredPostExcerpts_4by2Modules, url);
		globalPage.verifyPostsImages_Clickable(feauredPostExcerpts_4by2Modules, url);
	}
	@Test(dependsOnMethods = {"verifyFirst_4By2_featuredPostsExcerpts_FHM_AuthorPage"}, alwaysRun = true)
	public void verifySec_4By2_featuredPostsLinks_FHM_AuthorPage() throws IOException, InterruptedException
	{
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		int links = driver.findElements(sec_featuredPostLinks_4by2Modules).size();
		System.out.println("Total no of posts in second 4By2 Container module are: " + links);
		globalPage.verifyPostsImages_Clickable(sec_featuredPostLinks_4by2Modules, url);
		globalPage.verifyBrowseByList(sec_featuredPostLinks_4by2Modules, url);
	}

	@Test(dependsOnMethods = {"verifySec_4By2_featuredPostsLinks_FHM_AuthorPage"}, alwaysRun = true)
	public void verifySec_4By2_featuredPostsImgs_FHM_AuthorPage() throws IOException, InterruptedException
	{
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		int links = driver.findElements(sec_featuredPostImgs_4by2Modules).size();
		System.out.println("Total no of images in second 4By2 Container module are: " + links);
		//globalPage.verifyBrowseByList(sec_featuredPostImgs_4by2Modules, url);
		globalPage.verifyPostsImages_Clickable(sec_featuredPostImgs_4by2Modules, url);
	}

	@Test(dependsOnMethods = {"verifySec_4By2_featuredPostsImgs_FHM_AuthorPage"}, alwaysRun = true)
	public void verifySec_4By2_featuredPostsExcerpts_FHM_AuthorPage() throws IOException, InterruptedException
	{
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//section[6]/div[1]/div[1]//a[1]//div[1]//img[1]")));
		int links = driver.findElements(sec_featuredPostExcerpts_4by2Modules).size();
		System.out.println("Total no of excerpts in second 4By2 Container module are: " + links);
	//	globalPage.verifyBrowseByList(sec_featuredPostExcerpts_4by2Modules, url);
		globalPage.verifyPostsImages_Clickable(sec_featuredPostExcerpts_4by2Modules, url);
	}


	@Test(dependsOnMethods = {"verifySec_4By2_featuredPostsExcerpts_FHM_AuthorPage"}, alwaysRun = true)
	public void verify_4By1_featuredPostsLinks_FHM_AuthorPage() throws IOException, InterruptedException
	{
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		int links = driver.findElements(feauredPostLinks_4by1Modules).size();
		System.out.println("Total no of posts in 4By1 Container module are: " + links);
		globalPage.verifyPostsImages_Clickable(feauredPostLinks_4by1Modules, url);
		globalPage.verifyBrowseByList(feauredPostLinks_4by1Modules, url);
	}

	@Test(dependsOnMethods = {"verify_4By1_featuredPostsLinks_FHM_AuthorPage"}, alwaysRun = true)
	public void verify_4By1_featuredPostsImgs_FHM_AuthorPage() throws IOException, InterruptedException
	{
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		int links = driver.findElements(feauredPostImgs_4by1Modules).size();
		System.out.println("Total no of images in 4By1 Container module are: " + links);
		//globalPage.verifyBrowseByList(feauredPostImgs_4by1Modules, url);
		globalPage.verifyPostsImages_Clickable(feauredPostImgs_4by1Modules, url);
	}

	@Test(dependsOnMethods = {"verify_4By1_featuredPostsImgs_FHM_AuthorPage"}, alwaysRun = true)
	public void verify_4By1_featuredPostsExcerpts_FHM_AuthorPage() throws IOException, InterruptedException
	{
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		int links = driver.findElements(feauredPostExcerpts_4by1Modules).size();
		System.out.println("Total no of images in 4By1 Container module are: " + links);
		//globalPage.verifyBrowseByList(feauredPostExcerpts_4by1Modules, url);
		globalPage.verifyPostsImages_Clickable(feauredPostExcerpts_4by1Modules, url);
	}
	

	@Test(dependsOnMethods = {"verify_4By1_featuredPostsExcerpts_FHM_AuthorPage"}, alwaysRun = true)
	public void verify_Pagination_FHM_AuthorPage() throws IOException, InterruptedException
	{
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);
		try
		{            		   
			globalPage.scrollToElement(pagination); //pagination
		}
		catch(Exception e){
			//System.out.println("The Hide/Show console not present");
			APPLICATION_LOGS.info("pagination not present");
		}
		globalPage.verifyPagination(url, nextPage, prevPage);
	}

	@Test(dependsOnMethods = {"verify_Pagination_FHM_AuthorPage"}, alwaysRun = true)
	public void verify_NewsletterSignUpPage_FHM_AuthorPage() throws IOException, InterruptedException
	{
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);
	
		/*Actions act = new Actions(driver);
		WebElement ele = driver.findElement(newsletterText);
		act.moveToElement(ele);*/
		//Test Step
		globalPage.verifyNewsletterSignUp_Footer(url, newsletterSignupInputField, newsletterSignupButtonLocator, newspapaerSignUpLink );
	}






	@AfterMethod
	public void nameAfter(Method method)
	{
		System.out.println("Test : " + method.getName() + " execution completed .................. \n"); 
		System.out.println("********************************************************************************************");
	}

}


