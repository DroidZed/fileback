package com.wevioo.fileback.exceptions;

import java.io.Serial;

import static java.text.MessageFormat.format;

public class UserNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 2966525350248995014L;

    public UserNotFoundException(Long id) {
        super(format("User with id {0} could not be found !", id));
    }
}
