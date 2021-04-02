package com.wevioo.fileback.interfaces;

import com.wevioo.fileback.model.Devis;
import org.springframework.http.ResponseEntity;

public interface DevisManager {

    ResponseEntity<?> saveDevis(Devis d, Long jobberId, Long needId);

    ResponseEntity<?> confirmerDevis(Long devisId);

}
