package com.wevioo.fileback.service;

import com.wevioo.fileback.exceptions.DevisNotFoundException;
import com.wevioo.fileback.interfaces.DevisManager;
import com.wevioo.fileback.message.ResponseMessage;
import com.wevioo.fileback.model.Devis;
import com.wevioo.fileback.repository.DevisRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DevisService implements DevisManager {

    private final DevisRepository devisRepository;

    @Override
    public void saveDevis(Devis d) {
        this.devisRepository.save(d);
    }

    @Override
    public List<Devis> getAllDevisOfNeed(Long needId) {
        return this.devisRepository.findAllByNeedId(needId);
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
