package com.wevioo.fileback.exceptions;

import java.io.Serial;

public class NotifNotFoundException extends RuntimeException {

    /**
     *
     */
    @Serial
    private static final long serialVersionUID = -2353408264510362700L;

    public NotifNotFoundException(Long id) {
        super("Could not update notif " + id);
    }
}
