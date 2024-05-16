package base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import utils.WaitForHelper;

public class BasePage {
	
	//driver declaration and initialization
	//wait for element
	//wait for item
	//click
	//write text
	//read text
	//move to element
	
	protected WebDriver driver;
	
	public BasePage(WebDriver driver) {
		this.driver = driver;
	}
	
	//navigate
	public void goToUrl(String url) {
		driver.get(url);
	}
	
	public void waitForElementToAppear(By elementLocation) {
		new WaitForHelper(driver).presenceOfElement(elementLocation);
	}
	
	public void waitForLoading() {
		new WaitForHelper(driver).implicitWait();
	}
	
	public void click(By elementLocation) {
		driver.findElement(elementLocation).click();
	}
	
	public void writeText(By elementLocation, String text) {
		driver.findElement(elementLocation).clear();
		driver.findElement(elementLocation).sendKeys(text);
	}
	
	public String readText(By elementLocation) {
		return driver.findElement(elementLocation).getText();
	}
	
	public void moveToElement(By elementLocation) {
		new Actions(driver).moveToElement(driver.findElement(elementLocation)).build().perform();
	}

}
