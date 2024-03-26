package com.wp.general;

import java.lang.reflect.Method;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITest;
import org.testng.annotations.BeforeMethod;

import com.wp.genericLib.ConfigFileReader;


public abstract class BaseTest implements ITest {

	protected ThreadLocal<String> testName = new ThreadLocal<String>();
	
    public WebDriver driver;
    public WebDriverWait wait;
    protected Actions actions;
    
    protected String url;
    protected Page page;
    
    protected By entirePageLocator = By.tagName("html");
 
    public void driverSetup() {
    	String driverDir = Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "Drivers").toString();
		boolean isWindows = System.getProperty("os.name").toLowerCase().contains("windows");
	    	boolean isLinux = System.getProperty("os.name").toLowerCase().contains("linux");
		
		ConfigFileReader configFileReader = new ConfigFileReader();
		String browser = configFileReader.getBrowserName();
		
		
		if(browser.equals("chrome")) {
		
			String chromeDriverPath = Paths.get(driverDir, "chromedriver" + (isWindows ? ".exe" : "") + (isLinux ? "linux" : "")).toString();
			System.setProperty("webdriver.chrome.driver", chromeDriverPath);
			
			ChromeOptions options = new ChromeOptions();
			if(isLinux) {
	    			options.addArguments("--headless","--disable-extensions", "--no-sandbox", "--disable-gpu", "--window-size=1920,1080");
	    		}
			LoggingPreferences logPrefs = new LoggingPreferences();
			logPrefs.enable(LogType.BROWSER, Level.INFO);
			
			options.setCapability("goog:loggingPrefs", logPrefs);
			
			driver = new ChromeDriver(options);
			
			actions = new Actions(driver);
			
	        wait = new WebDriverWait(driver, 60); 
	        driver.manage().window().maximize();
	        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}
		else if(browser.equals("chromeEmulator")) {
			String chromeDriverPath = Paths.get(driverDir, "chromedriver" + (isWindows ? ".exe" : "")).toString();
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
    }
    
    @BeforeMethod
	public void BeforeMethod(Method method) {
		testName.set(method.getName() + "_" + getBrand(url));
		
		if(driver.getCurrentUrl().equals("data:,"))
			driver.get(page.getUrl());
		
		closeBounceExchange();
	}
 
    public void teardown () {
        driver.quit();
    }
    
    private void closeBounceExchange() {
		By bounceExchangeClose = By.xpath("//div[@class='bx-wrap']/a");
		
		try {
			driver.findElement(bounceExchangeClose).click();
		}
		catch(Exception e) {
			//ignore
		}
	}
    
    /**
	 * This method checks every open tab, and returns whether one of the tabs is on the passed URL. This method will change what tab the driver is on, but will always finish execution
	 * on the original tab the driver was on when the method was called. This method does not click anything.
	 * @param targetUrl the desired URL for one of the tabs to be on.
	 * @return true if one of the tabs is on targetUrl, false otherwise.
	 */
	protected boolean matchTabUrl(String targetUrl) {
		//save current window handle to switch back to later
		String currentHandle = driver.getWindowHandle();

		//get all tab handles
		List<String> handles = new ArrayList<String>(driver.getWindowHandles());

		for(String handle : handles) {
			driver.switchTo().window(handle);
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(entirePageLocator));

			//found a match
			if(driver.getCurrentUrl().contains(targetUrl)) {
				//make sure we dont close our own tab
				if(!currentHandle.equals(handle)) {
					driver.close();
					driver.switchTo().window(currentHandle);
				}
				return true;
			}
		}

