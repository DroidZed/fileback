package com.wevioo.fileback.controller;

import com.wevioo.fileback.interfaces.EvaluationsManager;
import com.wevioo.fileback.interfaces.JobberManager;
import com.wevioo.fileback.interfaces.Register;
import com.wevioo.fileback.message.ResponseMessage;
import com.wevioo.fileback.model.Evaluation;
import com.wevioo.fileback.model.Services;
import com.wevioo.fileback.model.User;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/jobbers")
@AllArgsConstructor
public class JobberController {

    private final Register register;
    private final JobberManager jobberManager;
    private final EvaluationsManager evaluationsManager;

    @PostMapping(path = "add")
    public ResponseEntity<?> addJobber(@RequestParam Long cat_id, @RequestBody User user)
    {
        return register.addJobber(cat_id, user);
    }

    @PutMapping(path = "service/add/{id_jb}")
    public ResponseEntity<?> assignService(@PathVariable Long id_jb, @RequestBody Services serv)
    {
        return this.jobberManager.addServiceToJobber(id_jb,serv);
    }

    @PostMapping(path = "/evaluate")
    public ResponseEntity<ResponseMessage> evaluateJobber(@RequestParam("jobberId") Long jobberId, @RequestBody Evaluation eval)
    {
        return this.evaluationsManager.publishEvaluation(eval,jobberId);
    }

    @GetMapping(path = "/get/evals")
    public ResponseEntity<?> getEvalsOfJobber(@RequestParam("jobberId") Long jobberId)
    {
        return this.evaluationsManager.collectEvaluationsOfJobber(jobberId);
    }

    @GetMapping(path = "get/all")
    public List<User> getAllJobbers()
    {
        return this.jobberManager.selectOnlyJobbers();
    }
}
