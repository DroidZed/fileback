package com.wevioo.fileback.controller;

import com.wevioo.fileback.model.User;
import com.wevioo.fileback.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/")
@CrossOrigin("http://localhost:4200")
public class UsersController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping(path = "add")
    public void addUser(@RequestBody User user){
        this.userRepository.save(user);
    }
}
