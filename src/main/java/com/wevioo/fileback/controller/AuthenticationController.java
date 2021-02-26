package com.wevioo.fileback.controller;

import com.wevioo.fileback.message.JwtRequest;
import com.wevioo.fileback.message.ResponseMessage;
import com.wevioo.fileback.model.User;
import com.wevioo.fileback.service.LoginService;
import com.wevioo.fileback.service.RegisterService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping(path = "/")
@CrossOrigin(origins = "*")
@AllArgsConstructor
@ResponseBody
public class AuthenticationController {

    private final LoginService loginService;

    private final RegisterService regService;

    @GetMapping(path = "hello")
    public ResponseEntity<?> Greeting (){
        return ResponseEntity.ok(new ResponseMessage("hello everyone I am working fine"));
    }

    @PostMapping(path = "signIn")
    public ResponseEntity<?> loginUser(@RequestBody JwtRequest jwtRequest) throws Exception
    {

        return loginService.createAuthenticationToken(jwtRequest);
    }

    @PostMapping(path = "signUp")
    public ResponseEntity<?> registerUser(/*ModelAndView modelAndView,*/
                                          @RequestBody User user)
    {
        return regService.addUser(/*modelAndView,*/user);
    }

    @GetMapping(value="/confirm-account")
    public ResponseEntity<?> confirmUserAccount(/*ModelAndView modelAndView,*/
                                                @RequestParam("token")String confirmationToken)
    {
        return regService.confirmAccount(/*modelAndView,*/confirmationToken);
    }
}
