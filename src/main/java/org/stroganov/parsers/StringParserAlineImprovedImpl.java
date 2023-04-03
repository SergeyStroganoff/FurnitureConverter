package org.stroganov.parsers;

import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component("stringParserAlineImprovedImpl")
public class StringParserAlineImprovedImpl implements StringParser {
    private static final String DEFAULT_VALUE = "0";

    @Override
    public String[] parseString(String currentString) {
        String[] parsedStringArray = new String[8];
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

        parsedStringArray[0] = splitString[0];
        parsedStringArray[1] = modelName.toString().trim();

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

        pattern = Pattern.compile("\\$(\\d+(?:,\\d+)?)\\s*\\$\\s*(\\d+(?:,\\d+)?)\\s*\\$\\s*(\\d+(?:,\\d+)?)");
        matcher = pattern.matcher(currentString);
        if (matcher.find()) {
            parsedStringArray[5] = matcher.group(1).replaceAll(",", "");
            parsedStringArray[6] = matcher.group(2).replaceAll(",", "");
            parsedStringArray[7] = matcher.group(3).replaceAll(",", "");
        } else {
            parsedStringArray[5] = DEFAULT_VALUE;
            parsedStringArray[6] = DEFAULT_VALUE;
            parsedStringArray[7] = DEFAULT_VALUE;
        }

        return parsedStringArray;
    }
}
