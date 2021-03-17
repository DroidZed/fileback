package com.wevioo.fileback.exceptionadvisors;

import com.wevioo.fileback.exceptions.NeedNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class NeedNotFoundExceptionAdvisor {

    @ResponseBody
    @ExceptionHandler(NeedNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String needNotFoundHandle(NeedNotFoundException ex)
    {
        return ex.getMessage();
    }
}
