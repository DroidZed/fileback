package com.wevioo.fileback.service;

import com.wevioo.fileback.exceptions.ChatRoomNotFoundException;
import com.wevioo.fileback.exceptions.UserNotFoundException;
import com.wevioo.fileback.interfaces.ChatManager;
import com.wevioo.fileback.message.ChatMessage;
import com.wevioo.fileback.model.ChatModel;
import com.wevioo.fileback.model.ChatRoom;
import com.wevioo.fileback.model.User;
import com.wevioo.fileback.repository.ChatModelRepository;
import com.wevioo.fileback.repository.ChatRoomRepository;
import com.wevioo.fileback.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ChatService implements ChatManager {

    private final SimpMessagingTemplate simpMessagingTemplate;
    private final ChatModelRepository chatModelRepository;
    private final ChatRoomRepository chatRoomRepository;

    @Override
    @Async
    public void deliverMessage(ChatMessage msg)
    {

        Long idchatroom = msg.getChatRoomId();

        Optional<ChatRoom> opt = this.chatRoomRepository.findById(idchatroom);

        if (opt.isEmpty())
            throw new ChatRoomNotFoundException(idchatroom);

        ChatRoom chatRoom = opt.get();

        ChatModel model = new ChatModel();

        model.setMessage(msg.getMessage());
        model.setChatRoom(chatRoom);
        model.setMsgDate(LocalDateTime.now());

        // TODO: this.chatModelRepository.save(model);

        simpMessagingTemplate.convertAndSend("/topic/messages/" + chatRoom.getChatRoomId(),  model);
    }
}
