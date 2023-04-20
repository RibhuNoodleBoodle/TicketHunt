package com.project.authentication.service;

import com.project.authentication.domain.User;

import java.util.Map;

public interface UserSecurityTokenGenerator {
    Map<String, String> tokenGenerator(User user);
}
