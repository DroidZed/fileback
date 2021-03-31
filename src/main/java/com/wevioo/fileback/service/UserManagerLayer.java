package com.wevioo.fileback.service;

import com.wevioo.fileback.exceptions.UserNotFoundException;
import com.wevioo.fileback.geolocationClasses.DisplayLatLng;
import com.wevioo.fileback.interfaces.EmailManager;
import com.wevioo.fileback.interfaces.GeoCoder;
import com.wevioo.fileback.interfaces.ImageManager;
import com.wevioo.fileback.interfaces.LocationManager;
import com.wevioo.fileback.interfaces.UserManager;
import com.wevioo.fileback.message.ResponseMessage;
import com.wevioo.fileback.model.Locations;
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
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

import static java.text.MessageFormat.format;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

@AllArgsConstructor
@Service
public class UserManagerLayer implements UserManager {

    private final UserRepository userRepository;
    private final EmailManager emailManager;
    private final PasswordEncoder encoder;
    private final GeoCoder geoCoder;
    private final LocationManager locationManager;
    private final ImageManager imageManager;

    public List<User> getAllUsers()
    {
        return this.userRepository.getUsersOnly();
    }

    public User getAUserById(Long id)
    {
        return this.userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    public Page<User> paginateUsers(Integer page)
    {

        return this.userRepository.findAll(PageRequest.of(page, 7));
    }

    public Long countAll()
    {
        return this.userRepository.count();
    }

    public Long countUsers()
    {
        return this.userRepository.countUsers();
    }

    public Long countJobbers()
    {
        return this.userRepository.countTravailleurs();
    }

    @Override
    public void becomeJobber(Long id) {
        this.userRepository.findById(id)
                .map(u -> {
                            u.setTravailleur(Boolean.TRUE);
                            return this.userRepository.save(u);
                })
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @Transactional
    public ResponseEntity<?> disableUser(Long id)
    {

        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isEmpty())
            return ResponseEntity.badRequest().body(new ResponseMessage("Impossible de modifier l'état du compte !"));

        User u = optionalUser.get();

        u.setEtat(false);

        userRepository.save(u);

        return ResponseEntity.ok(new ResponseMessage(format("Le compte ID = {0} à été désactivé avec succés !", id)));
    }

    public ResponseEntity<?> inviteUserByMail(String email)
    {
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setTo(email);

        mailMessage.setSubject("Venez découvrire notre platforme !");

        mailMessage.setText(
                "On vous invite à découvrire notre plateforme de recherche de services à l'adresse : http://localhost:4200");

        emailManager.sendEmail(mailMessage);

        return ResponseEntity.ok(new ResponseMessage("Email envoyé"));
    }

    @Transactional
    public ResponseEntity<?> modifyProfile(Long id, User user)
    {
        Optional<User> opt = this.userRepository.findById(id);

        if (opt.isPresent())
        {
            User data = opt.get();

            data.setFullName(user.getFullName());

            data.setTel(user.getTel());

            data.setAdresse(user.getAdresse());

            try
            {
                DisplayLatLng LatLng = geoCoder.getAddressCoded(user.getAdresse()).get();

                if(LatLng != null) {
                    Locations loc = data.getLocation();

                    loc.setLatitude(LatLng.lat);

                    loc.setLongitude(LatLng.lng);

                    this.locationManager.updateLocation(loc.getIdLoc(), loc);
                }
            }
            
            catch (IOException | ExecutionException | InterruptedException e)
            {
                e.printStackTrace();
            }

            data.setPasswordUser(encoder.encode(user.getPasswordUser()));

            return ResponseEntity.ok(userRepository.save(data));
        }

        else return ResponseEntity.badRequest().body(new ResponseMessage("Could not update profile !"));
    }

    public ResponseEntity<?> updateProfilePicture(MultipartFile imgreq, Long id) {

        Optional<User> opt = this.userRepository.findById(id);

        if(opt.isEmpty())
            return ResponseEntity.badRequest().body("User could not be found !");

        User u = opt.get();

        String imageName = imgreq.getOriginalFilename();

        if (u.getImageName() != null) {
            imageName = u.getImageName();
        }

        else {
            u.setImageName(imageName);
        }

        this.userRepository.save(u);

       this.imageManager.uploadToLocalFileSystem(imgreq,"user", imageName);

        return ResponseEntity.ok(new ResponseMessage("Okay !"));
    }

    public ResponseEntity<?> getProfileImage(Long id) throws IOException {

        Optional<User> opt = this.userRepository.findById(id);

        if(opt.isEmpty())
            return ResponseEntity.badRequest().body("User could not be found !");

        String name = opt.get().getImageName();

        if(name == null)
            return ResponseEntity.badRequest().body("Image not found !");

        return ResponseEntity.ok(imageManager.getImageWithMediaType(name,"user"));
    }
}
