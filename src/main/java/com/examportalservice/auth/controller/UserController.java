package com.examportalservice.auth.controller;

import com.examportalservice.auth.services.UserService;
import com.examportalservice.auth.user.entity.User;
import com.examportalservice.auth.user.repo.UserRepository;
import com.examportalservice.helper.UserFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/users")
@RestController
@AllArgsConstructor
@CrossOrigin("*")
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;


    @GetMapping("/")
    public ResponseEntity getAllUser(){
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/me")
    public ResponseEntity<?> getAuthenticateUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();
        return ResponseEntity.ok(currentUser);

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
        if(this.userRepository.existsById(Math.toIntExact(userId))){
            user.setId(userId);
            return userRepository.save(user);
        }
        return null;
    }

//    @RequestMapping(method= RequestMethod.GET)
//    public ResponseEntity<List<User>> getAllUsers(){
//        List<User> users = userService.getAllUsers();
//        if(!users.isEmpty()){
//            return ResponseEntity.ok(users);
//        }else{
//            return ResponseEntity.noContent().build();
//        }
//    }

    @GetMapping("/userId/{userId}")
    public User getUserById(@PathVariable("userId")Long userId){
        return userService.getUserByUserId(userId);
    }

    @ExceptionHandler(UserFoundException.class)
    public ResponseEntity<?> exceptionHandler(UserFoundException ex){
        return ResponseEntity.ok(ex);
    }

}
