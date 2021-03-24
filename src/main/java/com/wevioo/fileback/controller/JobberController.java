package com.wevioo.fileback.controller;

import com.wevioo.fileback.interfaces.JobberManager;
import com.wevioo.fileback.interfaces.Register;
import com.wevioo.fileback.model.Services;
import com.wevioo.fileback.model.User;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/jobbers")
@ResponseBody
@AllArgsConstructor
public class JobberController {

    private final Register register;
    private final JobberManager jobberManager;

    @PostMapping(path = "add")
    @ResponseBody
    public ResponseEntity<?> addJobber(@RequestParam Long cat_id, @RequestBody User user)
    {
        return register.addJobber(cat_id, user);
    }

    @PutMapping(path = "service/add/{id_jb}")
    @ResponseBody
    public ResponseEntity<?> assignService(@PathVariable Long id_jb, @RequestBody Services serv)
    {
        return this.jobberManager.addServiceToJobber(id_jb,serv);
    }

    @GetMapping(path = "get/all")
    @ResponseBody
    public List<User> getAllJobbers()
    {
        return this.jobberManager.selectOnlyJobbers();
    }
}
