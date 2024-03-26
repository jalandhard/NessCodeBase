package com.GA.generalTests;

import static org.testng.Assert.assertTrue;

import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import com.wp.general.BaseTest;
import com.wp.general.Page;
import com.wp.genericLib.ExcelLibrary;

public class GlobalTests extends BaseTest  {
	
	/**
	 * Class that holds all tests that only need to be performed on a single page of each brand
	 * @param url
	 */
	@Factory(dataProvider = "all-urls")
	public GlobalTests(String url) {
		this.url = url;
	}
	
	public GlobalTests() {
		
	}

	@BeforeClass
	public void beforeClass() {
		super.driverSetup();
		
		page = new Page(driver, wait, url);
		page.goToHome();
	}
	
	@Test
	public void googleAnalyiticsUaIdTest() {
		String pageSource = driver.getPageSource();
		String brand = getBrand(url);
		
		switch(brand) {
		
			case "RD":
				assertTrue(pageSource.contains("UA-17041328-1"), "Google analytic ID was not found in page source of " + url + ".");
				break;
				
			case "FHM":
				assertTrue(pageSource.contains("UA-42545046-1"), "Google analytic ID was not found in page source of " + url + ".");
				break;
			
			case "TOH":
				assertTrue(pageSource.contains("UA-38657577-1"), "Google analytic ID was not found in page source of " + url + ".");
				break;
				
			case "HLT":
				assertTrue(pageSource.contains("UA-143927731-1"), "Google analytic ID was not found in page source of " + url + ".");
				break;
				
			case "BNB":
				assertTrue(pageSource.contains("UA-42544865-1"), "Google analytic ID was not found in page source of " + url + ".");
				break;
			
			case "SRD":
				assertTrue(pageSource.contains("UA-61112934-1"), "Google analytic ID was not found in page source of " + url + ".");
				break;
				
			case "RDC":
				assertTrue(pageSource.contains("UA-61121049-1"), "Google analytic ID was not found in page source of " + url + ".");
				break;
				
			case "BHC":
				assertTrue(pageSource.contains("UA-61123544-1"), "Google analytic ID was not found in page source of " + url + ".");
				break;
		}
	}
	
	@Override
	public String getTestName() {
		return  testName.get();	
	}
	
	
	@DataProvider(name = "all-urls")  
	public Object[][] getAllUrls(){
		List<String> allUrls = ExcelLibrary.getColumnValues("GA_CPT", 2);
		
		return to2DArray(allUrls); 
	}
	
	@AfterClass
	public void after() {
		super.teardown();
	}
}
