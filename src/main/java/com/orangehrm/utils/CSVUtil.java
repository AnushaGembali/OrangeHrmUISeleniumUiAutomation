package com.orangehrm.utils;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class CSVUtil {

    private static final String filePath = "./src/test/resources/testdata/OrangeHRMTestData.csv";

    public static Object[][] getTestDataFromCSV(){
        Object[][] testData = null;

        try(FileReader fr = new FileReader(filePath)){
            CSVReader reader = new CSVReader(fr);
            List<String[]> allRows = reader.readAll();

            testData = new Object[allRows.size()][];

            for(int i = 0; i < allRows.size(); i++){
                testData[i] = allRows.get(i);
            }
            return  testData;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (CsvException e) {
            throw new RuntimeException(e);
        }
    }
}
