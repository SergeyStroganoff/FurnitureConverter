package org.stroganov.actions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
    private String sourceFileName;
    private String sheetSourceName;
    @Autowired
    private ExelFileReader exelFileReader;
    @Autowired
    @Qualifier("stringParserAlineImpl")
    private StringParser stringParser;

    @Override
    public List<String[]> convertAline(String sourceFileName, String sheetSourceName) throws IOException, FileExtensionError, NoSuchSheetException {
        List<String> listItemsString = getStringListFromSource(sourceFileName, sheetSourceName);
        return covertToBufferOfItemFieldValue(listItemsString);
    }

    private List<String> getStringListFromSource(String sourceFileName, String sheetSourceName) throws IOException, FileExtensionError, NoSuchSheetException {
        Map<Integer, List<Object>> mapFromSource = exelFileReader.readExelTable(sourceFileName, sheetSourceName);
        List<String> listItemsString = new ArrayList<>();
        for (Map.Entry<Integer, List<Object>> entry : mapFromSource.entrySet()) {
            for (Object object : entry.getValue()) {
                listItemsString.add(object.toString());
                LOGGER.debug(String.format("Object added: %s", object));
            }
        }
        return listItemsString;
    }

    private List<String[]> covertToBufferOfItemFieldValue(List<String> stringList) {
        List<String[]> convertedStringList = new ArrayList<>(stringList.size());
        for (String currentString : stringList) {
            LOGGER.debug(currentString);
            convertedStringList.add(stringParser.parseString(currentString));
        }
        return convertedStringList;
    }
}
