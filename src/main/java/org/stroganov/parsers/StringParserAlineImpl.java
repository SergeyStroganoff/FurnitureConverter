package org.stroganov.parsers;

import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component("stringParserAlineImpl")
public class StringParserAlineImpl implements StringParser {

    public static final String NONE = "none";

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
        StringBuilder modelName = new StringBuilder();
        for (int i = 1; i < firstDash; i++) {
            modelName.append(splitString[i]).append(" ");
        }
        // logic depends on source file format
        preparedStringBuf[0] = splitString[0];
        preparedStringBuf[1] = modelName.toString().trim();

        String[] stringsSeparatedByRegexp = currentString.split("\\S+”W");
        if (stringsSeparatedByRegexp.length == 2) {
            preparedStringBuf[2] = currentString.substring(stringsSeparatedByRegexp[0].length() - 1, currentString.length() - stringsSeparatedByRegexp[1].length()-2);
        } else {
            preparedStringBuf[2] = NONE;
        }

        stringsSeparatedByRegexp = currentString.split("\\S+”H");
        if (stringsSeparatedByRegexp.length == 2) {
            preparedStringBuf[3] = currentString.substring(stringsSeparatedByRegexp[0].length() - 1, currentString.length() - stringsSeparatedByRegexp[1].length()-2);
        } else {
            preparedStringBuf[3] = NONE;
        }

        stringsSeparatedByRegexp = currentString.split("\\S+”D");
        if (stringsSeparatedByRegexp.length == 2) {
            preparedStringBuf[4] = currentString.substring(stringsSeparatedByRegexp[0].length() - 1, currentString.length() - stringsSeparatedByRegexp[1].length()-2);
        } else {
            preparedStringBuf[4] = NONE;
        }

        stringsSeparatedByRegexp = currentString.split("\\$[\\d,]+\\s\\$[\\d,]+\\s\\$[\\d,]+");
        String priceString = currentString.substring(stringsSeparatedByRegexp[0].length());
        if (priceString.length() > 0) {
            stringsSeparatedByRegexp = priceString.split(" ");
            preparedStringBuf[5] = stringsSeparatedByRegexp[0].substring(1).replace(",","").strip();
            preparedStringBuf[6] = stringsSeparatedByRegexp[1].substring(1).replace(",","").strip();
            preparedStringBuf[7] = stringsSeparatedByRegexp[2].substring(1).replace(",","").strip();
        } else {
            preparedStringBuf[5] = NONE;
            preparedStringBuf[6] = NONE;
            preparedStringBuf[7] = NONE;
        }
        Arrays.stream(preparedStringBuf).forEach(x -> System.out.print(x + " "));
        System.out.println();
        return preparedStringBuf;
    }
}

/*
//Alternative solution of parsing can be faster and need to be tested.
Pattern pattern = Pattern.compile("(\\S+”W)\\sx\\s(\\S+”H)\\sx\\s(\\S+”D)");
        Matcher matcher = pattern.matcher(currentString);
        if (matcher.find()) {
            parsedStringArray[2] = matcher.group(1);
            parsedStringArray[3] = matcher.group(2);
            parsedStringArray[4] = matcher.group(3);
        } else {
            parsedStringArray[2] = DEFAULT_VALUE;
            parsedStringArray[3] = DEFAULT_VALUE;
            parsedStringArray[4] = DEFAULT_VALUE;
        }
 */