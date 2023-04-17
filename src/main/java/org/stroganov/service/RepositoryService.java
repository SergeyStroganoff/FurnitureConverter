package org.stroganov.service;

import org.stroganov.entities.*;

public interface RepositoryService {
    int saveCatalogItem(CatalogItem catalogItem);
    int saveSampleF(SampleF sampleF);
    SampleF getSampleFByArticle(String article);
    Model getModelByArticle(String article);
    Dimension getDimensionByWHD(String with, String height, String depth);
    Manufacture getManufactureByName(String name);
    CatalogItemStyle getCatalogItemStyleByArticleAndName(String article, String name);

    Dimension getOrCreateDimension(String width, String height, String depth);

    Model getOrCreateModel(String article, String description, Dimension dimension);

    SampleF getOrCreateSampleF(String article, Model model);

    Manufacture getOrCreateManufacture(Manufacture manufactureEntity);

    CatalogItemStyle getOrCreateCatalogItemStyle(String article, String name);
}
