package com.wevioo.fileback.controller;

import com.wevioo.fileback.model.Needs;
import com.wevioo.fileback.service.NeedsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/need")
public class NeedsController {

    private final NeedsService needsService;

    @PostMapping(path = "/add-new-need/user/{userid}/category/{catid}")
    public void addNewNeed(@RequestBody Needs needs,
                           @PathVariable Long catid,
                           @PathVariable Long userid){
        this.needsService.injectNewNeed(needs, catid, userid);
    }
}
