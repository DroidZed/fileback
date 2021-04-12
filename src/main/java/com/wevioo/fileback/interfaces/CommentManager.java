package com.wevioo.fileback.interfaces;

import com.wevioo.fileback.model.Comment;
import org.springframework.http.ResponseEntity;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public interface CommentManager {

    CompletableFuture<ResponseEntity<?>> publishComment(Comment comment, Long needId);

    Set<Comment> getCommentsOfNeed(Long needId);

    Comment getLastCommentPublishedOfNeed(Long needId);
}
