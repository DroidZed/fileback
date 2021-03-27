package com.wevioo.fileback.controller;

import com.wevioo.fileback.interfaces.UserManager;
import com.wevioo.fileback.model.User;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path = "/users")
@AllArgsConstructor
public class UsersController {

    private final UserManager userManagerLayer;

    @PostMapping(path = "invite")
    public ResponseEntity<?> inviteUser(@RequestBody String email) {
        return this.userManagerLayer.inviteUserByMail(email);
    }

    @GetMapping(path = "/list")
    public Page<User> getUsersPaged(@RequestParam(defaultValue = "0") Integer page) {
        return this.userManagerLayer.paginateUsers(page);
    }

    @GetMapping(path = "count/all")
    public Long getCountOfAllUsers() {
        return this.userManagerLayer.countAll();
    }

    @GetMapping(path = "count/jobbers")
    public Long getCountOfJobbers() {
        return this.userManagerLayer.countJobbers();
    }

    @GetMapping(path = "count/normal")
    public Long getCountOfNormalUsers() {
        return this.userManagerLayer.countUsers();
    }

    @GetMapping(path = "all")
    public List<User> getAllUsers() {
        return this.userManagerLayer.getAllUsers();
    }

    @GetMapping(path = "one/{id}")
    public User getOneUser(@PathVariable Long id) {
        return this.userManagerLayer.getAUserById(id);
    }

    @GetMapping(path = "/image/get/{id}", produces = {MediaType.IMAGE_JPEG_VALUE})
    public ResponseEntity<?> getProfileImage(@PathVariable Long id) throws IOException {
        return this.userManagerLayer.getProfileImage(id);
    }

    @PostMapping(value = "profile/{id}/img")
    public ResponseEntity<?> updateProfilePicture(@PathVariable Long id, @RequestBody MultipartFile file) {
        return this.userManagerLayer.updateProfilePicture(file, id);
    }

    @PutMapping(path = "/disable/{id}")
    public ResponseEntity<?> disableAccount(@PathVariable Long id) {
        return this.userManagerLayer.disableUser(id);
    }

    @PutMapping(path = "update/{id}")
    public ResponseEntity<?> updateProfile(@PathVariable Long id, @RequestBody User user) {
        return this.userManagerLayer.modifyProfile(id, user);
    }
}
