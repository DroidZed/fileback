package com.wevioo.fileback.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

import com.wevioo.fileback.geolocationClasses.DisplayLatLng;
import com.wevioo.fileback.interfaces.EmailManager;
import com.wevioo.fileback.interfaces.GeoCoder;
import com.wevioo.fileback.interfaces.Register;
import com.wevioo.fileback.message.ResponseMessage;
import com.wevioo.fileback.model.Category;
import com.wevioo.fileback.model.ConfirmationToken;
import com.wevioo.fileback.model.Locations;
import com.wevioo.fileback.model.User;
import com.wevioo.fileback.repository.CategoryRepository;
import com.wevioo.fileback.repository.ConfirmationTokenRepository;
import com.wevioo.fileback.repository.UserRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

@Service
@AllArgsConstructor
@Transactional
public class RegisterService implements Register {

    private final UserRepository userRepository;

    private final CategoryRepository categoryRepository;

    private final ConfirmationTokenRepository confirmationTokenRepository;

    private final EmailManager emailManager;

    private final PasswordEncoder encoder;

    private final GeoCoder geoCoder;

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

        if(token != null && !token.getExpiresAt().isBefore(LocalDateTime.now()))
        {
            User user = userRepository.findByEmail(token.getUser().getEmail());

            user.setEtat(true);

            token.setConfirmedAt(LocalDateTime.now());

            confirmationTokenRepository.save(token);

            userRepository.save(user);

            modelAndView.setViewName("accountVerified");

        }
        else
        {
            modelAndView.setViewName("errorpage");
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

            if (catOptional.isPresent())
            {
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

    @SneakyThrows
    private void sendMailAndSaveToken(User user) {
        ConfirmationToken confirmationToken = new ConfirmationToken(user);

        confirmationTokenRepository.save(confirmationToken);

        emailManager.sendConfirmationMail(user.getEmail(), confirmationToken.getConfirmationToken());
    }

    private void setLocation(User user)
    {
        try
        {
            DisplayLatLng latlng = geoCoder.getAddressCoded(user.getAdresse()).get();
            user.setLocation(new Locations(latlng.lat,latlng.lng));
        }

        catch (IOException | InterruptedException | ExecutionException e)
        {
            e.printStackTrace();
        }
    }

}
