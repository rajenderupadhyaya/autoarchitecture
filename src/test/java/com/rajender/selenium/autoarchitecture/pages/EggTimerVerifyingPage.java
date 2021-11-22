package com.rajender.selenium.autoarchitecture.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import com.rajender.selenium.autoarchitecture.utilities.CaptureScreenShot;
 
public class EggTimerVerifyingPage {
 
	 final WebDriver driver;
	 private static final Logger log = LogManager.getLogger(EggTimerVerifyingPage.class);
	 
	 //Constructor, as every page needs a Webdriver to find elements
	 public EggTimerVerifyingPage(WebDriver driver){
	   this.driver=driver;  
	 }	 
	 	 
	 @FindBy(xpath="//p[@class='ClassicTimer-time']//span")
	 private WebElement timeValue;	
	 
	 //Method that used to enter time using the web elements
	 public void verifyTimeValue(WebDriverWait webDriverWait, String count) throws InterruptedException {
		 
		try {
			 
			   webDriverWait.until(ExpectedConditions.visibilityOf(timeValue));
			   int j = Integer.parseInt(count.substring(0,count.indexOf(".")));
						   
			   // Using SoftAssert to verify every assertion works even if failure in middle
			   SoftAssert softAssert = new SoftAssert();
				
			   for(int i=1; i<=j; i++) {
					
					String timer = timeValue.getText();									
					softAssert.assertEquals(Integer.parseInt(timer.substring(0, 2).trim()), j);			
					j--;
				    Thread.sleep(1000); // Although Thread,sleep() should be avoided but here its a fit case for using it
				    if(Integer.parseInt(timer.substring(0, 2).trim())==0) {				    	
				    	break;
				    }
				}
				softAssert.assertAll();
				webDriverWait.until(ExpectedConditions.alertIsPresent());
				Alert alert = driver.switchTo().alert();				
				alert.accept();
			 	
		 } catch(Exception e) {
			 
				 // Printing logs   
				    log.error("Cannot Calculate Time ---- exception while calculating time");
				    
				 // Capturing screenshot
				 // Note: You can also use "Base" class method "emailCaptureScreenShot" in case want to capture and email the screenshot.
				    try {
				    	     CaptureScreenShot.captureScreenshot(driver);
				        
				    } catch(Exception ex) {
				        	
				        	 log.error("Cannot Calculate Time ---- exception while catching screen shot");
				     	 
				      }
				    
				 // Now i want to stop my test case
				    throw e;
		   }
	 }	
}