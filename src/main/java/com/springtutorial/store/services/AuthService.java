package com.springtutorial.store.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.springtutorial.store.dtos.LoginRequestDto;
import com.springtutorial.store.dtos.SignupRequestDto;
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
        if (!match) {
            throw new RuntimeException("Invalid Credentials");
        }
        return jwtUtil.generateToken(user.getEmail());
    }

    public String signup(SignupRequestDto data) {

        // check if user already exists
        Optional<User> user = userRepository.findByEmail(data.getEmail());

        if (user.isPresent()) {
            throw new RuntimeException("Username already exists");
        }
        // hash pwd
        String hashPwd = BCrypt.hashpw(data.getPassword(), BCrypt.gensalt());
        // create user and send email
        User newUser = new User();
        newUser.setEmail(data.getEmail());
        newUser.setPassword(hashPwd);
        newUser.setFirstname(data.getFirstname());
        newUser.setLastname(data.getLastname());
        // save temporary token for verification later on
        userRepository.save(newUser);

        // return temp token to user

        return jwtUtil.generateToken(newUser.getEmail());
    }
}
