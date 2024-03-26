package com.wp.general;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Page extends BasePage {

	public Page (WebDriver driver, WebDriverWait wait, String url) {
		super(driver, wait, url);
	}


	//Go to Homepage
	public Page goToHome(){
		driver.get(url);
		return this;
	} 

	
	
}