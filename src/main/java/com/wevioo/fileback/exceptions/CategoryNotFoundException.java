package com.wevioo.fileback.exceptions;

import static java.text.MessageFormat.format;

import java.io.Serial;

public class CategoryNotFoundException extends RuntimeException {


    @Serial
    private static final long serialVersionUID = 1L;

    public CategoryNotFoundException(Long id) {
        super(format("Category with id {0} could not be found !", id));
    }
}
