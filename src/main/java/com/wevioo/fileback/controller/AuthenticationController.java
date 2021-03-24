package com.wevioo.fileback.controller;

import com.wevioo.fileback.interfaces.Login;
import com.wevioo.fileback.interfaces.Register;
import com.wevioo.fileback.message.JwtRequest;
import com.wevioo.fileback.message.ResponseMessage;
import com.wevioo.fileback.model.User;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping(path = "/")
@AllArgsConstructor
@ResponseBody
public class AuthenticationController {

    private final Login login;

    private final Register register;

    @GetMapping(path = "hello")
    public ResponseEntity<?> Greeting (){
        return ResponseEntity.ok(new ResponseMessage("hello everyone I am working fine"));
    }

    @PostMapping(path = "signIn")
    public ResponseEntity<?> loginUser(@RequestBody JwtRequest jwtRequest) throws Exception
    {

        return login.createAuthenticationToken(jwtRequest);
    }

    @PostMapping(path = "signUp")
    public ResponseEntity<?> registerUser(@RequestBody User user)
    {
        return register.addUser(user);
    }


    @GetMapping(value="/confirm-account")
    public ModelAndView confirmUserAccount(ModelAndView modelAndView,
                                           @RequestParam("token")String confirmationToken)
    {
        return register.confirmAccount(modelAndView,confirmationToken);
    }
}
