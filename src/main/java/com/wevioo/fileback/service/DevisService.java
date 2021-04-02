package com.wevioo.fileback.service;

import com.wevioo.fileback.exceptions.DevisNotFoundException;
import com.wevioo.fileback.interfaces.DevisManager;
import com.wevioo.fileback.message.ResponseMessage;
import com.wevioo.fileback.model.Devis;
import com.wevioo.fileback.model.Needs;
import com.wevioo.fileback.model.User;
import com.wevioo.fileback.repository.DevisRepository;
import com.wevioo.fileback.repository.NeedsRepository;
import com.wevioo.fileback.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class DevisService implements DevisManager {

    private final DevisRepository devisRepository;
    private final UserRepository userRepository;
    private final NeedsRepository needsRepository;

    @Override
    public ResponseEntity<?> saveDevis(Devis d, Long jobberId, Long needId) {

        Optional<User> optUser = this.userRepository.findById(jobberId);

        Optional<Needs> optNeed = this.needsRepository.findById(needId);

        if (optUser.isEmpty() || optNeed.isEmpty())
            return ResponseEntity.badRequest().body("Error : can't find jobber or need !");

        User user = optUser.get();
        Needs need = optNeed.get();

        d.setJobber(user);
        d.setNeed(need);

        this.devisRepository.save(d);

        return ResponseEntity.ok(new ResponseMessage("Devis enregistré !"));
    }

    @Override
    public ResponseEntity<?> confirmerDevis(Long devisId) {
        return this.devisRepository.findById(devisId)
                .map(d -> {
                    d.setEtat(true);
                    devisRepository.save(d);
                    return ResponseEntity.ok(new ResponseMessage("Devis confirmé !"));
                })
                .orElseThrow(() -> new DevisNotFoundException(devisId));
    }
}
