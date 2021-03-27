package com.wevioo.fileback.exceptionadvisors;

import com.wevioo.fileback.exceptions.NotifNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class NotifNotFoundAdvisor {

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotifNotFoundException.class)
    public String handleException(NotifNotFoundException ex)
    {
        return ex.getMessage();
    }
}
