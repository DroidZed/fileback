package com.wevioo.fileback.interfaces;

import com.wevioo.fileback.model.ChatMessage;
import org.springframework.http.ResponseEntity;

public interface MasterChatManager {

    void dispatchMessage(ChatMessage chatMessage);
    ResponseEntity<Long> countArrivedMessages(Long senderId, Long recipientId);
    ResponseEntity<?> gatherChatMessages(Long senderId, Long recipientId);
    ResponseEntity<?> getOneMessage(Long id);
}
