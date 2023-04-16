package org.stroganov.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.stroganov.entities.*;
import org.stroganov.repositoty.*;

@Service
public class RepositoryServiceImpl implements RepositoryService {

    private final CatalogItemDAO catalogItemDAO;
    private final ModelDAO modelDAO;
    private final SampleFDAO sampleFDAO;
    private final DimensionDAO dimensionDAO;
    private final ManufactureDAO manufactureDAO;

    private final CatalogItemStyleDAO catalogItemStyleDAO;

    @Autowired
    public RepositoryServiceImpl(CatalogItemDAO catalogItemDAO, ModelDAO modelDAO, SampleFDAO sampleFDAO, DimensionDAO dimensionDAO, ManufactureDAO manufactureDAO, CatalogItemStyleDAO catalogItemStyleDAO) {
        this.catalogItemDAO = catalogItemDAO;
        this.modelDAO = modelDAO;
        this.sampleFDAO = sampleFDAO;
        this.dimensionDAO = dimensionDAO;
        this.manufactureDAO = manufactureDAO;
        this.catalogItemStyleDAO = catalogItemStyleDAO;
    }

    @Override
    public int saveOrUpdate(CatalogItem catalogItem) {
        return catalogItemDAO.saveOrUpdate(catalogItem);
    }

    @Override
    public Model getModelByArticle(String article) {
        return modelDAO.getByArticle(article);
    }

    @Override
    public SampleF getSampleFByArticle(String article) {
        return sampleFDAO.getByArticle(article);
    }

    @Override
    public Dimension getDimensionByWHD(String with, String height, String depth) {
        return dimensionDAO.findDimensionByDimensions(with, height, depth);
    }

    @Override
    public Manufacture getManufactureByName(String name) {
        return manufactureDAO.getByName(name);
    }

    @Override
    public CatalogItemStyle getCatalogItemStyleByArticleAndName(String article, String name) {
        return catalogItemStyleDAO.getByArticleAndName(article, name);
    }
}
