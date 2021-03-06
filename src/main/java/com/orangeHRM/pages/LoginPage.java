package com.orangeHRM.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.orangeHRM.utils.Constants;
import com.orangeHRM.utils.ElementUtil;

public class LoginPage {
	private WebDriver driver;
	private ElementUtil elementUtil;
	
	//1.private By locators                                                                                                                                                                                                        
	private By userName = By.id("email");
	private By password = By.id("passwd");
	private By loginBtn=By.id("SubmitLogin");
	private By forgotPassword=By.xpath("//a[text()='Forgot your password?']");
	private By loginErrorMessage=By.xpath("(//div[@class='alert alert-danger'])[1]");
	
	//2.constructor
	public LoginPage(WebDriver driver) {	
		this.driver=driver;
		elementUtil = new ElementUtil(driver);
	}
	
	//3.public page actions(methods)
	public String getLoginTitle() {
		return elementUtil.waitForTitle(Constants.LOGIN_PAGE_TITLE, 5);
	}
	
	public String getLoginPageURL() {
		return elementUtil.getPageURL();
	}
	
	public boolean forgotPasswordDisplay() {
		return elementUtil.doIsDisplayed(forgotPassword);
	}
	
	public AccountPage doLogin(String un,String pwd) {
		elementUtil.doSendKeys(userName, un);
		elementUtil.doSendKeys(password,pwd);
		
		elementUtil.doActionsClick(loginBtn);
		
		return new AccountPage(driver);				
	}
	
	public boolean doLoginNegative(String un,String pwd) {
		elementUtil.doSendKeys(userName, un);
		elementUtil.doSendKeys(password,pwd);
		
		elementUtil.doActionsClick(loginBtn);
		
		return elementUtil.doIsDisplayed(loginErrorMessage);				
	}
}
