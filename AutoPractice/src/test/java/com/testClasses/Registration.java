package com.testClasses;


import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.testBase.TestBase;
import com.testPages.KpTest;

public class Registration extends TestBase{

	
	@Test
	public void TC01_invalidUser() throws Exception {
		
		initialization();
		
		KpTest kp = new KpTest();
		
		Assert.assertEquals(kp.newRegistration(), true);
		Assert.assertEquals(kp.registrationPage(), true);
		Assert.assertEquals(kp.validatePage(), true);
	}
	
	@AfterTest
	public void closeBrowser() {
		driver.quit();
	}

}
