package org.example.e_commerce.controller.rest;

import org.example.e_commerce.model.AuthResponse;
import org.example.e_commerce.model.DTO.UserDTO;
import org.example.e_commerce.model.LoginRequest;
import org.example.e_commerce.model.User;
import org.example.e_commerce.repository.UserRepository;
import org.example.e_commerce.service.JwtService;
import org.example.e_commerce.service.UserService;
import org.example.e_commerce.util.HashPasswordUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.Collections;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private HashPasswordUser passwordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;


    @PostMapping("/register")
    @ResponseStatus(code = HttpStatus.CREATED)
    public User register(@RequestBody UserDTO userDTO) throws NoSuchAlgorithmException {
        return userService.registerUser(userDTO);
    }


    @PostMapping("/auth")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) throws NoSuchAlgorithmException {
        User user = userRepository.findByEmail(loginRequest.getEmail());

        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Credentials Not Found");
        }

        boolean passwordMatches = HashPasswordUser.verifyPassword(loginRequest.getPassword(), user.getSalt(), user.getPassword());

        if (passwordMatches) {
            String token = jwtService.generateToken(loginRequest.getEmail(), Collections.singletonList(user.getRole()));
            return ResponseEntity.ok(new AuthResponse(token));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Nom d'utilisateur ou mot de passe incorrect");
        }
    }


}
