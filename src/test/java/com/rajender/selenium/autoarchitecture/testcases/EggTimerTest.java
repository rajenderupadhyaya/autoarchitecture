package com.rajender.selenium.autoarchitecture.testcases;

import java.util.Hashtable;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.support.PageFactory;

import org.testng.annotations.Test;

import com.rajender.selenium.autoarchitecture.testutils.DataProviderForTests;
import com.rajender.selenium.autoarchitecture.base.Base;
import com.rajender.selenium.autoarchitecture.pages.EggTimerPage;
import com.rajender.selenium.autoarchitecture.pages.EggTimerVerifyingPage;



public class EggTimerTest extends Base {
	
	private static final Logger log = LogManager.getLogger(EggTimerTest.class);
	
		
	@Test(dataProviderClass=DataProviderForTests.class, dataProvider="TestData", priority=0, enabled=true)
	public void testTimerCounterValue(Hashtable<String, String> data) throws InterruptedException{		
		
		   assignAuthorTestCategory("Rajender Upadhyaya", "Regression");
		
		   log.info("Egg Timer Test : testEnterTime method");
		   
		   // Ensuring Page (https://e.ggtimer.com/) is fully loaded
		   waitForPageLoaded(driver);
		   
		   String timerCount = data.get("timerCount");
		 
		   EggTimerPage EggTimerPg = PageFactory.initElements(driver, EggTimerPage.class);
		   EggTimerPg.enterTime(timerCount);		 		
		
		   // Ensuring Page (https://e.ggtimer.com/25) is fully loaded
		   waitForPageLoaded(driver);
			
		   EggTimerVerifyingPage EggTimerVerifyingPg = PageFactory.initElements(driver, EggTimerVerifyingPage.class);
		   EggTimerVerifyingPg.verifyTimeValue(webDriverWait, timerCount);			
	}	 
}	
	

