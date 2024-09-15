package com.linktic.reservation_system.controller;

import com.linktic.reservation_system.model.User;
import com.linktic.reservation_system.service.UserService;
import com.linktic.reservation_system.service.TokenService;
import com.linktic.reservation_system.exception.UserAlreadyExistsException;
import com.linktic.reservation_system.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        try {
            User registeredUser = userService.registerUser(user);
            return new ResponseEntity<>("Usuario registrado correctamente", HttpStatus.CREATED);
        } catch (UserAlreadyExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody User user) {
        try {
            // Encriptar la contraseña ingresada antes de compararla
            String encryptedPassword = PasswordUtil.encryptPassword(user.getPassword());

            User foundUser = userService.findByEmail(user.getEmail())
                    .filter(u -> u.getPassword().equals(encryptedPassword)) // Comparar contraseñas encriptadas
                    .orElseThrow(() -> new RuntimeException("Invalidos email o contraseña"));

            String token = tokenService.generateToken(foundUser.getEmail());

            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("userId", foundUser.getId());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (RuntimeException e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
        }
    }
}
