package com.wevioo.fileback.interfaces;

import com.wevioo.fileback.model.Devis;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DevisManager {

    void saveDevis(Devis d);

    List<Devis> getAllDevisOfNeed(Long needId);

    ResponseEntity<?> confirmerDevis(Long devisId);

}
