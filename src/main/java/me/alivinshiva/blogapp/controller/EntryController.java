//       This is v1 of the code



//package me.alivinshiva.blogapp.controller;
//
//
//import me.alivinshiva.blogapp.entity.BlogPost;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/_posts")
//public class EntryController {
//
//    private Map<Long, BlogPost> blogpost = new HashMap<>();
//
//    @GetMapping
//    public List<BlogPost> getAllPosts() {
//        return new ArrayList<>(blogpost.values());
//    }
//
//    @PostMapping
//    public String addPost(@RequestBody BlogPost myPost) {
//        blogpost.put(myPost.getId(),myPost);
//        return "Post added successfully";
//    }
//
//    @GetMapping("id/{myId}")
//    public BlogPost getPostById(@PathVariable Long myId) {
//        return blogpost.get(myId);
//
//    }
//
//    @DeleteMapping("id/{myId}")
//    public String deleteBYId(@PathVariable Long myId) {
//        blogpost.remove(myId);
//        return "Deleted Successfully";
//    }
//
//    @PutMapping("id/{myId}")
//    public BlogPost updatePost(@PathVariable Long myId,@RequestBody BlogPost myPost) {
//        blogpost.put(myId,myPost);
//        return myPost;
//    }
//}
