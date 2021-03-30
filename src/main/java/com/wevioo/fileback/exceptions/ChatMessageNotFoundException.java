package com.wevioo.fileback.exceptions;

public class ChatMessageNotFoundException extends RuntimeException {

    public ChatMessageNotFoundException(Long id) {
        super("Could not find message with id " + id);
    }
}
