package org.stroganov.exeptions;

public class StringCheckerException extends Exception {

    public StringCheckerException(String message, Throwable cause) {
        super(message, cause);
    }

    public StringCheckerException(Throwable cause) {
        super(cause);
    }

    public StringCheckerException(String exceptionString) {
        super(exceptionString);
    }
}