		driver.switchTo().window(currentHandle);
		return false;
	}
	
	
	/**
	 * Determines if the URL the WebDriver is currently on is a 404 page on a TMBI site. Only works for TMBI sites.
	 * @return true if the page is a 404, false otherwise
	 */
	protected boolean is404Page() {
		final String ERROR_404_TEXT = "Error 404: Page not found";
		final String PAGE_TYPE_404_TEXT = "404Page";

		String pageSource = driver.getPageSource();

		return pageSource.contains(ERROR_404_TEXT) || pageSource.contains(PAGE_TYPE_404_TEXT);
	}
	
	/**
	 * Determines if the passed URL is a PUP site
	 * @param url the URL to be checked
	 * @return true if this URL's brand is a PUP site, false otherwise
	 */
	public boolean isPup(String url) {
		String brand = getBrand(url);
		
		/*
		 	This needs to be updated every time we add a new site to PUP.
		*/
		String pupBrands[] = {"RD", "CPT", "FHM", "HLT"};
		
		//this return is the same as: return pupBrands.contains(brand)
		return Arrays.stream(pupBrands).anyMatch(brand::equals);
	}
	
	/**
	 * Determines if the passed URL is a PUP site
	 * @param url the URL to be checked
	 * @return true if this URL's brand is a PUP site, false otherwise
	 */
	public boolean isCanadian(String url) {
		String brand = getBrand(url);

		String caBrands[] = {"BHC", "RDC", "SRD"};
		
		//this return is the same as: return pupBrands.contains(brand)
		return Arrays.stream(caBrands).anyMatch(brand::equals);
	}
	
	
	/**
	 * Returns the brand (FHM, CPT, etc) of this page objects URL
	 * @return the brand of this page
	 */
	public String getBrand(String url) {
		if(url.contains("familyhandyman"))
			return "FHM";
		
		else if(url.contains("tasteofhome"))
			return "TOH";
		
		else if(url.contains("rd.com"))
			return "RD";
		
		else if(url.contains("constructionprotips"))
			return "CPT";
		
		else if(url.contains("besthealthmag"))
			return "BHC";
		
		else if(url.contains("selection"))
			return "SRD";
		
		else if(url.contains("readersdigest"))
			return "RDC";
		
		else if(url.contains("thehealthy"))
			return "HLT";
		
		else if(url.contains("birdsandblooms.com"))
			return "BNB";
		
		//throw exception if url wasn't a TMBI brand
		throw new IllegalArgumentException("Passed URL is not a TMBI URL");
	}
	
	/**
	 * Returns the site prefix that is displayed for ad units
	 * @param url the site
	 * @return the ad unit prefix
	 */
	protected String getSitePrefix(String url) {
		String brand = getBrand(url);
		
		if(brand.equals("RD"))
			return "rdg";
		
		if(brand.equals("TOH"))
			return "toh";
		
		if(brand.equals("FHM"))
			return "fhm";
		
		if(brand.equals("HLT"))
			return "hlt";
		
		if(brand.equals("BNB"))
			return "bnb";
		
		if(brand.equals("RDC"))
			return "rdca";
		
		if(brand.equals("BHC"))
			return "bhc";
		
		if(brand.equals("SRD"))
			return "srd";
		
		//throw exception if url wasn't a TMBI brand
		throw new IllegalArgumentException("Passed URL is not a TMBI URL");
	}
	
	/**
	 * Returns whether the passed web element has a specific class
	 * @param element the element to check
	 * @param active the class
	 * @return true if the web element has active as a class, false otherwise
	 */
	protected boolean elementHasClass(WebElement element, String active) {
		String classes = element.getAttribute("class");
		for (String c : classes.split(" ")) {
			if (c.equals(active)) {
				return true;
		    }
		}

		return false;
	}
	
	/**
	 * Returns the domain URL from a passed URL. Example: https://www.rd.com/videos -> https://www.rd.com/
	 * @param url the url
	 * @return the domain URL
	 * @throws URISyntaxException
	 */
	protected String getDomainUrl(String url) throws URISyntaxException {
		URI uri = new URI(url);
		
		String domain = uri.getHost();
		
		String protocol;
		
		if(url.contains("https://"))
			protocol = "https://";
		else
			protocol = "http://";
		
	    return protocol + uri.getHost();
	}
	
	protected void continiousScroll() {
		final int SCROLLS = 5;
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		for(int i = 0; i < SCROLLS; i++) {
			js.executeScript("window.scrollBy(0,1000)");
		}
	}
	
	/**
	 * Scrolls the driver to an element
	 * @param elementBy the By to locate the element to be scrolled to
	 */
	public void scrollToElement(By elementBy) {
		scrollToElement(driver.findElement(elementBy));
	}
	
	public void scrollToElement(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("arguments[0].scrollIntoView();", element);
	}
	
	/**
	 * Gets the number of elements on the page, located by the passed By
	 * @param elementBy the By to locate the element(s)
	 * @return the number of elements found
	 */
	public int getNumberOfElements(By elementBy) {
		return driver.findElements(elementBy).size();
	}
	
	/**
	 * Determines whether an element exists or not
	 * @param elementBy the By to locate the element in question
	 * @return true whether the element exists, false otherwise
	 */
	public boolean elementExists(By elementBy) {
		return getNumberOfElements(elementBy) > 0;
	}
	
	/**
	 * Switches to the google force console
	 * @throws Exception
	 */
	protected void switchToFrame() throws Exception{

		Thread.sleep(5000);
		List<WebElement> iframes = driver.findElements(By.xpath("//iframe[@width='100%']"));
		System.out.println("Found " + iframes.size() + " iframes");
		for(int i=0;i<=iframes.size()-1;i++){
			String frameID=	iframes.get(i).getAttribute("id");


			driver.switchTo().frame(frameID);

			System.out.println("Switching to iframe");

			System.out.println("GOOGFC: " + elementExists(By.xpath("//div[contains(text(),'Page Request')]")));
			if(elementExists(By.xpath("//div[contains(text(),'Page Request')]"))) {
				
				break;

			}
			else{
				driver.switchTo().defaultContent();
			}


		}
	}
	
	/**
	 * A helper method for data providers. Takes a list as an argument and returns a 2D Object array.
	 * @param list the list to be converted
	 * @return the 2D array of list
	 */
	protected <T> Object[][] to2DArray(List<T> list) {
		
		Object[][] result = new Object[list.size()][1];
		
		for(int i = 0; i < list.size(); i++) {
			result[i][0] = list.get(i);
		}
		
		return result;
	}
}
