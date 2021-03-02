package com.wevioo.fileback.controller;

import com.wevioo.fileback.exceptions.UserNotFoundException;
import com.wevioo.fileback.message.ResponseMessage;
import com.wevioo.fileback.model.User;
import com.wevioo.fileback.repository.UserRepository;
import com.wevioo.fileback.service.EmailService;
import com.wevioo.fileback.service.ImageService;
import com.wevioo.fileback.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static java.text.MessageFormat.*;


@RestController
@RequestMapping(path = "/users")
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@ResponseBody
@AllArgsConstructor
public class UsersController {

    private final UserRepository userRepository;
    private final ImageService imageService;
    private final EmailService emailService;
    private final UserService userService;

    @PostMapping(path = "add")
    public void addUser(@RequestBody User user) {
        this.userRepository.save(user);
    }

    @GetMapping(path = "all")
    public List<User> getAllUsers() {
        return this.userService.getAllUsers();
    }

    @GetMapping(path = "one/{id}")
    public @ResponseBody User getOneUser(@PathVariable Long id) {
        return this.userRepository.findById(id).map(data -> {
            data.setPic(null);
            return data;
        }).orElseThrow(()-> new UserNotFoundException(id));
    }

    // ! modif mt3 taswira kahaw !!!!
    @PutMapping(path = "img/profile/{id}")
    public @ResponseBody ResponseEntity<?> updateProfilePicture(@RequestBody MultipartFile img, @PathVariable Long id)
            throws IOException {
        return imageService.updateProfilePicture(img, id);
    }

    @PostMapping(path = "invite")
    public @ResponseBody ResponseEntity<?> inviteUser(@RequestBody String email) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(email);
        mailMessage.setSubject("Venez découvrire notre platforme !");
        mailMessage.setText(
                "On vous invite à découvrire notre plateforme de recherche de services à l'adresse : http://localhost:4200");
        emailService.sendEmail(mailMessage);
        return ResponseEntity.ok(new ResponseMessage("Email envoyé"));
    }

    @PutMapping(path = "/disable/{id}")
    public @ResponseBody ResponseEntity<?> disableAccount(@PathVariable Long id) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isEmpty())
            return ResponseEntity.badRequest().body(new ResponseMessage("Impossible de modifier l'état du compte !"));

        User u = optionalUser.get();

        u.setEtat(false);

        userRepository.save(u);

        return ResponseEntity
                .ok(new ResponseMessage(format("Le compte ID = {0} à été {1} avec succés !", id, "désactivé")));
    }

    @GetMapping(path="/list")
    public @ResponseBody Page<User> getUsersPaged(@RequestParam(defaultValue = "0") Integer page) {
        return this.userRepository.findAll(PageRequest.of(page,5));
    }

    @GetMapping(path="count/all")
    public @ResponseBody Long getCountOfAllUsers() {
        return this.userRepository.count();
    }

    @GetMapping(path = "count/jobbers")
    public @ResponseBody Long getCountOfJobbers() {
        return this.userRepository.countTravailleurs();
    }

    @GetMapping(path = "count/normal")
    public @ResponseBody Long getCountOfNormalUsers() {
        return this.userRepository.countUsers();
    }

}
