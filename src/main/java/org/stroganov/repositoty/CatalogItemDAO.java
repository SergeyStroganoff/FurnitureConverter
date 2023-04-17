package org.stroganov.repositoty;

import org.stroganov.entities.CatalogItem;

public interface CatalogItemDAO {
    int save(CatalogItem catalogItem);
}
