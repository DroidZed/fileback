package com.wevioo.fileback.interfaces;

import com.wevioo.fileback.model.ChatModel;
import com.wevioo.fileback.model.ChatRoom;

import java.util.List;

public interface ChatRoomManager {

    ChatRoom getChatRoom(Long sender, Long receiver);

    ChatRoom createChatRoom(Long sender, Long receiver);
}
