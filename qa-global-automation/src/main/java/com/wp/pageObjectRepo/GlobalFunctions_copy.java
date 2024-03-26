package com.wp.pageObjectRepo;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.wp.genericLib.ConfigFileReader;
import com.wp.genericLib.WebDriverCommonLib;
import com.wp.genericLib.Link;




public class GlobalFunctions_copy {
	WebDriver driver;
	 public WebDriverWait wait;
	//Initialize object using page factory method 
	ConfigFileReader configFileReader;

	public Logger APPLICATION_LOGS = LogManager.getLogger("AppLogger");


	public GlobalFunctions_copy(WebDriver driver){ 
		this.driver=driver; 
	}




	//Common methods for RMS3.0 Home page

	public void navigateToRequiredSite(String url){
		//configFileReader= new ConfigFileReader();
		//String url= configFileReader.getDriverPath();		
		System.out.println("url: "+url);
		APPLICATION_LOGS.debug("url: "+url);
		driver.get(url);
		WebDriverCommonLib.waitForPageToBeLoad(driver);

	}

	public void verifyTmbi_ad_dataKeyValue(String url, String proerty, String siteId,String pageType,String template) throws IOException, InterruptedException{
		//Get page source		
		String pageSource=driver.getPageSource();
		//APPLICATION_LOGS.debug("Page Source= "+ pageSource);

		//Expected key-value from test data
		String expKeyValue= "tmbi_ad_data = {\"property\":\""+proerty+"\",\"siteId\":\""+siteId+"\",\"pageType\":\""+pageType+"\",\"template\":\""+template+"\"";
		APPLICATION_LOGS.debug("Expected Key-Value "+ expKeyValue);
		System.out.println("Expected Key-Value "+ expKeyValue);

		//Verify the Expected Key-Value is present in Page Source or not		
		if(pageSource.contains(expKeyValue)==true){
			APPLICATION_LOGS.debug("GPT Key values is present in Page Source: "+url+" Expected Key Value is:  "+expKeyValue);
			System.out.println("GPT Key values is present in Page Source: "+url+" Expected Key Value is:  "+expKeyValue);
			System.out.println("*************************************************************************************");
		}
		else{
			APPLICATION_LOGS.debug("GPT Key values is not present in Page Source: "+url+" Expected Key Value is:  "+expKeyValue);
			System.out.println("GPT Key values is not present in Page Source: "+url+" Expected Key Value is:  "+expKeyValue);
			System.out.println("*************************************************************************************");
			WebDriverCommonLib.getScreenShot(driver, "Error in verifyTmbi_ad_dataKeyValue");
			assertTrue(pageSource.contains(expKeyValue),"GPT Key values is not present in Page Source: "+url+" Expected Key Value is:  "+expKeyValue);
		}
	}

	public void verifyTmbi_ad_dataKeyValue(String proerty, String siteId,String pageType,String template) throws IOException, InterruptedException{
		//Get page source		
		String pageSource=driver.getPageSource();
		//APPLICATION_LOGS.debug("Page Source= "+ pageSource);

		//Expected key-value from test data
		String expKeyValue= "tmbi_ad_data = {\"property\":\""+proerty+"\",\"siteId\":\""+siteId+"\",\"pageType\":\""+pageType+"\",\"template\":\""+template+"\"";
		APPLICATION_LOGS.debug("Expected Key-Value "+ expKeyValue);

		//Verify the Expected Key-Value is present in Page Source or not		

		if(pageSource.contains(expKeyValue)==true){
			APPLICATION_LOGS.debug("GPT Key values is present in Page Source. Expected Key Value is:  "+expKeyValue);

		}
		else{
			APPLICATION_LOGS.debug("GPT Key values is not present in Page Source. Expected Key Value is:  "+expKeyValue);
			WebDriverCommonLib.getScreenShot(driver, "Error in verifyTmbi_ad_dataKeyValue");
			assertTrue(pageSource.contains(expKeyValue),"GPT Key values is not present in Page Source. Expected Key Value is:  "+expKeyValue);

		}
	}

	public void verifyAmazonHeaderIsRunning(String url) throws IOException{
		//Get page source
		String pageSource=driver.getPageSource();
		//APPLICATION_LOGS.debug("Page Source= "+ pageSource);

		//Expected key-value from test data
		String expKeyValue="";
		if(url.contains(".ca/")){
			String[] splitURL= url.split("ca/");
			String modifiedURL= splitURL[0];

			expKeyValue=""+modifiedURL+"ca/wp-content/plugins/tmbi-amazon-a9/js/tmbi-amazon-a9.js";
			System.out.println("Expected Key-Value "+ expKeyValue);
			APPLICATION_LOGS.debug("Expected Key-Value "+ expKeyValue);

		}
		else{
			String[] splitURL= url.split("com/");
			String modifiedURL= splitURL[0];

			expKeyValue=""+modifiedURL+"com/wp-content/plugins/tmbi-amazon-a9/js/tmbi-amazon-a9.js";
			System.out.println("Expected Key-Value "+ expKeyValue);
			APPLICATION_LOGS.debug("Expected Key-Value "+ expKeyValue);
		}

		//Verify the Expected Key-Value is present in Page Source or not		
		if(pageSource.contains(expKeyValue)==true){
			APPLICATION_LOGS.debug("Amazon header bidder present in page source.Expected Key Value is:  "+expKeyValue);

		}
		else{
			APPLICATION_LOGS.debug("Amazon header bidder is not present in page source.Expected Key Value is:  "+expKeyValue);
			WebDriverCommonLib.getScreenShot(driver, "Error in verifyAmazonHeaderIsRunning");
			assertTrue(pageSource.contains(expKeyValue),"Amazon header bidder is not present in page source.Expected Key Value is:  "+expKeyValue);

		}
	}

	//for verifying Prebid header in all brands : WPQA69
	public void verifyPrebidHeaderIsRunning(String url) throws IOException{
		//Get page source
		String pageSource=driver.getPageSource();
		//APPLICATION_LOGS.debug("Page Source= "+ pageSource);

		//Expected key-value from test data
		String expKeyValue="";
		if(url.contains(".ca/")){
			String[] splitURL= url.split("ca/");
			String modifiedURL= splitURL[0];

			expKeyValue=""+modifiedURL+"ca/wp-content/plugins/prebidjs/js/prebid.js";
			APPLICATION_LOGS.debug("Expected Key-Value "+ expKeyValue);

		}
		else{
			String[] splitURL= url.split("com/");
			String modifiedURL= splitURL[0];

			expKeyValue=""+modifiedURL+"com/wp-content/plugins/prebidjs/js/prebid.js";
			APPLICATION_LOGS.debug("Expected Key-Value "+ expKeyValue);

		}

		//Verify the Expected Key-Value is present in Page Source or not		
		if(pageSource.contains(expKeyValue)==true){
			APPLICATION_LOGS.debug("Prebid header bidder is  present in page source.Expected Key Value is:  "+expKeyValue);
			System.out.println("Prebid header bidder:- " +expKeyValue+ " is present in Page Source: "+url);
			System.out.println("*************************************************************************************");
		}
		else{
			APPLICATION_LOGS.debug("Prebid header bidder is not  present in page source.Expected Key Value is:  "+expKeyValue);
			System.out.println("Prebid header bidder is not present in Page Source: "+url+" Expected Key Value is:  "+expKeyValue);
			System.out.println("*************************************************************************************");
			WebDriverCommonLib.getScreenShot(driver, "Error in verifyPrebidHeaderIsRunning");
			assertTrue(pageSource.contains(expKeyValue),"Prebid header bidder is not present in page source.Expected Key Value is:  "+expKeyValue);

		}

	}


