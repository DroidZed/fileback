package com.wevioo.fileback.interfaces;

import com.wevioo.fileback.message.ChatMessage;
import com.wevioo.fileback.model.ChatModel;

import java.util.List;

public interface ChatManager
{
    void deliverMessage(ChatMessage chat);
}
