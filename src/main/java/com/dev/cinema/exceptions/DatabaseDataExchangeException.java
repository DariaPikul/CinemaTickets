package com.dev.cinema.exceptions;

public class DatabaseDataExchangeException extends RuntimeException {
    public DatabaseDataExchangeException(String message, Throwable cause) {
        super(message, cause);
    }

    public DatabaseDataExchangeException(String message) {
        super(message);
    }
}
