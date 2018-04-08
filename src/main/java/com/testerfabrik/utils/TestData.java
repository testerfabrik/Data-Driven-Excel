package com.testerfabrik.utils;

import org.testng.annotations.DataProvider;

public class TestData {
    static Object[][] testObjArray;
    // Localización del archivo excel
    static String testCaseWorkBook = System.getProperty("user.dir") + "\\resources\\FlightRegisterData.xlsx";
    //Nombre de la Hoja donde están los datos
    static String sheetData = "RegisterUser";

    @DataProvider(name = "UserRegistration")
    public static Object[][] Authentication() throws Exception {
        return testObjArray = ExcelUtils.getTableArray(testCaseWorkBook, sheetData);
    }
}
