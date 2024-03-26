package com.wp.genericLib;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import com.wp.genericLib.Driver;

public class Constant extends Driver {

//Newspaper SignUp Link
	public static final String NewspaperSignUpLink= ExcelLibrary.getExcelData("GA_CPT", 8, 3);
	
//	public static final String diyuLogoLink= 		ExcelLibrary.getExcelData("GA_CPT", 1, 4);

	//FHM 	
	public static final String FHM_HomePage= 		ExcelLibrary.getExcelData("GA_CPT", 8, 2);
	public static final String FHM_Page= 		ExcelLibrary.getExcelData("GA_CPT", 9, 2);
	public static final String FHM_HubPage= 			ExcelLibrary.getExcelData("GA_CPT", 10, 2);
	public static final String FHM_ArticlePage= 		ExcelLibrary.getExcelData("GA_CPT", 11, 2);
	public static final String FHM_ListiclePage= 		ExcelLibrary.getExcelData("GA_CPT", 12, 2);
	public static final String FHM_AuthorHub= 	ExcelLibrary.getExcelData("GA_CPT", 13, 2);
	public static final String FHM_VideoHubPage= 	ExcelLibrary.getExcelData("GA_CPT", 14, 2);
	public static final String FHM_ProjectDetailPage= 		ExcelLibrary.getExcelData("GA_CPT", 15, 2);
	public static final String FHM_LegacyProjectDetailPage= 		ExcelLibrary.getExcelData("GA_CPT", 16, 2);

	//Newspaper SignUp Link
	public static final String FHM_NewspaperSignUpLink= ExcelLibrary.getExcelData("GA_CPT", 8, 3);
	public static final String FHM_diyuLogoLink= 		ExcelLibrary.getExcelData("GA_CPT", 8, 4);
	public static final String FHM_authorPage=			ExcelLibrary.getExcelData("GA_CPT", 11, 3);

	//RD 
	public static final String RD_HomePage = ExcelLibrary.getExcelData("GA_CPT", 18, 2);
	public static final String RD_Page= 		ExcelLibrary.getExcelData("GA_CPT", 19, 2);
	public static final String RD_HubPage= 			ExcelLibrary.getExcelData("GA_CPT", 20, 2);
	public static final String RD_ArticlePage= 		ExcelLibrary.getExcelData("GA_CPT", 21, 2);
	public static final String RD_ListiclePage= 		ExcelLibrary.getExcelData("GA_CPT", 22, 2);
	public static final String RD_AuthorPage= 		ExcelLibrary.getExcelData("GA_CPT", 23, 2);
	public static final String RD_VideoHubPage= 	ExcelLibrary.getExcelData("GA_CPT", 24, 2);
	public static final String RD_JokePage = ExcelLibrary.getExcelData("GA_CPT", 25, 2);
	//Newspaper SignUp Link
	public static final String RD_NewspaperSignUpLink= ExcelLibrary.getExcelData("GA_CPT", 18, 3);
	public static final String RD_NewspaperLink= ExcelLibrary.getExcelData("GA_CPT", 19, 3);

