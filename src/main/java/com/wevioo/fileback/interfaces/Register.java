package com.wevioo.fileback.interfaces;

import com.wevioo.fileback.model.User;

import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.ModelAndView;

public interface Register {

    ResponseEntity<?> addUser(User user);
    ModelAndView confirmAccount(ModelAndView modelAndView,String confirmationToken);
    ResponseEntity<?> addJobber(Long cat_id, User user);
}
