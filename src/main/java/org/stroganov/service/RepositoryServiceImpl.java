package org.stroganov.service;

import org.springframework.stereotype.Service;
import org.stroganov.entities.CatalogItem;
import org.stroganov.repositoty.CatalogItemDAO;

@Service
public class RepositoryServiceImpl implements RepositoryService {

    private final CatalogItemDAO catalogItemDAO;

    public RepositoryServiceImpl(CatalogItemDAO catalogItemDAO) {
        this.catalogItemDAO = catalogItemDAO;
    }

    @Override
    public int saveOrUpdate(CatalogItem catalogItem) {
        return catalogItemDAO.saveOrUpdate(catalogItem);
    }
}
