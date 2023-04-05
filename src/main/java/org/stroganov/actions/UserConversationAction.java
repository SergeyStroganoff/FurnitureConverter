package org.stroganov.actions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.stroganov.ui.UserInterface;
import org.stroganov.ui.UserResponse;

import java.io.IOException;

@Component
public class UserConversationAction {
    @Autowired
    UserInterface userInterface;
    @Autowired
    ResponseProcessingAction responseProcessingAction;

    public void startConversation() {
        userInterface.showMenuForm();
        try {
            UserResponse userResponse = userInterface.getFromUser();
            String resultMessage = responseProcessingAction.handle(userResponse);
            userInterface.showMessage(resultMessage);
        } catch (IOException e) {
            userInterface.showMessage(e.getMessage());
            System.exit(0);
        }
    }
}
