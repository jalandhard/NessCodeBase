package com.GA.TOH;

import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.wp.genericLib.ConfigFileReader;
import com.wp.genericLib.Constant;
import com.wp.genericLib.Driver;
import com.wp.pageObjectRepo.GlobalFunctions;

public class TOH_NativeTemplate extends Driver {
	//Initialize object using page factory method 
	ConfigFileReader configFileReader;
	public Logger APPLICATION_LOGS = LogManager.getLogger("AppLogger");

	//Test Data
	String url = "https://www.tasteofhome.com/recipes/meringue-topped-pecan-custard-pie/";


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
	
	@Test
	public void verify_sponsorLogo_TOH_NativeTemplate() throws IOException, InterruptedException {
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		globalPage.IsElementExist(url, By.id("top-header-sponsor-logo"), "Sponsor Logo");
	}

	@Test
	public void verify_recipeTitle_TOH_NativeTemplate() throws IOException, InterruptedException {
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		globalPage.IsElementExist(url, By.className("recipe-title"), "Recipe Title");
	}

	@Test
	public void verify_recipeMeta_TOH_NativeTemplate() throws IOException, InterruptedException {
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		globalPage.IsElementExist(url, By.className("recipe-meta"), "Recipe Meta");
	}
	
	@Test
	public void verify_recipeTimeYeild_TOH_NativeTemplate() throws IOException, InterruptedException {
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		globalPage.IsElementExist(url, By.className("recipe-time-yield"), "Recipe Time Yield");
	}
	
	@Test
	public void verify_recipeSocialToolbar_TOH_NativeTemplate() throws IOException, InterruptedException {
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		globalPage.IsElementExist(url, By.className("recipe-social-toolbar"), "Recipe Social Toolbar");
	}
	
	@Test
	public void verify_recipeSponsoredContainer_TOH_NativeTemplate() throws IOException, InterruptedException {
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		globalPage.IsElementExist(url, By.className("recipe-sponsored-container"), "Recipe Sponsored Container");
	}
	
	@Test
	public void verify_recipeIngredients_TOH_NativeTemplate() throws IOException, InterruptedException {
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		globalPage.IsElementExist(url, By.className("recipe-ingredients"), "Recipe Ingredients");
	}

	@Test
	public void verify_recipeDirections_TOH_NativeTemplate() throws IOException, InterruptedException {
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		globalPage.IsElementExist(url, By.className("recipe-directions"), "Recipe Directions");
	}
	
	@Test
	public void verify_recipeTipNutritionContainer_TOH_NativeTemplate() throws IOException, InterruptedException {
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		globalPage.IsElementExist(url, By.className("tip-nutrition-container"), "Tip Nutrition Container");
	}
	
	@AfterMethod
	public void nameAfter(Method method)
	{
		System.out.println("Test : " + method.getName() + " execution completed .................. \n");   
		System.out.println("********************************************************************************************");
	}

}
