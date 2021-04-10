package com.wevioo.fileback.repository;

import com.wevioo.fileback.model.ChatModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatModelRepository extends JpaRepository<ChatModel, Long> {
}
