package com.examportalservice.controller;

import com.examportalservice.entity.Role;
import com.examportalservice.entity.User;
import com.examportalservice.entity.UserRole;
import com.examportalservice.helper.UserFoundException;
import com.examportalservice.repo.UserRepository;
import com.examportalservice.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/examportal")
@CrossOrigin("*")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;
//    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
//        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping("/user")
    public User createUser(@RequestBody User user) throws Exception {
        Set<UserRole> roles = new HashSet<>();
        user.setProfile("default.png");
//        user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
        Role role = new Role();
        role.setRoleId(45L);
        role.setRoleName("NORMAL");

        UserRole userRole = new UserRole();

        userRole.setRole(role);
        userRole.setUser(user);

        roles.add(userRole);

        return this.userService.createUser(user, roles);
    }

    @GetMapping("/{username}")
    public User getUserByUsername(@PathVariable("username") String username) {
        return this.userService.getUserByName(username);
    }

    @DeleteMapping("/delete/{userId}")
    public void deleteUserByUserId(@PathVariable("userId") Long userId) {
        this.userService.deleteUserById(userId);
    }

    @PutMapping("update/{userId}")
    public User updateUserById(@PathVariable("userId") Long userId, @RequestBody User user){
//        Optional<User> optionalUser = this.userRepository.findById(userId);
//        System.out.println("optionalUser:"+optionalUser);
//        if(optionalUser.isPresent()){
//            User user1 = optionalUser.get();
//            user1.setLastname(user.getLastname());
//            user1.setUsername(user.getUsername());
//          this.userRepository.save(user1);
//          return  user1;
//        }
//        return null;
        if(this.userRepository.existsById(userId)){
            user.setId(userId);
            return userRepository.save(user);
        }
        return null;
    }

    @RequestMapping(method= RequestMethod.GET)
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users = userService.getAllUsers();
        if(!users.isEmpty()){
            return ResponseEntity.ok(users);
        }else{
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/userId/{userId}")
    public User getUserById(@PathVariable("userId")Long userId){
        return userService.getUserByUserId(userId);
    }

    @ExceptionHandler(UserFoundException.class)
    public ResponseEntity<?> exceptionHandler(UserFoundException ex){
        return ResponseEntity.ok(ex);
    }
}
