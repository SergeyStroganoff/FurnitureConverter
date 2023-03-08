package org.stroganov.actions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.stroganov.exeptions.FileExtensionError;
import org.stroganov.exeptions.NoSuchSheetException;
import org.stroganov.parsers.StringParser;
import org.stroganov.utils.ExelFileReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@Getter
@Setter
@NoArgsConstructor
public class ConversionActionImpl implements ConversionAction {

    private static final Logger LOGGER = LogManager.getLogger(ConversionActionImpl.class);
    @Autowired
    private Environment environment;
    @Autowired
    private ExelFileReader exelFileReader;
    @Autowired
    private StringParser stringParser;


    @Override
    public boolean convertAline(String sourceFileName, String sheetSourceName, String resultFileString) throws IOException, FileExtensionError, NoSuchSheetException {
        String incomingFile = environment.getProperty(sourceFileName);
        String outFile = environment.getProperty(resultFileString);
        String sheetName = environment.getProperty(sheetSourceName);
        List<String> listItemsString = getStringListFromSource(incomingFile, sheetName);
        List<String[]> resultBuffersOfStrings = covertToBufferOfItemFieldValue(listItemsString);

        resultBuffersOfStrings.forEach(x -> {
            for (String s : x) {
                // System.out.println(s);
            }
        }); //todo

        return true;
    }

    private List<String> getStringListFromSource(String sourceFileName, String sheetSourceName) throws IOException, FileExtensionError, NoSuchSheetException {
        Map<Integer, List<Object>> mapFromSource = exelFileReader.readExelTable(sourceFileName, sheetSourceName);
        List<String> listItemsString = new ArrayList<>();
        for (Map.Entry<Integer, List<Object>> entry : mapFromSource.entrySet()) {
            for (Object object : entry.getValue()) {
                listItemsString.add(object.toString());
                LOGGER.info("Object added");
            }
        }
        return listItemsString;
    }

    private List<String[]> covertToBufferOfItemFieldValue(List<String> stringList) {
        List<String[]> convertedStringList = new ArrayList<>(stringList.size());
        for (String currentString : stringList) {
            LOGGER.info(currentString);
          //  System.out.println(currentString);
            convertedStringList.add(stringParser.stringCatalogueParser(currentString));
        }
        return convertedStringList;
    }

}
