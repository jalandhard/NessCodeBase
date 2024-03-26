package pages;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pojo.SelectionDates;

public class SelectionDatesPage extends HomePage{
	
	@FindBy(xpath = "//div[@class='page-title']")
	WebElement pageTitle;
	
	@FindBy(xpath = "//div[@class='page-title']/../../button[1]")
	WebElement newAdditionButton;
	
	@FindBy(xpath = "//div[@class='page-title']/../../button[2]")
	WebElement openButton;
	
	@FindBy(xpath = "//span[text()=' Delete ']")
	WebElement deleteButtonOnOpenOverlay;
	
	@FindBy(xpath = "//span[text()=' Close ']")
	WebElement closeButtonOnAllOverlays;
	
	@FindBy(xpath = "//div[@class='page-title']/../../button[3]")
	WebElement copyButton;
	
	@FindBy(xpath = "//div[@class='page-title']/../../a")
	WebElement recurranceButton;
	
	@FindBy(xpath = "//div[@class='page-title']/../../button[4]")
	WebElement deleteButton;
	
	@FindBy(xpath = "//input[@data-placeholder='Filter Table']")
	WebElement filterWithinTable;
	
	@FindBy(xpath = "//*[text()='close']")
	WebElement closeButton;
	
	@FindBy(xpath = "(//mat-select[@aria-label='Items per page:']/div/div)[1]")
	WebElement noOfItemsDropDown;
	
	@FindBy(xpath = "//div[@class='mat-paginator-range-actions']/div")
	WebElement paginatorRangeText;
	
	@FindBy(xpath = "(//div[@class='mat-paginator-range-actions']/button)[1]")
	WebElement navigateToFirstPaginationPage;
	
	@FindBy(xpath = "(//div[@class='mat-paginator-range-actions']/button)[2]")
	WebElement navigateToPreviousPaginationPage;

	@FindBy(xpath = "(//div[@class='mat-paginator-range-actions']/button)[3]")
	WebElement navigateToNextPaginationPage;

	@FindBy(xpath = "(//div[@class='mat-paginator-range-actions']/button)[4]")
	WebElement navigateToLastPaginationPage;
	
	@FindBy(xpath = "//mat-dialog-container/app-create-edit-selection/h2")
	WebElement newAdditionOverlayHeader;
	
	@FindBy(xpath = "(//mat-dialog-container/app-create-edit-selection/mat-dialog-content/form/div/mat-form-field)[1]/div/div/div/mat-select")
	WebElement magazineSelection;
	
	@FindBy(xpath = "(//mat-dialog-container/app-create-edit-selection/mat-dialog-content/form/div/mat-form-field)[2]/div/div/div/mat-select")
	WebElement selectionType;
	
	@FindBy(xpath = "(//mat-dialog-container/app-create-edit-selection/mat-dialog-content/form/div/mat-form-field)[3]/div/div/div/mat-select")
	WebElement deliveryMethod;
	
	@FindBy(xpath = "(//mat-dialog-container/app-create-edit-selection/mat-dialog-content/form/div/mat-form-field)[4]/div/div/div/input[1]")
	WebElement issueDate;
	
	@FindBy(xpath = "((//mat-dialog-container/app-create-edit-selection/mat-dialog-content/form/div/mat-form-field)[4]/div/div/div)[2]/mat-datepicker-toggle/button")
	WebElement issueDateCalender;
	
	@FindBy(xpath = "(//mat-dialog-container/app-create-edit-selection/mat-dialog-content/form/div/mat-form-field)[5]/div/div/div/input[1]")
	WebElement selectionDate;
	
	@FindBy(xpath = "((//mat-dialog-container/app-create-edit-selection/mat-dialog-content/form/div/mat-form-field)[5]/div/div/div)[2]/mat-datepicker-toggle/button")
	WebElement selectionDateCalender;
	
	@FindBy(xpath = "(//mat-dialog-container/app-create-edit-selection/mat-dialog-content/form/div/mat-form-field)[6]/div/div/div/input[1]")
	WebElement mailDate;
	
	@FindBy(xpath = "((//mat-dialog-container/app-create-edit-selection/mat-dialog-content/form/div/mat-form-field)[6]/div/div/div)[2]/mat-datepicker-toggle/button")
	WebElement mailDateCalender;
	
	@FindBy(xpath = "(//mat-dialog-container/app-create-edit-selection/mat-dialog-content/form/div/mat-form-field)[7]/div/div/div/input")
	WebElement daysBetween;
	
	@FindBy(xpath = "(//mat-dialog-container/app-create-edit-selection/mat-dialog-content/form/div/mat-form-field)[8]/div/div/div/input")
	WebElement selectionNumber;
	
	@FindBy(xpath = "//span[text()='Save']")
	WebElement saveButtonOnNewAddition;
	
