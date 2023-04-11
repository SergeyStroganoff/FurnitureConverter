package org.stroganov.utils;

import org.stroganov.exeptions.StringCheckerException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringChecker implements IStringCheck {

    public static final String BAD_FORMAT_DIRECTORY_PATH_MESSAGE = "Недопустимый формат пути к директории";
    public static final String BAD_FORMAT_FILE_NAME_MESSAGE = "Недопустимый формат имени файла";


    public boolean isStringDirectoryPath(String directoryPath) throws StringCheckerException {
        String regexp = "[A-Z]:\\\\[^&<>|#:;*?^\\/]*";
        return isStringValidPattern(directoryPath, regexp, BAD_FORMAT_DIRECTORY_PATH_MESSAGE);
    }

    public boolean isStringValidPartFileName(String fileNamePart) throws StringCheckerException {
        String regexp = "[^&<>|#:;*?^\\/]*";
        return isStringValidPattern(fileNamePart, regexp, BAD_FORMAT_FILE_NAME_MESSAGE);
    }

    private boolean isStringValidPattern(String string, String regexp, String exceptionString) throws StringCheckerException {
        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(string);
        if (!matcher.matches()) {
            throw new StringCheckerException(exceptionString);
        }
        return true;
    }
}
