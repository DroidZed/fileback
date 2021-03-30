package com.wevioo.fileback.exceptionadvisors;

import com.wevioo.fileback.exceptions.ChatMessageNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ChatMessageNotFoundAdvisor {

    @ResponseBody
    @ExceptionHandler(ChatMessageNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String chatNotFoundHandle(ChatMessageNotFoundException ex) {
        return ex.getMessage();
    }
}
