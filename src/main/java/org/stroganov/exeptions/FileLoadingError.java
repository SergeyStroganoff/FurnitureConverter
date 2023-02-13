package org.stroganov.exeptions;

public class FileLoadingError extends Exception {
    public FileLoadingError() {
    }

    public FileLoadingError(String message) {
        super(message);
    }

    public FileLoadingError(String message, Throwable cause) {
        super(message, cause);
    }

    public FileLoadingError(Throwable cause) {
        super(cause);
    }
}
