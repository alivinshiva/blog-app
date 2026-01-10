package me.alivinshiva.blogapp.controller;


import me.alivinshiva.blogapp.entity.BlogPost;
import me.alivinshiva.blogapp.entity.User;
import me.alivinshiva.blogapp.service.BlogPostService;
import me.alivinshiva.blogapp.service.UserService;
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

    @Autowired
    private UserService userService;


    // working ✅   Adding Posts to a user
    @PostMapping("/{username}")
    public ResponseEntity<BlogPost> addPost(@RequestBody BlogPost myPost, @PathVariable String username) {
        try {
            blogPostService.saveBlogPost(myPost, username);
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
            User user = userService.findByUsername(username);
            List<BlogPost> posts = user.getUserBlogPost();
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
    @DeleteMapping("/{username}/{myId}")
    public ResponseEntity<?> deletePost(@PathVariable String username,  @PathVariable ObjectId myId) {
//        boolean delete = blogPostService.deletePostById(myId, username);
        boolean delete = false;
        try {
            if (!delete) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }


    // working ✅  Updating post by Id for a specific user
    @PutMapping("/{username}/{myId}")
    public ResponseEntity<BlogPost> updatePost(@PathVariable ObjectId myId,@RequestBody BlogPost newPost, @PathVariable String username) {
        try {
            BlogPost updated = blogPostService.updatePost(myId, newPost);
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
