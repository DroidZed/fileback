package com.wevioo.fileback.service;

import com.wevioo.fileback.interfaces.ChatRoomManager;
import com.wevioo.fileback.model.ChatRoom;
import com.wevioo.fileback.repository.ChatRoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.text.MessageFormat.format;

@Service
@AllArgsConstructor
public class ChatRoomService implements ChatRoomManager {

    private final ChatRoomRepository chatRoomRepository;

    @Override
    public Optional<String> getChatId(Long senderId, Long recipientId, Boolean createIfNotExist) {
        return chatRoomRepository
                .findBySenderIdAndRecipientId(senderId, recipientId)
                .map(ChatRoom::getChatId)
                .or(() -> {
                    if(!createIfNotExist) {
                        return Optional.empty();
                    }
                    var chatId = format("{0}_{1}", senderId, recipientId);

                    ChatRoom senderRecipient = ChatRoom
                            .builder()
                            .chatId(chatId)
                            .senderId(senderId)
                            .recipientId(recipientId)
                            .build();

                    ChatRoom recipientSender = ChatRoom
                            .builder()
                            .chatId(chatId)
                            .senderId(recipientId)
                            .recipientId(senderId)
                            .build();

                    chatRoomRepository.save(senderRecipient);
                    chatRoomRepository.save(recipientSender);

                    return Optional.of(chatId);
                });
    }
}
