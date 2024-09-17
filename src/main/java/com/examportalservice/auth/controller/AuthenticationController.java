package com.examportalservice.auth.controller;

import com.examportalservice.auth.dtos.LoginUserDto;
import com.examportalservice.auth.services.AuthenticationService;
import com.examportalservice.auth.services.JwtService;
import com.examportalservice.auth.user.entity.User;
import com.examportalservice.entity.Role;
import com.examportalservice.entity.UserRole;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
@Slf4j
@Controller
@CrossOrigin("*")
public class AuthenticationController {

    private final JwtService jwtService;
    private final AuthenticationService authenticationService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @PostMapping("/signup")
    public Optional<User> register(@RequestBody User user) throws Exception {
        Set<UserRole> roles = new HashSet<>();
        user.setProfile("default.png");
        user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
        Role role = new Role();
        role.setRoleId(10l);
        role.setRoleName("NORMAL");

        UserRole userRole = new UserRole();

        userRole.setRole(role);
        userRole.setUser(user);

        roles.add(userRole);

        return this.authenticationService.createUser(user, roles);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
        User authenticatedUser = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse .setToken(jwtToken);
        loginResponse .setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }
}
