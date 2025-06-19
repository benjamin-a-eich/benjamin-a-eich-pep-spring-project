package com.example.exception;

import javax.naming.AuthenticationException;

public class InvalidLoginExpection extends AuthenticationException {
    public InvalidLoginExpection(String message) {
        super(message);
    }
}
