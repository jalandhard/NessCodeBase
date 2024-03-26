package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import general.BasePage;

public class LoginPage extends BasePage{

	//WebDriver driver;
	
	@FindBy(id = "userId")
	WebElement userId;
	
	@FindBy(id = "password")
	WebElement password;
	
	@FindBy(xpath = "//span[text()='Login']")
	WebElement loginButton;
	
	public LoginPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		//super.driver = this.driver;
	}
	
	public void enterUserId(String userId) {
		writeText(this.userId, userId);
	}
	
	public void enterPassword(String password) {
		writeText(this.password, password);
	}
	
	public HomePage clickOnLoginButton() {
		click(loginButton);
		//Create subsequent page object and return back(in this case, its HomePage)
		HomePage homePage = new HomePage(driver);
		return homePage;
	}

}
