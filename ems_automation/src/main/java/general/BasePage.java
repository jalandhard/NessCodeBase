package general;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import pojo.SelectionDates;
import utils.ConfigFileReader;
import utils.Months;

public class BasePage {
	
	public WebDriver driver;
	public WebDriverWait wait;
	public Logger logger = LogManager.getLogger("AppLogger");
	public static String mainWindowHandle;
	public ConfigFileReader configReader = new ConfigFileReader();
	
	public BasePage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		this.wait = new WebDriverWait(driver,Duration.ofSeconds(30));
	}
	
	public void waitVisibility(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void writeText(WebElement element, String text) {
		waitVisibility(element);
		element.sendKeys(text);
	}
	
	public WebElement getElementWithText(String text) {
		return driver.findElement(By.xpath("//*[contains(text(), '" + text + "')]"));
	}
	
	public void clearData(WebElement element) {
		element.sendKeys(Keys.CONTROL + "A" + Keys.DELETE);
	}
	
	public String readText(WebElement element) {
		String result = "";
		int attempts = 0;
		while(attempts < 3) {
			try {
				result = element.getText();
				break;
			} 
			catch(StaleElementReferenceException expected) {
				//ignore stale element exceptions
			}
			System.out.println("Trying " + attempts + " attempt");
			attempts++;
		}
		//System.out.println("Fetched text from element " + result);
		return result;	
	}
	
	public void scrollToElement(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("arguments[0].scrollIntoView();", element);
		waitVisibility(element);
	}
	
	public boolean elementExists(By elementBy) {
		return getNumberOfElements(elementBy) > 0;
	}
	
	public int getNumberOfElements(By elementBy) {
		return driver.findElements(elementBy).size();
	}
	
	public void refresh() {
		driver.navigate().refresh();
	}
	
	public boolean click(WebElement element) {
		int attempts = 0;
		boolean result = false;
		scrollToElement(element);
		while(attempts < 3) {
			try {
				element.click();
				result = true;
				break;
			} 
			catch(ElementClickInterceptedException e) {
				//closeBounceExchange();
			}
			catch(StaleElementReferenceException expected) {
				//ignore stale element exceptions
			}
			attempts++;
		}
		return result;
	}
	
	public void goBack() {
		logger.info("URL before clicking back: {}",driver.getCurrentUrl());
		logger.info("Going Back!!!!!");
		driver.navigate().back();
		logger.info("URL after clicking back: {}",driver.getCurrentUrl());
	}
	
	public void goForward() {
		logger.info("URL before clicking forward: {}",driver.getCurrentUrl());
		logger.info("Going Forward!!!!!");
		driver.navigate().forward();
		logger.info("URL after clicking forward: {}",driver.getCurrentUrl());
	}
	
	public String getUrl() {
		return driver.getCurrentUrl();
	}
	
	public void selectDropdownByName(WebElement element, String name) {
		waitVisibility(element);
		Select dropdown = new Select(element);
		dropdown.selectByVisibleText(name);
	}
	
	public void selectMatOptionDropDownByText(String text) {
		waitVisibility(driver.findElement(By.xpath("//span[@class='mat-option-text']")));
		List<WebElement> optionsList = driver.findElements(By.xpath("//span[@class='mat-option-text']"));
		//System.out.println("Number of elements for text : " + text + " is " + optionsList.size());
		for(WebElement element : optionsList) {
			//System.out.println(element.getText() + " compared with " + text);
			if(element.getText().equals(text)) {
				element.click();
				break;
			}
		}
	}
	
	public void selectYearOnCalender(int year) {
		waitVisibility(driver.findElement(By.xpath("(//mat-calendar/mat-calendar-header/div/div/button)[1]/span/span")));
		WebElement backArrow = driver.findElement(By.xpath("(//mat-calendar/mat-calendar-header/div/div/button)[2]"));
		WebElement forwardArrow = driver.findElement(By.xpath("(//mat-calendar/mat-calendar-header/div/div/button)[3]"));

		while(true) {
			String yearRangeShowing = driver.findElement(By.xpath("(//mat-calendar/mat-calendar-header/div/div/button)[1]/span/span")).getText();
			String firstYearInCalender = yearRangeShowing.substring(0, 4);
			String lastYearInCalender = yearRangeShowing.substring(7);
			System.out.println("Years data fetched : [" + firstYearInCalender + "," + lastYearInCalender + "] comparing with " + year);
			if(year < Integer.parseInt(firstYearInCalender))
				click(backArrow);
			else if(year > Integer.parseInt(lastYearInCalender))
				click(forwardArrow);
			else
				break;
		}
		WebElement yearLocator = driver.findElement(By.xpath("//div[text()=' " + year + " ']"));
		click(yearLocator);
		//yearLocator.click();
	}
	
	public void selectMonthOnCalender(int month) {
		String monthText = Months.values()[month - 1].toString();
		System.out.println("Month value being passed : " + month + " respective month text fetched from enum : " + monthText);
		WebElement monthLocator = driver.findElement(By.xpath("//div[text()=' " + monthText + " ']"));
		click(monthLocator);
		//monthLocator.click();
	}
	
	public void selectDayOnCalender(int day) {
		WebElement dayLocator = driver.findElement(By.xpath("//div[text()=' " + day + " ']"));
		click(dayLocator);
	}
	
	public void clickOnCalenderIcon() {
		WebElement calenderIconLocator = driver.findElement(By.xpath("//button[@aria-label='Choose month and year']/span/span"));
		click(calenderIconLocator);
	}
	
	public boolean checkBoxEnabledStatus(WebElement element) {
		waitVisibility(element);
		String ariaCheckedText = element.getAttribute("aria-checked");
		return ariaCheckedText.equals("true");
	}
	
	public Map<String, ArrayList<String>> fetchAllDatafilteredOut() {
		Map<String, ArrayList<String>> completeSetOfFileteredData = new HashMap<>();
		ArrayList<String> arrayList = new ArrayList<>();
		WebElement pageRangeElement = driver.findElement(By.xpath("//div[@class='mat-paginator-range-actions']/div"));
		WebElement nextPageElement = driver.findElement(By.xpath("//button[@aria-label='Next page']"));
		String pageRange = readText(pageRangeElement);
		int initialCount = 1;
		int internalCount = 1;
		int totalNumberOfData = Integer.parseInt((pageRange.split("of")[1]).trim());
		System.out.println("Text Being fetched is : " + pageRange);
		List<WebElement> elements = driver.findElements(By.xpath("//table/thead/mat-header-row/mat-header-cell"));
		int numberOfCells = elements.size();
		System.out.println("Number Of Cells : " + numberOfCells);
		for(int i = 1; i < numberOfCells; i++) {
			if(driver.findElement(By.xpath("//table/thead/mat-header-row/mat-header-cell[" + (i + 1) + "]")).getAttribute("hidden") == null) {
				String headerText = driver.findElement(By.xpath("//table/thead/mat-header-row/mat-header-cell[" + (i + 1) + "]/div/div")).getText();
				System.out.println("Header Text value is : " + headerText + " With Index : " + (i + 1));
				if(!headerText.trim().isEmpty())
					arrayList.add(headerText);
			}
		}
		System.out.println("List Of Header : " + arrayList.toString());
		completeSetOfFileteredData.put("Headers", arrayList);
		//int firstPageCount = driver.findElements(By.xpath("//table/tbody/mat-row")).size();
		//System.out.println("Total data available : " + totalNumberOfData + " and Total data available in first page " + firstPageCount);
		ArrayList<String> listOfMatIconHeaders = configReader.getMatIconHeaderData();
		
		while (totalNumberOfData > 0 && initialCount <= totalNumberOfData) {
			//String range = readText(pageRangeElement).split("of")[0];
			//System.out.println("Range Text inside Loop : " + range);
			//String[] strs = range.split("\\?");
			//System.out.println("Range size after splitting : " + strs.length);
			int pageDataCount = driver.findElements(By.xpath("//table/tbody/mat-row")).size();
			
			ArrayList<String> rowData = new ArrayList<>();
			int totalItemsRead = completeSetOfFileteredData.size() - 1;
			if(totalItemsRead > 0 && totalItemsRead%pageDataCount == 0) {
				click(nextPageElement);
				internalCount = 1;
			}
			String rowText = "RowNumber" + initialCount;
			System.out.println("Total Number Of Data : " + totalNumberOfData + " , Initial Count : " + initialCount + " , Internal Count : " + internalCount + " , Reading Data with Row Text : " + rowText);
			int indexCount = 0;
			for(int cellCount = 2; cellCount <= numberOfCells; cellCount++) {
				if(driver.findElement(By.xpath("//table/tbody/mat-row[" + internalCount + "]/mat-cell["+ cellCount + "]")).getAttribute("hidden") == null) {
					String headerText = arrayList.get(indexCount).replaceAll(" ", "");
					System.out.println("Comparing : " + listOfMatIconHeaders.toString() + " with Header Text : " + headerText);
					if(listOfMatIconHeaders.contains(headerText)) {
						System.out.println("Inside Mat-Icon Element");
						WebElement matIconElement = driver.findElement(By.xpath("//table/tbody/mat-row[" + internalCount + "]/mat-cell["+ cellCount + "]/mat-icon"));
						rowData.add(matIconElement.getText().contains("circle") ? "true" : "false");
					} else {
						System.out.println("Inside Mat-Label Element");
						WebElement matLabelElement = driver.findElement(By.xpath("//table/tbody/mat-row[" + internalCount + "]/mat-cell["+ cellCount + "]/mat-label"));
						rowData.add(matLabelElement.getText());
					}
					System.out.println(rowData.toString());
					indexCount++;
				}
			}
			completeSetOfFileteredData.put(rowText, rowData);
			internalCount ++;
			initialCount ++;

		}
		
		return completeSetOfFileteredData;
	}

}
