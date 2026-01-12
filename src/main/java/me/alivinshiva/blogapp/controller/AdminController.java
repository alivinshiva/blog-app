package me.alivinshiva.blogapp.controller;

import me.alivinshiva.blogapp.entity.BlogPost;
import me.alivinshiva.blogapp.entity.User;
import me.alivinshiva.blogapp.service.BlogPostService;
import me.alivinshiva.blogapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private BlogPostService blogPostService;


    // Admin endpoint to get all posts in db.
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> allUsers = userService.getAllUsers();
        if (allUsers != null && !allUsers.isEmpty()) {
            return new ResponseEntity<>(allUsers, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Admin endpoint to get all posts in db.
    @GetMapping("/posts")
    public ResponseEntity<Object> getAllPosts() {
        List<BlogPost> allPosts = blogPostService.getAllPosts();
        if (allPosts != null && !allPosts.isEmpty()) {
            return new ResponseEntity<>(allPosts, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Create admin user
    @PostMapping("/create")
    public User createAdminUser(@RequestBody User user) {
        return userService.createAdminUser(user);
    }
}
