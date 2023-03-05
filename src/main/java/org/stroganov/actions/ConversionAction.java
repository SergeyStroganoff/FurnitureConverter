package org.stroganov.actions;

import org.stroganov.exeptions.FileExtensionError;
import org.stroganov.exeptions.NoSuchSheetException;

import java.io.IOException;

public interface ConversionAction {
    boolean convertAline(String sourceFileName, String sheetSourceName, String resultFileString) throws IOException, FileExtensionError, NoSuchSheetException;
}
