package com.dev.cinema.exceptions;

public class SessionFactoryCreationException extends RuntimeException {
    public SessionFactoryCreationException(String message, Throwable cause) {
        super(message, cause);
    }
}
