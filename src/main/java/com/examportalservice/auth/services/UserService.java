package com.examportalservice.auth.services;

import com.examportalservice.auth.user.entity.User;
import com.examportalservice.auth.user.repo.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> getAllUsers(){
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);

        return  users;

    }

    public User getUserByName(String username) {
        return this.userRepository.findByUsername(username);
    }

    public void deleteUserById(Long userId) {
        this.userRepository.deleteById(Math.toIntExact(userId));
    }



    public User getUserByUserId(Long userId) {
        Optional<User> optionalUser = userRepository.findById(Math.toIntExact(userId));
        if(optionalUser.isPresent()){
            return optionalUser.get();
        }else{
            System.out.println("user not found ");
        }
        return  null;
    }
}
