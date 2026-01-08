package me.alivinshiva.blogapp.controller;


import me.alivinshiva.blogapp.entity.BlogPost;
import me.alivinshiva.blogapp.service.BlogPostService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/posts")
public class EntryControllerV2 {


    @Autowired
    private BlogPostService blogPostService;


    // working ✅   Adding Posts
    @PostMapping
    public ResponseEntity<BlogPost> addPost(@RequestBody BlogPost myPost) {
        try {
            myPost.setDate(LocalDateTime.now());
            blogPostService.saveBlogPost(myPost);
            return new ResponseEntity<> (myPost, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<> (HttpStatus.BAD_REQUEST);
        }
    }


    // working ✅  Getting all posts
    @GetMapping("/all/{username}")
    public ResponseEntity<List<BlogPost>> getAllPostsOfUsers(@PathVariable String username) {
//        return blogPostService.getAllPosts();
        try {
            List<BlogPost> posts = blogPostService.getAllPosts();
            if (posts.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(posts, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // working ✅ Getting post by Id
    @GetMapping("id/{myId}")
    public ResponseEntity<BlogPost> getPostById(@PathVariable ObjectId myId) {
        Optional<BlogPost> blogPost = blogPostService.getPostById(myId);
        if(blogPost.isPresent()) {
            return new ResponseEntity<> (blogPost.get() , HttpStatus.OK);
        }
            return new ResponseEntity<> (HttpStatus.NOT_FOUND);


    }


    // working ✅    Deleting post by Id
    @DeleteMapping("id/{myId}")
    public ResponseEntity<?> deletePostById(@PathVariable ObjectId myId) {
        boolean delete = blogPostService.deletePostById(myId);
        try {
            if (!delete) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }


    // working ✅  Updating post by Id
    @PutMapping("id/{myId}")
    public ResponseEntity<BlogPost> updatePost(@PathVariable ObjectId myId,@RequestBody BlogPost newPost) {
        BlogPost updated = blogPostService.updatePost(myId, newPost);
        try {
           if (updated != null) {
               return new ResponseEntity<>(newPost, HttpStatus.OK);
           } else {
               return new ResponseEntity<>(HttpStatus.NOT_FOUND);
           }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
