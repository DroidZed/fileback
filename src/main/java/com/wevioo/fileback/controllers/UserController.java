package com.wevioo.fileback.controllers;

// import com.wevioo.fileback.models.Activites;
import com.wevioo.fileback.models.Utilisateur;
// import com.wevioo.fileback.repo.ActivitiesRepositeries;
import com.wevioo.fileback.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepo repo;

    // @Autowired
    // private ActivitiesRepositeries activitiesRepositeries;

    @Autowired
    public UserController(UserRepo repo) {
        this.repo = repo;
    }

    @PostMapping("/add")
    public void insertUser(@RequestBody Utilisateur user) {
        this.repo.save(user);
    }




}
