package org.stroganov.utils;

import java.util.List;
import java.util.Map;

public interface ResultHandler {
    void saveToFile(String filePathName, Map<String, String> table);
    void saveToFile(String filePathName, List<String[]> list);
}
