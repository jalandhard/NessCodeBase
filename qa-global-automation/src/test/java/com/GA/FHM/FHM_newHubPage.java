package com.GA.FHM;

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
import com.wp.genericLib.Link;
import com.wp.pageObjectRepo.GlobalFunctions;

public class FHM_newHubPage extends Driver {
	//Initialize object using page factory method 
	ConfigFileReader configFileReader;
	public Logger APPLICATION_LOGS = LogManager.getLogger("AppLogger");

	//Test Data
	String url=Constant.FHM_HubPage;
	String desiredURL = Constant.FHM_HubPage;
	String newspapaerSignUpLink = Constant.FHM_NewspaperSignUpLink;
	//String diyuLink = Constant.diyuLogoLink;

	//banner
	private By banner = By.xpath("//div[@id='desktop-nav-banner']//a");
	//Hero container
	private By heroImage = By.xpath("//a[@class='pure-u-1-1 pure-u-md-12-24 hero-image']//div[@class='image-tout-container']"); //html/body/main/section[3]/div[1]/a/div
	private By heroTitle = By.xpath("//h2[@class='entry-title h3']//a");    //h3[@class='entry-title'] /html/body/main/section[3]/div[1]/div/div/h3

	//5*1 featured posts module - Main Container
	//div[@class='pure-u-1 pure-u-sm-1-3 category-card']//a
	private By feauredPostLinks = By.xpath("//div[@class='single-recipe']//a[1]");  //div[@class='recipe-content']/h2");
	private By feauredPostImgs = By.xpath("//div[@class='single-recipe']/a/div/img[1]");

	//4*2 featured posts module - Main Container
	private By feauredPostLinks_4by2Modules = By.xpath("//div[2]//div//div//a//div[2]//h4");
	private By feauredPostImgs_4by2Modules = By.xpath("//div[2]//div//div//a//div//img");

	//5*1 featured posts module - Main Container
	private By feauredPostLinks_4by1Modules = By.xpath("//section[4]//h4");
	private By feauredPostImgs_4by1Modules = By.xpath("//section[4]//img");

	//Newsletter sign up container

	private By newsletterSignupInputField= By.xpath("(//div[@class='newsletter'])[1]//input[@name='email']"); //By.xpath("//div[@class='nl-container']//input[@id='email']");//By.xpath("//div[@class='nl-container']//input[@id='email']"); //By.xpath("//div[@class='col-2 newsletter-signup']//input[@placeholder='Email Address']");

