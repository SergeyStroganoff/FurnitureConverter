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
    private String outFile;

    @Value("${source.file}")
    private String sourceFile;

    @Value("${sheet}")
    private String sheet;

    public static final String SUCSESS_MESSAGE = "Conversion was successful. Result saved in file: ";
    public static final String WRONG_INPUT_MESSAGE = "Wrong input, try again";
    @Autowired
    private ConversionAction conversionAction;
    @Autowired
    private ResultHandler resultHandler;

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
            List<String[]> listBufferedStrings = conversionAction.convertAline(sourceFile, sheet);
            resultHandler.saveToFile(outFile, listBufferedStrings);
        } catch (IOException | FileExtensionError | NoSuchSheetException e) {
            return e.getMessage();
        }
        return SUCSESS_MESSAGE + outFile;
    }
}
