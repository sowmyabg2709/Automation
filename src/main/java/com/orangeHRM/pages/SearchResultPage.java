package com.orangeHRM.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.orangeHRM.utils.ElementUtil;

public class SearchResultPage {
	
	private WebDriver driver;
	private ElementUtil elementUtil;
	
	private By searchItemResult=By.xpath("//div[@class='product-container']");
	//private By resultItemsQucickView=By.xpath("//div[@class='product-container']//a/following-sibling::a/span[text()='Quick view']");
	private By resultItems=By.xpath("//div[@class='product-container']//img");
			
	public SearchResultPage(WebDriver driver) {
		this.driver=driver;
		elementUtil=new ElementUtil(driver);
	}
	
	public int getSearchResult() {
		return elementUtil.getElements(searchItemResult).size();
	}
	
	public ProductInfoPage selectProductFromResult(String productName) {
		List<WebElement> resultItemList=elementUtil.getElements(resultItems);
		System.out.println("total number of items displayed for :"
				+ " "+ productName+" "+resultItemList.size());
		
		for(WebElement e:resultItemList) {
			if(e.getText().equals(productName)) {
				e.click();
				break;
			}
		}
		return new ProductInfoPage(driver);
	}

}
