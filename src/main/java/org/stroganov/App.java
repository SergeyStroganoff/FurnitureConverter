package org.stroganov;

import org.stroganov.config.MainConfig;
import org.stroganov.exeptions.FileLoadingError;

/**
 * Furniture  converter.
 * It takes the general designations of furniture from the input file
 * and converts them into manufacturer's articles according to the selected brand.
 */
public class App {
    public static void main(String[] args) throws FileLoadingError {
        String sourceFile = MainConfig.getProperty("source.file");
        System.out.println(sourceFile);
    }
}
