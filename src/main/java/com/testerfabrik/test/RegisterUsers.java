package com.testerfabrik.test;

import com.testerfabrik.commons.Commons;
import com.testerfabrik.utils.LocatorType;
import com.testerfabrik.utils.TestData;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.*;

public class RegisterUsers  extends Commons {

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

		// Agregando la información de contacto
		typeInTextBox(LocatorType.Name, "firstName",registerInfo[0]);
		typeInTextBox(LocatorType.Name, "lastName", registerInfo[1]);
		typeInTextBox(LocatorType.Name, "phone", registerInfo[2]);
		typeInTextBox(LocatorType.Id, "userName",registerInfo[3]);

		// Agregando la información de correo
		typeInTextBox(LocatorType.Name, "address1", registerInfo[4]);
		typeInTextBox(LocatorType.Name, "city", registerInfo[5]);
		typeInTextBox(LocatorType.Name, "state", registerInfo[6]);
		typeInTextBox(LocatorType.Name, "postalCode", registerInfo[7]);
		selectFromDropDown(LocatorType.Name, "country", registerInfo[8]);

		// Agregando la información del usuario
		typeInTextBox(LocatorType.Id, "email", registerInfo[9]);
		typeInTextBox(LocatorType.Name, "password", registerInfo[10]);
		typeInTextBox(LocatorType.Name, "confirmPassword", registerInfo[10]);
		getDriver().findElement(By.name("confirmPassword")).submit();

		// Verificar si el nombre de usuario se encuentra
		Assert.assertTrue(getElementText().contains(registerInfo[9]));
	}
}
