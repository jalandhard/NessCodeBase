package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelFileUtils {

	public static void writeOntoExcel(int empId, String empName, String empDesignation, String empType)
			throws Exception {

		File file = new File("EmployeeDetails.xlsx");

		FileInputStream inputStream = new FileInputStream(file);

		Workbook workbook = WorkbookFactory.create(inputStream);

		Sheet sheet = workbook.getSheet("Employees");

		Row row = sheet.createRow(sheet.getLastRowNum() + 1);

		row.createCell(0).setCellValue(empId);
		row.createCell(1).setCellValue(empName);
		row.createCell(2).setCellValue(empDesignation);
		row.createCell(3).setCellValue(empType);

		inputStream.close();

		FileOutputStream outputStream = new FileOutputStream(file);

		workbook.write(outputStream);

		workbook.close();

		outputStream.close();
	}

	public static void readFromExcel(int empId) throws Exception {
		File file = new File("EmployeeDetails.xlsx");

		FileInputStream inputStream = new FileInputStream(file);

		Workbook workbook = WorkbookFactory.create(inputStream);

		Sheet sheet = workbook.getSheet("Employees");
		
		int rowSize = sheet.getLastRowNum();
		
		int rowIndex = 1;
		
		Row row = sheet.getRow(0);
		
		for(;rowIndex < rowSize; rowIndex++) {
			row = sheet.getRow(rowIndex);
			
			if(row.getCell(0).getNumericCellValue() == empId)
				break;
		}
		
		System.out.println("Employee ID : " + (int) row.getCell(0).getNumericCellValue());
		System.out.println("Employee Name : " + row.getCell(1).getStringCellValue());
		System.out.println("Employee Name : " + row.getCell(2).getStringCellValue());
		System.out.println("Employee Name : " + row.getCell(3).getStringCellValue());
		
		workbook.close();
		
		inputStream.close();
	}

	public static void main(String[] args) {
		try {
//			writeOntoExcel(1295, "Akash", "Junior", "Contract");
			readFromExcel(1238);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
