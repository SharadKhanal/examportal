package com.examportalservice.service.impl;


import com.examportalservice.entity.User;
import com.examportalservice.entity.UserRole;
import com.examportalservice.repo.RoleRepository;
import com.examportalservice.repo.UserRepository;
import com.examportalservice.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    //creating user
    @Override
    public User createUser(User user, Set<UserRole> userRoles) throws Exception {

        User local = this.userRepository.findByUsername(user.getUsername());

        if (local != null) {
            throw new Exception("User Already exist!!!");
        } else {
            //first save role that exist in database
            for (UserRole userRole : userRoles) {
                roleRepository.save(userRole.getRole());
                System.out.println("ur:" + userRole);
            }
            //assign role to user and save user
            user.getUserRoles().addAll(userRoles);
            local = this.userRepository.save(user);
        }
        return local;
    }

    @Override
    public User getUserByName(String username) {
        return this.userRepository.findByUsername(username);
    }

    @Override
    public void deleteUserById(Long userId) {
        this.userRepository.deleteById(userId);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserByUserId(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isPresent()){
            return optionalUser.get();
        }else{
            System.out.println("user not found ");
        }
        return  null;
    }


}
