package com.testPages;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.testBase.TestBase;
import com.testUtilities.CommonMethods;

public class KpTest extends TestBase{
	
	
	CommonMethods generalMethods = new CommonMethods();
	
	@FindBy(xpath="//a[contains(text(),'Register')]")
	static WebElement register_btn;
	@FindBy(xpath="//a[contains(text(),'my account')]")
	static WebElement create_account;
	@FindBy(xpath="//h1[contains(text(),'yourself')]")
	static WebElement account_title;
	@FindBy(xpath="//select[@id='region']")
	static WebElement region;
	@FindBy(xpath="//input[@id='mrn']")
	static WebElement mrn_number;
	@FindBy(xpath="//input[@id='firstName']")
	static WebElement first_name;
	@FindBy(xpath="//input[@id='lastName']")
	static WebElement last_name;
	@FindBy(xpath="//input[@id='dob']")
	static WebElement dob;
	@FindBy(xpath="//button[@id='member-next']")
	static WebElement continue_btn;
	@FindBy(xpath="//p[@id='system-error-msg']")
	static WebElement error_msg;
	
	@FindBy(xpath="//span[contains(text(),'region')]")
	static WebElement region_msg;
	
	@FindBy(xpath="//label[contains(text(),'AREA')]")
	static WebElement out_region_msg;
	
	@FindBy(xpath="//span[contains(text(),'Record')]")
	static WebElement mrn_msg;
	@FindBy(xpath="//span[contains(text(),'first')]")
	static WebElement firstname_msg;
	@FindBy(xpath="//span[contains(text(),'last')]")
	static WebElement lastname_msg;
	@FindBy(xpath="//span[contains(text(),'birth')]")
	static WebElement dob_msg;
	
	@FindBy(xpath="	//p[contains(text(),'following')]")
	static WebElement missing_msg;

	public KpTest() {
		PageFactory.initElements(driver, this);
	}
	
	public boolean newRegistration() {
		register_btn.click();
		if(generalMethods.elementPresenceCheck(create_account)) {
			create_account.click();
		}
		else {
			WebDriverWait wait = new WebDriverWait(driver, 200);
			wait.until(ExpectedConditions.elementToBeClickable(create_account));
		}
		return true;
		
	}
	
	public boolean registrationPage() throws Exception {
		
		HashMap<String, String> map = generalMethods.readfromexcel_registration();
		
		for(Map.Entry<String, String> m : map.entrySet()) {
			System.out.println(m.getKey() + "-->" + m.getValue());
		}
		
		if(generalMethods.elementPresenceCheck(account_title)) {
	
				generalMethods.selectvalueFromDropdown_Text(region, map.get("Region"));
				mrn_number.sendKeys(map.get("MRN"));
				first_name.sendKeys(map.get("Firstname"));
				last_name.sendKeys(map.get("Lastname"));
				dob.sendKeys(map.get("DOB"));
				continue_btn.click();
			
			if(map.get("Region")=="") {
				generalMethods.elementPresenceCheck(region_msg);
				System.out.println("Region should be select");
			}
				
			if(map.get("MRN")=="") {
				generalMethods.elementPresenceCheck(mrn_msg);
				System.out.println("MRN should be enter");
			}
			
			if(map.get("Firstname")=="") {
				generalMethods.elementPresenceCheck(firstname_msg);
				System.out.println("Firstname should be enter");
			}
			
			if(map.get("Lastname")=="") {
				generalMethods.elementPresenceCheck(lastname_msg);
				System.out.println("Lastname should be enter");
			}
			
			if(map.get("DOB")=="") {
				generalMethods.elementPresenceCheck(dob_msg);
				System.out.println("DOB should be enter");
			}
			return true;
		}
		else 
		{
			return false;
		}
	}
	
	public boolean validatePage() {
		if(generalMethods.elementPresenceCheck(error_msg)) {
			System.out.println("User cannot proceed the registration process since the invalid MRN/data");
		}
		
		return true;
	}

}
