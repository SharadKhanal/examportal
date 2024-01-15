package com.examportalservice.service.impl;

import com.examportalservice.entity.User;
import com.examportalservice.repo.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    public UserDetailServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      User user =  this.userRepository.findByUsername(username);
      if(user== null){
          System.out.println("User not Found");
          throw new UsernameNotFoundException("Invalid Credintails");
      }
        return user;
    }
}
