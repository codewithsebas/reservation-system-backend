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
import java.util.Optional;

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
        Map<String, Object> response = new HashMap<>();

        try {
            // Buscar al usuario por email
            Optional<User> foundUserOpt = userService.findByEmail(user.getEmail());

            // Verificar si el usuario existe
            if (!foundUserOpt.isPresent()) {
                throw new RuntimeException("El usuario con el email proporcionado no existe.");
            }

            User foundUser = foundUserOpt.get();

            // Validar la contraseña
            String encryptedPassword = PasswordUtil.encryptPassword(user.getPassword());
            if (!foundUser.getPassword().equals(encryptedPassword)) {
                throw new RuntimeException("Contraseña incorrecta.");
            }

            // Generar token si las credenciales son correctas
            String token = tokenService.generateToken(foundUser.getEmail());

            // Construir la respuesta
            response.put("token", token);
            response.put("userId", foundUser.getId());
            response.put("username", foundUser.getUsername());
            response.put("email", foundUser.getEmail());

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (RuntimeException e) {
            // Manejo de excepciones con mensaje de error adecuado
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
    }

}
