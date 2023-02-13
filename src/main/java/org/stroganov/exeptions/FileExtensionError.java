package org.stroganov.exeptions;

public class FileExtensionError extends Exception {
    public FileExtensionError() {
        super();
    }

    public FileExtensionError(String message) {
        super(message);
    }

    public FileExtensionError(String message, Throwable cause) {
        super(message, cause);
    }
}
