package com.wevioo.fileback.repository;

import com.wevioo.fileback.model.Locations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationsRepository extends JpaRepository<Locations, Long> {
}
