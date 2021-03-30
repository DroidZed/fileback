package com.wevioo.fileback.service;

import com.wevioo.fileback.interfaces.ChatMessageManager;
import com.wevioo.fileback.interfaces.ChatRoomManager;
import com.wevioo.fileback.interfaces.MasterChatManager;
import com.wevioo.fileback.model.ChatMessage;
import com.wevioo.fileback.model.ChatNotification;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class MasterChatService implements MasterChatManager
{
    private final ChatRoomManager chatRoomManager;
    private final ChatMessageManager chatMessageManager;
    private final SimpMessagingTemplate messagingTemplate;

    @Override
    public void dispatchMessage(ChatMessage chatMessage) {

        Optional<String> chatId = chatRoomManager
                .getChatId(
                        chatMessage.getSenderId(),
                        chatMessage.getRecipientId(),
                        true
                );

        chatId.ifPresent(chatMessage::setChatId);

        ChatMessage saved = chatMessageManager.save(chatMessage);

        messagingTemplate.convertAndSendToUser(
                ""+chatMessage.getRecipientId(),
                "/queue/messages",
                new ChatNotification(
                        saved.getChatMessageId(),
                        saved.getSenderId(),
                        saved.getSenderName()
                )
        );
    }

    @Override
    public ResponseEntity<Long> countArrivedMessages(Long senderId, Long recipientId) {
        return ResponseEntity
                .ok(chatMessageManager.countNewMessages(senderId, recipientId));
    }

    @Override
    public ResponseEntity<?> gatherChatMessages(Long senderId, Long recipientId) {
        return ResponseEntity
                .ok(chatMessageManager.findChatMessages(senderId, recipientId));
    }

    @Override
    public ResponseEntity<?> getOneMessage(Long id) {
        return ResponseEntity
                .ok(chatMessageManager.findById(id));
    }
}
