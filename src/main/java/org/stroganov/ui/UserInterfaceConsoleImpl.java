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
    public static final String PRINTED_MESSAGE = "Printed message: ";

    private void print(String s) {
        System.out.println(s);
        LOGGER.debug(PRINTED_MESSAGE + s);
    }

    @Override
    public void showMenuForm() {
        print("Input action number:");
        print("- parsing list of Aline furniture - 1");
        print("- enter Aline furniture into database - 2");
        print("- for exit - 3");
    }

    @Override
    public UserResponse getFromUser() throws IOException {
        UserResponse userResponse = new UserResponse("");
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            userResponse.setValue(bufferedReader.readLine());
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
            throw e;
        }
        return userResponse;
    }

    @Override
    public void showMessage(String s) {
        print(s);
    }
}