	//HLT 
	public static final String HLT_HomePage = ExcelLibrary.getExcelData("GA_CPT",26, 2);
	public static final String HLT_Page= 		ExcelLibrary.getExcelData("GA_CPT", 27, 2);
	public static final String HLT_HubPage= 			ExcelLibrary.getExcelData("GA_CPT", 28, 2);
	public static final String HLT_ArticlePage= 		ExcelLibrary.getExcelData("GA_CPT", 29, 2);
	public static final String HLT_ListiclePage= 		ExcelLibrary.getExcelData("GA_CPT", 30, 2);
	public static final String HLT_AuthorHub= 		ExcelLibrary.getExcelData("GA_CPT", 31, 2);
	public static final String HLT_VideoHubPage= 	ExcelLibrary.getExcelData("GA_CPT", 32, 2);
	public static final String HLT_RecipeDetailPage = ExcelLibrary.getExcelData("GA_CPT",33, 2);
	//Newspaper SignUp Link
	public static final String HLT_NewspaperSignUpLink= ExcelLibrary.getExcelData("GA_CPT", 26, 3);

		   
	//SRD 
	public static final String SRD_HomePage = ExcelLibrary.getExcelData("GA_CPT",34, 2);
	public static final String SRD_Page= 		ExcelLibrary.getExcelData("GA_CPT", 35, 2);
	public static final String SRD_HubPage= 			ExcelLibrary.getExcelData("GA_CPT", 36, 2);
	public static final String SRD_ArticlePage= 		ExcelLibrary.getExcelData("GA_CPT", 37, 2);
	public static final String SRD_ListiclePage= 		ExcelLibrary.getExcelData("GA_CPT", 38, 2);
	public static final String SRD_AuthorHub= 		ExcelLibrary.getExcelData("GA_CPT", 39, 2);
	public static final String SRD_VideoHubPage= 	ExcelLibrary.getExcelData("GA_CPT", 41, 2);
	public static final String SRD_RecipeDetailPage = ExcelLibrary.getExcelData("GA_CPT",40, 2);
	//Newspaper SignUp Link
	public static final String SRD_NewspaperSignUpLink= ExcelLibrary.getExcelData("GA_CPT", 35, 3);


	//RDC 
	public static final String RDC_HomePage = ExcelLibrary.getExcelData("GA_CPT", 42, 2);
	public static final String RDC_NewsletterPage= 		ExcelLibrary.getExcelData("GA_CPT", 43, 2);
	public static final String RDC_HubPage= 			ExcelLibrary.getExcelData("GA_CPT", 44, 2);
	public static final String RDC_ArticlePage= 		ExcelLibrary.getExcelData("GA_CPT", 45, 2);
	public static final String RDC_ListiclePage= 		ExcelLibrary.getExcelData("GA_CPT", 46, 2);
	public static final String RDC_SlideshowPage= 		ExcelLibrary.getExcelData("GA_CPT", 47, 2);
	public static final String RDC_VideoHubPage= 	ExcelLibrary.getExcelData("GA_CPT", 48, 2);
	public static final String RDC_RecipeDetailPage = ExcelLibrary.getExcelData("GA_CPT", 49, 2);
	//Newspaper SignUp Link
	public static final String RDC_NewspaperSignUpLink= ExcelLibrary.getExcelData("GA_CPT", 42, 3);

	//BHC 
	public static final String BHC_HomePage = ExcelLibrary.getExcelData("GA_CPT", 50, 2);
	public static final String BHC_NewsletterPage= 		ExcelLibrary.getExcelData("GA_CPT", 51, 2);
	public static final String BHC_HubPage= 			ExcelLibrary.getExcelData("GA_CPT", 52, 2);
	public static final String BHC_ArticlePage= 		ExcelLibrary.getExcelData("GA_CPT", 53, 2);
	public static final String BHC_ListiclePage= 		ExcelLibrary.getExcelData("GA_CPT", 54, 2);
	public static final String BHC_SlideshowPage= 		ExcelLibrary.getExcelData("GA_CPT", 55, 2);
	public static final String BHC_VideoHubPage= 	ExcelLibrary.getExcelData("GA_CPT", 56, 2);
	public static final String BHC_RecipeDetailPage = ExcelLibrary.getExcelData("GA_CPT", 57, 2);
	//Newspaper SignUp Link
	public static final String BHC_NewspaperSignUpLink= ExcelLibrary.getExcelData("GA_CPT", 50, 3);

	//TOH
	public static final String TOH_RecipeDetailPage = ExcelLibrary.getExcelData("GA_CPT", 66, 2);
	public static final String TOH_HomePage = 	 ExcelLibrary.getExcelData("GA_CPT", 67, 2);
	public static final String TOH_HubPage= 		  ExcelLibrary.getExcelData("GA_CPT", 68, 2);
	public static final String TOH_ArticlePage= 	  ExcelLibrary.getExcelData("GA_CPT", 69, 2);
	public static final String TOH_ListiclePage= 	  ExcelLibrary.getExcelData("GA_CPT", 70, 2);
	public static final String TOH_VideoHubPage= 	  ExcelLibrary.getExcelData("GA_CPT", 71, 2);
	public static final String TOH_SubcatHubPage= 	  ExcelLibrary.getExcelData("GA_CPT", 72, 2);
	//Newspaper SignUp Link
	public static final String TOH_NewspaperSignUpLink= ExcelLibrary.getExcelData("GA_CPT", 66, 3);

