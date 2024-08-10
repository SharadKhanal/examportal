package com.examportalservice.auth.dtos;

import lombok.Data;

@Data
public class RegisterUserDto {
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String email;
    private String phone;
    private boolean enabled = true;
    private String profile;
}
