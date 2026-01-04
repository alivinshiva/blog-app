package me.alivinshiva.blogapp.controller;


import me.alivinshiva.blogapp.entity.BlogPost;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/posts")
public class EntryController {

    private Map<Long, BlogPost> blogpost = new HashMap<>();

    @GetMapping
    public List<BlogPost> getAllPosts() {
        return new ArrayList<>(blogpost.values());
    }

    @PostMapping
    public void addPost() {

    }
}
