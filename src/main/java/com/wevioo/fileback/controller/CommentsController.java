package com.wevioo.fileback.controller;

import com.wevioo.fileback.interfaces.CommentManager;
import com.wevioo.fileback.message.CommentBox;
import com.wevioo.fileback.model.Comment;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/comment")
public class CommentsController {

        private final CommentManager commentManager;

        @GetMapping(path = "/get/all")
        public List<CommentBox> getAllCommentsOfNeed(@RequestParam("needId") Long needId)
        {
            return this.commentManager.getCommentsOfNeed(needId);
        }

        @GetMapping(path = "/get/last")
        public CommentBox getLastComment(@RequestParam("needId") Long needId)
        {
                return this.commentManager.getLastCommentPublishedOfNeed(needId);
        }

        @PostMapping(path = "/send")
        public CompletableFuture<ResponseEntity<?>> publishComment(@RequestParam("needId") Long needId, @RequestBody Comment comment)
        {
            return this.commentManager.publishComment(comment,needId);
        }
}
