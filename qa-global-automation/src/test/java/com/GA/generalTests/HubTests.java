package com.GA.generalTests;

import static org.testng.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import com.wp.general.BaseTest;
import com.wp.general.Page;
import com.wp.genericLib.ExcelLibrary;

public class HubTests extends BaseTest  {
	
	/**
	 * Class that holds all tests that only need to be performed on a single page of each brand
	 * @param url
	 */
	@Factory(dataProvider = "hub-urls")
	public HubTests(String url) {
		this.url = url;
		
	}
	
	public HubTests() {
		
	}

	@BeforeClass
	public void beforeClass() {
		super.driverSetup();
		
		page = new Page(driver, wait, url);
		page.goToHome();
	}
	
	@Test
	public void defaultImageTest() {
		final String[] defaultImgs = {"https://www.besthealthmag.ca/wp-content/uploads/2020/05/bhc-search-logo-300x131.jpg", "https://www.familyhandyman.com/wp-content/uploads/2020/05/FHM-square-300x300.jpg", "https://www.tasteofhome.com/wp-content/uploads/2019/07/no-photo-recipe-700x700-300x300.jpg", "https://www.birdsandblooms.com/wp-content/uploads/2020/05/bnb_fav_512-295x295.jpg", "https://www.thehealthy.com/wp-content/uploads/2020/05/the-healthy-avatar-circle-orange-01-295x295.png", "https://www.selection.ca/wp-content/uploads/2016/03/assiette_326.jpg", "https://www.readersdigest.ca/wp-content/uploads/2020/05/rdc-search-logo-295x295.png", "https://www.rd.com/wp-content/uploads/2020/05/2018_RD_Icon2_RED_for-rd-editors-user-1-295x295.jpg"};

		String pageSource = driver.getPageSource();
		
		for(String imgSrc : defaultImgs) {
			if(pageSource.contains(imgSrc))
				Assert.fail("URL " + url + " has a default image on it.");
		}
	}
	
	@Override
	public String getTestName() {
		return  testName.get();	
	}
	

	@DataProvider(name = "hub-urls")  
	public Object[][] getBrandUrls(){
		List<String> articleUrls = ExcelLibrary.getTemplateUrls("GA_CPT", 2, "Hub Page");
		
		return to2DArray(articleUrls); 
	}
	
	@AfterClass
	public void after() {
		super.teardown();
	}
}
