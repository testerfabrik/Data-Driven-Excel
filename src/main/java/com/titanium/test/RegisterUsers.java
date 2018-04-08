package com.titanium.test;

import com.titanium.commons.Bussines;
import com.titanium.utils.LocatorType;
import com.titanium.utils.TestData;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class RegisterUsers  extends Bussines {
    public WebDriver driver;

	@BeforeTest
	public void setUp(){
		navigateTo();
	}
	
	@AfterTest
	public void tearDown(){
		getDriver().quit();
	}
	
	@BeforeMethod
	public void clickRegister(){
		clickOnLink(LocatorType.LinkText, "REGISTER");
	}
	
	@Test(dataProviderClass = TestData.class, dataProvider = "UserRegistration", description="Test Case for Register an user")
	public void registerUserInformation(String ... registerInfo){
		String[] contactInfo = new String[4];
		String[] mailInfo = new String[5];

		//Store data for mail info and contact info
		for(int i = 0; i <= 8; i++){
			if(i>3){
				mailInfo[i-4] = registerInfo[i];
			}else{
				contactInfo[i] = registerInfo[i];
			}
		}

		//Adding Contact Information
		addContactInfo(contactInfo);

		//Adding Mailing Information
		addMailingInfo(mailInfo);

		//Adding User Information
		submitUserInfo(registerInfo[9], registerInfo[10]);

		//Verify user name is displayed
		Assert.assertTrue(getElementText().contains(registerInfo[9]));
	}
}