	/*public void verifyPrebidHeaderIsRunning(String url) throws IOException{
		//Get page source
		String pageSource=driver.getPageSource();
		//APPLICATION_LOGS.debug("Page Source= "+ pageSource);

		//Expected key-value from test data
		String expKeyValue="";
		if(url.contains(".ca/")){
			String[] splitURL= url.split("ca/");
			String modifiedURL= splitURL[0];
			expKeyValue=""+modifiedURL+"ca/wp-content/plugins/prebidjs/js/prebid.js?ver=1.2.1";
			APPLICATION_LOGS.debug("Expected Key-Value "+ expKeyValue);

		}
		else{
			String[] splitURL= url.split("com/");
			String modifiedURL= splitURL[0];
			expKeyValue=""+modifiedURL+"com/wp-content/plugins/prebidjs/js/prebid.js?ver=1.2.1";
			APPLICATION_LOGS.debug("Expected Key-Value "+ expKeyValue);

		}

		//Verify the Expected Key-Value is present in Page Source or not		
		if(pageSource.contains(expKeyValue)==true){
			APPLICATION_LOGS.debug("Prebid header bidder is  present in page source.Expected Key Value is:  "+expKeyValue);

		}
		else{
			APPLICATION_LOGS.debug("Prebid header bidder is not  present in page source.Expected Key Value is:  "+expKeyValue);
			WebDriverCommonLib.getScreenShot(driver, "Error in verifyPrebidHeaderIsRunning");
			assertTrue(pageSource.contains(expKeyValue),"Prebid header bidder is not present in page source.Expected Key Value is:  "+expKeyValue);

		}

	}*/
	
	public void verifyIsBreadCrumbAvailable(By locator, String url) throws IOException, InterruptedException{
		
		 WebElement breadcrumb = driver.findElement(locator);
		 WebDriverCommonLib.waitForElementToBeClickable(driver, breadcrumb);
  	   try
  	   {            		   
  		   breadcrumb.click();
  	   }
 		catch(Exception e){
 			System.out.println("breadcrumb not present");
 			APPLICATION_LOGS.info("breadcrumb not present");
 		}
 		//assertEquals(driver.getCurrentUrl(), page.getUrl());
  	   String breadcrumText = breadcrumb.getText();

		//Verify the Expected Key-Value is present in Page Source or not			
		if(driver.getCurrentUrl().equals(url)==true){
			APPLICATION_LOGS.debug("Breadcrumb is available on site :"+url+" Expected Key Value is:  "+breadcrumText);
			System.out.println("Breadcrumb is available on site :"+url+" Expected Key Value is:  "+breadcrumText);
			System.out.println("Test case : PASSED !!");
		}
		else{
			APPLICATION_LOGS.debug("IBreadcrumb is not available on site :"+url+" Expected Key Value is:  "+breadcrumText);
			System.out.println("Breadcrumb is not available on site :"+url+" Expected Key Value is:  "+breadcrumText);
			WebDriverCommonLib.getScreenShot(driver, "Error in verifyIsBreadCrumbAvailable");
			assertTrue(driver.getCurrentUrl().equals(url),"Breadcrumb is not available on site :"+url+" Expected Key Value is: "+breadcrumText);
			System.out.println("Test case : FAILED !!");
		}
  	   
     }
     
	public void verifyIsTitleAvailable(By locator, String url) throws IOException, InterruptedException{
		
		 WebElement title = driver.findElement(locator);
 	   try
 	   {            		   
 		   title.isDisplayed();
 	   }
		catch(Exception e){
			//System.out.println("The Hide/Show console not present");
			APPLICATION_LOGS.info("title not present");
		}
		//assertEquals(driver.getCurrentUrl(), page.getUrl());
 	   String titleText = title.getText();

		//Verify the Expected Key-Value is present in Page Source or not			
		if(title.isDisplayed()==true){
			APPLICATION_LOGS.debug("title is available on site :"+url+" Expected Key Value is:  "+titleText);
			System.out.println("Title is available on site :"+url+" Expected Key Value is:  "+titleText);
			System.out.println("Test case : PASSED !!");

		}
		else{
			APPLICATION_LOGS.debug("Title is not available on site :"+url+" Expected Key Value is:  "+titleText);
			System.out.println("Title is not available on site :"+url+" Expected Key Value is:  "+titleText);
			WebDriverCommonLib.getScreenShot(driver, "Error in verifyIsTitleAvailable");
			assertTrue(title.isDisplayed(),"Title is not available on site :"+url+" Expected Key Value is: "+titleText);
			System.out.println("Test case : FAILED !!");
		}
 	   
    }
	
	public void verifyIsArchieveDescAvailable(By locator, String url) throws IOException, InterruptedException{
		
		 WebElement archieveDesc = driver.findElement(locator);
	   try
	   {   
		           		   
		  if(archieveDesc.isDisplayed())
		  {
			  String archieveText = archieveDesc.getText();
			  APPLICATION_LOGS.debug("Archieve description is available on site :"+url+" Expected Archieve description is:  "+archieveText);
			  System.out.println("Archieve description is available on site :"+url+" Expected Archieve description is :  "+archieveText);
			  System.out.println("Test case : PASSED !!");
		  }
		  else{
			  String archieveText = archieveDesc.getText();
				APPLICATION_LOGS.debug("Archieve description is not available on site :"+url+" Expected Archieve description is:  "+archieveText);
				System.out.println("Archieve description is not available on site :"+url+" Expected Archieve description is:  "+archieveText);
				WebDriverCommonLib.getScreenShot(driver, "Error in verifyIsArchieveDescAvailable");
				assertTrue(archieveDesc.isDisplayed(),"Archieve description is not available on site :"+url+" Expected Archieve description is: "+archieveText);
				System.out.println("Test case : FAILED !!");
			}
	   }
		catch(Exception e){
			//System.out.println("The Hide/Show console not present");
			APPLICATION_LOGS.info("archieve description not present");
		}
		//assertEquals(driver.getCurrentUrl(), page.getUrl());
	   /*String archieveText = archieveDesc.getText();

		//Verify the Expected Key-Value is present in Page Source or not			
		if(driver.getCurrentUrl().equals(url)==true){
			APPLICATION_LOGS.debug("Archieve description is available on site :"+url+" Expected Key Value is:  "+archieveText);
			System.out.println("Archieve description is available on site :"+url+" Expected Key Value is:  "+archieveText);
			System.out.println("Test case : PASSED !!");
		}
		else{
			APPLICATION_LOGS.debug("Archieve description is not available on site :"+url+" Expected Key Value is:  "+archieveText);
			System.out.println("Archieve description is not available on site :"+url+" Expected Key Value is:  "+archieveText);
			WebDriverCommonLib.getScreenShot(driver, "Error in verifyIsArchieveDescAvailable");
			assertTrue(archieveDesc.isDisplayed(),"Archieve description is not available on site :"+url+" Expected Key Value is: "+archieveText);
			System.out.println("Test case : FAILED !!");
		}*/
	   
   }
	protected boolean matchTabUrl(String targetUrl){
		//save current window handle to switch back to later
		String currentHandle = driver.getWindowHandle();

		//get all tab handles
		List<String> handles = new ArrayList<String>(driver.getWindowHandles());

		for(String handle : handles) {
			driver.switchTo().window(handle);
			
			WebDriverCommonLib.waitForPageToBeLoad(driver);

			//found a match
			if(driver.getCurrentUrl().contains(targetUrl)) {
				//make sure we dont close our own tab
				if(!currentHandle.equals(handle)) {
					driver.close();
					driver.switchTo().window(currentHandle);
				}
				return true;
			}
		}

		driver.switchTo().window(currentHandle);
		return false;
	}
	//List<WebElement> list = driver.findElements(By.xpath("//div[@class='archive-description']"));
	//assertTrue(list.size() > 0, "Text not found!"); verifyIsheroImageAvailable
	public void verifyIsLinkWorking(By locator, String url) throws IOException, InterruptedException{
	
		WebElement link = driver.findElement(locator);
		WebDriverCommonLib.waitForElementToBeClickable(driver, link);
		
		System.out.println("Before click: " + driver.getCurrentUrl());
		//System.out.println("Desired Url: " + link.getDesiredUrl());
		try
	  	   {            		   
			link.click();
			 System.out.println("Link clicked");
			 
	  	   }
	 		catch(Exception e){
	 			//System.out.println("The Hide/Show console not present");
	 			APPLICATION_LOGS.info("Link not present");
	 		}
		 
		
		
	}
	
