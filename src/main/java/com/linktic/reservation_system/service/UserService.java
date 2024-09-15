package com.linktic.reservation_system.service;

import com.linktic.reservation_system.exception.UserAlreadyExistsException;
import com.linktic.reservation_system.model.User;
import com.linktic.reservation_system.repository.UserRepository;
import com.linktic.reservation_system.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> findById(Long userId) {
        return userRepository.findById(userId);
    }

    public User registerUser(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException("El usuario con este email ya existe");
        }

        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException("El usuario con este username ya existe");
        }

        String hashedPassword = PasswordUtil.encryptPassword(user.getPassword());
        user.setPassword(hashedPassword);

        return userRepository.save(user);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
