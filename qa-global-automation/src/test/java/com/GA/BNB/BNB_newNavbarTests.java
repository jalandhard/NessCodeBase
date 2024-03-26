package com.GA.BNB;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.wp.genericLib.ConfigFileReader;
import com.wp.genericLib.Constant;
import com.wp.genericLib.Driver;
import com.wp.pageObjectRepo.GlobalFunctions;


public class BNB_newNavbarTests extends Driver{
	//Initialize object using page factory method 
	ConfigFileReader configFileReader;

	public Logger APPLICATION_LOGS = LogManager.getLogger("AppLogger");

	//Test Data
	String url=Constant.BNB_HomePage;
	String newspapaerSignUpLink = Constant.BNB_NewspaperSignUpLink;
	
	private By logo = By.xpath("//div[@id='logo-container']//img"); 
	private By NLSignupImg_header =By.xpath("//div[@class='newsletter-signup-header']//img");
	private By newsletterSignupButton =By.xpath("//div[@class='newsletter-signup-header']//a");     //div[@class='col-2 newsletter-signup']//button[@class='at-element-click-tracking']");  //By.xpath("//button[@class='at-element-click-tracking']"); //// 
	
	private By SubscribeLink_header =By.xpath("//div[@class='subOffer_header_BNB']//a[@class='offerText-subscribe at-element-click-tracking']"); 
	private By magazineImg_Header = By.xpath("//span[@class='mag']//img");



	private By navBars = By.xpath("//div[@class='menu-desktop-focus-menu-container']");   //div[@class='menu-desktop-focus-menu-container']//a[1] // By.xpath("//header[@class='site-header']//div[@class='wrap']");  main-navigation
	private By hamburgerMenuButtonLocator = By.xpath("//div[@class='hamburger-wrapper']//div[@class='hamburger menu-toggle']");  //By.xpath("//button[@id='toh-menu-toggle']");
//	private By hamburgerMenuButtonLocator = By.xpath("//div[@class='hamburger-wrapper mobile-hide']//div[@class='hamburger menu-toggle']");  //By.xpath("//button[@id='toh-menu-toggle']");
	private By hamburgerMenuContainer = By.xpath("//div[@class='menu-hamburger-menu-container slinky-menu slinky-theme-default']/ul[@id='menu'][1]/li");   //div[2]/div[1]/div[1]/ul[1]/li
	
	//private By rDC_hamburgerhavingChild=By.xpath("//ul/li[@class='menu-item menu-item-type-custom menu-item-object-custom menu-item-has-children']//li[@class='menu-item menu-item-type-custom menu-item-object-custom']//a");
	private By hamburgerMenuhavingChildLinks=By.xpath("//ul/li[contains(@class,'menu-item menu-item-type')]//li[contains(@class,'menu-item')]//a");
	private By hamburgerMenuNoChildLinks=By.xpath("//li/div[1]/ul[1]/li[contains(@class,'menu-item menu-item-type-post_type menu-item-object-page')]//a");
	private By hamburgerMenuHavingNoChild = By.xpath("//li/div[1]/ul[1]/li[@class='menu-item menu-item-type-custom menu-item-object-custom']//a");   //div[1]/div[2]/div[1]/div[1]/ul[1]/li[@class='menu-item menu-item-type-custom menu-item-object-custom']
	
