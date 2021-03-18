package com.wevioo.fileback.service;

import com.wevioo.fileback.helper.Base64Treatment;
import com.wevioo.fileback.message.ResponseMessage;
import com.wevioo.fileback.model.Services;
import com.wevioo.fileback.model.User;
import com.wevioo.fileback.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class JobberService {

    private final UserRepository userRepository;

    public ResponseEntity<?> addServiceToJobber(Long id_jobber, Services serv)
    {
       Optional<User> opt = userRepository.findById(id_jobber);

       if (opt.isEmpty())
       {
            return ResponseEntity
                    .badRequest()
                    .body(new ResponseMessage("Could not assign service !"));
       }

       User u = opt.get();
       u.setServices(serv);
       this.userRepository.save(u);

        return ResponseEntity
                .ok(new ResponseMessage("Service added successfully !"));
    }

    public List<User> selectOnlyJobbers()
    {
       List<User> lu = this.userRepository.getAllJobbers();

        Base64Treatment base64Treatment = new Base64Treatment();

        for (User u : lu)
            if (u.getPic() != null)
            {
                base64Treatment.setBase64String(u.getPic());
                u.setPic(base64Treatment.decompressBytes());
            }

       return lu;
    }
}
