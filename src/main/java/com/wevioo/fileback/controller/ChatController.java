package com.wevioo.fileback.controller;

import com.wevioo.fileback.interfaces.ChatManager;
import com.wevioo.fileback.message.ChatMessage;
import com.wevioo.fileback.model.ChatModel;
import lombok.AllArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class ChatController {

    private final ChatManager chatManager;
    
    @MessageMapping(value = "/chat")
    public void sendMessage(@Payload ChatMessage chat)
    {
        this.chatManager.deliverMessage(chat);
    }
}
