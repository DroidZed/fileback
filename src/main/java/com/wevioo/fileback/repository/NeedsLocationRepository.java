package com.wevioo.fileback.repository;

import com.wevioo.fileback.model.NeedLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NeedsLocationRepository extends JpaRepository<NeedLocation,Long> {
}
