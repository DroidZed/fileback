package com.wevioo.fileback.repository;

import com.wevioo.fileback.model.Evaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {

    @Query(value = "select * from evaluation e left outer join users u using(id_user) where e.id_user = ?1", nativeQuery = true)
    List<Evaluation> findAllByEvaluatedId(Long evaluatedId);
}
