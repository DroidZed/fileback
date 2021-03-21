package com.wevioo.fileback.service;

import com.wevioo.fileback.exceptions.UserNotFoundException;
import com.wevioo.fileback.geolocationClasses.DisplayLatLng;
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

@AllArgsConstructor
@Service
public class UserManagerLayer {

    private final UserRepository userRepository;
    private final EmailService emailService;
    private final PasswordEncoder encoder;
    private final GeoCoderService geoCoderService;
    private final LocationService locationService;
    private final ImageService imageService;

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

        emailService.sendEmail(mailMessage);

        return ResponseEntity.ok(new ResponseMessage("Email envoyé"));
    }

    @Transactional
    public ResponseEntity<?> modifyProfile(Long id, User user)
    {
        Optional<User> opt = this.userRepository.findById(id);

        if (opt.isPresent())
        {
            User data = opt.get();

            data.setUserName(user.getUserName());

            data.setFullName(user.getFullName());

            data.setTel(user.getTel());

            data.setAdresse(user.getAdresse());

            try
            {
                DisplayLatLng LatLng = geoCoderService.getAddressCoded(user.getAdresse());

                if(LatLng != null) {
                    Locations loc = data.getLocation();

                    loc.setLatitude(LatLng.lat);

                    loc.setLongitude(LatLng.lng);

                    this.locationService.updateLocation(loc.getIdLoc(), loc);
                }
            }
            
            catch (IOException e)
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

        ResponseEntity<?> image = this.imageService.uploadToLocalFileSystem(imgreq,"users", "user", imgreq.getOriginalFilename());

        System.out.println(imgreq.getOriginalFilename());

        u.setImageName(imgreq.getOriginalFilename());

        this.userRepository.save(u);

        return image;
    }

    public ResponseEntity<?> getProfileImage(Long id) throws IOException {

        Optional<User> opt = this.userRepository.findById(id);

        if(opt.isEmpty())
            return ResponseEntity.badRequest().body("User could not be found !");

        String name = opt.get().getImageName();

        if(name == null)
            return ResponseEntity.badRequest().body("Image not found !");

        return ResponseEntity.ok(imageService.getImageWithMediaType(name,"user"));
    }
}
