package com.GA.BHC;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.wp.genericLib.ConfigFileReader;
import com.wp.genericLib.Constant;
import com.wp.genericLib.Driver;
import com.wp.pageObjectRepo.GlobalFunctions;


public class BHC_newNavbarTests extends Driver{
	//Initialize object using page factory method 
	ConfigFileReader configFileReader;

	public Logger APPLICATION_LOGS = LogManager.getLogger("AppLogger");

	//Test Data
	String url=Constant.BHC_HomePage;
	String newspapaerSignUpLink = Constant.BHC_NewspaperSignUpLink;
	
	private By logo = By.xpath("//div[@id='logo-container']//img"); 
	private By NLSignupImg_header =By.xpath("//div[@class='newsletter-signup-header']//img");
	private By newsletterSignupButton =By.xpath("//div[@class='newsletter-signup-header']//a");     //div[@class='col-2 newsletter-signup']//button[@class='at-element-click-tracking']");  //By.xpath("//button[@class='at-element-click-tracking']"); //// 
	
	private By SubscribeLink_header =By.xpath("//div[@class='sub-to-mag']//a"); 
	private By magazineImg_Header = By.xpath("//div[@class='sub-to-mag']//img");


	private By navBars = By.className("main-navigation");  // By.xpath("//header[@class='site-header']//div[@class='wrap']");  main-navigation
	private By hamburgerMenuButtonLocator = By.xpath("//div[@class='hamburger-wrapper']//div[@class='hamburger menu-toggle']");  //By.xpath("//button[@id='toh-menu-toggle']");
//	private By hamburgerMenuButtonLocator = By.xpath("//div[@class='hamburger-wrapper mobile-hide']//div[@class='hamburger menu-toggle']");  //By.xpath("//button[@id='toh-menu-toggle']");
	private By hamburgerMenuContainer = By.xpath("//div[@class='menu-hamburger-menu-container slinky-menu slinky-theme-default']");   //div[2]/div[1]/div[1]/ul[1]/li
	
	//private By rDC_hamburgerhavingChild=By.xpath("//ul/li[@class='menu-item menu-item-type-custom menu-item-object-custom menu-item-has-children']//li[@class='menu-item menu-item-type-custom menu-item-object-custom']//a");
	private By hamburgerMenuhavingChildLinks=By.xpath("//ul/li[contains(@class,'menu-item menu-item-type')]//li[contains(@class,'menu-item')]//a");
	private By hamburgerMenuNoChildLinks=By.xpath("//li/div[1]/ul[1]/li[@class='menu-item menu-item-type-taxonomy menu-item-object-category']//a");
	private By hamburgerMenuHavingNoChild = By.xpath("//li/div[1]/ul[1]/li[@class='menu-item menu-item-type-custom menu-item-object-custom']//a");   //div[1]/div[2]/div[1]/div[1]/ul[1]/li[@class='menu-item menu-item-type-custom menu-item-object-custom']
	/*//sub menus
	private By hamburgerMenuHavingChild = By.xpath("//div[1]/ul[1]/li[@class='menu-item menu-item-type-custom menu-item-object-custom menu-item-has-children']");   //div[2]/div[1]/div[1]/ul[1]/li[@class='menu-item menu-item-type-custom menu-item-object-custom menu-item-has-children']
	private By hamburgerSubMenus1 = By.xpath("//ul[@class='sub-menu active']/li"); 
	private By hamburgerSubMenusHavingChild = By.xpath("//ul[@class='sub-menu active']/li[@class='menu-item menu-item-type-custom menu-item-object-custom menu-item-has-children']"); ///div[2]/div[1]/div[1]/ul[1]/li[1]/ul[1]/li
	
	
	
	private By subMenuBackButton = By.xpath("//ul[contains(@class, 'active')]/li/a[contains(@class, 'back')]");
	//div[2]/div[1]/div[1]/ul[1]/li[1]/ul[1]/li[2]/ul[1]/li//a
*/
	private By hamburgerCloseBtn = By.xpath("//div[@class='hamburger-wrapper']//div[@class='hamburger-close']");   //button[@id='toh-menu-toggle']
//	private By hamburgerCloseBtn = By.xpath("//div[@class='hamburger-wrapper mobile-hide']//div[@class='hamburger-close']");   //button[@id='toh-menu-toggle']
	private By categoryLinks = By.xpath("//div[@class='menu-desktop-focus-menu-container']//ul[@id='menu-desktop-focus-menu']/li//a"); //ul[@id='menu-desktop-focus-menu']/li
	private By searchIcon = By.className("sticky-search-button"); 
//	private By searchIcon = By.className("search-button");  // By.className("search-button"); //button[@id='toh-search-toggle']
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

	
	@Test()
	public void verify_BrandLogo_BHC() throws IOException, InterruptedException {
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);
		
