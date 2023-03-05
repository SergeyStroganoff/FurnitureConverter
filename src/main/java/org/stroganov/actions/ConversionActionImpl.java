package org.stroganov.actions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.stroganov.utils.ExelFileReader;

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
    public boolean convertAline(String sourceFileName, String sheetSourceName, String resultFileString) {
        String incomingFile = environment.getProperty(sourceFileName);
        String outFile = environment.getProperty(resultFileString);
        String sheetName = environment.getProperty(sheetSourceName);
        Map<String, List<Object>> mapFromSource = exelFileReader.readExelTable(incomingFile, sheetName);


        return true;
    }
}
