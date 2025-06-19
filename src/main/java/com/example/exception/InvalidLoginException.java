package com.example.exception;

import javax.naming.AuthenticationException;

public class InvalidLoginException extends AuthenticationException {
    public InvalidLoginException(String message) {
        super(message);
    }
}
