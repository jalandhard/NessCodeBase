package com.GA.listicleUseCases;


import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.wp.genericLib.ConfigFileReader;
import com.wp.genericLib.Driver;
import com.wp.pageObjectRepo.GlobalFunctions;

public class ListicleUseCaseTests extends Driver {
	//Initialize object using page factory method 
	ConfigFileReader configFileReader;
	public Logger APPLICATION_LOGS = LogManager.getLogger("AppLogger");


	@BeforeClass
	public void navigateToRequiredURL() throws Exception{
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Step:1 Navigate required URL
		//globalPage.navigateToRequiredSite(url);
	}

	@BeforeMethod
	public void nameBefore(Method method)
	{
		System.out.println("********************************************************************************************");
		System.out.println("Test : " + method.getName() + " execution started ................");       
	}

	@Test(dataProvider = "image-free-form-urls")
	public void imageFreeFormTest(String url) throws IOException, InterruptedException {
		driver.get(url);
		assertFalse(is404Page(), "URL " + url + " is a 404 page");
		
		WebElement cardContent = driver.findElement(By.className("card-content"));
		
		assertTrue(elementExists(cardContent, By.tagName("p")), "Card is missing <p> on URL " + url);
		assertTrue(elementExists(cardContent, By.tagName("h4")) || elementExists(cardContent, By.tagName("h2")), "Card is missing <h> on URL " + url);
	}
	
	
	@Test(dataProvider = "jw-video-free-form-urls")
	public void JWVideoFreeFormTest(String url) throws IOException, InterruptedException {
		driver.get(url);
		assertFalse(is404Page(), "URL " + url + " is a 404 page");
		
		//taken from above since the test is identical
		WebElement cardContent = driver.findElement(By.className("card-content"));
		
		assertTrue(elementExists(cardContent, By.tagName("p")), "Card is missing <p> on URL " + url);
		assertTrue(elementExists(cardContent, By.tagName("h4")) || elementExists(cardContent, By.tagName("h2")), "Card is missing <h> on URL " + url);

	}
	
	@Test(dataProvider = "jw-and-image-free-form-urls")
	public void imageAndJwFreeFormTest(String url) throws IOException, InterruptedException {
		driver.get(url);
		assertFalse(is404Page(), "URL " + url + " is a 404 page");

	}
	
	@Test(dataProvider = "youtube-and-image-free-form-urls")
	public void imageAndYoutubeFreeFormTest(String url) throws IOException, InterruptedException {
		driver.get(url);
		assertFalse(is404Page(), "URL " + url + " is a 404 page");

	}

	@Test(dataProvider = "shop-now-urls")
	public void shopNowButtonTest(String url) throws IOException, InterruptedException {
		driver.get(url);
		assertFalse(is404Page(), "URL " + url + " is a 404 page");
		
		assertTrue(elementExists(By.linkText("Shop Now")), "Shop now link does not exist on URL " + url);
	}
	
	@Test(dataProvider = "card-style-urls")
	public void cardStyleTest(String url) throws IOException, InterruptedException {
		driver.get(url);
		assertFalse(is404Page(), "URL " + url + " is a 404 page");
		
		WebElement content = null;
		try {
			content = driver.findElement(By.className("card-content"));
		}
		catch(NoSuchElementException nsee) {
			Assert.fail("Unable to locate card content on URL " + url);
		}
		assertTrue(elementExists(content, By.tagName("ul")) || elementExists(content, By.tagName("ol")), "Card is missing <ul>/<ol> on URL " + url);
		assertTrue(elementExists(content, By.tagName("h4")) || elementExists(content, By.tagName("h2")), "Card is missing <h> on URL " + url);
	}
	
	@Test(dataProvider = "under-sized-urls")
	public void undersizedImageTest(String url) throws IOException, InterruptedException {
		driver.get(url);
		assertFalse(is404Page(), "URL " + url + " is a 404 page");
		
		By blurredOverlay = By.className("blurred-overlay");
		By blurredBackground = By.className("blurred-background");
		assertTrue(elementExists(blurredOverlay) || elementExists(blurredBackground), "Blurred image does not exist on URL " + url);

	}
	
