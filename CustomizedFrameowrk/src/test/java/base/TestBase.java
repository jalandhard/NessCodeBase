package base;

import java.io.ByteArrayInputStream;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.qameta.allure.Allure;
import utils.CustomLogging;
import utils.DriverManager;
import utils.PropertyReader;

public class TestBase extends DriverManager{
	
	//Common Functionalities like setup and teardown
	
	public WebDriver driver;
	PropertyReader pr = new PropertyReader();
	
	public TestBase() {
		this.driver = super.getDriver();
	}
	
	@BeforeMethod(alwaysRun = true)
	public void setup() {
		CustomLogging logger = new CustomLogging();
		//Read from properties file and set the webdriver
		try {
			if(PropertyReader.readItem("browser").equalsIgnoreCase("chrome")) {
				System.out.println("Initializing firefox driver");
				//ChromeOptions options = new ChromeOptions();
				System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir") + "/src/main/resources/chromedriver.exe");
				driver = new ChromeDriver();
				driver.manage().window().maximize();
				System.out.println("Done with initialization");
			}
			else {
				try {
					throw new Exception("Browser Driver is not supported");
				}catch (Exception e) {
					logger.info("No Compatible Browser Found");
				}
			}
		}
		catch (Exception e) {
			logger.info("Browser Launch Error");
		}
	}
	
	@AfterMethod(alwaysRun = true)
	public void teardown() {
		driver.quit();
	}
	
	public void takescreenshot(String name) {
		Allure.addAttachment(name, new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
	}

}
