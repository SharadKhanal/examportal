package com.examportalservice.auth.services;

import com.examportalservice.auth.dtos.LoginUserDto;
import com.examportalservice.auth.user.entity.User;
import com.examportalservice.auth.user.repo.UserRepository;
import com.examportalservice.entity.UserRole;
import com.examportalservice.helper.UserFoundException;
import com.examportalservice.repo.RoleRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
@Slf4j
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private final RoleRepository roleRepository;


    public Optional<User> createUser(User user, Set<UserRole> userRoles) throws Exception {

        Optional<User> local = this.userRepository.findByEmail(user.getEmail());

        if (local.isPresent()) {
            throw new UserFoundException();
        } else {
            //first save role that exist in database
            for (UserRole userRole : userRoles) {
                roleRepository.save(userRole.getRole());
                System.out.println("ur:" + userRole);
            }
            //assign role to user and save user
            user.getUserRoles().addAll(userRoles);

            local = Optional.of(this.userRepository.save(user));
        }
        return local;
    }
    public User authenticate(LoginUserDto  input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );

        return userRepository.findByEmail(input.getEmail())
                .orElseThrow();
    }
}
