package com.wevioo.fileback.repository;

import com.wevioo.fileback.model.ChatModel;
import com.wevioo.fileback.model.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {

    ChatRoom findBySenderIdAndReceiverId(Long senderId, Long receiverId);
}
