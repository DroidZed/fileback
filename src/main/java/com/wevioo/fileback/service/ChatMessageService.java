package com.wevioo.fileback.service;

import com.wevioo.fileback.enums.MessageStatus;
import com.wevioo.fileback.exceptions.ChatMessageNotFoundException;
import com.wevioo.fileback.interfaces.ChatMessageManager;
import com.wevioo.fileback.interfaces.ChatRoomManager;
import com.wevioo.fileback.model.ChatMessage;
import com.wevioo.fileback.repository.ChatMessageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ChatMessageService implements ChatMessageManager {

    private final ChatMessageRepository chatMessageRepository;
    private final ChatRoomManager chatRoomManager;

    @Override
    public ChatMessage save(ChatMessage chatMessage) {
        chatMessage.setStatus(MessageStatus.RECEIVED);
        return chatMessageRepository.save(chatMessage);
    }

    @Override
    public Long countNewMessages(Long senderId, Long recipientId) {
        return chatMessageRepository.
                countBySenderIdAndRecipientIdAndStatus(
                        senderId,
                        recipientId,
                        MessageStatus.RECEIVED
                );
    }

    @Override
    public List<ChatMessage> findChatMessages(Long senderId, Long recipientId) {
        Optional<String> chatId =
                chatRoomManager.
                        getChatId(
                                senderId,
                                recipientId,
                                false
                        );

        List<ChatMessage> messages =
                chatId.map(chatMessageRepository::findByChatId).orElse(new ArrayList<>());

        if (messages.size() > 0) {
            updateStatuses(senderId, recipientId, MessageStatus.DELIVERED);
        }

        return messages;
    }

    @Override
    public ChatMessage findById(Long chatMessageId) {

        return chatMessageRepository
                .findById(chatMessageId)
                .map(chatMessage -> {
                    chatMessage.setStatus(MessageStatus.DELIVERED);
                    return chatMessageRepository.save(chatMessage);
                })
                .orElseThrow(() ->
                        new ChatMessageNotFoundException(chatMessageId));
    }

    @Override
    public void updateStatuses(Long senderId, Long recipientId, MessageStatus status) {

      this.chatMessageRepository.
              findAllBySenderIdAndRecipientId(senderId,recipientId)
              .forEach(message -> {
                  message.setStatus(status);
                  this.chatMessageRepository.save(message);
              });
    }
}
