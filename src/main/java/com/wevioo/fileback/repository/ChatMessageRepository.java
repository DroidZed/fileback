package com.wevioo.fileback.repository;

import com.wevioo.fileback.enums.MessageStatus;
import com.wevioo.fileback.model.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {

    Long countBySenderIdAndRecipientIdAndStatus(Long senderId, Long recipientId, MessageStatus status);

    List<ChatMessage> findByChatId(String chatId);

    List<ChatMessage> findAllBySenderIdAndRecipientId(Long senderId, Long recipientId);

}
