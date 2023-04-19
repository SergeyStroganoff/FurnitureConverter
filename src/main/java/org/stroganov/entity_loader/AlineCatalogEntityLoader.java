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
import java.util.*;

/**
 * implementation of loading the value of fields of furniture items from an exel file,
 * deserializing entities and saving them in a list
 * row in exel file: samplf_article | model_article | model_description | dimension_width | dimension_height | dimension_depth | cost1 |cost2 | cost3
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
    public static final String SPLITERATOR = "-";
    public static final String EMPTY_SIGN = "-";
    private final Manufacture manufactureAline = new Manufacture(0, "Aline", MANUFACTURE_DESCRIPTION);

    //key - article and style name, value - price position in exel file
    private static final Map<String, Integer> alineCatalogStylesMap
            = Map.of(
            "AC-Aspen Charcoal Gray", 8,
            "SW-Shaker White", 7,
            "CS-Charleston Saddle", 6,
            "CW-Charleston White", 6,
            "ES-Shaker Espresso", 8,
            "AW-Aspen White", 7,
            "GR-Shaker Gray", 8
    );

    private final Set<SampleF> sampleFList = new HashSet<>();
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
        String firstValue = objectList.get(0).toString().strip();
        if (firstValue.equals(EMPTY_SIGN)) {
            return catalogItemList;
        }
        // todo //todelete
        //objectList.forEach(System.out::println);
        //
        Dimension dimension = repositoryService.getOrCreateDimension(objectList.get(3).toString(),
                objectList.get(4).toString(),
                objectList.get(5).toString());
        Model model = repositoryService.getOrCreateModel(objectList.get(1).toString(), objectList.get(2).toString(), dimension);
        SampleF sampleF = repositoryService.getOrCreateSampleF(firstValue, model);
        sampleFList.add(sampleF);
        Manufacture manufacture = repositoryService.getOrCreateManufacture(manufactureAline);
        for (Map.Entry<String, Integer> entry : alineCatalogStylesMap.entrySet()) {
            String[] articleAndStyleNameBuf = entry.getKey().split(SPLITERATOR);
            Assert.isTrue(articleAndStyleNameBuf.length == 2, "article And StyleName must be as key in HAsMAp and separated by" + SPLITERATOR);
            CatalogItemStyle catalogItemStyle = repositoryService.getOrCreateCatalogItemStyle(articleAndStyleNameBuf[0], articleAndStyleNameBuf[1]);
            double price = Double.parseDouble(objectList.get(entry.getValue()).toString().strip());
            if (price == 0) {
                continue;
            }
            CatalogItem catalogItem = new CatalogItem(0, model, manufacture, catalogItemStyle, price);
            catalogItemList.add(catalogItem);
        }
        //row in exel file: samplf_article | model_article | model_description | dimension_width | dimension_height | dimension_depth | cost1 |cost2 | cost3
        return catalogItemList;
    }

    @Override
    public Set<SampleF> getSampleFList() {
        return sampleFList;
    }
}

