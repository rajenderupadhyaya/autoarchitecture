package com.rajender.selenium.autoarchitecture.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


import com.rajender.selenium.autoarchitecture.utilities.CaptureScreenShot;
 
public class EggTimerPage {
 
	 final WebDriver driver;
	 private static final Logger log = LogManager.getLogger(EggTimerPage.class);
	 
	 //Constructor, as every page needs a Webdriver to find elements
	 public EggTimerPage(WebDriver driver){
	   this.driver=driver;  
	 }	 
	 	 
	 @FindBy(xpath="//input[@id='EggTimer-start-time-input-text']")
	 private WebElement enterTime;
	 
	 @FindBy(xpath="//button[@data-for='startpage']")
	 private WebElement start;	 
	 
	 //Method that used to enter time using the web elements
	 public void enterTime(String time){
		 
		 try {
			     enterTime.click();
				 enterTime.clear();			    
				 enterTime.sendKeys(time.substring(0,time.indexOf("."))); 
				 start.click();			 	
			 	
		 } catch(Exception e) {
			 
				 // Printing logs   
				    log.error("Cannot Enter Time ---- exception while clicking element");
				    
				 // Capturing screenshot
				 // Note: You can also use "Base" class method "emailCaptureScreenShot" in case want to capture and email the screenshot.
				    try {
				    	     CaptureScreenShot.captureScreenshot(driver);
				        
				    } catch(Exception ex) {
				        	
				        	 log.error("Cannot Enter Time ---- exception while catching screen shot");
				        	 
				      }
				    
				 // Now i want to stop my test case
				    throw e;
		   }
	 }	
}