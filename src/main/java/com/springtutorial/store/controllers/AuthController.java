package com.springtutorial.store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springtutorial.store.dtos.LoginRequestDto;
import com.springtutorial.store.dtos.ResponseDto;
import com.springtutorial.store.services.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    public AuthService authService;

    @PostMapping("signin")
    public ResponseEntity<ResponseDto<String>> login(@Valid @RequestBody LoginRequestDto body) {
        try {
            String token = authService.login(body);
            return ResponseEntity.ok(ResponseDto.success("Sign in Successfull", token));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ResponseDto.failure(e.getMessage(), 401));
        }
    }

}
