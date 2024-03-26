package com.GA.TOH;

import java.io.IOException;
import java.lang.reflect.Method;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.wp.genericLib.Constant;
import com.wp.genericLib.Driver;
import com.wp.pageObjectRepo.GlobalFunctions;

public class TOH_FooterTests extends Driver {

	//private Link footerLogoLink = new Link(driver, By.className("footer-logo"), "https://www.constructionprotips.com/");
	private By footerLogo = By.className("footer-logo");
	//social media icons
	private By facebookIconLocator = By.xpath("//i[@class='fa fa-facebook']");
	private By twitterIconLocator = By.xpath("//i[@class='fa fa-twitter']");
	private By pinterestIconLocator = By.xpath("//i[@class='fa fa-pinterest']");
	private By youtubeIconLocator = By.xpath("//i[@class='fa fa-youtube']");
	private By instagramIconLocator = By.xpath("//i[@class='fa fa-instagram']");

	private By socialIcons = By.xpath("//ul[@id='menu-tmbi-social-profiles']//a");

	//copyright
	private By copyrightLocator = By.className("copyright");

	//newsletter
	private By newsletterSignUpTitle = By.className("newsletter-signup-content-widget__title");
	private By newsletterSignUpInput = By.name("EmailAddress");
	private By newsletterSignUpButton = By.xpath("//div[@class='newsletter']//form"); //By.xpath("//div[@class='pure-u-1 pure-u-md-2-5 right-foot']//form//button");
	private By newsletterSignUpTagLine = By.xpath("//div[@class='pure-u-1 pure-u-md-2-5 right-foot']//div[@class='diyu-text']");
	private By newsletterSignUpImage = By.xpath("//div[@class='pure-u-1 pure-u-md-2-5 right-foot']//div[@class='diyu-img']");
	String newspapaerSignUpLink = Constant.TOH_NewspaperSignUpLink;

	//DIYU logo
	//String diyuLink = Constant.diyuLogoLink;
	private By universityLocator = By.xpath("//div[@class='pure-u-1 pure-u-md-2-5 right-foot']//div[@class='diyu-img']");

	private By ourBrandsLocator = By.xpath("//ul[@id='menu-our-brands']//a");
	private By globalLinksLocator = By.xpath("//ul[@class='footer-global-links']//a");
	private By siteLinksLocator = By.xpath("//div[@class='site-links']//a");

	//Test Data
	String url=Constant.TOH_HomePage;

	@BeforeClass
	public void navigateToRequiredURL() throws Exception {
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


	//Brand Logo
	//There is no brand logo on mobile
	@Test()
	public void verify_BrandLogo_FooterSection() throws IOException, InterruptedException {

		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		String element = "Brand Logo";
		//Test Step
		//Step 2 Verify Amazon Header Bidder is Running
		globalPage.verifyIsElementAvailable(footerLogo, url, element);


	}
	@Test(dependsOnMethods = {"verify_BrandLogo_FooterSection"}, alwaysRun = true)
	public void verify_copyright_FooterSection() throws InterruptedException, IOException {

		String element = "Copyright ";
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step
		//Step 2 Verify Amazon Header Bidder is Running
		globalPage.verifyIsElementAvailable(copyrightLocator, url, element);

	}
	@Test(dependsOnMethods = {"verify_copyright_FooterSection"}, alwaysRun = true)
	public void verify_ourBrandsLinks_FooterSection() throws IOException, InterruptedException
	{
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);
		String element = "Our Brand Links";
		//globalPage.brandLinksNavigation_TOH(ourBrandsLocator, url, element);
		globalPage.verifyPostsImages_Clickable(ourBrandsLocator, url);
		globalPage.brandLinksNavigation(ourBrandsLocator, url, element);


	}

