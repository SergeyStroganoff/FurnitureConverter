package org.stroganov.parsers;

import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component("stringParserAlineImpl")
public class StringParserAlineImpl implements StringParser {
    @Override
    public String[] parseString(String currentString) {
        String[] preparedStringBuf = new String[8];
        String[] splitString = currentString.split(" ");
        int firstDash = 0;
        for (int i = 1; i < splitString.length; i++) {
            firstDash = i;
            if (splitString[i].equals("-")) {
                break;
            }
        }
        int firstDollarSign = 0;
        for (int i = 1; i < splitString.length; i++) {
            if (splitString[i].charAt(0) == '$') {
                firstDollarSign = i;
                break;
            }
        }
        StringBuilder modelName = new StringBuilder();
        for (int i = 1; i < firstDash; i++) {
            modelName.append(splitString[i]).append(" ");
        }
        // logic depends on source file format
        preparedStringBuf[0] = splitString[0];
        preparedStringBuf[1] = modelName.toString().trim();
        preparedStringBuf[2] = splitString[firstDash + 1].substring(0, splitString[firstDash + 1].length()-2);
        preparedStringBuf[3] = splitString[firstDash + 3].substring(0, splitString[firstDash + 3].length()-2);
        preparedStringBuf[4] = splitString[firstDash + 5].substring(0, splitString[firstDash + 5].length()-2);
        preparedStringBuf[5] = splitString[firstDollarSign].substring(1);
        preparedStringBuf[6] = splitString[firstDollarSign + 1].substring(1);
        preparedStringBuf[7] = splitString[firstDollarSign + 2].substring(1);

        Arrays.stream(preparedStringBuf).forEach(x -> System.out.print(x + " "));
        System.out.println();
        return preparedStringBuf;
    }
}
