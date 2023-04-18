package org.stroganov.actions;


import lombok.NoArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.stroganov.entities.CatalogItem;
import org.stroganov.entities.SampleF;
import org.stroganov.entity_loader.EntityLoader;
import org.stroganov.exeptions.FileExtensionError;
import org.stroganov.exeptions.NoSuchSheetException;
import org.stroganov.service.RepositoryService;

import java.io.IOException;
import java.util.List;

@Component
@NoArgsConstructor
public class CatalogItemSaveActionImpl implements CatalogItemSaveAction {
    public static final Logger LOGGER = LogManager.getLogger(CatalogItemSaveActionImpl.class);
    public static final String SHEET_IN_EXEL_FILE_WAS_T_FOUND = "Sheet in exelFile wasn't found";
    private EntityLoader entityLoader;
    private RepositoryService repositoryService;

    @Autowired
    public CatalogItemSaveActionImpl(EntityLoader entityLoader, RepositoryService repositoryService) {
        this.entityLoader = entityLoader;
        this.repositoryService = repositoryService;
    }

    @Override
    public int saveAlineItemsToDB(String sourceFileName, String sheetSourceName) {
        int savedEntitiesCount = 0;
        List<CatalogItem> catalogItemList;
        List<SampleF> sampleFList;
        try {
            catalogItemList = entityLoader.loadCatalogItemsFromExelFile(sourceFileName, sheetSourceName);
            sampleFList = entityLoader.getSampleFList();
        } catch (IOException | FileExtensionError e) {
            LOGGER.error(e);
            return savedEntitiesCount - 1;
        } catch (NoSuchSheetException e) {
            LOGGER.error(SHEET_IN_EXEL_FILE_WAS_T_FOUND, e);
            return savedEntitiesCount - 1;
        }
        repositoryService.saveAllCatalogItem(catalogItemList);
        savedEntitiesCount = catalogItemList.size();

        for (SampleF sampleF : sampleFList) {
            repositoryService.saveSampleF(sampleF);
        }
        return savedEntitiesCount;
    }
}
