package com.wevioo.fileback.service;

import com.wevioo.fileback.exceptions.UserNotFoundException;
import com.wevioo.fileback.helper.Base64Treatment;
import com.wevioo.fileback.message.ImageRequest;
import com.wevioo.fileback.message.ImageResponse;
import com.wevioo.fileback.message.ResponseMessage;
import com.wevioo.fileback.model.User;
import com.wevioo.fileback.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ImageService {

    private final UserRepository userRepository;

    public ResponseEntity<?> updateProfilePicture(ImageRequest imgReq, Long id) {

        User u = this.collectUserIfExists(id);

        Base64Treatment base64Treatment = new Base64Treatment(imgReq.getImage());

        u.setPic(base64Treatment.compressBytes());

        userRepository.save(u);

        return ResponseEntity.ok(new ResponseMessage("Image sauvegardée avec succés !"));
    }

    public ResponseEntity<?> getProfileImage(Long id) {

        User u = this.collectUserIfExists(id);

        ImageResponse imgRep = new ImageResponse();

        if(u.getPic() != null) {

            Base64Treatment base64Treatment = new Base64Treatment(u.getPic());

            imgRep.setImage(base64Treatment.decompressBytes());
        }

        return ResponseEntity.ok(imgRep);
    }

    private User collectUserIfExists(Long id) {
        return userRepository
                .findById(id)
                .orElseThrow(
                        () -> new UserNotFoundException(id)
                );
    }
}
