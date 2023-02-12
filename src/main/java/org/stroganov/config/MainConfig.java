package org.stroganov.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.stroganov.exeptions.FileLoadingError;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;


public class MainConfig {
    private static final String CONFIG_NAME = "app.properties";
    private static final String ERRORMESSAGE = "\"Error loading property file\" + CONFIG_NAME";
    private static Properties mainProperty;
    private static final Logger LOGGER = LogManager.getLogger(MainConfig.class);

    private static void initProperty() throws IOException {
        try (InputStream in = Files.newInputStream(Paths.get(CONFIG_NAME))) {
            mainProperty.load(in);
        }
    }

    public static String getProperty(String property) throws FileLoadingError {
        if (mainProperty == null) {
            mainProperty = new Properties();
            try {
                initProperty();
            } catch (IOException e) {
                LOGGER.error(ERRORMESSAGE, e);
                throw new FileLoadingError(ERRORMESSAGE, e);
            }
        }
        return mainProperty.getProperty(property);
    }
}
