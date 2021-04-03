package com.wevioo.fileback.exceptions;

import java.io.Serial;

public class ChatMessageNotFoundException extends RuntimeException {

    /**
     *
     */
    @Serial
    private static final long serialVersionUID = 8228559706309391827L;

    public ChatMessageNotFoundException(Long id) {
        super("Could not find message with id " + id);
    }
}
