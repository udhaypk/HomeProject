package com.testBase;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class TestBase {
	
	public static WebDriver driver;
	public static Properties prop;
	public static Logger logger = LogManager.getLogger(TestBase.class.getName());

	public void initialization() {
		BasicConfigurator.configure();
		System.setProperty("webdriver.chrome.driver", "Driver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(prop.getProperty("url"));
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		
	}
	
	public TestBase() {
		try {
			prop = new Properties();
			FileInputStream fileInputStream = new FileInputStream("Configuration\\data.properties");
			prop.load(fileInputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
