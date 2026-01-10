package me.alivinshiva.blogapp.controller;


import me.alivinshiva.blogapp.entity.User;
import me.alivinshiva.blogapp.repo.UserRepo;
import me.alivinshiva.blogapp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private UserRepo userRepo;


    // working ✅  updating authenticated user with username and password
    // authenticated  route requires username and password in basic auth header.
    @PutMapping
    public ResponseEntity<User> updateUserByUsername(@RequestBody User updatedUser) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        User user = userService.updateUser( currentUsername,updatedUser);
            return new ResponseEntity<>(user, HttpStatus.OK);
    }

    // working ✅  Deleting user by username for logged in user
    // authenticated route requires username and password in basic auth header.
    @DeleteMapping
    public boolean deleteUser() {
        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        return userService.deleteUserByUsername(currentUsername);
    }



}