	@FindBy(xpath = "//span[text()=' Close ']")
	WebElement closeButtonOnNewAddition;
	
	@FindBy(xpath = "//table/tbody/mat-row")
	List<WebElement> listOfData;
	
	@FindBy(xpath = "//span[text()=' Toggle Filter ']")
	WebElement toggleFilter;
	
	public SelectionDatesPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public String fetchingPageTitle() {
		return readText(pageTitle).substring(14, 29);
	}
	
	public void clickOnNewAdditionButton() {
		click(newAdditionButton);
	}
	
	public void clickOnOpenButton() {
		click(openButton);
	}
	
	public void clickOnDeleteButtonOnOpenOverlay() {
		click(deleteButtonOnOpenOverlay);
	}
	
	public void clickOnCancelButtonOnOpenOverlay() {
		click(closeButtonOnAllOverlays);
	}
	
	public void clickOnCopyButton() {
		click(copyButton);
	}
	
	public String clickOnCancelButtonOnCopyOverlay() {
		click(closeButtonOnAllOverlays);
		Alert alert = driver.switchTo().alert();
		alert.dismiss();
		click(closeButtonOnAllOverlays);
		alert = driver.switchTo().alert();
		String alertText = alert.getText();
		alert.accept();
		return alertText;
	}
	
	public void clickOnRecurranceButton() {
		click(recurranceButton);
	}
	
	public void clickOnDeleteButton() {
		click(deleteButton);
	}
	
	public void filterDatawithinTable(String filterText) {
		writeText(filterWithinTable, filterText);
	}
	
	public void clickOnCloseMarkInFilterDataWithinTable() {
		click(closeButton);
	}
	
	public String fetchNewAdditionOverlayText() {
		waitVisibility(newAdditionOverlayHeader);
		return readText(newAdditionOverlayHeader);
	}
	
	public void fillNewAdditionForm(SelectionDates selectionDates) {
		waitVisibility(newAdditionOverlayHeader);
		
		//Lines to select an option from mat-select element
		click(magazineSelection);
		selectMatOptionDropDownByText(selectionDates.getMagazine());
		click(selectionType);
		selectMatOptionDropDownByText(selectionDates.getSelectionType());
		click(deliveryMethod);
		selectMatOptionDropDownByText(selectionDates.getDeliveryMethod());
		selectMatOptionDropDownByText(selectionDates.getDeliveryMethod());
		
		click(issueDateCalender);
		selectYearOnCalender(Integer.parseInt(selectionDates.getIssueDate().substring(0, 4)));
		selectMonthOnCalender(Integer.parseInt(selectionDates.getIssueDate().substring(4)));
		//writeText(issueDate, selectionDates.getIssueDate());
		//selectionDate.clear();
		
		String[] date = selectionDates.getSelectionDate().split("/");
		//System.out.println("Length of date is : " + date.length + " And values are [" + date[0] + "," + date[1] + "," + date[2] + "]");
		//clearData(selectionDate);
		click(selectionDateCalender);
		clickOnCalenderIcon();
		selectYearOnCalender(Integer.parseInt(date[0]));
		selectMonthOnCalender(Integer.parseInt(date[1]));
		selectDayOnCalender(Integer.parseInt(date[2]));
		//writeText(selectionDate, selectionDates.getSelectionDate());
		//mailDate.clear();
		//clearData(mailDate);
		String[] mailDateArray = selectionDates.getMailDate().split("/");
		click(mailDateCalender);
		clickOnCalenderIcon();
		selectYearOnCalender(Integer.parseInt(mailDateArray[0]));
		selectMonthOnCalender(Integer.parseInt(mailDateArray[1]));
		selectDayOnCalender(Integer.parseInt(mailDateArray[2]));
		//writeText(mailDate, selectionDates.getMailDate());
		//writeText(daysBetween, String.valueOf(selectionDates.getDaysBetween()));
		writeText(selectionNumber, String.valueOf(selectionDates.getSelectionNumber()));
	}
	
	public void clickOnSaveButtonOnNewAdditionPage() {
		click(saveButtonOnNewAddition);
	}
	
	public void clickOnCancelButtonOnNewAdditionPage() {
		click(closeButtonOnNewAddition);
	}
	
	public ToggleFilter clickOnToggleFilter() {
		click(toggleFilter);
		return new ToggleFilter(driver);
	}
	
