package com.wevioo.fileback.interfaces;

import com.wevioo.fileback.message.ChatMessage;

public interface ChatManager
{
    void deliverMessage(ChatMessage chat);
}