	private By hamburgerCloseBtn = By.xpath("//div[@class='hamburger-wrapper']//div[@class='hamburger-close']");   //button[@id='toh-menu-toggle']
//	private By hamburgerCloseBtn = By.xpath("//div[@class='hamburger-wrapper mobile-hide']//div[@class='hamburger-close']");   //button[@id='toh-menu-toggle']
	private By categoryLinks = By.xpath("//div[@class='menu-desktop-focus-menu-container']//a[1]"); //ul[@id='menu-desktop-focus-menu']/li
	private By searchIcon = By.className("sticky-search-button"); 
//	private By searchIcon =By.xpath("//div[@id='search-form-wrapper']//input[@class='search-button']");  // By.className("search-button");  // By.className("search-button"); //button[@id='toh-search-toggle']
	private By searchTextField = By.xpath("//input[@id='s']");
	/*//HamburgerMenu Locators
	private By subCat_links= By.xpath("//ul[@class='sub-menu active']/li[contains(@class,'menu-item menu-item-type')]/a/span");
	private By category_Health= By.xpath("//li[@class='menu-item menu-item-type-taxonomy menu-item-object-category menu-item-has-children']/a/span[text()='Health']");
	private By category_Food= By.xpath("//li[@class='menu-item menu-item-type-taxonomy menu-item-object-category menu-item-has-children']/a/span[text()='Food']");
	private By category_Advice= By.xpath("//li[@class='menu-item menu-item-type-taxonomy menu-item-object-category menu-item-has-children']/a/span[text()='Advice']");
	private By category_TrueStories= By.xpath("//li[@class='menu-item menu-item-type-taxonomy menu-item-object-category menu-item-has-children']/a/span[text()='True Stories']");
	private By category_Jokes= By.xpath("//li[@class='menu-item menu-item-type-custom menu-item-object-custom menu-item-has-children']/a/span[text()='Jokes']");
	private By category_FollowUs= By.xpath("//li[@class='menu-item menu-item-type-custom menu-item-object-custom menu-item-has-children']/a/span[text()='Follow Us']");
	private By category_Culture= By.xpath("//div/ul/li[@class='menu-item menu-item-type-taxonomy menu-item-object-category']/a/span");
	private By category_Contests=By.xpath("//div/ul/li[@class='menu-item menu-item-type-taxonomy menu-item-object-post_tag']/a/span");
	private By category_Main=By.xpath("//div/ul/li[@class='menu-item menu-item-type-custom menu-item-object-custom']/a/span");
	*/
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
	public void verify_BrandLogo_BNB() throws IOException, InterruptedException {
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);
		
