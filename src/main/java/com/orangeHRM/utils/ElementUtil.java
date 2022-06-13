package com.orangeHRM.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.orangeHRM.factory.DriverFactory;

public class ElementUtil {
	
	private WebDriver driver;
	private JavaScriptUtil jsUtil;
	
	public ElementUtil(WebDriver driver) {
		this.driver=driver;
		jsUtil= new JavaScriptUtil(driver);
	}

	public  WebElement getElement(By locator) {
		WebElement element= driver.findElement(locator);
		if(Boolean.parseBoolean(DriverFactory.highlite)) {
			jsUtil.flash(element);
		}
		return element;
	}
	
	public List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
	}
	
	public  void doSendKeys(By locator,String value) {
		WebElement element=getElement(locator);
		element.clear();
		element.sendKeys(value);
	}
	
	public void doActionsSendKeys(By locator,String value) {
		Actions act = new Actions(driver);
		act.sendKeys(getElement(locator), value).perform();
	}
	
	public void doActionsClick(By locator) {
		Actions act = new Actions(driver);
		act.click(getElement(locator)).perform();
	}
	
	public void doClick(By locator) {
		getElement(locator).click();
	}
	
	public String doGetTetx(By loactor) {
		return getElement(loactor).getText();
	}
	
	public boolean doIsDisplayed(By locator) {
		return getElement(locator).isDisplayed();
	}
	
	public String getPageURL() {
		return driver.getCurrentUrl();
	}
	
	public List<String> getElementsTextList(By locator) {
		List<String> eleTextList= new ArrayList<String>();
		
		List<WebElement> eleList=getElements(locator);
		for(WebElement e: eleList) {
			if(!e.getText().isEmpty()) {
			eleTextList.add(e.getText());
			}
		}
		return eleTextList;
	}
	
	public  List<String> getElementAttrList(By locator,String attrName) {
		List<String> attrList= new ArrayList<String>();
		
		List<WebElement>eleList=getElements(locator);
		
		for(WebElement e: eleList) {
			attrList.add(e.getAttribute(attrName));
		}
		return attrList;
	}
	
	public void doActionsMoveToElement(By locator) {
		Actions act= new Actions(driver);
		act.moveToElement(getElement(locator)).perform();
	}
	
	public void doActionsMoveToElementandClick(By locator) {
		doActionsMoveToElement(locator);
		getElement(locator).click();
	}
	
	/*** Drodown utils  @param locator * @param index ***/
	
	public void doSelectDropdownByIndex(By locator,int index) {
		Select select = new Select(getElement(locator));
		select.selectByIndex(index);
	}
	
	public void doSelectDropdownByvisibleText(By locator,String text) {
		Select select = new Select(getElement(locator));
		select.deselectByVisibleText(text);
	}
	
	public void doSelectDropdownByValue(By locator,String value) {
		Select select = new Select(getElement(locator));
		select.selectByValue(value);
	}
	
	public void doSelectDropDownValue(By locator,String value) {
		
		Select select= new Select(getElement(locator));
		List<WebElement> optionList=select.getOptions();
		System.out.println(optionList.size());
		
		for(WebElement e:optionList) {
			String text=e.getText();
			System.out.println(text);
			if(text.equals(value)) {
				e.click();
				break;
			}
		}
	} 
	
	//**********Select without select class****************//
	public void doSelectDropDownValueWithOutSelect(By locator,String value) {
		
		List<WebElement> list=getElements(locator);
		
		for(WebElement e: list) {
			if(e.getText().equals(value)){
				e.click();
				break;
			}
			
		}
	}
	
	public String switchToWindowGetTitle(String windowID){
		driver.switchTo().window(windowID);
		String title=driver.getTitle();
		return title;
	}
	
	public WebElement waitForElementPresent(By locator,int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
					
	}
	
	//******* wait utils******//
	public  Alert waitForaAlertPresent(int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver,10);
		return wait.until(ExpectedConditions.alertIsPresent());			
	}
	public String  getText(int timeOut) {
		return waitForaAlertPresent(timeOut).getText();
	}
	
	public void acceptAlert(int timeOut) {
		waitForaAlertPresent(timeOut).accept();
	}
	
	public void dismissAlert(int timeOut) {
		waitForaAlertPresent(timeOut).dismiss();
	}
	
	public  String waitForTitle(String title,int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		wait.until(ExpectedConditions.titleIs(title));
		return driver.getTitle();
	}
	
	public  String waitForTitle(String title,int timeOut,int intervelTime) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut,intervelTime);
		wait.until(ExpectedConditions.titleIs(title));
		return driver.getTitle();
	}
	
	public  String waitForTitleContains(String title,int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		wait.until(ExpectedConditions.titleContains(title)); //partial title is passed
		return driver.getTitle();
	}
	
	public boolean waitForUrl(String url,int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.urlContains(url)); //partial title is passed
	}
	
	public  void waitForFrameSwitchToIt(String idOrName, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(idOrName));
	}
	
	public  void waitForFrameSwitchToIt(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
	}
	
	public  void waitForFrameSwitchToIt(int index, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(index));
	}
	
	public  void waitForFrameSwitchToIt(WebElement freameElement, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(freameElement));
	}
	
	public  WebElement waitForElementpresent(By locator,int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	
	public  WebElement waitForElementVisible(By locator,int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
		

	public  List<WebElement> waitForVisibilityofElements(By locator,int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}
	
	public void printElementsTextStream(By locator,int timeOut) {
		waitForVisibilityofElements(locator, timeOut)
			.stream().forEach(ele->System.out.println(ele.getText()));
	}
	
	public List<String> getElementsListWithTextStrem(By locator,int timeOut,String filter) {
		return waitForVisibilityofElements(locator, timeOut)
			.stream().filter(ele->ele.getText().contains(filter))
			.map(ele->ele.getText()).collect(Collectors.toList());
	}
	
	public void printListElementsStreams(List<String> eleList) {
		eleList.forEach(ele->System.out.println(ele));
	}
	
	public List<WebElement> getElementsListStrem(By locator,int timeOut,String filter) {
		return waitForVisibilityofElements(locator, timeOut)
			.stream().filter(ele->ele.getText()
					.contains(filter)).collect(Collectors.toList());
	}
}