	//BNB 
	public static final String BNB_HomePage = 			ExcelLibrary.getExcelData("GA_CPT", 58, 2);
	public static final String BNB_AuthorHub= 			ExcelLibrary.getExcelData("GA_CPT", 59, 2);
	public static final String BNB_HubPage= 			ExcelLibrary.getExcelData("GA_CPT", 60, 2);
	public static final String BNB_ArticlePage= 		ExcelLibrary.getExcelData("GA_CPT", 61, 2);
	public static final String BNB_BackyardProjects= 	ExcelLibrary.getExcelData("GA_CPT", 62, 2);
	public static final String BNB_BirdSpecies= 		ExcelLibrary.getExcelData("GA_CPT", 63, 2);
	public static final String BNB_BlogPosts= 		    ExcelLibrary.getExcelData("GA_CPT", 64, 2);
	public static final String BNB_Top10s =         	ExcelLibrary.getExcelData("GA_CPT", 65, 2);
	public static final String BNB_ListiclePage =       ExcelLibrary.getExcelData("GA_CPT", 73, 2);
	
	
	//Newspaper SignUp Link
	public static final String BNB_NewspaperSignUpLink= ExcelLibrary.getExcelData("GA_CPT", 58, 3);
	public static final String BNBFooter_NLSignUpLink= ExcelLibrary.getExcelData("GA_CPT", 59, 3);

	public static final String ANSI_RESET = "\033[0m";
	public static final String ANSI_RED = "\033[0;31m";
	public static final String ANSI_GREEN = "\033[0;32m";
	
	



	//common xpath for Hub Page
	//banner 
	public static By banner = By.xpath("//a[@class='pure-u-sm-1 pure-u-md-2-5 logo']//img");
	//title
	public static By titleLocator = By.xpath("//h1[@class='page-title']");
	//breadcrumb
	public static By breadcrumbLocator = By.xpath("//p[@id='breadcrumbs']//a");
	//Archive description
	public static By descriptionLocator = By.xpath("//div[@class='archive-description']");
	//Hero container
	public static By heroImage = By.xpath("//a[@class='pure-u-sm-2-5 hero-image']//div[@class='image-tout-container']"); //html/body/main/section[3]/div[1]/a/div
	public static By heroTitle = By.xpath("//h3[@class='entry-title']");    //h3[@class='entry-title'] /html/body/main/section[3]/div[1]/div/div/h3

	// Article Page xpath

	//Commonvideo on posts
	public static By commonvideo = By.xpath("//div[@class='jw-media jw-reset']");
	public static By closeBtn = By.xpath("//div[@class='jw-float-icon jw-icon jw-button-color jw-reset']");
	//title
	public static By articleTitleLocator = By.xpath("//h1[@class='post-title']");
	//by line
	public static By avatarLocator = By.xpath("//img[@class='avatar lazyloaded']");    //div[contains(@class, 'byline')]/img");
	public static By authorLocator = By.xpath("//span[@class='post-author']//a");    //div[contains(@class, 'byline')]/div/span[contains(@class, 'author-name')]");
	//dek
	public static By dekLocator = By.className("dek");
	//social share
	public static By menuListLocator = By.xpath("//section[@class='social-menu-desktop pure-u-lg-2-24']//ul[@class='pure-menu-list social-menu']");
	public static By facebookIconLocator = By.xpath("//div[@class='social-share social-menu-desktop']/ul/li/a[@id='fb-share']/img");
	public static By fb = By.xpath("//section[@class='social-menu-desktop pure-u-lg-2-24']//a[@id='fb-share']");
	public static By facebookLocator =   By.xpath("//*[contains(@class, 'social-menu-desktop')]/ul/li/a[@id='fb-share']/img");
	public static By fbLocator = By.id("fb-share");
	public static By twitterIconLocator = By.id("twitter-share");
	public static By pinterestIconLocator = By.id("pinterest-share");
	//taboola sidebar
	public static By taboolaSidebarLocator = By.className("taboola-sidebar");
	//Newsletter sign up container
	public static By newsletterSignupInputField= By.xpath("//div[@class='in-content-nl-container']/div/form/input");
	public static By newsletterSignupButtonLocator= By.xpath("//div[@class='in-content-nl-container']/div/form/button");

