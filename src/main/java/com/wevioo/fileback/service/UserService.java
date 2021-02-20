package com.wevioo.fileback.service;

import com.wevioo.fileback.model.User;
import com.wevioo.fileback.repository.UserRepository;
import com.wevioo.fileback.securityConfig.UserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username);
        if (user  == null){
            throw new UsernameNotFoundException("user not found in data base ");
        }
        return  new UserDetail(user) ;
    }
}
