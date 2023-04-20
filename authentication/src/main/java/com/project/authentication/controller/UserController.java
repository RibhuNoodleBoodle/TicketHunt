package com.project.authentication.controller;

import com.project.authentication.domain.User;
import com.project.authentication.service.UserSecurityTokenGenerator;
import com.project.authentication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/niit/auth/")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserSecurityTokenGenerator userSecurityTokenGenerator;

    @GetMapping("get")
    public ResponseEntity<?> getAllUser() {
        return new ResponseEntity<>(userService.getUser(), HttpStatus.FOUND);
    }

    @PostMapping("register")
    public ResponseEntity<?> insertUser(@RequestBody User user) {
        return new ResponseEntity<>(userService.addUser(user), HttpStatus.CREATED);
    }

    @PostMapping("login")
    public ResponseEntity<?> userAuth(@RequestBody User user) {
        User userObj = userService.authCheck(user.getEmail(), user.getPassword());

        if (userObj != null) {
            Map<String, String> key = userSecurityTokenGenerator.tokenGenerator(user);
            return new ResponseEntity<>(key, HttpStatus.OK);
        } else {

            return new ResponseEntity<>("User not authorized", HttpStatus.NOT_FOUND);
        }
    }
}
