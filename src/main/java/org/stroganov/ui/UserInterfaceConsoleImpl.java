package org.stroganov.ui;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Component
public class UserInterfaceConsoleImpl implements UserInterface {

    private static final Logger LOGGER = LogManager.getLogger(UserInterfaceConsoleImpl.class);

    private void print(String s) {
        System.out.println(s);
    }

    @Override
    public void showMenuForm() {
        print("Input action number:");
        print("- parsing list of Aline furniture - 1");
        print("- enter Aline furniture into database - 2");
    }

    @Override
    public UserResponse getFromUser() {
        UserResponse userResponse = new UserResponse("");
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            userResponse.setValue(bufferedReader.readLine().strip());
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
            userResponse.setValue(e.getMessage());
        }
        return userResponse;
    }

    @Override
    public void showMessage(String s) {
        print(s);
    }
}
