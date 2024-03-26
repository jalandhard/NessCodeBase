package com.GA.RD;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

//import com.globalAutomation.misc.Link;
import com.wp.genericLib.ConfigFileReader;
import com.wp.genericLib.Constant;
import com.wp.genericLib.Driver;
import com.wp.genericLib.ExcelLibrary;
import com.wp.genericLib.Link;
import com.wp.genericLib.WebDriverCommonLib;
import com.wp.pageObjectRepo.GlobalFunctions;

public class RD_JokePage extends Driver{

	//Initialize object using page factory method 
	ConfigFileReader configFileReader;
	public Logger APPLICATION_LOGS = LogManager.getLogger("AppLogger");

	//Test Data
	String url=Constant.RD_JokePage;

	private By breadcrumbLink = By.xpath("//p[@id = 'breadcrumbs']/span/a");
	private By jokeTitle = By.xpath("//div[@class = 'jokes-intro pure-u']/h1[1]");
	private By jokeDek = By.xpath("//p[@class='jokes-dek']");
	private By curatedContent = By.xpath("//div[@class='widget ja-curated-content-row-one']");
	private By jokesentrySection=By.xpath("//section[@class='joke-section full-width']");//By.xpath("//section[@class='joke-section full-width ajax-content']");
	private By jokesEntryCardTitle = By.xpath("//section[@class='joke-section full-width ajax-content']//h3");//By.xpath("//section[@class='joke-section full-width ajax-content']//h3");
	private By JokesEntryContent = By.xpath("//section[@class='joke-section full-width ajax-content']//div[@class='entry-content']");
	private By jokeEntryCard = By.xpath("//section[@class = 'joke-section full-width ajax-content']/article");
	private By jokeTaxHeader = By.xpath("//div[@class = 'joke-tax-popular']/h2[@class = 'jokes-tax-header']");
	private By moreJokesLinks = By.xpath("//ul[@class='wp-tag-cloud pure-g']//a");



	@BeforeClass
	public void navigateToRequiredURL() throws Exception{
		//Object Initialization
		GlobalFunctions globalPage=PageFactory.initElements(driver, GlobalFunctions.class);

		//Step:1 Navigate required URL
		globalPage.navigateToRequiredSite(url);
	}
	@BeforeMethod
	public void nameBefore(Method method)
	{
		System.out.println("********************************************************************************************");
		System.out.println("Test : " + method.getName() + " execution started ..................");       
	}

	@Test
	public void verify_breadcrumb_RDJokesPage() throws IOException, InterruptedException {
		GlobalFunctions globalPage = PageFactory.initElements(driver, GlobalFunctions.class);
		
		globalPage.verifyIsBreadCrumbAvailable(breadcrumbLink, url); //, desiredUrl);
		driver.navigate().to(url);
	}
	
	@Test(dependsOnMethods = {"verify_breadcrumb_RDJokesPage"}, alwaysRun = true)
	public void verify_Title_RDJokesPage() throws IOException, InterruptedException {
		GlobalFunctions globalPage = PageFactory.initElements(driver, GlobalFunctions.class);
		String element = "Title";
		//Test Step
		//Step 2 Verify Amazon Header Bidder is Running
		globalPage.verifyIsElementAvailable(jokeTitle, url, element);
		
	}
	
	@Test(dependsOnMethods = {"verify_Title_RDJokesPage"}, alwaysRun = true)
	public void verify_Dek_RDJokesPage() throws IOException, InterruptedException {
		//Object Initialization
		GlobalFunctions globalPage = PageFactory.initElements(driver, GlobalFunctions.class);
		String dekTest = "Dek";
		
		//Test Step
		globalPage.verifyIsElementAvailable(jokeDek, url, dekTest);
	
	}
	
	@Test(dependsOnMethods = {"verify_Dek_RDJokesPage"}, alwaysRun = true)
	public void verify_curatedContainerJokes__RDJokesPage() throws IOException, InterruptedException {
		GlobalFunctions globalPage = PageFactory.initElements(driver, GlobalFunctions.class);
		
		List<WebElement> links = driver.findElements(curatedContent);
		int count = links.size();
		System.out.println("Total no of Jokes in Curated Container are: " + count);
		globalPage.NavigationtoDiffTab(curatedContent, url);
		
	
	}
	@Test(dependsOnMethods = {"verify_curatedContainerJokes__RDJokesPage"}, alwaysRun = true)
	public void verify_jokesSection_Available_RDJokesPage() throws IOException, InterruptedException{
		GlobalFunctions globalPage = PageFactory.initElements(driver, GlobalFunctions.class);
		
		String element = "Jokes Entry Section";
		globalPage.IsElementExist(url, jokesentrySection, element);
	}
	
	/*@Test(dependsOnMethods = {"verify_Title_RDJokesPage"}, alwaysRun = true)
	public void verify_JokesSection__RDJokesPage() throws IOException, InterruptedException {
		GlobalFunctions globalPage = PageFactory.initElements(driver, GlobalFunctions.class);
		
		int links = driver.findElements(jokeEntryCard).size();
		System.out.println("Total no of Jokes in Jokes Section are: " + links);
		for (int i = 0; i < links; i++) {
			//System.out.println(i);
			List<WebElement> lists = driver.findElements(jokesEntryCardTitle);
			WebElement list = lists.get(i);
			String browseText= list.getText();
		
		String element = "jokes title";
		globalPage.IsElementExist(url, jokesEntryCardTitle, element);
		}
		
		int numCuratedContents = driver.findElements(jokeEntryCard).size();
		
		final int EXPECTED_CURATED_CONTENT_NUM = 3;
		
		assertTrue(numCuratedContents == EXPECTED_CURATED_CONTENT_NUM, "Did not find 3 joke entry cards");
		
	}*/
	
	@Test(dependsOnMethods = {"verify_Title_RDJokesPage"}, alwaysRun = true)
	public void verify_jokesTaxHeader_RDJokesPage() throws IOException, InterruptedException {
		GlobalFunctions globalPage = PageFactory.initElements(driver, GlobalFunctions.class);
				
		globalPage.IsElementExist(url, jokeTaxHeader, "Joke Tax Header");
	}
	
	@Test(dependsOnMethods = {"verify_jokesTaxHeader_RDJokesPage"}, alwaysRun = true)
	public void verify_moreJokes_RDJokesPage() throws IOException, InterruptedException {
		GlobalFunctions globalPage = PageFactory.initElements(driver, GlobalFunctions.class);
		
		int links = driver.findElements(moreJokesLinks).size();
		System.out.println("Total no of Jokes in More Jokes Section are: " + links);	
		globalPage.IsElementExist(url, moreJokesLinks, "Joke Tax Header");
	}


	@AfterMethod
	public void nameAfter(Method method)
	{
		System.out.println("Test : " + method.getName() + " execution completed .................... \n");       
		System.out.println("********************************************************************************************");
	}


}


