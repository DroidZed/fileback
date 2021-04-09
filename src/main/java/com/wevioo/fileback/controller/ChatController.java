package com.wevioo.fileback.controller;

import com.wevioo.fileback.interfaces.ChatManager;
import com.wevioo.fileback.message.ChatMessage;
import com.wevioo.fileback.model.ChatModel;
import lombok.AllArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ChatController {

    private final ChatManager chatManager;
    
    @MessageMapping(value = "/chat/{to}")
    public void sendMessage(@DestinationVariable String to, @Payload ChatMessage chat)
    {
        this.chatManager.deliverMessage(to, chat);
    }
}
