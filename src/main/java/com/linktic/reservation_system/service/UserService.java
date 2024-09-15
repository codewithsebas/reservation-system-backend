package com.linktic.reservation_system.service;

import com.linktic.reservation_system.model.User;
import com.linktic.reservation_system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User registerUser(User user) {
        return userRepository.save(user);
    }

    public User findByUsername(String username) {
        return  userRepository.findByUsername(username).orElse(null);
    }
}
