package com.vev.exemplo.bancodados;

public class DatabaseAccessException extends Exception {
    public DatabaseAccessException() {
        super();
    }
    public DatabaseAccessException(String message) {
        super(message);
    }
    public DatabaseAccessException(String message, Throwable cause) {
        super(message, cause);
    }
    public DatabaseAccessException(Throwable cause) {
        super(cause);
    }
}
