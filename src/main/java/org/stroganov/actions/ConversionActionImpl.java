package org.stroganov.actions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.stroganov.exeptions.FileExtensionError;
import org.stroganov.exeptions.NoSuchSheetException;
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
    @Autowired
    Environment environment;
    @Autowired
    ExelFileReader exelFileReader;

    @Override
    public boolean convertAline(String sourceFileName, String sheetSourceName, String resultFileString) throws IOException, FileExtensionError, NoSuchSheetException {
        String incomingFile = environment.getProperty(sourceFileName);
        String outFile = environment.getProperty(resultFileString);
        String sheetName = environment.getProperty(sheetSourceName);
        List<String> listItemsString = getStringListFromSource(incomingFile,sheetName);

        return true;
    }

    private List<String> getStringListFromSource(String sourceFileName, String sheetSourceName) throws IOException, FileExtensionError, NoSuchSheetException {
        Map<Integer, List<Object>> mapFromSource = exelFileReader.readExelTable(sourceFileName, sheetSourceName);
        List<String>  listItemsString  = new ArrayList<>();
        for (Map.Entry<Integer, List<Object>> entry : mapFromSource.entrySet()) {
            for (Object object : entry.getValue()) {
                System.out.println(object);
                listItemsString.add(object.toString());
            }
        }
        return listItemsString;
    }

    private String [] covertToBuffer(List<String> stringList){

        String[] strings = new String[]
    }
}
