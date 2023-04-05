package org.stroganov.ui;

import java.io.IOException;

public interface UserInterface {

    void showMenuForm();

    UserResponse getFromUser() throws IOException;

    void showMessage(String s);
}
