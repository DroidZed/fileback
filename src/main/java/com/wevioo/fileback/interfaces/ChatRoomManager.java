package com.wevioo.fileback.interfaces;

import java.util.Optional;

public interface ChatRoomManager {

   Optional<String> getChatId(Long senderId, Long recipientId, Boolean createIfNotExist);

}
