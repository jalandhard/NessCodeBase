package com.GA.FHM;

import static org.testng.Assert.assertFalse;

import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

//import com.globalAutomation.misc.Link;
import com.wp.genericLib.ConfigFileReader;
import com.wp.genericLib.Constant;
import com.wp.genericLib.Driver;
import com.wp.pageObjectRepo.GlobalFunctions;

public class FHM_ProjectDetailPage extends Driver{
	//Initialize object using page factory method 
	ConfigFileReader configFileReader;
	public Logger APPLICATION_LOGS = LogManager.getLogger("AppLogger");

	//Test Data
	String url=Constant.FHM_ProjectDetailPage;
	String newspapaerSignUpLink = Constant.FHM_NewspaperSignUpLink;
	//String diyuLink = Constant.diyuLogoLink;

	
	//Newsletter sign up container
	private By title= By.xpath("//h1[@class='post-title']");
	private By dek= By.className("dek");

	private By projectImg =By.xpath("//div[@class='featured-image']/img"); 
	private By imageCredit =By.xpath("//div[@class='featured-image']//span[@class='image-credit']"); 

	private By nextPrjctLink = By.xpath("//a[contains(@class,'btn next-project')]");
	//private By socialShareLinks = By.xpath("//div[@class='social-share']//ul[@class='pure-menu-list social-menu']//a");
	/*private By socialShareLinks1 = By.xpath("//header[@class='entry-header']//div[@class='social-share social-share-sticky']");
	private By socialShareLinks = By.xpath("//div[@class='social-share']//ul[@class='pure-menu-list social-menu']//a");*/
	private By fblink = By.xpath("//div[@class='social-share social-share-sticky']//a[@id='fb-share']");
	private By fbLocator = By.xpath("//div[@class='social-share social-share-sticky']//a[@id='fb-share']");
	private By twitterIconLocator = By.xpath("//div[@class='social-share social-share-sticky']//a[@id='twitter-share']");
	private By pinterestIconLocator = By.xpath("//div[@class='social-share social-share-sticky']//a[@id='pinterest-share']");
	private By emailWidget = By.xpath("//div[@class='social-share social-share-sticky']//a[@id='email_a_friend']");
	private By printWidget = By.id("print-project");
	private By projectTitle = By.xpath("//h2[contains(text(),'Introduction')]");
	private By paragraphcontent = By.xpath("//div[@class='introduction']");
	private By projectauthor = By.className("project-author");
	private By toolsReq = By.className("tools-required");
	private By materialsReq = By.className("materials-required");
	private By projectSteps = By.className("project-page-group-container");
	private By similarProjects = By.className("recirc-module");
	private By taboolaFeeds = By.xpath("//section[@class='widget widget_taboola_widget']"); //div[@class='tbl-feed-header-logo']");
	//private By socialShareLinks = By.xpath("//header[@class='entry-header']//div[@class='social-share social-share-sticky']");
	private By socialShareLinks = By.xpath("//div[@class='social-sharing social-menu-desktop']//a");   //header[@class='entry-header']//div[@class='social-share social-share-sticky']");
	private By jw_video = By.xpath("//video[@class='jw-video jw-reset']");
	private By newsletterSignupButtonLocator= By.xpath("//div[@class='nl-container']//button[@id='subscribe']");
	

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


	@Test//(dependsOnMethods = {"verify_Banner_FHM_ProjectDetailPage"}, alwaysRun = true)
	public void verify_BreadCrumb_FHM_ProjectDetailPage() throws IOException, InterruptedException {
	
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step
		//Step 2 Verify Amazon Header Bidder is Running
		globalPage.verifyIsBreadCrumbAvailable(Constant.breadcrumbLocator, url);
	
	}
	
	/*@Test
	public void projectDetailNativoTest_FHM_ProjectDetailPage() {
		By nativo = By.id("projectd_nativo1");
		
		assertFalse(driver.findElements(nativo).isEmpty(), "projectd_nativo1 ID is not found on project detail page on URL " + url);
	}*/

	@Test(dependsOnMethods = {"verify_BreadCrumb_FHM_ProjectDetailPage"}, alwaysRun = true)
	public void verify_Title_FHM_ProjectDetailPage() throws IOException, InterruptedException {
	
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);
		
