package org.stroganov.actions;

import org.stroganov.ui.UserResponse;

@FunctionalInterface
public interface ResponseProcessingAction {
    String handle(UserResponse userResponse);
}
