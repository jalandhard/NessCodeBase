package com.GA.TOH;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
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

public class TOH_RecipeDetail extends Driver {

	ConfigFileReader configFileReader;
	public Logger APPLICATION_LOGS = LogManager.getLogger("AppLogger");

	String url= Constant.TOH_RecipeDetailPage;//"https://rf-staging.tasteofhome.com/recipes/homemade-ladybug-cookies/";

	//Locators
	

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
	public void recipeTitle_TOHRecipeDetailPage() throws IOException, InterruptedException {
		GlobalFunctions globalPage = PageFactory.initElements(driver, GlobalFunctions.class);
		
		By titleLocator = By.className("recipe-title");
		
		globalPage.IsElementExist(url, titleLocator, "Recipe Title");
	}
	
	@Test(dependsOnMethods = {"recipeTitle_TOHRecipeDetailPage"}, alwaysRun = true)
	public void recipeTaglineImage_TOHRecipeDetailPage() throws IOException, InterruptedException {
		GlobalFunctions globalPage = PageFactory.initElements(driver, GlobalFunctions.class);
		
		By locator = By.xpath("//div[contains(@class, 'recipe-tagline__image')]/img");
		globalPage.IsElementExist(url, locator, "Recipe Tagline Image");
	}
	
	@Test(dependsOnMethods = {"recipeTaglineImage_TOHRecipeDetailPage"}, alwaysRun = true)
	public void recipeTaglineText_TOHRecipeDetailPage() throws IOException, InterruptedException {
		GlobalFunctions globalPage = PageFactory.initElements(driver, GlobalFunctions.class);
		
		By locator = By.className("recipe-tagline__text");
		
		globalPage.IsElementExist(url, locator, "Recipe Tagline Text");
	}
	
	@Test(dependsOnMethods = {"recipeTaglineText_TOHRecipeDetailPage"}, alwaysRun = true)
	public void testKitchenApprove_TOHRecipeDetailPage() throws IOException, InterruptedException {
		GlobalFunctions globalPage = PageFactory.initElements(driver, GlobalFunctions.class);
		
		By locator = By.xpath("//li[@class='approved']//a"); // By.className("approved");
		
		globalPage.brandLinksNavigation(locator, url, "Test kitchen approve link");
	}
	
	@Test(dependsOnMethods = {"testKitchenApprove_TOHRecipeDetailPage"}, alwaysRun = true)
	public void reviewStars_TOHRecipeDetailPage() throws IOException, InterruptedException {
		GlobalFunctions globalPage = PageFactory.initElements(driver, GlobalFunctions.class);
		
		By locator = By.xpath("//li[contains(@class, 'rating')]/i");
		
		int numStars = driver.findElements(locator).size();

		assertTrue(numStars == 5, "There are not 5 review stars on " + url + ". Found " + numStars);
	}
	
	@Test(dependsOnMethods = {"reviewStars_TOHRecipeDetailPage"}, alwaysRun = true)
	public void recommendedTitle_TOHRecipeDetailPage() throws IOException, InterruptedException {
		GlobalFunctions globalPage = PageFactory.initElements(driver, GlobalFunctions.class);
		
		By locator = By.xpath("//div[contains(@class, 'non-contextual-video')]/h2");
		
		globalPage.IsElementExist(url, locator, "Recommended Title");
	}
	
	@Test(dependsOnMethods = {"recommendedTitle_TOHRecipeDetailPage"}, alwaysRun = true)
	public void recommendedVideo_TOHRecipeDetailPage() throws IOException, InterruptedException {
		GlobalFunctions globalPage = PageFactory.initElements(driver, GlobalFunctions.class);
		
		By locator = By.xpath("//div[contains(@class, 'non-contextual-video')]//video");
		
		globalPage.IsElementExist(url, locator, "Recommended Video");
	}
	
	
	@Test(dependsOnMethods = {"recommendedVideo_TOHRecipeDetailPage"}, alwaysRun = true)
	public void socialShare_TOHRecipeDetailPage() throws IOException, InterruptedException {
		GlobalFunctions globalPage = PageFactory.initElements(driver, GlobalFunctions.class);
		
		By locator = By.xpath("//a[@id='social-share-toggle']");
		WebElement ele = driver.findElement(locator);
		Actions actions = new Actions(driver);
		actions.moveToElement(ele).click().perform();
		//driver.findElement(locator).click();
		
		//By popUpLocator = By.xpath("//div[@class='social-share social-menu-desktop toh-recipe']//li[@class='social-share-item']");
		By popUpLocator = By.xpath("//div[@class='recipe-toolbar__share-buttons social_tool_tip -open']//li[@class='social-share-item']//a");
		//Thread.sleep(2000);
		//globalPage.IsElementExist(url, popUpLocator, "Social Share PopUp");
		globalPage.socialShareNavigation(popUpLocator, popUpLocator, url);
	}
	
