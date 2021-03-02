package com.wevioo.fileback.controller;

import com.wevioo.fileback.model.User;
import com.wevioo.fileback.service.RegisterService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/jobbers")
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@ResponseBody
@AllArgsConstructor
public class JobberController {

    private final RegisterService regService;

    @PostMapping(path = "add/")
    public ResponseEntity<?> addJobber(@RequestParam Long cat_id, @RequestBody User user) {
        return regService.addJobber(cat_id, user);
    }
}
