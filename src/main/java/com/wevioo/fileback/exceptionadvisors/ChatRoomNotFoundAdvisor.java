package com.wevioo.fileback.exceptionadvisors;

import com.wevioo.fileback.exceptions.ChatRoomNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ChatRoomNotFoundAdvisor {

    @ResponseBody
    @ExceptionHandler(ChatRoomNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String chatRoomNotFoundHandle(ChatRoomNotFoundException ex)
    {
        return ex.getMessage();
    }
}
