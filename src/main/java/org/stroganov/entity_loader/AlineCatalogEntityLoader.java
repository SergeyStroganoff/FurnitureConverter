package org.stroganov.entity_loader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.stroganov.entities.*;
import org.stroganov.exeptions.FileExtensionError;
import org.stroganov.exeptions.NoSuchSheetException;
import org.stroganov.utils.ExelFileReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AlineCatalogEntityLoader implements EntityLoader {
    public static final String CATALOG_ITEM_FROM_EXEL_ROW_IS_NULL = "Input parameter 'objectList' in method createCatalogItemFromExelRow is NULL";
    /**
     * implementation of loading the value of fields of furniture items from an exel file,
     * deserializing entities and saving them in a list
     */

    private static final String DESCRIPTION =
            "Aline International is a kitchen and bath cabinets wholesaler based in Great Chicago area." +
            "Phone: (708) 478-2471\n" +
            "Email: info@alineintl.com\n" +
            "Address: 9100 W 191st St, Mokena, IL 60448, United States";

    private final Manufacture manufacture = new Manufacture(0,"Aline", DESCRIPTION);

    private final HashMap alineStyleMap = new HashMap<>(); //todo
    @Autowired
    private ExelFileReader exelFileReader;

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
        Size size = new Size();
        Model model = new Model();
        SampleF sampleF = new SampleF();
        sampleF.setModel(model);
        model.setSize(size);
        String firstValue = objectList.get(0).toString().strip();
        if (firstValue.isEmpty()) {
            return catalogItemList;
        }
        sampleF.setArticle(firstValue);
        model.setArticle(objectList.get(1).toString());
        model.setDescription((String)objectList.get(2));
        size.setWith(objectList.get(3).toString());
        size.setHigh(objectList.get(4).toString());
        size.setDepth(objectList.get(5).toString());

        CatalogItem catalogItem = new CatalogItem();
        catalogItem.setModel(model);
        catalogItem.setProducer(manufacture);
        return catalogItemList;
    }
}

