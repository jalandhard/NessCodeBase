package com.GA.BHC;

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
import org.openqa.selenium.support.ui.WebDriverWait;
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

public class BHC_HubPage extends Driver{
	//Initialize object using page factory method 
	ConfigFileReader configFileReader;

	public Logger APPLICATION_LOGS = LogManager.getLogger("AppLogger");

	//Test Data
	String url=Constant.BHC_HubPage;
	String desiredURL = Constant.BHC_HubPage;
	String newspapaerSignUpLink = Constant.BHC_NewspaperSignUpLink;
	////String diyuLink = Constant.diyuLogoLink;

	//banner
	private By banner = By.xpath("//div[@id='desktop-nav-banner']//a");
	//Hero container
	private By heroImage = By.xpath("//a[@class='pure-u-sm-2-5 hero-image']"); //html/body/main/section[3]/div[1]/a/div
	private By heroTitle = By.xpath("//h3[@class='entry-title']//a");    //h3[@class='entry-title'] /html/body/main/section[3]/div[1]/div/div/h3

	//5*1 featured posts module - Main Container
	private By feauredPostLinks_5by1Modules = By.xpath("//ul[@class='featured-posts']//li[@class='featured-container']//a");
	private By feauredPostImgs_5by1Modules = By.xpath("//ul[@class='featured-posts']//div[@class='image-tout-container']//img[1]");
	
	//4*2 featured posts module - Main Container
	private By featuredLinks_4by2 = By.xpath("//section[3]//div[2]//div//div//a");
	private By feauredPostLinks_4by2Modules = By.xpath("//div[2]//div//div//a//div[2]//h4");
	private By feauredPostImgs_4by2Modules = By.xpath("//div[2]//div//div//a//div//img[1]");
	
	//4*1 featured posts module - Main Container
	private By featuredLinks_4by1 = By.xpath("//section[4]//div[1]//div[1]//a");
	private By feauredPostLinks_4by1Modules = By.xpath("//section[4]//h4");
	private By feauredPostImgs_4by1Modules = By.xpath("//section[4]//img[1]");
	
	//Newsletter sign up container

	private By newsletterSignupInputField= By.xpath("//div[@class='nl-container']//input[@id='email']"); //By.xpath("//div[@class='col-2 newsletter-signup']//input[@placeholder='Email Address']");

	private By newsletterSignupButtonLocator=By.xpath("//div[@class='nl-container']//button[@id='subscribe']"); //By.xpath("//button[@class='at-element-click-tracking']"); //   //By.xpath("//button[@class='at-element-click-tracking']");

	

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

	private By cookieconsoleCloseBtn=By.xpath("//a[@id='cn-accept-cookie']");

	@BeforeClass
	public void navigateToRequiredURL() throws Exception{
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Step:1 Navigate required URL
		globalPage.navigateToRequiredSite(url);
		//WebElement cookieConsolemsg = driver.findElement(cookieconsoleCloseBtn);
		try{
			WebElement cookieConsolemsg = driver.findElement(cookieconsoleCloseBtn);
			//WebDriverWait wait1 = new WebDriverWait(driver, 10);
			if(cookieConsolemsg.isDisplayed())
			{
				/*new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(cookieconsoleCloseBtn));
				WebElement element1 = wait1.until(ExpectedConditions.elementToBeClickable(cookieConsolemsg));
				element1.click();*/
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

	@Test//(dependsOnMethods = {"verify_Pagination_BHC_HubPage"}, alwaysRun = true)
	public void verify_Banner_BHC_HubPage() throws IOException, InterruptedException{
		
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);
		//Test Step
		globalPage.verifyIsBannerAvailable(banner, url);
	}
	@Test(dependsOnMethods = {"verify_Banner_BHC_HubPage"}, alwaysRun = true)
	public void verify_BreadCrumb_BHC_HubPage() throws IOException, InterruptedException {
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		String element = "Breadcrumb";
		globalPage.verifyIsElementAvailable(Constant.breadcrumbLocator, url, element);
		//globalPage.verifyIsBreadCrumbAvailable(Constant.breadcrumbLocator, url);
	}

	@Test(dependsOnMethods = {"verify_BreadCrumb_BHC_HubPage"}, alwaysRun = true)
	public void verify_Title_BHC_HubPage() throws IOException, InterruptedException {
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);
		
		String element = "Title";
		//Test Step
		globalPage.verifyIsElementAvailable(Constant.titleLocator, url, element);
	}

	@Test(dependsOnMethods = {"verify_Title_BHC_HubPage"}, alwaysRun = true)
	public void verify_BrowseByList_BHC_HubPage() throws IOException, InterruptedException {
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);
		
		//Test Step
		int links = driver.findElements(browseBy).size();
		System.out.println("Total no of links in Browse By module are: " + links);
		globalPage.verifyPostsImages_Clickable(browseBy, url);
		globalPage.verifyBrowseByList(browseBy, url);
	}
	
	@Test(dependsOnMethods = {"verify_BrowseByList_BHC_HubPage"}, alwaysRun = true)
	public void verify_heroContainer_LinkWorking_BHC_HubPage() throws IOException, InterruptedException {
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step
		String element = "Hero Link";
		//globalPage.verifyPostsImages_Clickable(Constant.heroTitle, url);
		globalPage.verifyPostsImages_Clickable(heroTitle, url);
		globalPage.verifyLinkStatus(heroTitle, url, element);
		//globalPage.verifyIsElementWorking(Constant.heroTitle, url, element);
	}
	@Test(dependsOnMethods = {"verify_heroContainer_LinkWorking_BHC_HubPage"}, alwaysRun = true)
	public void verify_heroContainer_ImageWorking_BHC_HubPage() throws IOException, InterruptedException {
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step
		String element = "Hero Image";
		globalPage.verifyPostsImages_Clickable(Constant.heroImage, url);
		//Step 2 Verify Amazon Header Bidder is Running
		//globalPage.verifyIsElementWorking(Constant.heroImage, url, element);
	}

