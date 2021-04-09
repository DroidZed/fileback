package com.wevioo.fileback.exceptions;

public class ChatRoomNotFoundException extends RuntimeException {
    
    public ChatRoomNotFoundException(Long id)
    {
        super("Chat room "+id+" could not be found !");
    }
}
