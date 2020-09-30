package com.dev.cinema.exceptions;

public class DatabaseDataExchangeErrorException extends RuntimeException {
    public DatabaseDataExchangeErrorException(String message, Throwable cause) {
        super(message, cause);
    }
}
