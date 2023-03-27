package org.stroganov;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;
import org.stroganov.actions.ConversionAction;
import org.stroganov.actions.ConversionActionImpl;
import org.stroganov.configuration.ConfigurationSpring;
import org.stroganov.exeptions.FileExtensionError;
import org.stroganov.exeptions.NoSuchSheetException;
import org.stroganov.utils.ExelHandlerImpl;
import org.stroganov.utils.ResultHandler;

import java.io.IOException;
import java.util.List;

/**
 * Furniture  converter.
 * It takes the general designations of furniture from the input file
 * and converts them into manufacturer's articles according to the selected brand.
 */
public class App {

    public static final String OUTPUT_FILE = "output.file";
    public static final String SOURCE_FILE = "source.file";
    public static final String SHEET = "sheet";

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ConfigurationSpring.class);
        ConversionAction conversionAction = context.getBean(ConversionActionImpl.class);
        ResultHandler resultHandler = context.getBean(ExelHandlerImpl.class);
        Environment environment = context.getEnvironment();
        String outFile = environment.getProperty(OUTPUT_FILE);
        try {
            List<String[]> listBufferedStrings = conversionAction.convertAline(SOURCE_FILE, SHEET);
            resultHandler.saveToFile(outFile, listBufferedStrings);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (FileExtensionError e) {
            System.out.println(e.getMessage());
        } catch (NoSuchSheetException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Conversion successfully");
    }
}
