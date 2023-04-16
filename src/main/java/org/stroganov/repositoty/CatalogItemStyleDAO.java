package org.stroganov.repositoty;

import org.stroganov.entities.CatalogItemStyle;

public interface CatalogItemStyleDAO {
    CatalogItemStyle getByArticleAndName(String article, String name);
}
