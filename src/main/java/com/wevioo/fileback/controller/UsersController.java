package com.wevioo.fileback.controller;

import com.wevioo.fileback.message.ImageRequest;
import com.wevioo.fileback.model.User;
import com.wevioo.fileback.service.ImageService;
import com.wevioo.fileback.service.UserManagerLayer;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/users")
@ResponseBody
@AllArgsConstructor
public class UsersController {

    private final ImageService imageService;
    private final UserManagerLayer userManagerLayer;

    @PostMapping(path = "invite")
    public @ResponseBody
    ResponseEntity<?> inviteUser(@RequestBody String email) {
        return this.userManagerLayer.inviteUserByMail(email);
    }

    @GetMapping(path = "/list")
    public @ResponseBody
    Page<User> getUsersPaged(@RequestParam(defaultValue = "0") Integer page) {
        return this.userManagerLayer.paginateUsers(page);
    }

    @GetMapping(path = "count/all")
    public @ResponseBody
    Long getCountOfAllUsers() {
        return this.userManagerLayer.countAll();
    }

    @GetMapping(path = "count/jobbers")
    public @ResponseBody
    Long getCountOfJobbers() {
        return this.userManagerLayer.countJobbers();
    }

    @GetMapping(path = "count/normal")
    public @ResponseBody
    Long getCountOfNormalUsers() {
        return this.userManagerLayer.countUsers();
    }

    @GetMapping(path = "all")
    public List<User> getAllUsers() {
        return this.userManagerLayer.getAllUsers();
    }

    @GetMapping(path = "one/{id}")
    public @ResponseBody
    User getOneUser(@PathVariable Long id) {
        return this.userManagerLayer.getAUserById(id);
    }

    @GetMapping(path = "img/profile/{id}")
    public @ResponseBody
    ResponseEntity<?> getProfileImage(@PathVariable Long id) {
        return imageService.getProfileImage(id);
    }

    // ! This is for the profile picture only !!
    @PutMapping(path = "img/profile/{id}")
    public @ResponseBody
    ResponseEntity<?> updateProfilePicture(@RequestBody ImageRequest imgReq, @PathVariable Long id)
    {
        return imageService.updateProfilePicture(imgReq, id);
    }

    @PutMapping(path = "/disable/{id}")
    public @ResponseBody
    ResponseEntity<?> disableAccount(@PathVariable Long id) {
        return this.userManagerLayer.disableUser(id);
    }

    @PutMapping(path="update/{id}")
    public @ResponseBody
    ResponseEntity<?> updateProfile(@PathVariable Long id, @RequestBody User user) {
        return this.userManagerLayer.modifyProfile(id, user);
    }
}
