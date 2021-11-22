package com.rajender.selenium.autoarchitecture.pages;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.testng.Assert;

import com.rajender.selenium.autoarchitecture.utilities.CaptureScreenShot;

public class ProfilePage {
 
	 final WebDriver driver;
	 private static final Logger log = LogManager.getLogger(ProfilePage.class);
	 
	 //Constructor, as every page needs a Webdriver to find elements
	 public ProfilePage(WebDriver driver){
	 this.driver=driver;
	 }
	 
	 @FindBys({
	 @FindBy(id="books-wrapper"),
	 @FindBy(id="userName-value")
	 })
	 private WebElement user;
	 
	 @CacheLookup
	 @FindBy(id="submit")
	 private WebElement logoutBtn;
	 
	 @FindBy(xpath="//input[@id='searchBox']")
	 private WebElement searchBox;	
	 
	 @FindBy(xpath="//button[@id='gotoStore']")
	 private WebElement gotoStore;	 

	 // These "tagName" attribute has been used in hiding "span" element as we are getting 
	 /*
		   [ERROR] testProfilePageElement(com.techugo.swadesi.testcases.ProfileTest)  Time elapsed: 1.126 s  <<< FAILURE!
	        org.openqa.selenium.ElementClickInterceptedException: 
	        element click intercepted: Element <button id="gotoStore" type="button" class="btn btn-primary">...</button> is not clickable at 
	        point (706, 678). Other element would receive the click: <span>...</span>
	        (Session info: chrome=88.0.4324.190)
	  */
	  // Actually Last header and footer are creating issues (org.openqa.selenium.ElementClickInterceptedException) whose  
	 // location comes after the "Go To Book Store" button
	 
	
	 @FindBy(tagName = "span") 
	 private List<WebElement> spanElements;
	 
	 
	// These "tagName" attribute has been used in hiding "footer" element as we are getting 
	/*
		   [ERROR] testProfilePageElement(com.techugo.swadesi.testcases.ProfileTest)  Time elapsed: 1.126 s  <<< FAILURE!
	        org.openqa.selenium.ElementClickInterceptedException: 
	        element click intercepted: Element <button id="gotoStore" type="button" class="btn btn-primary">...</button> is not clickable at 
	        point (706, 678). Other element would receive the click: <footer>...</footer>
	        (Session info: chrome=88.0.4324.190)
	*/
	 // Actually Last header and footer are creating issues (org.openqa.selenium.ElementClickInterceptedException) whose  
	 // location comes after the "Go To Book Store" button
	 
	 
	 @FindBy(tagName = "footer") 
	 private List<WebElement> footerElements;
	 
	 
	 private void hideElement(WebDriver webdriver, WebElement element){
		      
		    ((JavascriptExecutor) webdriver).executeScript("arguments[0].style.visibility='hidden'", element);
		   // System.out.println("====11=====================   " + element.isDisplayed());	  
	 }
		
	 private void visibleElement(WebDriver webdriver, WebElement element){
		         
		    ((JavascriptExecutor) webdriver).executeScript("arguments[0].style.visibility='visible'", element);
		  //  System.out.println("====22=====================   " + element.isDisplayed());	  
	 }
	
	
	 //Method to check logged in username
	 public void verifyUser(String usrNm){ 
		 Assert.assertEquals(usrNm, user.getText()); 
	 }
	 
	 //method to logout
	 public void logout_Action(){
		 // Let's log off now!!!!
		 logoutBtn.click();
	 }
	 
	//Method to verify Profile Page 
	 public void verifyProfilePageElement(WebDriver driver, String elementName){
		 
		 Assert.assertEquals(elementName, searchBox.getAttribute("id"));
		 
		 // Actually Last header and footer are creating issues (org.openqa.selenium.ElementClickInterceptedException) whose  
		 // location comes after the "Go To Book Store" button
		 /*
		     <footer>
              <span>© 2013-2020 TOOLSQA.COM | ALL RIGHTS RESERVED.</span>
             </footer>
		  */
		 
		 /* There is another failure due to Google ads (using iframe). 
		  * But this will not happen in QA or Dev environment. So no issue in test environment.
		  *  <iframe frameborder="0" src="https://2432085e8588868ef9f7be48cf884b0e.safeframe.googlesyndication.com/safeframe/1-0-38/html/container.html" 
                id="google_ads_iframe_/21849154601,22343295815/Ad.Plus-Anchor_0" 
				title="3rd party ad content"
				name="" 
				scrolling="no"
				 marginwidth="0" 
				marginheight="0" 
				width="728" 
				height="90" 
				data-is-safeframe="true" 
				sandbox="allow-forms allow-popups allow-popups-to-escape-sandbox allow-same-origin allow-scripts allow-top-navigation-by-user-activation"
				allow="attribution-reporting" 
				data-google-container-id="1" 
				style="border: 0px; 
				vertical-align: bottom;" 
				data-load-complete="true">
			  </iframe>
		  */
		 
		 WebElement spanElement = spanElements.get(spanElements.size()-1);
		 WebElement footerElement = footerElements.get(footerElements.size()-1);
		 hideElement(driver, spanElement);
		 hideElement(driver, footerElement);
		 
			 try {
				 	gotoStore.click();
				 	
			 } catch(Exception e) {
				 
					 // Printing logs   
					    log.error("Cannot Verify Profile Page Element ---- exception while clicking button");
					    
					 // Capturing screenshot
					 // Note: You can also use "Base" class method "emailCaptureScreenShot" in case want to capture and email the screenshot.
					    try {
					    	     CaptureScreenShot.captureScreenshot(driver);
					        
					    } catch(Exception ex) {
					        	
					        	 log.error("Cannot Verify Profile Page Element ---- exception while catching screen shot");
					        	 
					      }
					    
					 // Now i want to stop my test case
					    throw e;
			   }
			 
		 visibleElement(driver, spanElement);
		 visibleElement(driver, footerElement);
	 }
}