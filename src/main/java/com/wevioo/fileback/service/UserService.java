package com.wevioo.fileback.service;

import com.wevioo.fileback.model.User;
import com.wevioo.fileback.repository.UserRepository;
import com.wevioo.fileback.config.UserDetail;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUserName(username);

        if (user == null) {
            throw new UsernameNotFoundException("Utilisateur introuvable !");
        }

        return new UserDetail(user);
    }
}
