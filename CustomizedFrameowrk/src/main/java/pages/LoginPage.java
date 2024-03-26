package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import base.BasePage;
import utils.PropertyReader;

public class LoginPage extends BasePage {
	
	WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	
	//Page Locators
	By username = By.id("login-username");
	By password = By.id("login-password");
	By signInButton = By.id("js-login-btn");
	
	//Page Actions
	public void openLoginPage() {
		goToUrl(PropertyReader.readItem("url"));
	}
	
	public void loginToVwo() {
		writeText(username, PropertyReader.readItem("username"));
		writeText(password, PropertyReader.readItem("password"));
		click(signInButton);
	}
	
	public DashboardPage afterLogin() {
		return new DashboardPage(driver);
	}
}
