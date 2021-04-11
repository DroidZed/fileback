package com.wevioo.fileback.controller;

import com.wevioo.fileback.interfaces.ChatRoomManager;
import com.wevioo.fileback.model.ChatRoom;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/chatroom")
public class ChatRoomController {

    private final ChatRoomManager chatRoomManager;

    @PostMapping(path = "/create")
    public ChatRoom createChatRoom(@RequestParam("user1") Long user1, @RequestParam("user2") Long user2)
    {
        return this.chatRoomManager.createChatRoom(user1,user2);
    }

    @GetMapping(path = "/get")
    public ChatRoom getChatRoom(@RequestParam("chatRoomId") Long chatRoomId)
    {
        return this.chatRoomManager.getChatRoom(chatRoomId);
    }

    @GetMapping(path = "/get/user1")
    public List<ChatRoom> getAllOfUser1(@RequestParam("id") Long id)
    {
        return this.chatRoomManager.getAllOfUser1(id);
    }

    @GetMapping(path = "/get/user2")
    public List<ChatRoom> getAllOfUser2(@RequestParam("id") Long id)
    {
        return this.chatRoomManager.getAllOfUser2(id);
    }
}
