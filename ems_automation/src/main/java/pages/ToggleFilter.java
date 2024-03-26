package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import general.BasePage;
import utils.PageNames;

public class ToggleFilter extends HomePage {
	
	//WebDriver driver;
	public Logger logger = LogManager.getLogger("AppLogger");
	
	@FindBy(xpath = "//mat-select[@formcontrolname='MagazineID']")
	WebElement selectMagazine;
	
	@FindBy(xpath = "//mat-select[@formcontrolname='SelectionTypeID']")
	WebElement selectionTypeId;
	
	@FindBy(xpath = "//mat-select[@formcontrolname='DeliveryMethodID']")
	WebElement deliveryMethodID;
	
	@FindBy(xpath = "(//input[@formcontrolname='SelectionDateFrom']/../../div)[2]/mat-datepicker-toggle/button")
	WebElement selectFromDateCalenderIcon;
	
	@FindBy(xpath = "//input[@formcontrolname='SelectionDateFrom']")
	WebElement selectionDateFrom;
	
	@FindBy(xpath = "(//input[@formcontrolname='SelectionDateThru']/../../div)[2]/mat-datepicker-toggle/button")
	WebElement selectionToDateCalenderIcon;
	
	@FindBy(xpath = "//input[@formcontrolname='SelectionDateThru']")
	WebElement selectionDateThru;
	
	@FindBy(xpath = "//input[@formcontrolname='SelectionNbr']")
	WebElement selectionNumber;
	
	@FindBy(xpath = "//mat-select[@formcontrolname='SegmentID']")
	WebElement segmentId;
	
	@FindBy(xpath = "//mat-select[@formcontrolname='inactiveFilter']")
	WebElement segmentInactive;
	
	@FindBy(xpath = "(//input[@formcontrolname='MailDateFrom']/../../div)[2]/mat-datepicker-toggle/button")
	WebElement mailFromDateCalenderIcon;
	
	@FindBy(xpath = "//input[@formcontrolname='MailDateFrom']")
	WebElement mailFromDateFrom;
	
	@FindBy(xpath = "(//input[@formcontrolname='MailDateThru']/../../div)[2]/mat-datepicker-toggle/button")
	WebElement mailToDateCalenderIcon;
	
	@FindBy(xpath = "//input[@formcontrolname='MailDateThru']")
	WebElement mailToDate;
	
	@FindBy(xpath = "//input[@formcontrolname='SelectionNbrFrom']")
	WebElement selectionNumberFrom;
	
	@FindBy(xpath = "//input[@formcontrolname='SelectionNbrThru']")
	WebElement selectionNumberThru;
	
	@FindBy(xpath = "//mat-select[@formcontrolname='PackageId']")
	WebElement packageDropDown;
	
	@FindBy(xpath = "//mat-select[@formcontrolname='ComboPackageId']")
	WebElement comboPackageDropDown;
	
	@FindBy(xpath = "//mat-select[@formcontrolname='OfferId']")
	WebElement offerDropDown;
	
	@FindBy(xpath = "//input[@formcontrolname='KeyDateFrom']")
	WebElement keyDateFrom;
	
	@FindBy(xpath = "//input[@formcontrolname='KeyDateThru']")
	WebElement keyDateThru;
	
	@FindBy(xpath = "//mat-select[@formcontrolname='DeliveryMethodId']")
	WebElement deliveryMethodId;
	
	@FindBy(xpath = "//input[@formcontrolname='Effort']")
	WebElement effort;
	
	@FindBy(xpath = "(//input[@formcontrolname='CreatedFrom']/../../div)[2]/mat-datepicker-toggle/button")
	WebElement createdFromCalenderIcon;
	
	@FindBy(xpath = "//input[@formcontrolname='CreatedFrom']")
	WebElement createdFrom;
	
	@FindBy(xpath = "(//input[@formcontrolname='CreatedThru']/../../div)[2]/mat-datepicker-toggle/button")
	WebElement createdThruCalenderIcon;
	
	@FindBy(xpath = "//input[@formcontrolname='CreatedThru']")
	WebElement createdThru;

	@FindBy(xpath = "(//input[@formcontrolname='ModifiedFrom']/../../div)[2]/mat-datepicker-toggle/button")
	WebElement modifiedFromCalenderIcon;
	
