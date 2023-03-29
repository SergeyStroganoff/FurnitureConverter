package org.stroganov.parsers;

import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component("stringParserAlineAlternativeImpl")
public class StringParserAlineAlternativeImpl implements StringParser {
    @Override
    public String[] stringCatalogueParser(String currentString) {
        String[] preparedStringBuf = new String[8];
        String[] splitString = currentString.split(" ");
        int firstDash = 0;
        for (int i = 1; i < splitString.length; i++) {
            firstDash = i;
            if (splitString[i].equals("-")) {
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

        String[] stringsSeparatedByRegexp = currentString.split("\\S+”W");
        if (stringsSeparatedByRegexp.length == 2) {
            preparedStringBuf[2] = currentString.substring(stringsSeparatedByRegexp[0].length() - 1, currentString.length() - stringsSeparatedByRegexp[1].length());
        } else {
            preparedStringBuf[2] = "0";
        }

        stringsSeparatedByRegexp = currentString.split("\\S+”H");
        if (stringsSeparatedByRegexp.length == 2) {
            preparedStringBuf[3] = currentString.substring(stringsSeparatedByRegexp[0].length() - 1, currentString.length() - stringsSeparatedByRegexp[1].length());
        } else {
            preparedStringBuf[3] = "0";
        }

        stringsSeparatedByRegexp = currentString.split("\\S+”D");
        if (stringsSeparatedByRegexp.length == 2) {
            preparedStringBuf[4] = currentString.substring(stringsSeparatedByRegexp[0].length() - 1, currentString.length() - stringsSeparatedByRegexp[1].length());
        } else {
            preparedStringBuf[4] = "0";
        }

        stringsSeparatedByRegexp = currentString.split("\\$[\\d,]+\\s\\$[\\d,]+\\s\\$[\\d,]+");
        String priceString = currentString.substring(stringsSeparatedByRegexp[0].length());
        if (priceString.length() > 0) {
            stringsSeparatedByRegexp = priceString.split(" ");
            preparedStringBuf[5] = stringsSeparatedByRegexp[0];
            preparedStringBuf[6] = stringsSeparatedByRegexp[1];
            preparedStringBuf[7] = stringsSeparatedByRegexp[2];
        } else {
            preparedStringBuf[5] = "zerro";
            preparedStringBuf[6] = "zerro";
            preparedStringBuf[7] = "zerro";
        }
        Arrays.stream(preparedStringBuf).forEach(x -> System.out.print(x + " "));
        System.out.println();
        return preparedStringBuf;
    }
}
