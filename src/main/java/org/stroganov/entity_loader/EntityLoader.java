package org.stroganov.entity_loader;

import org.stroganov.entities.CatalogItem;
import org.stroganov.exeptions.FileExtensionError;
import org.stroganov.exeptions.NoSuchSheetException;

import java.io.IOException;
import java.util.List;

public interface EntityLoader {
    List<CatalogItem> loadCatalogItemsFromExelFile(String sourceFileName, String sheetSourceName) throws IOException, FileExtensionError, NoSuchSheetException;
}
