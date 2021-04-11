package com.wevioo.fileback.service;

import com.wevioo.fileback.exceptions.ChatRoomNotFoundException;
import com.wevioo.fileback.interfaces.ChatManager;
import com.wevioo.fileback.model.ChatModel;
import com.wevioo.fileback.repository.ChatModelRepository;
import com.wevioo.fileback.repository.ChatRoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ChatService implements ChatManager {

    private final ChatRoomRepository chatRoomRepository;
    private final ChatModelRepository chatModelRepository;

    @Override
    @Transactional
    public ChatModel deliverMessage(ChatModel msg, Long to) {

        msg.setChatRoom(this.chatRoomRepository.findById(to)
                .orElseThrow(
                        () -> new ChatRoomNotFoundException(to)
                ));

        return this.chatModelRepository.saveAndFlush(msg);
    }

    @Override
    public List<ChatModel> getAllByChatRoomId(Long chatroomId) {
        return this.chatModelRepository
                .findAllByChatRoomId(chatroomId)
                .stream()
                .map(this::getChats)
                .collect(Collectors.toList());
    }

    private ChatModel getChats(ChatModel entity) {
        return new ChatModel(entity.getChatId(),
                entity.getMessage(),
                entity.getMsgDate(),
                entity.getSenderId(),
                entity.getChatRoom()
        );
    }
}
