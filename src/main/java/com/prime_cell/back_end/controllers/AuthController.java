package com.prime_cell.back_end.controllers;

import com.prime_cell.back_end.dto.LoginRequestDTO;
import com.prime_cell.back_end.dto.RegisterRequestDTO;
import com.prime_cell.back_end.dto.ResponseDTO;
import com.prime_cell.back_end.infra.security.TokenService;
import com.prime_cell.back_end.models.User;
import com.prime_cell.back_end.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDTO bory) {
        User user = this.userRepository.findByEmail(bory.email()).orElseThrow(()-> new RuntimeException("User not found"));
        if (passwordEncoder.matches(bory.password(), user.getPassword())) {
            String token = tokenService.generateToken(user);
            return ResponseEntity.ok(new ResponseDTO(user.getName(), token));
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterRequestDTO bory) {

        Optional<User> user= this.userRepository.findByEmail(bory.email());

        if(user.isEmpty()){
            User newUser = new User();
            newUser.setPassword(passwordEncoder.encode(bory.password()));
            newUser.setEmail(bory.email());
            newUser.setName(bory.name());
            this.userRepository.save(newUser);

            String token = tokenService.generateToken(newUser);

            return ResponseEntity.ok(new ResponseDTO(newUser.getName(), token));

        }
        return ResponseEntity.badRequest().build();
    }
}
