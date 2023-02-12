package org.stroganov.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
class ExelHandlerImplTest {
    @InjectMocks
    ExelHandlerImpl exelHandler;
    String userDirectory = Paths.get("")
            .toAbsolutePath()
            .toString();

    @Test
    void saveToFile() {
        // given
        System.out.println(userDirectory);
        String fileName = "test";
        Map<String, String> testMap = new HashMap<>();
        testMap.put("DB3040", "B3040");
        //when
        exelHandler.saveToFile(fileName, testMap);
        //then
        File tempFile = new File(fileName + ".xls");
        boolean exists = tempFile.exists();
        Assertions.assertTrue(exists);
    }
}