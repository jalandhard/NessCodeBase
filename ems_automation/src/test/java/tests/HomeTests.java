package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import general.BaseTest;

public class HomeTests extends BaseTest {
	
	//@Test
	public void clickableOnSelectionDatesDropdown() {
		magazinesMailSchedule = homePage.clickOnMagazinesMailSchedule();
		selectionDatesPage = magazinesMailSchedule.goToSelectionDatesPage();
		Assert.assertEquals(configFileReader.getSelectionDatesPageHeading(), selectionDatesPage.fetchingPageTitle());
	}
	
	//@Test
	public void clickOnSelectionOption() {
		magazinesMailSchedule = homePage.clickOnMagazinesMailSchedule();
		selectionsPage = magazinesMailSchedule.goToSelectionsPage();
		Assert.assertEquals(configFileReader.getSelectionsPageHeading(), selectionsPage.fetchPageTitle());
	}

}
