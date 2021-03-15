package com.wevioo.fileback.controller;

import com.wevioo.fileback.model.Needs;
import com.wevioo.fileback.service.NeedsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/need")
public class NeedsController {

    @Autowired
    private NeedsService needsService;

    @PostMapping(path = "/add-new-need")
    public void addNewNeed (@RequestBody Needs needs){
        this.needsService.injectNewNeed(needs);
    }
}
