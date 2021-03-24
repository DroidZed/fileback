package com.wevioo.fileback.interfaces;

import com.wevioo.fileback.model.User;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface UserManager {

    List<User> getAllUsers();
    User getAUserById(Long id);
    Page<User> paginateUsers(Integer page);
    Long countAll();
    Long countUsers();
    Long countJobbers();
    ResponseEntity<?> disableUser(Long id);
    ResponseEntity<?> inviteUserByMail(String email);
    ResponseEntity<?> modifyProfile(Long id, User user);
    ResponseEntity<?> updateProfilePicture(MultipartFile imgreq, Long id);
    ResponseEntity<?> getProfileImage(Long id) throws IOException;
}
