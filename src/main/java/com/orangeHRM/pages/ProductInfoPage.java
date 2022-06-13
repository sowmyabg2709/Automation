package com.orangeHRM.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.orangeHRM.utils.ElementUtil;

public class ProductInfoPage {
	private WebDriver driver;
	private ElementUtil elementUtil;
	
	private By productHeader= By.xpath("//h1[text()='Printed Summer Dress']");
	private By productImage=By.xpath("//div[@id='thumbs_list']//img");
	private By productCondition=By.xpath("//div[contains(@class,'pb-center-column')]/p/following-sibling::p");
	private By quantity=By.xpath("//input[@id='quantity_wanted']");
	private By addToCart=By.id("add_to_cart");
	private By successMessage=By.xpath("(//div[@id='layer_cart']//h2)[1]");
	
	
	public ProductInfoPage(WebDriver driver) {
		this.driver=driver;
		elementUtil =new ElementUtil(driver);
	}
	
	public String getProductHeader() {
		return elementUtil.doGetTetx(productHeader);
	}
	
	public int getProductImageCount() {
		return elementUtil.getElements(productImage).size();
	}
	
	/**
	 * this method will collect the product metadata and pricing data info in the form of
	 * hashmap
	 * this method will return product info map in key and value map 
	 * @return 
	 */
	
	
	public List<String> getProductCondition() {
		List<String> listCount= new ArrayList<String>();
		
		List<WebElement> prodConditionList=elementUtil.getElements(productCondition);
		for(WebElement e:prodConditionList) {
			listCount.add(e.getText());
		}
		return listCount;
	}
	
	public void addQuantity(String qnt) {
		elementUtil.doSendKeys(quantity, qnt);
	}
	
	public void addToCart() {
		elementUtil.doClick(addToCart);
	}
	
	public String getSuccessMsg() {
		return elementUtil.doGetTetx(successMessage);
	}
}
