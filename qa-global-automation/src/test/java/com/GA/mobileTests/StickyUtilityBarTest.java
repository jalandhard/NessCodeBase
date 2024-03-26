package com.GA.mobileTests;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import com.wp.general.BaseTest;
import com.wp.general.Page;
import com.wp.genericLib.ExcelLibrary;

public class StickyUtilityBarTest extends BaseTest  {
	
	/**
	 * Class that holds all tests that only need to be performed on a single page of each brand
	 * @param url
	 */
	@Factory(dataProvider = "mobile-urls")
	public StickyUtilityBarTest(String url) {
		this.url = url;
		//this.url = "https://www.birdsandblooms.com/birding/attracting-birds/attract-bluebirds-with-nesting-boxes/";
	}
	
	public StickyUtilityBarTest() {
		
	}

	@BeforeClass
	public void beforeClass() {
		super.driverSetup();
		
		page = new Page(driver, wait, url);
		page.goToHome();
	}
	
	@Test
	public void utilityBarTest() {
		//share button
		System.out.println("Clicking share button");
		driver.findElement(By.id("sm-recipe-social-share-header")).click();
		System.out.println("Share clicked");
		
		assertTrue(driver.findElement(By.className("sticky-social-share-menu")).isDisplayed(), "Share menu did not open when clicking share button on URL " + url);
		
		//social media button in middle
		int numWindows = driver.getWindowHandles().size();
		
		System.out.println("Clicking social media button");
		driver.findElement(By.className("srf-link")).click();
		System.out.println("Social media button clicked");
		
		assertTrue(driver.getWindowHandles().size() > numWindows, "Browser did not open a new window when clicking social media button on URL " + url);
		
		
		//next post button
		System.out.println("Clicking next button");
		driver.findElement(By.id("next_content_link")).click();
		System.out.println("Next clicked");
		
		System.out.println("404: " + is404Page());
		System.out.println("Led to new page: " + !url.equals(driver.getCurrentUrl()));
		
		assertTrue(!is404Page() && !url.equals(url + "#"), "Next button did not work properly.\n404: " + is404Page()+"\nLed to new page: " + !url.equals(driver.getCurrentUrl()));
		
		
	}
	
	@Override
	public String getTestName() {
		return  testName.get();	
	}
	
	@DataProvider(name = "mobile-urls")  
	public Object[][] getMobileUrls() {
		List<String> urls = ExcelLibrary.getTemplateUrls("GA_CPT", 2, "Listicle");
		urls.addAll(ExcelLibrary.getTemplateUrls("GA_CPT", 2, "Article"));
		
		return to2DArray(urls); 
	}
	
	@AfterClass
	public void after() {
		super.teardown();
	}
}
