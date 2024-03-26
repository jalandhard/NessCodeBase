package com.GA.generalTests;

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

public class BrandTests extends BaseTest  {
	
	/**
	 * Class that holds all tests that only need to be performed on a single page of each brand
	 * @param url
	 */
	@Factory(dataProvider = "brand-urls")
	public BrandTests(String url) {
		this.url = url;
	}
	
	public BrandTests() {
		
	}

	@BeforeClass
	public void beforeClass() {
		super.driverSetup();
		
		page = new Page(driver, wait, url);
		page.goToHome();
	}
	
	@Test
	public void comscoreTest() {
		String pageSource = driver.getPageSource();
		
		final String COMSCORE = "var comscore_vars = {\"c1\":\"2\",\"c2\":\"6034767\"}";
		
		assertTrue(pageSource.contains(COMSCORE), "Comscore string not found in page source of URL " + url);
	}
	
	@Override
	public String getTestName() {
		return  testName.get();	
	}
	
	/**
	 * Returns a list of URLs, where there is exactly 1 URL from each brand, and no duplicates.
	 * @return a list of URLs, where there is exactly 1 URL from each brand, and no duplicates
	 */
	@DataProvider(name = "brand-urls")  
	public Object[][] getBrandUrls(){
		List<String> allUrls = ExcelLibrary.getColumnValues("GA_CPT", 2);
		
		//get all the URLs, retrieve just the domain (rd.com/videos -> rd.com) from each URL,
		//and then put them in a Set, which will ensure there are no duplicates.
		
		//sets do not allow duplicates
		Set<String> brandSet = new HashSet<String>();
		
		for(String url : allUrls) {
			try {
				brandSet.add(getDomainUrl(url)); //add domain of the URL to the set
			} catch (URISyntaxException e) {
				System.err.println("Cannot get domain of URL " + url + ". Make sure URL is formed properly.");
				e.printStackTrace();
			}
		}
		
		List<String> result = new LinkedList<String>(brandSet);
		return to2DArray(result); 
	}
	
	@AfterClass
	public void after() {
		super.teardown();
	}
}
