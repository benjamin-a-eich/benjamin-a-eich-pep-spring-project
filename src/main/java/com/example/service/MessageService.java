package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Message;
import com.example.exception.InvalidMessageException;
import com.example.exception.MessageUpdateException;
import com.example.repository.MessageRepository;

@Service
public class MessageService {

    private MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Transactional(readOnly = true)
    public List<Message> getAllMessages() {
        return (List<Message>) messageRepository.findAll();
    }

    public Message createMessage(Message message) {
        // validate message text
        if(messageVaildationHelper(message.getMessageText())) {
            // Validate the user in the controller
            return messageRepository.save(message);
        } else {
            throw new InvalidMessageException("Message is blank or exceeds length.");
        }
    }

    @Transactional
    public Integer deleteMessageById(Integer id) {
        Integer result = messageRepository.deleteByIdAndReturnCount(id);
        if(result.equals(0)) {
            return null;
        } else {
            return result;
        }
    }

    public Integer updateMessageById(Message newText, Integer id) {
        boolean validText = messageVaildationHelper(newText.getMessageText());
        if(validText) {
            //  Update message by id. if the message does not exist, throw a new Exception.
            Optional<Message> messageOptional = messageRepository.findById(id);
            if(messageOptional.isEmpty()) {
                throw new MessageUpdateException("Message with the id of " + id + "Does not exist.");
            }
            else {
                Message message = messageOptional.get();
                message.setMessageText(newText.getMessageText());
                messageRepository.save(message);
                return 1;
            }
        } else {
            throw new MessageUpdateException("Message is blank or exceeds length.");
        }
    }

    @Transactional(readOnly = true)
    public List<Message> getAllMessagesByAccountId(Integer accountID) {
        return (List<Message>)messageRepository.findAllMessagesByPostedBy(accountID);
    }

    @Transactional(readOnly = true)
    public Message getMessageById(Integer id) {
        Optional<Message> result = messageRepository.findById(id);
        if(result.isPresent()) {
            return result.get();
        } else {
            return null;
        }
    }

    private boolean messageVaildationHelper(String text){
        if(text == null || text == "") {
            return false;
        } else if (text.length() > 255) {
            return false;
        } else {
            return true;
        }
    }
}
