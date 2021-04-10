package com.wevioo.fileback.service;

import com.wevioo.fileback.exceptions.ChatRoomNotFoundException;
import com.wevioo.fileback.interfaces.ChatManager;
import com.wevioo.fileback.message.ChatMessage;
import com.wevioo.fileback.model.ChatModel;
import com.wevioo.fileback.model.ChatRoom;
import com.wevioo.fileback.repository.ChatModelRepository;
import com.wevioo.fileback.repository.ChatRoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
@Service
@AllArgsConstructor
public class ChatService implements ChatManager {

    private final SimpMessagingTemplate simpMessagingTemplate;
    private final ChatRoomRepository chatRoomRepository;

    @Override
    public void deliverMessage(ChatMessage msg)
    {
        Long idchatroom = msg.getChatRoomId();

       ChatRoom chatRoom  = this.chatRoomRepository.findById(idchatroom)
       .orElseThrow(() -> new
            ChatRoomNotFoundException(idchatroom)
       );

        ChatModel model = new ChatModel(msg.getMessage(),LocalDateTime.now(),chatRoom);

        simpMessagingTemplate.convertAndSend("/topic/messages/" + chatRoom.getChatRoomId(),  model);
    }
}