	@Test(dataProvider = "social-embeds-urls")
	public void socialEmbedsTest(String url) throws IOException, InterruptedException {
		driver.get(url);
		assertFalse(is404Page(), "URL " + url + " is a 404 page");

		By twitterEmbed = By.className("twitter-tweet");
		By instagramEmbed = By.className("instagram-media");
		
		assertTrue(elementExists(twitterEmbed) || elementExists(instagramEmbed), "No social embed on URL " + url);
	}
	
	@Test(dataProvider = "jw-player-no-card-text-urls")
	public void jwPlayerNoCardText(String url) throws IOException, InterruptedException {
		driver.get(url);
		assertFalse(is404Page(), "URL " + url + " is a 404 page");
		
		//check for no <p> tag?
	}
	
	@Test(dataProvider = "toh-recipe-short-code-urls")
	public void tohRecipeShortCodeTest(String url) throws IOException, InterruptedException {
		driver.get(url);
		assertFalse(is404Page(), "URL " + url + " is a 404 page");
		
		WebElement content = driver.findElement(By.className("card-content"));
		
		assertTrue(elementExists(content, By.tagName("a")), "Card does not have a recipe <a> on URL " + url);
	}

	@Test(dataProvider = "toh-recipe-short-code-with-cta-urls")
	public void tohRecipeShortCodeWithCTATest(String url) throws IOException, InterruptedException {
		driver.get(url);
		assertFalse(is404Page(), "URL " + url + " is a 404 page");

		assertTrue(elementExists(By.linkText("Get Recipe")), "There is no Get Recipe CTA on URL " + url);
	}
	
	@Test(dataProvider = "toh-recipe-short-code-with-modified-title-urls")
	public void tohRecipeShortCodeWithModifiedTitleTest(String url) throws IOException, InterruptedException {
		driver.get(url);
		assertFalse(is404Page(), "URL " + url + " is a 404 page");
		
		

	}
	
	@Test(dataProvider = "recipe-short-code-free-form-urls")
	public void recipeShortCodeFreeFormTest(String url) throws IOException, InterruptedException {
		driver.get(url);
		assertFalse(is404Page(), "URL " + url + " is a 404 page");

	}
	
	@Test(dataProvider = "fhm-tip-short-code-urls")
	public void fhmTipShortCode(String url) throws IOException, InterruptedException {
		driver.get(url);
		assertFalse(is404Page(), "URL " + url + " is a 404 page");

	}

	@AfterMethod
	public void nameAfter(Method method)
	{
		System.out.println("Test : " + method.getName() + " execution completed ...............\n");       
		System.out.println("********************************************************************************************");
	}
	
	//Data Providers

	@DataProvider(name = "image-free-form-urls")  
	public Object[][] imageFreeForm(){
		List<String> urls = new LinkedList<String>();
		
		urls.add("https://rf-staging.tasteofhome.com/collection/supermarket-tricks-you-still-fall-for/");
		urls.add("https://staging.familyhandyman.com/diy-advice/10-awesome-diy-cabin-projects/");
		urls.add("https://staging.rd.com/culture/strangest-things-found-by-deep-sea-divers/");
		urls.add("https://staging.rd.com/advice/parenting/homeschooling-cartoons/");
		urls.add("https://staging.thehealthy.com/home/6-ordinary-products-that-could-affect-your-health/");
		urls.add("https://staging.readersdigest.ca/health/conditions/7-ways-get-rid-hiccups/");
		urls.add("https://staging.besthealthmag.ca/best-you/health/risk-of-sunburn/");
		urls.add("https://staging.selection.ca/sante/famille/11-conseils-aux-aidants-naturels-pour-eviter-lepuisement/");
		
		return to2DArray(urls); 
	}
	
	@DataProvider(name = "jw-video-free-form-urls")  
	public Object[][] jwFreeForm(){
		List<String> urls = new LinkedList<String>();
		
		urls.add("https://rf-staging.tasteofhome.com/collection/recipes-to-teach-your-kids-right-now/");
		urls.add("https://staging.familyhandyman.com/garden/hardy-prairie-plants-to-try/");
		urls.add("https://staging.thehealthy.com/exercise/online-fitness-classes/");
		urls.add("https://staging.readersdigest.ca/health/healthy-living/germ-spreading-habits/");
		urls.add("https://staging.besthealthmag.ca/best-eats/healthy-eating/meat-free-ways-to-increase-your-protein-intake/");
		urls.add("https://staging.selection.ca/sante/vivre-sainement/3-masques-de-protection-a-faire-sans-couture/");
		
		return to2DArray(urls); 
	}
	
