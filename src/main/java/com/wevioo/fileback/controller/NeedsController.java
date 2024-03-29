package com.wevioo.fileback.controller;

import com.wevioo.fileback.interfaces.NeedsManager;
import com.wevioo.fileback.model.Needs;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(path = "/need")
@AllArgsConstructor
public class NeedsController {

    private final NeedsManager needsManager;

    @PostMapping(path = "/add-new-need/user/{userid}/category/{catid}")
    public ResponseEntity<?> addNewNeed(@RequestBody Needs needs,
                                        @PathVariable Long catid,
                                        @PathVariable Long userid){
       return this.needsManager.injectNewNeed(needs, catid, userid);
    }

    @GetMapping(path = "/get/user/{userid}")
    public List<Needs> getNeedsOfUser(@PathVariable Long userid)
    {
        return this.needsManager.collectNeedsOfUser(userid);
    }

    @GetMapping(path = "/count/needs")
    public Long countNeedsOfUser(@RequestParam("idUser") Long idUser)
    {
        return this.needsManager.countAllOfUser(idUser);
    }

    @GetMapping(path = "/get/all")
    public List<Needs> getAllNeeds()
    {
        return this.needsManager.getAll();
    }

    @GetMapping(path = "/count/comments")
    public  Long countCommentsOfNeed(@RequestParam("idNeed") Long idNeed)
    {
        return this.needsManager.countComments(idNeed);
    }


    @GetMapping(path = "/get/dates")
    public List<LocalDate> getLimitDates()
    {
        return this.needsManager.getLimitDates();
    }

    @GetMapping(path = "/get/count-by-cat")
    public List<?> getCountOfNeedsByCategory()
    {
        return this.needsManager.countByCategory();
    }

    @GetMapping(path = "/get/{id}")
    public ResponseEntity<?> getByID(@PathVariable Long id)
    {
        return this.needsManager.getNeedByID(id);
    }

    @GetMapping(path = "/get/category/{catid}")
    public List<Needs> getNeedsOfCategory(@PathVariable Long catid)
    {
        return this.needsManager.collectNeedsOfCategory(catid);
    }

    @GetMapping(path="/get/imgA/{needId}", produces = {MediaType.IMAGE_JPEG_VALUE})
    public CompletableFuture<ResponseEntity<?>> getNeedImageA(@PathVariable Long needId) throws IOException {
        return this.needsManager.getImageA(needId);
    }

    @GetMapping(path="/get/imgB/{needId}", produces = {MediaType.IMAGE_JPEG_VALUE})
    public CompletableFuture<ResponseEntity<?>> getNeedImageB(@PathVariable Long needId) throws IOException {
        return this.needsManager.getImageB(needId);
    }

    @GetMapping(path="/get/imgC/{needId}", produces = {MediaType.IMAGE_JPEG_VALUE})
    public CompletableFuture<ResponseEntity<?>> getNeedImageC(@PathVariable Long needId) throws IOException {
        return this.needsManager.getImageC(needId);
    }

    @GetMapping(path="/get/imgD/{needId}", produces = {MediaType.IMAGE_JPEG_VALUE})
    public CompletableFuture<ResponseEntity<?>> getNeedImageD(@PathVariable Long needId) throws IOException {
        return this.needsManager.getImageD(needId);
    }

    @PutMapping(path = "/update/{needid}")
    public ResponseEntity<?> updateNeed(@PathVariable Long needid, @RequestBody Needs need)
    {
        return this.needsManager.modifyNeed(needid, need);
    }
    
    @PutMapping(path = "/close/{needId}")
    public void closeNeed(@PathVariable Long needId)
    {
        this.needsManager.cloturerBesoin(needId);
    }

    @PostMapping(path = "/update/{id}/picA")
    public CompletableFuture<ResponseEntity<?>> setNeedPictureA(@RequestParam("file") MultipartFile imageA, @PathVariable Long id)
    {
        return this.needsManager.uploadNeedPicA(imageA,id);
    }

    @PostMapping(path = "/update/{id}/picB")
    public CompletableFuture<ResponseEntity<?>> setNeedPictureB(@RequestParam("file") MultipartFile imageB, @PathVariable Long id)
    {
        return this.needsManager.uploadNeedPicB(imageB,id);
    }

    @PostMapping(path = "/update/{id}/picC")
    public CompletableFuture<ResponseEntity<?>> setNeedPictureC(@RequestParam("file") MultipartFile imageC, @PathVariable Long id)
    {
        return this.needsManager.uploadNeedPicC(imageC,id);
    }

    @PostMapping(path = "/update/{id}/picD")
    public CompletableFuture<ResponseEntity<?>> setNeedPictureD(@RequestParam("file") MultipartFile imageD, @PathVariable Long id)
    {
        return this.needsManager.uploadNeedPicD(imageD,id);
    }

    @DeleteMapping(path = "/del/{needid}")
    public ResponseEntity<?> deleteOneNeed(@PathVariable Long needid)
    {
        return this.needsManager.deleteOne(needid);
    }

    @DeleteMapping(path = "/del/all")
    public ResponseEntity<?> deleteAllNeeds()
    {
        return this.needsManager.deleteAllTheNeeds();
    }
}
