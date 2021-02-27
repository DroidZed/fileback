package com.wevioo.fileback.exceptions;

import static java.text.MessageFormat.format;

public class UserNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 2966525350248995014L;

    public UserNotFoundException(Long id) {
        super(format("User with id {0} could not be found !", id));
    }
}