	@Test(dependsOnMethods = {"verify_ourBrandsLinks_FooterSection"}, alwaysRun = true)
	public void verify_GlobalBrandsLinks_FooterSection() throws IOException, InterruptedException
	{
		String element = "Global Brands Links";
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//globalPage.brandLinksNavigation_TOH(globalLinksLocator, url, element);
		globalPage.verifyPostsImages_Clickable(globalLinksLocator, url);
		globalPage.brandLinksNavigation(globalLinksLocator, url,element);


	}
	@Test(dependsOnMethods = {"verify_GlobalBrandsLinks_FooterSection"}, alwaysRun = true)
	public void verify_siteLinks_FooterSection() throws IOException, InterruptedException
	{
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);
		String element = "Site Links";
		//globalPage.brandLinksNavigation_TOH(siteLinksLocator, url, element);
		globalPage.verifyPostsImages_Clickable(siteLinksLocator, url);
		globalPage.brandLinksNavigation(siteLinksLocator, url, element);

	}

	@Test (dependsOnMethods = {"verify_GlobalBrandsLinks_FooterSection"}, alwaysRun = true)
	public void verify_socialSiteLinks_FooterSection() throws IOException, InterruptedException
	{
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);
		String element = "Social site Links";
		//globalPage.brandLinksNavigation_TOH(socialIcons, url, element);
		globalPage.verifyPostsImages_Clickable(socialIcons, url);
		globalPage.brandLinksNavigation(socialIcons, url, element);

	}

	/**
	 * This test clicks the sign up button on the newsletter sign up widget and ensures the user is brought to the sign up page.
	 */
	@Test(dependsOnMethods = {"verify_socialSiteLinks_FooterSection"}, alwaysRun = true)
	public void verify_NewsletterSignUpPage_FooterSection() throws IOException, InterruptedException
	{
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		globalPage.verifyNewsletterSignUp_Footer(url, newsletterSignUpInput, newsletterSignUpButton, newspapaerSignUpLink );

	}

	//not available on rf fhm sites
	/*@Test(dependsOnMethods = {"verify_NewsletterSignUpPage_FooterSection"}, alwaysRun = true)
	public void verify_DiyuLogoTest_FooterSection() throws IOException, InterruptedException
	{
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		globalPage.verifyDiyuLogo(url, universityLocator, diyuLink);

	}

	 */

	/*//Social media tests
	//Social media icons open new tabs when opened
	//Find better way to determine if we are on the brands page or a 404 page

	@Test(dependsOnMethods = {"verify_NewsletterSignUpPage_FooterSection"}, alwaysRun = true)
	public void verifyFacebookLink_FooterSection() throws IOException, InterruptedException {
		String facebookLink = "facebook.com";

		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step
		//Step 2 Verify Amazon Header Bidder is Running
		globalPage.Test(url, facebookIconLocator, facebookIconLocator,facebookLink );
	}


	@Test(dependsOnMethods = {"verifyFacebookLink_FooterSection"}, alwaysRun = true)
	public void verify_InstagramLink_FooterSection() throws InterruptedException, IOException {

		String instaLink = "instagram.com";
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step
		//Step 2 Verify Amazon Header Bidder is Running
		globalPage.Test(url, instagramIconLocator, instagramIconLocator,instaLink );	

	}


	@Test(dependsOnMethods = {"verify_InstagramLink_FooterSection"}, alwaysRun = true)
	public void verifyTwitterLink_FooterSection() throws InterruptedException, IOException {

		String twittertLink = "twitter.com";
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step
		//Step 2 Verify Amazon Header Bidder is Running
		globalPage.Test(url, twitterIconLocator, twitterIconLocator,twittertLink );	

	}

	@Test(dependsOnMethods = {"verify_InstagramLink_FooterSection"}, alwaysRun = true)
	public void verifyYouTubeLink_FooterSection() throws InterruptedException, IOException {

		String utubeLink = "pinterest.com";
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step
		//Step 2 Verify Amazon Header Bidder is Running
		globalPage.Test(url, youtubeIconLocator, youtubeIconLocator,utubeLink );	

	}

	@Test(dependsOnMethods = {"verify_InstagramLink_FooterSection"}, alwaysRun = true)
	public void verifyPinteresttLink_FooterSection() throws InterruptedException, IOException {

		String pinterestLink = "pinterest.com";
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Test Step
		//Step 2 Verify Amazon Header Bidder is Running
		globalPage.Test(url, pinterestIconLocator, pinterestIconLocator,pinterestLink );	

	}


		//End social media tests
	 */

	@AfterMethod
	public void nameAfter(Method method)
	{
		System.out.println("Test : " + method.getName() + " execution completed .................. \n");   
		System.out.println("********************************************************************************************");
	}




}