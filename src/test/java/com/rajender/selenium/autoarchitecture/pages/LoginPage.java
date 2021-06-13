package com.rajender.selenium.autoarchitecture.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
 
public class LoginPage {
 
	 final WebDriver driver;
	 
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
		 username.click();
		 username.clear(); 
		 username.sendKeys(uName);
		 pswd.click();
		 pswd.clear();
		 pswd.sendKeys(pwd);
		 loginBtn.click();
	 }
	 
	 public void verifyLoginInvalidCred(){
		 Assert.assertEquals("Invalid username or password!", errorMessage.getText());
	 }
}