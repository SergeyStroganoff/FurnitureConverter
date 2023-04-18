package org.stroganov.repositoty;

import org.stroganov.entities.CatalogItem;

import javax.transaction.Transactional;
import java.util.List;

public interface CatalogItemDAO {
    int save(CatalogItem catalogItem);

    @Transactional
    void saveAll(List<CatalogItem> catalogItemList);
}
