package com.examportalservice.auth.controller;

import lombok.Data;

@Data
public class LoginResponse {
    private String token;

    private long expiresIn;

}