		String element = "Brand Logo";
		//Test Step
		globalPage.verifyIsElementAvailable(logo, url, element);
	}
		
	@Test(dependsOnMethods = {"verify_BrandLogo_BHC"}, alwaysRun = true)
	public void verify_subsribleMagazineImg_Header_BHC() throws IOException, InterruptedException {

		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step
		String element = "Subscribe to Magazine Header";

		//Step 2 Verify Amazon Header Bidder is Running
		//globalPage.verifyIsElementWorking_BHC(heroText, url, element);
		globalPage.verifyPostsImages_Clickable(magazineImg_Header, url);
		globalPage.verifyPostsImages_Clickable(SubscribeLink_header, url);
	//	globalPage.verifyLinkStatus(SubscribeLink_header, url, element);

	}

	@Test(dependsOnMethods = {"verify_subsribleMagazineImg_Header_BHC"}, alwaysRun = true)
	public void verify_headerNavBars_BHC_HomePage() throws IOException, InterruptedException {
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);
		
		String element = "Header NavBar";
		//Test Step
		globalPage.verifyIsElementAvailable(navBars, url, element);
	}
	
	/*@Test(dependsOnMethods = {"verify_headerNavBars_BHC_HomePage"}, alwaysRun = true)
	public void verify_Header_Categories_BHC_HomePage() throws IOException, InterruptedException {
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);
		String element = "Header category link ";
		//Test Step
		globalPage.verifyheaderCategories(categoryLinks, url, element, Constant.BHC_Headers);
		
	}
	*/
	@Test(dependsOnMethods = {"verify_headerNavBars_BHC_HomePage"}, alwaysRun = true)
	public void verify_hamburgerMenu_Opened_BHC_HomePage() throws IOException, InterruptedException {
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);
		
		//Test Step;
		String item = "Hamburger Menu button ";
		
		globalPage.isElementClickable(url, hamburgerMenuButtonLocator, item);
		System.out.println("Hamburger menu opened .");
	
	}
	
	@Test(dependsOnMethods = {"verify_hamburgerMenu_Opened_BHC_HomePage"}, alwaysRun = true)
	public void verify_hamburger_MainMenu_Categories_BHC_HomePage() throws IOException, InterruptedException {
		//Object Initialization
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

	@Test(dependsOnMethods = {"verify_hamburger_MainMenu_Categories_BHC_HomePage"}, alwaysRun = true)
	public void verify_hamburgerMenusHavingChild_BHC_HomePage() throws IOException, InterruptedException {
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);
		String element = "hamburger menu having child ";
		//Test Step;
		//globalPage.brandLinksNavigation(rDC_hamburgerhavingChild, url, element);
			globalPage.brandLinksNavigation(hamburgerMenuhavingChildLinks, url, element);
			
	}
	
	@Test(dependsOnMethods = {"verify_hamburger_MainMenu_Categories_BHC_HomePage"}, alwaysRun = true)
	public void verify_hamburgerMenusHavingNoChild_BHC_HomePage() throws IOException, InterruptedException {
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);
		String element = "hamburger menu having no child ";
		//Test Step;
			//globalPage.verifyhamburgerMenus(hamburgerMenuButtonLocator, hamburgerMenuContainer, hamburgerMenuHavingChild, hamburgerMenuNoChild, hamburgerSubMenus1, hamburgerSubMenusHavingChild, subMenuBackButton, url);
			//globalPage.brandLinksNavigation(hamburgerMenuNoChildLinks, url, element);
		try{
			globalPage.IsElementExist(url, hamburgerMenuHavingNoChild, element);
			globalPage.brandLinksNavigation(hamburgerMenuHavingNoChild, url, element);
		}
		catch(NoSuchElementException e)
		{
			System.out.println("Hamburger menu having no child is not available");
			System.out.println("Test case : PASSED!!.........................");
		}
			

	}
	
	@Test(dependsOnMethods = {"verify_hamburgerMenusHavingChild_BHC_HomePage"}, alwaysRun = true)
	public void verify_hamburgerMenu_Closed_BHC_HomePage() throws IOException, InterruptedException {
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);
		
		//Test Step;
		String item = "Hamburger Menu Close  ";
		
		globalPage.isElementClickable(url, hamburgerCloseBtn, item);	
	}
	
	
	
	@Test(dependsOnMethods = {"verify_hamburgerMenu_Closed_BHC_HomePage"}, alwaysRun = true)
	public void verify_NavBar_CategoryLinks_BHC_HomePage() throws IOException, InterruptedException {
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
