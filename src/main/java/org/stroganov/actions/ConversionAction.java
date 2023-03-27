package org.stroganov.actions;

import org.stroganov.exeptions.FileExtensionError;
import org.stroganov.exeptions.NoSuchSheetException;

import java.io.IOException;
import java.util.List;

public interface ConversionAction {
    List<String[]> convertAline(String sourceFileName, String sheetSourceName) throws IOException, FileExtensionError, NoSuchSheetException;
}
