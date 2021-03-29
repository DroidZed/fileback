package com.wevioo.fileback.interfaces;

import com.wevioo.fileback.enums.MessageStatus;
import com.wevioo.fileback.model.ChatMessage;

import java.util.List;

public interface ChatMessageManager {

   ChatMessage save(ChatMessage chatMessage);
   Long countNewMessages(Long senderId, Long recipientId);
   List<ChatMessage> findChatMessages(Long senderId, Long recipientId);
   ChatMessage findById(Long id);
   void updateStatuses(Long senderId, Long recipientId, MessageStatus status);
}