	public static By sidebarClass = By.xpath("//div[@id='right-sidebar-container']");

	//listicle xpaths
	//title
	public static By listicleTitleLocator = By.xpath("//h1[contains(@class, 'entry-title')]");
	//updated date
	public static By updatedDateLocator = By.className("updated-date");
	//card
	public static By cardImageLocator = By.xpath("//div[contains(@class, 'listicle-card')]/div/img");
	public static By cardPageCountLocator = By.xpath("//div[contains(@class, 'listicle-card')]//span[contains(@class, 'total-page-count')]");
	public static By cardImageCreditLocator = By.xpath("//div[contains(@class, 'listicle-card')]/div[contains(@class, 'image-wrapper')]/span/span[contains(@class, 'image-credit')]");
	public static By cardTitleLocator = By.xpath("//div[contains(@class, 'listicle-card')]/h2");
	public static By cardCaptionLocator = By.xpath("//div[contains(@class, 'listicle-card')]/p");
	//post date
	public static By postDateLocator = By.xpath("//div[contains(@class, 'post-date')]");
	//originally published
	public static By originallyPublishedBrandLocator = By.xpath("//div[@id='brand-container']/div[@id='brand-img']");
	public static By originallyPublishedUrlLocator = By.xpath("//div[@id='brand-container']/div[@id='brand-url']");
	//taboola container
	public static By taboolaContainerLocator = By.xpath("//div[@class=' trc_related_container trc_spotlight_widget tbl-feed-container tbl-feed-frame-DIVIDER  render-late-effect']");
	//page count -- not the same as cardPageCountLocator above
	public static By cardCountLocator = By.className("total-page-count");
	public static By cardLocator = By.className("card-number");


	/*// BHC header comparison data
	public static final String[] BHC_Headers = {"BEST LOOKS", "BEST YOU", "BEST EATS", "GIFT GUIDE", "CONTESTS", "SUBSCRIBE" };
	
	public static final String[] BNB_Headers = {"BIRDING", "GARDENING", "BACKYARD PROJECTS", "SUBMIT A STORY", "SUBSCRIBE", "", "", "SHOP" };
	
	public static final String[] FHM_Headers = {"PRO", "70TH ANNIVERSARY", "HOMELAB", "PROJECTS", "DIY UNIVERSITY", "WIN A GRILL", "SUBSCRIBE", "", "", "" };
	
	public static final String[] HLT_Headers = {"EXERCISE", "NUTRITION", "MENTAL HEALTH", "SELF-CARE", "BOOK OFFER" };
	
	public static final String[] RD_Headers = {"THE HEALTHY", "FOOD", "HOME", "HUMOR", "KNOWLEDGE", "HOLIDAYS", "VIDEOS", "SUBSCRIBE", "", "", "", "", "", "" };
	
	public static final String[] RDC_Headers = {"GIFT GUIDE", "FINANCIAL LITERACY", "JOKES", "OUR CANADA", "GAMES", "VIDEO", "SUBSCRIBE" };
	
	public static final String[] SRD_Headers = {"CORONAVIRUS", "SANTÉ", "CUISINE", "MAISON", "VOYAGE", "AUTO", "VIDÉOS", "ABONNEZ-VOUS" };
	
	public static final String[] TOH_Headers = {"RECIPES", "DINNER", "BAKING", "SHOP", "VIDEOS", "SUBSCRIBE", "", "", "", "", "" };
	*/
}
