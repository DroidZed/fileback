package com.wevioo.fileback.repository;

import com.wevioo.fileback.model.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {

    List<ChatRoom> findAllByUser1Id(Long user1Id);
}
