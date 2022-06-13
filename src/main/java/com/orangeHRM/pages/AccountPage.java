package com.orangeHRM.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.orangeHRM.utils.Constants;
import com.orangeHRM.utils.ElementUtil;

public class AccountPage {
	private ElementUtil elementUtil;
	private WebDriver driver;

	private By subHeader= By.xpath("//span[text()='My account']");
	private By logo= By.id("header_logo");
	private By myAccount=By.xpath("//ul[@class='myaccount-link-list']//span");
	private By signOut=By.className("logout");
	private By searchBar= By.id("search_query_top");
	private By searchBtn=By.name("submit_search");
	
	public AccountPage(WebDriver driver) {
		this.driver=driver;
		elementUtil =new ElementUtil(driver);
	}
	
	public String getHomePageTitle() {
		return elementUtil.waitForTitle(Constants.HOME_PAGE_TITLE, 5);
	}
			
	public boolean getsubHeader() {
		return elementUtil.doIsDisplayed(subHeader);
	}
	
	public boolean logoCheck() {
		return elementUtil.doIsDisplayed(logo);
	}
	
	public List<String> getMyAccountList() {
		List <String> valList=new ArrayList<String>();
		List<WebElement> subHeaders= elementUtil.waitForVisibilityofElements(myAccount, 5);
		for(WebElement e: subHeaders) {
			valList.add(e.getText());
		}
		return valList;
	}
	
	public boolean signOutCheck() {
		return elementUtil.doIsDisplayed(signOut);
	}
	
	public SearchResultPage doSearch(String productName) {
		elementUtil.doSendKeys(searchBar, productName);
		elementUtil.doClick(searchBtn);
		
		return new SearchResultPage(driver);
	}
	
	
}
