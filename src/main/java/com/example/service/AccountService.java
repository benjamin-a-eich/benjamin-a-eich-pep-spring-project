package com.example.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.entity.Account;
import com.example.exception.DuplicateUsernameException;
import com.example.exception.InvalidLoginException;
import com.example.exception.InvalidRegistrationException;
import com.example.exception.InvalidUserException;
import com.example.repository.AccountRepository;

@Service
public class AccountService {

    private AccountRepository accountRepository;
    
    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account login(Account account) throws InvalidLoginException  {
        Optional<Account> accountOptional = accountRepository.findByUsernameAndPassword(account.getUsername(), account.getPassword());
        if(accountOptional.isPresent()) {
            return accountOptional.get();
        } else {
            throw new InvalidLoginException("Account invalid.");
        }
    }

    public Account register(Account account) {
        // first validate input
        // username must not be blank, and password must not be blank & >= 4 chars
        if(validateUsernameHelper(account.getUsername()) == false) throw new InvalidRegistrationException("Invalid Username.");
        if(validatePasswordHelper(account.getPassword()) == false) throw new InvalidRegistrationException("Invalid password.");

        // check if there is a user with the username
        Optional<Account> accountOptional = accountRepository.findByUsername(account.getUsername());
        if(accountOptional.isPresent()) {
            // There is an account, Throw exception
            throw new DuplicateUsernameException(account.getUsername() + " is already in use. Try a new Username.");
        } else {
            Account result = accountRepository.save(account);
            return result;
        }
    }

    @Transactional(readOnly = true)
    public boolean validateUserHelper(int accountID) {
        Optional<Account> accountOptional = accountRepository.findById(accountID);
        if(accountOptional.isPresent()) {
            return true;
        } else {
            throw new InvalidUserException(accountID + " is not a valid account id.");
        }

    }

    private boolean validateUsernameHelper(String username) {
        // Check if username is blank or null 
        if(username == null || username == "") {
            return false;
        } else {
            return true;
        }
    }

    private boolean validatePasswordHelper(String password) {
        // Check to make sure its not null
        if(password == null) {
            return false;
        } else if (password.length() >= 4) {
            // Here we check to make sure it meets minimum length requirements.
            return true;
        }
        return false;
    }
}
