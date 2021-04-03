package com.wevioo.fileback.service;

import com.wevioo.fileback.interfaces.Login;
import com.wevioo.fileback.message.JwtRequest;
import com.wevioo.fileback.message.JwtResponse;
import com.wevioo.fileback.config.UserDetail;
import com.wevioo.fileback.utility.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoginService implements Login {

    private final AuthenticationManager authenticationManager;

    private final UserService userService;

    private final JwtUtil jwtUtil;

    public ResponseEntity<?> createAuthenticationToken(JwtRequest jwtRequest) throws Exception {
        try
        {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            jwtRequest.getUsername(),
                            jwtRequest.getPassword()
                    )
            );
        }
        catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }

        final UserDetail userDetails = (UserDetail) userService.loadUserByUsername(jwtRequest.getUsername());

        final String token = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(userDetails.getStatus(),userDetails.getId(),userDetails.getIsAdmin(),token));
    }
}
