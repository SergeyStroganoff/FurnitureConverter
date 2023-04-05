package org.stroganov.actions;

import org.stroganov.ui.UserResponse;

@FunctionalInterface
public interface ResponseProcessingAction {
    void handle(UserResponse userResponse);
}
