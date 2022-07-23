package utils;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ExcelFileReader {
    static    Workbook book;
      static Sheet sheet;
public static void openExcelFile(String filePath){
    try {
        FileInputStream fileInput=new FileInputStream(filePath);
        try {
            book=new XSSFWorkbook(fileInput);
        } catch (IOException e) {
            e.printStackTrace();
        }
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    }
}
    public static void getSheet(String sheetName){
     sheet=book.getSheet(sheetName);
    }
    public static int getRows(){
     return    sheet.getPhysicalNumberOfRows();
    }
    public static int getColsCount(int rowIndex){
        return    sheet.getRow(rowIndex).getPhysicalNumberOfCells();
    }
    public static String getData(int rowIndex,int colIndex){
        return    sheet.getRow(rowIndex).getCell(colIndex).toString();
    }
    public static List<Map<String,String>> readExcelData(String filePath,String sheetName)
{
    openExcelFile(filePath);
    getSheet(sheetName);
        List<Map<String,String>> dataList=new ArrayList();
    for (int i = 1; i <getRows() ; i++) {
        Map<String,String> map=new LinkedHashMap();
        for (int j = 0; j < getColsCount(i); j++) {
            map.put(getData(0,j),getData(i,j));

        }dataList.add(map);
    }

        return dataList;
    }

}
