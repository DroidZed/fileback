package com.wevioo.fileback.interfaces;

import com.wevioo.fileback.model.Services;
import com.wevioo.fileback.model.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface JobberManager {

    ResponseEntity<?> addServiceToJobber(Long id_jobber, Services serv);
    List<User> selectOnlyJobbers();
}
