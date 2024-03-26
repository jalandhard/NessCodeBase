package tests;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import general.BaseTest;
import pages.SelectionDatesPage;
import pojo.SelectionDates;
import utils.PageNames;

public class SelectionDatesPageTests extends BaseTest {
	
	@BeforeClass
	public void clickableOnSelectionDatesDropdown() {
		magazinesMailSchedule = homePage.clickOnMagazinesMailSchedule();
		selectionDatesPage = magazinesMailSchedule.goToSelectionDatesPage();
		Assert.assertEquals(configFileReader.getSelectionDatesPageHeading(), selectionDatesPage.fetchingPageTitle());
	}
	
	@Test(priority = 1)
	public void checkUserOnNewAdditionPopupOnSelectionDates() {
		selectionDatesPage.clickOnNewAdditionButton();
		assertEquals(configFileReader.getSelectionDatesNewAdditionHeading(), selectionDatesPage.fetchNewAdditionOverlayText());
		//selectionDatesPage.logoutUser();
		SelectionDates selectionDates = new SelectionDates();
		selectionDates.setMagazine("TOH-Taste of Home");
		selectionDates.setSelectionType("1 - Single Renewals");
		selectionDates.setDeliveryMethod("SUP-Supplement");
		selectionDates.setIssueDate("198801");
		selectionDates.setSelectionDate("2014/11/28");
		selectionDates.setMailDate("2047/7/10");
		selectionDates.setDaysBetween(2);
		selectionDates.setSelectionNumber(2123);
		selectionDatesPage.fillNewAdditionForm(selectionDates);
		selectionDatesPage.clickOnCancelButtonOnNewAdditionPage();
	}
	
	@Test(priority = 2)
	public void filterValidation() {
		SelectionDates selectionDates = new SelectionDates();
		selectionDates.setMagazine("COO-Country Digital");
		//selectionDates.setMagazine("BNB-Birds & Blooms");
		selectionDates.setSelectionType("1 - Single Renewals");
		selectionDates.setSelectionDate("3/6/2017");
		//selectionDates.setSelectionDate("2022/5/8");
		toggleFilter = selectionDatesPage.clickOnToggleFilter();
		toggleFilter.selectMagazine(selectionDates.getMagazine());
		toggleFilter.selectionTypeId(selectionDates.getSelectionType());
		toggleFilter.selectionDateFrom(selectionDates.getSelectionDate());
		Object object = toggleFilter.clickFindNowButton(PageNames.SELECTION_DATES.name());
		try {
			//Explicitly making thread to sleep for 3 seconds for loading the data post filter
			Thread.sleep(3000);
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		if(object instanceof SelectionDatesPage)
			selectionDatesPage = (SelectionDatesPage)object;
		else
			throw new RuntimeException("Class Casting Exception");
		List<SelectionDates> selectionDatesList = new ArrayList<SelectionDates>();
		selectionDatesList = selectionDatesPage.listOfSelectionDatesForValidation();
		for(SelectionDates selectionDate : selectionDatesList)
			System.out.println(selectionDate.toString());
	}
	
	@Test(priority = 3)
	public void validateRecurranceModule() {
		selectionDatesPage.clickOnToggleFilter();
		selectionDatesPage.selectFirstData();
		recurrancePage = selectionDatesPage.clickAndRedirectToRecurrancePage();
		recurrancePage.selectSelectionType("1 - Single Renewals");
		recurrancePage.enterPatternEvery("3");
		recurrancePage.selectAllDaysCheckbox();
		List<String> list = new ArrayList<>();
		list.add("Monday");
		list.add("Thursday");
		recurrancePage.SelectCustomizedDaysCheckbox(list);
		recurrancePage.enterPatternEndsAfter("26");
		selectionDatesPage = recurrancePage.closeWindow();
		//selectionDatesPage.selectFirstData();
		selectionDatesPage.clickOnOpenButton();
		selectionDatesPage.clickOnCancelButtonOnOpenOverlay();
		selectionDatesPage.clickOnCopyButton();
		System.out.println(selectionDatesPage.clickOnCancelButtonOnCopyOverlay());
		selectionDatesPage.selectFirstData();
		selectionDatesPage.logoutUser();
	}
	
}