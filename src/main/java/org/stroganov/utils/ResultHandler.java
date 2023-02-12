package org.stroganov.utils;

import java.util.Map;

public interface ResultHandler {
    void saveToFile(String filePathName, Map<String, String> table);
}
