package com.testerfabrik.utils;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ExcelUtils {
	//XSSF para .xlsx y HSSF para .xls
	private static XSSFSheet ExcelWSheet;
	private static XSSFWorkbook ExcelWBook;
	private static org.apache.poi.ss.usermodel.Cell Cell;
	
	public static Object[][] getTableArray(String FilePath, String SheetName) throws Exception {
		String[][] tabArray = null;
		try {
			// Abrir el archivo de excel
			FileInputStream ExcelFile = new FileInputStream(FilePath);

			// Acceder a la hoja de datos requerida
			ExcelWBook = new XSSFWorkbook(ExcelFile);
			ExcelWSheet = ExcelWBook.getSheet(SheetName);

			// Declaración de Variables
			int startRow, startCol, ci, cj;

			// Obtener el total de filas
			int totalRows = ExcelWSheet.getLastRowNum();
			
			//Obtener el total de columnas
			int totalCols = ExcelWSheet.getRow(0).getLastCellNum();

			// Darle dimensiones al arreglo de String
			tabArray = new String[totalRows][totalCols];
			ci=0;
            // Iniciamos en 1, ya que la primer fila es la de títulos
			for(startRow = 1; startRow <=totalRows; startRow++, ci++){
				cj= 0;
                // Iniciamos en 0 ya que se necesitan todas las columnas
				for(startCol = 0; startCol<totalCols; startCol++, cj++){
				    // Llenar el arreglo Objetc con datos
					tabArray[ci][cj] = getCellDataDDT(startRow,startCol);
				}
			}	
		}catch (FileNotFoundException e){
			throw new Exception("Class ExcelUtils | Method getTableArray | Exception desc: Excel not found: "+e.getMessage());
		}catch (IOException e){
			throw new Exception("Class ExcelUtils | Method getTableArray | Exception desc: Could not read the Excel sheet: "+e.getMessage());  
		} 
		return(tabArray);  
	} 

	// Este método es para leer los datos de prueba de la celda de Excel, estamos pasando los parámetros como Row num y Col num
	public static String getCellDataDDT(int RowNum, int ColNum) throws Exception {
        try{
            Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
            String data = "";
            // Convertir el dato de la celda a String
            if(Cell.getCellTypeEnum()== CellType.STRING){
                data = Cell.getStringCellValue();
            }else if (Cell.getCellTypeEnum()== CellType.NUMERIC){
                data = String.valueOf(Cell.getNumericCellValue());
            }
            return data;
        }catch (Exception e){
            throw new Exception("Class ExcelUtils | Method getCellDataDDT | Exception desc: " + e.getMessage());
        }
    }
}
