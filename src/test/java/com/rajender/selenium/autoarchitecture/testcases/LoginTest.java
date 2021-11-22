package com.rajender.selenium.autoarchitecture.testcases;

import java.util.Hashtable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.rajender.selenium.autoarchitecture.testutils.DataProviderForTests;
import com.rajender.selenium.autoarchitecture.base.Base;
import com.rajender.selenium.autoarchitecture.pages.LoginPage;
import com.rajender.selenium.autoarchitecture.pages.ProfilePage;


public class LoginTest extends Base {
	
	private static final Logger log = LogManager.getLogger(LoginTest.class);
	
	@Test(dataProviderClass=DataProviderForTests.class, dataProvider="TestData", priority=0, enabled=true)
	public void testLoginInvalidUser(Hashtable<String, String> data){		
		
		assignAuthorTestCategory("Rajender Upadhyaya", "Regression");
		
		 log.info("Test Login Invalid User : testLoginInvalidUser method");
		 //Instantiating Login page using initElements()
		 LoginPage loginPg = PageFactory.initElements(driver, LoginPage.class);		 
		 loginPg.LogIn_Action(data.get("email"), data.get("password"));
			/*
			 try {
				  emailCaptureScreenShot(driver);
			 } catch (IOException | MessagingException e) {
				  e.printStackTrace();
			 }
			 */
		 loginPg.verifyLoginInvalidCred();
		 loginPg=null;		 
	}
	
	@Test(dataProviderClass=DataProviderForTests.class, dataProvider="TestData", priority=1, enabled=true)
	public void testLoginInvalidPwd(Hashtable<String, String> data){
		
		 assignAuthorTestCategory("Rajender Upadhyaya", "Regression");
		
		 log.info("Test Login Invalid Password : testLoginInvalidPwd method");
		//Instantiating Login page using initElements()
		 LoginPage loginPg = PageFactory.initElements(driver, LoginPage.class);
		 loginPg.LogIn_Action(data.get("email"), data.get("password"));
		 loginPg.verifyLoginInvalidCred();
		 loginPg=null;
	}
	
	@Test(dataProviderClass=DataProviderForTests.class, dataProvider="TestData", priority=2, enabled=true)
	public void testLogin(Hashtable<String, String> data){
		
		assignAuthorTestCategory("Rajender Upadhyaya", "Regression");
		
		log.info("Test Login : testLogin method");
		//Instantiating Login page using initElements()
		LoginPage loginPg = PageFactory.initElements(driver, LoginPage.class); 
		loginPg.LogIn_Action(data.get("email"), data.get("password"));
		
		//Instantiating Profile page using initElements()
		ProfilePage profilePg = PageFactory.initElements(driver, ProfilePage.class);		
		profilePg.verifyUser(data.get("email"));
	   // profilePg.logout_Action();		
		
		loginPg=null;
		profilePg=null;
	}
}