		public void verifyIsHeroImageWorking(By locator, String url) throws IOException, InterruptedException{
		
				verifyIsLinkWorking(locator, url);
				
				WebDriverCommonLib.waitForPageToBeLoad(driver);
				Thread.sleep(5000);
			
				String desiredUrl = driver.getCurrentUrl();
				System.out.println("After click: " + desiredUrl);
				driver.navigate().back();
			//Verify the Expected Key-Value is present in Page Source or not			
			if(desiredUrl.contains(url)==true){
				APPLICATION_LOGS.debug("Hero Image is available and working successfully on site :"+url+" After clicking Hero image, user navigates to :  "+desiredUrl);
				System.out.println("Hero Image is available and working successfully on site :"+url+" After clicking Hero image, user navigates to :  "+desiredUrl);
				System.out.println("Test case : PASSED !!");
			}
			else{
				APPLICATION_LOGS.debug("Hero Image is not available and working successfully on site :"+url+" After clicking Hero image, user navigates to :  "+desiredUrl);
				System.out.println("Hero Image is not available and working successfully on site :"+url+" After clicking Hero image, user navigates to :  "+desiredUrl);
				WebDriverCommonLib.getScreenShot(driver, "Error in verifyIsheroImageAvailable");
				assertTrue(desiredUrl.contains(url),"Hero Image is not available and working successfully on site :"+url+" After clicking Hero image, user navigates to : "+desiredUrl);
				System.out.println("Test case : FAILED !!");
			}
			
		}
	   
	public void verifyIsHeroLinkWorking(By locator, String url) throws IOException, InterruptedException
	{
		verifyIsLinkWorking(locator, url);
		WebDriverCommonLib.waitForPageToBeLoad(driver);
		Thread.sleep(5000);
		
		String desiredUrl = driver.getCurrentUrl();
		System.out.println("After click: " + desiredUrl);
		driver.navigate().back();
		
		//Verify the Expected Key-Value is present in Page Source or not			
		if(desiredUrl.contains(url)==true){
			APPLICATION_LOGS.debug("Hero link is available and working successfully on site :"+url+" After clicking Hero link, user navigates to :  "+desiredUrl);
			System.out.println("Hero link is available and working successfully on site :"+url);
			System.out.println(" After clicking Hero link, user navigates to :  "+desiredUrl);
			System.out.println("Test case : PASSED !!");
		}
		else{
			APPLICATION_LOGS.debug("Hero link is not available and working successfully on site :"+url+" After clicking Hero link, user navigates to :  "+desiredUrl);
			System.out.println("Hero link is not available and working successfully on site :"+url);
			System.out.println(" After clicking Hero link, user navigates to :  "+desiredUrl);
			WebDriverCommonLib.getScreenShot(driver, "Error in verifyIsheroImageAvailable");
			assertTrue(desiredUrl.contains(url),"Hero link is not available and working successfully on site :"+url+" After clicking Hero link, user navigates to : "+desiredUrl);
			System.out.println("Test case : FAILED !!");
		}
		
	}
	

	
	public void verifyNewsletterSignUp(String url, By locator, String newspaperLink) throws IOException, InterruptedException
	{
		 	//wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		 	WebElement element = driver.findElement(locator);
		 	Thread.sleep(3000);
		 	element.sendKeys("abc@gmail.com");
    		//page.writeText(newsletterSignupInputField, "abc@gmail.com");
		 	verifyIsLinkWorking(locator, url);
		 	
		 	//Thread.sleep(5000);
			//driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
			WebDriverCommonLib.waitForPageToBeLoad(driver);
			Thread.sleep(5000);
			
			String actualUrl = driver.getCurrentUrl();
			System.out.println("After click: " + actualUrl);
			//System.out.println("Result will be: " + driver.getCurrentUrl().equals(link.getDesiredUrl()));
			
			//Verify the Expected Key-Value is present in Page Source or not			
			if(newspaperLink.equals(actualUrl)==true){
				APPLICATION_LOGS.debug("Newspaper link is working fine on site :"+url+" After clicking Newspaper link, user navigates to :  "+actualUrl);
				System.out.println("Newspaper link is working successfully on site :"+url);
				System.out.println(" After clicking Newspaper link, user navigates to :  "+actualUrl);
				System.out.println("Test case : PASSED !!");
			}
			else{
				APPLICATION_LOGS.debug("Newspaper link is not working successfully on site :"+url+" After clicking Newspaper link, user navigates to :  "+actualUrl);
				System.out.println("Newspaper link is not working successfully on site :"+url+" After clicking Newspaper link, user navigates to :  "+actualUrl);
				System.out.println(" After clicking Newspaper link, user navigates to :  "+actualUrl);
				WebDriverCommonLib.getScreenShot(driver, "Error in verifyIsheroImageAvailable");
				assertTrue(newspaperLink.equals(actualUrl),"Newspaper link is not working successfully on site :"+url+" After clicking Newspaper link, user navigates to : "+actualUrl);
				System.out.println("Test case : FAILED !!");
			}
			
			driver.navigate().back();
			
    		//matchTabUrl("https://www.constructionprotips.com/newsletters/");
	}
	
	public void verifyDiyuLogo(String url, By locator, String diyuLogoLink) throws IOException, InterruptedException
	{
		WebElement element = driver.findElement(locator);
		//wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			Thread.sleep(5000);	
		//page.scrollToElement(locator);
		//page.click(locator);
		Actions action = new Actions(driver);
		action.moveToElement(element).click().perform();
		//Thread.sleep(5000);
		//driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		WebDriverCommonLib.waitForPageToBeLoad(driver);
		Thread.sleep(5000);
		String actualUrl = driver.getCurrentUrl();
		System.out.println("After click: " + actualUrl);
		
		matchTabUrl(diyuLogoLink);
	
			//Verify the Expected Key-Value is present in Page Source or not			
			if(matchTabUrl(diyuLogoLink)==true){
				APPLICATION_LOGS.debug("Diyu link is working fine on site :"+url+" After clicking Diyu link, user navigates to :  "+actualUrl);
				System.out.println("Diyu link is working successfully on site :"+url);
				System.out.println(" After clicking Diyu link, user navigates to :  "+actualUrl);
				System.out.println("Test case : PASSED !!");
			}
			else{
				APPLICATION_LOGS.debug("Diyu link is not working successfully on site :"+url+" After clicking Diyu link, user navigates to :  "+actualUrl);
				System.out.println("Diyu link is not working successfully on site :"+url+" After clicking Diyu link, user navigates to :  "+actualUrl);
				System.out.println(" After clicking Diyu link, user navigates to :  "+actualUrl);
				WebDriverCommonLib.getScreenShot(driver, "Error in verifyIsheroImageAvailable");
				assertTrue(matchTabUrl(diyuLogoLink),"Diyu link is not working successfully on site :"+url+" After clicking Diyu link, user navigates to : "+actualUrl);
				System.out.println("Test case : FAILED !!");
			}
			
			driver.navigate().back();
			
    		//matchTabUrl("https://www.constructionprotips.com/newsletters/");
	}
	
