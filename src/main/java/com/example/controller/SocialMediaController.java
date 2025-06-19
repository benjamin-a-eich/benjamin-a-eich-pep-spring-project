package com.example.controller;

import java.util.List;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.service.AccountService;
import com.example.service.MessageService;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@RestController
@RequestMapping()
public class SocialMediaController {

    private AccountService accountService;
    private MessageService messageService;

    @Autowired
    public SocialMediaController(AccountService accountService, MessageService messageService) {
        this.accountService = accountService;
        this.messageService = messageService;
    }

    @PostMapping("login")
    public ResponseEntity<Account> login(@RequestBody Account account) throws AuthenticationException {
        Account result  = accountService.login(account);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping("register")
    public ResponseEntity<Account> register(@RequestBody Account account) {
        Account result = accountService.register(account);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping("messages")
    public ResponseEntity<Message> createMessage(@RequestBody Message message) {
        // Check if the user exists 
        accountService.validateUserHelper(message.getPostedBy());

        Message result = messageService.createMessage(message);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("messages")
    public ResponseEntity<List<Message>> getAllMessages() {
        List<Message> allMessages = messageService.getAllMessages();
        return ResponseEntity.status(HttpStatus.OK).body(allMessages);
    }

    @GetMapping("messages/{message_id}")
    public ResponseEntity<Message> getSingleMessageById(@PathVariable int message_id) {
        Message result = messageService.getMessageById(message_id);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @DeleteMapping("messages/{message_id}")
    public ResponseEntity<Integer> deleteSingleMessageById(@PathVariable int message_id) {
        Integer result = messageService.deleteMessageById(message_id);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PatchMapping("messages/{message_id}")
    public ResponseEntity<Integer> updateSingleMessageById(@PathVariable int message_id, @RequestBody Message newMessage) {
        Integer result = messageService.updateMessageById(newMessage, message_id);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("accounts/{account_id}/messages")
    public ResponseEntity<List<Message>> getAllMessagesByAccount(@PathVariable int account_id) {
        return ResponseEntity.status(HttpStatus.OK).body(messageService.getAllMessagesByAccountId(account_id));
    }
}
