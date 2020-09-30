package com.dev.cinema.exceptions;

public class SessionFactoryCreationErrorException extends RuntimeException {
    public SessionFactoryCreationErrorException(String message, Throwable cause) {
        super(message, cause);
    }
}