	@Test(dependsOnMethods = {"socialShare_TOHRecipeDetailPage"}, alwaysRun = true)
	public void printButton_TOHRecipeDetailPage() throws IOException, InterruptedException {
		GlobalFunctions globalPage = PageFactory.initElements(driver, GlobalFunctions.class);
		
		By locator = By.linkText("Print");
		globalPage.IsElementExist(url, locator, "Print Button ");
		//globalPage.brandLinksNavigation(locator, url, "Print Button");
	}
	
	@Test(dependsOnMethods = {"printButton_TOHRecipeDetailPage"}, alwaysRun = true)
	public void commentButton_TOHRecipeDetailPage() throws IOException, InterruptedException {
		GlobalFunctions globalPage = PageFactory.initElements(driver, GlobalFunctions.class);
		
		By locator = By.xpath("//div[@class='recipe-toolbar']//li[2]//a[@class='recipe-comments-scroll']");
		globalPage.IsElementExist(url, locator, "Comment Button ");
		//globalPage.brandLinksNavigation(locator, url, "Comment Button");
	}
	
	@Test(dependsOnMethods = {"commentButton_TOHRecipeDetailPage"}, alwaysRun = true)
	public void rateButton_TOHRecipeDetailPage() throws IOException, InterruptedException {
		GlobalFunctions globalPage = PageFactory.initElements(driver, GlobalFunctions.class);
		
		By locator = By.xpath("//div[@class='recipe-toolbar']//li[1]//a[@class='recipe-comments-scroll']");
		globalPage.IsElementExist(url, locator, "Rate Button ");
		//globalPage.brandLinksNavigation(locator, url, "Rate Button");
	}
	
	
	@Test(dependsOnMethods = {"rateButton_TOHRecipeDetailPage"}, alwaysRun = true)
	public void ingredientsHeader_TOHRecipeDetailPage() throws IOException, InterruptedException {
		GlobalFunctions globalPage = PageFactory.initElements(driver, GlobalFunctions.class);
		
		By locator = By.xpath("//div[contains(@class, 'recipe-ingredients')]//h2");
		globalPage.IsElementExist(url, locator, "Ingredients Header");
	}
	
	@Test(dependsOnMethods = {"ingredientsHeader_TOHRecipeDetailPage"}, alwaysRun = true)
	public void ingredientsList_TOHRecipeDetailPage() throws IOException, InterruptedException {
		GlobalFunctions globalPage = PageFactory.initElements(driver, GlobalFunctions.class);
		
		By locator = By.xpath("//div[contains(@class, 'recipe-ingredients')]//ul");
		
		globalPage.IsElementExist(url, locator, "Ingredients List");
	}
	
	@Test(dependsOnMethods = {"ingredientsList_TOHRecipeDetailPage"}, alwaysRun = true)
	public void recipeDirections_TOHRecipeDetailPage() throws IOException, InterruptedException {
		GlobalFunctions globalPage = PageFactory.initElements(driver, GlobalFunctions.class);
		
		By locator = By.xpath("//ul[contains(@class, 'recipe-directions__list')]//li");
		
		int numDirections = driver.findElements(locator).size();
		
		assertTrue(numDirections > 0, "No directions appearing for recipe on " + url);
	}
	
	@Test(dependsOnMethods = {"recipeDirections_TOHRecipeDetailPage"}, alwaysRun = true)
	public void nutritionFacts_TOHRecipeDetailPage() throws IOException, InterruptedException {
		GlobalFunctions globalPage = PageFactory.initElements(driver, GlobalFunctions.class);
		
		By locator = By.xpath("//div[contains(@class,'recipe-nutrition-facts')]");
		
		globalPage.IsElementExist(url, locator, "Nutrition Facts");
	}
	
	@Test(dependsOnMethods = {"nutritionFacts_TOHRecipeDetailPage"}, alwaysRun = true)
	public void affiliateDisclaimer_TOHRecipeDetailPage() throws IOException, InterruptedException {
		GlobalFunctions globalPage = PageFactory.initElements(driver, GlobalFunctions.class);
		
		By locator = By.xpath("//div[contains(@class,'affiliate_disclaimer_text')]");
		
		globalPage.IsElementExist(url, locator, "Affiliate Disclaimer");
	}
	