		String element = "Title";
		//Test Step
		//Step 2 Verify Amazon Header Bidder is Running
		globalPage.verifyIsElementAvailable(title, url, element);
	
		
	}

	@Test(dependsOnMethods = {"verify_Title_FHM_ProjectDetailPage"}, alwaysRun = true)
	public void verify_Dek_FHM_ProjectDetailPage() throws IOException, InterruptedException {
		String dekTest = "Dek";
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step
		//Step 2 Verify Amazon Header Bidder is Running
		globalPage.verifyIsElementAvailable(dek, url, dekTest);
	}

	@Test(dependsOnMethods = {"verify_Dek_FHM_ProjectDetailPage"}, alwaysRun = true)
	public void verifySocialShareLinks() throws IOException, InterruptedException
	{
		//Object Initialization
				GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

				//Test Step
				//Step 2 Verify Amazon Header Bidder is Running
				globalPage.socialShareNavigation(jw_video, socialShareLinks, url);

	}
	

	/*@Test(dependsOnMethods = {"verify_Dek_FHM_ProjectDetailPage"}, alwaysRun = true)
	public void verify_FacebookLink_FHM_ProjectDetailPage() throws IOException, InterruptedException {
		String facebookLink = "facebook.com";

		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step
		//Step 2 Verify Amazon Header Bidder is Running
		globalPage.Test(url, Constant.dekLocator, fbLocator,facebookLink );
	}


	@Test(dependsOnMethods = {"verify_FacebookLink_FHM_ProjectDetailPage"}, alwaysRun = true)
	public void verify_TwitterLink_FHM_ProjectDetailPage() throws InterruptedException, IOException {

		String twitterLink = "twitter.com";
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step
		//Step 2 Verify Amazon Header Bidder is Running
		globalPage.Test(url, Constant.dekLocator, twitterIconLocator,twitterLink );	

	}

	@Test(dependsOnMethods = {"verify_TwitterLink_FHM_ProjectDetailPage"}, alwaysRun = true)
	public void verify_PinteresttLink_FHM_ProjectDetailPage() throws InterruptedException, IOException {

		String pinterestLink = "pinterest.com";
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step
		//Step 2 Verify Amazon Header Bidder is Running
		globalPage.Test(url, Constant.dekLocator, pinterestIconLocator,pinterestLink );	

	}

	@Test(dependsOnMethods = {"verify_PinteresttLink_FHM_ProjectDetailPage"}, alwaysRun = true)
	public void verify_EmailLink_FHM_ProjectDetailPage() throws InterruptedException, IOException {

		String emailLink = "Email social share link ";
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step
		//Step 2 Verify Amazon Header Bidder is Running
		globalPage.Test(url, Constant.dekLocator, emailWidget,emailLink );	

	}

	@Test(dependsOnMethods = {"verify_EmailLink_FHM_ProjectDetailPage"}, alwaysRun = true)
	public void verify_PrintlLink_FHM_ProjectDetailPage() throws InterruptedException, IOException {

		String printLink = "Print social share link ";
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step
		//Step 2 Verify Amazon Header Bidder is Running
		globalPage.Test(url, Constant.dekLocator, printWidget,printLink );	

	}*/
	
	@Test(dependsOnMethods = {"verifySocialShareLinks"}, alwaysRun = true)
	public void verify_FeaturedImage_FHM_ProjectDetailPage() throws IOException, InterruptedException {
		String dekTest = "Featured Image";
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step
		//Step 2 Verify Amazon Header Bidder is Running
		globalPage.verifyIsElementAvailable(projectImg, url, dekTest);
		
	}

	@Test(dependsOnMethods = {"verify_FeaturedImage_FHM_ProjectDetailPage"}, alwaysRun = true)
	public void verify_NextProjectLink_FHM_ProjectDetailPage() throws IOException, InterruptedException {
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step
		String element = "Next Project Link";
		//Step 2 Verify Amazon Header Bidder is Running
		//globalPage.verifyNextProject(nextPrjctLink, url, element);
		globalPage.verifyPostsImages_Clickable(nextPrjctLink, url);
		globalPage.verifyLinkStatus(nextPrjctLink, url, element);
	}
	
	@Test(dependsOnMethods = {"verify_NextProjectLink_FHM_ProjectDetailPage"}, alwaysRun = true)
	public void verify_paragraphTitle_FHM_ProjectDetailPage() throws InterruptedException, IOException {

		String element = "Paragraph Title ";
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step
		//Step 2 Verify Amazon Header Bidder is Running
		globalPage.verifyIsElementAvailable(projectTitle, url, element);

	}

	@Test(dependsOnMethods = {"verify_paragraphTitle_FHM_ProjectDetailPage"}, alwaysRun = true)
	public void verify_paragraphContent_FHM_ProjectDetailPage() throws InterruptedException, IOException {

		String element = "Paragraph Content ";
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step
		//Step 2 Verify Amazon Header Bidder is Running
		globalPage.verifyIsElementAvailable(paragraphcontent, url, element);

	}

	/*@Test(dependsOnMethods = {"verify_paragraphContent_FHM_ProjectDetailPage"}, alwaysRun = true)
	public void verify_paragraphAuthor_FHM_ProjectDetailPage() throws InterruptedException, IOException {

		String element = "Paragraph Author  ";
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step
		//Step 2 Verify Amazon Header Bidder is Running
		globalPage.verifyIsElementAvailable(projectauthor, url, element);
	}*/

	@Test(dependsOnMethods = {"verify_paragraphContent_FHM_ProjectDetailPage"}, alwaysRun = true)
	public void verify_ToolsRequired_FHM_ProjectDetailPage() throws InterruptedException, IOException {

		String element = "Tools Required panel ";
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step
		//Step 2 Verify Amazon Header Bidder is Running
		globalPage.verifyIsElementAvailable(toolsReq, url, element);

	}

	@Test(dependsOnMethods = {"verify_ToolsRequired_FHM_ProjectDetailPage"}, alwaysRun = true)
	public void verify_MaterialsRequired_FHM_ProjectDetailPage() throws InterruptedException, IOException {

		String element = "Materials Required panel  ";
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step
		//Step 2 Verify Amazon Header Bidder is Running
		globalPage.verifyIsElementAvailable(materialsReq, url, element);
	}
	
	@Test(dependsOnMethods = {"verify_MaterialsRequired_FHM_ProjectDetailPage"}, alwaysRun = true)
	public void verify_ProjectStepBystepContainer_FHM_ProjectDetailPage() throws InterruptedException, IOException {

		String element = "Project Step By step Container ";
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step
		//Step 2 Verify Amazon Header Bidder is Running
		globalPage.verifyIsElementAvailable(projectSteps, url, element);

	}

	@Test(dependsOnMethods = {"verify_ProjectStepBystepContainer_FHM_ProjectDetailPage"}, alwaysRun = true)
	public void verify_SimilarProjects_FHM_ProjectDetailPage() throws IOException, InterruptedException
	{
		//Object Initialization
		String element = "similar Projects panel ";
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step
		//Step 2 Verify Amazon Header Bidder is Running
		globalPage.verifyIsElementAvailable(similarProjects, url, element);
	}
		

	@Test(dependsOnMethods = {"verify_SimilarProjects_FHM_ProjectDetailPage"}, alwaysRun = true)
	public void verify_TaboolaContainer_FHM_ProjectDetailPage() throws IOException, InterruptedException
	{
		//Object Initialization
				String element = "Taboola Container ";
				//Object Initialization
				GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);
				Actions action = new Actions(driver);
				WebElement similarPrjcts = driver.findElement(similarProjects);
				WebElement taboolaFeed = driver.findElement(taboolaFeeds);
				action.moveToElement(similarPrjcts);
				action.moveToElement(taboolaFeed);
			   /* action.sendKeys(Keys.chord(Keys.SHIFT),
			        Keys.chord(Keys.END)).build().perform();
			 Thread.sleep(3000);*/
				//Test Step
				//Step 2 Verify Amazon Header Bidder is Running
				globalPage.verifyIsElementAvailable(taboolaFeeds, url, element);

	}

	/*@Test(dependsOnMethods = {"verify_TaboolaContainer_FHM_ProjectDetailPage"}, alwaysRun = true)
	public void verifySocialShareLinks() throws IOException, InterruptedException
	{
		//Object Initialization
				GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

				//Test Step
				//Step 2 Verify Amazon Header Bidder is Running
				globalPage.socialShareNavigation(jw_video, socialShareLinks, url);
	}*/
	
	/*@Test()
	public void verifySocialShareLinks() throws IOException, InterruptedException
	{
		//Object Initialization
				GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

				//Test Step
				//Step 2 Verify Amazon Header Bidder is Running
				globalPage.socialShareNavigation(projectImg, socialShareLinks, url);
	}
*/

	
	@AfterMethod
	public void nameAfter(Method method)
	{
	    System.out.println("Test : " + method.getName() + " execution completed .................. \n");       
	    System.out.println("********************************************************************************************");
	}


}
