package com.wevioo.fileback.controller;

import com.wevioo.fileback.message.JwtRequest;
import com.wevioo.fileback.message.JwtResponse;
import com.wevioo.fileback.model.User;
import com.wevioo.fileback.securityConfig.UserDetail;
import com.wevioo.fileback.service.RegisterService;
import com.wevioo.fileback.service.UserService;
import com.wevioo.fileback.utility.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping(path = "/")
@CrossOrigin(origins ="*")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;


    @Autowired
    private RegisterService regService;

    @GetMapping(path = "hello")
    public String Greeting (){
        return "hello everyone i'am working fine";
    }

    @PostMapping(path = "signIn")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }
        final UserDetail userDetails = (UserDetail) userService.loadUserByUsername(jwtRequest.getUsername());
        final String token = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(userDetails.getStatus(),userDetails.getId(),token));

    }

    @PostMapping(path = "signUp")
    public String registerUser(ModelAndView modelAndView,@RequestBody User user)
    {
        return regService.addUser(modelAndView, user);
    }

    @RequestMapping(value="/confirm-account", method= {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView confirmUserAccount(ModelAndView modelAndView, @RequestParam("token")String confirmationToken)
    {
        return regService.confirmAccount(modelAndView, confirmationToken);
    }






}
