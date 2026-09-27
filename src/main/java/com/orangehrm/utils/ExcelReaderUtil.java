package com.orangehrm.utils;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ExcelReaderUtil {

    private static final String filePath = "./src/test/resources/testdata/OrangeHRMTestData.xslx";

    public static Object[][] getDataFromExcelSheet(String sheetName){
        Object[][] testData = null;
        try(FileInputStream fp = new FileInputStream(filePath)) {
            Workbook wb = WorkbookFactory.create(fp);
            Sheet sheet = wb.getSheet(sheetName);

            int rowCount = sheet.getLastRowNum();
            int columnCount = sheet.getRow(0).getLastCellNum();

            testData = new Object[rowCount][columnCount];

            for(int i = 0; i < rowCount; i++){
                for(int j = 0; j < columnCount; j++){
                    testData[i][j] = sheet.getRow(i+1).getCell(j).toString();
                }
            }
            return testData;
        } catch (FileNotFoundException e) {
            System.out.println("File is not found at location: " + filePath);
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.out.println("Error while reading data from sheet: " + sheetName);
            throw new RuntimeException(e);
        } catch (InvalidFormatException e) {
            throw new RuntimeException(e);
        }

    }
}