	@Test(dependsOnMethods = {"verify_heroContainer_ImageWorking_BHC_HubPage"}, alwaysRun = true)
	public void verify_5By1_featuredPostsLinks_BHC_HubPage() throws IOException, InterruptedException
	{
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		List<WebElement> links = driver.findElements(feauredPostLinks_5by1Modules);
		int count = links.size();
		//int links = driver.findElements(feauredPostLinks_5by1Modules).size();
		System.out.println("Total no of posts in 5By1 Container module are: " + count);
		globalPage.verifyPostsImages_Clickable(feauredPostLinks_5by1Modules, url); 
		globalPage.verifyBrowseByList(feauredPostLinks_5by1Modules, url);
	}
	
	@Test(dependsOnMethods = {"verify_5By1_featuredPostsLinks_BHC_HubPage"}, alwaysRun = true)
	public void verify_5By1_featuredPostsImgs_BHC_HubPage() throws IOException, InterruptedException
	{
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);
		List<WebElement> links = driver.findElements(feauredPostImgs_5by1Modules);
		int count = links.size();
		//int links = driver.findElements(feauredPostImgs_5by1Modules).size();
		System.out.println("Total no of images in 5By1 Container module are: " + count);
		//globalPage.verifyBrowseByList(feauredPostImgs_5by1Modules, url);
		globalPage.verifyPostsImages_Clickable(feauredPostImgs_5by1Modules, url);
		
	}
	
	@Test(dependsOnMethods = {"verify_5By1_featuredPostsImgs_BHC_HubPage"}, alwaysRun = true)
	public void verify_4By2_featuredPostsLinks_BHC_HubPage() throws IOException, InterruptedException
	{
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);
		List<WebElement> links = driver.findElements(featuredLinks_4by2);
		int count = links.size();
		//int links = driver.findElements(feauredPostLinks_4by2Modules).size();
		System.out.println("Total no of posts in 4By2 Container module are: " + count);
		globalPage.verifyPostsImages_Clickable(featuredLinks_4by2, url); 
		globalPage.verifyBrowseByList(featuredLinks_4by2, url);
	}
	
	@Test(dependsOnMethods = {"verify_4By2_featuredPostsLinks_BHC_HubPage"}, alwaysRun = true)
	public void verify_4By2_featuredPostsImgs_BHC_HubPage() throws IOException, InterruptedException
	{
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);
		List<WebElement> links = driver.findElements(feauredPostImgs_4by2Modules);
		int count = links.size();
		//int links = driver.findElements(feauredPostImgs_4by2Modules).size();
		System.out.println("Total no of images in 4By2 Container module are: " + count);
		//globalPage.verifyBrowseByList(feauredPostImgs_4by2Modules, url);
		globalPage.verifyPostsImages_Clickable(feauredPostImgs_4by2Modules, url);
	}
	
	@Test(dependsOnMethods = {"verify_4By2_featuredPostsImgs_BHC_HubPage"}, alwaysRun = true)
	public void verify_4By1_featuredPostsLinks_BHC_HubPage() throws IOException, InterruptedException
	{
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);
		List<WebElement> links = driver.findElements(featuredLinks_4by1);
		int count = links.size();
		//int links = driver.findElements(feauredPostLinks_4by1Modules).size();
		System.out.println("Total no of posts in 4By1 Container module are: " + count);
		//globalPage.verifyBrowseByList(feauredPostLinks_4by1Modules, url);
		globalPage.verifyPostsImages_Clickable(featuredLinks_4by1, url); //Constant.heroImage
     	globalPage.verifyBrowseByList(featuredLinks_4by1, url);
	}
	
	@Test(dependsOnMethods = {"verify_4By1_featuredPostsLinks_BHC_HubPage"}, alwaysRun = true)
	public void verify_4By1_featuredPostsImgs_BHC_HubPage() throws IOException, InterruptedException
	{
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);
		List<WebElement> links = driver.findElements(feauredPostImgs_4by1Modules);
		int count = links.size();
		//int links = driver.findElements(feauredPostImgs_4by1Modules).size();
		System.out.println("Total no of images in 4By1 Container module are: " + count);
		//globalPage.verifyBrowseByList(feauredPostImgs_4by1Modules, url);
		globalPage.verifyPostsImages_Clickable(feauredPostImgs_4by1Modules, url); //Constant.heroImage
	}

	
	@Test(dependsOnMethods = {"verify_4By1_featuredPostsLinks_BHC_HubPage"}, alwaysRun = true)
	public void verify_Pagination_BHC_HubPage() throws IOException, InterruptedException
	{
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);
		try
		{            		   
			globalPage.scrollToElement(pagination); //featured module container
			globalPage.verifyPagination(url, nextPage, prevPage);
		}
		catch(Exception e){
			//System.out.println("The Hide/Show console not present");
			APPLICATION_LOGS.info("pagination not present");
		}
			
	}
	
	
	
	@AfterMethod
	public void nameAfter(Method method)
	{
	    System.out.println("Test : " + method.getName() + " execution completed .................. \n"); 
	    System.out.println("********************************************************************************************");
	}

}


	
