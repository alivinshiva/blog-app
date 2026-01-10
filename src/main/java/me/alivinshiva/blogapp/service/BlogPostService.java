package me.alivinshiva.blogapp.service;

import me.alivinshiva.blogapp.entity.BlogPost;
import me.alivinshiva.blogapp.entity.User;
import me.alivinshiva.blogapp.repo.BlogPostRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class BlogPostService {

    @Autowired
    private BlogPostRepo blogPostRepo;

    @Autowired
    private UserService userService;

    @Transactional
    public void saveBlogPost(BlogPost blogPost, String username) {
        User user = userService.findByUsername(username);
        blogPost.setDate(LocalDateTime.now());
        BlogPost saved = blogPostRepo.save(blogPost);
        user.getUserBlogPost().add(saved);
        userService.saveUser(user);
    }

    public List<BlogPost> getAllPosts() {
        return blogPostRepo.findAll();
    }

    public Optional<BlogPost> getPostById(ObjectId id) {
        return blogPostRepo.findById(id);
    }

//    public Boolean deletePostById(ObjectId id, String username) {
//        User user = userService.findByUsername(username);
//        if (!blogPostRepo.existsById(id)) {
//            return false;
//        }
//        blogPostRepo.deleteById(id);
//        user.getUserBlogPost().removeIf(post -> post.getId().equals(id));
//        userService.saveUser(user);
//        return true;
//    }

    // Update post with user verification
    public BlogPost updatePost(ObjectId id, BlogPost updatedPost, String username) {
        User user = userService.findByUsername(username);
        return getBlogPost(id, updatedPost);
    }

    // Update post without user verification
    public BlogPost updatePost(ObjectId id, BlogPost updatedPost) {
        return getBlogPost(id, updatedPost);
    }

    // Helper method to update blog post fields
    private BlogPost getBlogPost(ObjectId id, BlogPost updatedPost) {
        return blogPostRepo.findById(id).map(existingPost -> {
            existingPost.setTitle(updatedPost.getTitle());
            existingPost.setContent(updatedPost.getContent());
            existingPost.setAuthor(updatedPost.getAuthor());
            existingPost.setDate(updatedPost.getDate());
            return blogPostRepo.save(existingPost);
        }).orElse(null);
    }


}

