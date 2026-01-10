package me.alivinshiva.blogapp.controller;

import me.alivinshiva.blogapp.entity.User;
import me.alivinshiva.blogapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserService userService;

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("OK");
    }

    // Signup endpoint open for public access
    @PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestBody User newUser) {
        try {
            User createdUser = userService.createUser(newUser);
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT); // Username exists
        }
    }
}
