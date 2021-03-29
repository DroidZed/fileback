package com.wevioo.fileback.controller;

import com.wevioo.fileback.interfaces.MasterChatManager;
import com.wevioo.fileback.model.ChatMessage;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ChatController {

    private final MasterChatManager chatmanager;

    @MessageMapping(value = "/chat")
    public void processMessage(@Payload ChatMessage chatMessage)
    {
      this.chatmanager.dispatchMessage(chatMessage);
    }

    @GetMapping(path = "/messages/count-of/{senderId}/{recipientId}")
    public ResponseEntity<Long> countNewMessages(
            @PathVariable Long senderId,
            @PathVariable Long recipientId)
    {
        return this.chatmanager.countArrivedMessages(senderId,recipientId);
    }

    @GetMapping(path = "/messages/find-of/{senderId}/{recipientId}")
    public ResponseEntity<?> findChatMessages ( @PathVariable Long senderId,
                                                @PathVariable Long recipientId)
    {
        return this.chatmanager.gatherChatMessages(senderId,recipientId);
    }

    @GetMapping("/messages/{id}")
    public ResponseEntity<?> findMessage ( @PathVariable Long id)
    {
        return this.chatmanager.getOneMessage(id);
    }
}
