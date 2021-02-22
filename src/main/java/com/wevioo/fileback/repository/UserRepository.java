package com.wevioo.fileback.repository;

import com.wevioo.fileback.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUserName(String username);
    User findByEmail(String email);
}
