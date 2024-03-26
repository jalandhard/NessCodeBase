package com.GA.TOH;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.wp.genericLib.ConfigFileReader;
import com.wp.genericLib.Constant;
import com.wp.genericLib.Driver;
import com.wp.genericLib.WebDriverCommonLib;
import com.wp.pageObjectRepo.GlobalFunctions;

public class NavbarTests extends Driver {
	//Initialize object using page factory method 
	ConfigFileReader configFileReader;
	public Logger APPLICATION_LOGS = LogManager.getLogger("AppLogger");

	//Test Data
	String url=Constant.TOH_HomePage;

	private By navBars = By.className("main-navigation");  // By.xpath("//header[@class='site-header']//div[@class='wrap']");  main-navigation
	private By hamburgerMenuButtonLocator = By.xpath("//div[@class='hamburger-wrapper mobile-hide']//div[@class='hamburger menu-toggle']");  //By.xpath("//button[@id='toh-menu-toggle']");
	private By hamburgerMenuContainer = By.xpath("//div[@class='menu-hamburger-menu-container slinky-menu slinky-theme-default']");   //div[2]/div[1]/div[1]/ul[1]/li

	//sub menus
	private By hamburgerMenuHavingChild = By.xpath("//div[1]/ul[1]/li[@class='menu-item menu-item-type-custom menu-item-object-custom menu-item-has-children']");   //div[2]/div[1]/div[1]/ul[1]/li[@class='menu-item menu-item-type-custom menu-item-object-custom menu-item-has-children']
	private By hamburgerSubMenus1 = By.xpath("//ul[@class='sub-menu active']/li"); 
	private By hamburgerSubMenusHavingChild = By.xpath("//ul[@class='sub-menu active']/li[@class='menu-item menu-item-type-custom menu-item-object-custom menu-item-has-children']"); ///div[2]/div[1]/div[1]/ul[1]/li[1]/ul[1]/li
	private By hamburgerMenuNoChild = By.xpath("//li/div[1]/ul[1]/li[@class='menu-item menu-item-type-custom menu-item-object-custom']");   //div[1]/div[2]/div[1]/div[1]/ul[1]/li[@class='menu-item menu-item-type-custom menu-item-object-custom']


	private By subMenuBackButton = By.xpath("//ul[contains(@class, 'active')]/li/a[contains(@class, 'back')]");
	//div[2]/div[1]/div[1]/ul[1]/li[1]/ul[1]/li[2]/ul[1]/li//a

	private By hamburgerCloseBtn = By.xpath("//div[@class='hamburger-wrapper mobile-hide']//div[@class='hamburger-close']");   //button[@id='toh-menu-toggle']
	private By categoryLinks = By.xpath("//ul[@id='menu-desktop-focus-menu']/li"); //ul[@id='menu-desktop-focus-menu']/li
	private By searchIcon = By.className("search-button");  // By.className("search-button"); //button[@id='toh-search-toggle']
	private By searchTextField = By.xpath("//input[@id='s']");

	//verify_HamburgerMenuCats_SubcatsErrors_TOH_HomePage Locators
	private By category_Recipes= By.xpath("//li[@class='menu-item menu-item-type-custom menu-item-object-custom menu-item-has-children']/a/span[text()='Recipes']");
	private By category_FoodNews= By.xpath("//li[@class='menu-item menu-item-type-custom menu-item-object-custom menu-item-has-children']/a/span[text()='Food News']");
	private By category_HealthWellness= By.xpath("//li[@class='menu-item menu-item-type-custom menu-item-object-custom menu-item-has-children']/a/span[text()='Health & Wellness']");
	private By category_HolidaysEvents= By.xpath("//li[@class='menu-item menu-item-type-custom menu-item-object-custom menu-item-has-children']/a/span[text()='Holidays & Events']");
	private By category_HomeLiving = By.xpath("//li[@class='menu-item menu-item-type-custom menu-item-object-custom menu-item-has-children']/a/span[text()='Home & Living']");
	private By category_TestKitchen= By.xpath("//li[@class='menu-item menu-item-type-custom menu-item-object-custom menu-item-has-children']/a/span[text()='Test Kitchen']");
	private By category_FollowUs = By.xpath("//li[@class='menu-item menu-item-type-custom menu-item-object-custom menu-item-has-children']/a/span[text()='Follow Us']");

