package org.stroganov.service;

import org.stroganov.entities.*;

public interface RepositoryService {
    int saveOrUpdate(CatalogItem catalogItem);

    SampleF getSampleFByArticle(String article);

    Model getModelByArticle(String article);

    Size getSizeByWHD(String with, String height, String depth);

    Manufacture getManufactureByName(String name);
    CatalogItemStyle getCatalogItemStyleByArticleAndName(String article, String name);
}