	public void verifyIsFeaturedModuleWorking(String url, Link link) throws IOException, InterruptedException
	{
		
			By locator = link.getLocator();
			WebElement element = driver.findElement(locator);
			WebDriverCommonLib.waitForElementToBeClickable(driver, element);
			
			System.out.println("Locator: " + locator);
			
			System.out.println("Before click: " + driver.getCurrentUrl());
			
			Actions action = new Actions(driver);
			action.moveToElement(element).click().perform();
			//Thread.sleep(5000);
			//driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
			WebDriverCommonLib.waitForPageToBeLoad(driver);
			Thread.sleep(5000);
			
			String desiredUrl = driver.getCurrentUrl();
			System.out.println("After click, Successfully navigates to desired web page!!");
			System.out.println("Desired URL: " + desiredUrl);
			
			//Thread.sleep(1000);
			driver.navigate().back();
			WebDriverCommonLib.waitForPageToBeLoad(driver);
			Thread.sleep(5000);
			
			//Verify the Expected Key-Value is present in Page Source or not			
			if(driver.getCurrentUrl().equals(url)==true){
				APPLICATION_LOGS.debug("Feature module link is working successfully on site :"+url+" After clicking featured module link, user navigates to :  "+desiredUrl);
				System.out.println("Feature module link is working successfully on site :"+url+" After clicking Feature module link, user navigates to :  "+desiredUrl);
				System.out.println("Test case : PASSED !!");
			}
			else{
				APPLICATION_LOGS.debug("Feature module link is not working successfully on site :"+url+" After clicking Feature module link, user navigates to :  "+desiredUrl);
				System.out.println("Feature module link is not working successfully on site :"+url+" After clicking Feature module link, user navigates to :  "+desiredUrl);
				WebDriverCommonLib.getScreenShot(driver, "Error in verifyIsheroImageAvailable");
				assertTrue(desiredUrl.contains(url),"Feature module link is not working successfully on site :"+url+" After clicking Feature module link, user navigates to : "+desiredUrl);
				System.out.println("Test case : FAILED !!");
			}
			//assertTrue(isfeaturedModulesWorking(link));
		}
		
	public void verifyPagination(String url, By locator) throws InterruptedException {
		List<WebElement> pagination = driver.findElements(locator);
        int s = pagination.size();
        System.out.println("Total no of pages are: " +s);
        for(int i=0;i<=s;i++){
            //this.getAuthors();
                driver.get(url);
                Thread.sleep(5000);

            //pagination = driver.findElements(By.xpath("//div[@class='pagination-container']//a"));
            pagination.get(i).click();
            Thread.sleep(5000);
            String desiredUrl = driver.getCurrentUrl();
			System.out.println("After click, Successfully navigates to desired web page!!");
			System.out.println("Desired URL: " + desiredUrl);
			
            
        }
		
	}
	
	   
  	public void verifyIndexExchangeHeaderBidderIsRunning(String url) throws IOException, InterruptedException{
		//Get page source
		String pageSource=driver.getPageSource();

		//Expected key-value from test data		
		String expKeyValue= "js-sec.indexww.com\\/ht\\/p\\/184414";

		APPLICATION_LOGS.debug("Expected Key-Value "+ expKeyValue);
		System.out.println("Expected Key-Value "+ expKeyValue);

		//Verify the Expected Key-Value is present in Page Source or not			
		if(pageSource.contains(expKeyValue)==true){
			APPLICATION_LOGS.debug("Index Exchange header bidder is running on site :"+url+" Expected Key Value is:  "+expKeyValue);
			System.out.println("Index Exchange header bidder is running on site :"+url+" Expected Key Value is:  "+expKeyValue);

		}
		else{
			APPLICATION_LOGS.debug("Index Exchange header bidder is not running on site :"+url+" Expected Key Value is:  "+expKeyValue);
			System.out.println("Index Exchange header bidder is not running on site :"+url+" Expected Key Value is:  "+expKeyValue);
			WebDriverCommonLib.getScreenShot(driver, "Error in verifyIndexExchangeHeaderBidderIsRunning");
			assertTrue(pageSource.contains(expKeyValue),"Index Exchange header bidder is not running on site :"+url+" Expected Key Value is: "+expKeyValue);

		}

	}


	public void switchToFrame() throws Exception{

		Thread.sleep(5000);
		try{
			driver.findElement(By.id("google_pubconsole_mbutton")).click();


		}
		catch(Exception e){
			//System.out.println("The Hide/Show console not present");
			APPLICATION_LOGS.info("The Hide/Show console not present");
		}
		Thread.sleep(5000);
		List<WebElement> iframes = driver.findElements(By.xpath("//iframe[@width='100%']"));
		//System.out.println(iframes);
		for(int i=0;i<=iframes.size()-1;i++){
			String frameID=	iframes.get(i).getAttribute("id");
			//System.out.println("text: "+ frameID);
			APPLICATION_LOGS.info("text: "+ frameID);


			driver.switchTo().frame(frameID);



			if(driver.findElements(By.xpath("//div[contains(text(),'Page Request')]")).size() != 0){
				driver.findElement(By.xpath("//div[contains(text(),'Page Request')]")).click();
				//System.out.println(" X path  found in iframe. Driver switched successfully ");
				APPLICATION_LOGS.info(" X path  found in iframe. Driver switched successfully ");
				break;


			}
			else{
				driver.switchTo().defaultContent();

			}


		}


	}



	public void verifyKeyValueAttributesGoogfc(String url, String expectedKey) throws IOException, InterruptedException{
		Thread.sleep(3000);
		//Get the actual string from UI based on Key provided
		List<WebElement> actalValues= driver.findElements(By.xpath("//td[contains(text(),'"+expectedKey+"')]"));
		if(actalValues.size()==0){
			Assert.assertEquals(false, true,"The Key: "+ expectedKey+" is not found in the URL: "+url);
		}
		for (int i=0;i<=actalValues.size()-1; i++){
			String actalValue= actalValues.get(i).getText();

			APPLICATION_LOGS.debug("actalValue: "+actalValue);
			System.out.println("actalValue: "+actalValue);

			// Fetch the value between value and for string
			Pattern pattern = Pattern.compile("value(.*?)for", Pattern.DOTALL);
			Matcher matcher = pattern.matcher(actalValue);
			matcher.find();
			String str= matcher.group(1);
			APPLICATION_LOGS.debug("Key-value fetched from UI: "+str);
			System.out.println("Key-value fetched from UI: "+str);

			//Identify the key has value or not
			//boolean text= str.matches(".*[a-z].*");
			//boolean capstext= str.matches(".*[A-Z].*");
			//	boolean number=str.matches(".*[0-9].*");
			//	boolean specialCharacter=str.matches(".*/.*");
			boolean text = str.matches(".*[a-zA-Z0-9/].*");
			//System.out.println("text1: "+text1);
			System.out.println("text: "+text);
			//	System.out.println("number: "+number);
			//	System.out.println("special Character: "+specialCharacter);


			//if(text==true || capstext==true || number==true || specialCharacter==true)
			if(text==true){
				APPLICATION_LOGS.debug("The Key :"+ expectedKey +" has a value: "+str +" in the URL: "+ url);
				System.out.println("The Key :"+ expectedKey +" has a value: "+str +" in the URL: "+ url );


			}
			else{
				APPLICATION_LOGS.debug("The Key :"+ expectedKey +" doesn't have any value in the URL: "+ url +". The actual value fetched from UI is: "+actalValue);
				System.out.println("The Key :"+ expectedKey +" doesn't have any value in the URL: "+ url +". The actual value fetched from UI is: "+actalValue);
				WebDriverCommonLib.getScreenShot(driver, "verifyKeyValueAttributesGoogfc");
				Assert.assertEquals(false, true, "The Key :"+ expectedKey +" doesn't have any value in the URL: "+ url +". The actual value fetched from UI is: "+actalValue);
			}
		}
	}

