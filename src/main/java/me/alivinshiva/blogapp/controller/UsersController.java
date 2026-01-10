package me.alivinshiva.blogapp.controller;


import me.alivinshiva.blogapp.entity.User;
import me.alivinshiva.blogapp.repo.UserRepo;
import me.alivinshiva.blogapp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UsersController {


    @Autowired
    private UserService userService;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private UserRepo userRepo;

    // endpoint for login - not yet implemented
    @PostMapping("/login")
    public User login() {
//        userRepo.save();
        return null;
    }

    // working ✅   Signing up a new user
    @PostMapping
    public User signup(@RequestBody User newUser) {
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        newUser.setRoles(Arrays.asList("USER"));
        userRepo.save(newUser);
        return newUser;
    }


    // working ✅  Getting all users
    @GetMapping("/all")
    public List<User> getAlluser() {
        return userService.getAllUsers();
    }

    // Working ✅  Updating user by username
    @PutMapping("/{username}")
    public ResponseEntity<User> updateUserByUsername(@RequestBody User updatedUser, @PathVariable String username) {
//        User existedUser = userService.findByUsername(username);
//        if (existedUser != null) {
            User user = userService.updateUser(username, updatedUser);
            return new ResponseEntity<>(user, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }

    }

    // working ✅  Deleting user by username
    @DeleteMapping("/{username}")
    public boolean deleteUser(@PathVariable  String username) {
        User userInDb = userService.findByUsername(username);
        if (userInDb != null) {
            userService.deleteUserByUsername(username);
            return true;
        }
        return false;
    }

    // working ✅  Getting user by username
    @GetMapping("/{username}")
    public ResponseEntity<?> getUserByUsername(@PathVariable String username) {
        User userInDb = userService.findByUsername(username);
        if (userInDb != null) {
            return new ResponseEntity<>(userInDb, HttpStatus.OK);
        }
        return new ResponseEntity<>(userInDb, HttpStatus.NOT_FOUND);
    }





}
