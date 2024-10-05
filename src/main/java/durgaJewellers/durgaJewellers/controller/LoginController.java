package durgaJewellers.durgaJewellers.controller;

import durgaJewellers.durgaJewellers.dto.CustomError;
import durgaJewellers.durgaJewellers.dto.CustomLogin;
import durgaJewellers.durgaJewellers.dto.Response;
import durgaJewellers.durgaJewellers.model.User.User;
import durgaJewellers.durgaJewellers.service.UserDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@Slf4j
@RequiredArgsConstructor
public class LoginController {

    @Autowired
    UserDetailsService userDetailsService;

    @PostMapping("/login")
    public ResponseEntity<Response> login(@RequestBody User user) {
        log.info("Request for login");
        Response response;

         if(userDetailsService.loadUserByUsername(user)){
             log.info("Response Ok for login");
             response = new Response(new CustomLogin("success"));
         }
         else{
             log.info("Response Ok for login");
             response = new Response(new CustomError("failed"));
         }
        return new ResponseEntity<> (response,HttpStatus.OK);
    }
}

