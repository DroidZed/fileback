package com.wevioo.fileback.service;

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
    public ChatRoom getChatRoom(Long sender, Long receiver) {
        return this.chatRoomRepository.findBySenderIdAndReceiverId(sender,receiver);
    }

    @Override
    public ChatRoom createChatRoom(Long sender, Long receiver) {
       return this.chatRoomRepository.save(new ChatRoom(sender,receiver));
    }
}
