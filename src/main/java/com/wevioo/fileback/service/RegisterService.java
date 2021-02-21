package com.wevioo.fileback.service;

import com.wevioo.fileback.message.ResponseMessage;
import com.wevioo.fileback.model.ConfirmationToken;
import com.wevioo.fileback.model.User;
import com.wevioo.fileback.repository.ConfirmationTokenRepository;
import com.wevioo.fileback.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

@Service
@AllArgsConstructor
public class RegisterService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    private final EmailService emailService;

    @Autowired
    PasswordEncoder encoder;

    public ResponseEntity<?> addUser(ModelAndView modelAndView, User user) {

        User existingUser = userRepository.findByEmail(user.getEmail());

        if(existingUser != null)
        {
            modelAndView.addObject("message","This email already exists!");
            modelAndView.setViewName("error");
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

            modelAndView.addObject("email", user.getEmail());

            modelAndView.setViewName("successfulRegistration");
        }

        return ResponseEntity.ok(new ResponseMessage("Activate your account !"));
    }

    @Transactional
    public ModelAndView confirmAccount(ModelAndView modelAndView, String confirmationToken) {
        System.out.println("Before token confirmation");
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

        System.out.println("After retrieving token");
        System.out.println(token);

        if(token != null)
        {
            User user = userRepository.findByEmail(token.getUser().getEmail());
            user.setEtat(true);
            userRepository.save(user);
            modelAndView.setViewName("accountVerified");
        }
        else
        {
            modelAndView.addObject("message","The link is invalid or broken!");
            modelAndView.setViewName("error");
        }

        return modelAndView;
    }

}
