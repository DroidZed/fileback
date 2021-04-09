package com.wevioo.fileback.repository;

import com.wevioo.fileback.model.ChatModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatModelRepository extends JpaRepository<ChatModel, Long> {
}
