package org.stroganov.entity_loader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.stroganov.entities.*;
import org.stroganov.exeptions.FileExtensionError;
import org.stroganov.exeptions.NoSuchSheetException;
import org.stroganov.service.RepositoryService;
import org.stroganov.utils.ExelFileReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 * implementation of loading the value of fields of furniture items from an exel file,
 * deserializing entities and saving them in a list
 * row in exel file: samplf_article | model_article | model_description | dimesion_width | dimension_hieght | dimension_depth | cost1 |cost2 | cost3
 * each row converts to 6 CatalogItems with different Stiles
 */
@Component
public class AlineCatalogEntityLoader implements EntityLoader {
    public static final String CATALOG_ITEM_FROM_EXEL_ROW_IS_NULL = "Input parameter 'objectList' in method createCatalogItemFromExelRow is NULL";
    private static final String MANUFACTURE_DESCRIPTION =
            "Aline International is a kitchen and bath cabinets wholesaler based in Great Chicago area." +
                    "Phone: (708) 478-2471\n" +
                    "Email: info@alineintl.com\n" +
                    "Address: 9100 W 191st St, Mokena, IL 60448, United States";
    private final Manufacture manufactureAline = new Manufacture(0, "Aline", MANUFACTURE_DESCRIPTION);

    private static final Map<String, String> alineCatalogStylesMap
            = Map.of(
            "AC", "Aspen Charcoal Gray",
            "SW", "Shaker White",
            "CS", "Charleston Saddle",
            "CW", "Charleston White",
            "ES", "Shaker Espresso",
            "AW", "Aspen White"
    );
    @Autowired
    private ExelFileReader exelFileReader;

    @Autowired
    private RepositoryService repositoryService;

    @Override
    public List<CatalogItem> loadCatalogItemsFromExelFile(String sourceFileName, String sheetSourceName) throws IOException, FileExtensionError, NoSuchSheetException {
        Map<Integer, List<Object>> mapFromSource = exelFileReader.readExelTable(sourceFileName, sheetSourceName);
        List<CatalogItem> catalogItems = new ArrayList<>();
        for (Map.Entry<Integer, List<Object>> entry : mapFromSource.entrySet()) {
            catalogItems.addAll(createCatalogItemFromExelRow(entry.getValue()));
        }
        return catalogItems;
    }

    private List<CatalogItem> createCatalogItemFromExelRow(List<Object> objectList) {
        Assert.notNull(objectList, CATALOG_ITEM_FROM_EXEL_ROW_IS_NULL);
        List<CatalogItem> catalogItemList = new ArrayList<>();
        Dimension dimension = new Dimension();
        Model model = new Model();
        SampleF sampleF = new SampleF();
        sampleF.setModel(model);
        model.setDimension(dimension);
        String firstValue = objectList.get(0).toString().strip();
        if (firstValue.isEmpty()) {
            return catalogItemList;
        }

        sampleF.setArticle(firstValue);
        model.setArticle(objectList.get(1).toString());
        model.setDescription((String) objectList.get(2));
        dimension.setWidth(objectList.get(3).toString());
        dimension.setHeight(objectList.get(4).toString());
        dimension.setDepth(objectList.get(5).toString());

        CatalogItem catalogItem = new CatalogItem();
        catalogItem.setModel(model);
        catalogItem.setProducer(manufactureAline);
        return catalogItemList;
    }

}

