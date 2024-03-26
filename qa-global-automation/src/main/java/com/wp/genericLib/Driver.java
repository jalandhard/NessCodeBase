package com.wp.genericLib;

import java.net.URL;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;



public class Driver {
	public static  WebDriver driver=null;	
	ConfigFileReader configFileReader = new ConfigFileReader();

	public Logger APPLICATION_LOGS = LogManager.getLogger("AppLogger");

	@BeforeClass
	public void initialize() throws Exception{
		
		String driverDir = Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "Drivers").toString();
		//Use the .exe drivers if the os is Windows
		boolean useExe = System.getProperty("os.name").toLowerCase().contains("windows");
		boolean useLinux = System.getProperty("os.name").toLowerCase().contains("linux");
		boolean useMac = System.getProperty("os.name").toLowerCase().contains("mac");

		configFileReader = new ConfigFileReader();
		String browser = configFileReader.getBrowserName();

		//Check if parameter passed from property file is 'firefox'
		if(browser.equalsIgnoreCase("firefox")){
			String firefoxDriverPath = Paths.get(driverDir, "geckodriver" + (useExe ? ".exe" : "")).toString();
			System.setProperty("webdriver.gecko.driver", firefoxDriverPath);
			APPLICATION_LOGS.debug("Initializing the Driver");
			
			//create firefox instance
			driver = new FirefoxDriver();
			//To maximize browser
			driver.manage().window().maximize();
			//Implicit wait
			driver.manage().timeouts().implicitlyWait(300, TimeUnit.SECONDS);

		}
		//Check if parameter passed from property file is 'chrome'
		else if(browser.equalsIgnoreCase("chrome")){
			String chromeDriverPath = Paths.get(driverDir, "chromedriver" + ((useExe ? ".exe" : "")) + ((useLinux ? "linux" : "")) + ((useMac ? "Mac" : ""))).toString();
			System.setProperty("webdriver.chrome.driver", chromeDriverPath);
			APPLICATION_LOGS.debug("Initializing the Driver");
			
			//create chrome instance
			if(useLinux){
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--headless","--disable-extensions", "--no-sandbox", "--disable-gpu", "--window-size=1920,1080");
				driver = new ChromeDriver(options);
			}
			else{
				driver = new ChromeDriver();	
			}
			//To maximize browser
			driver.manage().window().maximize();
			//Implicit wait
			driver.manage().timeouts().implicitlyWait(300, TimeUnit.SECONDS);

		}
		else if(browser.equalsIgnoreCase("ie")){
			//set path to IEDriverServer.exe
			String ieDriverPath = Paths.get(driverDir, "IEDriverServer.exe").toString();
			System.setProperty("webdriver.ie.driver", ieDriverPath);
			APPLICATION_LOGS.debug("Initializing the Driver");
			
			//create ie instance
			driver = new InternetExplorerDriver();
			//To maximize browser
			driver.manage().window().maximize();
			//Implicit wait
			driver.manage().timeouts().implicitlyWait(300, TimeUnit.SECONDS);

		}

		//Check if parameter passed from property file is 'chromeEmulator'
		else if(browser.equalsIgnoreCase("chromeEmulator")){
			String chromeDriverPath = Paths.get(driverDir, "chromedriver.exe").toString();
			System.setProperty("webdriver.chrome.driver", chromeDriverPath);
			Map<String, Object> deviceMetrics = new HashMap<String, Object>();

			deviceMetrics.put("width", 375);

			deviceMetrics.put("height", 812);

			deviceMetrics.put("pixelRatio", 3.0);



			Map<String, Object> mobileEmulation = new HashMap<String, Object>();

			mobileEmulation.put("deviceMetrics", deviceMetrics);

			mobileEmulation.put("userAgent", "Mozilla/5.0 (Linux; Android 4.2.1; en-us; Nexus 5 Build/JOP40D) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.166 Mobile Safari/535.19");



			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
			driver = new ChromeDriver(chromeOptions);
			//To maximize browser
			driver.manage().window().maximize();
			//Implicit wait
			driver.manage().timeouts().implicitlyWait(300, TimeUnit.SECONDS);
		}

		//Check if parameter passed from property file is 'browserStack'
		else if(browser.equalsIgnoreCase("browserStack")){
			String USERNAME="mahesh672";
			String ACCESSKEY="2iatMHtZddFJm4dRqxD4";
			String URL="https://"+USERNAME+":"+ACCESSKEY+"@hub.browserstack.com/wd/hub";

			DesiredCapabilities capability= new DesiredCapabilities();
			capability.setCapability("browserName", "iPhone");
			capability.setCapability("device", "iPhone 8 Plus");
			capability.setCapability("realMobile", "true");
			capability.setCapability("os_version", "11");
			capability.setCapability("name", "Bstack-[Java] Sample Test");

			URL browserStackURL= new URL(URL);

			driver= new RemoteWebDriver(browserStackURL, capability);
		}

		else if(browser.equalsIgnoreCase("cbTest")){
			System.out.println("cb Test starts....");
			String username = ("maheshgouda.patil@ness.com").replaceAll("@", "%40");
			System.out.println("username: "+username);
			String authkey = ("ua1e0ef1cb58f8ca");
			System.out.println("authkey: "+authkey);
			String URL="http://" + username + ":" + authkey +"@hub.crossbrowsertesting.com:80/wd/hub";
			System.out.println("URL: "+URL);


			DesiredCapabilities caps= new DesiredCapabilities();
			caps.setCapability("browserName", "Safari");
			caps.setCapability("deviceName", "iPhone X Simulator");
			caps.setCapability("platformVersion", "11.0");
			caps.setCapability("platformName", "iOS");
			caps.setCapability("deviceOrientation", "portrait");

			URL crossBrowserURL= new URL(URL); 
			driver = new RemoteWebDriver(crossBrowserURL , caps);


		}

		else{
			//If no browser passed throw exception
			throw new Exception("Browser is not correct");
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@AfterClass
	//Test cleanup
	public void TeardownTest()
	{
		if (driver != null){
			driver.quit();
		}
	}

}