	private By subCat_links= By.xpath("//ul[@class='sub-menu active']/li[@class='menu-item menu-item-type-custom menu-item-object-custom']/a/span");
	private By main_cat_1= By.xpath("//ul[@class='pure-menu-children hamburger-menu-items']/li[@class='menu-item menu-item-type-custom menu-item-object-custom']/a/span");
	private By main_cat_2=By.xpath("//div[@class='menu-logged-in-menu-container']/ul/li[@class='menu-item menu-item-type-custom menu-item-object-custom']/a/span");
	//Login to TOH
	private String userName="qaautomation@gmail.com";
	private String password="qaautomation";

	@BeforeClass
	public void navigateToRequiredURL() throws Exception{
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Step:1 Navigate required URL
		globalPage.navigateToRequiredSite(url);

		//Login to TOH
		globalPage.loginTOHSite(userName, password);
	}

	@BeforeMethod
	public void nameBefore(Method method)
	{
		System.out.println("********************************************************************************************");
		System.out.println("Test : " + method.getName() + " execution started ...................");      

	}


	@Test
	public void verify_headerNavBars_TOH_HomePage() throws IOException, InterruptedException {
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		String element = "Header NavBar";
		//Test Step
		globalPage.verifyIsElementAvailable(navBars, url, element);
	}

	@Test(dependsOnMethods = {"verify_headerNavBars_TOH_HomePage"}, alwaysRun = true)
	public void verify_NavBar_CategoryLinks_TOH_HomePage() throws IOException, InterruptedException {
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step
		int links = driver.findElements(categoryLinks).size();
		System.out.println("Total no of links in NavBar Main Menu module are: " + links);
		globalPage.verifyNavMainMenu(categoryLinks, url);
	}

	@Test(dependsOnMethods = {"verify_NavBar_CategoryLinks_TOH_HomePage"}, alwaysRun = true)
	public void verify_HamburgerMenu_MainCat_1_TOH_HomePage() throws IOException, InterruptedException {
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step
		//Click on Hamburger icon
		driver.findElement(hamburgerMenuButtonLocator).click();



		int links = driver.findElements(main_cat_1).size();
		System.out.println("Total no of links in NavBar Main Menu module are: " + links);
		globalPage.HamburgerMenuCats_subcatsErrors(main_cat_1, url, hamburgerMenuButtonLocator);

	}

	@Test(dependsOnMethods = {"verify_HamburgerMenu_MainCat_1_TOH_HomePage"}, alwaysRun = true)
	public void verify_HamburgerMenu_MainCat_2_TOH_HomePage() throws IOException, InterruptedException {
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step
		//Click on Hamburger icon		
		driver.findElement(hamburgerMenuButtonLocator).click();		

		int links = driver.findElements(main_cat_2).size();
		System.out.println("Total no of links in NavBar Main Menu module are: " + links);
		globalPage.HamburgerMenuCats_subcatsErrors(main_cat_2, url, hamburgerMenuButtonLocator);

	}

	@Test(dependsOnMethods = {"verify_HamburgerMenu_MainCat_2_TOH_HomePage"}, alwaysRun = true)
	public void verify_HamburgerMenu_RecipesCat_TOH_HomePage() throws IOException, InterruptedException {
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step
		//Click on Hamburger icon
		driver.findElement(hamburgerMenuButtonLocator).click();

		driver.findElement(category_Recipes).click();


		int links = driver.findElements(subCat_links).size();
		System.out.println("Total no of links in NavBar Main Menu module are: " + links);

		globalPage.HamburgerMenuCats_subcatsErrors(subCat_links, url, hamburgerMenuButtonLocator,category_Recipes);
	}

