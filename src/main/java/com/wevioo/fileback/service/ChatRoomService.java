package com.wevioo.fileback.service;

import com.wevioo.fileback.exceptions.ChatRoomNotFoundException;
import com.wevioo.fileback.interfaces.ChatRoomManager;
import com.wevioo.fileback.model.ChatModel;
import com.wevioo.fileback.model.ChatRoom;
import com.wevioo.fileback.repository.ChatRoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ChatRoomService implements ChatRoomManager {

    private final ChatRoomRepository chatRoomRepository;

    @Override
    public ChatRoom getChatRoom(Long chatRoomId) {
        return this.chatRoomRepository.findById(chatRoomId)
                .orElseThrow(() -> new ChatRoomNotFoundException(chatRoomId));
    }

    @Override
    public ChatRoom createChatRoom(Long user1, Long user2) {
       return this.chatRoomRepository.save(new ChatRoom(user1, user2));
    }

    @Override
    public List<ChatRoom> getAllOfUser(Long user1) {
        return this.chatRoomRepository.findAllByUser1Id(user1);
    }
}
