package org.stroganov.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

public class ExelHandlerImpl implements ResultHandler {

    private Workbook book;
    private Sheet sheet;
    private final String EXELEXT = ".xls";

    public ExelHandlerImpl() {
    }

    private int createExelRow(int rowNumber, String... stringOfEmail) {
        Row row = sheet.createRow(rowNumber);
        for (int i = 0; i < stringOfEmail.length; i++) {
            Cell rowCell = row.createCell(i);
            rowCell.setCellValue(stringOfEmail[i]);
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
            File file = new File(filePathName + EXELEXT);
            FileOutputStream outFile = new FileOutputStream(file);
            book.write(outFile);
            System.out.println("Created file: " + file.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("We have got a mistake" + e.getMessage());
        }
    }
    @Override
    public void saveToFile(String filePathName, Map<String, String> table) {
        book = new HSSFWorkbook();
        sheet = book.createSheet("Result");
        Row row = sheet.createRow(0);
        Cell tableName = row.createCell(0);
        tableName.setCellValue("Result conversation tables");
        writeMapToExel(table);
        saveExel(filePathName);
    }
}



