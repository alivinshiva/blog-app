package me.alivinshiva.blogapp.controller;


import me.alivinshiva.blogapp.entity.BlogPost;
import me.alivinshiva.blogapp.entity.User;
import me.alivinshiva.blogapp.service.BlogPostService;
import me.alivinshiva.blogapp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/posts")
public class EntryControllerV2 {


    @Autowired
    private BlogPostService blogPostService;

    @Autowired
    private UserService userService;


    // working ✅   Adding Posts to a user
    @PostMapping("/add")
    public ResponseEntity<BlogPost> addPost(@RequestBody BlogPost myPost) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        try {
            blogPostService.saveBlogPost(myPost, name);
            return new ResponseEntity<> (myPost, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<> (HttpStatus.BAD_REQUEST);
        }
    }


    // working ✅  Getting all posts of authenticated user
    @GetMapping("/all")
    public ResponseEntity<List<BlogPost>> getAllPostsOfUsers() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        try {
            List<BlogPost> posts = userService.getUserPosts(name);
            if (posts.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(posts, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // working ✅ Getting post by Id
    @GetMapping("/id/{myId}")
    public ResponseEntity<BlogPost> getPostById(@PathVariable ObjectId myId) {
        Optional<BlogPost> blogPost = blogPostService.getPostById(myId);
        if(blogPost.isPresent()) {
            return new ResponseEntity<> (blogPost.get() , HttpStatus.OK);
        }
            return new ResponseEntity<> (HttpStatus.NOT_FOUND);


    }


    // working ✅    Deleting post by Id for a specific user
    @DeleteMapping("/{myId}")
    public ResponseEntity<?> deletePost(@PathVariable ObjectId myId) {
        try {
            String name = SecurityContextHolder.getContext().getAuthentication().getName();
            Boolean delete = blogPostService.deletePostById(myId, name);
            if (!delete) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }


    // working ✅  Updating post by Id for a specific user
    @PutMapping("/id/{myId}")
    public ResponseEntity<BlogPost> updatePost(@PathVariable ObjectId myId,@RequestBody BlogPost newPost) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();

        try {
            BlogPost updated = blogPostService.updatePost(myId, newPost, name);
            if (updated != null) {
               return new ResponseEntity<>(updated, HttpStatus.OK);
           } else {
               return new ResponseEntity<>(HttpStatus.NOT_FOUND);
           }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
