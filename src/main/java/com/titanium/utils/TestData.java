package com.titanium.utils;

import org.testng.annotations.DataProvider;

public class TestData {
    static Object[][] testObjArray;
    static String testCaseWorkBook = System.getProperty("user.dir") + "\\resources\\FlightRegisterData.xlsx";
    static String sheetData = "RegisterUser";

    @DataProvider(name = "UserRegistration")
    public static Object[][] Authentication() throws Exception {
        return testObjArray = ExcelUtils.getTableArray(testCaseWorkBook, sheetData);
    }
}
