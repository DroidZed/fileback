package com.wevioo.fileback.repository;

import com.wevioo.fileback.model.Needs;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NeedsRepository extends JpaRepository<Needs,Long> {

    @Query("from Needs n WHERE n.user.idUser = ?1")
    List<Needs> findNeedsOfUser(Long id);

    @Query("from Needs n WHERE n.category.idCat = ?1")
    List<Needs> findNeedsByCategory(Long catid);
}
