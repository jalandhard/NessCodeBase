package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import general.BasePage;

public class MagazinesMailSchedule extends HomePage {
	
	@FindBy(xpath = "//button[@routerlink='/categories']")
	WebElement categories;
	
	@FindBy(xpath = "//button[@routerlink='/combo-adjustment-percentages']")
	WebElement comboAdjustmentPercentages;
	
	@FindBy(xpath = "//button[@routerlink='/efforts']")
	WebElement efforts;
	
	@FindBy(xpath = "//button[@routerlink='/export-offer']")
	WebElement exportOffers;
	
	@FindBy(xpath = "//button[@routerlink='/export-package']")
	WebElement exportPackages;
	
	@FindBy(xpath = "//button[@routerlink='/host-target-magazine']")
	WebElement hostTargetMagazine;
	
	@FindBy(xpath = "//button[@routerlink='/offers']")
	WebElement offers;
	
	@FindBy(xpath = "//button[@routerlink='/packages']")
	WebElement packages;
	
	@FindBy(xpath = "//button[@routerlink='/segments']")
	WebElement segments;
	
	@FindBy(xpath = "//button[@routerlink='/selections']")
	WebElement selections;
	
	@FindBy(xpath = "//button[@routerlink='/selectionDates']")
	WebElement selectionDates;
		
	public MagazinesMailSchedule(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public CategoriesPage goToCategoriesPage() {
		click(categories);
		int retryCount = 3;
		int i = 1;
		while(i <= retryCount) {
			if(driver.getCurrentUrl().indexOf("categories") != -1)
				break;
			else
				i++;
		}
		if(i <= retryCount)
			return new CategoriesPage(driver);
		else
			return null;
	}
	
	public SelectionsPage goToSelectionsPage() {
		click(selections);
		int retryCount = 3;
		int i = 1;
		while(i <= retryCount) {
			if(driver.getCurrentUrl().indexOf("selections") != -1)
				break;
			else
				i++;
		}
		if(i <= retryCount)
			return new SelectionsPage(driver);
		else
			return null;
	}
	
	public SelectionDatesPage goToSelectionDatesPage() {
		click(selectionDates);
		int retryCount = 3;
		int i = 1;
		while(i <= retryCount) {
			if(driver.getCurrentUrl().indexOf("selectionDates") != -1)
				break;
			else
				i++;
		}
		if(i <= retryCount)
			return new SelectionDatesPage(driver);
		else
			return null;
	}

}