	@DataProvider(name = "jw-and-image-free-form-urls")  
	public Object[][] jwAndImageFreeForm(){
		List<String> urls = new LinkedList<String>();
		
		urls.add("https://staging.familyhandyman.com/garden/10-balcony-gardening-tips/");
		urls.add("https://staging.rd.com/advice/travel/things-you-wont-see-in-airports-anymore/");
		urls.add("https://staging.thehealthy.com/weight-loss/weight-loss-tricks/");
		
		return to2DArray(urls); 
	}
	
	@DataProvider(name = "youtube-and-image-free-form-urls")  
	public Object[][] youtubeAndImageFreeForm(){
		List<String> urls = new LinkedList<String>();
		
		urls.add("https://staging.readersdigest.ca/culture/feel-good-movies-on-netflix-canada/");
		urls.add("https://staging.besthealthmag.ca/best-you/besthealth-challenge/13-secrets-to-a-better-workout/");
		urls.add("https://staging.selection.ca/sante/famille/ados-confines-reapprendre-a-vivre-ensemble/");
		
		return to2DArray(urls); 
	}
	
	@DataProvider(name = "shop-now-urls")  
	public Object[][] shopNow(){
		List<String> urls = new LinkedList<String>();
		
		//404
		//urls.add("https://rf-staging.tasteofhome.com/collection/9-best-stainless-steel-water-bottles-for-staying-hydrated/");
		
		urls.add("https://staging.familyhandyman.com/diy-advice/10-boredom-busting-ideas-your-kids-will-love-this-winter/");
		urls.add("https://staging.rd.com/advice/pets/best-dog-nail-clippers/");
		urls.add("https://staging.thehealthy.com/mental-health/best-bullet-journals-amazon/");
		
		return to2DArray(urls); 
	}
	
	@DataProvider(name = "card-style-urls")  
	public Object[][] cardStyle(){
		List<String> urls = new LinkedList<String>();
		
		urls.add("https://rf-staging.tasteofhome.com/collection/st-patricks-day-drinks/");
		urls.add("https://rf-staging.tasteofhome.com/collection/quick-thanksgiving-dinner-menu/");
		urls.add("https://rf-staging.tasteofhome.com/collection/best-new-snacks-2019/");
		urls.add("https://staging.familyhandyman.com/stuff-we-love/best-home-tech-deals-for-black-friday/");
		urls.add("https://staging.rd.com/culture/secret-recipes-released-for-the-first-time/");
		urls.add("https://staging.thehealthy.com/foot-care/walking-shoes-sneakers-podiatrists-recommend/");
		urls.add("https://staging.thehealthy.com/food/recipes/healthy-breakfast-recipes/");
		
		return to2DArray(urls); 
	}
	
	@DataProvider(name = "under-sized-urls")  
	public Object[][] undersizedImage(){
		List<String> urls = new LinkedList<String>();
		
		//urls.add("https://staging.rd.com/funny-stuff/funny-foreign-signs-bad-translations/");
		urls.add("https://staging.thehealthy.com/digestive-health/weird-digestive-system-facts/");
		urls.add("https://staging.readersdigest.ca/health/conditions/25-tricks-healthy-brain/");
		urls.add("https://staging.besthealthmag.ca/best-you/ask-your-pharmacist/5-risk-factors-for-iron-deficiency/");
		urls.add("https://staging.selection.ca/sante/vivre-sainement/8-astuces-amusantes-pour-garder-la-forme/");
		
		return to2DArray(urls); 
	}
	
	@DataProvider(name = "social-embeds-urls")  
	public Object[][] socialEmbeds(){
		List<String> urls = new LinkedList<String>();
		
		urls.add("https://staging.readersdigest.ca/culture/canadians-to-follow-on-social-media/");
		urls.add("https://staging.besthealthmag.ca/best-you/wellness/canadian-podcasts/");
		urls.add("https://staging.selection.ca/reportages/insolite/8-challenges-sur-instagram-pour-soccuper-a-la-maison/");
		
		return to2DArray(urls); 
	}
	
