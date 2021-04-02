package com.wevioo.fileback.exceptionadvisors;

import com.wevioo.fileback.exceptions.DevisNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class DevisNotFoundExceptionAdvisor {

    @ResponseBody
    @ExceptionHandler(DevisNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String devisNotFoundHandle(DevisNotFoundException ex)
    {
        return ex.getMessage();
    }
}
