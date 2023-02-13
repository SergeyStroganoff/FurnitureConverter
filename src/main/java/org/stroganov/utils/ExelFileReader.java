package org.stroganov.utils;

import org.stroganov.exeptions.FileExtensionError;
import org.stroganov.exeptions.NoSuchSheetException;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface ExelFileReader {
    Map<Integer, List<Object>> readExelTable(String file, String sheetName) throws IOException, FileExtensionError, NoSuchSheetException;
}
