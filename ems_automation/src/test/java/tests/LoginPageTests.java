package tests;

import org.testng.annotations.Test;

import general.BaseTest;
import pages.LoginPage;

public class LoginPageTests extends BaseTest {
	
	@Test
	public void loginToEMSPortal() {
		driver.get(configFileReader.getLoginUrl());
		loginPage = new LoginPage(driver);
		loginPage.enterUserId(configFileReader.getLoginUserName());
		loginPage.enterPassword(configFileReader.getLoginPassword());
		homePage = loginPage.clickOnLoginButton();
	}

}
