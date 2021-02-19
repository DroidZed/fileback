package com.wevioo.fileback.repo;

import com.wevioo.fileback.models.Administrateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepo extends JpaRepository<Administrateur,Long> {
}
