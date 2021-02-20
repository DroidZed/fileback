package com.wevioo.fileback.repo;

import com.wevioo.fileback.models.Activites;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivitiesRepositeries extends JpaRepository<Activites , Long> {
}
