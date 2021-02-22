package com.wevioo.fileback.service;

import com.wevioo.fileback.message.ResponseMessage;
import com.wevioo.fileback.model.ConfirmationToken;
import com.wevioo.fileback.model.User;
import com.wevioo.fileback.repository.ConfirmationTokenRepository;
import com.wevioo.fileback.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
// import org.springframework.web.servlet.ModelAndView;

@Service
@AllArgsConstructor
public class RegisterService {

    private final UserRepository userRepository;

    private final ConfirmationTokenRepository confirmationTokenRepository;

    private final EmailService emailService;

    private final PasswordEncoder encoder;

    public ResponseEntity<?> addUser(/*ModelAndView modelAndView,*/ User user) {

        User existingUser = userRepository.findByEmail(user.getEmail());

        if(existingUser != null)
        {
           // modelAndView.addObject("message","This email already exists!");
           // modelAndView.setViewName("error");
            return ResponseEntity
                .badRequest()
                .body(new ResponseMessage("Error: Account already exists!"));
        }
        else
        {
            user.setPasswordUser(encoder.encode(user.getPasswordUser()));
            userRepository.save(user);

            ConfirmationToken confirmationToken = new ConfirmationToken(user);

            confirmationTokenRepository.save(confirmationToken);

            SimpleMailMessage mailMessage = new SimpleMailMessage();

            mailMessage.setTo(user.getEmail());
            mailMessage.setSubject("Complete Registration!");

            mailMessage.setText("To confirm your account, please click here : "
                    +"http://localhost:8080/confirm-account?token="+confirmationToken.getConfirmationToken());

            emailService.sendEmail(mailMessage);

            // modelAndView.addObject("email", user.getEmail());

           // modelAndView.setViewName("successfulRegistration");
        }

        return ResponseEntity.ok(new ResponseMessage("Vérifier votre boite mail pour un lien de confirmation !"));
    }

    @Transactional
    public ResponseEntity<?> confirmAccount(/*ModelAndView modelAndView,*/ String confirmationToken) {

        String message;

        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

        if(token != null)
        {
            User user = userRepository.findByEmail(token.getUser().getEmail());

            user.setEtat(true);

            userRepository.save(user);

            //modelAndView.setViewName("accountVerified");

            message = "Compte confirmé !";
        }
        else
        {
            return ResponseEntity.badRequest().body(new ResponseMessage("Lien invalide !"));
        }
        return ResponseEntity.ok(new ResponseMessage(message));
    }

}
