package org.stroganov;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.stroganov.actions.ConversionAction;
import org.stroganov.actions.ConversionActionImpl;
import org.stroganov.configuration.ConfigurationSpring;
import org.stroganov.exeptions.FileExtensionError;
import org.stroganov.exeptions.NoSuchSheetException;

import java.io.IOException;

/**
 * Furniture  converter.
 * It takes the general designations of furniture from the input file
 * and converts them into manufacturer's articles according to the selected brand.
 */
public class App {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ConfigurationSpring.class);
        ConversionAction conversionAction = context.getBean(ConversionActionImpl.class);
        try {
            conversionAction.convertAline("source.file", "sheet","output.file");
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
