package com.example.exception;

public class InvalidRegistrationExpection extends RuntimeException{
    public InvalidRegistrationExpection(String message) {
        super(message);
    }
}
