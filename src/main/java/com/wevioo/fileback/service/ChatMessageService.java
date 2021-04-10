package com.wevioo.fileback.service;

import com.wevioo.fileback.model.ChatModel;
import com.wevioo.fileback.repository.ChatModelRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ChatMessageService {

    private final ChatModelRepository chatModelRepository;

    public void saveMessage(ChatModel model) {
        System.out.println("Saved entity : "+this.chatModelRepository.save(model));
    }
}
