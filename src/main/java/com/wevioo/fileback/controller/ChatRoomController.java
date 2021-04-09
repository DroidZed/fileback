package com.wevioo.fileback.controller;

import com.wevioo.fileback.interfaces.ChatRoomManager;
import com.wevioo.fileback.model.ChatRoom;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/chatroom")
public class ChatRoomController {

    private final ChatRoomManager chatRoomManager;

    @PostMapping(path = "/create")
    public ChatRoom createChatRoom(@RequestParam("sender") Long sender, @RequestParam("receiver") Long receiver)
    {
        return this.chatRoomManager.createChatRoom(sender,receiver);
    }

    @GetMapping(path = "/get")
    public ChatRoom getChatRoom(@RequestParam("sender") Long sender, @RequestParam("receiver") Long receiver)
    {
        return this.chatRoomManager.getChatRoom(sender,receiver);
    }
}
