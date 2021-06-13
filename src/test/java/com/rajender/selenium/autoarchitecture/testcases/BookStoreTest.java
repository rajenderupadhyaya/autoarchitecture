package com.rajender.selenium.autoarchitecture.testcases;

import java.util.Hashtable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.rajender.selenium.autoarchitecture.testutils.DataProviderForTests;
import com.rajender.selenium.autoarchitecture.base.Base;
import com.rajender.selenium.autoarchitecture.pages.BookStorePage;


public class BookStoreTest extends Base {
	
	private static final Logger log = LogManager.getLogger(BookStoreTest.class);
	
	@Test(dataProviderClass=DataProviderForTests.class, dataProvider="TestData", priority=0, enabled=true)
	public void testBookExistence(Hashtable<String, String> data){	 
		
		testLevelLog.get().assignAuthor("Rajender Upadhyaya");
		testLevelLog.get().assignCategory("Regression");
		log.info("Test Book Existence : testBookExistence method");
		 //Instantiating BookStore page using initElements()
		BookStorePage bookStorePg = PageFactory.initElements(driver, BookStorePage.class);		 
		bookStorePg.verifyBookExistence(data.get("bookTitle"));
			
		bookStorePg=null;
	}	
}
