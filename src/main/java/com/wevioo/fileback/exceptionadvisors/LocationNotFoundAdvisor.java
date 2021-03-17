package com.wevioo.fileback.exceptionadvisors;

import com.wevioo.fileback.exceptions.LocationNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class LocationNotFoundAdvisor {
    
    @ResponseBody
    @ExceptionHandler(LocationNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String locationNotFoundHandle(LocationNotFoundException ex)
    {
        return ex.getMessage();
    }
}
