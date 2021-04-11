package com.wevioo.fileback.repository;

import com.wevioo.fileback.model.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {

    @Query(value = "SELECT cr FROM ChatRoom cr WHERE cr.user1Id = ?1")
    List<ChatRoom> findAllByUser1Id(Long user1Id);

    @Query(value = "SELECT cr FROM ChatRoom cr WHERE cr.user2Id = ?1")
    List<ChatRoom> findAllByUser2Id(Long user2Id);
}
