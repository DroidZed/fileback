package com.wevioo.fileback.repository;

import com.wevioo.fileback.model.Locations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationsRepository extends JpaRepository<Locations, Long> {

    @Query(value = "SELECT * from locations right join users using(id_loc) where users.travailleur = false",nativeQuery = true)
    List<Locations> getLocationsOfUsers();

    @Query(value = "SELECT * from locations right join users using(id_loc) where users.travailleur = true",nativeQuery = true)
    List<Locations> getLocationsOfJobbers();
}
