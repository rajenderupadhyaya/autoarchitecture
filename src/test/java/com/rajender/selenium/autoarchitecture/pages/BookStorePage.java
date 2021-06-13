package com.rajender.selenium.autoarchitecture.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import org.testng.Assert;
 
public class BookStorePage {
 
	final WebDriver driver;
	 
	 //Constructor, as every page needs a Webdriver to find elements
	 public BookStorePage(WebDriver driver){
	   this.driver=driver;
	 }	 
	
	 @FindBy(xpath="//input[@id='searchBox']")
	 private WebElement searchBox;  	  
	 
	 @FindBy(xpath="//a[@href='/books?book=9781449325862']")
	 private WebElement hyperLink;
	 
	 public void verifyBookExistence(String bookTitle) {		 
		 searchBox.sendKeys(bookTitle); 
		 Assert.assertEquals(bookTitle, hyperLink.getText());
	 }	
	 
}