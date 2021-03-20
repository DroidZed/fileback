package com.wevioo.fileback.controller;

import com.wevioo.fileback.model.Services;
import com.wevioo.fileback.model.User;
import com.wevioo.fileback.service.JobberService;
import com.wevioo.fileback.service.RegisterService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/jobbers")
@ResponseBody
@AllArgsConstructor
public class JobberController {

    private final RegisterService regService;
    private final JobberService jobberService;

    @PostMapping(path = "add/")
    @ResponseBody
    public ResponseEntity<?> addJobber(@RequestParam Long cat_id, @RequestBody User user)
    {
        return regService.addJobber(cat_id, user);
    }

    @PutMapping(path = "service/add/{id_jb}")
    @ResponseBody
    public ResponseEntity<?> assignService(@PathVariable Long id_jb, @RequestBody Services serv)
    {
        return this.jobberService.addServiceToJobber(id_jb,serv);
    }

    @GetMapping(path = "get/all")
    @ResponseBody
    public List<User> getAllJobbers()
    {
        return this.jobberService.selectOnlyJobbers();
    }
}
