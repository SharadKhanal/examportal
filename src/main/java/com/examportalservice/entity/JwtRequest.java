package com.examportalservice.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class JwtRequest {
    private String username;
    private String password;

    public JwtRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
