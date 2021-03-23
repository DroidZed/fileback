package com.wevioo.fileback.config;

import com.wevioo.fileback.model.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
// import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.util.Collection;
// import java.util.Collections;
@AllArgsConstructor
public class UserDetail implements UserDetails {

    @Serial
    private static final long serialVersionUID = 1L;
    private final User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //return Collections.singleton(new SimpleGrantedAuthority(utilisateur.getRole()));
      return null;
    }

    public Long getId() { return user.getIdUser(); }

    public Boolean getStatus() { return user.getTravailleur(); }

    public Boolean getIsAdmin() { return user.getIsAdmin(); }

    @Override
    public String getPassword() {
        return user.getPasswordUser();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return user.getEtat();
    }
}

