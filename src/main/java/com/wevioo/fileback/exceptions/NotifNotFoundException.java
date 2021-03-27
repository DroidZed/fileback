package com.wevioo.fileback.exceptions;

public class NotifNotFoundException extends RuntimeException {

    public NotifNotFoundException(Long id) {
        super("Could not update notif " + id);
    }
}
