package com.wevioo.fileback.repository;

import com.wevioo.fileback.model.Needs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
@Repository
public interface NeedsRepository extends JpaRepository<Needs,Long> {

    @Query(value = "from Needs n WHERE n.user.idUser = ?1")
    List<Needs> findNeedsOfUser(Long id);

    @Query(value = "from Needs n WHERE n.category.idCat = ?1")
    List<Needs> findNeedsByCategory(Long catid);

    @Query(value = "select count(c.id_cat), c.nom from needs n join category c using (id_cat) group by c.nom order by c.nom", nativeQuery = true)
    List<?> getCountByCategoryName();

    @Query(value = "SELECT dateLimite from Needs order by dateLimite")
    List<LocalDate> getLimitDates();
}
