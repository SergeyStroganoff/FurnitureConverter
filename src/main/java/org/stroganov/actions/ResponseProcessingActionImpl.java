package org.stroganov.actions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.stroganov.exeptions.FileExtensionError;
import org.stroganov.exeptions.NoSuchSheetException;
import org.stroganov.ui.UserResponse;
import org.stroganov.utils.ResultHandler;

import java.io.IOException;
import java.util.List;


@Component
public class ResponseProcessingActionImpl implements ResponseProcessingAction {

    @Value("${output.file}")
    private String alineOutFile;

    @Value("${prepared.file}")
    private String alinePreparedFile;

    @Value("${source.file}")
    private String alineSourceFile;

    @Value("${sheet}")
    private String sheetName;

    public static final String CONVERSION_SUCCSESS_MESSAGE = "Conversion was successful. Result saved in file: ";
    public static final String SAVE_INTO_DB_SUCCSESS_MESSAGE = "Saving into DB was successful. Saved: ";
    public static final String WRONG_INPUT_MESSAGE = "Wrong input, try again";
    @Autowired
    private ConversionAction conversionAction;
    @Autowired
    private ResultHandler resultHandler;

    @Autowired
    private CatalogItemSaveAction catalogItemSaveAction;

    @Autowired
    Environment environment;

    @Override
    public String handle(UserResponse userResponse) {
        String resultMessage = "";
        switch (userResponse.getValue()) {
            case "1": {
                resultMessage = alineConversation();
                break;
            }
            case "2": {
                resultMessage = saveAlineCatalogItemsToDB();
                break;
            }
            case "3": {
                System.exit(0);
            }
            default: {
                resultMessage = WRONG_INPUT_MESSAGE;
            }
        }
        return resultMessage;
    }

    private String alineConversation() {
        try {
            List<String[]> listBufferedStrings = conversionAction.convertAline(alineSourceFile, sheetName);
            resultHandler.saveToFile(alineOutFile, listBufferedStrings, sheetName);
        } catch (IOException | FileExtensionError | NoSuchSheetException e) {
            return e.getMessage();
        }
        return CONVERSION_SUCCSESS_MESSAGE + alineOutFile;
    }

    private String saveAlineCatalogItemsToDB() {
        int entitiesSavedCount = catalogItemSaveAction.saveAlineItemsToDB(alinePreparedFile, sheetName);
        return SAVE_INTO_DB_SUCCSESS_MESSAGE + entitiesSavedCount;
    }
}
