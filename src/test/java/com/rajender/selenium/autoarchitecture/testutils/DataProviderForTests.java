package com.rajender.selenium.autoarchitecture.testutils;

import java.lang.reflect.Method;
import java.util.Hashtable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.DataProvider;

import com.rajender.selenium.autoarchitecture.base.Base;
import com.rajender.selenium.autoarchitecture.testcases.LoginTest;

public class DataProviderForTests extends Base{
	
	private static final Logger log = LogManager.getLogger(DataProviderForTests.class);
	
	@DataProvider(name="TestData")
	public  Object[][] getData(Method m) {
		
		log.info("Entering into method : getData");
		
		//System.out.println(generalConfigProperty);		
		String sheetName=generalConfigProperty.getTestDataSheetName();
		//System.out.println("SheetName-->"+generalConfigProperty.getTestDataSheetName());
		int rows = excel1.getRowCount(sheetName);//100
		//System.out.println("rows:::::::::::::::::::>>>  " + rows);
		String testName = m.getName();
		//System.out.println("Method Name:::::::::::::::::::>>>  " + testName);
		int testCaseRowNum = 1;

		for (testCaseRowNum = 1; testCaseRowNum <= rows; testCaseRowNum++) {

			String testCaseName = excel1.getCellData(sheetName, 0, testCaseRowNum);
			//System.out.println("TestCase name in excel2-->"+testCaseName);
			//System.out.println("TestCase name in Method-->"+testName);
			if (testCaseName.equalsIgnoreCase(testName))
				break;

		}// Checking total rows in test case
		
		//log.info("TestCase starts from:- "+testCaseRowNum);

		int dataStartRowNum = testCaseRowNum + 2;//dataStartRowNum=3
		
		//System.out.println("dataStartRowNum: "+ dataStartRowNum);

		int testRows = 0;
		while (!excel1.getCellData(sheetName, 0, dataStartRowNum + testRows).equals("endOfTestData")) {

			testRows++;//1
		//	System.out.println("testRows11: "+ testRows);
		}
		// Checking total cols in test case

		//System.out.println("Total no of rows:"+testRows);
		int colStartColNum = testCaseRowNum + 1;//2
		int testCols = 0;

		while (!excel1.getCellData(sheetName, testCols, colStartColNum).equals("")) {

			testCols++;

		}
		//[2][1]
		
		Object[][] data = new Object[testRows][1];
		//object[][] data= new Object[2][1];
		//data[0][0]=
		//data[1][0]=

		int i = 0;
		for (int rNum = dataStartRowNum; rNum < (dataStartRowNum + testRows); rNum++) {

			Hashtable<String, String> table = new Hashtable<String, String>();

		
			for (int cNum = 0; cNum < testCols; cNum++) {

				String colName = excel1.getCellData(sheetName, cNum, colStartColNum);
				String testData = excel1.getCellData(sheetName, cNum, rNum);
				
                //System.out.println("testData::::::::::::::::::::::::  " + testData);
				table.put(colName, testData);

			}

			data[i][0] = table;
			i++;

		}

		return data;
	}
}
