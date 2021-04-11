package com.wevioo.fileback.interfaces;

import com.wevioo.fileback.model.ChatModel;

import java.util.List;

public interface ChatManager
{
    ChatModel deliverMessage(ChatModel chat, Long to);
    List<ChatModel> getAllByChatRoomId(Long chatroomId);
}
