package com.wp.pageObjectRepo;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
//import java.util.s;
import java.util.List;
import java.util.Set;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import org.testng.Reporter;

//import org.testng.Assert;
//import org.testng.asserts.SoftAssert;
import com.wp.genericLib.ConfigFileReader;
import com.wp.genericLib.Constant;
import com.wp.genericLib.Link;
import com.wp.genericLib.WebDriverCommonLib;

public class GlobalFunctions {
	WebDriver driver;
	public WebDriverWait wait;
	// Initialize object using page factory method
	ConfigFileReader configFileReader;
//	public Logger APPLICATION_LOGS = Logger.getLogger("AppLogger");
	public Logger APPLICATION_LOGS = LogManager.getLogger("AppLogger");

	public GlobalFunctions(WebDriver driver) {
		this.driver = driver;
	}

	// Common methods for Global Automation

	public void navigateToRequiredSite(String url) {
		// configFileReader= new ConfigFileReader();
		// String url= configFileReader.getDriverPath();
		System.out.println("url: " + url);
		APPLICATION_LOGS.debug("url: " + url);
		driver.get(url);
		WebDriverCommonLib.waitForPageToBeLoad(driver);

	}

	// this method will look for webelement
	public void scrollToElement(By elementBy) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try {
			WebElement element = driver.findElement(elementBy);
			js.executeScript("arguments[0].scrollIntoView();", element);
		} catch (ElementNotVisibleException e) {
			System.out.println("Element not present on webpage");
		}

	}

	// check for bounce exchange popup
	public void closeBounceExchange() throws InterruptedException {
		Thread.sleep(1000);
		By NoBtn = By.xpath("//button[@type='reset']");
		By bounceExchangeClose = By.xpath(
				"//div[@class='bx-creative bx-creative-1010478']//a[@class='bx-close bx-close-link bx-close-inside']");
		// By.xpath("//div[@id='bx-campaign-1010478']//div[@class='bx-wrap']//a");


		try{
		if(driver.findElement(NoBtn).isDisplayed())
		{

			driver.findElement(NoBtn).click();
			System.out.println("Bounce Exchnage link clicked");
		} else {
			Thread.sleep(1000);
			driver.findElement(bounceExchangeClose).click();
			System.out.println("BX popup closed");
		}
		}
		catch(NoSuchElementException e)
		{
			System.out.println("BX popup not found..");
		}
	}

	public void closeBxPopup() throws InterruptedException {
		Thread.sleep(3000);
		By bounceExchangeClose = By.xpath("//a[@id='bx-close-inside-1048800']//*[local-name()='svg']");
		// By.xpath("//div[@id='bx-campaign-1010478']//div[@class='bx-wrap']//a");

		Thread.sleep(2000);
		driver.findElement(bounceExchangeClose).click();
		System.out.println("BX popup closed");
	}

	/**
	 * Attempts to click an element, located by the passed By. Attempts to close
	 * bounce exchange if click is intercepted. Ignores StaleElementExceptions.
	 * 
	 * @param locator
	 *            the By to locate the desired element to be clicked
	 * @return true if the click was successful, false otherwise
	 * @throws InterruptedException
	 */
	public boolean click(By locator) throws InterruptedException {
		return click(driver.findElement(locator));
	}

	/**
	 * Attempts to click the passed WebElement. Attempts to close bounce
	 * exchange if click is intercepted. Ignores StaleElementExceptions.
	 * 
	 * @param element
	 *            the element to be clicked
	 * @return true if the click was successful, false otherwise
	 * @throws InterruptedException
	 */
	public boolean click(WebElement element) throws InterruptedException {
		final int MAX_ATTEMPTS = 3;

		boolean result = false;
		int attempts = 0;
		while (attempts < MAX_ATTEMPTS) {
			try {
				element.click();
				result = true;
				break;
			} catch (ElementClickInterceptedException e) {
				closeBounceExchange();
			} catch (StaleElementReferenceException expected) {
				// ignore stale element exceptions
			}
			attempts++;
		}
		return result;
	}

	// this function will check if any post link or post image or post excerpt
	// is clickable
	public void verifyPostsImages_Clickable(By locator, String url) throws IOException {
		SoftAssert softAssert = new SoftAssert();
		List<WebElement> links = driver.findElements(locator);
		int link = links.size();

		// System.out.println("Total no of links in BrowseBY tag are: " +
		// links);
		if (link != 0) {
			try {
				closeBounceExchange();
			} catch (Exception e) {
				System.out.println("Bounce exchange popup not visible");
			}
			for (int i = 0; i < link; i++) {

				// System.out.println(i);
				List<WebElement> lists = driver.findElements(locator);
				WebElement webe = lists.get(i);
				Actions action = new Actions(driver);
				action.moveToElement(webe);
				String linkText = webe.getText();
				// webe.isDisplayed();
				WebDriverWait wait = new WebDriverWait(driver, 10);
				try {
					wait.until(ExpectedConditions.elementToBeClickable(webe)); 
					APPLICATION_LOGS.debug(linkText + " link " + (i + 1) + " is clickable on site :" + url);
					System.out.println(linkText + " link " + (i + 1) + " is clickable on site :" + url);
					System.out.println("Test case : PASSED !!");
				} catch (TimeoutException te) {
					APPLICATION_LOGS.debug(linkText + "link " + (i + 1) + " is not clickable on site :" + url);
					System.out.println(linkText + " link  " + (i + 1) + " is not clickable on site :" + url);
					WebDriverCommonLib.getScreenShot(driver, "Error in isElementClickable");
					softAssert.assertTrue(webe.isDisplayed(),
							linkText + " link " + (i + 1) + " is not clickable on site :" + url);

					System.out.println("Test case : FAILED !!");
				}
			}
			softAssert.assertAll();
		} else {
			assertFalse((link == 0), "Test case : FAILED, No post available ");
		}
	}

	/*
	 * public void verifyPostsImages_Clickable_TOH(By locator, String url)
	 * throws IOException { List<WebElement> links =
	 * driver.findElements(locator); int link = links.size(); for (int i = 0; i
	 * < link; i++) { //System.out.println(i); List<WebElement> lists =
	 * driver.findElements(locator); WebElement webe = lists.get(i); String
	 * linkText= webe.getText(); //webe.isDisplayed(); WebDriverWait wait = new
	 * WebDriverWait(driver, 5); try {
	 * wait.until(ExpectedConditions.elementToBeClickable(webe));
	 * APPLICATION_LOGS.debug(linkText + " link image or excerpt "+(i+1)+
	 * " is clickable on site :"+url); System.out.println(linkText +
	 * "link image or excerpt "+(i+1)+ " is clickable on site :"+url);
	 * System.out.println("Test case : PASSED !!"); } catch(TimeoutException te)
	 * { APPLICATION_LOGS.debug(linkText + "link image or excerpt "+(i+1)+
	 * " is not clickable on site :"+url); System.out.println(linkText +
	 * "link image or excerpt "+(i+1)+ " is not clickable on site :"+url);
	 * WebDriverCommonLib.getScreenShot(driver, "Error in isElementClickable");
	 * assertTrue(webe.isDisplayed(),linkText + "link image or excerpt "+(i+1)+
	 * " is not clickable on site :"+url);
	 * 
	 * System.out.println("Test case : FAILED !!"); } } }
	 */

	public void verifyPostsImages_Clickable_BNB(By locator, String url) throws IOException {
		SoftAssert softAssert = new SoftAssert();
		List<WebElement> links = driver.findElements(locator);
		int link = links.size();
		for (int i = 0; i < link; i++) {
			// System.out.println(i);
			List<WebElement> lists = driver.findElements(locator);
			WebElement webe = lists.get(i);
			/*
			 * Actions action = new Actions(driver); action.moveToElement(webe);
			 */
			String linkText = webe.getText();
			// webe.isDisplayed();
			WebDriverWait wait = new WebDriverWait(driver, 5);
			try {
				wait.until(ExpectedConditions.elementToBeClickable(webe));
				if (webe.isDisplayed() && webe.isEnabled()) {
					APPLICATION_LOGS
							.debug(linkText + " link image or excerpt " + (i + 1) + " is clickable on site :" + url);
					System.out.println(linkText + "link image or excerpt " + (i + 1) + " is clickable on site :" + url);
					System.out.println("Test case : PASSED !!");
				}
			} catch (TimeoutException te) {
				APPLICATION_LOGS
						.debug(linkText + "link image or excerpt " + (i + 1) + " is not clickable on site :" + url);
				System.out.println(linkText + "link image or excerpt " + (i + 1) + " is not clickable on site :" + url);
				WebDriverCommonLib.getScreenShot(driver, "Error in isElementClickable");
				softAssert.assertTrue(webe.isDisplayed(),
						linkText + "link image or excerpt " + (i + 1) + " is not clickable on site :" + url);

				System.out.println("Test case : FAILED !!");
			}
		}
		softAssert.assertAll();
	}

	public void jwVideoClose() throws InterruptedException {
		Thread.sleep(10000);
		// By commonvideo = By.xpath("//div[@class='jw-media jw-reset']");
		By closeBtn = By.xpath("//div[@class='jw-media jw-reset']");
		if (driver.findElement(closeBtn).isDisplayed()) {
			driver.findElement(closeBtn).click();
			System.out.println("Jw reset video closed");
		}
	}

	// this method is used to check for pages banners
	public void verifyIsBannerAvailable(By locator, String url) throws IOException, InterruptedException {

		WebElement banner = driver.findElement(locator);
		String linkText = banner.getText();
		String desiredURL = banner.getAttribute("href");
		System.out.println("Expected Banner URL is : " + desiredURL);
		URL desiredurl = new URL(desiredURL);

		HttpURLConnection.setFollowRedirects(false);
		HttpURLConnection httpURLConnection = (HttpURLConnection) desiredurl.openConnection();

		// We don't need to get data
		httpURLConnection.setRequestMethod("HEAD");

		// Some websites don't like programmatic access so pretend to be a
		// browser
		httpURLConnection.setRequestProperty("User-Agent",
				"Mozilla/5.0 (Windows; U; Windows NT 6.0; en-US; rv:1.9.1.2) Gecko/20090729 Firefox/3.5.2 (.NET CLR 3.5.30729)");
		int responseCode = httpURLConnection.getResponseCode();

		// We only accept response code 200
		System.out.println("Getting Response Code : " + responseCode + " in " + desiredURL);
		if (driver.getCurrentUrl().equals(url) && (responseCode == HttpURLConnection.HTTP_OK
				|| responseCode == HttpURLConnection.HTTP_MOVED_PERM || responseCode == 301)) {
			APPLICATION_LOGS.debug("Banner is available on site :" + url);
			System.out.println("Banner is available on site :" + url);
			System.out.println("Test case : PASSED !!");

		} else {
			APPLICATION_LOGS.debug("Banner is not available on site :" + url);
			System.out.println("Banner is not available on site :" + url);
			WebDriverCommonLib.getScreenShot(driver, "Error in verifyIsBannerAvailable");
			assertTrue(driver.getCurrentUrl().equals(url), "Banner is not available on site :" + url);
			System.out.println("Test case : FAILED !!");
		}
	}

	public void verifyIsBannerAvailable_copy(By locator, String url) throws IOException, InterruptedException {

		WebElement banner = driver.findElement(locator);
		// WebDriverCommonLib.waitForElementToBeClickable(driver, banner);
		Thread.sleep(2000);
		try {
			// WebDriverCommonLib.waitForElementToBeClickable(driver, banner);
			click(banner);
		} catch (ElementNotVisibleException e) {
			System.out.println("Element not found");
			assertTrue(banner.isDisplayed() == true, "Banner is not available on site :" + url);
			System.out.println("Test case : FAILED !!");

		}
		// banner.click(); ,
		WebDriverCommonLib.waitForPageToBeLoad(driver);
		System.out.println("After clicking banner, the desired URL is: " + driver.getCurrentUrl());

		final String ERROR_404_TEXT = "Error 404: Page not found";
		final String PAGE_TYPE_404_TEXT = "404Page";
		final String sourceCode = "Status Code: 503";
		String pageSource = driver.getPageSource();

		boolean flag = pageSource.contains(ERROR_404_TEXT) || pageSource.contains(PAGE_TYPE_404_TEXT)
				|| pageSource.contains(sourceCode);

		driver.navigate().back();
		// Thread.sleep(2000);
		WebDriverCommonLib.waitForPageToBeLoad(driver);
		System.out.println(driver.getCurrentUrl());
		if (driver.getCurrentUrl().equals(url) && flag == false) {
			APPLICATION_LOGS.debug("Banner is available on site :" + url);
			System.out.println("Banner is available on site :" + url);
			System.out.println("Test case : PASSED !!");

		} else {
			APPLICATION_LOGS.debug("Banner is not available on site :" + url);
			System.out.println("Banner is not available on site :" + url);
			WebDriverCommonLib.getScreenShot(driver, "Error in verifyIsBannerAvailable");
			assertTrue(driver.getCurrentUrl().equals(url), "Banner is not available on site :" + url);
			System.out.println("Test case : FAILED !!");
		}
	}

	// this method is used to check if element is clickable
	public void isElementClickable(String url, By locator, String item) throws IOException, InterruptedException {
		WebElement element = driver.findElement(locator);
		if (element.isDisplayed() && element.isEnabled()) {
			click(element);
			// element.click();
			APPLICATION_LOGS.debug(item + " is clickable on site :" + url);
			System.out.println(item + " is clickable on site :" + url);
			System.out.println("Test case : PASSED !!");
		} else {
			APPLICATION_LOGS.debug(item + " is not clickable on site :" + url);
			System.out.println(item + " is not clickable on site :" + url);
			WebDriverCommonLib.getScreenShot(driver, "Error in isElementClickable");
			assertTrue(driver.getCurrentUrl().equals(url), item + " is not clickable on site :" + url);
			System.out.println("Test case : FAILED !!");
		}
	}

	public void verifyIsBreadCrumbAvailable(By locator, String url) throws IOException, InterruptedException {

		List<WebElement> allLinks = driver.findElements(locator);
		int size = allLinks.size();
		// System.out.println("size : " +size);

		WebElement breadcrumb = driver.findElement(locator);
		/*
		 * WebDriverCommonLib.waitForElementToBeClickable(driver, breadcrumb);
		 * Thread.sleep(3000); Actions action = new Actions(driver);
		 * action.moveToElement(breadcrumb);//.click().perform();
		 */String breadcrumText = allLinks.get(size - 1).getText();
		System.out.println("the Expected breadcrumb value is : " + breadcrumText);
		// String linkText = ele.getText();
		String desiredURL = allLinks.get(size - 1).getAttribute("href");
		System.out.println("desired URL is : " + desiredURL);
		URL desiredurl = new URL(desiredURL);

		HttpURLConnection.setFollowRedirects(false);
		HttpURLConnection httpURLConnection = (HttpURLConnection) desiredurl.openConnection();

		// We don't need to get data
		httpURLConnection.setRequestMethod("HEAD");

		// Some websites don't like programmatic access so pretend to be a
		// browser
		httpURLConnection.setRequestProperty("User-Agent",
				"Mozilla/5.0 (Windows; U; Windows NT 6.0; en-US; rv:1.9.1.2) Gecko/20090729 Firefox/3.5.2 (.NET CLR 3.5.30729)");
		int responseCode = httpURLConnection.getResponseCode();

		// We only accept response code 200
		System.out.println("Getting Response Code : " + responseCode + " in " + url);
		if ((responseCode == HttpURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_MOVED_PERM)
				&& (((desiredURL.contains(url)) || (desiredURL.contains(Constant.FHM_HomePage))
						|| (desiredURL.contains(Constant.RD_HomePage)) || (desiredURL.contains(Constant.HLT_HomePage))
						|| (desiredURL.contains(Constant.SRD_HomePage)) || (desiredURL.contains(Constant.RDC_HomePage))
						|| (desiredURL.contains(Constant.BNB_HomePage))
						|| (desiredURL.contains(Constant.BHC_HomePage))))) {
			APPLICATION_LOGS.debug(breadcrumText + " link is working fine on site :" + url
					+ " After clicking link, user navigates to :  " + desiredURL);
			System.out.println(breadcrumText + " breadcrumb link is working successfully on site :" + url);
			System.out.println(" After clicking breadcrumb link, user navigates to :  " + desiredURL);
			System.out.println("Test case : PASSED !!");
		} else {
			APPLICATION_LOGS.debug(breadcrumText + " breadcrumb link is not working successfully on site :" + url
					+ " After clicking link, user navigates to :  " + desiredURL);
			System.out.println(breadcrumText + " breadcrumb link is not working successfully on site :" + url);
			System.out.println(" After clicking breadcrumb link, user navigates to :  " + desiredURL);
			WebDriverCommonLib.getScreenShot(driver, "Error in verifyIsheroImageAvailable");
			assertTrue((responseCode == HttpURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_MOVED_PERM),
					responseCode + " error occured on :" + url);
			// Assert.fail(" After clicking link, user navigates to :
			// "+desiredURL); //(" After clicking link, user navigates to :
			// "+desiredURL);
			System.out.println("Test case : FAILED !!");
		}

		/*
		 * if((driver.getCurrentUrl().equals(url)) { APPLICATION_LOGS.debug(
		 * "Breadcrumb is available on site :"+url+" Expected Key Value is:  "
		 * +breadcrumText); System.out.println("Test case : PASSED !!");
		 * System.out.println("Breadcrumb is available on site :"+url+
		 * " Expected Key Value is:  "+breadcrumText); } else{
		 * APPLICATION_LOGS.debug("Breadcrumb is not available on site :"+url+
		 * " Expected Key Value is:  "+breadcrumText); System.out.println(
		 * "Breadcrumb is not available on site :"+url+
		 * " Expected Key Value is:  "+breadcrumText);
		 * WebDriverCommonLib.getScreenShot(driver,
		 * "Error in verifyIsBreadCrumbAvailable");
		 * assertTrue(driver.getCurrentUrl().equals(url),
		 * "Breadcrumb is not available on site :"+url+
		 * " Expected Key Value is: "+breadcrumText); System.out.println(
		 * "Test case : FAILED !!"); }
		 */
	}

	public void verifyIsBreadCrumbAvailable_BNB(By locator, String url) throws IOException, InterruptedException {

		List<WebElement> allLinks = driver.findElements(locator);
		int size = allLinks.size();

		WebElement breadcrumb = driver.findElement(locator);
		WebDriverCommonLib.waitForElementToBeClickable(driver, breadcrumb);
		Thread.sleep(3000);
		Actions action = new Actions(driver);
		action.moveToElement(breadcrumb);// .click().perform();
		String breadcrumText = allLinks.get(size - 1).getText();
		System.out.println("the Expected breadcrumb value is : " + breadcrumText);
		try {
			// Thread.sleep(2000);
			action.moveToElement(allLinks.get(size - 1)); // .click().perform();
			click(allLinks.get(size - 1));
		} catch (ElementClickInterceptedException e) {
			System.out.println("Link not clickable");
		}
		// allLinks.get(size-1).click();
		// click(allLinks.get(size-1));
		// breadcrumb.click();
		WebDriverCommonLib.waitForPageToBeLoad(driver);
		System.out.println("The desired URL after clicking on breadcrumb is : " + driver.getCurrentUrl());

		final String ERROR_404_TEXT = "Error 404: Page not found";
		final String PAGE_TYPE_404_TEXT = "404Page";
		final String sourceCode = "Status Code: 503";
		String pageSource = driver.getPageSource();

		boolean flag = pageSource.contains(ERROR_404_TEXT) || pageSource.contains(PAGE_TYPE_404_TEXT)
				|| pageSource.contains(sourceCode);

		driver.get(url);
		WebDriverCommonLib.waitForPageToBeLoad(driver);
		System.out.println(driver.getCurrentUrl());

		if (!(driver.getCurrentUrl().equals(url)) && !is404Page() && flag == false) {
			APPLICATION_LOGS
					.debug("Breadcrumb is available on site :" + url + " Expected Key Value is:  " + breadcrumText);
			System.out.println("Test case : PASSED !!");
			System.out.println("Breadcrumb is available on site :" + url + " Expected Key Value is:  " + breadcrumText);
		} else {
			APPLICATION_LOGS
					.debug("Breadcrumb is not available on site :" + url + " Expected Key Value is:  " + breadcrumText);
			System.out.println(
					"Breadcrumb is not available on site :" + url + " Expected Key Value is:  " + breadcrumText);
			WebDriverCommonLib.getScreenShot(driver, "Error in verifyIsBreadCrumbAvailable");
			assertTrue(driver.getCurrentUrl().equals(url),
					"Breadcrumb is not available on site :" + url + " Expected Key Value is: " + breadcrumText);
			System.out.println("Test case : FAILED !!");
		}
	}

	public void verifyIsElementAvailable(By locator, String url, String element)
			throws IOException, InterruptedException {
		WebElement loc = null;
		try {
			loc = driver.findElement(locator);

		} catch (NoSuchElementException e) {
			// System.out.println("Element not found on site : "+url);
			Assert.fail(element + " not found on site : " + url);

		}
		loc.isDisplayed();
		String elementText = loc.getText();

		if (loc.isDisplayed()) {
			APPLICATION_LOGS
					.debug(element + "  available on site :" + url + " Expected " + element + " is:  " + elementText);
			System.out.println(element + "  available on site :" + url);
			System.out.println("Expected " + element + " :-  " + elementText);
			System.out.println("Test case : PASSED !!");
		} else {
			APPLICATION_LOGS.debug(
					element + "  not available on site :" + url + " Expected " + element + " :-  " + elementText);
			System.out.println(
					element + "  not available on site :" + url + " Expected " + element + " is:  " + elementText);
			WebDriverCommonLib.getScreenShot(driver, "Error in verifyIsTitleAvailable");
			assertTrue(loc.isDisplayed(),
					element + "  not available on site :" + url + " Expected " + element + " :-  " + elementText);
			System.out.println("Test case : FAILED !!");
		}
	}

	public void verifyIsVideoAvailable(By locator, String url, String element)
			throws IOException, InterruptedException {

		Thread.sleep(2000);

		WebElement video = driver.findElement(locator);
		if (video.isDisplayed()) {
			APPLICATION_LOGS.debug(element + " is available on site :" + url);
			System.out.println(element + " is available on site :" + url);
			System.out.println("Test case : PASSED !!");
		} else {
			APPLICATION_LOGS.debug(element + " is not available on site :" + url);
			System.out.println(element + " is not available on site :" + url);
			WebDriverCommonLib.getScreenShot(driver, "Error in verifyIsArchieveDescAvailable");
			Assert.fail(element + " is not available on site :" + url);
			System.out.println("Test case : FAILED !!");
		}
	}

	public void verifyIsVideoLinksWorking(By locator, String url) throws IOException, InterruptedException {

		int links = driver.findElements(locator).size();
		System.out.println("Total no of videos in video conatainer are: " + links);
		SoftAssert softAssert = new SoftAssert();
		for (int i = 0; i < 10; i++) {
			List<WebElement> videos = driver.findElements(locator);
			WebElement video = videos.get(i);
			System.out.println("Running video title is - " + video.getText());
			click(video);
			// video.click();
			WebDriverCommonLib.waitForPageToBeLoad(driver);
			Thread.sleep(1000);
			driver.getCurrentUrl();
			System.out.println("Capturing URL of Running Video - " + driver.getCurrentUrl() + " on site :" + url);
			if (driver.getCurrentUrl().contains(url)) {
				System.out.println("Test case : PASSED !!" + "\n");
			} else {
				APPLICATION_LOGS.debug("Video " + video.getText() + " is not available on site :" + url);
				System.out.println("Video " + video.getText() + " is not available on site :" + url);
				WebDriverCommonLib.getScreenShot(driver, "Error in verifyIsArchieveDescAvailable");
				softAssert.assertTrue(driver.getCurrentUrl().contains(url),
						"Video " + video.getText() + " is not available on site :" + url);
				System.out.println("Test case : FAILED !!");
			}
		}
		softAssert.assertAll();
	}

	public void verifyIsVideoImgLinksWorking(By locator, String url) throws IOException, InterruptedException {
		SoftAssert softAssert = new SoftAssert();
		for (int i = 0; i < 10; i++) {
			List<WebElement> videos = driver.findElements(locator);
			WebElement video = videos.get(i);
			System.out.println(
					"Clicked Video Image-" + (i + 1) + " from Video Container " + video.getCssValue("data-src"));
			click(video);
			// video.click();
			WebDriverCommonLib.waitForPageToBeLoad(driver);
			Thread.sleep(5000);
			driver.getCurrentUrl();
			System.out.println("Capturing URL of Running Video - " + driver.getCurrentUrl() + " on site :" + url);
			if (driver.getCurrentUrl().contains(url)) {
				System.out.println("Test case : PASSED !!" + "\n");
			} else {
				APPLICATION_LOGS.debug("Video " + video.getText() + " is not available on site :" + url);
				System.out.println("Video " + video.getText() + " is not available on site :" + url);
				WebDriverCommonLib.getScreenShot(driver, "Error in verifyIsArchieveDescAvailable");
				softAssert.assertTrue(driver.getCurrentUrl().contains(url),
						"Video " + video.getText() + " is not available on site :" + url);
				System.out.println("Test case : FAILED !!");

			}
		}
		softAssert.assertAll();
	}

	protected boolean matchTabUrl(String targetUrl) throws InterruptedException {
		// save current window handle to switch back to later
		String currentHandle = driver.getWindowHandle();

		// get all tab handles
		List<String> handles = new ArrayList<String>(driver.getWindowHandles());
		for (String handle : handles) {
			driver.switchTo().window(handle);
			WebDriverCommonLib.waitForPageToBeLoad(driver);
			Thread.sleep(5000);
			// found a match
			if (driver.getCurrentUrl().contains(targetUrl)) {
				System.out.println("Link clicked :" + driver.getCurrentUrl());
				// make sure we dont close our own tab
				if (!currentHandle.equals(handle)) {
					driver.close();
					driver.switchTo().window(currentHandle);
				}
				return true;
			}
		}
		driver.switchTo().window(currentHandle);
		return false;
	}

	public void verifyIsElementWorking(By locator, String url, String element)
			throws IOException, InterruptedException {
		WebElement ele = driver.findElement(locator);
		// verifyIsLinkWorking(locator, url);
		Actions actions = new Actions(driver);
		actions.moveToElement(ele); // .click().perform();
		click(ele);

		WebDriverCommonLib.waitForPageToBeLoad(driver);
		Thread.sleep(1000);

		String desiredUrl = driver.getCurrentUrl();
		System.out.println("After click: " + desiredUrl);
		final String ERROR_404_TEXT = "Page not found";
		final String PAGE_TYPE_404_TEXT = "404Page";
		final String sourceCode = "Status Code: 503";
		String pageSource = driver.getPageSource();

		boolean flag = pageSource.contains(ERROR_404_TEXT) || pageSource.contains(PAGE_TYPE_404_TEXT)
				|| pageSource.contains(sourceCode);

		// driver.navigate().back();

		if (((desiredUrl.contains(url)) || (desiredUrl.contains(Constant.FHM_HomePage))
				|| (desiredUrl.contains(Constant.RD_HomePage)) || (desiredUrl.contains(Constant.HLT_HomePage))
				|| (desiredUrl.contains(Constant.SRD_HomePage)) || (desiredUrl.contains(Constant.RDC_HomePage))
				|| (desiredUrl.contains(Constant.BNB_HomePage)) || (desiredUrl.contains(Constant.BHC_HomePage)))
				&& !is404Page() && flag == false) {
			APPLICATION_LOGS.debug(element + " is available and working successfully on site :" + url
					+ " After clicking " + element + ", user navigates to :  " + desiredUrl);
			System.out.println(element + " is available and working successfully on site :" + url);
			System.out.println("Test case : PASSED !!");
			driver.navigate().back();

		} else {
			APPLICATION_LOGS.debug(element + " is not available and working successfully on site :" + url
					+ " After clicking " + element + ", user navigates to :  " + desiredUrl);
			System.out.println(element + " is not available and working successfully on site :" + url);
			WebDriverCommonLib.getScreenShot(driver, "Error in verifyIsheroImageAvailable");
			assertTrue(desiredUrl.contains(url),
					element + " is not available and working successfully on site :" + url);
			System.out.println("Test case : FAILED !!");
		}
	}

	public void verifyIsElementWorking_TOH(By locator, String url, String element)
			throws IOException, InterruptedException {
		WebElement ele = driver.findElement(locator);
		// verifyIsLinkWorking(locator, url);
		Actions actions = new Actions(driver);
		actions.moveToElement(ele); // .click().perform();
		click(ele);

		WebDriverCommonLib.waitForPageToBeLoad(driver);
		Thread.sleep(5000);

		String desiredUrl = driver.getCurrentUrl();
		System.out.println("After click: " + desiredUrl);
		driver.get(url);
		WebDriverCommonLib.waitForPageToBeLoad(driver);

		if (((desiredUrl.contains(url)) || (desiredUrl.contains(Constant.FHM_HomePage))
				|| (desiredUrl.contains(Constant.TOH_HomePage)) || (desiredUrl.contains(Constant.RD_HomePage))
				|| (desiredUrl.contains(Constant.HLT_HomePage)) || (desiredUrl.contains(Constant.SRD_HomePage))
				|| (desiredUrl.contains(Constant.RDC_HomePage)) || (desiredUrl.contains(Constant.BNB_HomePage))
				|| (desiredUrl.contains(Constant.BHC_HomePage))) && !is404Page()) {
			APPLICATION_LOGS.debug(element + " is available and working successfully on site :" + url
					+ " After clicking " + element + ", user navigates to :  " + desiredUrl);
			System.out.println(element + " is available and working successfully on site :" + url);
			System.out.println("Test case : PASSED !!");
		} else {
			APPLICATION_LOGS.debug(element + " is not available and working successfully on site :" + url
					+ " After clicking " + element + ", user navigates to :  " + desiredUrl);
			System.out.println(element + " is not available and working successfully on site :" + url);
			WebDriverCommonLib.getScreenShot(driver, "Error in verifyIsheroImageAvailable");
			assertTrue(desiredUrl.contains(url),
					element + " is not available and working successfully on site :" + url);
			System.out.println("Test case : FAILED !!");
		}
	}

	public void verifyIsElementWorking_BNB(By locator, String url, String element)
			throws IOException, InterruptedException {
		WebElement ele = driver.findElement(locator);
		// verifyIsLinkWorking(locator, url);
		Actions actions = new Actions(driver);
		actions.moveToElement(ele); // .click().perform();
		click(ele);

		WebDriverCommonLib.waitForPageToBeLoad(driver);
		Thread.sleep(5000);

		String desiredUrl = driver.getCurrentUrl();
		System.out.println("After click: " + desiredUrl);
		driver.get(url);
		WebDriverCommonLib.waitForPageToBeLoad(driver);

		if (((desiredUrl.contains(url)) || (desiredUrl.contains(Constant.FHM_HomePage))
				|| (desiredUrl.contains(Constant.TOH_HomePage)) || (desiredUrl.contains(Constant.RD_HomePage))
				|| (desiredUrl.contains(Constant.HLT_HomePage)) || (desiredUrl.contains(Constant.SRD_HomePage))
				|| (desiredUrl.contains(Constant.RDC_HomePage)) || (desiredUrl.contains(Constant.BNB_HomePage))
				|| (desiredUrl.contains(Constant.BHC_HomePage))) && !is404Page()) {
			APPLICATION_LOGS.debug(element + " is available and working successfully on site :" + url
					+ " After clicking " + element + ", user navigates to :  " + desiredUrl);
			System.out.println(element + " is available and working successfully on site :" + url);
			System.out.println("Test case : PASSED !!");
		} else {
			APPLICATION_LOGS.debug(element + " is not available and working successfully on site :" + url
					+ " After clicking " + element + ", user navigates to :  " + desiredUrl);
			System.out.println(element + " is not available and working successfully on site :" + url);
			WebDriverCommonLib.getScreenShot(driver, "Error in verifyIsheroImageAvailable");
			assertTrue(desiredUrl.contains(url),
					element + " is not available and working successfully on site :" + url);
			System.out.println("Test case : FAILED !!");
		}
	}

	public void verifyIsElementWorking_CA(By locator, String url, String element)
			throws IOException, InterruptedException {
		WebElement ele = driver.findElement(locator);
		// verifyIsLinkWorking(locator, url);
		Actions actions = new Actions(driver);
		actions.moveToElement(ele); // .click().perform();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,-200)", "");
		actions.click().build().perform();
		click(ele);

		WebDriverCommonLib.waitForPageToBeLoad(driver);
		Thread.sleep(1000);

		String desiredUrl = driver.getCurrentUrl();
		System.out.println("After click: " + desiredUrl);
		final String ERROR_404_TEXT = "Error 404: Page not found";
		final String PAGE_TYPE_404_TEXT = "404Page";
		final String sourceCode = "Status Code: 503";
		String pageSource = driver.getPageSource();

		boolean flag = pageSource.contains(ERROR_404_TEXT) || pageSource.contains(PAGE_TYPE_404_TEXT)
				|| pageSource.contains(sourceCode);

		driver.navigate().back();

		if (((desiredUrl.contains(url)) || (desiredUrl.contains(Constant.FHM_HomePage))
				|| (desiredUrl.contains(Constant.RD_HomePage)) || (desiredUrl.contains(Constant.HLT_HomePage))
				|| (desiredUrl.contains(Constant.SRD_HomePage)) || (desiredUrl.contains(Constant.RDC_HomePage))
				|| (desiredUrl.contains(Constant.BNB_HomePage)) || (desiredUrl.contains(Constant.BHC_HomePage)))
				&& !is404Page() && flag == false) {
			APPLICATION_LOGS.debug(element + " is available and working successfully on site :" + url
					+ " After clicking " + element + ", user navigates to :  " + desiredUrl);
			System.out.println(element + " is available and working successfully on site :" + url);
			System.out.println("Test case : PASSED !!");
		} else {
			APPLICATION_LOGS.debug(element + " is not available and working successfully on site :" + url
					+ " After clicking " + element + ", user navigates to :  " + desiredUrl);
			System.out.println(element + " is not available and working successfully on site :" + url);
			WebDriverCommonLib.getScreenShot(driver, "Error in verifyIsheroImageAvailable");
			assertTrue(desiredUrl.contains(url),
					element + " is not available and working successfully on site :" + url);
			System.out.println("Test case : FAILED !!");
		}
	}

	public void verifyNextProject(By locator, String url, String element) throws IOException, InterruptedException {

		WebElement ele = driver.findElement(locator);
		click(ele);
		// verifyIsLinkWorking(locator, url);
		WebDriverCommonLib.waitForPageToBeLoad(driver);
		Thread.sleep(5000);

		String desiredUrl = driver.getCurrentUrl();
		System.out.println("After click: " + desiredUrl);
		final String ERROR_404_TEXT = "Error 404: Page not found";
		final String PAGE_TYPE_404_TEXT = "404Page";
		final String sourceCode = "Status Code: 503";
		String pageSource = driver.getPageSource();

		boolean flag = pageSource.contains(ERROR_404_TEXT) || pageSource.contains(PAGE_TYPE_404_TEXT)
				|| pageSource.contains(sourceCode);

		driver.navigate().back();

		if (driver.getCurrentUrl().equals(url) && !is404Page() && flag == false) {
			APPLICATION_LOGS.debug(element + " is available and working successfully on site :" + url
					+ " After clicking " + element + ", user navigates to :  " + desiredUrl);
			System.out.println(element + " is available and working successfully on site :" + url);
			System.out.println("Test case : PASSED !!");
		} else {
			APPLICATION_LOGS.debug(element + " is not available and working successfully on site :" + url
					+ " After clicking " + element + ", user navigates to :  " + desiredUrl);
			System.out.println(element + " is not available and working successfully on site :" + url);
			WebDriverCommonLib.getScreenShot(driver, "Error in verifyIsheroImageAvailable");
			assertTrue(driver.getCurrentUrl().equals(url),
					element + " is not available and working successfully on site :" + url);
			System.out.println("Test case : FAILED !!");
		}
	}
	
	public void verifyNewsletterSignUp(String url, By emailId, By locator, String newspaperLink)
			throws IOException, InterruptedException {
		// WebElement email_ID = driver.findElement(emailId);
		WebElement ele = null;
		String newsletterURL = "";
		try {
			ele = driver.findElement(locator);
			newsletterURL = ele.getAttribute("href");
		} catch (NoSuchElementException e) {
			Assert.fail("Newspaper element not found on site: " + url);
		}
		URL desiredurl = new URL(newsletterURL);

		HttpURLConnection.setFollowRedirects(false);
		HttpURLConnection httpURLConnection = (HttpURLConnection) desiredurl.openConnection();

		// We don't need to get data
		httpURLConnection.setRequestMethod("HEAD");

		// Some websites don't like programmatic access so pretend to be a
		// browser
		httpURLConnection.setRequestProperty("User-Agent",
				"Mozilla/5.0 (Windows; U; Windows NT 6.0; en-US; rv:1.9.1.2) Gecko/20090729 Firefox/3.5.2 (.NET CLR 3.5.30729)");
		int responseCode = httpURLConnection.getResponseCode();

		// We only accept response code 200
		System.out.println("Getting Response Code : " + responseCode + " in " + url);
		if ((responseCode == HttpURLConnection.HTTP_OK) && (responseCode == HttpURLConnection.HTTP_MOVED_TEMP)
				&& (responseCode == HttpURLConnection.HTTP_MOVED_PERM) && (newsletterURL.contains(newspaperLink))) {
			APPLICATION_LOGS.debug("Newspaper link is working fine on site :" + url
					+ " After clicking Newspaper link, user navigates to :  " + newsletterURL);
			System.out.println("Newspaper link is working successfully on site :" + url);
			System.out.println(" After clicking Newspaper link, user navigates to :  " + newsletterURL);
			System.out.println("Test case : PASSED !!");
		} else {
			APPLICATION_LOGS.debug("Newspaper link is not working successfully on site :" + url
					+ " After clicking Newspaper link, user navigates to :  " + newsletterURL);
			System.out.println("Newspaper link is not working successfully on site :" + url);
			System.out.println(" After clicking Newspaper link, user navigates to :  " + newsletterURL);
			WebDriverCommonLib.getScreenShot(driver, "Error in verifyIsheroImageAvailable");
			assertTrue(newsletterURL.contains(newspaperLink), "Newspaper link is not working successfully on site :"
					+ url + " After clicking Newspaper link, user navigates to : " + newsletterURL);
			System.out.println("Test case : FAILED !!");
		}

	}

	public void verifyNewsletterSignUp_Footer(String url, By emailId, By locator, String newspaperLink)
			throws IOException, InterruptedException {
		// WebElement email_ID = driver.findElement(emailId);
		WebElement ele = null;
		String newsletterURL = "";
		try {
			ele = driver.findElement(locator);
			newsletterURL = ele.getAttribute("action");
		} catch (NoSuchElementException e) {
			Assert.fail("Newspaper element not found on site: " + url);
		}
		URL desiredurl = new URL(newsletterURL);

		HttpURLConnection.setFollowRedirects(false);
		HttpURLConnection httpURLConnection = (HttpURLConnection) desiredurl.openConnection();

		// We don't need to get data
		httpURLConnection.setRequestMethod("HEAD");

		// Some websites don't like programmatic access so pretend to be a
		// browser
		httpURLConnection.setRequestProperty("User-Agent",
				"Mozilla/5.0 (Windows; U; Windows NT 6.0; en-US; rv:1.9.1.2) Gecko/20090729 Firefox/3.5.2 (.NET CLR 3.5.30729)");
		int responseCode = httpURLConnection.getResponseCode();

		// We only accept response code 200
		System.out.println("Getting Response Code : " + responseCode + " in " + url);
		if ((responseCode == HttpURLConnection.HTTP_OK) && (responseCode == HttpURLConnection.HTTP_MOVED_TEMP)
				&& (responseCode == HttpURLConnection.HTTP_MOVED_PERM) && (newsletterURL.contains(newspaperLink))) {
			APPLICATION_LOGS.debug("Newspaper link is working fine on site :" + url
					+ " After clicking Newspaper link, user navigates to :  " + newsletterURL);
			System.out.println("Newspaper link is working successfully on site :" + url);
			System.out.println(" After clicking Newspaper link, user navigates to :  " + newsletterURL);
			System.out.println("Test case : PASSED !!");
		} else {
			APPLICATION_LOGS.debug("Newspaper link is not working successfully on site :" + url
					+ " After clicking Newspaper link, user navigates to :  " + newsletterURL);
			System.out.println("Newspaper link is not working successfully on site :" + url);
			System.out.println(" After clicking Newspaper link, user navigates to :  " + newsletterURL);
			WebDriverCommonLib.getScreenShot(driver, "Error in verifyIsheroImageAvailable");
			assertTrue(newsletterURL.contains(newspaperLink), "Newspaper link is not working successfully on site :"
					+ url + " After clicking Newspaper link, user navigates to : " + newsletterURL);
			System.out.println("Test case : FAILED !!");
		}

	} // Verify the Expected Key-Value is present in Page Source or not

	public void verifyLinkStatus(By locator, String url, String element) throws IOException {
		SoftAssert softAssert = new SoftAssert();
		WebElement ele = driver.findElement(locator);
		String linkText = ele.getText();
		String desiredURL = ele.getAttribute("href");
		System.out.println("Expected URL is : " + desiredURL);
		URL desiredurl = new URL(desiredURL);

		HttpURLConnection.setFollowRedirects(false);
		HttpURLConnection httpURLConnection = (HttpURLConnection) desiredurl.openConnection();

		// We don't need to get data
		httpURLConnection.setRequestMethod("HEAD");

		// Some websites don't like programmatic access so pretend to be a
		// browser
		httpURLConnection.setRequestProperty("User-Agent",
				"Mozilla/5.0 (Windows; U; Windows NT 6.0; en-US; rv:1.9.1.2) Gecko/20090729 Firefox/3.5.2 (.NET CLR 3.5.30729)");
		int responseCode = httpURLConnection.getResponseCode();

		// We only accept response code 200
		System.out.println("Getting Response Code : " + responseCode + " in " + desiredURL);
		if ((responseCode == HttpURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_MOVED_PERM || responseCode == HttpURLConnection.HTTP_MOVED_TEMP )
				&& (((desiredURL.contains(url)) || (desiredURL.contains(Constant.FHM_HomePage))
						|| (desiredURL.contains(Constant.RD_HomePage)) || (desiredURL.contains(Constant.HLT_HomePage))
						|| (desiredURL.contains(Constant.SRD_HomePage)) || (desiredURL.contains(Constant.RDC_HomePage))
						|| (desiredURL.contains(Constant.BNB_HomePage))
						|| (desiredURL.contains(Constant.BHC_HomePage))))) {
			APPLICATION_LOGS.debug(linkText + " link is working fine on site :" + url
					+ " After clicking link, user navigates to :  " + desiredURL);
			System.out.println(linkText + " link is working successfully on site :" + url);
			System.out.println(" After clicking link, user navigates to :  " + desiredURL);
			System.out.println("Test case : PASSED !!");
		} else {
			APPLICATION_LOGS.debug(linkText + " link is not working successfully on site :" + url
					+ " After clicking link, user navigates to :  " + desiredURL);
			System.out.println(linkText + " link is not working successfully on site :" + url);
			System.out.println(" After clicking link, user navigates to :  " + desiredURL);
			WebDriverCommonLib.getScreenShot(driver, "Error in verifyIsheroImageAvailable");
			softAssert.assertTrue(
					(responseCode == HttpURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_MOVED_PERM),
					responseCode + " error occured on link :" + linkText);
			// Assert.fail(" After clicking link, user navigates to :
			// "+desiredURL); //(" After clicking link, user navigates to :
			// "+desiredURL);
			System.out.println("Test case : FAILED !!");
		}
		softAssert.assertAll();
	}

	public void verifyDiyuLogo(String url, By locator, String diyuLogoLink) throws IOException, InterruptedException {
		WebElement element = driver.findElement(locator);
		// wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		Thread.sleep(3000);
		Actions action = new Actions(driver);
		action.moveToElement(element).click().perform();

		WebDriverCommonLib.waitForPageToBeLoad(driver);
		Thread.sleep(5000);

		String currentHandle = driver.getWindowHandle();
		// get all tab handles
		ArrayList<String> handles = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(handles.get(1));

		WebDriverCommonLib.waitForPageToBeLoad(driver);
		Thread.sleep(5000);
		String actualUrl = driver.getCurrentUrl();
		System.out.println("After click: " + actualUrl);

		// found a match
		if (driver.getCurrentUrl().equals(diyuLogoLink)) {
			System.out.println("Link clicked :" + driver.getCurrentUrl());
			APPLICATION_LOGS.debug("Diyu link is working fine on site :" + url
					+ " After clicking Diyu link, user navigates to :  " + actualUrl);
			System.out.println("Diyu link is working successfully on site :" + url);
			System.out.println(" After clicking Diyu link, user navigates to :  " + actualUrl);
			System.out.println("Test case : PASSED !!");
		} else {
			APPLICATION_LOGS.debug("Diyu link is not working successfully on site :" + url
					+ " After clicking Diyu link, user navigates to :  " + actualUrl);
			System.out.println("Diyu link is not working successfully on site :" + url
					+ " After clicking Diyu link, user navigates to :  " + actualUrl);
			System.out.println(" After clicking Diyu link, user navigates to :  " + actualUrl);
			WebDriverCommonLib.getScreenShot(driver, "Error in verifyIsheroImageAvailable");
			assertTrue(driver.getCurrentUrl().equals(diyuLogoLink), "Diyu link is not working successfully on site :"
					+ url + " After clicking Diyu link, user navigates to : " + actualUrl);
			System.out.println("Test case : FAILED !!");
		}
		driver.close();
		driver.switchTo().window(currentHandle);

	}

	public void isLineExist(String url, By avatarLocator, By authorLocator) throws InterruptedException, IOException {
		scrollToElement(avatarLocator);
		int avatarSize = driver.findElements(avatarLocator).size();
		int authorSize = driver.findElements(authorLocator).size();
		String name = driver.findElement(authorLocator).getText();
		System.out.println("Post author is available on site :" + name);

		if (avatarSize > 0 && authorSize > 0) {
			APPLICATION_LOGS.debug("Avatar image is available on site :" + url);
			System.out.println("Avatar image is available on site :" + url);
			System.out.println("Test case : PASSED !!");
		} else {
			APPLICATION_LOGS.debug("Avatar image does not exist on site :" + url);
			System.out.println("Avatar image or author does not exist on site  :" + url);
			WebDriverCommonLib.getScreenShot(driver, "Error in isLineExist");
			Assert.fail("Avatar image does not exist on site  :" + url);
			System.out.println("Test case : FAILED !!");
		}
	}

	public void IsElementExist(String url, By Locator, String element) throws InterruptedException, IOException {
		scrollToElement(Locator);
		int LocatorSize = driver.findElements(Locator).size();
		String elementText = driver.findElement(Locator).getText();
		System.out.println(element + "size is : " + LocatorSize);

		if (LocatorSize > 0) {
			APPLICATION_LOGS.debug(element + " is available on site :" + url);
			System.out.println(element + " is available on site :" + url + "Element Text is :" + elementText);
			System.out.println("Test case : PASSED !!");
		} else {
			APPLICATION_LOGS.debug(element + " is does not exist on site :" + url);
			System.out.println(element + " is does not exist on site  :" + url);
			Assert.fail(element + " is does not exist on site  :" + url);
			System.out.println("Test case : FAILED !!");
		}
	}

	public void verifyCardCount(String url, By CardCountLocator, By cardLoc) throws InterruptedException, IOException {
		scrollToElement(CardCountLocator);
		String cardText = driver.findElement(CardCountLocator).getText();
		System.out.println("Card text is: " + cardText);
		int expectedCardCount = Integer.parseInt(cardText.split(" ")[1]);

		int actualCardCount = driver.findElements(cardLoc).size();
		System.out.println("Total number of Cards are " + actualCardCount);

		if (expectedCardCount == actualCardCount) {
			APPLICATION_LOGS.debug("The actual number of cards [" + actualCardCount
					+ "] is matched with the expected number of cards [" + expectedCardCount + "]");
			System.out.println("The actual number of cards [" + actualCardCount
					+ "] is matched with the expected number of cards [" + expectedCardCount + "]");
			System.out.println("Test case : PASSED !!");
		} else {
			APPLICATION_LOGS.debug("The actual number of cards [" + actualCardCount
					+ "] does not match the expected number of cards [" + expectedCardCount + "]");
			System.out.println("The actual number of cards [" + actualCardCount
					+ "] does not match the expected number of cards [" + expectedCardCount + "]");
			WebDriverCommonLib.getScreenShot(driver, "Error in IsdekExist");
			Assert.fail("The actual number of cards [" + actualCardCount
					+ "] does not match the expected number of cards [" + expectedCardCount + "]");
			System.out.println("Test case : FAILED !!");
		}
	}

	// validating social share links individually(eg : checking fb, twitter,
	// insta links individually)
	public void Test(String url, By dekLocator, By socialIconLocator, String targetUrl)
			throws InterruptedException, IOException {

		driver.findElement(dekLocator);
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(dekLocator));
		WebElement element = driver.findElement(socialIconLocator);

		try {
			click(element);
			// action.moveToElement(element).click().perform();
			// element.click();
			System.out.println("Link clicked!!");
		} catch (Exception e) {
			System.out.println("Link not present");
		}

		String parentWindow = driver.getWindowHandle();

		// Iterate through the collection of all available window handles
		for (String childWindowHandle : driver.getWindowHandles()) {

			// Ignore which handle is equal to the parent handle
			if (!childWindowHandle.equalsIgnoreCase(parentWindow)) {

				// Switch to the child window handle
				driver.switchTo().window(childWindowHandle);
				driver.manage().window().maximize();
				Thread.sleep(2000);
				System.out.println(driver.getCurrentUrl());

				if (driver.getCurrentUrl().contains(targetUrl) && !is404Page()) {
					APPLICATION_LOGS.debug(targetUrl + " is working as expected on site :" + url);
					System.out.println(targetUrl + " is working as expected on site :" + url);
					System.out.println(
							"After clicking " + targetUrl + " , user navigates to :  " + driver.getCurrentUrl());
					System.out.println("Test case : PASSED !!");
				} else {
					APPLICATION_LOGS.debug(targetUrl + " is not working as expected on site :" + url);
					System.out.println(targetUrl + " is not working as expected on site :" + url);
					WebDriverCommonLib.getScreenShot(driver, "Error in verifySocialShareLinks");
					Assert.fail(targetUrl + " is not working as expected on site :" + url);
					System.out.println("Test case : FAILED !!");
				}
				driver.close();
				// Switch back to the parent handle
				driver.switchTo().window(parentWindow);
			}
		}
	}

	public void verifyPagination(String url, By nextPage, By prevPage) throws InterruptedException, IOException {
		// WebDriverWait wait = new WebDriverWait(driver, 10);
		Actions action = new Actions(driver);
		try {
			action.moveToElement(driver.findElement(By.xpath("//span[@class='page-numbers current']")));
		} catch (NoSuchElementException e) {
			System.out.println("Element not present");
		}

		WebElement nextPg = driver.findElement(nextPage);
		action.moveToElement(nextPg);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,-200)", "");
		action.click().build().perform();
		WebDriverCommonLib.waitForPageToBeLoad(driver);

		String desiredUrl = driver.getCurrentUrl();
		System.out.println("After clicking on NextPage link, Successfully navigates to desired web page!!");
		System.out.println("Desired URL: " + desiredUrl);
		final String ERROR_404_TEXT = "Error 404: Page not found";
		final String PAGE_TYPE_404_TEXT = "404Page";
		final String sourceCode = "Status Code: 503";
		String pageSource = driver.getPageSource();

		boolean flag = pageSource.contains(ERROR_404_TEXT) || pageSource.contains(PAGE_TYPE_404_TEXT)
				|| pageSource.contains(sourceCode);

		WebDriverCommonLib.waitForPageToBeLoad(driver);
		Thread.sleep(5000);// Pagination loading after 5 seconds
		WebElement prevPg = driver.findElement(prevPage);
		action.moveToElement(prevPg);
		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		js1.executeScript("window.scrollBy(0,-200)", "");
		action.click().build().perform();
		WebDriverCommonLib.waitForPageToBeLoad(driver);

		/*
		 * js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		 * js.executeScript("window.scrollBy(0,-300)", "");
		 * action.moveToElement(driver.findElement(By.xpath(
		 * "//div[@class='pagination']")));
		 * action.moveToElement(driver.findElement(By.xpath(
		 * "//span[@class='page-numbers current']"))); WebElement prevPg =
		 * driver.findElement(prevPage);
		 * action.moveToElement(prevPg).click().perform();
		 * WebDriverCommonLib.waitForPageToBeLoad(driver);
		 * Thread.sleep(10000);//Pagination loading after 5 seconds
		 */
		System.out.println("After clicking on Previous Page link, Successfully navigates to actual web page!!");
		System.out.println("Current URL: " + driver.getCurrentUrl());

		if (driver.getCurrentUrl().equals(url) && desiredUrl.contains("/2") && flag == false) {

			APPLICATION_LOGS.debug("pagination links are working successfully on site :" + url);
			System.out.println("pagination links are working successfully on site :" + url);
			System.out.println("Test case : PASSED !!");
		} else {
			APPLICATION_LOGS.debug("pagination links are not working successfully on site :" + url);
			System.out.println("pagination links are not working successfully on site :" + url);
			WebDriverCommonLib.getScreenShot(driver, "Error in verifyPagination");
			Assert.fail("pagination links are not working successfully on site :" + url);
			System.out.println("Test case : FAILED !!");
		}
	}

	public void verifyIsVideoRunning(By locator, String url) throws IOException, InterruptedException {

		int links = driver.findElements(locator).size();
		System.out.println("Total no of videos in video container are: " + links);

		for (int i = 0; i < links; i++) {
			System.out.println(i);
			List<WebElement> videos = driver.findElements(locator);
			WebElement video = videos.get(i);
			// String videoTitle= video.getAttribute(Title);
			// video.getText();
			if (video.isDisplayed() && video.isEnabled()) {
				// click(locator);
				video.click();
				Thread.sleep(5000);
				APPLICATION_LOGS.debug("video# " + (i + 1) + " is clickable on site : " + url);
				System.out.println("video# " + (i + 1) + ":-" + " is clickable on site : " + url);
				System.out.println("Test case : PASSED !!");
			} else {
				APPLICATION_LOGS.debug("video# " + (i + 1) + " is not clickable on site : " + url);
				System.out.println("video# " + (i + 1) + " is not clickable on site : " + url);
				WebDriverCommonLib.getScreenShot(driver, "Error in isElementClickable");
				Assert.fail("video# " + (i + 1) + " is not clickable on site : " + url);
				System.out.println("Test case : FAILED !!");
			}
		}
	}

	public void brandLinksNavigation_BNB(By locator, String url, String element)
			throws IOException, InterruptedException {
		List<WebElement> links = driver.findElements(locator);
		int count = links.size();
		System.out.println("Total no of links in footer brands logos are: " + count);
		if (count == 0) {
			Assert.fail(element + " not available in footer section of site: " + url);
		}
		for (int i = 0; i < count; i++) {
			// System.out.println(i);
			List<WebElement> lists = driver.findElements(locator);
			WebElement list = lists.get(i);

			String browseText = list.getText();
			Thread.sleep(2000);
			System.out.println(browseText);
			// if (list.isDisplayed() && list.isEnabled()) {
			Actions actions = new Actions(driver);
			actions.moveToElement(list).click().perform();
			// click(list);
			// list.click();
			// WebDriverCommonLib.waitForPageToBeLoad(driver);
			Thread.sleep(5000);
			ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
			System.out.println("Number of tabs: " + tabs);
			String title = driver.getTitle();
			System.out.println(title);

			String desiredUrl = driver.getCurrentUrl();
			System.out.println("After clicking - " + title + " , Successfully navigates to desired web page!!");
			System.out.println("Desired URL: " + desiredUrl);
			final String ERROR_404_TEXT = "Error 404: Page not found";
			final String PAGE_TYPE_404_TEXT = "404Page";
			final String sourceCode = "Status Code: 503";
			String pageSource = driver.getPageSource();

			boolean flag = pageSource.contains(ERROR_404_TEXT) || pageSource.contains(PAGE_TYPE_404_TEXT)
					|| pageSource.contains(sourceCode);

			if ((desiredUrl.equals(url) && tabs.size() == 1) || is404Page() || desiredUrl.isEmpty() || flag == true) {
				APPLICATION_LOGS.debug(browseText + " brands link is not working successfully on site :" + url
						+ " After clicking brands link, user navigates to :  " + desiredUrl);
				System.out.println(browseText + " brand link is not working successfully on site :" + url);
				WebDriverCommonLib.getScreenShot(driver, "Error in verifyIsheroImageAvailable");
				boolean Browserstatus = driver.getCurrentUrl().equals(url);
				if (Browserstatus == false) {
					driver.navigate().back();
					WebDriverCommonLib.waitForPageToBeLoad(driver);
					Thread.sleep(3000);
				}
				Assert.fail(browseText + " brand link is not working successfully on site :" + url
						+ " After clicking brands link, user navigates to : " + desiredUrl);
				System.out.println("Test case : FAILED !!");
			} else {
				// Thread.sleep(1000);
				boolean Browserstatus = driver.getCurrentUrl().equals(url);
				if (Browserstatus == false) {
					driver.navigate().back();
					WebDriverCommonLib.waitForPageToBeLoad(driver);
					Thread.sleep(1000);
				}
			}
		}
		boolean Browserstatus = driver.getCurrentUrl().equals(url);
		if (Browserstatus == false) {
			driver.navigate().back();
			WebDriverCommonLib.waitForPageToBeLoad(driver);
			Thread.sleep(3000);
		}
		try {
			closeBounceExchange();
		} catch (Exception e) {
			System.out.println("Bounce Exchange not present");
		}
	}

	public void brandLinksNavigation_copy(By locator, String url, String element)
			throws IOException, InterruptedException {
		List<WebElement> links = driver.findElements(locator);
		int count = links.size();
		// System.out.println("Total no of links in footer brands logos are: " +
		// count);

		if (count == 0) {
			Assert.fail(element + " not available in footer section of site: " + url);
		}
		for (int i = 0; i < count; i++) {
			// System.out.println(i);
			List<WebElement> lists = driver.findElements(locator);
			WebElement list = lists.get(i);

			String browseText = list.getText();

			Thread.sleep(2000);

			System.out.println(browseText);
			// if (list.isDisplayed() && list.isEnabled()) {
			Actions actions = new Actions(driver);
			actions.moveToElement(list).click().perform();
			// click(list);
			// list.click();
			// WebDriverCommonLib.waitForPageToBeLoad(driver);
			Thread.sleep(5000);

			String title = driver.getTitle();
			System.out.println(title);

			String desiredUrl = driver.getCurrentUrl();
			System.out.println("After clicking - " + title + " , Successfully navigates to desired web page!!");
			System.out.println("Desired URL: " + desiredUrl);

			final String ERROR_404_TEXT = "Error 404: Page not found";
			final String PAGE_TYPE_404_TEXT = "404Page";
			final String sourceCode = "Status Code: 503";
			String pageSource = driver.getPageSource();

			boolean flag = pageSource.contains(ERROR_404_TEXT) || pageSource.contains(PAGE_TYPE_404_TEXT)
					|| pageSource.contains(sourceCode);

			if (desiredUrl.equals(url) && !is404Page() && !(title.contains("Page not found")) && flag == false) {
				APPLICATION_LOGS.debug(browseText + " brand link is working successfully on site :" + url);
				System.out.println(browseText + " brand link is working successfully on site :" + url);
				System.out.println("Test case : PASSED !!");
			} else {
				// Thread.sleep(1000);
				driver.navigate().back();
				WebDriverCommonLib.waitForPageToBeLoad(driver);
				Thread.sleep(1000);

				if (!desiredUrl.isEmpty() && !(desiredUrl.equals(url)) && !is404Page()
						&& !(title.contains("Page not found")) && flag == false) {
					APPLICATION_LOGS.debug(browseText + " brand link is working successfully on site :" + url);
					System.out.println(browseText + " brand link is working successfully on site :" + url);
					System.out.println("Test case : PASSED !!");
				} else {
					APPLICATION_LOGS.debug(browseText + " brands link is not working successfully on site :" + url
							+ " After clicking brands link, user navigates to :  " + desiredUrl);
					System.out.println(browseText + " brand link is not working successfully on site :" + url);
					WebDriverCommonLib.getScreenShot(driver, "Error in verifyIsheroImageAvailable");
					Assert.fail(browseText + " brand link is not working successfully on site :" + url
							+ " After clicking brands link, user navigates to : " + desiredUrl);
					System.out.println("Test case : FAILED !!");

				}
			}
			// }
		}
	}

	public void brandLinksNavigation(By locator, String url, String element) throws IOException, InterruptedException {
		SoftAssert softAssert = new SoftAssert();
		List<WebElement> links = driver.findElements(locator);
		int count = links.size();
		System.out.println("Total no of links are: " + count);

		if (count == 0) {
			Assert.fail(element + " not available on site: " + url);
		}
		for (int i = 0; i < count; i++) {
			// System.out.println(i);
			List<WebElement> lists = driver.findElements(locator);
			WebElement list = lists.get(i);

			String browseText = list.getText();
			System.out.println(browseText);
			String desiredUrl = list.getAttribute("href");
			System.out.println("desired URL is: " + desiredUrl);
			// Thread.sleep(500);

			URL desiredurl = new URL(desiredUrl);

			HttpURLConnection.setFollowRedirects(false);
			HttpURLConnection httpURLConnection = (HttpURLConnection) desiredurl.openConnection();

			// We don't need to get data
			httpURLConnection.setRequestMethod("HEAD");
			httpURLConnection.setRequestProperty("User-Agent",
					"Mozilla/5.0 (Windows; U; Windows NT 6.0; en-US; rv:1.9.1.2) Gecko/20090729 Firefox/3.5.2 (.NET CLR 3.5.30729)");
			int responseCode = 0;
			try {
				responseCode = httpURLConnection.getResponseCode();
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
			// We only accept response code 200
			System.out.println("Getting Response Code : " + responseCode + " in " + desiredurl);
			if (((responseCode == HttpURLConnection.HTTP_OK) || (responseCode == HttpURLConnection.HTTP_MOVED_PERM)
					|| (responseCode == HttpURLConnection.HTTP_MOVED_TEMP) || (responseCode == 405) || (responseCode == 308))
					&& (!desiredUrl.isEmpty() && !(desiredUrl.equals(url)))) {
				System.out.println(browseText + " brand link is working successfully on site :" + url);
				System.out.println(" Page loaded successfully.....");
				System.out.println("Test case : Passed.. !!");
			} else {
				softAssert.assertTrue(
						responseCode == (HttpURLConnection.HTTP_OK) || responseCode == 301 || responseCode == 302,
						responseCode + " Error found on : " + desiredUrl);
				System.out.println("Test case : FAILED !!");

			}
		}
		softAssert.assertAll();
	}

	public void brandLinksNavigation1(By locator, String url, String element) throws IOException, InterruptedException {
		List<WebElement> links = driver.findElements(locator);
		int count = links.size();
		// System.out.println("Total no of links in footer brands logos are: " +
		// count);

		if (count == 0) {
			Assert.fail(element + " not available in footer section of site: " + url);
		}
		for (int i = 0; i < count; i++) {
			// System.out.println(i);
			List<WebElement> lists = driver.findElements(locator);
			WebElement list = lists.get(i);

			String browseText = list.getText();

			Thread.sleep(2000);

			System.out.println(browseText);
			// if (list.isDisplayed() && list.isEnabled()) {
			Actions actions = new Actions(driver);
			actions.moveToElement(list).click().perform();
			// click(list);
			// list.click();
			// WebDriverCommonLib.waitForPageToBeLoad(driver);
			Thread.sleep(5000);

			String title = driver.getTitle();
			System.out.println(title);

			String desiredUrl = driver.getCurrentUrl();
			System.out.println("After clicking - " + title + " , Successfully navigates to desired web page!!");
			System.out.println("Desired URL: " + desiredUrl);

			final String ERROR_404_TEXT = "Error 404: Page not found";
			final String PAGE_TYPE_404_TEXT = "404Page";
			final String sourceCode = "Status Code: 503";
			String pageSource = driver.getPageSource();

			boolean flag = pageSource.contains(ERROR_404_TEXT) || pageSource.contains(PAGE_TYPE_404_TEXT)
					|| pageSource.contains(sourceCode);

			if (desiredUrl.equals(url) && !is404Page() && !(title.contains("Page not found")) && flag == false) {
				APPLICATION_LOGS.debug(browseText + " brand link is working successfully on site :" + url);
				System.out.println(browseText + " brand link is working successfully on site :" + url);
				System.out.println("Test case : PASSED !!");
			} else {
				// Thread.sleep(1000);
				driver.navigate().back();
				WebDriverCommonLib.waitForPageToBeLoad(driver);
				Thread.sleep(1000);

				if (!desiredUrl.isEmpty() && !(desiredUrl.equals(url)) && !is404Page()
						&& !(title.contains("Page not found")) && flag == false) {
					APPLICATION_LOGS.debug(browseText + " brand link is working successfully on site :" + url);
					System.out.println(browseText + " brand link is working successfully on site :" + url);
					System.out.println("Test case : PASSED !!");
				} else {
					APPLICATION_LOGS.debug(browseText + " brands link is not working successfully on site :" + url
							+ " After clicking brands link, user navigates to :  " + desiredUrl);
					System.out.println(browseText + " brand link is not working successfully on site :" + url);
					WebDriverCommonLib.getScreenShot(driver, "Error in verifyIsheroImageAvailable");
					Assert.fail(browseText + " brand link is not working successfully on site :" + url
							+ " After clicking brands link, user navigates to : " + desiredUrl);
					System.out.println("Test case : FAILED !!");

				}
			}
			// }
		}
	}

	public void brandLinksNavigation_TOH(By locator, String url, String element)
			throws IOException, InterruptedException {
		List<WebElement> links = driver.findElements(locator);
		int count = links.size();
		// System.out.println("Total no of links in footer brands logos are: " +
		// count);

		if (count == 0) {
			Assert.fail(element + " not available in footer section of site: " + url);
		}
		for (int i = 0; i < count; i++) {
			// System.out.println(i);
			List<WebElement> lists = driver.findElements(locator);
			WebElement list = lists.get(i);

			String browseText = list.getText();

			Thread.sleep(2000);

			System.out.println(browseText);
			// if (list.isDisplayed() && list.isEnabled()) {
			Actions actions = new Actions(driver);
			actions.moveToElement(list).click().perform();
			// click(list);
			// list.click();
			// WebDriverCommonLib.waitForPageToBeLoad(driver);
			Thread.sleep(5000);

			String title = driver.getTitle();
			System.out.println(title);

			String desiredUrl = driver.getCurrentUrl();
			System.out.println("After clicking - " + title + " , Successfully navigates to desired web page!!");
			System.out.println("Desired URL: " + desiredUrl);

			if (desiredUrl.equals(url) && !is404Page()) {
				APPLICATION_LOGS.debug(browseText + " brand link is working successfully on site :" + url);
				System.out.println(browseText + " brand link is working successfully on site :" + url);
				System.out.println("Test case : PASSED !!");
			} else {
				// Thread.sleep(1000);
				driver.get(url);
				WebDriverCommonLib.waitForPageToBeLoad(driver);
				Thread.sleep(1000);

				if (!desiredUrl.isEmpty() && !(desiredUrl.equals(url)) && !is404Page()) {
					APPLICATION_LOGS.debug(browseText + " brand link is working successfully on site :" + url);
					System.out.println(browseText + " brand link is working successfully on site :" + url);
					System.out.println("Test case : PASSED !!");
				} else {
					APPLICATION_LOGS.debug(browseText + " brands link is not working successfully on site :" + url
							+ " After clicking brands link, user navigates to :  " + desiredUrl);
					System.out.println(browseText + " brand link is not working successfully on site :" + url);
					WebDriverCommonLib.getScreenShot(driver, "Error in verifyIsheroImageAvailable");
					Assert.fail(browseText + " brand link is not working successfully on site :" + url
							+ " After clicking brands link, user navigates to : " + desiredUrl);
					System.out.println("Test case : FAILED !!");

				}
			}

		}
	}

	public void check_allLinksStatus(By locator, String url, String element) throws IOException, InterruptedException {
		SoftAssert softAssert = new SoftAssert();
		List<WebElement> links = driver.findElements(locator);
		int count = links.size();
		System.out.println("Total no of links are : " + count);

		if (count == 0) {
			Assert.fail(element + " not available on : " + url);
		}
		for (int i = 0; (i < count && i < 100); i++) {
			// System.out.println(i);
			List<WebElement> lists = driver.findElements(locator);
			WebElement list = lists.get(i);

			String browseText = list.getText();
			System.out.println(browseText);
			String desiredUrl = list.getAttribute("data-href");
			System.out.println("desired URL is: " + desiredUrl);
			// Thread.sleep(500);

			URL desiredurl = new URL(desiredUrl);

			HttpURLConnection.setFollowRedirects(false);
			HttpURLConnection httpURLConnection = (HttpURLConnection) desiredurl.openConnection();

			// We don't need to get data
			httpURLConnection.setRequestMethod("HEAD");
			httpURLConnection.setRequestProperty("User-Agent",
					"Mozilla/5.0 (Windows; U; Windows NT 6.0; en-US; rv:1.9.1.2) Gecko/20090729 Firefox/3.5.2 (.NET CLR 3.5.30729)");
			int responseCode = httpURLConnection.getResponseCode();

			// We only accept response code 200
			System.out.println("Getting Response Code : " + responseCode + " in " + desiredurl);
			if ((responseCode == HttpURLConnection.HTTP_OK) || (responseCode == HttpURLConnection.HTTP_MOVED_PERM)
					|| (responseCode == HttpURLConnection.HTTP_MOVED_TEMP)
							&& (!desiredUrl.isEmpty() && !(desiredUrl.equals(url)))) {
				System.out.println(browseText + " brand link is working successfully on site :" + url);
				System.out.println(" Page loaded successfully.....");
				System.out.println("Test case : Passed.. !!");
			} else {
				softAssert.assertTrue(
						responseCode == (HttpURLConnection.HTTP_OK) || responseCode == 301 || responseCode == 302,
						browseText + " link is not loaded successfully. " + responseCode + " Error Code found on : "
								+ desiredUrl);
				// Assert.fail(browseText +" brand link is not working
				// successfully on site :"+url+" After clicking link, user
				// navigates to : "+desiredUrl);
				System.out.println("Test case : Failed.. !!");
			}
		}
		softAssert.assertAll();
	}

	public void verifyBrowseByList1(By locator, String url) throws IOException, InterruptedException {

		List<WebElement> links = driver.findElements(locator);

		if (links.isEmpty()) {
			System.out.println("No links available");
		} else {
			for (WebElement list : links) {
				String browseText = list.getText();
				if (list.isDisplayed() && list.isEnabled()) {
					click(list);
					WebDriverCommonLib.waitForPageToBeLoad(driver);
					Thread.sleep(5000);

					String desiredUrl = driver.getCurrentUrl();
					System.out.println(
							"After clicking - " + browseText + " , Successfully navigates to desired web page!!");
					System.out.println("Desired URL: " + desiredUrl);
					// Thread.sleep(1000);
					driver.navigate().back();
					WebDriverCommonLib.waitForPageToBeLoad(driver);
					Thread.sleep(5000);

					if ((desiredUrl.contains(url) && (!desiredUrl.equals(url))
							|| desiredUrl.contains(Constant.FHM_HomePage) || desiredUrl.contains(Constant.RD_HomePage))
							&& !is404Page()) {
						APPLICATION_LOGS.debug(browseText + "  link is working successfully on site :" + url);
						System.out.println(browseText + "  link is working successfully on site :" + url);
						System.out.println("Test case : PASSED !!");
					} else {
						APPLICATION_LOGS.debug(browseText + "  link is not working successfully on site :" + url
								+ " After clicking link, user navigates to :  " + desiredUrl);
						System.out.println(browseText + "  link is not working successfully on site :" + url);
						WebDriverCommonLib.getScreenShot(driver, "Error in verifyIsheroImageAvailable");
						assertTrue(desiredUrl.contains(url), browseText + "  link is not working successfully on site :"
								+ url + " After clicking link, user navigates to : " + desiredUrl);
						System.out.println("Test case : FAILED !!");
					}
				}
			}
		}
	}

	public void verifyURL_status(By locator, String url) throws IOException, InterruptedException {
		SoftAssert softAssert = new SoftAssert();
		int links = driver.findElements(locator).size();
		// System.out.println("Total no of links in BrowseBY tag are: " +
		// links);

		if (links < 0) {
			System.out.println("No links available");
		} else {
			try {
				closeBounceExchange();
				closeBxPopup();
			} catch (Exception e) {
				// System.out.println("Bounce exchange popup not visible");
			}
			for (int i = 0; i < links; i++) {
				List<WebElement> lists = driver.findElements(locator);
				WebElement list = lists.get(i);
				String browseText = list.getText();
				list.getAttribute("href");
				Thread.sleep(500);

				Actions actions = new Actions(driver);
				actions.moveToElement(list).click().perform();

				// WebDriverCommonLib.waitForPageToBeLoad(driver);
				Thread.sleep(5000);
				String desiredUrl = driver.getCurrentUrl();
				System.out
						.println("After clicking - " + browseText + " , Successfully navigates to desired web page!!");
				System.out.println("Desired URL: " + desiredUrl);
				if (desiredUrl.equals(url)) {
					System.out.println("Element not clicked, trying clicking element again!!");
					click(list);
					Thread.sleep(2000);
				}
				if (desiredUrl.equals(url)) {
					System.out.println("click element - 3rd attempt!!");
					Thread.sleep(2000);
					click(list);
					Thread.sleep(2000);
				}

				final String ERROR_404_TEXT = "Error 404: Page not found";
				final String PAGE_TYPE_404_TEXT = "404Page";
				final String sourceCode = "Status Code: 503";
				String pageSource = driver.getPageSource();

				boolean flag = pageSource.contains(ERROR_404_TEXT) || pageSource.contains(PAGE_TYPE_404_TEXT)
						|| pageSource.contains(sourceCode);

				// Verify the Expected Key-Value is present in Page Source or
				// not
				if (((desiredUrl.contains(url) && (desiredUrl.equals(url)) != true)
						|| (desiredUrl.contains(Constant.FHM_HomePage) == true)
						|| (desiredUrl.contains(Constant.TOH_HomePage) == true)
						|| (desiredUrl.contains(Constant.RD_HomePage) == true)
						|| (desiredUrl.contains(Constant.HLT_HomePage) == true)
						|| (desiredUrl.contains(Constant.SRD_HomePage) == true)
						|| (desiredUrl.contains(Constant.RDC_HomePage) == true)
						|| (desiredUrl.contains(Constant.BHC_HomePage) == true)
						|| (desiredUrl.contains(Constant.BNB_HomePage) == true)) && flag == false) {
					APPLICATION_LOGS.debug(browseText + "  link is working successfully on site :" + url);
					System.out.println(browseText + "  link is working successfully on site :" + url);
					System.out.println("Test case : PASSED !!");
					driver.navigate().back();
					WebDriverCommonLib.waitForPageToBeLoad(driver);
					Thread.sleep(500);
				} else {
					driver.navigate().to(url);
					APPLICATION_LOGS.debug(browseText + "  link is not working successfully on site :" + url
							+ " After clicking link, user navigates to :  " + desiredUrl);
					System.out.println(browseText + "  link is not working successfully on site :" + url);
					WebDriverCommonLib.getScreenShot(driver, "Error in verifyIsheroImageAvailable");
					Assert.fail("link is not working successfully on site :" + url
							+ " After clicking link, user navigates to :  " + desiredUrl);
					System.out.println("Test case : FAILED !!");
				}
				// }
			}
		}
		// softAssert.assertAll();
	}

	public void verifyBrowseByList_TOH(By locator, String url) throws IOException, InterruptedException {

		int links = driver.findElements(locator).size();
		try {
			closeBounceExchange();
		} catch (Exception e) {
			// System.out.println("Bounce exchange popup not visible");
		}

		Assert.assertEquals(true, (links > 0 == true), locator + "  link is not working successfully on site :" + url);

		for (int i = 0; i <= links - 1; i++) {
			List<WebElement> lists = driver.findElements(locator);
			WebElement list = lists.get(i);
			String browseText = list.getText();
			Thread.sleep(500);
			Actions actions = new Actions(driver);
			actions.moveToElement(list).click().perform();

			Thread.sleep(6000);
			final String ERROR_404_TEXT = "Error 404: Page not found";
			final String PAGE_TYPE_404_TEXT = "404Page";
			final String sourceCode = "Status Code: 503";
			String pageSource = driver.getPageSource();

			boolean flag = pageSource.contains(ERROR_404_TEXT) || pageSource.contains(PAGE_TYPE_404_TEXT)
					|| pageSource.contains(sourceCode);

			String desiredUrl = driver.getCurrentUrl();
			System.out.println("After clicking - " + browseText + " , Successfully navigates to desired web page!!");
			System.out.println("Desired URL: " + desiredUrl);
			Thread.sleep(6000);
			driver.navigate().back();
			WebDriverCommonLib.waitForPageToBeLoad(driver);
			driver.get(url);
			WebDriverCommonLib.waitForPageToBeLoad(driver);

			// Verify the Expected Key-Value is present in Page Source or not
			if (((desiredUrl.contains(url) && (!desiredUrl.equals(url)) == true)
					|| (desiredUrl.contains(Constant.TOH_HomePage) == true) && flag == false)) {
				APPLICATION_LOGS.debug(browseText + "  link is working successfully on site :" + url);
				System.out.println(browseText + "  link is working successfully on site :" + url);
				System.out.println("Test case : PASSED !!");
			} else {
				APPLICATION_LOGS.debug(browseText + "  link is not working successfully on site :" + url
						+ " After clicking link, user navigates to :  " + desiredUrl);
				System.out.println(browseText + "  link is not working successfully on site :" + url);
				WebDriverCommonLib.getScreenShot(driver, "Error in verifyIsheroImageAvailable");
				Assert.fail("link is not working successfully on site :" + url
						+ " After clicking link, user navigates to :  " + desiredUrl);
				System.out.println("Test case : FAILED !!");
			}
		}
	}

	public void verifyBrowseByList_BNB(By locator, String url) throws IOException, InterruptedException {

		int links = driver.findElements(locator).size();
		try {
			closeBounceExchange();
		} catch (Exception e) {
			// System.out.println("Bounce exchange popup not visible");
		}

		Assert.assertEquals(true, (links > 0 == true), locator + "  link is not working successfully on site :" + url);

		for (int i = 0; i < links; i++) {
			List<WebElement> lists = driver.findElements(locator);
			WebElement list = lists.get(i);
			String browseText = list.getText();
			Thread.sleep(500);
			Actions actions = new Actions(driver);
			actions.moveToElement(list).click().perform();

			Thread.sleep(6000);
			final String ERROR_404_TEXT = "Error 404: Page not found";
			final String PAGE_TYPE_404_TEXT = "404Page";
			final String sourceCode = "Status Code: 503";
			String pageSource = driver.getPageSource();

			boolean flag = pageSource.contains(ERROR_404_TEXT) || pageSource.contains(PAGE_TYPE_404_TEXT)
					|| pageSource.contains(sourceCode);

			String desiredUrl = driver.getCurrentUrl();
			System.out.println("After clicking - " + browseText + " , Successfully navigates to desired web page!!");
			System.out.println("Desired URL: " + desiredUrl);
			Thread.sleep(6000);
			driver.navigate().back();
			WebDriverCommonLib.waitForPageToBeLoad(driver);
			driver.get(url);
			WebDriverCommonLib.waitForPageToBeLoad(driver);

			// Verify the Expected Key-Value is present in Page Source or not
			if (((desiredUrl.contains(url) && (!desiredUrl.equals(url)) == true)
					|| (desiredUrl.contains(Constant.TOH_HomePage) == true)
					|| (desiredUrl.contains(Constant.BNB_HomePage) == true) && flag == false)) {
				APPLICATION_LOGS.debug(browseText + "  link is working successfully on site :" + url);
				System.out.println(browseText + "  link is working successfully on site :" + url);
				System.out.println("Test case : PASSED !!");
			} else {
				APPLICATION_LOGS.debug(browseText + "  link is not working successfully on site :" + url
						+ " After clicking link, user navigates to :  " + desiredUrl);
				System.out.println(browseText + "  link is not working successfully on site :" + url);
				WebDriverCommonLib.getScreenShot(driver, "Error in verifyIsheroImageAvailable");
				Assert.fail("link is not working successfully on site :" + url
						+ " After clicking link, user navigates to :  " + desiredUrl);
				System.out.println("Test case : FAILED !!");
			}
		}
	}

	public void verifyBrowseByList(By locator, String url) throws IOException, InterruptedException {
		SoftAssert softAssert = new SoftAssert();
		int links = driver.findElements(locator).size();

		if (links < 0) {
			System.out.println("No links available");
		} else {
			try {
				closeBounceExchange();
			} catch (Exception e) {
				// System.out.println("Bounce exchange popup not visible");
			}
			for (int i = 0; i < links; i++) {
				List<WebElement> lists = driver.findElements(locator);
				WebElement list = lists.get(i);
				String browseText = list.getText();
				String desiredUrl = list.getAttribute("href");
				System.out.println("desired URL is: " + desiredUrl);
				Thread.sleep(500);

				
					URL desiredurl = new URL(desiredUrl); 

				   HttpURLConnection.setFollowRedirects(false);
				    HttpURLConnection httpURLConnection = (HttpURLConnection) desiredurl.openConnection();

				    // We don't need to get data
				    httpURLConnection.setRequestMethod("HEAD");

				    // Some websites don't like programmatic access so pretend to be a browser
				    httpURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 6.0; en-US; rv:1.9.1.2) Gecko/20090729 Firefox/3.5.2 (.NET CLR 3.5.30729)");
				    int responseCode = httpURLConnection.getResponseCode();

				    // We only accept response code 200
				    System.out.println( "Getting Response Code : " + responseCode + " in "+desiredurl );
				    if ((responseCode== HttpURLConnection.HTTP_OK) || (responseCode==HttpURLConnection.HTTP_MOVED_PERM) || (responseCode==HttpURLConnection.HTTP_MOVED_TEMP) && (desiredUrl.contains(Constant.FHM_HomePage) || desiredUrl.contains(Constant.TOH_HomePage) || desiredUrl.contains(Constant.RD_HomePage) || desiredUrl.contains(Constant.HLT_HomePage) || desiredUrl.contains(Constant.SRD_HomePage) || desiredUrl.contains(Constant.RDC_HomePage) || desiredUrl.contains(Constant.BHC_HomePage) || desiredUrl.contains(Constant.BNB_HomePage)))
				    {
				    	System.out.println(browseText +"  link is working successfully on site :"+url);						
						System.out.println(" Page loaded successfully.....");
				    	System.out.println("Test case : Passed.. !!");
				    }
				    else
				    {
				    	softAssert.assertTrue(responseCode==(HttpURLConnection.HTTP_OK) || responseCode==301 || responseCode==302, responseCode + " Error Code found on : " +url );
				    	Assert.fail(responseCode + " Error Code found on : " +desiredurl+"link is not working successfully on site :"+url);
				    	System.out.println("Test case : Failed.. !!");
				    }

			}
		}
		softAssert.assertAll();
	}

	public void verifyhamburgerMenus(By hamburgerMenuButtonLocator, By locator, By hamburgerMenuHavingChild,
			By hamburgerMenuNoChild, By hamburgerSubMenus1, By hamburgerSubMenusHavingChild, By backbtn, String url)
			throws IOException, InterruptedException {

		int navlink = driver.findElements(locator).size();
		System.out.println("Total no of menus in hamburger tab are: " + navlink);

		int navhavingchild = driver.findElements(hamburgerMenuHavingChild).size();
		System.out.println("Total no of menus having children in hamburger  tab are: " + navhavingchild);

		for (int i = 0; i < navhavingchild; i++) {
			List<WebElement> menus = driver.findElements(hamburgerMenuHavingChild);
			WebElement menu = menus.get(i);

			String navTitle = menu.getText();
			System.out.println("Clicking main menu option: " + navTitle);
			click(menu);
			Thread.sleep(1000);
			String desiredUrl = driver.getCurrentUrl();
			int menu1 = driver.findElements(hamburgerSubMenus1).size();
			System.out.println("Total no of menus in sub menu -  " + navTitle + " are :" + (menu1 - 1));

			int submenuhavingchild = driver.findElements(hamburgerSubMenusHavingChild).size();
			System.out.println(
					"Total no of menus having children in sub menu -  " + navTitle + " are :" + submenuhavingchild);

			for (int j = 1; (j < submenuhavingchild && j < 10); j++) {
				List<WebElement> list1 = driver.findElements(hamburgerSubMenusHavingChild);
				WebElement list_submenu = list1.get(j);
				String submenu = list_submenu.getText();

				System.out.println("Clicking child option : " + submenu);
				click(list_submenu);
				Thread.sleep(1000);

				List<WebElement> subchilds = driver.findElements(By.xpath("//ul[@class='sub-menu active']//li"));
				System.out.println("total no of sub childs are: " + (subchilds.size() - 1));
				WebElement backBtn = driver.findElement(backbtn);
				click(backBtn);
				WebDriverCommonLib.waitForElementToBeClickable(driver, list_submenu);
				Thread.sleep(1000);
			}

			WebElement backBtn = driver.findElement(backbtn);
			click(backBtn);
			WebDriverCommonLib.waitForElementToBeClickable(driver, menu);
			Thread.sleep(1000);

			if (desiredUrl.equals(url)) {
				APPLICATION_LOGS.debug(navTitle + "  link is working successfully on site :" + url);
				System.out.println(navTitle + "  link is working successfully on site :" + url);
				System.out.println("Test case : PASSED !!");
			} else {
				APPLICATION_LOGS.debug(navTitle + "  link is not working successfully on site :" + url);
				System.out.println(navTitle + "  link is not working successfully on site :" + url);
				WebDriverCommonLib.getScreenShot(driver, "Error in verifyIsheroImageAvailable");
				Assert.fail(navTitle + "  link is not working successfully on site :" + url
						+ " After clicking link, user navigates to : " + desiredUrl);
				System.out.println("Test case : FAILED !!");
			}
		}
	}

	public void verifySearchField(String url, By searchField, By searchIconlocator)
			throws IOException, InterruptedException {
		/*WebElement searchText = driver.findElement(searchField);
		// Thread.sleep(3000);
		searchText.sendKeys("Spotlight");*/
		// page.writeText(newsletterSignupInputField, "abc@gmail.com");
		//By searchBtn = By.xpath("//input[@class='search-button']");
		WebElement searchIcon = driver.findElement(searchIconlocator);
		click(searchIcon);
		WebElement searchText = driver.findElement(searchField);
		// Thread.sleep(3000);
		searchText.sendKeys("Spotlight");
		By searchBtn = By.xpath("//input[@class='search-button']");
		WebElement newSearch = driver.findElement(searchBtn);
		click(newSearch);
		System.out.println("Search button clicked!!");
		WebDriverCommonLib.waitForPageToBeLoad(driver);
		Thread.sleep(5000);

		String actualUrl = driver.getCurrentUrl();
		System.out.println("After click: " + actualUrl);
		final String ERROR_404_TEXT = "Error 404: Page not found";
		final String PAGE_TYPE_404_TEXT = "404Page";
		final String sourceCode = "Status Code: 503";
		String pageSource = driver.getPageSource();
		String search = "=Spotlight";
		
		boolean flag = pageSource.contains(ERROR_404_TEXT) || pageSource.contains(PAGE_TYPE_404_TEXT)
				|| pageSource.contains(sourceCode);

		if (actualUrl.contains(url) && actualUrl.contains(search) && !is404Page() && flag == false) {
			APPLICATION_LOGS.debug("Search Icon is working fine on site :" + url
					+ " After clicking search Icon, user navigates to :  " + actualUrl);
			System.out.println("search Icon is working successfully on site :" + url);
			System.out.println(" After clicking search Icon, user navigates to :  " + actualUrl);
			System.out.println("Test case : PASSED !!");
		} else {
			APPLICATION_LOGS.debug("Search Icon is not working successfully on site :" + url
					+ " After clicking search Icon, user navigates to :  " + actualUrl);
			System.out.println("Search Icon is not working successfully on site :" + url);
			System.out.println(" After clicking Search Icon, user navigates to :  " + actualUrl);
			WebDriverCommonLib.getScreenShot(driver, "Error in verifyIsheroImageAvailable");
			Assert.fail("Search Icon is not working successfully on site :" + url
					+ " After clicking Search Icon, user navigates to : " + actualUrl);
			System.out.println("Test case : FAILED !!");
		}

		driver.navigate().back();
	}

	public void verifyNavMainMenu(By locator, String url) throws IOException, InterruptedException {

		int links = driver.findElements(locator).size();
		// System.out.println("Total no of links in BrowseBY tag are: " +
		// links);

		for (int i = 0; i < links; i++) {
			// System.out.println(i);
			List<WebElement> lists = driver.findElements(locator);
			WebElement list = lists.get(i);
			String browseText = list.getText();
			if (list.isDisplayed() && list.isEnabled()) {
				click(list);
				WebDriverCommonLib.waitForPageToBeLoad(driver);
				Thread.sleep(2000);

				String desiredUrl = driver.getCurrentUrl();
				System.out
						.println("After clicking - " + browseText + " , Successfully navigates to desired web page!!");
				System.out.println("Desired URL: " + desiredUrl);
				final String ERROR_404_TEXT = "Error 404: Page not found";
				final String PAGE_TYPE_404_TEXT = "404Page";
				final String sourceCode = "Status Code: 503";
				String pageSource = driver.getPageSource();

				boolean flag = pageSource.contains(ERROR_404_TEXT) || pageSource.contains(PAGE_TYPE_404_TEXT)
						|| pageSource.contains(sourceCode);

				if (!(desiredUrl.isEmpty()) && !is404Page() && flag == false) {
					APPLICATION_LOGS.debug(browseText + "  link is working successfully on site :" + url);
					System.out.println(browseText + "  link is working successfully on site :" + url);
					System.out.println("Test case : PASSED !!");
				} else {
					APPLICATION_LOGS.debug(browseText + "  link is not working successfully on site :" + url
							+ " After clicking link, user navigates to :  " + desiredUrl);
					System.out.println(browseText + "  link is not working successfully on site :" + url);
					WebDriverCommonLib.getScreenShot(driver, "Error in verifyIsheroImageAvailable");
					Assert.fail(browseText + "  link is not working successfully on site :" + url
							+ " After clicking link, user navigates to : " + desiredUrl);
					System.out.println("Test case : FAILED !!");
				}
				// Thread.sleep(1000);
				driver.navigate().to(url);
				WebDriverCommonLib.waitForPageToBeLoad(driver);
				Thread.sleep(5000);
			}
		}
	}

	public void socialShareNavigation(By dekLocator, By locator, String url) throws IOException, InterruptedException {

		// driver.findElement(dekLocator);
		SoftAssert softAssert = new SoftAssert();
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(dekLocator));
		action.moveToElement(driver.findElement(locator));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");
		// System.out.println("element found");
		List<WebElement> links = driver.findElements(locator);
		int count = links.size();
		System.out.println("Total no of links in social share are: " + count);

		List<WebElement> lists = driver.findElements(locator);
		// System.out.println(lists);
		for (int i = 0; i < (count - 2); i++) {
			// System.out.println(i);
			// List<WebElement> lists = driver.findElements(locator);
			WebElement list = lists.get(i);
			String browseText = list.getText();
			System.out.println(browseText);
			/*
			 * if(browseText.contains("facebook") ||
			 * browseText.contains("twitter") || browseText.contains("youtube")
			 * || browseText.contains("flipboard") ||
			 * browseText.contains("pinterest")) {
			 */
			click(list);
			String parentWindow = driver.getWindowHandle();

			// Iterate through the collection of all available window handles
			for (String childWindowHandle : driver.getWindowHandles()) {

				// Ignore which handle is equal to the parent handle
				if (!childWindowHandle.equalsIgnoreCase(parentWindow)) {

					// Switch to the child window handle
					driver.switchTo().window(childWindowHandle);
					driver.manage().window().maximize();
					Thread.sleep(2000);
					String title = driver.getTitle();
					System.out.println(title);
					System.out.println(driver.getCurrentUrl());

					if (!is404Page() && ((driver.getCurrentUrl().contains("facebook.com")
							|| driver.getCurrentUrl().contains("flipboard.com")
							|| driver.getCurrentUrl().contains("twitter.com")
							|| driver.getCurrentUrl().contains("pinterest.com")))) {

						APPLICATION_LOGS.debug(title + " is working as expected on site :" + url);
						System.out.println(title + " is working as expected on site :" + url);
						System.out.println(
								"After clicking " + title + " , user navigates to :  " + driver.getCurrentUrl());
						System.out.println("Test case : PASSED !!");
					} else {
						APPLICATION_LOGS.debug(title + " is not working as expected on site :" + url);
						System.out.println(title + " is not working as expected on site :" + url);
						WebDriverCommonLib.getScreenShot(driver, "Error in verifySocialShareLinks");
						softAssert.assertTrue(driver.getCurrentUrl().contains(title),
								title + " is not working as expected on site :" + url);
						System.out.println("Test case : FAILED !!");
					}
					driver.close();

					// Switch back to the parent handle
					driver.switchTo().window(parentWindow);
				}
			}
		}
		softAssert.assertAll();

	}

	public void socialShareNavigation_copy(By dekLocator, By locator, String url)
			throws IOException, InterruptedException {

		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(dekLocator));
		action.moveToElement(driver.findElement(locator));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");
		// System.out.println("element found");
		List<WebElement> links = driver.findElements(locator);
		int count = links.size();
		System.out.println("Total no of links in social share are: " + count);

		List<WebElement> lists = driver.findElements(locator);
		// System.out.println(lists);
		for (int i = 0; i < (count); i++) {
			WebElement list = lists.get(i);
			String browseText = list.getText();
			System.out.println(browseText);
			String desiredUrl = list.getAttribute("href");
			System.out.println("desired URL is: " + desiredUrl);
			// Thread.sleep(500);

			URL desiredurl = new URL(desiredUrl);

			HttpURLConnection.setFollowRedirects(false);
			HttpURLConnection httpURLConnection = (HttpURLConnection) desiredurl.openConnection();

			// We don't need to get data
			httpURLConnection.setRequestMethod("HEAD");

			// Some websites don't like programmatic access so pretend to be a
			// browser
			httpURLConnection.setRequestProperty("User-Agent",
					"Mozilla/5.0 (Windows; U; Windows NT 6.0; en-US; rv:1.9.1.2) Gecko/20090729 Firefox/3.5.2 (.NET CLR 3.5.30729)");
			int responseCode = httpURLConnection.getResponseCode();

			// We only accept response code 200
			System.out.println("Getting Response Code : " + responseCode + " in " + desiredurl);
			if ((responseCode == HttpURLConnection.HTTP_OK)
					&& ((desiredUrl.contains("facebook.com") || desiredUrl.contains("flipboard.com")
							|| desiredUrl.contains("twitter.com") || desiredUrl.contains("pinterest.com")))) {

				APPLICATION_LOGS.debug(browseText + " is working as expected on site :" + url);
				System.out.println(browseText + " is working as expected on site :" + url);
				System.out
						.println("After clicking " + browseText + " , user navigates to :  " + driver.getCurrentUrl());
				System.out.println("Test case : PASSED !!");
			} else {
				APPLICATION_LOGS.debug(browseText + " is not working as expected on site :" + url);
				System.out.println(browseText + " is not working as expected on site :" + url);
				WebDriverCommonLib.getScreenShot(driver, "Error in verifySocialShareLinks");
				assertTrue(desiredUrl.contains(browseText), browseText + " is not working as expected on site :" + url);
				System.out.println("Test case : FAILED !!");
			}

		}
	}

	public void NavigationtoDiffTab(By locator, String url) throws IOException, InterruptedException {
		SoftAssert softAssert = new SoftAssert();
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(locator));
		List<WebElement> lists = driver.findElements(locator);
		// System.out.println(lists);
		int count = lists.size();
		System.out.println("Total no of posts are: " + count);
		for (int i = 0; i < (count); i++) {
			// System.out.println(i);
			// List<WebElement> lists = driver.findElements(locator);
			WebElement list = lists.get(i);
			String browseText = list.getText();
			System.out.println(browseText);
			click(list);
			String parentWindow = driver.getWindowHandle();

			// Iterate through the collection of all available window handles
			for (String childWindowHandle : driver.getWindowHandles()) {

				// Ignore which handle is equal to the parent handle
				if (!childWindowHandle.equalsIgnoreCase(parentWindow)) {

					// Switch to the child window handle
					driver.switchTo().window(childWindowHandle);
					// driver.manage().window().maximize();
					Thread.sleep(2000);
					String title = driver.getTitle();
					System.out.println(title);
					String desiredURL = driver.getCurrentUrl();
					System.out.println(desiredURL);
					driver.close();

					// Switch back to the parent handle
					driver.switchTo().window(parentWindow);

					if (!is404Page() && (desiredURL.contains(Constant.RD_HomePage))) {

						APPLICATION_LOGS.debug(title + " is working as expected on site :" + url);
						System.out.println(title + " is working as expected on site :" + url);
						System.out.println(
								"After clicking " + title + " , user navigates to :  " + driver.getCurrentUrl());
						System.out.println("Test case : PASSED !!");
					} else {
						APPLICATION_LOGS.debug(title + " is not working as expected on site :" + url);
						// System.out.println(title +" is not working as
						// expected on site :"+url);
						System.out.println(
								"Unexpected url : " + desiredURL + " after clicking on " + title + "  on site :" + url);
						WebDriverCommonLib.getScreenShot(driver, "Error in verifySocialShareLinks");
						softAssert.assertTrue(desiredURL.contains(title),
								title + " is not working as expected on site :" + url);
						System.out.println("Test case : FAILED !!");
					}
					/*
					 * driver.close();
					 * 
					 * //Switch back to the parent handle
					 * driver.switchTo().window(parentWindow);
					 */
				}
			}
		}
		softAssert.assertAll();
	}

	/**
	 * Determines if a link is working
	 * 
	 * @param locator
	 * @return true if the link leads to the desired URL and the link was
	 *         clicked, false otherwise
	 * @throws InterruptedException
	 */
	public boolean isLinkWorking(Link link) throws InterruptedException {
		boolean clickSuccess = click(link.getLocator());

		// TODO: wait mechanism - use webdriverwait

		// return if the link led to the correct place, and the click was
		// successful
		return driver.getCurrentUrl().equals(link.getDesiredUrl()) && clickSuccess;
	}

	/**
	 * Determines if the passed URL is a 404 page on a TMBI site. Only works for
	 * TMBI sites.
	 * 
	 * @param url
	 *            the url to check
	 * @return true if the page is a 404, false otherwise
	 */
	public boolean is404Page(String url) {
		driver.get(url);
		return is404Page();
	}

	/**
	 * Determines if the URL the WebDriver is currently on is a 404 page on a
	 * TMBI site. Only works for TMBI sites.
	 * 
	 * @return true if the page is a 404, false otherwise
	 */
	public boolean is404Page() {
		final String ERROR_404_TEXT = "Error 404: Page not found";
		final String PAGE_TYPE_404_TEXT = "404Page";
		final String sourceCode = "Status Code: 503";
		String pageSource = driver.getPageSource();

		boolean flag1 = pageSource.contains(ERROR_404_TEXT) || pageSource.contains(PAGE_TYPE_404_TEXT)
				|| pageSource.contains(sourceCode);
		return flag1;
	}

	public void loginToCMSApp(String username, String password) throws InterruptedException {
		// step 1 Login
		driver.findElement(By.name("log")).sendKeys(username);
		driver.findElement(By.name("pwd")).sendKeys(password);
		driver.findElement(By.name("wp-submit")).click();
		WebDriverCommonLib.waitForPageToBeLoad(driver);
		try {
			driver.findElement(By.xpath("//h1[@class='wp-heading-inline']")).isDisplayed();
		} catch (org.openqa.selenium.NoSuchElementException e) {
			Assert.fail("CMS Login Failed");
		}
	}

	public void verifyActivePugins(List<String> listOne, List<String> listTwo, String url) {
		SoftAssert softAssert = new SoftAssert();
		List<String> sourceList = new ArrayList<String>(listOne);
		List<String> destinationList = new ArrayList<String>(listTwo);

		sourceList.removeAll(listTwo);
		destinationList.removeAll(listOne);

		softAssert.assertTrue(sourceList.isEmpty(),"Recently Activated Plugin(s):" + sourceList + " in the URL:" + url);
		softAssert.assertTrue(destinationList.isEmpty(),"Recently Deactivated Plugin(s):" + destinationList + " in the URL:" + url);

		Reporter.log("The following plugins deactivated in the URL: " + url);
		int i = 1;

		for (String value : listTwo) {
			Reporter.log("Plugins:" + i + " " + value);
			i++;
		}

		softAssert.assertAll();

	}

	public List<String> getActivePluginsFromAPI(String pluginsFromAPI) {
		if (pluginsFromAPI.contains("{\"status\":404}")) {
			Assert.assertEquals(true, false, "Getting Error in API Request: " + pluginsFromAPI);
		}
		// Remove special character form API response
		pluginsFromAPI = pluginsFromAPI.replace("\\", "");
		pluginsFromAPI = pluginsFromAPI.replace("[", "");
		pluginsFromAPI = pluginsFromAPI.replace("]", "");
		pluginsFromAPI = pluginsFromAPI.replace("\"", "");
		APPLICATION_LOGS.debug("pluginsFromAPI: " + pluginsFromAPI);
		System.out.println("pluginsFromAPI: " + pluginsFromAPI);

		// Add actual plugins to List
		List<String> actualPlugins = new ArrayList<String>(Arrays.asList(pluginsFromAPI.split(",")));
		APPLICATION_LOGS.debug("Active plugin size fetched from UI: " + actualPlugins.size());
		System.out.println("Active plugin size fetched from UI: " + actualPlugins.size());

		return actualPlugins;

	}

	public void HamburgerMenuCats_subcatsErrors(By locator, String url, By hamburgerMenuButtonLocator, By mainCategory)
			throws IOException, InterruptedException {
		List<WebElement> links = driver.findElements(locator);
		if (links.size() == 0) {
			Assert.fail("The links xpath: " + locator + " is in correct for URL: " + url);
		}

		SoftAssert softAssert = new SoftAssert();
		WebDriverCommonLib.waitForPageToBeLoad(driver);
		Thread.sleep(3000);

		for (int i = 0; i < links.size(); i++) {
			try {
				closeBounceExchange();
			} catch (Exception e) {
				// System.out.println("Bounce exchange popup not visible");
			}
			List<WebElement> lists = driver.findElements(locator);
			WebElement list = lists.get(i);
			String browseText = list.getText();
			System.out.println("Browser Text: " + browseText);

			Actions actions = new Actions(driver);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,200)", "");
			Thread.sleep(1000);
			actions.moveToElement(list).click().perform();
			WebDriverCommonLib.waitForPageToBeLoad(driver);

			// System.out.println("After clicking - " +browseText +" ,
			// Successfully navigates to desired web page!!");
			Thread.sleep(5000);

			Set<String> allWindowHandles = driver.getWindowHandles();
			ArrayList<String> tabs = new ArrayList<String>(allWindowHandles);
			System.out.println("No. of tabs: " + tabs.size());
			String desiredUrl = null;

			final String ERROR_404_TEXT = "Error 404: Page not found";
			final String PAGE_TYPE_404_TEXT = "404Page";
			final String sourceCode = "Status Code: 503";
			String pageSource = driver.getPageSource();

			boolean flag = pageSource.contains(ERROR_404_TEXT) || pageSource.contains(PAGE_TYPE_404_TEXT)
					|| pageSource.contains(sourceCode);
			// System.out.println("Boolean status:"+flag);

			if (tabs.size() > 1) {
				System.out.println("Tab size is more than 1");

				driver.switchTo().window(tabs.get(1));
				WebDriverCommonLib.waitForPageToBeLoad(driver);
				desiredUrl = driver.getCurrentUrl();
				System.out.println(" Switced URL" + desiredUrl);
				softAssert.assertEquals(flag, false,
						browseText + " link is not working successfully on site :" + url + " Getting 404 error");

				driver.close();
				// System.out.println("closed 2nd window");
				driver.switchTo().window(tabs.get(0));
				// System.out.println("switched 1st window");

				driver.get(url);
				WebDriverCommonLib.waitForPageToBeLoad(driver);
			} else {
				WebDriverCommonLib.waitForPageToBeLoad(driver);
				Thread.sleep(2000);
				desiredUrl = driver.getCurrentUrl();
				softAssert.assertEquals(flag, false,
						browseText + " link is not working successfully on site :" + url + " Getting 404 error");
				// commented this code since URL comparison leads failure some
				// time when the link not contains the home URL
				// boolean status= desiredUrl.contains(url) &&
				// (!desiredUrl.equals(url));
				// System.out.println("desiredURL status: "+status);
				// softAssert.assertEquals(status, true, browseText+" link is
				// not working successfully on site :"+url+" After clicking
				// link, user navigates to : "+desiredUrl);

				driver.get(url);
				WebDriverCommonLib.waitForPageToBeLoad(driver);

			}

			driver.findElement(hamburgerMenuButtonLocator).click();
			Thread.sleep(3000);
			driver.findElement(mainCategory).click();

			System.out.println(
					"After clicking - " + browseText + " , Successfully navigates to desired web page!! " + desiredUrl);

		}
		driver.get(url);
		WebDriverCommonLib.waitForPageToBeLoad(driver);
		softAssert.assertAll();
	}

	public void HamburgerMenuCats_subcatsErrors(By locator, String url, By hamburgerMenuButtonLocator)
			throws IOException, InterruptedException {
		List<WebElement> links = driver.findElements(locator);
		if (links.size() == 0) {
			Assert.fail("The links xpath: " + locator + " is in correct for URL: " + url);
		}
		SoftAssert softAssert = new SoftAssert();
		WebDriverCommonLib.waitForPageToBeLoad(driver);
		Thread.sleep(3000);

		for (int i = 0; i < links.size(); i++) {
			try {
				closeBounceExchange();
			} catch (Exception e) {
				// System.out.println("Bounce exchange popup not visible");
			}
			List<WebElement> lists = driver.findElements(locator);
			WebElement list = lists.get(i);
			String browseText = list.getText();
			System.out.println("Browser Text: " + browseText);

			Actions actions = new Actions(driver);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,200)", "");
			Thread.sleep(1000);
			actions.moveToElement(list).click().perform();
			WebDriverCommonLib.waitForPageToBeLoad(driver);

			// System.out.println("After clicking - " +browseText +" ,
			// Successfully navigates to desired web page!!");
			Thread.sleep(5000);

			Set<String> allWindowHandles = driver.getWindowHandles();
			ArrayList<String> tabs = new ArrayList<String>(allWindowHandles);
			System.out.println("No. of tabs: " + tabs.size());
			String desiredUrl = null;

			final String ERROR_404_TEXT = "Error 404: Page not found";
			final String PAGE_TYPE_404_TEXT = "404Page";
			final String sourceCode = "Status Code: 503";
			String pageSource = driver.getPageSource();

			boolean flag = pageSource.contains(ERROR_404_TEXT) || pageSource.contains(PAGE_TYPE_404_TEXT)
					|| pageSource.contains(sourceCode);
			System.out.println("Boolean status:" + flag);

			if (tabs.size() > 1) {
				System.out.println("Tab size is more than 1");

				driver.switchTo().window(tabs.get(1));
				WebDriverCommonLib.waitForPageToBeLoad(driver);
				desiredUrl = driver.getCurrentUrl();
				// System.out.println(" Switced URL"+ desiredUrl);
				softAssert.assertEquals(flag, false, browseText + " link is not working successfully on site :" + url
						+ " After clicking link, user navigates to :  " + desiredUrl);

				driver.close();
				// System.out.println("closed 2nd window");
				driver.switchTo().window(tabs.get(0));
				// System.out.println("switched 1st window");

				driver.get(url);
				WebDriverCommonLib.waitForPageToBeLoad(driver);
			} else {
				WebDriverCommonLib.waitForPageToBeLoad(driver);
				Thread.sleep(2000);
				desiredUrl = driver.getCurrentUrl();
				softAssert.assertEquals(flag, false,
						browseText + " link is not working successfully on site :" + url + " Getting 404 error");
				// commented this code since URL comparison leads failure some
				// time when the link not contains the home URL
				// boolean status= desiredUrl.contains(url) &&
				// (!desiredUrl.equals(url));
				// System.out.println("desiredURL status: "+status);
				// softAssert.assertEquals(status, true, browseText+" link is
				// not working successfully on site :"+url+" After clicking
				// link, user navigates to : "+desiredUrl);

				driver.get(url);
				WebDriverCommonLib.waitForPageToBeLoad(driver);

			}

			driver.findElement(hamburgerMenuButtonLocator).click();
			Thread.sleep(3000);

			System.out.println(
					"After clicking - " + browseText + " , Successfully navigates to desired web page!! " + desiredUrl);

		}

		driver.get(url);
		WebDriverCommonLib.waitForPageToBeLoad(driver);
		softAssert.assertAll();
	}

	public void loginTOHSite(String username, String password) {
		driver.findElement(By
				.xpath("//div[@class='menu-upper-right-menu-container']/ul/li[@class='login-class menu-item menu-item-type-custom menu-item-object-custom']"))
				.click();
		WebDriverCommonLib.waitForPageToBeLoad(driver);
		driver.findElement(By.id("txtUser")).sendKeys(username);
		driver.findElement(By.id("txtPass")).sendKeys(password);
		driver.findElement(By.id("btnLoginMobile")).click();
		WebDriverCommonLib.waitForPageToBeLoad(driver);

	}

	public void HamburgerMenuCats_subcatsErrors(By locator, String url, By hamburgerMenuButtonLocator, By mainCategory,
			By subCategory) throws IOException, InterruptedException {
		List<WebElement> links = driver.findElements(locator);
		if (links.size() == 0) {
			Assert.fail("The links xpath: " + locator + " is in correct for URL: " + url);
		}

		SoftAssert softAssert = new SoftAssert();
		WebDriverCommonLib.waitForPageToBeLoad(driver);
		Thread.sleep(3000);

		for (int i = 0; i < links.size(); i++) {
			try {
				closeBounceExchange();
			} catch (Exception e) {
				// System.out.println("Bounce exchange popup not visible");
			}
			List<WebElement> lists = driver.findElements(locator);
			WebElement list = lists.get(i);
			String browseText = list.getText();
			System.out.println("Browser Text: " + browseText);

			Actions actions = new Actions(driver);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,200)", "");
			Thread.sleep(1000);
			actions.moveToElement(list).click().perform();
			WebDriverCommonLib.waitForPageToBeLoad(driver);

			// System.out.println("After clicking - " +browseText +" ,
			// Successfully navigates to desired web page!!");
			Thread.sleep(5000);

			Set<String> allWindowHandles = driver.getWindowHandles();
			ArrayList<String> tabs = new ArrayList<String>(allWindowHandles);
			System.out.println("No. of tabs: " + tabs.size());
			String desiredUrl = null;

			final String ERROR_404_TEXT = "Error 404: Page not found";
			final String PAGE_TYPE_404_TEXT = "404Page";
			final String sourceCode = "Status Code: 503";
			String pageSource = driver.getPageSource();

			boolean flag = pageSource.contains(ERROR_404_TEXT) || pageSource.contains(PAGE_TYPE_404_TEXT)
					|| pageSource.contains(sourceCode);
			// System.out.println("Boolean status:"+flag);

			if (tabs.size() > 1) {
				System.out.println("Tab size is more than 1");

				driver.switchTo().window(tabs.get(1));
				WebDriverCommonLib.waitForPageToBeLoad(driver);
				desiredUrl = driver.getCurrentUrl();
				System.out.println(" Switced URL" + desiredUrl);
				softAssert.assertEquals(flag, false,
						browseText + " link is not working successfully on site :" + url + " Getting 404 error");

				driver.close();
				// System.out.println("closed 2nd window");
				driver.switchTo().window(tabs.get(0));
				// System.out.println("switched 1st window");

				driver.get(url);
				WebDriverCommonLib.waitForPageToBeLoad(driver);
			} else {
				WebDriverCommonLib.waitForPageToBeLoad(driver);
				Thread.sleep(2000);
				desiredUrl = driver.getCurrentUrl();
				softAssert.assertEquals(flag, false,
						browseText + " link is not working successfully on site :" + url + " Getting 404 error");
				// commented this code since URL comparison leads failure some
				// time when the link not contains the home URL
				// boolean status= desiredUrl.contains(url) &&
				// (!desiredUrl.equals(url));
				// System.out.println("desiredURL status: "+status);
				// softAssert.assertEquals(status, true, browseText+" link is
				// not working successfully on site :"+url+" After clicking
				// link, user navigates to : "+desiredUrl);

				driver.get(url);
				WebDriverCommonLib.waitForPageToBeLoad(driver);

			}

			driver.findElement(hamburgerMenuButtonLocator).click();
			Thread.sleep(3000);
			driver.findElement(mainCategory).click();
			Thread.sleep(1000);
			driver.findElement(subCategory).click();

			System.out.println(
					"After clicking - " + browseText + " , Successfully navigates to desired web page!! " + desiredUrl);

		}
		driver.get(url);
		WebDriverCommonLib.waitForPageToBeLoad(driver);
		softAssert.assertAll();
	}

	public void verifyheaderCategories(By locator, String url, String element, String[] constantData)
			throws IOException, InterruptedException {
		SoftAssert softAssert = new SoftAssert();
		List<WebElement> links = driver.findElements(locator);
		int count = links.size();
		System.out.println("Actual " + element + " on site are : " + count);

		if (count == 0) {
			Assert.fail(element + " not available on site: " + url);
		}

		System.out.println("Expected " + element + " on site are : " + constantData.length);
		if (count != constantData.length) {
			Assert.fail("Test case : FAILED, Total no of " + element + "  are: " + count);
		}

		for (int i = 0; i < constantData.length; i++) {

			List<WebElement> lists = driver.findElements(locator);
			WebElement list = lists.get(i);

			String browseText = list.getText();
			System.out.println("Header Category : " + browseText);

			if (browseText.equals(constantData[i])) {
				System.out.println(browseText + " brand link is available on site :" + url);
				System.out.println("Test case : Passed.. !!");
			} else {
				Assert.fail("Test case : FAILED," + browseText + element + " Not available " + "expected element = "
						+ constantData[i]);
				System.out.println("Test case : FAILED !!");

			}
		}
		softAssert.assertAll();
	}

}
