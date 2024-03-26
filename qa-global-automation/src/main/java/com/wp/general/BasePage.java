package com.wp.general;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class BasePage {
	public WebDriver driver;
	public WebDriverWait wait;

	protected String url;

	public BasePage(WebDriver driver, WebDriverWait wait, String url) {
		this.driver = driver;
		this.url = url;
		this.wait = wait;
	}

	/**
	 * Waits for the element located by the passed By to become visible
	 * @param elementBy the By to locate the element to be waited for
	 */
	public void waitVisibility(By elementBy) {
		waitVisibility(driver.findElement(elementBy));
	}
	
	public void waitVisibility(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	/**
	 * Waits for the element located by the passed By to become invisible
	 * @param elementBy the By to locate the element to be waited for
	 */
	public void waitInvisibility(By elementBy) {
		wait.until(ExpectedConditions.invisibilityOfElementLocated(elementBy));
	}
	
	private void closeBounceExchange() {
		By bounceExchangeClose = By.xpath("//div[@class='bx-wrap']/a");
		
		try {
			if(driver.findElement(bounceExchangeClose).isDisplayed())
				driver.findElement(bounceExchangeClose).click();
		}
		catch(NoSuchElementException nsee) {
			//ignore
		}
	}

	/**
	 * Writes text to an element
	 * @param elementBy the By to locate the element to be written to
	 * @param text the text to be written
	 */
	public void writeText(By elementBy, String text) {
		waitVisibility(elementBy);
		driver.findElement(elementBy).sendKeys(text);
	}
	
	public WebElement getElementWithText(String text) {
		return driver.findElement(By.xpath("//*[contains(text(), '" + text + "')]"));
	}

	/**
	 * Retrieves the text contained by an element
	 * @param elementBy the By to locate the element
	 * @return the String contained in the found element
	 */
	public String readText(By elementBy) {
		String result = "";
		int attempts = 0;
		while(attempts < 3) {
			try {
				result = driver.findElement(elementBy).getText();
				break;
			} 
			catch(StaleElementReferenceException expected) {
				//ignore stale element exceptions
			}
			attempts++;
		}
		return result;
		
	}

	/**
	 * Scrolls the driver to an element
	 * @param elementBy the By to locate the element to be scrolled to
	 */
	public void scrollToElement(By elementBy) {
		scrollToElement(driver.findElement(elementBy));
	}
	
	public void scrollToElement(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("arguments[0].scrollIntoView();", element);
		waitVisibility(element);
	}

	/**
	 * Determines whether an element exists or not
	 * @param elementBy the By to locate the element in question
	 * @return true whether the element exists, false otherwise
	 */
	public boolean elementExists(By elementBy) {
		return getNumberOfElements(elementBy) > 0;
	}
	
	public void refresh() {
		driver.navigate().refresh();
	}
	
	public void click(String linkText) {
		By locator = By.xpath("//*[text()='" + linkText + "']");
		click(locator);	
	}
	
	/**
	 * Clicks an element located by the passed By, ignoring StaleElementExceptions
	 * @param elementBy the By to locate the element to be clicked
	 * @return true if the click was successful, false otherwise
	 */
	public boolean click(By elementBy) {
		scrollToElement(elementBy);
		return click(driver.findElement(elementBy));
	}
	
	public boolean click(WebElement element) {
		int attempts = 0;
		
		boolean result = false;
		
		while(attempts < 3) {
			try {
				element.click();
				result = true;
				break;
			} 
			catch(ElementClickInterceptedException e) {
				closeBounceExchange();
			}
			catch(StaleElementReferenceException expected) {
				//ignore stale element exceptions
			}
			attempts++;
		}
		
		return result;
	}
	
	public WebElement getElement(By elementBy) {
		if(elementExists(elementBy))
			return driver.findElement(elementBy);
		
		throw new NoSuchElementException("The element located by " + elementBy + " does not exist");
	}
	
	public List<WebElement> getElements(By elementsBy) {
		return driver.findElements(elementsBy);
	}
	
	public void goBack() {
		System.out.println("Going back...");
		System.out.println("Current URL: " + driver.getCurrentUrl());
		
		driver.navigate().back();
		
		System.out.println("URL after going back: " + driver.getCurrentUrl());
	}

	/**
	 * Gets the number of elements on the page, located by the passed By
	 * @param elementBy the By to locate the element(s)
	 * @return the number of elements found
	 */
	public int getNumberOfElements(By elementBy) {
		return driver.findElements(elementBy).size();
	}
	
	//Getters and Setters
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public String toString() {
		return "Page on URL: " + url;
	}

}