		String element = "Brand Logo";
		//Test Step
		globalPage.verifyIsElementAvailable(logo, url, element);
	}
	
	@Test(dependsOnMethods = {"verify_BrandLogo_BNB"}, alwaysRun = true)
	public void NLSignUpImg_Header_BNB() throws IOException, InterruptedException
	{
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);
		
		String element = "NL signup image in header";
		globalPage.verifyIsElementAvailable(NLSignupImg_header, url, element);
	}
	
	@Test(dependsOnMethods = {"NLSignUpImg_Header_BNB"}, alwaysRun = true)
	public void NLSignUp_Header_BNB() throws IOException, InterruptedException
	{
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);
		
		String element = "NL SignUp Link in Header";
		globalPage.verifyLinkStatus(newsletterSignupButton, url, element);
	}
	
	@Test(dependsOnMethods = {"NLSignUp_Header_BNB"}, alwaysRun = true)
	public void verify_subsribleMagazineImg_Header_BNB() throws IOException, InterruptedException {

		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step
		String element = "Subscribe to Magazine Header";

		//Step 2 Verify Amazon Header Bidder is Running
		//globalPage.verifyIsElementWorking_BNB(heroText, url, element);
		globalPage.verifyPostsImages_Clickable(magazineImg_Header, url);
		globalPage.verifyPostsImages_Clickable(SubscribeLink_header, url);
	//	globalPage.verifyLinkStatus(SubscribeLink_header, url, element);

	}

	
	@Test(dependsOnMethods = {"verify_subsribleMagazineImg_Header_BNB"}, alwaysRun = true)
	public void verify_headerNavBars_BNB_HomePage() throws IOException, InterruptedException {
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);
		
		String element = "Header NavBar";
		//Test Step
		globalPage.verifyIsElementAvailable(navBars, url, element);
	}
	
	/*@Test(dependsOnMethods = {"verify_headerNavBars_BNB_HomePage"}, alwaysRun = true)
	public void verify_Header_Categories_BNB_HomePage() throws IOException, InterruptedException {
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);
		String element = "Header category link ";
		//Test Step
		globalPage.verifyheaderCategories(categoryLinks, url, element, Constant.BNB_Headers);
		
	}*/
	
	@Test(dependsOnMethods = {"verify_headerNavBars_BNB_HomePage"}, alwaysRun = true)
	public void verify_hamburgerMenu_Opened_BNB_HomePage() throws IOException, InterruptedException {
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);
		
		//Test Step;
		String item = "Hamburger Menu button ";
		
		globalPage.isElementClickable(url, hamburgerMenuButtonLocator, item);
		System.out.println("Hamburger menu opened .");
	
	}
	
	@Test(dependsOnMethods = {"verify_hamburgerMenu_Opened_BNB_HomePage"}, alwaysRun = true)
	public void verify_hamburger_MainMenu_Categories_BNB_HomePage() throws IOException, InterruptedException {
		//Object Initialization
		//SoftAssert softAssert= new SoftAssert();
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);
		int navlink = driver.findElements(hamburgerMenuContainer).size();
		//System.out.println("Total no of menus in hamburger tab are: " + navlink);
		System.out.println("Below is the list of hamburger main menu");

		for (int i = 0; i < navlink; i++)
		{
			List<WebElement> menus = driver.findElements(hamburgerMenuContainer);
			WebElement menu = menus.get(i);
			
				String navTitle = menu.getText();
				System.out.println(navTitle);
		}
	}

	@Test(dependsOnMethods = {"verify_hamburger_MainMenu_Categories_BNB_HomePage"}, alwaysRun = true)
	public void verify_hamburgerMenusHavingChild_BNB_HomePage() throws IOException, InterruptedException {
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);
		String element = "hamburger menu having child ";
		//Test Step;
		//globalPage.brandLinksNavigation(rDC_hamburgerhavingChild, url, element);
			globalPage.brandLinksNavigation(hamburgerMenuhavingChildLinks, url, element);
			
	}
	
	@Test(dependsOnMethods = {"verify_hamburger_MainMenu_Categories_BNB_HomePage"}, alwaysRun = true)
	public void verify_hamburgerMenusHavingNoChild_BNB_HomePage() throws IOException, InterruptedException {
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);
		String element = "hamburger menu having no child ";
		//Test Step;
			//globalPage.verifyhamburgerMenus(hamburgerMenuButtonLocator, hamburgerMenuContainer, hamburgerMenuHavingChild, hamburgerMenuNoChild, hamburgerSubMenus1, hamburgerSubMenusHavingChild, subMenuBackButton, url);
			/*globalPage.brandLinksNavigation(hamburgerMenuNoChildLinks, url, element);
			globalPage.brandLinksNavigation(hamburgerMenuHavingNoChild, url, element);*/

			try{
				globalPage.IsElementExist(url, hamburgerMenuHavingNoChild, element);
				globalPage.brandLinksNavigation(hamburgerMenuHavingNoChild, url, element);
				globalPage.brandLinksNavigation(hamburgerMenuNoChildLinks, url, element);
			}
			catch(NoSuchElementException e)
			{
				System.out.println("Hamburger menu having no child is not available");
				System.out.println("Test case : PASSED!!.........................");
			}
	}
	
	@Test(dependsOnMethods = {"verify_hamburgerMenusHavingChild_BNB_HomePage"}, alwaysRun = true)
	public void verify_hamburgerMenu_Closed_BNB_HomePage() throws IOException, InterruptedException {
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);
		
		//Test Step;
		String item = "Hamburger Menu Close  ";
		
		globalPage.isElementClickable(url, hamburgerCloseBtn, item);	
	}
	
	@Test(dependsOnMethods = {"verify_hamburgerMenu_Closed_BNB_HomePage"}, alwaysRun = true)
	public void verify_NavBar_CategoryLinks_BNB_HomePage() throws IOException, InterruptedException {
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);
		String element = "Header category link ";
		//Test Step
		int links = driver.findElements(categoryLinks).size();
		System.out.println("Total no of links in NavBar Main Menu module are: " + links);
		//globalPage.verifyNavMainMenu(categoryLinks, url);
		globalPage.brandLinksNavigation(categoryLinks, url, element);
		
	}
	
	
}
