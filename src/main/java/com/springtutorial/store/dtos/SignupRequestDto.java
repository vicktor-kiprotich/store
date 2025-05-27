package com.springtutorial.store.dtos;

import jakarta.validation.constraints.*;

public class SignupRequestDto {
    @Email(message = "Email should be valid")
    @NotBlank(message = "Email must not be empty")
    public String email;

    @NotBlank(message = "Password must be provided")
    public String password;

    @NotBlank(message = "First name must be provided")
    public String firstname;

    @NotBlank(message = "Last name must be provided")
    public String lastname;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }
}