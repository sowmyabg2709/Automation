package com.orangeHRM.Test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.oranageHRM.Base.BaseTest;

public class ProductInfoTest extends BaseTest{

	 SoftAssert softasrt= new SoftAssert();
	
	@BeforeClass
	public void productInfoSetup() {
		accountPage=loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		
	}
	
	@Test
	public void productCountTest() {
		searchRstPage=accountPage.doSearch("dress");
		Assert.assertTrue(searchRstPage.getSearchResult()==7);
	}
	
	@Test
	public void productInfoTest() {
		searchRstPage=accountPage.doSearch("dress");
		prodInfoPage=searchRstPage.selectProductFromResult("Printed Summer Dress");
		//System.out.println(prodInfoPage.getProductHeader());
		softasrt.assertAll();
	}
	
	
}