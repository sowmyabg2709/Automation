package com.orangeHRM.Test;


import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.oranageHRM.Base.BaseTest;
import com.orangeHRM.utils.Constants;



public class LoginPageTest extends BaseTest{

	
	@Test(priority=1)
	public void loginPageTitleTest() {
		String title=loginPage.getLoginTitle();
		System.out.println("title is" + title);
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE);
	}
	
	@Test(priority=2,enabled=false)
	public void getCurrentURLTest() {
		String url=loginPage.getLoginPageURL();
		Assert.assertTrue(url.contains(Constants.LOGIN_PAGE_URL));
	}
	
	@Test(priority=3)
	public void forGotPasswordTest() {
		Assert.assertTrue(loginPage.forgotPasswordDisplay());
	}
	
	@Test(priority=4)
	public void loginTest() {
		loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@DataProvider
	public Object[][] loginNegative() {
		return new Object[][] {{"test@123","test123"},{" ", "test@123"}};
	}
	
	@Test(dataProvider = "loginNegative")
	public void doLoginNegativeTest(String un,String pwd) {
		loginPage.doLoginNegative(un,pwd);
	}
	
}
