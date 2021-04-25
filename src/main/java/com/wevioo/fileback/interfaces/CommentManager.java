package com.wevioo.fileback.interfaces;

import com.wevioo.fileback.message.CommentBox;
import com.wevioo.fileback.model.Comment;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface CommentManager {

    CompletableFuture<ResponseEntity<?>> publishComment(Comment comment, Long needId);

    List<CommentBox> getCommentsOfNeed(Long needId);

    CommentBox getLastCommentPublishedOfNeed(Long needId);
}