	public void verifyDFP_CodeIsRunning(String url) throws IOException{
		//Get page source
		String pageSource=driver.getPageSource();
		//APPLICATION_LOGS.debug("Page Source= "+ pageSource);

		//Expected key-value from test data
		String expKeyValue="https://www.googletagservices.com/tag/js/gpt.js";
		System.out.println("Expected Key-Value "+ expKeyValue);
		APPLICATION_LOGS.debug("Expected Key-Value "+ expKeyValue);

		//Verify the Expected Key-Value is present in Page Source or not		
		if(pageSource.contains(expKeyValue)==true){
			APPLICATION_LOGS.debug("DFP Code is present in page source.Expected Key Value is:  "+expKeyValue);
			System.out.println("DFP Code is present in page source.Expected Key Value is:  "+expKeyValue);
			System.out.println("***************************************************************************");

		}
		else{
			APPLICATION_LOGS.debug("DFP Code is not present in page source.Expected Key Value is:  "+expKeyValue);
			System.out.println("DFP Code is not present in page source.Expected Key Value is:  "+expKeyValue);
			System.out.println("***************************************************************************");
			WebDriverCommonLib.getScreenShot(driver, "Error in verifyDFP_CodeIsRunning");
			assertTrue(pageSource.contains(expKeyValue),"DFP Code is not present in page source.Expected Key Value is:  "+expKeyValue);

		}
	}

