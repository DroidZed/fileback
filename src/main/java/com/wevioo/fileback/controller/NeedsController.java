package com.wevioo.fileback.controller;

import com.wevioo.fileback.model.Needs;
import com.wevioo.fileback.service.NeedsService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(path = "/need")
@AllArgsConstructor
public class NeedsController {

    private final NeedsService needsService;

    @PostMapping(path = "/add-new-need/user/{userid}/category/{catid}")
    @ResponseBody
    public ResponseEntity<?> addNewNeed(@RequestBody Needs needs,
                                        @PathVariable Long catid,
                                        @PathVariable Long userid){
       return this.needsService.injectNewNeed(needs, catid, userid);
    }

    @GetMapping(path = "/get/user/{userid}")
    @ResponseBody
    public List<Needs> getNeedsOfUser(@PathVariable Long userid)
    {
        return this.needsService.collectNeedsOfUser(userid);
    }

    @GetMapping(path = "/get/category/{catid}")
    @ResponseBody
    public List<Needs> getNeedsOfCategory(@PathVariable Long catid)
    {
        return this.needsService.collectNeedsOfCategory(catid);
    }

    @PutMapping(path = "/update/{needid}")
    @ResponseBody
    public ResponseEntity<?> updateNeed(@PathVariable Long needid, @RequestBody Needs need)
    {
        return this.needsService.modifyNeed(needid, need);
    }

    @PostMapping(path = "/update/{id}/picA")
    public CompletableFuture<ResponseEntity<?>> setNeedPictureA(@RequestParam("file") MultipartFile imageA, @PathVariable Long id)
    {
        return this.needsService.uploadNeedPicA(imageA,id);
    }

    @PostMapping(path = "/update/{id}/picB")
    public CompletableFuture<ResponseEntity<?>> setNeedPictureB(@RequestParam("file") MultipartFile imageB, @PathVariable Long id)
    {
        return this.needsService.uploadNeedPicB(imageB,id);
    }

    @PostMapping(path = "/update/{id}/picC")
    public CompletableFuture<ResponseEntity<?>> setNeedPictureC(@RequestParam("file") MultipartFile imageC, @PathVariable Long id)
    {
        return this.needsService.uploadNeedPicC(imageC,id);
    }

    @PostMapping(path = "/update/{id}/picD")
    public CompletableFuture<ResponseEntity<?>> setNeedPictureD(@RequestParam("file") MultipartFile imageD, @PathVariable Long id)
    {
        return this.needsService.uploadNeedPicD(imageD,id);
    }

    @DeleteMapping(path = "/del/{needid}")
    public ResponseEntity<?> deleteOneNeed(@PathVariable Long needid)
    {
        return this.needsService.deleteOne(needid);
    }

    @DeleteMapping(path = "/del/all")
    public ResponseEntity<?> deleteAllNeeds()
    {
        return this.needsService.deleteAllTheNeeds();
    }
}
