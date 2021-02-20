package com.wevioo.fileback.controller;

import com.wevioo.fileback.model.User;
import com.wevioo.fileback.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/")
public class UsersController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping(path = "add")
    public void addUser(@RequestBody User user){
        this.userRepository.save(user);
    }
}
