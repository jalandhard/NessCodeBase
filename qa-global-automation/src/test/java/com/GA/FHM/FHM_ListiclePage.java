package com.GA.FHM;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.awt.RenderingHints.Key;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
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

public class FHM_ListiclePage extends Driver{
	//Initialize object using page factory method 
	ConfigFileReader configFileReader;
	public Logger APPLICATION_LOGS = LogManager.getLogger("AppLogger");

	//Test Data
	String url=Constant.FHM_ListiclePage;
	String newspapaerSignUpLink = Constant.FHM_NewspaperSignUpLink;
	//card
	private By cardImageLocator = By.xpath("//div[@class='listicle-card']//img");
	private By cardPageCountLocator = By.xpath("//div[contains(@class, 'listicle-card')]/div[contains(@class, 'card-number')]");
	private By cardImageCreditLocator = By.xpath("//span[@class='image-credit noskim']");
	private By cardTitleLocator = By.xpath("//div[contains(@class, 'listicle-card')]/div[contains(@class, 'card-content')]/h2");
	private By cardCaptionLocator = By.xpath("//div[contains(@class, 'listicle-card')]/div[contains(@class, 'card-content')]/p[contains(@class, 'p1')]");

	private By socialShareLinks = By.xpath("//section[@class='social-menu-desktop pure-u-lg-2-24']//a");
	
	
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
	    System.out.println("Test : " + method.getName() + " execution started ................");       
	}

	@Test
	public void verifyTitle_FHM_ListiclePage() throws IOException, InterruptedException {
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);
		
		String element = "Title";
		//Test Step
		globalPage.verifyIsElementAvailable(Constant.listicleTitleLocator, url, element);
	}

	@Test(dependsOnMethods = {"verifyTitle_FHM_ListiclePage"}, alwaysRun = true)
	public void verifyLineTest_FHM_ListiclePage() throws InterruptedException, IOException{
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step
		globalPage.isLineExist(url, Constant.avatarLocator, Constant.authorLocator);
	}
	
		
	/*@Test(dependsOnMethods = {"verifyLineTest_FHM_ListiclePage"}, alwaysRun = true)
	public void verifyFacebookLink_FHM_ListiclePage() throws IOException, InterruptedException {
		String facebookLink = "facebook.com";

		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step
		globalPage.Test(url, Constant.menuListLocator, Constant.fb,facebookLink );
	}


	@Test(dependsOnMethods = {"verifyFacebookLink_FHM_ListiclePage"}, alwaysRun = true)
	public void verifyTwitterLink_FHM_ListiclePage() throws InterruptedException, IOException {

		String twitterLink = "twitter.com";
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step
		globalPage.Test(url, Constant.menuListLocator, Constant.twitterIconLocator,twitterLink );	

	}

	@Test(dependsOnMethods = {"verifyTwitterLink_FHM_ListiclePage"}, alwaysRun = true)
	public void verifyPinteresttLink_FHM_ListiclePage() throws InterruptedException, IOException {

		String pinterestLink = "pinterest.com";
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step
		globalPage.Test(url, Constant.menuListLocator, Constant.pinterestIconLocator,pinterestLink );	

	}
	*/
	@Test(dependsOnMethods = {"verifyLineTest_FHM_ListiclePage"}, alwaysRun = true)
	public void verifyDekTest_FHM_ListiclePage() throws IOException, InterruptedException {

		String dekTest = "Dek";
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step
		globalPage.IsElementExist(url, Constant.dekLocator, dekTest);
	}
	
	@Test(dependsOnMethods = {"verifyDekTest_FHM_ListiclePage"}, alwaysRun = true)
	public void verifycardImage_FHM_ListiclePage() throws InterruptedException, IOException {
		String element = "Card Image ";
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step
		globalPage.IsElementExist(url, cardImageLocator, element);
	}
	
	@Test(dependsOnMethods = {"verifycardImage_FHM_ListiclePage"}, alwaysRun = true)
	public void verifycardPageCount_FHM_ListiclePage() throws InterruptedException, IOException {
		String element = "Card page ";
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step
		globalPage.IsElementExist(url, Constant.cardPageCountLocator, element);
	}
	
	@Test(dependsOnMethods = {"verifycardPageCount_FHM_ListiclePage"}, alwaysRun = true)
	public void verifycardImageCredit_FHM_ListiclePage() throws InterruptedException, IOException {
		String element = "Card Image Credit ";
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step
		globalPage.IsElementExist(url, cardImageCreditLocator, element);
	}
	
	@Test(dependsOnMethods = {"verifycardImageCredit_FHM_ListiclePage"}, alwaysRun = true)
	public void verifycardTitle_FHM_ListiclePage() throws InterruptedException, IOException {
		String element = "Card Title ";
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step
		globalPage.IsElementExist(url, Constant.cardTitleLocator, element);
	}
	
	@Test(dependsOnMethods = {"verifycardTitle_FHM_ListiclePage"}, alwaysRun = true)
	public void verifycardCaption_FHM_ListiclePage() throws InterruptedException, IOException {
		String element = "Card Caption ";
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step
		globalPage.IsElementExist(url, Constant.cardCaptionLocator, element);
	}
	
	@Test(dependsOnMethods = {"verifycardCaption_FHM_ListiclePage"}, alwaysRun = true)
	public void verifySocialShareLinks() throws IOException, InterruptedException
	{
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step
		globalPage.socialShareNavigation(Constant.listicleTitleLocator, socialShareLinks, url);
	}
	
	@Test(dependsOnMethods = {"verifyCardCount_FHM_ListiclePage"}, alwaysRun = true)
	public void verifyTaboolaSidebar_FHM_ListiclePage() throws InterruptedException, IOException{
		String element = "Taboola Sidebar ";
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step
		globalPage.IsElementExist(url, Constant.taboolaSidebarLocator, element);
	}
		
	@Test(dependsOnMethods = {"verifyTaboolaSidebar_FHM_ListiclePage"}, alwaysRun = true)
	public void verifyTaboolaContainer_FHM_ListiclePage() throws InterruptedException, IOException{
		String element = "Taboola container ";
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		 Actions action = new Actions(driver);
		 action.sendKeys(Keys.chord(Keys.SHIFT),
		 Keys.chord(Keys.END)).build().perform();
		 Thread.sleep(3000);
		/*((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.id("Constant.taboolaSidebarLocator")));
		Thread.sleep(500); */
		//Test Step
		globalPage.IsElementExist(url, Constant.taboolaContainerLocator, element);
	}
	
	
	@AfterMethod
	public void nameAfter(Method method)
	{
	    System.out.println("Test : " + method.getName() + " execution completed ...............\n");       
	    System.out.println("********************************************************************************************");
	}
}
	






