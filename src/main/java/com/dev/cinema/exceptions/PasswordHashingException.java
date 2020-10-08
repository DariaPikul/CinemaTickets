package com.dev.cinema.exceptions;

import java.security.NoSuchAlgorithmException;

public class PasswordHashingException extends RuntimeException {
    public PasswordHashingException(String message,
                                    NoSuchAlgorithmException exception) {
        super(message, exception);
    }
}
