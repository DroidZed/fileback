package com.wevioo.fileback.repository;

import com.wevioo.fileback.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query(value = "SELECT * FROM Comment c left outer join needs using (id_need) where c.id_need = ?1 ORDER BY c.comment_id DESC LIMIT 1", nativeQuery = true)
    Comment getLastComment(Long besoinId);
}
