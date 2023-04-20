package com.project.authentication.service;

import com.project.authentication.domain.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserSecurityGeneratorImpl implements UserSecurityTokenGenerator {

    @Autowired
    UserService userService;

    @Override
    public Map<String, String> tokenGenerator(User user) {
        Map<String, String> map = new HashMap<>();
        String jwtToken = Jwts.builder().setIssuer("myApp")
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS512, "mysecret")
                .compact();
        Map<String, Object> map2 = new HashMap<>();
        map.put("token", jwtToken);
        map.put("userEmail", String.valueOf(user));
        map.put("role", userService.findRoleUsingEmail(user.getEmail()));
        map.put("Message", "LoggedIn successfully");
        map2.put("user", user);
        return map;
    }
}
