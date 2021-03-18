package com.wevioo.fileback.repository;

import com.wevioo.fileback.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    
    User findByUserName(String username);
    User findByEmail(String email);

    @Query("SELECT COUNT(u) from User u WHERE u.travailleur = true")
    Long countTravailleurs();

    @Query("SELECT COUNT(u) from User u WHERE u.travailleur = false")
    Long countUsers();

    @Query("SELECT u FROM User u WHERE u.travailleur = true")
    List<User> getAllJobbers();

    @Query("SELECT u FROM User u where u.travailleur = false")
    List<User> getUsersOnly();
}
