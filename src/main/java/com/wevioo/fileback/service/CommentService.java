package com.wevioo.fileback.service;

import com.wevioo.fileback.enums.EtatBesoin;
import com.wevioo.fileback.exceptions.NeedNotFoundException;
import com.wevioo.fileback.interfaces.CommentManager;
import com.wevioo.fileback.message.CommentBox;
import com.wevioo.fileback.model.Comment;
import com.wevioo.fileback.model.Needs;
import com.wevioo.fileback.model.User;
import com.wevioo.fileback.repository.CommentRepository;
import com.wevioo.fileback.repository.NeedsRepository;
import com.wevioo.fileback.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.CompletableFuture;

@Service
@AllArgsConstructor
public class CommentService implements CommentManager {

    private final CommentRepository commentRepository;
    private final NeedsRepository needsRepository;
    private final UserManagerLayer userManagerLayer;

    @Override
    @Async
    public CompletableFuture<ResponseEntity<?>> publishComment(Comment comment, Long needId) {

        Optional<Needs> opt = this.needsRepository.findById(needId);

        if (opt.isEmpty())
            return CompletableFuture.completedFuture(ResponseEntity.status(HttpStatus.NOT_FOUND).body("Need with id " + needId + " could not befound !"));

        Needs n = opt.get();

        if (n.getEtatBesoin() == EtatBesoin.CLOTURE)
            return CompletableFuture.completedFuture(ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Le besoin est clôturé !!"));

        comment.setBesoin(n);

        CommentBox cbox = new CommentBox();
        cbox.setCommentObject(comment);
        User details = this.userManagerLayer.getAUserById(comment.getSenderId());
        cbox.setCommentOwnerFullName(details.getFullName());
        cbox.setCommentOwnerImage("http://localhost:8080/users/image/get/"+comment.getSenderId());
        this.commentRepository.save(comment);
        return CompletableFuture.completedFuture(ResponseEntity.ok(cbox));
    }

    @Override
    public List<CommentBox> getCommentsOfNeed(Long needId) {

       return this.needsRepository.findById(needId)
               .map(need -> {
                   Set<Comment> comments = need.getComments();
                   List<CommentBox> cboxes = new ArrayList<>();
                   for (Comment c : comments) {

                       CommentBox cbox = new CommentBox();
                       cbox.setCommentObject(c);
                       User details = this.userManagerLayer.getAUserById(c.getSenderId());
                       cbox.setCommentOwnerFullName(details.getFullName());
                       cbox.setCommentOwnerImage("http://localhost:8080/users/image/get/"+c.getSenderId());

                       cboxes.add(cbox);
                   }
                   return cboxes;
               })
               .orElseThrow(() -> new NeedNotFoundException(needId));
    }

    @Override
    public CommentBox getLastCommentPublishedOfNeed(Long needId)
    {
        Comment c = this.commentRepository.getLastComment(needId);

        CommentBox cbox = new CommentBox();

        cbox.setCommentObject(c);

        User details = this.userManagerLayer.getAUserById(c.getSenderId());

        cbox.setCommentOwnerFullName(details.getFullName());

        cbox.setCommentOwnerImage("http://localhost:8080/users/image/get/"+c.getSenderId());

        return cbox;

    }
}
