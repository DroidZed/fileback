package com.wevioo.fileback.controller;

import com.wevioo.fileback.model.User;
import com.wevioo.fileback.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/users")
@CrossOrigin("http://localhost:4200")
@ResponseBody
@AllArgsConstructor
public class UsersController {

    private final UserRepository userRepository;

    @PostMapping(path = "add")
    public void addUser(@RequestBody User user){
        this.userRepository.save(user);
    }
}
