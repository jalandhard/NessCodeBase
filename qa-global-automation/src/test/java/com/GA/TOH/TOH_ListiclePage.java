package com.GA.TOH;

import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.wp.genericLib.ConfigFileReader;
import com.wp.genericLib.Constant;
import com.wp.genericLib.Driver;
import com.wp.pageObjectRepo.GlobalFunctions;

public class TOH_ListiclePage extends Driver {
	//Initialize object using page factory method 
	ConfigFileReader configFileReader;
	public Logger APPLICATION_LOGS = LogManager.getLogger("AppLogger");

	//Test Data
	String url=Constant.TOH_ListiclePage;
	String newspapaerSignUpLink = Constant.TOH_NewspaperSignUpLink;
	
	public static By cardImageLocator = By.xpath("//div[contains(@class, 'listicle-card')]/div[2]");
	public static By cardImageCreditLocator = By.xpath("//div[contains(@class, 'listicle-card')]/span/span[contains(@class, 'image-credit')]");
	public static By cardTitleLocator = By.xpath("//div[contains(@class, 'listicle-card')]//h2");
	public static By avatarLocator = By.xpath("//div[@class='byline']/img");
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
	public void verifyTitle_TOH_ListiclePage() throws IOException, InterruptedException {
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		String element = "Title";
		//Test Step

		globalPage.verifyIsElementAvailable(Constant.listicleTitleLocator, url, element);
	}

	@Test(dependsOnMethods = {"verifyTitle_TOH_ListiclePage"}, alwaysRun = true)
	public void verifyLineTest_TOH_ListiclePage() throws InterruptedException, IOException{
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step

		globalPage.isLineExist(url,avatarLocator, Constant.authorLocator);
	}
	
	@Test(dependsOnMethods = {"verifyLineTest_TOH_ListiclePage"}, alwaysRun = true)
	public void verifySocialShareLinks_TOH_ListiclePage() throws IOException, InterruptedException
	{
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step
		
		globalPage.socialShareNavigation(Constant.listicleTitleLocator, socialShareLinks, url);
	}


	

	@Test(dependsOnMethods = {"verifySocialShareLinks_TOH_ListiclePage"}, alwaysRun = true)
	public void verifyDekTest_TOH_ListiclePage() throws IOException, InterruptedException {

		String dekTest = "Dek";
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step

		globalPage.IsElementExist(url, Constant.dekLocator, dekTest);
	}


	

	@Test(dependsOnMethods = {"verifyDekTest_TOH_ListiclePage"}, alwaysRun = true)
	public void verifycardImage_TOH_ListiclePage() throws InterruptedException, IOException {
		String element = "Card Image ";
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step

		globalPage.IsElementExist(url, cardImageLocator, element);
	}

	@Test(dependsOnMethods = {"verifycardImage_TOH_ListiclePage"}, alwaysRun = true)
	public void verifycardPageCount_TOH_ListiclePage() throws InterruptedException, IOException {
		String element = "Card page ";
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step

		globalPage.IsElementExist(url, Constant.cardPageCountLocator, element);
	}

	@Test(dependsOnMethods = {"verifycardPageCount_TOH_ListiclePage"}, alwaysRun = true)
	public void verifycardImageCredit_TOH_ListiclePage() throws InterruptedException, IOException {
		String element = "Card Image Credit ";
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step

		globalPage.IsElementExist(url,cardImageCreditLocator, element);
	}

	@Test(dependsOnMethods = {"verifycardImageCredit_TOH_ListiclePage"}, alwaysRun = true)
	public void verifycardTitle_TOH_ListiclePage() throws InterruptedException, IOException {
		String element = "Card Title ";
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step

		globalPage.IsElementExist(url,cardTitleLocator, element);
	}

	@Test(dependsOnMethods = {"verifycardTitle_TOH_ListiclePage"}, alwaysRun = true)
	public void verifycardCaption_TOH_ListiclePage() throws InterruptedException, IOException {
		String element = "Card Caption ";
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step

		globalPage.IsElementExist(url, Constant.cardCaptionLocator, element);
	}


	/*@Test(dependsOnMethods = {"verifycardCaption_TOH_ListiclePage"}, alwaysRun = true)
	public void verifyPostDate_TOH_ListiclePage() throws InterruptedException, IOException{
		String element = "Post date  ";
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step

		globalPage.IsElementExist(url, Constant.postDateLocator, element);
	}*/


	@Test(dependsOnMethods = {"verifycardCaption_TOH_ListiclePage"}, alwaysRun = true)
	public void verifyOriginallyPublishedBrand_TOH_ListiclePage() throws InterruptedException, IOException{
		String element = "Originally publised brand ";
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step

		globalPage.IsElementExist(url, Constant.originallyPublishedBrandLocator, element);
	}

	@Test(dependsOnMethods = {"verifyOriginallyPublishedBrand_TOH_ListiclePage"}, alwaysRun = true)
	public void verifyOriginallyPublishedURL_TOH_ListiclePage() throws InterruptedException, IOException{
		String element = "Originally publised URL ";
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step

		globalPage.IsElementExist(url, Constant.originallyPublishedUrlLocator, element);
	}


	@Test(dependsOnMethods = {"verifyOriginallyPublishedURL_TOH_ListiclePage"}, alwaysRun = true)
	public void verifyTaboolaContainer_TOH_ListiclePage() throws InterruptedException, IOException{
		String element = "Taboola container ";
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step

		globalPage.IsElementExist(url, Constant.taboolaContainerLocator, element);
	}

	@Test(dependsOnMethods = {"verifyTaboolaContainer_TOH_ListiclePage"}, alwaysRun = true)
	public void verifyTaboolaSidebar_TOH_ListiclePage() throws InterruptedException, IOException{
		String element = "Taboola Sidebar ";
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step

		globalPage.IsElementExist(url, Constant.taboolaSidebarLocator, element);
	}


	@AfterMethod
	public void nameAfter(Method method)
	{
		System.out.println("Test : " + method.getName() + " execution completed ...............\n");       
		System.out.println("********************************************************************************************");
	}

}
