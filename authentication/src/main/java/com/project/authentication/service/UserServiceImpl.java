package com.project.authentication.service;

import com.project.authentication.domain.User;
import com.project.authentication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JavaMailSender javaMailSender;


    @Override
    public List<User> getUser() {
        return userRepository.findAll();
    }

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User authCheck(String email, String pass) {
        if (userRepository.findById(email).isPresent()) {
            User user = userRepository.findById(email).get();
            if (user.getPassword().equals(pass)) {
                SimpleMailMessage msg = new SimpleMailMessage();
                msg.setTo(email);
                msg.setSubject("Login");
                msg.setText("Welcome To TICKET HUNT...\n\nHEY! You have successfully Logged In.We look forward to serving you real soon.\n \n" +
                        "In case of any technical difficulty do drop us a mail at tickethunt7@gmail.com \n \nThanks! \nTeam TICKET HUNT");
                javaMailSender.send(msg);
                return user;
            } else {
                return null;
            }
        } else {
            return null;
        }
        // User user = userRepository.findById(email).get();

    }

    @Override
    public String findRoleUsingEmail(String email) {
        User user = userRepository.findById(email).get();
        return user.getRole();
    }

}
