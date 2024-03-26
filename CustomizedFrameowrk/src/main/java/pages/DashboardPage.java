package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import base.BasePage;

public class DashboardPage extends BasePage{

	WebDriver driver;
	
	public DashboardPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	
	//Page Locators
	By username = By.xpath("//p[@class='page-sub-title']/span[1]");
	
	//Page Actions
	public String loggedInUser() {
		waitForElementToAppear(username);
		return readText(username);
	}

}
