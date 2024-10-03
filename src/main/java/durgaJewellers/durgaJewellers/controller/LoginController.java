package durgaJewellers.durgaJewellers.controller;

import durgaJewellers.durgaJewellers.model.User.User;
import durgaJewellers.durgaJewellers.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class LoginController {

    @Autowired
    UserDetailsService userDetailsService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        ResponseEntity<String> token = userDetailsService.loadUserByUsername(user);
        return new ResponseEntity<>(String.valueOf(token), HttpStatus.OK);
    }
}

