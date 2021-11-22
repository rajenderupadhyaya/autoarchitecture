package com.rajender.selenium.autoarchitecture.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import org.testng.Assert;

import com.rajender.selenium.autoarchitecture.utilities.CaptureScreenShot;
 
public class BookStorePage {
 
	final WebDriver driver;
	private static final Logger log = LogManager.getLogger(BookStorePage.class);
	 
	 //Constructor, as every page needs a Webdriver to find elements
	 public BookStorePage(WebDriver driver){
	   this.driver=driver;
	 }	 
	
	 @FindBy(xpath="//input[@id='searchBox']")
	 private WebElement searchBox;  	  
	 
	 @FindBy(xpath="//a[@href='/books?book=9781449325862']")
	 private WebElement hyperLink;
	 
	 public void verifyBookExistence(String bookTitle) {	
		 searchBox.clear();
		 searchBox.sendKeys(bookTitle); 
		 String strHyperLink = "";
		       
		    try {
				 	strHyperLink = hyperLink.getText();
				 	
			 } catch(Exception e) {
				 
					 // Printing logs   
					    log.error("Cannot Verify Book Existence ---- exception while getting text from hyperlink element");
					    
					 // Capturing screenshot
					 // Note: You can also use "Base" class method "emailCaptureScreenShot" in case want to capture and email the screenshot.
					    try {
					    	     CaptureScreenShot.captureScreenshot(driver);
					        
					    } catch(Exception ex) {
					        	
					        	 log.error("Cannot Verify Book Existence ---- exception while catching screen shot");
					        	 
					      }
					    
					 // Now i want to stop my test case
					    throw e;
			   }
		     
		    Assert.assertEquals(bookTitle, strHyperLink);		        
		 
	 }	
	 
}