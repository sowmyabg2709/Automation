package com.orangeHRM.factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	WebDriver driver;
	Properties prop;
	private OptionsManager optionsManager;
	
	public static String highlite =null;
	
	public static ThreadLocal<WebDriver> tldriver = new ThreadLocal<>();
	
	public WebDriver init_driver(Properties prop) {
		
		highlite=prop.getProperty("highlight"); //javascript utill usage to get element highlite
		optionsManager =new OptionsManager(prop); // options manage instantiate to get the headless and incognito
		
		String browserName=prop.getProperty("browser").trim();
		System.out.println("Browser name is : "+ browserName);
		
				
		if(browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			tldriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			//driver =new ChromeDriver(optionsManager.getChromeOptions());
		}
		else if(browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			tldriver.set(new FirefoxDriver(optionsManager.getFireFoxOptions()));
			//driver=new FirefoxDriver(optionsManager.getFireFoxOptions());			
		}
		else {
			System.out.println("please pass the correct browser : " +browserName);
		}
		
		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();
		getDriver().get(prop.getProperty("url").trim());
		
		return getDriver();
	}
	
	
	public static synchronized WebDriver getDriver() {
		return tldriver.get();
	}
	
	public Properties init_prop() {
		prop =new Properties();
		try {
			FileInputStream io=new FileInputStream(".\\src\\test\\resources\\config\\config.properties");
			prop.load(io);
		} catch (FileNotFoundException e) {
			System.out.println("file not found");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return prop;
	}
	
}
