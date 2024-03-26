package general;

import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import pages.CategoriesPage;
import pages.ComboAdjustmentPercentagesPage;
import pages.EffortsPage;
import pages.ExportOffersPage;
import pages.ExportPackagesPage;
import pages.HomePage;
import pages.HostTargetMagazinePage;
import pages.LoginPage;
import pages.MagazinesInventory;
import pages.MagazinesMailSchedule;
import pages.OffersPage;
import pages.PackagesPage;
import pages.RecurrancePage;
import pages.SegmentsPage;
import pages.SelectionDatesPage;
import pages.SelectionsPage;
import pages.ToggleFilter;
import utils.ConfigFileReader;
import utils.DBManager;

public class BaseTest {
	
	//Declaring all PageClass as static variable, so that we can use page objects in any of test classes
	public static LoginPage loginPage;
	public static HomePage homePage;
	public static MagazinesMailSchedule magazinesMailSchedule;
	public static MagazinesInventory magazinesInventory;
	public static CategoriesPage categoriesPage;
	public static ComboAdjustmentPercentagesPage comboAdjustmentPercentagesPage;
	public static EffortsPage effortsPage;
	public static ExportOffersPage exportOffersPage;
	public static ExportPackagesPage exportPackagesPage;
	public static HostTargetMagazinePage hostTargetMagazinePage;
	public static OffersPage offersPage;
	public static PackagesPage packagesPage;
	public static SegmentsPage segmentsPage;
	public static SelectionDatesPage selectionDatesPage;
	public static SelectionsPage selectionsPage;
	public static ToggleFilter toggleFilter;
	public static RecurrancePage recurrancePage;
	
	public WebDriver driver;
	public Logger logger = LogManager.getLogger("AppLogger");
	public ConfigFileReader configFileReader = new ConfigFileReader();
	
	@BeforeSuite
	public void setUp() {
		//Instantiating the driver
		String driverDir = Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "Drivers").toString();
		
		String browser = configFileReader.getBrowserName();
		boolean isWindows = configFileReader.getOSName().equals("windows")?true:false;
	    boolean isLinux = configFileReader.getOSName().equals("linux")?true:false;		
		
		if(browser.equals("chrome")) {
			logger.info("Starting chrome Driver!!!!!");
			String chromeDriverPath = Paths.get(driverDir, "chromedriver" + (isWindows ? ".exe" : "") + (isLinux ? "linux" : "")).toString();
			logger.debug("Driver path constructed is {}", chromeDriverPath);
			System.out.println("Driver path constructed is " + chromeDriverPath);
			System.setProperty("webdriver.chrome.driver", chromeDriverPath);
			
			ChromeOptions options = new ChromeOptions();
			if(isLinux) {
	    			options.addArguments("--headless","--disable-extensions", "--no-sandbox", "--disable-gpu", "--window-size=1920,1080");
	    		}
			//LoggingPreferences logPrefs = new LoggingPreferences();
			//logPrefs.enable(LogType.BROWSER, Level.INFO);
			
			//options.setCapability("goog:loggingPrefs", logPrefs);
			
			driver = new ChromeDriver(options);
			
			driver.manage().window().maximize();
	        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}
		else if(browser.equals("firefox")) {
			logger.info("Starting Firefox Driver!!!!!");
			String firefoxDriverPath = Paths.get(driverDir, "geckodriver" + (isWindows ? ".exe" : "")).toString();
			logger.info("Driver path constructed is {}", firefoxDriverPath);
			System.setProperty("webdriver.gecko.driver", firefoxDriverPath);
			
			//create firefox instance
			driver = new FirefoxDriver();
			//To maximize browser
			driver.manage().window().maximize();
			//Implicit wait
			driver.manage().timeouts().implicitlyWait(300, TimeUnit.SECONDS);
		}
		else if(browser.equals("IE")) {
			logger.info("Starting IE Driver!!!!!");
			String ieDriverPath = Paths.get(driverDir, "IEDriverServer.exe").toString();
			logger.info("Driver path constructed is {}", ieDriverPath);
			System.setProperty("webdriver.ie.driver", ieDriverPath);
			
			//create ie instance
			driver = new InternetExplorerDriver();
			//To maximize browser
			driver.manage().window().maximize();
			//Implicit wait
			driver.manage().timeouts().implicitlyWait(300, TimeUnit.SECONDS);
		}
		else {
			logger.info("Browser value configured in properties file is not matching with either chrome, firefox or IE. So exiting the flow abruptly!!!!!");
			System.exit(0);
		}
	}
	
	@AfterSuite
	public void tearDown() {
		//Closing all windows opened by driver
		//driver.quit();
		homePage.logoutUser();
		logger.info("Closed all windows associated with driver");
		//closing the DB Connection
		//if(DBManager.getInstance().getDBConnection() != null)
			//DBManager.getInstance().closeDBConnection();
		logger.info("Closed DB Connection");
		
	}

}
