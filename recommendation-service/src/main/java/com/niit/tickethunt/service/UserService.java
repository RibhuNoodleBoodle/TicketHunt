package com.niit.tickethunt.service;

import com.niit.tickethunt.domain.User;
import com.niit.tickethunt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IGlobalService<User> {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findById(int id) {
        return userRepository.findById((long) id);
    }

    @Override
    public User update(User user) {
        return userRepository.save(user);
    }

    public String addBooking(int eventId, int userId) {
        if (findById(userId).isEmpty()) {
            return "failed";
        }
        userRepository.addBooking(eventId, userId);
        return "success";
    }
}
