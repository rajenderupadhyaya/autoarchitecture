package com.rajender.selenium.autoarchitecture.testcases;

import java.util.Hashtable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.rajender.selenium.autoarchitecture.testutils.DataProviderForTests;
import com.rajender.selenium.autoarchitecture.base.Base;
import com.rajender.selenium.autoarchitecture.pages.ProfilePage;


public class ProfileTest extends Base {	
	
	private static final Logger log = LogManager.getLogger(ProfileTest.class);
	
	@Test(dataProviderClass=DataProviderForTests.class, dataProvider="TestData", priority=0, enabled=true)
	public void testProfilePageElement(Hashtable<String, String> data){	 
		
		 testLevelLog.get().assignAuthor("Rajender Upadhyaya");
		 testLevelLog.get().assignCategory("Regression");
		 log.info("Test Profile Page Element : testProfilePageElement method");
		 //Instantiating Profile page using initElements()
		 ProfilePage profilePg = PageFactory.initElements(driver, ProfilePage.class);		 
		 profilePg.verifyProfilePageElement(driver, data.get("elementName"));
			
		 profilePg=null;
	}	
}
