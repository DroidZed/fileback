package com.wevioo.fileback.repository;

import com.wevioo.fileback.model.Devis;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DevisRepository extends JpaRepository<Devis, Long> {

    List<Devis> findAllByNeedId(Long needId);
}
