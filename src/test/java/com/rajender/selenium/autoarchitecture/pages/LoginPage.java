package com.rajender.selenium.autoarchitecture.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.rajender.selenium.autoarchitecture.utilities.CaptureScreenShot;
 
public class LoginPage {
 
	 final WebDriver driver;
	 private static final Logger log = LogManager.getLogger(LoginPage.class);
	 
	 //Constructor, as every page needs a Webdriver to find elements
	 public LoginPage(WebDriver driver){
	   this.driver=driver;  
	 }
	 
	 //Locating the username text box
	 @FindAll({
	 @FindBy(id="wrapper"),
	 @FindBy(id="userName")
	 })
	 private WebElement username;
	 
	 //Locating the password text box
	 @FindBy(id="password")
	 private WebElement pswd;
	 
	 //Locating Login Button
	 @FindBy(id="login")
	 private WebElement loginBtn;
	 
	 @FindBy(xpath="//p[@id='name']")
	 private WebElement errorMessage;
	 
	 
	 //Method that performs login action using the web elements
	 public void LogIn_Action(String uName, String pwd){
		 
		 try {
				 username.click();
				 username.clear(); 
				 username.sendKeys(uName);
				 pswd.click();
				 pswd.clear();
				 pswd.sendKeys(pwd);
				 loginBtn.click();			 	
			 	
		 } catch(Exception e) {
			 
				 // Printing logs   
				    log.error("Cannot Verify Login ---- exception while clicking element");
				    
				 // Capturing screenshot
				 // Note: You can also use "Base" class method "emailCaptureScreenShot" in case want to capture and email the screenshot.
				    try {
				    	     CaptureScreenShot.captureScreenshot(driver);
				        
				    } catch(Exception ex) {
				        	
				        	 log.error("Cannot Verify Login ---- exception while catching screen shot");
				        	 
				      }
				    
				 // Now i want to stop my test case
				    throw e;
		   }
	 }
	 
	 public void verifyLoginInvalidCred(){
		 Assert.assertEquals("Invalid username or password!", errorMessage.getText());
	 }
}