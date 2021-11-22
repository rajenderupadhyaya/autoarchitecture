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
		
		assignAuthorTestCategory("Shiva Dev", "Sanity");
		
		log.info("Test Book Existence : testBookExistence method");
		 //Instantiating BookStore page using initElements()
		BookStorePage bookStorePg = PageFactory.initElements(driver, BookStorePage.class);		 
		bookStorePg.verifyBookExistence(data.get("bookTitle"));
			
		bookStorePg=null;
	}
	
	/* Unblock to see concept of failed method retrying and skipping of depended method
	   Note that if "testSkipConceptWhenDependentMthdFails" retried three more times after first time test fails then it will appear four times in Extent Report and out of which 
       three times it will be shown as skipped and fourth time it will be shown as failure. After that the depended method "testSkipThisDependentMthd" 
	   will appear as seperate node and shown as skipped. 
	
	//Deliberately failing to show concept of "RetryListeners".  
	//This method will be tried three more times as
	// in "RetryListeners" class we have implemented this logic.
	// Also depended method "testSkipThisDependentMthd" will be skipped.
	@Test(priority=1, enabled=true)
	public void testSkipConceptWhenDependentMthdFails(){	 
		
		testLevelLog.get().assignAuthor("Rajender Upadhyaya");
		testLevelLog.get().assignCategory("Regression");
		log.info("test Skip Concept When Depenedent Mthd Fails : testSkipConceptWhenDepenedentMthdFails method");
		// Deliberately failing so that method "testSkipThisDependentMthd" skipped and shown in report
		Assert.fail();
	}	
	
	// Depended method
	@Test(priority=2, dependsOnMethods="testSkipConceptWhenDependentMthdFails", enabled=true)
	public void testSkipThisDependentMthd(Hashtable<String, String> data){	 
		
		assignAuthorTestCategory("Shiva Dev", "Sanity");
		log.info("test Skip This Depenedent Mthd : testSkipThisDependentMthd method");				
	}
  */
}
