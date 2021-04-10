package com.wevioo.fileback.service;

import com.wevioo.fileback.enums.EtatBesoin;
import com.wevioo.fileback.exceptions.NeedNotFoundException;
import com.wevioo.fileback.interfaces.CommentManager;
import com.wevioo.fileback.model.Comment;
import com.wevioo.fileback.model.Needs;
import com.wevioo.fileback.repository.CommentRepository;
import com.wevioo.fileback.repository.NeedsRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

@Service
@AllArgsConstructor
public class CommentService implements CommentManager {

    private final CommentRepository commentRepository;
    private final NeedsRepository needsRepository;

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

        return CompletableFuture.completedFuture(ResponseEntity.ok(this.commentRepository.save(comment)));
    }

    @Override
    public Set<Comment> getCommentsOfNeed(Long needId) {

       return this.needsRepository.findById(needId)
               .map(Needs::getComments).orElseThrow(() -> new NeedNotFoundException(needId));
    }
}
