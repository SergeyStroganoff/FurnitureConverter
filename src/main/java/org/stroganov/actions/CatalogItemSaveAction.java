package org.stroganov.actions;

public interface CatalogItemSaveAction {
    //read file
    //create entity
    //save entity

    /**
    Method saves furniture items into MySQL DB
     */
    int saveAlineItemsToDB(String sourceFileName, String sheetSourceName);
}
