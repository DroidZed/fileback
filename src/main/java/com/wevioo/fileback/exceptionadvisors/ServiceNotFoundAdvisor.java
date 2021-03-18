package com.wevioo.fileback.exceptionadvisors;

import com.wevioo.fileback.exceptions.ServiceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ServiceNotFoundAdvisor {

    @ResponseBody
    @ExceptionHandler(ServiceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String serviceNotFoundHandle(ServiceNotFoundException ex)
    {
        return ex.getMessage();
    }
}