	@Test(dependsOnMethods = {"verify_HamburgerMenu_RecipesCat_TOH_HomePage"}, alwaysRun = true)
	public void verify_HamburgerMenu_FoodNewsCat_TOH_HomePage() throws IOException, InterruptedException {
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step
		//Click on Hamburger icon
		driver.findElement(hamburgerMenuButtonLocator).click();

		driver.findElement(category_FoodNews).click();

		int links = driver.findElements(subCat_links).size();
		System.out.println("Total no of links in NavBar Main Menu module are: " + links);

		globalPage.HamburgerMenuCats_subcatsErrors(subCat_links, url, hamburgerMenuButtonLocator,category_FoodNews);
	}

	@Test(dependsOnMethods = {"verify_HamburgerMenu_FoodNewsCat_TOH_HomePage"}, alwaysRun = true)
	public void verify_HamburgerMenu_HealthWellnessCat_TOH_HomePage() throws IOException, InterruptedException {
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step
		//Click on Hamburger icon
		driver.findElement(hamburgerMenuButtonLocator).click();

		driver.findElement(category_HealthWellness).click();

		int links = driver.findElements(subCat_links).size();
		System.out.println("Total no of links in NavBar Main Menu module are: " + links);

		globalPage.HamburgerMenuCats_subcatsErrors(subCat_links, url, hamburgerMenuButtonLocator,category_HealthWellness);
	}

	@Test(dependsOnMethods = {"verify_HamburgerMenu_HealthWellnessCat_TOH_HomePage"}, alwaysRun = true)
	public void verify_HamburgerMenu_HolidaysEventsCat_TOH_HomePage() throws IOException, InterruptedException {
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step
		//Click on Hamburger icon
		driver.findElement(hamburgerMenuButtonLocator).click();

		driver.findElement(category_HolidaysEvents).click();

		int links = driver.findElements(subCat_links).size();
		System.out.println("Total no of links in NavBar Main Menu module are: " + links);

		globalPage.HamburgerMenuCats_subcatsErrors(subCat_links, url, hamburgerMenuButtonLocator,category_HolidaysEvents);
	}

	@Test(dependsOnMethods = {"verify_HamburgerMenu_HolidaysEventsCat_TOH_HomePage"}, alwaysRun = true)
	public void verify_HamburgerMenu_HomeLivingCat_TOH_HomePage() throws IOException, InterruptedException {
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step
		//Click on Hamburger icon
		driver.findElement(hamburgerMenuButtonLocator).click();

		driver.findElement(category_HomeLiving).click();

		int links = driver.findElements(subCat_links).size();
		System.out.println("Total no of links in NavBar Main Menu module are: " + links);

		globalPage.HamburgerMenuCats_subcatsErrors(subCat_links, url, hamburgerMenuButtonLocator,category_HomeLiving);
	}

	@Test(dependsOnMethods = {"verify_HamburgerMenu_HomeLivingCat_TOH_HomePage"}, alwaysRun = true)
	public void verify_HamburgerMenu_TestKitchenCat_TOH_HomePage() throws IOException, InterruptedException {
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step
		//Click on Hamburger icon
		driver.findElement(hamburgerMenuButtonLocator).click();

		driver.findElement(category_TestKitchen).click();

		int links = driver.findElements(subCat_links).size();
		System.out.println("Total no of links in NavBar Main Menu module are: " + links);

		globalPage.HamburgerMenuCats_subcatsErrors(subCat_links, url, hamburgerMenuButtonLocator,category_TestKitchen);
	}

	@Test(dependsOnMethods = {"verify_HamburgerMenu_TestKitchenCat_TOH_HomePage"}, alwaysRun = true)
	public void verify_HamburgerMenu_FollowUsCat_TOH_HomePage() throws IOException, InterruptedException {
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step
		//Click on Hamburger icon
		driver.findElement(hamburgerMenuButtonLocator).click();

		driver.findElement(category_FollowUs).click();

		int links = driver.findElements(subCat_links).size();
		System.out.println("Total no of links in NavBar Main Menu module are: " + links);

		globalPage.HamburgerMenuCats_subcatsErrors(subCat_links, url, hamburgerMenuButtonLocator,category_FollowUs);
	}

}
