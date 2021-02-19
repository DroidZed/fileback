package com.wevioo.fileback.repo;

import com.wevioo.fileback.models.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<Utilisateur, Long> {
}
