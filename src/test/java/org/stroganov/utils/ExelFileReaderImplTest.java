package org.stroganov.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.stroganov.exeptions.FileExtensionError;
import org.stroganov.exeptions.NoSuchSheetException;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
class ExelFileReaderImplTest {

    @InjectMocks
    ExelFileReaderImpl exelFileReader;

    @Test
    void readExelTable() throws IOException, FileExtensionError, NoSuchSheetException {
        //given
        String fileName = "test.xls";
        String sheetName = "Result";
        //when
        Map<Integer, List<Object>> exelTable = exelFileReader.readExelTable(fileName, sheetName);
        //then
        Assertions.assertNotNull(exelTable);
    }
}