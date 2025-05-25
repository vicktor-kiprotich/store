package com.springtutorial.store.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class LoginRequestDto {
    @Email(message = "Email should be valid")
    @NotBlank(message = "Email caanot be Empty!")
    public String email;

    @NotBlank(message = "Password cannot be blank")
    public String password;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
