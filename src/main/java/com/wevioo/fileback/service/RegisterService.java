package com.wevioo.fileback.service;

import com.wevioo.fileback.geolocationClasses.DisplayLatLng;
import com.wevioo.fileback.message.ResponseMessage;
import com.wevioo.fileback.model.Category;
import com.wevioo.fileback.model.ConfirmationToken;
import com.wevioo.fileback.model.Locations;
import com.wevioo.fileback.model.User;
import com.wevioo.fileback.repository.CategoryRepository;
import com.wevioo.fileback.repository.ConfirmationTokenRepository;
import com.wevioo.fileback.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class RegisterService {

    private final UserRepository userRepository;

    private final CategoryRepository categoryRepository;

    private final ConfirmationTokenRepository confirmationTokenRepository;

    private final EmailService emailService;

    private final PasswordEncoder encoder;

    private final GeoCoderService geoCoderService;

    public ResponseEntity<?> addUser(User user) {

        User existingUser = userRepository.findByEmail(user.getEmail());

        if(existingUser != null)
        {
            return ResponseEntity
                .badRequest()
                .body(new ResponseMessage("Error: Account already exists!"));
        }
        else
        {
            user.setPasswordUser(encoder.encode(user.getPasswordUser()));

            this.setLocation(user);

            userRepository.save(user);
            
            this.sendMailAndSaveToken(user);
        }

        return ResponseEntity.ok(new ResponseMessage("Vérifier votre boite mail pour un lien de confirmation !"));
    }

    public ModelAndView confirmAccount(ModelAndView modelAndView,String confirmationToken) {

        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

        if(token != null)
        {
            User user = userRepository.findByEmail(token.getUser().getEmail());

            user.setEtat(true);

            userRepository.save(user);

            modelAndView.setViewName("accountVerified");

        }
        else
        {
            modelAndView.setViewName("error");
            modelAndView.addObject("message","The link is invalid or broken!");
        }
        return modelAndView;
    }

    public ResponseEntity<?> addJobber(Long cat_id, User user) {

        User existingUser = userRepository.findByEmail(user.getEmail());

        if(existingUser != null)
        {
            return ResponseEntity
                    .badRequest()
                    .body(new ResponseMessage("Error: Account already exists!"));
        }
        else {

            Optional<Category> catOptional = categoryRepository.findById(cat_id);
            if (catOptional.isPresent()) {
                Category cat = catOptional.get();

                user.getActivity().setCategory(cat);

                user.setPasswordUser(encoder.encode(user.getPasswordUser()));

                this.setLocation(user);

                this.userRepository.save(user);

                this.sendMailAndSaveToken(user);
            }

        }
        return ResponseEntity.ok(new ResponseMessage("Vérifier votre boite mail pour un lien de confirmation !"));
    }

    private void sendMailAndSaveToken(User user) {
        ConfirmationToken confirmationToken = new ConfirmationToken(user);

        confirmationTokenRepository.save(confirmationToken);

        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Complete Registration!");

        mailMessage.setText("To confirm your account, please click here : "
                +"http://localhost:8080/confirm-account?token="+confirmationToken.getConfirmationToken());

        emailService.sendEmail(mailMessage);
    }


    private void setLocation(User user)
    {
        try
        {
            DisplayLatLng latlng = geoCoderService.getAddressCoded(user.getAdresse());
            user.setLocation(new Locations(latlng.lat,latlng.lng));
        }

        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}
