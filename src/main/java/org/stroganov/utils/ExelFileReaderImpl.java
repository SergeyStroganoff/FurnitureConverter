package org.stroganov.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.stroganov.exeptions.FileExtensionError;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class ExelFileReaderImpl implements ExelFileReader {
    private static final String UNDEFINED = "undefined";

    @Override
    public Map<Integer, List<Object>> readExelTable(String file, String sheetName) throws IOException, FileExtensionError {
        Map<Integer, List<Object>> exelTable = null;
        try (Workbook workbook = loadWorkbook(file);) {
            Iterator<Sheet> sheetIterator = workbook.sheetIterator();
            while (sheetIterator.hasNext()) {
                Sheet sheet = sheetIterator.next();
                if (sheet.getSheetName().equals(sheetName)) {
                    exelTable = processSheet(sheet);
                }
                System.out.println();
            }
        }
        return exelTable;
    }

    public void read(String filename, String sheetName) throws IOException, FileExtensionError {
        try (Workbook workbook = loadWorkbook(filename);) {
            Iterator<Sheet> sheetIterator = workbook.sheetIterator();
            while (sheetIterator.hasNext()) {
                Sheet sheet = sheetIterator.next();
                if (sheet.getSheetName().equals(sheetName)) {
                    processSheet(sheet);
                }
                System.out.println();
            }
        }
    }

    private Workbook loadWorkbook(String filename) throws IOException, FileExtensionError {
        String extension = filename.substring(filename.lastIndexOf(".") + 1).toLowerCase();
        try (FileInputStream fileInputStream = new FileInputStream(new File(filename));) {
            switch (extension) {
                case "xls":
                    // old format
                    return new HSSFWorkbook(fileInputStream);
                case "xlsx":
                    // new format
                    return new XSSFWorkbook(fileInputStream);
                default:
                    throw new FileExtensionError("Unknown Excel file extension: " + extension);
            }
        }
    }

    private HashMap<Integer, List<Object>> processSheet(Sheet sheet) {
        System.out.println("Start processSheet: " + sheet.getSheetName());
        HashMap<Integer, List<Object>> data = new HashMap<>();
        Iterator<Row> iterator = sheet.rowIterator();
        for (int rowIndex = 0; iterator.hasNext(); rowIndex++) {
            Row row = iterator.next();
            processRow(data, rowIndex, row);
        }
        System.out.println("Sheet data:");
        System.out.println(data);
        return data;
    }

    private void processRow(HashMap<Integer, List<Object>> data, int rowIndex, Row row) {
        data.put(rowIndex, new ArrayList<>());
        for (Cell cell : row) {
            processCell(cell, data.get(rowIndex));
        }
    }

    /*
  В методе processCell() для каждой ячейки мы вначале смотрим формат ячейки с помощью метода getCellType().
  Всего есть 4 значимых типа для формата ячейки: это строка, число, булевый (логический) тип и формула,
  в которой значение ячейки вычисляется динамически на основании других ячеек.

  Значение каждого типа мы получаем с помощью соответствующего метода.
  Для текстовых значений мы используем getStringCellValue().
  Для числового типа мы сначала проверяем формат с помощью метода DateUtil.isCellDateFormatted()
  на предмет наличия в ней даты. Если метод возвращает true,
  то интерпретируем значение ячейки как дату с помощью метода getLocalDateTimeCellValue(),
  иначе берём значение как число с помощью getNumericCellValue().
  При этом используем NumberToTextConverter, который преобразует числа в текст.
  Если его не использовать, то даже целые числа будут иметь один десятичный знак после запятой.
  Формула, возвращаемая методом getCellFormula() содержит буквенно-числовые имена ячеек
  и выглядит примерно так: «A2+C2*2».
     */
    private void processCell(Cell cell, List<Object> dataRow) {
        switch (cell.getCellType()) {
            case STRING:
                dataRow.add(cell.getStringCellValue());
                break;
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    dataRow.add(cell.getLocalDateTimeCellValue());
                } else {
                    dataRow.add(NumberToTextConverter.toText(cell.getNumericCellValue()));
                }
                break;
            case BOOLEAN:
                dataRow.add(cell.getBooleanCellValue());
                break;
            case FORMULA:
                dataRow.add(cell.getCellFormula());
                break;
            default:
                dataRow.add(UNDEFINED);
        }
    }
}
