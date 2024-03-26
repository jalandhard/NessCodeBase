package com.wp.genericLib;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.wp.genericLib.Driver;

public class WebDriverCommonLib extends Driver {

	static WebDriver driver;
	ConfigFileReader configFileReader;

	public WebDriverCommonLib(WebDriver driver){ 
		WebDriverCommonLib.driver = driver;
		PageFactory.initElements(driver,this);
	}

	public static void getScreenShot(WebDriver driver,String screenshotFileName) throws IOException{
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		//FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")+"\\screenshots\\" +screenshotFileName+ ".png"));
	}

	public static void waitForPageToBeLoad(WebDriver driver){
		driver.manage().timeouts().pageLoadTimeout(300, TimeUnit.SECONDS);

	}

	public static void waitForTextPresent(WebDriver driver,String text)
	{
		WebDriverWait wait = new WebDriverWait(driver, 300 );
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'"+text+"')]")));	
	}

	public static void waitForXpathPresent(WebDriver driver,String xpath)
	{
		WebDriverWait wait = new WebDriverWait(driver, 300 );
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
	}
	public static void waitForIdPresent(WebDriver driver,String Id) throws InterruptedException
	{
		WebDriverWait wait = new WebDriverWait(driver, 300 );
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(Id)));
	}

	public static void waitForElementToBeClickable(WebDriver driver,WebElement element) throws InterruptedException
	{
		WebDriverWait wait = new WebDriverWait(driver, 300 );
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	/*public static void waitForElementToBePresent(WebDriver driver,WebElement element) throws InterruptedException
	{
		WebDriverWait wait = new WebDriverWait(driver, 300 );
		wait.until(ExpectedConditions.presenceOfElementLocated(locator)
	}*/
	public static void actionClick(By elementBy) {
		Actions actions = new Actions(driver);
		actions.moveToElement(driver.findElement(elementBy)).click().perform();
	}
	
	
}
