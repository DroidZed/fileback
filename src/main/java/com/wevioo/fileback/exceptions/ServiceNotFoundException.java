package com.wevioo.fileback.exceptions;

import java.io.Serial;

import static java.text.MessageFormat.format;

public class ServiceNotFoundException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 10165495L;

    public ServiceNotFoundException(Long id)
    {
        super(format("Unable to find service with id = {0}", id));
    }
}
