//package com.examportalservice.controller;
//
//import com.examportalservice.config.JwtUtils;
//import com.examportalservice.entity.JwtRequest;
//import com.examportalservice.entity.JwtResponse;
//import com.examportalservice.service.impl.UserDetailServiceImpl;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.DisabledException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//@CrossOrigin("*")
//public class AuthencationController {
//    private final JwtUtils jwtUtils;
//    private final UserDetailServiceImpl userDetailService;
//    private final AuthenticationManager authenticationManager;
//
//    public AuthencationController(JwtUtils jwtUtils, UserDetailServiceImpl userDetailService, AuthenticationManager authenticationManager) {
//        this.jwtUtils = jwtUtils;
//        this.userDetailService = userDetailService;
//        this.authenticationManager = authenticationManager;
//    }
//
//    //generate token
//    @PostMapping("/generate-token")
//
//    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {
//        try {
//            authencitae(jwtRequest.getUsername(),jwtRequest.getPassword());
//        }catch (UsernameNotFoundException e){
//            e.printStackTrace();
//            throw  new Exception("User Not Found.");
//        }
//
//        //authentiacte
//
//        UserDetails userDetails = this.userDetailService.loadUserByUsername(jwtRequest.getUsername());
//        String token = this.jwtUtils.generateToken(userDetails);
//        return ResponseEntity.ok(new JwtResponse(token));
//    }
//
//    public void  authencitae(String username, String password) throws Exception {
//
//        try{
//                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
//        }catch (DisabledException e){
//            throw new Exception("USER DISABLED!!");
//        }catch (BadCredentialsException e){
//            throw new Exception("Invalid Credetials" +e.getMessage());
//        }
//
//    }
//}
