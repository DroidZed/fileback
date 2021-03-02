package com.wevioo.fileback.exceptionadvisors;

import com.wevioo.fileback.exceptions.CategoryNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class CategoryNotFoundAdivsor {

    @ResponseBody
    @ExceptionHandler(CategoryNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String imageNotFoundHandle(CategoryNotFoundException ex) {
        return ex.getMessage();
    }
}
