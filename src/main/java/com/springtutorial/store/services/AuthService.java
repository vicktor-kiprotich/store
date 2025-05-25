package com.springtutorial.store.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.springtutorial.store.dtos.LoginRequestDto;
import com.springtutorial.store.entities.User;
import com.springtutorial.store.repositories.UserRepository;
import com.springtutorial.store.security.JwtUtil;

@Service
public class AuthService {
    @Autowired
    public UserRepository userRepository;
    @Autowired
    private JwtUtil jwtUtil;

    // sign in function
    public String login(LoginRequestDto data) {
        User user = userRepository.findByEmail(data.getEmail())
                .orElseThrow(() -> new RuntimeException("Username does not exist"));

        boolean match = BCrypt.checkpw(data.getPassword(), user.getPassword());
        if(!match){
            throw new RuntimeException("Invalid Credentials");
        }
        return jwtUtil.generateToken(user.getEmail());
    }
}
