package org.stroganov;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.stroganov.actions.UserConversationAction;
import org.stroganov.configuration.ConfigurationSpring;

/**
 * Furniture  converter -
 * utility to convert and list furniture to store in database.
 */
public class App {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ConfigurationSpring.class);
        UserConversationAction userConversationAction = context.getBean(UserConversationAction.class);
        userConversationAction.startConversation();
    }
}
