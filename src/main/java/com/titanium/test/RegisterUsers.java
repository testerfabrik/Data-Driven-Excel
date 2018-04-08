package com.titanium.test;

import com.titanium.commons.Commons;
import com.titanium.utils.LocatorType;
import com.titanium.utils.TestData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class RegisterUsers  extends Commons {
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

		//Adding Contact Information
		typeInTextBox(LocatorType.Name, "firstName",registerInfo[0]);
		typeInTextBox(LocatorType.Name, "lastName", registerInfo[1]);
		typeInTextBox(LocatorType.Name, "phone", registerInfo[2]);
		typeInTextBox(LocatorType.Id, "userName",registerInfo[3]);

		//Adding Mailing Information
		typeInTextBox(LocatorType.Name, "address1", registerInfo[4]);
		typeInTextBox(LocatorType.Name, "city", registerInfo[5]);
		typeInTextBox(LocatorType.Name, "state", registerInfo[6]);
		typeInTextBox(LocatorType.Name, "postalCode", registerInfo[7]);
		selectFromDropDown(LocatorType.Name, "country", registerInfo[8]);

		//Adding User Information
		typeInTextBox(LocatorType.Id, "email", registerInfo[9]);
		typeInTextBox(LocatorType.Name, "password", registerInfo[10]);
		typeInTextBox(LocatorType.Name, "confirmPassword", registerInfo[10]);
		getDriver().findElement(By.name("confirmPassword")).submit();

		//Verify user name is displayed
		Assert.assertTrue(getElementText().contains(registerInfo[9]));
	}
}