	private By newsletterSignupButtonLocator=By.xpath("//div[@class='col-2 newsletter-signup']//form");  //(//div[@class='newsletter'])[1]//button[@id='subscribe']");//By.xpath("//div[@class='nl-container']//button[@id='subscribe']"); //By.xpath("//button[@class='at-element-click-tracking']"); //   //By.xpath("//button[@class='at-element-click-tracking']");
	private By newsletterText=By.xpath("(//div[@class='newsletter'])[1]");



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
	private By searchFilter = By.xpath("(//input[@class='tag_search_field'])[2]");    
    private By viewMoreIn= By.xpath("//div[@class='archive-sidebar-section subcategories-list']//li//a");     //   (//div[@class='menu-categories-v2-sidebar-container'])[2]//li//a");   //(//div[@class='archive-sidebar-section categories-v2-static-menu'])[2]");  
    private By selectFilters=By.xpath("(//div[@class='archive-filters']/div/ul/li[@data-value='breads-rolls-pastries']/a)[2]"); 
	private By allSelectFilterLinks=By.xpath("//div[@class='archive-filters']/div/ul/li//a");
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
	public void verify_Banner_FHM_HubPage() throws IOException, InterruptedException{

		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);
		//Test Step
		globalPage.verifyIsBannerAvailable(banner, url);
	}
	@Test(dependsOnMethods = {"verify_Banner_FHM_HubPage"}, alwaysRun = true)
	public void verify_BreadCrumb_FHM_HubPage() throws IOException, InterruptedException {
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		String element = "Breadcrumb";
		globalPage.verifyIsElementAvailable(Constant.breadcrumbLocator, url, element);
		//globalPage.verifyIsBreadCrumbAvailable(Constant.breadcrumbLocator, url);
	}

	@Test(dependsOnMethods = {"verify_BreadCrumb_FHM_HubPage"}, alwaysRun = true)
	public void verify_Title_FHM_HubPage() throws IOException, InterruptedException {
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		String element = "Title";
		//Test Step
		globalPage.verifyIsElementAvailable(Constant.titleLocator, url, element);
	}

	@Test(dependsOnMethods = {"verify_Title_FHM_HubPage"}, alwaysRun = true)
	public void verify_ArchiveDescription_FHM_HubPage() throws IOException, InterruptedException {
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		String element = "Archive Description";
		//Test Step
		globalPage.verifyIsElementAvailable(Constant.descriptionLocator, url, element);
	}
	
	@Test(dependsOnMethods = {"verify_ArchiveDescription_FHM_HubPage"}, alwaysRun = true)
	public void verify_heroContainer_ImageWorking_FHM_HubPage() throws IOException, InterruptedException {
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step
		String element = "Hero Image";
		globalPage.verifyPostsImages_Clickable(heroImage, url);
		//Step 2 Verify Amazon Header Bidder is Running
		//globalPage.verifyIsElementWorking(Constant.heroImage, url, element);
	}

	@Test(dependsOnMethods = {"verify_heroContainer_ImageWorking_FHM_HubPage"}, alwaysRun = true)
	public void verify_heroContainer_LinkWorking_FHM_HubPage() throws IOException, InterruptedException {
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step
		String element = "Hero Link";
		//globalPage.verifyIsElementWorking_RD(heroTitle, url, element);
		globalPage.verifyPostsImages_Clickable(heroTitle, url);
		globalPage.verifyLinkStatus(heroTitle, url, element);
	}

	@Test(dependsOnMethods = {"verify_heroContainer_LinkWorking_FHM_HubPage"}, alwaysRun = true)
	public void verify_featuredPostsLinks_FHM_HubPage() throws IOException, InterruptedException
	{
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		List<WebElement> links = driver.findElements(feauredPostLinks);
		int count = links.size();
		System.out.println("Total no of posts in featured Category Container are: " + count);
		//globalPage.verifyBrowseByList_RD(feauredPostLinks, url);
		globalPage.verifyPostsImages_Clickable(feauredPostLinks, url); //Constant.heroImage
		globalPage.verifyBrowseByList(feauredPostLinks, url);
	}

	@Test(dependsOnMethods = {"verify_featuredPostsLinks_FHM_HubPage"}, alwaysRun = true)
	public void verify_featuredPostsImgs_FHM_HubPage() throws IOException, InterruptedException
	{
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		List<WebElement> links = driver.findElements(feauredPostImgs);
		int count = links.size();
		System.out.println("Total no of images in 1by3 featureCategory Container are: " + count);
		//globalPage.verifyBrowseByList(feauredPostImgs_5by1Modules, url);
		globalPage.verifyPostsImages_Clickable_BNB(feauredPostImgs, url);

	}

	@Test(dependsOnMethods = {"verify_featuredPostsImgs_FHM_HubPage"}, alwaysRun = true)
	public void verify_SearchFilter_LinkWorking_FHM_HubPage() throws IOException, InterruptedException {
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step
		String element = "Search Filter";
		globalPage.verifyIsElementAvailable(searchFilter, url, element);
			
	}
	
	@Test(dependsOnMethods = {"verify_SearchFilter_LinkWorking_FHM_HubPage"}, alwaysRun = true)
	public void verify_ViewMoreIn_LinkWorking_FHM_HubPage() throws IOException, InterruptedException {
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step
		String element = "View More In:";
		globalPage.verifyPostsImages_Clickable(viewMoreIn, url);
		globalPage.brandLinksNavigation(viewMoreIn, url, element);
	}
	
	@Test(dependsOnMethods = {"verify_ViewMoreIn_LinkWorking_FHM_HubPage"}, alwaysRun = true)
	public void verify_SelectFilters_LinkWorking_FHM_HubPage() throws IOException, InterruptedException {
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step
	//	driver.findElement(By.xpath("(//div[@class='archive-filters']/div/span[text()='Dishes & Beverages'])[2]")).click();
		String element = "Select Filters";
		//globalPage.verifyIsElementWorking_TOH(selectFilters, url, element);
		globalPage.check_allLinksStatus(allSelectFilterLinks, url, element);
		/*globalPage.verifyPostsImages_Clickable(selectFilters, url);
		globalPage.verifyLinkStatus(selectFilters, url, element);*/
	}

	
	@Test(dependsOnMethods = {"verify_SelectFilters_LinkWorking_FHM_HubPage"}, alwaysRun = true)
	public void verify_Pagination_FHM_HubPage() throws IOException, InterruptedException
	{
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		globalPage.scrollToElement(pagination); //featured module container
		globalPage.verifyPagination(url, nextPage, prevPage);
	}
	
	@Test(dependsOnMethods = {"verify_Pagination_FHM_HubPage"}, alwaysRun = true)
	public void verify_NewsletterSignUpPage_FHM_HubPage() throws IOException, InterruptedException
	{
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);
		/*((JavascriptExecutor) driver).executeScript("window.scrollTo(document.body.scrollHeight, 0)");

		Actions act = new Actions(driver);
		WebElement ele = driver.findElement(newsletterText);
		act.moveToElement(ele);*/

		//Test Step
		globalPage.verifyNewsletterSignUp(url, newsletterSignupInputField, newsletterSignupButtonLocator, newspapaerSignUpLink );
	}
	

	@AfterMethod
	public void nameAfter(Method method)
	{
		System.out.println("Test : " + method.getName() + " execution completed .................. \n"); 
		System.out.println("********************************************************************************************");
	}

}
 