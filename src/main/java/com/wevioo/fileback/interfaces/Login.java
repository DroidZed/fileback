package com.wevioo.fileback.interfaces;

import com.wevioo.fileback.message.JwtRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface Login {

    ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest jwtRequest) throws Exception;
}
