package com.project.authentication.service;

import com.project.authentication.domain.User;

import java.util.List;

public interface UserService {
    List<User> getUser();

    User addUser(User user);

    User authCheck(String email, String pass);

    String findRoleUsingEmail(String email);

}
