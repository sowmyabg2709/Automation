package com.oranageHRM.Base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.orangeHRM.factory.DriverFactory;
import com.orangeHRM.pages.AccountPage;
import com.orangeHRM.pages.LoginPage;
import com.orangeHRM.pages.ProductInfoPage;
import com.orangeHRM.pages.SearchResultPage;

public class BaseTest {
	DriverFactory df ;
	public WebDriver driver;
	public Properties prop;
	
	public LoginPage loginPage;
	public AccountPage accountPage;
	public SearchResultPage searchRstPage;
	public ProductInfoPage prodInfoPage;
	
	@Parameters({"browser"})
	@BeforeTest
	public void setUp(String browserName) {
		df = new DriverFactory();
		prop=df.init_prop(); 
		prop.setProperty("browser", browserName);
		driver =df.init_driver(prop);	
		
		loginPage = new LoginPage(driver);
		
	}
	
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
