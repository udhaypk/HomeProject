package com.testUtilities;

import java.io.FileInputStream;
import java.util.HashMap;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.testBase.TestBase;

public class CommonMethods extends TestBase{
	
	public boolean elementPresenceCheck(WebElement element) {
		try {
			logger.info("*******Element presence check*********" + element.isDisplayed());
			if (element.isDisplayed()) {
			logger.info("Element is displayed in UI");
				return true;
			} else {
			logger.info("Element is not presenet in UI");
				return false;
			}
		}catch (org.openqa.selenium.NoSuchElementException e) {
			logger.info("No Such Element Exception Occur");
			return false;
		}catch (Exception e) {
			logger.info("Exception occured - "+e);
			return false;
		}
	}
	
	public String selectvalueFromDropdown_Value(WebElement element,String value)
	{
		Select select = new Select(element);
		select.selectByValue(value);
		String sv = select.getFirstSelectedOption().getText();
		logger.info(sv);
		return sv;
	}
	
	public void selectvalueFromDropdown_Index(WebElement element,int value)
	{
		Select select = new Select(element);
		logger.info(""+select.getOptions().size());
		select.selectByIndex(value);
		String sv = select.getFirstSelectedOption().getText();
		logger.info(sv);
	}
	
	
	public void selectvalueFromDropdown_Text(WebElement element,String value)
	{
		logger.info("Element is" + element +"---> value : " + value);
		Select select = new Select(element);
		select.selectByVisibleText(value);
		String sv = select.getFirstSelectedOption().getText();
		logger.info(sv);
	}
		
	public  HashMap<String, String> readfromexcel_registration() throws Exception{
		try {
			
			HashMap<String, String> map = new HashMap<String, String>();
			String key = null;
			String val = null;
			
			FileInputStream fis = new FileInputStream("TestData\\Data.xlsx");
		    XSSFWorkbook wb = new XSSFWorkbook(fis);
		    XSSFSheet sheet = wb.getSheet("Registration");
		    for(int i = 0; i <= 4; i++) 
		    {
			    	key = sheet.getRow(i).getCell(0).toString();
			    	val = sheet.getRow(i).getCell(1).toString();
			    	map.put(key, val);
		    }
		 return map;
		 
		} catch (Exception e) {
			System.out.println("Error while retriving the values from excel sheet");
			e.printStackTrace();
			return null;
		} 
	}

}