	@DataProvider(name = "jw-player-no-card-text-urls")  
	public Object[][] jwPlayerNoCardText(){
		List<String> urls = new LinkedList<String>();
		
		urls.add("https://staging.familyhandyman.com/diy-advice/10-awesome-hammer-hacks/");
		
		return to2DArray(urls); 
	}
	
	@DataProvider(name = "toh-recipe-short-code-urls")  
	public Object[][] tohRecipeShortCode(){
		List<String> urls = new LinkedList<String>();
		
		urls.add("https://rf-staging.tasteofhome.com/collection/working-from-home-lunches/");
		
		return to2DArray(urls); 
	}
	
	@DataProvider(name = "toh-recipe-short-code-with-cta-urls")  
	public Object[][] tohRecipeShortCodeWithCTALink(){
		List<String> urls = new LinkedList<String>();
		
		urls.add("https://rf-staging.tasteofhome.com/collection/your-january-meal-plan/");
		
		return to2DArray(urls); 
	}
	
	@DataProvider(name = "toh-recipe-short-code-with-modified-title-urls")  
	public Object[][] tohRecipeShortCodeWithModifiedTitle(){
		List<String> urls = new LinkedList<String>();
		
		urls.add("https://www.tasteofhome.com/collection/jewish-foods-everyone-should-learn-to-cook/");
		
		return to2DArray(urls); 
	}
	
	@DataProvider(name = "recipe-short-code-free-form-urls")  
	public Object[][] recipeShortCodeFreeForm(){
		List<String> urls = new LinkedList<String>();
		
		urls.add("https://rf-staging.tasteofhome.com/collection/bridal-shower-favor-ideas/");
		
		return to2DArray(urls); 
	}
	
	@DataProvider(name = "fhm-tip-short-code-urls")
	public Object[][] fhmTipShortCode(){
		List<String> urls = new LinkedList<String>();
		
		urls.add("https://staging.familyhandyman.com/smart-homeowner/50-extraordinary-uses-for-ordinary-things/");
		
		return to2DArray(urls); 
	}
	
	@DataProvider(name = "card-style-ca-urls")
	public Object[][] cardStyleCA(){
		List<String> urls = new LinkedList<String>();
		
		urls.add("https://staging.readersdigest.ca/travel/world/things-to-do-in-peru/");
		urls.add("https://staging.readersdigest.ca/cars/maintenance/how-to-replace-a-starter/");
		urls.add("https://staging.besthealthmag.ca/best-you/covid-19/coronavirus-quarantine-essentials/");
		urls.add("https://staging.besthealthmag.ca/best-looks/how-to/how-to-trim-your-hair/");
		urls.add("https://staging.selection.ca/sante/vivre-sainement/covid-19-faire-soi-meme-son-masque-de-protection/");
		
		return to2DArray(urls); 
	}
	
	//End Data Providers
	
	/**
	 * A helper method for data providers. Takes a list as an argument and returns a 2D Object array.
	 * @param list the list to be converted
	 * @return the 2D array of list
	 */
	private <T> Object[][] to2DArray(List<T> list) {
		
		Object[][] result = new Object[list.size()][1];
		
		for(int i = 0; i < list.size(); i++) {
			result[i][0] = list.get(i);
		}
		
		return result;
	}
	
	private boolean elementExists(By locator) {
		return !driver.findElements(locator).isEmpty();
	}
	
	private boolean elementExists(WebElement parent, By locator) {
		return !parent.findElements(locator).isEmpty();
	}
	
	/**
	 * Determines if the URL the WebDriver is currently on is a 404 page on a TMBI site. Only works for TMBI sites.
	 * @return true if the page is a 404, false otherwise
	 */
	private boolean is404Page() {
		final String ERROR_404_TEXT = "Error 404: Page not found";
		final String PAGE_TYPE_404_TEXT = "404Page";

		String pageSource = driver.getPageSource();

		return pageSource.contains(ERROR_404_TEXT) || pageSource.contains(PAGE_TYPE_404_TEXT);
	}

}

