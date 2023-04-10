package org.stroganov.service;

import org.stroganov.entities.CatalogItem;

public interface RepositoryService {
    int saveOrUpdate(CatalogItem catalogItem);
}
