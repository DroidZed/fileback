package com.wevioo.fileback.exceptions;

import java.io.Serial;
import java.io.Serializable;

import static java.text.MessageFormat.format;

public class CategoryNotFoundException extends RuntimeException implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    public CategoryNotFoundException(Long id) {
        super(format("Category with id {0} could not be found !", id));
    }
}
