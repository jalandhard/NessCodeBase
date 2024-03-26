package tests;

import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import general.BaseTest;
import pages.SelectionsPage;
import pojo.Selection;
import utils.PageNames;
import utils.Utils;

public class SelectionPageTests extends BaseTest{
	
	@BeforeClass
	public void clickOnSelectionOption() {
		magazinesMailSchedule = homePage.clickOnMagazinesMailSchedule();
		selectionsPage = magazinesMailSchedule.goToSelectionsPage();
		Assert.assertEquals(configFileReader.getSelectionsPageHeading(), selectionsPage.fetchPageTitle());
	}
	
	@Test(priority = 1)
	public void validateDataPageWithoutApplyingFilter() {
		String noFilteredData = selectionsPage.fetchNoFilteredData();
		assertTrue(noFilteredData.contains("Find"), "Reset Filter Functionality Failed!!!!!");
	}
	
	@Test(priority = 2)
	public void validateFilterData() {
		Selection selection = new Selection();
		//selection.setMagazine("BNB-Birds & Blooms");
		selection.setMagazine("COO-Country Digital");
		selection.setSelectionType("1 - Single Renewals");
		selection.setSelectionFromDate("3/24/2014");
		selection.setSelectionToDate("3/24/2014");
		toggleFilter = selectionsPage.clickOnToggleFilter();
		toggleFilter.selectMagazine(selection.getMagazine());
		toggleFilter.selectionTypeId(selection.getSelectionType());
		toggleFilter.selectionDateFrom(selection.getSelectionFromDate());
		toggleFilter.selectionDateThru(selection.getSelectionToDate());
		
		Object object = toggleFilter.clickFindNowButton(PageNames.SELECTIONS.name());
		try {
			//Explicitly making thread to sleep for 3 seconds for loading the data post filter
			Thread.sleep(3000);
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		if(object instanceof SelectionsPage)
			selectionsPage = (SelectionsPage)object;
		else
			throw new RuntimeException("Class Casting Exception");
		List<Selection> selectionList = new ArrayList<Selection>();
		selectionList = selectionsPage.fetchCompleteListOfSelectionData();
		System.out.println("Size of Data fetched : " + selectionList.size());
		boolean filterStatus = true;
		Utils utils = new Utils();
		for(Selection selections : selectionList) {
			System.out.println(selections.toString());
			boolean dateRangeStatus = utils.validationOfDateBetweenDateRanges(selection.getSelectionFromDate(), selection.getSelectionToDate(), selections.getSelectionDate().split(" ")[0]);
			if(!selection.getMagazine().contains(selections.getMagazine()) 
					|| !selections.getSelectionType().equals(selection.getSelectionType()) 
					|| !dateRangeStatus) {
				filterStatus = false;
				break;
			}
		}
		assertTrue(filterStatus, "Filtered Data Validated Failed!!!!!");
	}
	
	@Test(priority = 3)
	public void validateResetFilterFunctionality() {
		Object object = toggleFilter.clickResetFilterButton(PageNames.SELECTIONS.name());
		if(object instanceof SelectionsPage)
			selectionsPage = (SelectionsPage)object;
		else
			throw new RuntimeException("Class Casting Exception");
		String noFilteredData = selectionsPage.fetchNoFilteredData();
		assertTrue(noFilteredData.contains("Find"), "Reset Filter Functionality Failed!!!!!");
	}
	
	@Test(priority = 4)
	public void validateEmptyResultFunctionality() {
		Selection selection = new Selection();
		//selection.setMagazine("BNB-Birds & Blooms");
		selection.setMagazine("COO-Country Digital");
		selection.setSelectionType("1 - Single Renewals");
		selection.setSelectionFromDate("3/25/2014");
		selection.setSelectionToDate("3/25/2014");
		//toggleFilter = selectionsPage.clickOnToggleFilter();
		toggleFilter.selectMagazine(selection.getMagazine());
		toggleFilter.selectionTypeId(selection.getSelectionType());
		toggleFilter.selectionDateFrom(selection.getSelectionFromDate());
		toggleFilter.selectionDateThru(selection.getSelectionToDate());
		
		Object object = toggleFilter.clickFindNowButton(PageNames.SELECTIONS.name());
		try {
			//Explicitly making thread to sleep for 3 seconds for loading the data post filter
			Thread.sleep(3000);
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		if(object instanceof SelectionsPage)
			selectionsPage = (SelectionsPage)object;
		else
			throw new RuntimeException("Class Casting Exception");
		String noDataMatching = selectionsPage.fetchNoFilteredData();
		assertTrue(noDataMatching.contains("There Is No Data Matching"), "No Result Functionality Failed!!!!!");
	}

}
