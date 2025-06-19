package com.example.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.exception.DuplicateUsernameException;
import com.example.exception.InvalidLoginException;
import com.example.exception.InvalidMessageException;
import com.example.exception.InvalidRegistrationException;
import com.example.exception.InvalidUserException;
import com.example.exception.MessageUpdateException;

@RestControllerAdvice
public class ExceptionAndErrorController {
    @ExceptionHandler(DuplicateUsernameException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String duplicateUsername(DuplicateUsernameException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(InvalidLoginException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String unauthorizedUser(InvalidLoginException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(InvalidRegistrationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String invalidRegistration(InvalidRegistrationException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(InvalidUserException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String invalidUser(InvalidUserException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(InvalidMessageException.class) 
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String invalidMessage(InvalidMessageException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(MessageUpdateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String invalidMessageUpdate(MessageUpdateException ex) {
        return ex.getMessage();
    }
}
