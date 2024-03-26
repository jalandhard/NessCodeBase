package managers;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import enums.DriverType;
import enums.EnvironmentType;

public class WebDriverManager {
	private WebDriver driver;
	private static DriverType driverType;
	private static EnvironmentType environmentType;
	
	public WebDriverManager() {
		driverType = FileReaderManager.getInstance().getConfigReader().getBrowser();
		environmentType = FileReaderManager.getInstance().getConfigReader().getEnvironment();
	}

	public WebDriver getDriver() {
		if(driver == null) driver = createDriver();
		return driver;
	}

	private WebDriver createDriver() {
		   switch (environmentType) {	    
	        case LOCAL : driver = createLocalDriver();
	        	break;
	        case REMOTE : driver = createRemoteDriver();
	        	break;
		   }
		   return driver;
	}

	private WebDriver createRemoteDriver() {
		throw new RuntimeException("RemoteWebDriver is not yet implemented");
	}

	private WebDriver createLocalDriver() {
        switch (driverType) {	    
        case FIREFOX : driver = new FirefoxDriver();
	    	break;
        case CHROME : 
        	ChromeOptions chromeOptions = new ChromeOptions();
    		System.out.println("After ChromeOptions Object Creation");
    		chromeOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);
    		System.out.println("After ChromeOptions Object Creation - Eager");
    		io.github.bonigarcia.wdm.WebDriverManager.chromedriver().setup();
    		System.out.println("After ChromeOptions Object Creation - Web Manager Setup");
    		driver = new ChromeDriver(chromeOptions);
    		driver.manage().window().maximize();
    		break;
        case INTERNETEXPLORER : driver = new InternetExplorerDriver();
    		break;
        }

        if(FileReaderManager.getInstance().getConfigReader().getBrowserWindowSize())
        	driver.manage().window().maximize();
        return driver;
	}
	
	public void closeDriver() {
		driver.close();
		driver.quit();
	}

}