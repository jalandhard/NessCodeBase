package utils;

import java.util.Date;

import pojo.Categories;
import pojo.ComboAdjustmentPercentages;
import pojo.Efforts;
import pojo.HostTargetMagazine;
import pojo.Offers;
import pojo.Packages;
import pojo.Segments;
import pojo.Selection;
import pojo.SelectionDates;

public class Utils {
	
	public Object createDynamicPageObject(String dynamicPageName) {
		Object object = null;
		//Create and return the object based on filterAppliedOnPage value
		if(dynamicPageName.equals(PageNames.CATEGORIES.name()))
			object = new Categories();
		else if(dynamicPageName.equals(PageNames.COMBO_ADJUSTMENT_PERCENTAGES.name()))
			object = new ComboAdjustmentPercentages();
		else if(dynamicPageName.equals(PageNames.EFFORTS.name()))
			object = new Efforts();
		else if(dynamicPageName.equals(PageNames.HOST_TARGET_MAGAZINE.name()))
			object = new HostTargetMagazine();
		else if(dynamicPageName.equals(PageNames.OFFERS.name()))
			object = new Offers();
		else if(dynamicPageName.equals(PageNames.PACKAGES.name()))
			object = new Packages();
		else if(dynamicPageName.equals(PageNames.SEGMENTS.name()))
			object = new Segments();
		else if(dynamicPageName.equals(PageNames.SELECTION_DATES.name()))
			object = new SelectionDates();
		else if(dynamicPageName.equals(PageNames.SELECTIONS.name()))
			object = new Selection();
		return object;
	}
	
	public boolean validationOfDateBetweenDateRanges(String fromDate, String toDate, String date) {
		boolean status = true;
		String[] fromDateArray = fromDate.split("/");
		String[] toDateArray = toDate.split("/");
		String[] dateArray = date.split("/");
		if(!compareIntegers(Integer.parseInt(fromDateArray[2]), Integer.parseInt(dateArray[2]), Integer.parseInt(toDateArray[2])))
			status = false;
		else if(!compareIntegers(Integer.parseInt(fromDateArray[0]), Integer.parseInt(dateArray[0]), Integer.parseInt(toDateArray[0])))
			status = false;
		else if(!compareIntegers(Integer.parseInt(fromDateArray[1]), Integer.parseInt(dateArray[1]), Integer.parseInt(toDateArray[1])))
			status = false;
		return status;
	}
	
	public boolean compareIntegers(int a, int b, int c) {
		if(a <= b)
			if(b <= c)
				return true;
		return false;
	}

}
