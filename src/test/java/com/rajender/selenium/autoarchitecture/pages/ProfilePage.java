package com.rajender.selenium.autoarchitecture.pages;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.testng.Assert;
 
public class ProfilePage {
 
	final WebDriver driver;
	 
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
		 
		 WebElement spanElement = spanElements.get(spanElements.size()-1);
		 WebElement footerElement = footerElements.get(footerElements.size()-1);
		 hideElement(driver, spanElement);
		 hideElement(driver, footerElement);
		 
		 gotoStore.click();
		 
		 visibleElement(driver, spanElement);
		 visibleElement(driver, footerElement);
	 }
}