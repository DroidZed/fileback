package com.wevioo.fileback.service;

import com.wevioo.fileback.exceptions.NeedNotFoundException;
import com.wevioo.fileback.message.ResponseMessage;
import com.wevioo.fileback.model.Category;
import com.wevioo.fileback.model.Needs;
import com.wevioo.fileback.model.User;
import com.wevioo.fileback.repository.CategoryRepository;
import com.wevioo.fileback.repository.NeedsRepository;
import com.wevioo.fileback.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.text.MessageFormat.format;

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

    public List<Needs> collectNeedsOfUser(Long userid) {

        return this.needsRepository.findNeedsOfUser(userid);
    }

    public List<Needs> collectNeedsOfCategory(Long catid) {

        return this.needsRepository.findNeedsByCategory(catid);
    }

    public ResponseEntity<?> deleteAllTheNeeds() {
        this.needsRepository.deleteAll();
        return ResponseEntity.ok(new ResponseMessage("All needs deleted successfully !"));
    }

    public ResponseEntity<?> deleteOne(Long needid) {
        this.needsRepository.deleteById(needid);
        return ResponseEntity.ok(new ResponseMessage(format("Need {0} deleted successfully !", needid)));
    }

    public ResponseEntity<?> modifyNeed(Long needid, Needs need) {
        return this.needsRepository.findById(needid)
                .map(oldNeed -> {
                    oldNeed.setNeedTitle(need.getNeedTitle());
                    oldNeed.setNeedDescription(need.getNeedDescription());
                    oldNeed.setBudget(need.getBudget());
                    oldNeed.setNbJobber(need.getNbJobber());
                    oldNeed.setVehicleInfos(need.getVehicleInfos());
                    return ResponseEntity.ok(this.needsRepository.save(oldNeed));
                })
                .orElseThrow(() -> new NeedNotFoundException(needid));
    }
}
