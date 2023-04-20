/*
 * Author Name: Mohini
 * Date: 12/27/2022
 * Created With: IntelliJ IDEA Community Edition
 */
package com.niit.jdp.userservice.controller;

import com.niit.jdp.userservice.domain.User;
import com.niit.jdp.userservice.exception.UserAlreadyExistsException;
import com.niit.jdp.userservice.exception.UserNotFoundException;
import com.niit.jdp.userservice.rabbitmqproducer.CommonUser;
import com.niit.jdp.userservice.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/userData/")
//@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
    private IUserService iUserService;

    @Autowired
    public UserController(IUserService iUserService) {
        this.iUserService = iUserService;
    }

    @PostMapping("addUser")
    public ResponseEntity<?> add(@RequestBody User user) throws UserAlreadyExistsException {
        ResponseEntity responseEntity = null;
        try {
            responseEntity = new ResponseEntity<>(iUserService.addUser(user), HttpStatus.CREATED);
        } catch (UserAlreadyExistsException e) {
            throw new UserAlreadyExistsException();
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @GetMapping("allUser")
    public ResponseEntity<?> fetchAllUser() {
        ResponseEntity responseEntity = null;
        try {
            responseEntity = new ResponseEntity(iUserService.getAllUser(), HttpStatus.OK);
        } catch (Exception exception) {
            responseEntity = new ResponseEntity(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @GetMapping("email/{email}")
    public ResponseEntity<?> fetchUserByEmail(@PathVariable String email) throws UserNotFoundException {
        ResponseEntity responseEntity = null;
        try {
            responseEntity = new ResponseEntity(iUserService.findByEmail(email), HttpStatus.OK);
        } catch (UserNotFoundException exception) {
            throw new UserNotFoundException();
        } catch (Exception e) {
            responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @PutMapping("update/{email}")
    public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable String email) throws UserNotFoundException {
        return new ResponseEntity<>(iUserService.updateUser(email, user), HttpStatus.OK);
    }

    @PostMapping("commonUser")
    public ResponseEntity<?> addUserDetails(@RequestBody CommonUser commonUser) {
        return new ResponseEntity<>(iUserService.addUser1(commonUser), HttpStatus.OK);
    }

}
