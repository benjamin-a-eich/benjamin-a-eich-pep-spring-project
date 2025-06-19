package com.example.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.exception.DuplicateUsernameException;
import com.example.exception.InvalidLoginExpection;
import com.example.exception.InvalidRegistrationExpection;
import com.example.exception.InvalidUserException;

@RestControllerAdvice
public class ExceptionAndErrorController {
    @ExceptionHandler(DuplicateUsernameException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String duplicateUsername(DuplicateUsernameException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(InvalidLoginExpection.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String unauthorizedUser(InvalidLoginExpection ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(InvalidRegistrationExpection.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String invalidRegistration(InvalidRegistrationExpection ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(InvalidUserException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String invalidUser(InvalidLoginExpection ex) {
        return ex.getMessage();
    }
}
