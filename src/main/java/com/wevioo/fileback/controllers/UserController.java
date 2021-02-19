package com.wevioo.fileback.controllers;

import com.wevioo.fileback.models.Utilisateur;
import com.wevioo.fileback.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepo repo;

    @Autowired
    public UserController(UserRepo repo) {
        this.repo = repo;
    }

    @PostMapping("/add")
    public void insertUser(@RequestParam Utilisateur user) {
        this.repo.save(user);
    }

}
