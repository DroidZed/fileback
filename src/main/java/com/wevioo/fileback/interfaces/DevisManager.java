package com.wevioo.fileback.interfaces;

import com.wevioo.fileback.enums.EtatDevis;
import com.wevioo.fileback.model.Devis;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface DevisManager {

    ResponseEntity<?> saveDevis(Devis d, Long jobberId, Long needId);

    CompletableFuture<ResponseEntity<?>> changeEtatDevis(Long devisId, EtatDevis etat);

    Devis getDevisById(Long devisId);

    List<Devis> getAll();

    CompletableFuture<ResponseEntity<?>> getParticulierImage(Long devisID) throws IOException;

}
