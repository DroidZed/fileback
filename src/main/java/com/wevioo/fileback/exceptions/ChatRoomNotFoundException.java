package com.wevioo.fileback.exceptions;

public class ChatRoomNotFoundException extends RuntimeException {
    
    /**
     *
     */
    private static final long serialVersionUID = -2749241344602057972L;

    public ChatRoomNotFoundException(Long id)
    {
        super("Chat room "+id+" could not be found !");
    }
}
