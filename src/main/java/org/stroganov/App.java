package org.stroganov;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.stroganov.actions.ConversionAction;
import org.stroganov.actions.ConversionActionImpl;
import org.stroganov.configuration.ConfigurationSpring;

/**
 * Furniture  converter.
 * It takes the general designations of furniture from the input file
 * and converts them into manufacturer's articles according to the selected brand.
 */
public class App {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ConfigurationSpring.class);
        ConversionAction conversionAction = context.getBean(ConversionActionImpl.class);
        conversionAction.convertAline("source.file", "output.file", "sheet");
    }
}