	@Test(dependsOnMethods = {"affiliateDisclaimer_TOHRecipeDetailPage"}, alwaysRun = true)
	public void ratings_TOHRecipeDetailPage() throws IOException, InterruptedException {
		GlobalFunctions globalPage = PageFactory.initElements(driver, GlobalFunctions.class);
		
		By locator = By.id("toh-show-more-reviews");
		
		WebElement button = driver.findElement(locator);
		button.click();
		Thread.sleep(1000);
		
		//Button may refresh so get a fresh copy
		button = driver.findElement(locator);
		System.out.println(button.getText());
		assertTrue(button.getText().toLowerCase().contains("view less"), "View more ratings button does not work properly");
	}
	
	@Test(dependsOnMethods = {"ratings_TOHRecipeDetailPage"}, alwaysRun = true)
	public void recircTitle_TOHRecipeDetailPage() throws IOException, InterruptedException {
		GlobalFunctions globalPage = PageFactory.initElements(driver, GlobalFunctions.class);
		
		By locator = By.xpath("//div[contains(@class, 'recirc-module')]/h2");
		globalPage.IsElementExist(url, locator, "Recirc Title");
	}
	
	@Test(dependsOnMethods = {"recircTitle_TOHRecipeDetailPage"}, alwaysRun = true)
	public void recircContent_TOHRecipeDetailPage() throws IOException, InterruptedException {
		GlobalFunctions globalPage = PageFactory.initElements(driver, GlobalFunctions.class);
		
		By locator = By.xpath("//div[contains(@class, 'recirc-module')]//div[contains(@class, 'recirc-item')]");
		
		globalPage.IsElementExist(url, locator, "Recirc Content");
	}
	
	@Test(dependsOnMethods = {"recircContent_TOHRecipeDetailPage"}, alwaysRun = true)
	public void recipeTime_TOHRecipeDetailPage() throws IOException, InterruptedException {
		GlobalFunctions globalPage = PageFactory.initElements(driver, GlobalFunctions.class);
		
		By locator = By.className("recipe-time-yield__label-prep");
		
		globalPage.IsElementExist(url, locator, "Recipe Time");
	}

	@Test(dependsOnMethods = {"recipeTime_TOHRecipeDetailPage"}, alwaysRun = true)
	public void recipeYield_TOHRecipeDetailPage() throws IOException, InterruptedException {
		GlobalFunctions globalPage = PageFactory.initElements(driver, GlobalFunctions.class);
		
		By locator = By.className("recipe-time-yield__label-servings");
		
		globalPage.IsElementExist(url, locator, "Recipe Yield");
	}
	
	@Test(dependsOnMethods = {"recipeYield_TOHRecipeDetailPage"}, alwaysRun = true)
	public void recirc_module_Title() throws IOException, InterruptedException {
		GlobalFunctions globalPage = PageFactory.initElements(driver, GlobalFunctions.class);
		
		By locator = By.xpath("//h2[@class='recirc_content_title']");
		
		globalPage.IsElementExist(url, locator, "Recipe Yield");
		//globalPage.brandLinksNavigation(locator, url, "Recirc Module ");
	}
	
	@Test(dependsOnMethods = {"recipeYield_TOHRecipeDetailPage"}, alwaysRun = true)
	public void recirc_module_LinksCheck() throws IOException, InterruptedException {
		GlobalFunctions globalPage = PageFactory.initElements(driver, GlobalFunctions.class);
		
		By locator = By.xpath("//div[@class='recirc-module']//a");
		
		globalPage.IsElementExist(url, locator, "Recirc module title ");
		globalPage.brandLinksNavigation(locator, url, "Recirc Module ");
	}
	
	@Test(dependsOnMethods = {"recipeYield_TOHRecipeDetailPage"}, alwaysRun = true)
	public void verifyTaboolaContainer_TOHRecipeDetailPage() throws InterruptedException, IOException{
		String element = "Taboola container ";
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step
		globalPage.IsElementExist(url, Constant.taboolaContainerLocator, element);
	}
	
	@AfterMethod
	public void nameAfter(Method method)
	{
		System.out.println("Test : " + method.getName() + " execution completed .................... \n");       
		System.out.println("********************************************************************************************");
	}


}


