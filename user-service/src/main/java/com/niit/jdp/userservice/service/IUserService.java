package com.niit.jdp.userservice.service;

import com.niit.jdp.userservice.domain.User;
import com.niit.jdp.userservice.exception.UserAlreadyExistsException;
import com.niit.jdp.userservice.exception.UserNotFoundException;
import com.niit.jdp.userservice.rabbitmqproducer.CommonUser;

import java.util.List;

public interface IUserService {

    User addUser(User user) throws UserAlreadyExistsException;

    List<User> getAllUser();

    User findByEmail(String email) throws UserNotFoundException;

    User updateUser(String email, User user) throws UserNotFoundException;

    User addUser1(CommonUser commonUser);

}
