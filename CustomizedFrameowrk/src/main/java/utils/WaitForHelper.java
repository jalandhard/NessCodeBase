package utils;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitForHelper {
	
	public WebDriver driver;
	
	public WaitForHelper(WebDriver driver) {
		this.driver = driver;
	}
	
	@SuppressWarnings("deprecation")
	public void implicitWait() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	public WebElement presenceOfElement(final By elementLocation) {
		return new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.presenceOfElementLocated(elementLocation));
	}
	
	public WebElement elementToBeClickable(final By elementLocation) {
		return new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(elementLocation));
	}

}
