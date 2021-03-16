package com.wevioo.fileback.exceptions;

import java.io.Serial;

import static java.text.MessageFormat.format;

public class NeedNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 15497894562194964L;

    public NeedNotFoundException(Long id){
        super(format("Need with id = {0} could not be found !", id));
    }
}
