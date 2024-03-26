package com.GA.generalTests;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import com.wp.general.BaseTest;
import com.wp.general.Page;
import com.wp.genericLib.ExcelLibrary;

public class ListicleTests extends BaseTest  {
	
	/**
	 * Class that holds all tests that only need to be performed on a single page of each brand
	 * @param url
	 */
	@Factory(dataProvider = "listicle-urls")
	public ListicleTests(String url) {
		this.url = url;
		
	}
	
	public ListicleTests() {
		
	}

	@BeforeClass
	public void beforeClass() {
		super.driverSetup();
		
		page = new Page(driver, wait, url);
		page.goToHome();
	}
	
	@Test
	public void cardNumberingTest() {
		By cardNumberLocator = By.className("card-number");
		
		String brand = getBrand(url);
		
		if(brand.equals("TOH") || brand.equals("FHM") || isCanadian(url)) {
			assertTrue(elementExists(cardNumberLocator), "Listicle card numbers not found on " + brand + " listicle page, and it should've been found.");
		}
		else {
			assertFalse(elementExists(cardNumberLocator), "Listicle card numbers are found on " + brand + " listicle page, and they should NOT be found.");
		}
	}
	
	@Override
	public String getTestName() {
		return  testName.get();	
	}
	

	@DataProvider(name = "listicle-urls")  
	public Object[][] getListicleUrls(){
		List<String> listicleUrls = ExcelLibrary.getTemplateUrls("GA_CPT", 2, "Listicle");
		
		return to2DArray(listicleUrls); 
	}
	
	@AfterClass
	public void after() {
		super.teardown();
	}
}
