package com.wp.genericLib;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelLibrary {
	public static FileInputStream fis=null;
	public static FileOutputStream fos=null;
	public static Workbook wb=null;
	public static Sheet s=null;
	public static Row r=null;
	public static Cell c=null;

	public static String getExcelData(String sheetName,int rowNum,int colNum){
		String retVal=null;
		ConfigFileReader configFileReader = new ConfigFileReader();
		String environment=configFileReader.getEnvironment();
		try {
			if(environment.equalsIgnoreCase("production")) {
				fis = new FileInputStream("datafiles/GA_data_production.xlsx");
			}
			else if (environment.equalsIgnoreCase("staging")) {
				fis = new FileInputStream("datafiles/GA_data_staging.xlsx");
			}
			
			//fis = new FileInputStream("datafiles/GA_data_production.xlsx");
			wb = WorkbookFactory.create(fis);
			s = wb.getSheet(sheetName);
			r = s.getRow(rowNum);
			c = r.getCell(colNum);
			retVal = c.getStringCellValue();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return retVal;
	}



	public int getRowCount(String sheetName){
		int rowCount=0;
		try {
			fis = new FileInputStream("datafiles/data.xlsx");
			wb = WorkbookFactory.create(fis);
			s = wb.getSheet(sheetName);
			rowCount = s.getLastRowNum();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return rowCount;
	}



	public void writeToExcel(String sheetName, int rowNum, int colNum, String data){
		try {
			fis = new FileInputStream("datafiles/data.xlsx");
			wb = WorkbookFactory.create(fis);
			s = wb.getSheet(sheetName);
			r = s.getRow(rowNum);
			c = r.createCell(colNum);
			c.setCellValue(data);
			fos = new FileOutputStream("datafiles/data.xlsx");
			wb.write(fos);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static List<String> getColumnValues(String filePath, String sheetName, int col) {

		try {
			fis = new FileInputStream(filePath);
			wb = WorkbookFactory.create(fis);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Sheet sheet = wb.getSheet(sheetName);

		List<String> values = new ArrayList<String>((sheet.getLastRowNum()));

		//row index starts at 1 since row 0 is the column names
		for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
			Row row = sheet.getRow(rowIndex);
			if (row != null) {
				Cell cell = row.getCell(col);
				if (cell != null) {
					String value = cell.getStringCellValue();
					if(!value.isEmpty())
						values.add(value);
				}
			}
		}

		return values;
	}

	public static List<String> ReadExcel(String sheetName) throws InterruptedException, IOException{
		String filePath= "datafiles/GA_data_production.xlsx";	
		//Create an object of FileInputStream class to read excel file
		FileInputStream inputStream = new FileInputStream(filePath);
		Workbook AddCatalog = null;
		//create object of XSSFWorkbook class
		AddCatalog = new XSSFWorkbook(inputStream);
		//Read sheet inside the workbook by its name
		Sheet AddCatalogSheet = AddCatalog.getSheet(sheetName);
		//Find number of rows in excel file
		int rowcount = AddCatalogSheet.getLastRowNum()- AddCatalogSheet.getFirstRowNum();
		List<String> arrName = new ArrayList<String>();
		for(int i=0; i<rowcount+1; i++){
			//Create a loop to get the cell values of a row for one iteration
			Row row = AddCatalogSheet.getRow(i);

			for(int j=0; j<row.getLastCellNum(); j++){
				// Create an object reference of 'Cell' class
				Cell cell = row.getCell(j);
				// Add all the cell values of a particular row
				arrName.add(cell.getStringCellValue());
			}
		}
		return arrName;
	}
	
public static List<String> getColumnValues(String sheetName, int col) {
		
		ConfigFileReader configFileReader = new ConfigFileReader();
		String environment = configFileReader.getEnvironment();
		
		String filePath = "";
		
		if(environment.equalsIgnoreCase("production")) {
			filePath = "datafiles/GA_data_production.xlsx";
		}
		else if (environment.equalsIgnoreCase("staging")) {
			filePath = "datafiles/GA_data_staging.xlsx";
		}
		else {
			filePath = "datafiles/data_rf_staging.xlsx";
		}

		try {
			fis = new FileInputStream(filePath);
			wb = WorkbookFactory.create(fis);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Sheet sheet = wb.getSheet(sheetName);

		List<String> values = new ArrayList<String>((sheet.getLastRowNum()));

		//row index starts at 1 since row 0 is the column names
		for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
			Row row = sheet.getRow(rowIndex);
			if (row != null) {
				Cell cell = row.getCell(col);
				if (cell != null) {
					String value = cell.getStringCellValue();
					if(!value.isEmpty())
						values.add(value);
				}
			}
		}

		return values;
	}
	
	public static List<String> getTemplateUrls(String sheetName, int col, String template) {
		ConfigFileReader configFileReader = new ConfigFileReader();
		String environment = configFileReader.getEnvironment();
		
		String filePath = "";
		
		if(environment.equalsIgnoreCase("production")) {
			filePath = "datafiles/GA_data_production.xlsx";
		}
		else if (environment.equalsIgnoreCase("staging")) {
			filePath = "datafiles/GA_data_staging.xlsx";
		}
		else {
			filePath = "datafiles/data_rf_staging.xlsx";
		}

		try {
			fis = new FileInputStream(filePath);
			wb = WorkbookFactory.create(fis);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Sheet sheet = wb.getSheet(sheetName);

		List<String> values = new ArrayList<String>((sheet.getLastRowNum()));

		//row index starts at 1 since row 0 is the column names
		for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
			Row row = sheet.getRow(rowIndex);

			if (row != null) { //make sure row exists
				Cell urlCell = row.getCell(col);
				if (urlCell != null) { //make sure cell exists
					
					Cell templateCell = row.getCell(col-1);
					if(templateCell != null) { //make sure template cell exists
						String urlTemplate = templateCell.getStringCellValue();
						
						if(urlTemplate.equalsIgnoreCase(template)) { //see if template is the desired one
							String value = urlCell.getStringCellValue();
							System.out.println(value);
							if(!value.isEmpty())
								values.add(value);
							
						}
					}
				}
			}
		}

		return values;
	}
}