	public List<SelectionDates> listOfSelectionDatesForValidation() {
		WebElement pageRangeElement = driver.findElement(By.xpath("//div[@class='mat-paginator-range-actions']/div"));
		String pageRange = readText(pageRangeElement);
		int initialCount = 1;
		int internalCount = 1;
		int totalNumberOfData = Integer.parseInt((pageRange.split("of")[1]).trim());
		System.out.println("Text Being fetched is : " + pageRange);
		List<SelectionDates> listOfData = new ArrayList<>();
		while (totalNumberOfData > 0 && initialCount <= totalNumberOfData) {
			if(listOfData.size() > 0 && listOfData.size()%10 == 0) {
				click(navigateToNextPaginationPage);
				internalCount = 1;
			}
			System.out.println("Total Number Of Data : " + totalNumberOfData + " , Initial Count : " + initialCount + " , Internal Count : " + internalCount);
			WebElement attachedLocator = driver.findElement(By.xpath("((//table/tbody/mat-row)[" + internalCount + "]/mat-cell)[2]/mat-icon"));
			WebElement magazineLocator = driver.findElement(By.xpath("((//table/tbody/mat-row)[" + internalCount + "]/mat-cell)[4]/mat-label"));
			WebElement selectionTypeLocator = driver.findElement(By.xpath("((//table/tbody/mat-row)[" + internalCount + "]/mat-cell)[5]/mat-label"));
			WebElement issueDateLocator = driver.findElement(By.xpath("((//table/tbody/mat-row)[" + internalCount + "]/mat-cell)[6]/mat-label"));
			WebElement deliveryMethodLocator = driver.findElement(By.xpath("((//table/tbody/mat-row)[" + internalCount + "]/mat-cell)[7]/mat-label"));
			WebElement selectionDateLocator = driver.findElement(By.xpath("((//table/tbody/mat-row)[" + internalCount + "]/mat-cell)[8]/mat-label"));
			WebElement mailDateLocator = driver.findElement(By.xpath("((//table/tbody/mat-row)[" + internalCount + "]/mat-cell)[9]/mat-label"));
			WebElement daysBetweenLocator = driver.findElement(By.xpath("((//table/tbody/mat-row)[" + internalCount + "]/mat-cell)[10]/mat-label"));
			WebElement selectionNbrLocator = driver.findElement(By.xpath("((//table/tbody/mat-row)[" + internalCount + "]/mat-cell)[11]/mat-label"));
			WebElement createdLocator = driver.findElement(By.xpath("((//table/tbody/mat-row)[" + internalCount + "]/mat-cell)[12]/mat-label"));
			WebElement createdByLocator = driver.findElement(By.xpath("((//table/tbody/mat-row)[" + internalCount + "]/mat-cell)[13]/mat-label"));
			WebElement modifiedLocator = driver.findElement(By.xpath("((//table/tbody/mat-row)[" + internalCount + "]/mat-cell)[14]/mat-label"));
			WebElement modifiedByLocator = driver.findElement(By.xpath("((//table/tbody/mat-row)[" + internalCount + "]/mat-cell)[15]/mat-label"));
			
			SelectionDates selectionDate = new SelectionDates();
			selectionDate.setAttached(attachedLocator.getText().contains("circle"));
			selectionDate.setMagazine(magazineLocator.getText());
			selectionDate.setSelectionType(selectionTypeLocator.getText());
			selectionDate.setIssueDate(issueDateLocator.getText());
			selectionDate.setDeliveryMethod(deliveryMethodLocator.getText());
			selectionDate.setSelectionDate(selectionDateLocator.getText());
			selectionDate.setMailDate(mailDateLocator.getText());
			selectionDate.setDaysBetween(Integer.parseInt(daysBetweenLocator.getText()));
			selectionDate.setSelectionNumber(Integer.parseInt(selectionNbrLocator.getText()));
			selectionDate.setCreated(createdLocator.getText());
			selectionDate.setCreatedBy(createdByLocator.getText());
			selectionDate.setModified(modifiedLocator.getText());
			selectionDate.setModifiedBy(modifiedByLocator.getText());
			
			//Adding Selection Dates object onto list
			listOfData.add(selectionDate);
			internalCount ++;
			initialCount ++;
		}
		return listOfData;
	}

	public void selectFirstData() {
		// TODO Auto-generated method stub
		System.out.println("Inside select First Data");
		try {
			//Thread.sleep(3000);
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		WebElement element = driver.findElement(By.xpath("((//label[@class='mat-checkbox-layout'])[2]/span)[1]"));
		//click(element);
		element.click();
	}
	
	public int noOfRowsShowing() {
		return listOfData.size();
	}
	
	public RecurrancePage clickAndRedirectToRecurrancePage() {
		click(recurranceButton);
		mainWindowHandle = driver.getWindowHandle();
		Set<String> listOfWindowHandles = driver.getWindowHandles();
		for(String windowHandle : listOfWindowHandles)
			if(!mainWindowHandle.equals(windowHandle))
				driver.switchTo().window(windowHandle);
		RecurrancePage recurrancePage = new RecurrancePage(driver);
		return recurrancePage;
	}

}
