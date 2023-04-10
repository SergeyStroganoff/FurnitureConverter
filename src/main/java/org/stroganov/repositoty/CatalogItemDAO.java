package org.stroganov.repositoty;

import org.stroganov.entities.CatalogItem;

public interface CatalogItemDAO {
    int saveOrUpdate(CatalogItem catalogItem);
}
