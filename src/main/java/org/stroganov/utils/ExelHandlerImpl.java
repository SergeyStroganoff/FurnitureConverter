package org.stroganov.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
@Component
public class ExelHandlerImpl implements ResultHandler {
    private Workbook book;
    private Sheet sheet;
    private final String EXEL_EXT = ".xls";

    private int createExelRow(int rowNumber, String... savingString) {
        Row row = sheet.createRow(rowNumber);
        for (int i = 0; i < savingString.length; i++) {
            Cell rowCell = row.createCell(i);
            rowCell.setCellValue(savingString[i]);
        }
        return rowNumber;
    }

    private void writeMapToExel(Map<String, String> table) {
        int rowNumber = 1;
        for (Map.Entry<String, String> entry : table.entrySet()) {
            createExelRow(rowNumber, entry.getKey(), entry.getValue());
            rowNumber++;
        }
    }

    private void saveExel(String filePathName) {
        try {
            // Записываем всё в файл
            File file = new File(filePathName + EXEL_EXT);
            FileOutputStream outFile = new FileOutputStream(file);
            book.write(outFile);
            System.out.println("Created file: " + file.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("We have got a mistake" + e.getMessage());
        }
    }

    @Override
    public void saveToFile(String filePathName, Map<String, String> table, String sheetName) {
        book = new HSSFWorkbook();
        sheet = book.createSheet(sheetName);
        Row row = sheet.createRow(0);
        Cell tableName = row.createCell(0);
        tableName.setCellValue("Result conversation tables");
        writeMapToExel(table);
        saveExel(filePathName);
    }

    @Override
    public void saveToFile(String filePathName, List<String[]> list, String sheetName) {
        book = new HSSFWorkbook();
        sheet = book.createSheet(sheetName);
        Row row = sheet.createRow(0);
        Cell tableName = row.createCell(0);
        tableName.setCellValue("Result conversation tables");
        writeBufListToExel(list);
        saveExel(filePathName);
    }

    private void writeBufListToExel(List<String[]> list) {
        int rowNumber = 1;
        for (String[] stringBuf : list) {
            createExelRow(rowNumber, stringBuf);
            rowNumber++;
        }
    }
}



