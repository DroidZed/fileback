package com.wevioo.fileback.interfaces;

import com.wevioo.fileback.model.ChatModel;
import com.wevioo.fileback.model.ChatRoom;

import java.util.List;

public interface ChatRoomManager {

    ChatRoom getChatRoom(Long chatRoomId);

    ChatRoom createChatRoom(Long user1, Long user2);

    List<ChatRoom> getAllOfUser(Long user);
}
