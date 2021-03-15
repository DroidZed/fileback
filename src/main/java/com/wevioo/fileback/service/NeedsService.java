package com.wevioo.fileback.service;

import com.wevioo.fileback.model.Category;
import com.wevioo.fileback.model.Needs;
import com.wevioo.fileback.model.User;
import com.wevioo.fileback.repository.CategoryRepository;
import com.wevioo.fileback.repository.NeedsRepository;
import com.wevioo.fileback.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class NeedsService {

    private final NeedsRepository needsRepository;

    private final UserRepository userRepository;

    private final CategoryRepository categoryRepository;

    public ResponseEntity<?> injectNewNeed (Needs needs, Long catid, Long userid){

        Optional<User> optUsr = userRepository.findById(userid);
        Optional<Category> optCat = categoryRepository.findById(catid);

        if(optUsr.isPresent() && optCat.isPresent())
        {
            needs.setUser(optUsr.get());
            needs.setCategory(optCat.get());
        }

        return ResponseEntity.ok(this.needsRepository.save(needs));
    }
}