	public void scrollToTheEndOfPage() throws InterruptedException{
		/*//Scroll to the end of page
		WebElement lastElement = driver.findElement(By.xpath("//div[@class='footer-copyright']"));
		int y = lastElement.getLocation().getY();
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollTo(0,"+y+")");
		Thread.sleep(3000);*/
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try {
			long lastHeight=((Number)js.executeScript("return document.body.scrollHeight")).longValue();
			System.out.println("lastHeight: "+ lastHeight);
			while (true) {
				((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
				Thread.sleep(3000);

				js.executeScript("window.scrollBy(0,-3000)", "");
				Thread.sleep(3000);
				((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");

				js.executeScript("window.scrollBy(0,-3000)", "");
				Thread.sleep(3000);
				((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");


				long newHeight = ((Number)js.executeScript("return document.body.scrollHeight")).longValue();

				System.out.println("newHeight: "+newHeight);
				if (newHeight == lastHeight) {
					break;
				}
				lastHeight = newHeight;

			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
	public void scrollToElement(By elementBy) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement element = driver.findElement(elementBy);

		js.executeScript("arguments[0].scrollIntoView();", element);
	}

	public void scrollDownPage() throws InterruptedException{
		int scroll_count=0;

		//WebElement Taboola_feed = driver.findElement(By.xpath("//*[@id='taboola-below-gallery-thumbnails']"));
		//WebElement Taboola_feed = driver.findElement(By.id("toh-taboola"));
		//System.out.println("text: " +Taboola_feed.getText());

		Actions action = new Actions(driver);
		do
		{
			Thread.sleep(1000);
			action.sendKeys(Keys.PAGE_DOWN).build().perform();
			Thread.sleep(1000);
			action.sendKeys(Keys.ESCAPE).perform();
			scroll_count++;
		}while(scroll_count<70);

	}
	//This code is used to verify the Ad Units in googfc console
	/*public void verifyAdUnitsInAdSlots(String url, String adStackID, String platform, String template, String adUnit) throws InterruptedException{	
		//Click on Ad Slots tab
		driver.findElement(By.xpath("//div[contains(text(),'Ad Slots')]")).click();
		//Get the required Ad unit from the Ad slots section
		String actualAdUnitValuefromUI= driver.findElement(By.xpath("//span[text()='"+adUnit+"']/preceding-sibling::span")).getText();
		APPLICATION_LOGS.debug("Actual Ad unit value fetched from UI is:  "+actualAdUnitValuefromUI);
		System.out.println("Actual Ad unit value fetched from UI is:  "+actualAdUnitValuefromUI);
		//Get the expected value from test data
		System.out.println("adStackID:"+adStackID);
		System.out.println("platform:"+platform);
		System.out.println("template:"+template);
		String expectedAdUnitValuefromTestData= adStackID+ " >  "  +platform+ " >  "  +template +" >  ";

		APPLICATION_LOGS.debug("Expected Ad unit value fetched from test data is:  "+expectedAdUnitValuefromTestData);
		System.out.println("Expected Ad unit value fetched from test data is:  "+expectedAdUnitValuefromTestData);

		//Get the browser details
		configFileReader = new ConfigFileReader();
		String browser = configFileReader.getBrowserName();
		if(browser.equalsIgnoreCase("firefox")|| browser.equalsIgnoreCase("chrome")|| browser.equalsIgnoreCase("ie")){
			Assert.assertEquals(actualAdUnitValuefromUI, expectedAdUnitValuefromTestData,"The actual Ad Unit value: " +actualAdUnitValuefromUI +" is not match with expected Ad Unit value: "+ expectedAdUnitValuefromTestData);
			APPLICATION_LOGS.debug("The actual Ad Unit value: " +actualAdUnitValuefromUI +" is not match with expected Ad Unit value: "+ expectedAdUnitValuefromTestData);
			System.out.println("The actual Ad Unit value: " +actualAdUnitValuefromUI +" is not match with expected Ad Unit value: "+ expectedAdUnitValuefromTestData);

		}
		else{
			String mobileAdUnitValue=expectedAdUnitValuefromTestData.replaceAll("desktop", "mobile");
			System.out.println("mobileAdUnitValue: "+ mobileAdUnitValue);
			Assert.assertEquals(actualAdUnitValuefromUI, mobileAdUnitValue,"The actual Ad Unit value: " +actualAdUnitValuefromUI +" is not match with expected Ad Unit value: "+ mobileAdUnitValue);
			APPLICATION_LOGS.debug("The actual Ad Unit value: " +actualAdUnitValuefromUI +" is not match with expected Ad Unit value: "+ mobileAdUnitValue);
			System.out.println("The actual Ad Unit value: " +actualAdUnitValuefromUI +" is not match with expected Ad Unit value: "+ mobileAdUnitValue);

		}
	}*/

	//This code is used to verify the Ad Units in showads=1 console
	public void verifyAdUnitsInAdSlots(String url, String adStackID, String platform, String template, String adUnit) throws Exception{	
		//Get the required Ad unit from the Ad slots section
		String actualAdUnitValuefromUI= driver.findElement(By.xpath("//button[contains(text(),'"+adUnit+"')]")).getText();
		APPLICATION_LOGS.debug("Actual Ad unit value fetched from UI is:  "+actualAdUnitValuefromUI);
		System.out.println("Actual Ad unit value fetched from UI is:  "+actualAdUnitValuefromUI);
		//Get the expected value from test data
		System.out.println("adStackID:"+adStackID);
		System.out.println("platform:"+platform);
		System.out.println("template:"+template);
		String expectedAdUnitValuefromTestData= "/"+adStackID+"/"+platform+"/"+template+"/"+adUnit;

		APPLICATION_LOGS.debug("Expected Ad unit value fetched from test data is:  "+expectedAdUnitValuefromTestData);
		System.out.println("Expected Ad unit value fetched from test data is:  "+expectedAdUnitValuefromTestData);

		//Get the browser details
		configFileReader = new ConfigFileReader();
		String browser = configFileReader.getBrowserName();
		if(browser.equalsIgnoreCase("firefox")|| browser.equalsIgnoreCase("chrome")|| browser.equalsIgnoreCase("ie")){
			Assert.assertEquals(actualAdUnitValuefromUI, expectedAdUnitValuefromTestData,"The actual Ad Unit value: " +actualAdUnitValuefromUI +" is not match with expected Ad Unit value: "+ expectedAdUnitValuefromTestData);
			APPLICATION_LOGS.debug("The actual Ad Unit value: " +actualAdUnitValuefromUI +" is match with expected Ad Unit value: "+ expectedAdUnitValuefromTestData);
			System.out.println("The actual Ad Unit value: " +actualAdUnitValuefromUI +" is match with expected Ad Unit value: "+ expectedAdUnitValuefromTestData);

		}
		else{
			String mobileAdUnitValue=expectedAdUnitValuefromTestData.replaceAll("desktop", "mobile");
			System.out.println("mobileAdUnitValue: "+ mobileAdUnitValue);
			Assert.assertEquals(actualAdUnitValuefromUI, mobileAdUnitValue,"The actual Ad Unit value: " +actualAdUnitValuefromUI +" is not match with expected Ad Unit value: "+ mobileAdUnitValue);
			APPLICATION_LOGS.debug("The actual Ad Unit value: " +actualAdUnitValuefromUI +" is match with expected Ad Unit value: "+ mobileAdUnitValue);
			System.out.println("The actual Ad Unit value: " +actualAdUnitValuefromUI +" is match with expected Ad Unit value: "+ mobileAdUnitValue);

		}
	}
	
	public void verifyBreadCrumb(String url, String adStackID, String platform, String template, String adUnit) throws Exception{	
		//Get the required Ad unit from the Ad slots section
		String actualAdUnitValuefromUI= driver.findElement(By.xpath("//button[contains(text(),'"+adUnit+"')]")).getText();
		APPLICATION_LOGS.debug("Actual Ad unit value fetched from UI is:  "+actualAdUnitValuefromUI);
		System.out.println("Actual Ad unit value fetched from UI is:  "+actualAdUnitValuefromUI);
		//Get the expected value from test data
		System.out.println("adStackID:"+adStackID);
		System.out.println("platform:"+platform);
		System.out.println("template:"+template);
		String expectedAdUnitValuefromTestData= "/"+adStackID+"/"+platform+"/"+template+"/"+adUnit;

		APPLICATION_LOGS.debug("Expected Ad unit value fetched from test data is:  "+expectedAdUnitValuefromTestData);
		System.out.println("Expected Ad unit value fetched from test data is:  "+expectedAdUnitValuefromTestData);

		//Get the browser details
		configFileReader = new ConfigFileReader();
		String browser = configFileReader.getBrowserName();
		if(browser.equalsIgnoreCase("firefox")|| browser.equalsIgnoreCase("chrome")|| browser.equalsIgnoreCase("ie")){
			Assert.assertEquals(actualAdUnitValuefromUI, expectedAdUnitValuefromTestData,"The actual Ad Unit value: " +actualAdUnitValuefromUI +" is not match with expected Ad Unit value: "+ expectedAdUnitValuefromTestData);
			APPLICATION_LOGS.debug("The actual Ad Unit value: " +actualAdUnitValuefromUI +" is match with expected Ad Unit value: "+ expectedAdUnitValuefromTestData);
			System.out.println("The actual Ad Unit value: " +actualAdUnitValuefromUI +" is match with expected Ad Unit value: "+ expectedAdUnitValuefromTestData);

		}
		else{
			String mobileAdUnitValue=expectedAdUnitValuefromTestData.replaceAll("desktop", "mobile");
			System.out.println("mobileAdUnitValue: "+ mobileAdUnitValue);
			Assert.assertEquals(actualAdUnitValuefromUI, mobileAdUnitValue,"The actual Ad Unit value: " +actualAdUnitValuefromUI +" is not match with expected Ad Unit value: "+ mobileAdUnitValue);
			APPLICATION_LOGS.debug("The actual Ad Unit value: " +actualAdUnitValuefromUI +" is match with expected Ad Unit value: "+ mobileAdUnitValue);
			System.out.println("The actual Ad Unit value: " +actualAdUnitValuefromUI +" is match with expected Ad Unit value: "+ mobileAdUnitValue);

		}
	}
	

	public void Verify_Nativo_Global_Variable_PartnerList(String url) throws IOException{
		//Get page source
		String pageSource=driver.getPageSource();
		//APPLICATION_LOGS.debug("Page Source= "+ pageSource);

		//Expected key-value from test data
		String expKeyValue="";
		if(url.contains(".ca/")){
			String[] splitURL= url.split("ca/");
			String modifiedURL= splitURL[0];

			expKeyValue=""+modifiedURL+"ca/wp-content/plugins/tmbi-nativo/js/nativo-custom.js";
			System.out.println("Expected Key-Value "+ expKeyValue);
			APPLICATION_LOGS.debug("Expected Key-Value "+ expKeyValue);
		}
		else{
			String[] splitURL= url.split("com/");
			String modifiedURL= splitURL[0];

			expKeyValue=""+modifiedURL+"com/wp-content/plugins/tmbi-nativo/js/nativo-custom.js";
			System.out.println("Expected Key-Value "+ expKeyValue);
			APPLICATION_LOGS.debug("Expected Key-Value "+ expKeyValue);

		}

		//Verify the Expected Key-Value is present in Page Source or not		
		if(pageSource.contains(expKeyValue)==true){
			APPLICATION_LOGS.debug("Nativo global partner list is present in page source.Expected Key Value is:  "+expKeyValue);

		}
		else{
			APPLICATION_LOGS.debug("Nativo global partner list is not present in page source.Expected Key Value is:  "+expKeyValue);
			WebDriverCommonLib.getScreenShot(driver, "Error in Verify_Nativo_Global_Variable_PartnerList");
			assertTrue(pageSource.contains(expKeyValue),"Nativo global partner list is not present in page source.Expected Key Value is:  "+expKeyValue);

		}
	}

	public void Verify_Taboola_Global_Variable_PartnerList(String url, String siteID) throws IOException{
		//Get page source
		String pageSource=driver.getPageSource();
		//APPLICATION_LOGS.debug("Page Source= "+ pageSource);

		//Expected key-value from test data
		String expKeyValue="";
		if(url.contains(".ca/")){
			String[] splitURL= url.split("ca/");
			String modifiedURL= splitURL[0];
			expKeyValue=""+modifiedURL+"ca/wp-content/plugins/rd-taboola/js/"+siteID+"-taboola-loader.js";
			System.out.println("Expected Key-Value "+ expKeyValue);
			APPLICATION_LOGS.debug("Expected Key-Value "+ expKeyValue);

		}
		else{
			String[] splitURL= url.split("com/");
			String modifiedURL= splitURL[0];
			expKeyValue=""+modifiedURL+"com/wp-content/plugins/rd-taboola/js/"+siteID+"-taboola-loader.js";
			System.out.println("Expected Key-Value "+ expKeyValue);
			APPLICATION_LOGS.debug("Expected Key-Value "+ expKeyValue);

		}


		//Verify the Expected Key-Value is present in Page Source or not		
		if(pageSource.contains(expKeyValue)==true){
			APPLICATION_LOGS.debug("Taboola global partner list is present in page source.Expected Key Value is:  "+expKeyValue);

		}
		else{
			APPLICATION_LOGS.debug("Taboola global partner list is not present in page source.Expected Key Value is:  "+expKeyValue);
			WebDriverCommonLib.getScreenShot(driver, "Error in Verify_Taboola_Global_Variable_PartnerList");
			assertTrue(pageSource.contains(expKeyValue),"Taboola global partner list is not present in page source.Expected Key Value is:  "+expKeyValue);

		}
	}

	public void Verify_BounceExchnage_Global_Variable_PartnerList(String url) throws IOException{
		//Get page source
		String pageSource=driver.getPageSource();
		//APPLICATION_LOGS.debug("Page Source= "+ pageSource);

		//Expected key-value from test data
		String expKeyValue="";
		if(url.contains(".ca/")){
			String[] splitURL= url.split("ca/");
			String modifiedURL= splitURL[0];
			expKeyValue=""+modifiedURL+"ca/wp-content/plugins/tmbi-bx/js/smart-tag.js";
			System.out.println("Expected Key-Value "+ expKeyValue);
			APPLICATION_LOGS.debug("Expected Key-Value "+ expKeyValue);

		}
		else{
			String[] splitURL= url.split("com/");
			String modifiedURL= splitURL[0];
			expKeyValue=""+modifiedURL+"com/wp-content/plugins/tmbi-bx/js/smart-tag.js";
			System.out.println("Expected Key-Value "+ expKeyValue);
			APPLICATION_LOGS.debug("Expected Key-Value "+ expKeyValue);

		}


		//Verify the Expected Key-Value is present in Page Source or not		
		if(pageSource.contains(expKeyValue)==true){
			APPLICATION_LOGS.debug("Bounce Exchange global partner list is present in page source.Expected Key Value is:  "+expKeyValue);

		}
		else{
			APPLICATION_LOGS.debug("Bounce Exchange global partner list is not present in page source.Expected Key Value is:  "+expKeyValue);
			WebDriverCommonLib.getScreenShot(driver, "Error in Verify_BounceExchnage_Global_Variable_PartnerList");
			assertTrue(pageSource.contains(expKeyValue),"Bounce Exchange global partner list is not present in page source.Expected Key Value is:  "+expKeyValue);

		}
	}

	public void Verify_JWPlayer_Global_Variable_PartnerList(String url) throws IOException{
		//Get page source
		String pageSource=driver.getPageSource();
		//APPLICATION_LOGS.debug("Page Source= "+ pageSource);

		//Expected key-value from test data
		String expKeyValue="";
		if(url.contains(".ca/")){
			String[] splitURL= url.split("ca/");
			String modifiedURL= splitURL[0];
			expKeyValue=""+modifiedURL+"ca/wp-content/plugins/rd-video/inc/players/../js";
			System.out.println("Expected Key-Value "+ expKeyValue);
			APPLICATION_LOGS.debug("Expected Key-Value "+ expKeyValue);

		}
		else{
			String[] splitURL= url.split("com/");
			String modifiedURL= splitURL[0];
			expKeyValue=""+modifiedURL+"com/wp-content/plugins/rd-video/inc/players/../js";
			System.out.println("Expected Key-Value "+ expKeyValue);
			APPLICATION_LOGS.debug("Expected Key-Value "+ expKeyValue);

		}


		//Verify the Expected Key-Value is present in Page Source or not		
		if(pageSource.contains(expKeyValue)==true){
			APPLICATION_LOGS.debug("JW Player global partner list is present in page source.Expected Key Value is:  "+expKeyValue);

		}
		else{
			APPLICATION_LOGS.debug("JW Player global partner list is not present in page source.Expected Key Value is:  "+expKeyValue);
			WebDriverCommonLib.getScreenShot(driver, "Error in Verify_JWPlayer_Global_Variable_PartnerList");
			assertTrue(pageSource.contains(expKeyValue),"JW Player global partner list is not present in page source.Expected Key Value is:  "+expKeyValue);

		}
	}

	public void Verify_JWPlayer_Ad_Partners_Spotx_EMX_Telaria(String spotxId, String emxId, String telariaId) throws IOException{
		//Get page source
		String pageSource=driver.getPageSource();
		//APPLICATION_LOGS.debug("Page Source= "+ pageSource);

		//Expected values for Spotx, EMX and Telaria
		String expectedspotX= "{\"name\":\"SpotX\",\"id\":"+spotxId+"}";
		String expectedEMX= "{\"name\":\"EMX\",\"id\":"+emxId+"}";
		String expectedTelaria= "{\"name\":\"Telaria\",\"id\":"+telariaId+"}";

		System.out.println("The expected JW Player Ad partners are Spotx:"+ expectedspotX+" EMX: "+expectedEMX+" Telaria: "+expectedTelaria+"");
		APPLICATION_LOGS.debug("The expected JW Player Ad partners are Spotx:"+ expectedspotX+" EMX: "+expectedEMX+" Telaria: "+expectedTelaria+"");

		//Verify the Expected Key-Value is present in Page Source or not		
		if(pageSource.contains(expectedspotX)==true && pageSource.contains(expectedEMX)==true && pageSource.contains(expectedTelaria)==true){
			APPLICATION_LOGS.debug("JW Player global partner list: Spotx,EMX and Telaria is present in page source.Expected Key Value is:  "+expectedspotX);

		}
		else{
			APPLICATION_LOGS.debug("JJW Player global partner list:Spotx,EMX and Telaria is not present in page source.Expected Key Value is:  "+expectedspotX);
			WebDriverCommonLib.getScreenShot(driver, "Error in Verify_JWPlayer_Ad_Partners_Spotx_EMX_Telaria");
			assertTrue(pageSource.contains(expectedspotX),"JW Player global partner list:Spotx,EMX and Telaria is not present in page source.Expected Key Value is:  "+expectedspotX);

		}
	}

	public void Verify_Krux_Global_Variable_PartnerList(String url) throws IOException{
		//Get page source
		String pageSource=driver.getPageSource();
		//APPLICATION_LOGS.debug("Page Source= "+ pageSource);

		//Expected key-value from test data
		String expKeyValue="";
		if(url.contains(".ca/")){
			String[] splitURL= url.split("ca/");
			String modifiedURL= splitURL[0];
			expKeyValue=""+modifiedURL+"ca/wp-content/plugins/tmbi-custom-social-share/js/krux-tracking.js";
			System.out.println("Expected Key-Value "+ expKeyValue);
			APPLICATION_LOGS.debug("Expected Key-Value "+ expKeyValue);

		}
		else{
			String[] splitURL= url.split("com/");
			String modifiedURL= splitURL[0];
			expKeyValue=""+modifiedURL+"com/wp-content/plugins/tmbi-custom-social-share/js/krux-tracking.js";
			System.out.println("Expected Key-Value "+ expKeyValue);
			APPLICATION_LOGS.debug("Expected Key-Value "+ expKeyValue);

		}

		//Verify the Expected Key-Value is present in Page Source or not		
		if(pageSource.contains(expKeyValue)==true){
			APPLICATION_LOGS.debug("Krux global partner list is present in page source.Expected Key Value is:  "+expKeyValue);

		}
		else{
			APPLICATION_LOGS.debug("Krux global partner list is not present in page source.Expected Key Value is:  "+expKeyValue);
			WebDriverCommonLib.getScreenShot(driver, "Error in Verify_Krux_Global_Variable_PartnerList");
			assertTrue(pageSource.contains(expKeyValue),"Krux global partner list is not present in page source.Expected Key Value is:  "+expKeyValue);

		}
	}

	public void Verify_AdobeDTM_Global_Variable_PartnerList(String url) throws IOException{
		//Get page source
		String pageSource=driver.getPageSource();
		//APPLICATION_LOGS.debug("Page Source= "+ pageSource);

		//Expected key-value from test data
		String expKeyValue="";
		if(url.contains(".ca/")){
			String[] splitURL= url.split("ca/");
			String modifiedURL= splitURL[0];
			expKeyValue=""+modifiedURL+"ca/wp-content/plugins/rd-adobe-dtm/js/adobe_dtm.js";
			System.out.println("Expected Key-Value "+ expKeyValue);
			APPLICATION_LOGS.debug("Expected Key-Value "+ expKeyValue);

		}
		else{
			String[] splitURL= url.split("com/");
			String modifiedURL= splitURL[0];
			expKeyValue=""+modifiedURL+"com/wp-content/plugins/rd-adobe-dtm/js/adobe_dtm.js";
			System.out.println("Expected Key-Value "+ expKeyValue);
			APPLICATION_LOGS.debug("Expected Key-Value "+ expKeyValue);

		}


		//Verify the Expected Key-Value is present in Page Source or not		
		if(pageSource.contains(expKeyValue)==true){
			APPLICATION_LOGS.debug("Adobe Launch global partner list is present in page source.Expected Key Value is:  "+expKeyValue);

		}
		else{
			APPLICATION_LOGS.debug("Adobe Launch  global partner list is not present in page source.Expected Key Value is:  "+expKeyValue);
			WebDriverCommonLib.getScreenShot(driver, "Error in Verify_AdobeLaunch_Global_Variable_PartnerList");
			assertTrue(pageSource.contains(expKeyValue),"Adobe Launch  global partner list is not present in page source.Expected Key Value is:  "+expKeyValue);

		}
	}

	public void Verify_Appnexus_Global_Variable_PartnerList(String url) throws IOException{
		//Get page source
		String pageSource=driver.getPageSource();
		//APPLICATION_LOGS.debug("Page Source= "+ pageSource);

		//Expected key-value from test data
		String expKeyValue="\"appnexus_enabled\":\"1\"";
		System.out.println("Expected Key-Value "+ expKeyValue);
		APPLICATION_LOGS.debug("Expected Key-Value "+ expKeyValue);

		//Verify the Expected Key-Value is present in Page Source or not		
		if(pageSource.contains(expKeyValue)==true){
			APPLICATION_LOGS.debug("Appnexus Launch global partner list is present in page source.Expected Key Value is:  "+expKeyValue);

		}
		else{
			APPLICATION_LOGS.debug("Appnexus Launch  global partner list is not present in page source.Expected Key Value is:  "+expKeyValue);
			WebDriverCommonLib.getScreenShot(driver, "Error in Verify_AdobeLaunch_Global_Variable_PartnerList");
			assertTrue(pageSource.contains(expKeyValue),"Appnexus Launch  global partner list is not present in page source.Expected Key Value is:  "+expKeyValue);

		}
	}

	public void Verify_Rubicon_Global_Variable_PartnerList(String rubicon_account_id , String rubicon_site_id) throws IOException{
		//Get page source
		String pageSource=driver.getPageSource();
		//APPLICATION_LOGS.debug("Page Source= "+ pageSource);

		//Expected values for Spotx, EMX and Telaria
		String expected_rubicon_account_id= "rubicon_account_id\":"+rubicon_account_id+"}";
		String expected_rubicon_site_id= "rubicon_site_id\":"+rubicon_site_id+"}";

		System.out.println("The Rubicon Ad partners values are rubicon_account_id:"+ rubicon_account_id+" rubicon_site_id: "+rubicon_site_id+"");
		APPLICATION_LOGS.debug("The Rubicon Ad partners values are rubicon_account_id:"+ rubicon_account_id+" rubicon_site_id: "+rubicon_site_id+"");

		//Verify the Expected Key-Value is present in Page Source or not		
		if(pageSource.contains(expected_rubicon_account_id)==true && pageSource.contains(expected_rubicon_site_id)==true){
			APPLICATION_LOGS.debug("The Rubicon Ad partners has values for: rubicon_account_id and rubicon_site_id in the page source");

		}
		else{
			APPLICATION_LOGS.debug("The Rubicon Ad partners doesn't have values for: rubicon_account_id and rubicon_site_id in the page source");
			WebDriverCommonLib.getScreenShot(driver, "Error in Verify_Rubicon_Ad_Partners_Spotx_EMX_Telaria");
			assertTrue(pageSource.contains(expected_rubicon_account_id),"The Rubicon Ad partners doesn't have values for: rubicon_account_id and rubicon_site_id in the page source");

		}
	}

	public void Verify_Audigent_Global_Variable_PartnerList(String url) throws IOException{
		//Navigate to page source URL
		driver.navigate().to("view-source:"+url);
		WebDriverCommonLib.waitForPageToBeLoad(driver);

		// Store the current window handle
		String winHandleBefore = driver.getWindowHandle();

		//Find the Adobe URL
		try{
			WebElement adobeURL= driver.findElement(By.xpath("//a[contains(text(),'https://assets.adobedtm.com/')]"));
			System.out.println("The Adobe URL:"+ adobeURL+"");
			APPLICATION_LOGS.debug("The Adobe URL:"+ adobeURL+"");
			//Click on Adobe URL
			adobeURL.click();
		}
		catch (Exception e) {
			Assert.assertEquals(true, false, "Adobe URL is not present in the page source:"+url);
		}

		// Switch to new window opened
		for(String winHandle : driver.getWindowHandles()){
			driver.switchTo().window(winHandle);
		}

		// Perform the actions on new window
		String pageSource=driver.getPageSource();
		//Verify the Expected Key-Value is present in Page Source or not		
		if(pageSource.contains("Audigent")==true){
			APPLICATION_LOGS.debug("Audigent global partner list is present in page source");

		}
		else{
			APPLICATION_LOGS.debug("Audigent global partner list is present in page source");
			WebDriverCommonLib.getScreenShot(driver, "Error in Verify_Audigent_Global_Variable_PartnerList");
			assertTrue(pageSource.contains("Audigent"),"Audigent global partner list is present in page source");

		}

		// Close the new window, if that window no more required
		driver.close();

		// Switch back to original browser (first window)
		driver.switchTo().window(winHandleBefore);
		//driver.navigate().to(url);
		//WebDriverCommonLib.waitForPageToBeLoad(driver);

	}

	public void verifyCampaignIdInPageSource(String url) throws IOException, InterruptedException{
		//Get page source
		String pageSource=driver.getPageSource();

		//Expected key-value from test data		
		String expKeyValue= "campaign_id";

		APPLICATION_LOGS.debug("Expected Key-Value "+ expKeyValue);
		System.out.println("Expected Key-Value "+ expKeyValue);

		//Verify the Expected Key-Value is present in Page Source or not			
		if(pageSource.contains(expKeyValue)==true){
			APPLICATION_LOGS.debug("campaign_id is present on site :"+url+" Expected Key Value is:  "+expKeyValue);
			System.out.println("campaign_id is present on site :"+url+" Expected Key Value is:  "+expKeyValue);

		}
		else{
			APPLICATION_LOGS.debug("campaign_id is not present on site :"+url+" Expected Key Value is:  "+expKeyValue);
			System.out.println("campaign_id is not present on site :"+url+" Expected Key Value is:  "+expKeyValue);
			WebDriverCommonLib.getScreenShot(driver, "verifyCampaignIdInPageSource");
			assertTrue(pageSource.contains(expKeyValue),"campaign_id is not present on site :"+url+" Expected Key Value is:  "+expKeyValue);

		}

	}

}

