package com.wp.general;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Link {

	private WebDriver driver;
	private By locator;
	private String desiredUrl;

	public Link(WebDriver driver, By locator, String desiredUrl) {
		super();
		this.driver = driver;
		this.locator = locator;
		this.desiredUrl = desiredUrl;
	}

	public void click() {
		driver.findElement(locator).click();
	}

	public boolean linksToCorrectPage() {
		//assumes element has already been clicked
		return driver.getCurrentUrl().equalsIgnoreCase(desiredUrl);
	}

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	public By getLocator() {
		return locator;
	}

	public void setLocator(By locator) {
		this.locator = locator;
	}

	public String getDesiredUrl() {
		return desiredUrl;
	}

	public void setDesiredUrl(String desiredUrl) {
		this.desiredUrl = desiredUrl;
	}
	
	public String toString() {
		return "Link locator: " + locator + " Desired URL: " + desiredUrl;
	}

}

