package org.stroganov.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.stroganov.entities.*;
import org.stroganov.repositoty.*;

@Service
public class RepositoryServiceImpl implements RepositoryService {

    public final CatalogItemDAO catalogItemDAO;
    public final ModelDAO modelDAO;
    public final SampleFDAO sampleFDAO;
    public final DimensionDAO dimensionDAO;
    public final ManufactureDAO manufactureDAO;
    public final CatalogItemStyleDAO catalogItemStyleDAO;

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
    public int saveCatalogItem(CatalogItem catalogItem) {
        return catalogItemDAO.save(catalogItem);
    }

    @Override
    public int saveSampleF(SampleF sampleF) {
        return sampleFDAO.save(sampleF);
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

    @Override
    public Dimension getOrCreateDimension(String width, String height, String depth) {
        Dimension dimension = getDimensionByWHD(width, height, depth);
        if (dimension == null) {
            dimension = new Dimension(0, width, height, depth);
        }
        return dimension;
    }

    @Override
    public Model getOrCreateModel(String article, String description, Dimension dimension) {
        Model model = getModelByArticle(article);
        if (model == null) {
            model = new Model(0, article, description, dimension);
        }
        return model;
    }

    @Override
    public SampleF getOrCreateSampleF(String article, Model model) {
        SampleF sampleF = getSampleFByArticle(article);
        if (sampleF == null) {
            sampleF = new SampleF(0, article, model);
        }
        return sampleF;
    }

    @Override
    public Manufacture getOrCreateManufacture(Manufacture manufactureEntity) {
        Manufacture manufacture = getManufactureByName(manufactureEntity.getName());
        return manufacture == null ? manufactureEntity : manufacture;
    }

    @Override
    public CatalogItemStyle getOrCreateCatalogItemStyle(String article, String name) {
        CatalogItemStyle catalogItemStyle = getCatalogItemStyleByArticleAndName(article, name);
        if (catalogItemStyle == null) {
            catalogItemStyle = new CatalogItemStyle(0, article, name);
        }
        return catalogItemStyle;
    }
}