	@FindBy(xpath = "//input[@formcontrolname='ModifiedFrom']")
	WebElement modifiedFrom;

	@FindBy(xpath = "(//input[@formcontrolname='ModifiedThru']/../../div)[2]/mat-datepicker-toggle/button")
	WebElement modifiedThruCalenderIcon;
	
	@FindBy(xpath = "//input[@formcontrolname='ModifiedThru']")
	WebElement modifiedThru;
	
	@FindBy(xpath = "//span[text()='Find Now']")
	WebElement findNowButton;
	
	@FindBy(xpath = "//span[text()='Reset Filter']")
	WebElement resetFilterButton;
	
	public ToggleFilter(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void selectMagazine(String magazineName) {
		click(selectMagazine);
		selectMatOptionDropDownByText(magazineName);
		//selectDropdownByName(selectMagazine, magazineName);
	}
	
	public void selectionTypeId(String selectionTypeId) {
		click(this.selectionTypeId);
		selectMatOptionDropDownByText(selectionTypeId);
		//selectDropdownByName(this.selectionTypeId, selectionTypeId);
	}
	
	public void deliveryMethodID(String deliveryId) {
		click(deliveryMethodID);
		selectMatOptionDropDownByText(deliveryId);
		//selectDropdownByName(deliveryMethodId, deliveryId);
	}
	
	public void selectionDateFrom(String selectionDateFrom) {
		String[] dates = selectionDateFrom.split("/");
		click(selectFromDateCalenderIcon);
		clickOnCalenderIcon();
		selectYearOnCalender(Integer.parseInt(dates[2]));
		selectMonthOnCalender(Integer.parseInt(dates[0]));
		selectDayOnCalender(Integer.parseInt(dates[1]));
		//writeText(this.selectionDateFrom, selectionDateFrom);
	}
	
	public void selectionDateThru(String selectionDateThru) {
		String[] dates = selectionDateThru.split("/");
		click(selectionToDateCalenderIcon);
		clickOnCalenderIcon();
		selectYearOnCalender(Integer.parseInt(dates[2]));
		selectMonthOnCalender(Integer.parseInt(dates[0]));
		selectDayOnCalender(Integer.parseInt(dates[1]));
		//writeText(this.selectionDateThru, selectionDateThru);
	}
	
	public void selectionNumber(String selectionNumber) {
		writeText(this.selectionNumber, selectionNumber);
	}
	
	public void selectSegmentId(String segmentId) {
		click(this.segmentId);
		selectMatOptionDropDownByText(segmentId);
	}
	
	public void selectSegmentInactive(String segmentInactive) {
		click(this.segmentInactive);
		selectMatOptionDropDownByText(segmentInactive);
	}
	
	public void mailFromDate(String mailFromDate) {
		String[] dates = mailFromDate.split("/");
		click(mailFromDateCalenderIcon);
		clickOnCalenderIcon();
		selectYearOnCalender(Integer.parseInt(dates[0]));
		selectMonthOnCalender(Integer.parseInt(dates[1]));
		selectDayOnCalender(Integer.parseInt(dates[2]));
		//writeText(this.mailFromDate, mailFromDate);
	}
	
	public void mailToDate(String mailToDate) {
		String[] dates = mailToDate.split("/");
		click(mailToDateCalenderIcon);
		clickOnCalenderIcon();
		selectYearOnCalender(Integer.parseInt(dates[0]));
		selectMonthOnCalender(Integer.parseInt(dates[1]));
		selectDayOnCalender(Integer.parseInt(dates[2]));
		//writeText(this.mailToDate, mailToDate);
	}
	
	public void selectionNumberFrom(String selectionNumberFrom) {
		writeText(this.selectionNumberFrom, selectionNumberFrom);
	}
	
	public void selectionNumberThru(String selectionNumberThru) {
		writeText(this.selectionNumberThru, selectionNumberThru);
	}
	
	public void selectPackageId(String packageId) {
		click(packageDropDown);
		selectMatOptionDropDownByText(packageId);
	}
	
	public void selectComboPackageId(String comboPackageId) {
		click(comboPackageDropDown);
		selectMatOptionDropDownByText(comboPackageId);
	}
	
	public void selectOfferId(String offerId) {
		click(offerDropDown);
		selectMatOptionDropDownByText(offerId);
	}
	
	public void keyDateFrom(String keyDateFrom) {
		writeText(this.keyDateFrom, keyDateFrom);
	}
	
	public void keyDateThru(String keyDateThru) {
		writeText(this.keyDateThru, keyDateThru);
	}
	
	public void deliveryMethodId(String deliveryId) {
		click(deliveryMethodId);
		selectMatOptionDropDownByText(deliveryId);
		//selectDropdownByName(deliveryMethodId, deliveryId);
	}
	
	public void effort(String effort) {
		writeText(this.effort, effort);
	}
	
	public void createdFromDate(String createdFrom) {
		String[] dates = createdFrom.split("/");
		click(createdFromCalenderIcon);
		clickOnCalenderIcon();
		selectYearOnCalender(Integer.parseInt(dates[0]));
		selectMonthOnCalender(Integer.parseInt(dates[1]));
		selectDayOnCalender(Integer.parseInt(dates[2]));
		//writeText(this.createdFrom, createdFrom);
	}
	
	public void createdThruDate(String createdThru) {
		String[] dates = createdThru.split("/");
		click(createdThruCalenderIcon);
		clickOnCalenderIcon();
		selectYearOnCalender(Integer.parseInt(dates[0]));
		selectMonthOnCalender(Integer.parseInt(dates[1]));
		selectDayOnCalender(Integer.parseInt(dates[2]));
		//writeText(this.createdThru, createdThru);
	}
	
	public void modifiedFromDate(String modifiedFrom) {
		String[] dates = modifiedFrom.split("/");
		click(modifiedFromCalenderIcon);
		clickOnCalenderIcon();
		selectYearOnCalender(Integer.parseInt(dates[0]));
		selectMonthOnCalender(Integer.parseInt(dates[1]));
		selectDayOnCalender(Integer.parseInt(dates[2]));
		//writeText(this.modifiedFrom, modifiedFrom);
	}
	
	public void modifiedThruDate(String modifiedThru) {
		String[] dates = modifiedThru.split("/");
		click(modifiedThruCalenderIcon);
		clickOnCalenderIcon();
		selectYearOnCalender(Integer.parseInt(dates[0]));
		selectMonthOnCalender(Integer.parseInt(dates[1]));
		selectDayOnCalender(Integer.parseInt(dates[2]));
		//writeText(this.modifiedThru, modifiedThru);
	}
	
	public Object clickFindNowButton(String filterAppliedOnPage) {
		click(findNowButton);
		return createDynamicPageObject(filterAppliedOnPage);
	}
	
	public Object clickResetFilterButton(String resetFilterAppliedOnPage) {
		click(resetFilterButton);
		return createDynamicPageObject(resetFilterAppliedOnPage);
	}
	
	public Object createDynamicPageObject(String dynamicPageName) {
		Object object = null;
		//Create and return the object based on filterAppliedOnPage value
		if(dynamicPageName.equals(PageNames.CATEGORIES.name()))
			object = new CategoriesPage(driver);
		else if(dynamicPageName.equals(PageNames.COMBO_ADJUSTMENT_PERCENTAGES.name()))
			object = new ComboAdjustmentPercentagesPage(driver);
		else if(dynamicPageName.equals(PageNames.EFFORTS.name()))
			object = new EffortsPage(driver);
		else if(dynamicPageName.equals(PageNames.EXPORT_PACKAGES.name()))
			object = new ExportPackagesPage(driver);
		else if(dynamicPageName.equals(PageNames.EXPORTS_OFFERS.name()))
			object = new ExportOffersPage(driver);
		else if(dynamicPageName.equals(PageNames.HOST_TARGET_MAGAZINE.name()))
			object = new HostTargetMagazinePage(driver);
		else if(dynamicPageName.equals(PageNames.OFFERS.name()))
			object = new OffersPage(driver);
		else if(dynamicPageName.equals(PageNames.PACKAGES.name()))
			object = new PackagesPage(driver);
		else if(dynamicPageName.equals(PageNames.SEGMENTS.name()))
			object = new SegmentsPage(driver);
		else if(dynamicPageName.equals(PageNames.SELECTION_DATES.name()))
			object = new SelectionDatesPage(driver);
		else if(dynamicPageName.equals(PageNames.SELECTIONS.name()))
			object = new SelectionsPage(driver);
		return object;
	}

}
