package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RecurrancePage extends HomePage {

	@FindBy(xpath = "//h3")
	WebElement pageHeaderText;
	
	@FindBy(xpath = "//span[text()='Build ']")
	WebElement buildButton;
	
	@FindBy(xpath = "//span[text()='Save ']")
	WebElement saveButton;
	
	@FindBy(xpath = "//span[text()='Close ']")
	WebElement closeButton;
	
	@FindBy(xpath = "(//mat-select)[2]")
	WebElement selectionType;
	
	@FindBy(xpath = "//input[@data-placeholder='Every']")
	WebElement patternEvery;
	
	@FindBy(xpath = "(//section[@class='example-section']/p/mat-checkbox)[1]/label/span/input")
	WebElement sundayCheckboxEnableStatus;
	
	@FindBy(xpath = "(//section[@class='example-section']/p/mat-checkbox)[1]/label/span[1]")
	WebElement sundayCheckbox;
	
	@FindBy(xpath = "(//section[@class='example-section']/p/mat-checkbox)[2]/label/span/input")
	WebElement mondayCheckboxEnableStatus;
	
	@FindBy(xpath = "(//section[@class='example-section']/p/mat-checkbox)[2]/label/span[1]")
	WebElement mondayCheckbox;

	@FindBy(xpath = "(//section[@class='example-section']/p/mat-checkbox)[3]/label/span/input")
	WebElement tuesdayCheckboxEnableStatus;

	@FindBy(xpath = "(//section[@class='example-section']/p/mat-checkbox)[3]/label/span[1]")
	WebElement tuesdayCheckbox;
	
	@FindBy(xpath = "(//section[@class='example-section']/p/mat-checkbox)[4]/label/span/input")
	WebElement wednesdayCheckboxEnableStatus;
	
	@FindBy(xpath = "(//section[@class='example-section']/p/mat-checkbox)[4]/label/span[1]")
	WebElement wednesdayCheckbox;

	@FindBy(xpath = "(//section[@class='example-section']/p/mat-checkbox)[5]/label/span/input")
	WebElement thursdayCheckboxEnableStatus;
	
	@FindBy(xpath = "(//section[@class='example-section']/p/mat-checkbox)[5]/label/span[1]")
	WebElement thursdayCheckbox;

	@FindBy(xpath = "(//section[@class='example-section']/p/mat-checkbox)[6]/label/span/input")
	WebElement fridayCheckboxEnableStatus;
	
	@FindBy(xpath = "(//section[@class='example-section']/p/mat-checkbox)[6]/label/span[1]")
	WebElement fridayCheckbox;

	@FindBy(xpath = "(//section[@class='example-section']/p/mat-checkbox)[7]/label/span/input")
	WebElement saturdayCheckboxEnableStatus;
	
	@FindBy(xpath = "(//section[@class='example-section']/p/mat-checkbox)[7]/label/span[1]")
	WebElement saturdayCheckbox;

	@FindBy(xpath = "//input[@data-placeholder='Ends After']")
	WebElement patternEndsAfter;
	
	@FindBy(xpath = "(//mat-paginator/div/div/div)[1]/mat-form-field/div/div/div/mat-select")
	WebElement itemsPerPageDropdown;
	
	@FindBy(xpath = "(//mat-paginator/div/div/div)[2]/div")
	WebElement rangeText;
	
	@FindBy(xpath = "((//mat-paginator/div/div/div)[2]/button)[1]")
	WebElement navigateToExtremeLeftPage;
	
	@FindBy(xpath = "((//mat-paginator/div/div/div)[2]/button)[2]")
	WebElement navigateToPreviousPage;
	
	@FindBy(xpath = "((//mat-paginator/div/div/div)[2]/button)[3]")
	WebElement navigateToNextPage;
	
	@FindBy(xpath = "((//mat-paginator/div/div/div)[2]/button)[4]")
	WebElement navigateToExtremeRightPage;
	
	public RecurrancePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public String fetchPageHeaderText() {
		return readText(pageHeaderText);
	}
	
	public void clickOnBuildButton() {
		click(buildButton);
	}
	
	public void clickOnSaveButton() {
		click(saveButton);
	}
	
	public void clickOnCloseButton() {
		click(closeButton);
	}
	
	public void selectSelectionType(String selectionType) {
		click(this.selectionType);
		selectMatOptionDropDownByText(selectionType);
		//selectMatOptionDropDownByText(selectionType);
	}
	
	public void enterPatternEvery(String every) {
		clearData(patternEvery);
		writeText(patternEvery, every);
	}
	
	public void enterPatternEndsAfter(String endsAfter) {
		clearData(patternEndsAfter);
		writeText(patternEndsAfter, endsAfter);
	}
	
	public void unselectAllDaysCheckbox() {
		if(checkBoxEnabledStatus(sundayCheckboxEnableStatus))
			click(sundayCheckbox);
		if(checkBoxEnabledStatus(mondayCheckboxEnableStatus))
			click(mondayCheckbox);
		if(checkBoxEnabledStatus(tuesdayCheckboxEnableStatus))
			click(tuesdayCheckbox);
		if(checkBoxEnabledStatus(wednesdayCheckboxEnableStatus))
			click(wednesdayCheckbox);
		if(checkBoxEnabledStatus(thursdayCheckboxEnableStatus))
			click(thursdayCheckbox);
		if(checkBoxEnabledStatus(fridayCheckboxEnableStatus))
			click(fridayCheckbox);
		if(checkBoxEnabledStatus(saturdayCheckboxEnableStatus))
			click(saturdayCheckbox);
	}
	
	public void selectAllDaysCheckbox() {
		if(!checkBoxEnabledStatus(sundayCheckboxEnableStatus))
			click(sundayCheckbox);
		if(!checkBoxEnabledStatus(mondayCheckboxEnableStatus))
			click(mondayCheckbox);
		if(!checkBoxEnabledStatus(tuesdayCheckboxEnableStatus))
			click(tuesdayCheckbox);
		if(!checkBoxEnabledStatus(wednesdayCheckboxEnableStatus))
			click(wednesdayCheckbox);
		if(!checkBoxEnabledStatus(thursdayCheckboxEnableStatus))
			click(thursdayCheckbox);
		if(!checkBoxEnabledStatus(fridayCheckboxEnableStatus))
			click(fridayCheckbox);
		if(!checkBoxEnabledStatus(saturdayCheckboxEnableStatus))
			click(saturdayCheckbox);
	}
	
	public void SelectCustomizedDaysCheckbox(List<String> daysList) {
		unselectAllDaysCheckbox();
		for(String day : daysList) {
			switch (day) {
			case "Sunday":
				if(!checkBoxEnabledStatus(sundayCheckboxEnableStatus))
					click(sundayCheckbox);
				break;
			
			case "Monday":
				if(!checkBoxEnabledStatus(mondayCheckboxEnableStatus))
					click(mondayCheckbox);
				break;
				
			case "Tuesday":
				if(!checkBoxEnabledStatus(tuesdayCheckboxEnableStatus))
					click(tuesdayCheckbox);
				break;
				
			case "Wednesday":
				if(!checkBoxEnabledStatus(wednesdayCheckboxEnableStatus))
					click(wednesdayCheckbox);
				break;
				
			case "Thursday":
				if(!checkBoxEnabledStatus(thursdayCheckboxEnableStatus))
					click(thursdayCheckbox);
				break;
				
			case "Friday":
				if(!checkBoxEnabledStatus(fridayCheckboxEnableStatus))
					click(fridayCheckbox);
				break;
				
			case "Saturday":
				if(!checkBoxEnabledStatus(saturdayCheckboxEnableStatus))
					click(saturdayCheckbox);
				break;

			default:
				logger.info("Invalid Day Passed in the list : {}", day);
				break;
			}
		}
	}

	public SelectionDatesPage closeWindow() {
		driver.close();
		driver.switchTo().window(mainWindowHandle);
		return new SelectionDatesPage(driver);
	}
	
}
