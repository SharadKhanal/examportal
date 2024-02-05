package com.examportalservice.config;

import com.examportalservice.service.impl.UserDetailServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MySecurityConfig {

    private final UserDetailServiceImpl userDetailServiceImpl;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAuthenticationFilter jwtAuthencationFilter;

    public MySecurityConfig(UserDetailServiceImpl userDetailServiceImpl,
                            JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint,
                            JwtAuthenticationFilter jwtAuthencationFilter) {

        this.userDetailServiceImpl = userDetailServiceImpl;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
        this.jwtAuthencationFilter = jwtAuthencationFilter;
    }


    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailServiceImpl);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.csrf(csrf -> csrf.disable())
//                .exceptionHandling(exception -> exception.authenticationEntryPoint(jwtAuthenticationEntryPoint))
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .authorizeRequests(authorize -> {
//                    authorize
//                            .antMatchers("/generate-token", "/user/").permitAll()
//                            .antMatchers(HttpMethod.OPTIONS).permitAll()
//                            .anyRequest().authenticated();
//                });

        //usable
        http.csrf(AbstractHttpConfigurer ::disable).
                authorizeHttpRequests(request->request.requestMatchers("/generate-token","/examportal/user","/category/add",
                                "/category/update",
                                "/category/all",
                                "/category/getCategoryById/{id}",
                                "/category/delete/{id}").permitAll().
                        requestMatchers(HttpMethod.OPTIONS).permitAll().
                        anyRequest().authenticated()).
                sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).
                exceptionHandling(exception->exception.authenticationEntryPoint(jwtAuthenticationEntryPoint));

        http.addFilterBefore(jwtAuthencationFilter, UsernamePasswordAuthenticationFilter.class);
        http.authenticationProvider(authenticationProvider());
        return http.build();
    }



}
