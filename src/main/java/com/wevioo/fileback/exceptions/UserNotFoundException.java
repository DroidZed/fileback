package com.wevioo.fileback.exceptions;

import static java.text.MessageFormat.format;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(Long id) {
        super(format("User with id {0} could not be found !", id));
    }
}
