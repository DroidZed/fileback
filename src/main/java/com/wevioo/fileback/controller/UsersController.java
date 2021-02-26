package com.wevioo.fileback.controller;

import com.wevioo.fileback.exceptions.UserNotFoundException;
import com.wevioo.fileback.message.ResponseMessage;
import com.wevioo.fileback.model.User;
import com.wevioo.fileback.repository.UserRepository;
import com.wevioo.fileback.service.EmailService;
import com.wevioo.fileback.service.ImageService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path = "/users")
@CrossOrigin(origins = "*")
@ResponseBody
@AllArgsConstructor
public class UsersController {

    private final UserRepository userRepository;
    private final ImageService imageService;
    private final EmailService emailService;

    @PostMapping(path = "add")
    public void addUser(@RequestBody User user){
        this.userRepository.save(user);
    }

    @GetMapping(path = "all")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // TODO: a reviser pour le prochain sprint !
    @PutMapping(path = "update/{id}")
    public @ResponseBody ResponseEntity<?> updateUser(@PathVariable Long id,
                                                      @RequestBody User newUser) {
        User oldUser = userRepository
                .findById(id)
                .map(data ->
                {
                    data.setAdresse(newUser.getAdresse());

                    // data.setPasswordUser(newUser.getPasswordUser());

                    data.setFullName(newUser.getFullName());

                    data.setTel(newUser.getTel());

                    data.setActivity(newUser.getActivity());

                    return userRepository.save(data);
                }
        )
                .orElseThrow(() -> new UserNotFoundException(id));

        return ResponseEntity.ok(oldUser);
    }

    // ! modif mt3 taswira kahaw !!!!
    @PutMapping(path = "img/profile/{id}")
    public @ResponseBody ResponseEntity<?> updateProfilePicture(@RequestBody MultipartFile img,
                                                  @PathVariable Long id)
            throws IOException {
        return imageService.updateProfilePicture(img, id);
    }

    @PostMapping(path = "invite")
    public @ResponseBody ResponseEntity<?> inviteUser(@RequestBody String email) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(email);
        mailMessage.setSubject("Venez découvrire notre platforme !");
        mailMessage.setText("On vous invite à découvrire notre plateforme de recherche de services à l'adresse : http://localhost:4200");
        emailService.sendEmail(mailMessage);
        return ResponseEntity.ok(new ResponseMessage("Email envoyé"));
    }
}
