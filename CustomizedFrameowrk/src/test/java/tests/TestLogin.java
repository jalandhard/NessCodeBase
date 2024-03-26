package tests;

import java.sql.DriverManager;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.TestBase;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import pages.DashboardPage;
import pages.LoginPage;
import utils.PropertyReader;

public class TestLogin extends TestBase{
	
	@Severity(SeverityLevel.CRITICAL)
	@Description("Verify Login to Vwo")
	@Test
	public void loginToVwo() {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.openLoginPage();
		loginPage.loginToVwo();
		takescreenshot("Login to Vwo");
	}
	
	@Severity(SeverityLevel.CRITICAL)
	@Description("Verify logged in user")
	@Test
	public void verifyLoggedInUser() {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.openLoginPage();
		loginPage.loginToVwo();
		DashboardPage dashboardPage = loginPage.afterLogin();
		Assert.assertEquals(dashboardPage.loggedInUser(), PropertyReader.readItem("expectedusername"));
		takescreenshot("Verify logged in user");
	}

}
