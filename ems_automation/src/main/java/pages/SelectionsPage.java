package pages;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pojo.Selection;

public class SelectionsPage extends HomePage {
	
	@FindBy(xpath = "//div[@class='page-title']")
	WebElement pageTitleText;
	
	@FindBy(xpath = "//span[text()=' Toggle Filter ']")
	WebElement toggleFilter;
	
	@FindBy(xpath = "//mat-table/mat-row")
	List<WebElement> noOfRowsShowing;
	
	@FindBy(xpath = "//input[@data-placeholder='Filter Table']")
	WebElement filterWithinTable;
	
	@FindBy(xpath = "(//div[@id='selectionGrid']/div/span)[1]")
	WebElement totalActualQuantity;
	
	@FindBy(xpath = "(//div[@id='selectionGrid']/div/span)[2]")
	WebElement totalEstimatedQuantity;
	
	@FindBy(xpath = "(//mat-paginator/div/div/div)[1]/mat-form-field/div/div[1]/div/mat-select")
	WebElement itemsPerPageDropdown;
	
	@FindBy(xpath = "(//mat-paginator/div/div/div)[2]/div")
	WebElement rangeText;
	
	@FindBy(xpath = "(//mat-paginator/div/div/div)[2]/button[1]")
	WebElement gotoFirstPage;
	
	@FindBy(xpath = "(//mat-paginator/div/div/div)[2]/button[2]")
	WebElement gotoPreviousPage;
	
	@FindBy(xpath = "(//mat-paginator/div/div/div)[2]/button[3]")
	WebElement gotoNextPage;
	
	@FindBy(xpath = "(//mat-paginator/div/div/div)[2]/button[4]")
	WebElement gotoLastPage;
	
	@FindBy(xpath = "//mat-toolbar/button[1]")
	WebElement newAddtionButton;
	
	@FindBy(xpath = "//mat-toolbar/button[2]")
	WebElement openButton;
	
	@FindBy(xpath = "//mat-toolbar/button[3]")
	WebElement copyButton;
	
	@FindBy(xpath = "//mat-toolbar/button[4]")
	WebElement deleteButton;
	
	@FindBy(xpath = "//mat-toolbar/a")
	WebElement buildButton;
	
	@FindBy(xpath = "//mat-toolbar/button[5]")
	WebElement massChangeButton;
	
	@FindBy(xpath = "//mat-toolbar/button[6]")
	WebElement updateEstimatedQuantities;
	
	@FindBy(xpath = "//table/tbody/tr/td")
	WebElement noFilteredData;
	
	public SelectionsPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public String fetchPageTitle() {
		return readText(pageTitleText).substring(10);
	}
	
	public ToggleFilter clickOnToggleFilter() {
		click(toggleFilter);
		return new ToggleFilter(driver);
	}
	
	public int fetchNumberOfDataShowingInPage() {
		return noOfRowsShowing.size();
	}
	
	public String fetchTotalActualQuantity() {
		return readText(totalActualQuantity);
	}
	
	public String fetchTotalEstimatedQuantity() {
		return readText(totalEstimatedQuantity);
	}
	
	public void selectItemsParPage(String data) {
		click(itemsPerPageDropdown);
		selectMatOptionDropDownByText(data);
	}
	
	public void filterWithinTable(String filterText) {
		writeText(filterWithinTable, filterText);
	}
	
	public String fetchNoFilteredData() {
		return readText(noFilteredData);
	}
	
	public List<Selection> fetchCompleteListOfSelectionData() {
		List<Selection> listOfSelectionData = new ArrayList<>();
		Map<String, ArrayList<String>> listOfData = fetchAllDatafilteredOut();
		
		for(int i = 1; i < listOfData.size(); i++) {
			String rowKey = "RowNumber" + i;
			listOfSelectionData.add(mapDataToObject(listOfData.get("Headers"), listOfData.get(rowKey)));
		}
		
		return listOfSelectionData;
	}
	
	public Selection mapDataToObject(ArrayList<String> headers, ArrayList<String> rowData) {
		Selection selection = new Selection();
		for(String header : headers) {
			String rowValue = rowData.get(headers.indexOf(header));
			System.out.println("Header Value : " + header + " and row Value : " + rowValue);
			switch (header.replaceAll(" ", "")) {
				case "Magazine":
					selection.setMagazine(rowValue);
					break;
				
				case "SelectionType":
					selection.setSelectionType(rowValue);
					break;
					
				case "Segment":
					selection.setSegment(rowValue);
					break;
					
				case "SelectionDate":
					selection.setSelectionDate(rowValue);
					break;
					
				case "Effort":
					selection.setEffort(rowValue);
					break;
					
				case "KeyDate":
					selection.setKeyDate(rowValue);
					break;
					
				case "Package":
					selection.setPackage(rowValue);
					break;
					
				case "ComboPackage":
					selection.setComboPackage(rowValue);
					break;
					
				case "Offer":
					selection.setOffer(rowValue);
					break;
					
				case "ActualQuantity":
					selection.setActualQuantity(Integer.parseInt(rowValue));
					break;
					
				case "EstimatedQuantity":
					selection.setEstimatedQuantity(Integer.parseInt(rowValue));
					break;
					
				case "EstimatedQuantityUpdatedDate":
					selection.setEstimatedQuantityUpdatedDate(rowValue);
					break;
					
				case "EstimatedOverride":
					selection.setEstimateOverride(rowValue.equals("true"));
					break;
					
				case "MailDate":
					selection.setMailDate(rowValue);
					break;
					
				case "DeliveryMethod":
					selection.setDeliveryMethod(rowValue);
					break;
					
				case "SelectionNBR":
					selection.setSelectionNumber(Integer.parseInt(rowValue));
					break;
					
				case "SkipSelection":
					selection.setSkipSelection(rowValue.equals("true"));
					break;
					
				case "Created":
					selection.setCreated(rowValue);
					break;
					
				case "CreatedBy":
					selection.setCreatedBy(rowValue);
					break;
					
				case "Modified":
					selection.setModified(rowValue);
					break;
					
				case "ModifiedBy":
					selection.setModifiedBy(rowValue);
					break;

				default:
					System.out.println("Header Value : " + header.replaceAll(" ", "") + " Not matching any of the attributes of Selection Page");
					break;
			}
		}
		return selection;
	}

}
