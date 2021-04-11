package com.wevioo.fileback.controller;

import com.wevioo.fileback.interfaces.ChatManager;
import com.wevioo.fileback.model.ChatModel;
import com.wevioo.fileback.model.ChatRoom;
import lombok.AllArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class ChatController {

    private final ChatManager chatManager;

    @SubscribeMapping("/messages/{chatroomId}")
    public List<ChatModel> getMessagesOfChatRoom(@DestinationVariable Long chatRoomId)
    {
        return this.chatManager.getAllByChatRoomId(chatRoomId);
    }

    @MessageMapping(value = "/chat")
    @SendTo(value = "/topic/messages/{to}")
    public ChatModel sendMessage(@Payload ChatModel chat, @DestinationVariable Long to)
    {
        return this.chatManager.deliverMessage(chat, to);
    }
}
