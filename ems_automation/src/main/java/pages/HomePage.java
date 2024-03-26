package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import general.BasePage;

public class HomePage extends BasePage{

	//WebDriver driver;
	
	@FindBy(xpath = "(//div[@id='menu-tab']/button)[1]/span/mat-icon")
	WebElement magazinesMailSchedule;
	
	@FindBy(xpath = "(//div[@id='menu-tab']/button)[2]/span/mat-icon")
	WebElement magazinesInventory;
	
	@FindBy(xpath = "//div[@class='logout-tab']")
	WebElement loggedInUserName;
	
	@FindBy(xpath = "//div[@class='logout-tab']/button")
	WebElement logoutButton;
	
	public HomePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		//Re Initializing the parent driver with Page Object web elements Initialized driver,
		//So that common method would be able to work effectively
		//super.driver = this.driver;
	}
	
	public MagazinesMailSchedule clickOnMagazinesMailSchedule() {
		click(magazinesMailSchedule);
		return new MagazinesMailSchedule(driver);
	}
	
	public MagazinesInventory clickOnMagazinesInventory() {
		click(magazinesInventory);
		return new MagazinesInventory(driver);
	}
	
	public String fetchLoggedInUserName() {
		return readText(loggedInUserName);
	}
	
	public void logoutUser() {
		click(logoutButton);
	}

}
