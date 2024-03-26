package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import general.BasePage;

public class ComboAdjustmentPercentagesPage extends HomePage {
	
	public ComboAdjustmentPercentagesPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

}
