package com.wp.genericLib;

public class TestExcel {
	
	static int i = 2;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String FHM_Home = Constant.FHM_HomePage;
		String BrandTitle = ExcelLibrary.getExcelData("GA_CPT", 0, 0);
		System.out.println(BrandTitle);
		System.out.println(FHM_Home);
		
		TestExcel test = new TestExcel();
		test.method();

	}
	
	private void method() {
		// TODO Auto-generated method stub
		i = i + 3;
		System.out.println(i);

	}

}
