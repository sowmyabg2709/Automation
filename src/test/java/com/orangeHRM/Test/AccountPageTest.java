package com.orangeHRM.Test;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.oranageHRM.Base.BaseTest;
import com.orangeHRM.utils.Constants;
import com.orangeHRM.utils.Error;

public class AccountPageTest extends BaseTest{
	
	@BeforeClass
	public void accPageSetup() {
		accountPage=loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		
	}
	
	@Test
	public void accountPageTitleTest() {
		String title=accountPage.getHomePageTitle();
		Assert.assertEquals(title, Constants.HOME_PAGE_TITLE,Error.ACC_PAGE_TITLE_ERROR);
	}
	
	@Test
	public void accountPageSubHeaderTest() {
		boolean header=accountPage.getsubHeader();		 
		Assert.assertTrue(header, "header is displayed");
	}
	
	@Test
	public void MyAccountListTest() {
		List<String> listAccount=accountPage.getMyAccountList();
		for(String e:listAccount) {
			System.out.println(e);
		}
		//Collections.sort(Constants.HOME_PAGE_ACCOUNT_LIST);
		Assert.assertEquals(listAccount, Constants.HOME_PAGE_ACCOUNT_LIST);
	}
	
	@Test
	public void signOutLinkTest() {
		Assert.assertTrue(accountPage.signOutCheck());
	}

}
