package com.wevioo.fileback.service;

import com.wevioo.fileback.exceptions.UserNotFoundException;
import com.wevioo.fileback.helper.Base64Treatment;
import com.wevioo.fileback.message.ResponseMessage;
import com.wevioo.fileback.model.User;
import com.wevioo.fileback.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static java.text.MessageFormat.format;

@AllArgsConstructor
@Service
public class UserManagerLayer {

    private final UserRepository userRepository;
    private final EmailService emailService;
    private final PasswordEncoder encoder;

    public List<User> getAllUsers() {

        List<User> users = this.userRepository.findAll();

        Base64Treatment base64Treatment = new Base64Treatment();

        for (User u : users)
            if (u.getPic() != null) {
                base64Treatment.setBase64String(u.getPic());
                u.setPic(base64Treatment.decompressBytes());
            }

        return users;
    }

    public User getAUserById(Long id) {
        return this.userRepository
                .findById(id)
                .map(
                        data ->
                        {
                            data.setPic(null);
                            return data;
                        }
                )
                .orElseThrow(
                        () -> new UserNotFoundException(id)
                );
    }

    public Page<User> paginateUsers(Integer page) {
        return this
                .userRepository
                .findAll(
                        PageRequest.of(page, 7)
                );
    }

    public Long countAll() {
        return this.userRepository.count();
    }

    public Long countUsers() {
        return this.userRepository.countUsers();
    }

    public Long countJobbers() {
        return this.userRepository.countTravailleurs();
    }

    @Transactional
    public ResponseEntity<?> disableUser(Long id) {

        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isEmpty())
            return ResponseEntity
                    .badRequest()
                    .body(
                            new ResponseMessage(
                                    "Impossible de modifier l'état du compte !")
                    );

        User u = optionalUser.get();

        u.setEtat(false);

        userRepository.save(u);

        return ResponseEntity
                .ok(new ResponseMessage(
                        format("Le compte ID = {0} à été {1} avec succés !",
                                id,
                                "désactivé")
                ));
    }

    public ResponseEntity<?> inviteUserByMail(String email) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setTo(email);

        mailMessage.setSubject("Venez découvrire notre platforme !");

        mailMessage.setText("On vous invite à découvrire notre plateforme de recherche de services à l'adresse : http://localhost:4200");

        emailService.sendEmail(mailMessage);

        return ResponseEntity.ok(new ResponseMessage("Email envoyé"));
    }

    @Transactional
    public ResponseEntity<?> modifyProfile(Long id, User user) {
        return this.userRepository
                .findById(id)
                .map(data -> {
                    data.setFullName(user.getFullName());
                    data.setTel(user.getTel());
                    data.setTravailleur(user.getTravailleur());
                    data.setAdresse(user.getAdresse());
                    data.setPasswordUser(encoder.encode(user.getPasswordUser()));
                    return ResponseEntity.ok(userRepository.save(data));
                })
                .orElseThrow(() -> new UserNotFoundException(id));
    }
}
