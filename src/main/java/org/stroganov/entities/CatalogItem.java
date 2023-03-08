package org.stroganov.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CatalogItem {
    private CommonModel commonModel;
    private String colorStyle;
    private double priceIn;
    private double priceOut;
}
