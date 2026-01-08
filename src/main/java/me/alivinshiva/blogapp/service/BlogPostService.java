package me.alivinshiva.blogapp.service;

import me.alivinshiva.blogapp.entity.BlogPost;
import me.alivinshiva.blogapp.repo.BlogPostRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BlogPostService {

    @Autowired
    private BlogPostRepo blogPostRepo;

    public void saveBlogPost(BlogPost blogPost) {
        blogPostRepo.save(blogPost);
    }

    public List<BlogPost> getAllPosts() {
        return blogPostRepo.findAll();
    }

    public Optional<BlogPost> getPostById(ObjectId id) {
        return blogPostRepo.findById(id);
    }

    public Boolean deletePostById(ObjectId id) {
        if (!blogPostRepo.existsById(id)) {
            return false;
        }
        blogPostRepo.deleteById(id);
        return true;
    }

    public BlogPost updatePost(ObjectId id, BlogPost updatedPost) {

        return blogPostRepo.findById(id).map(existingPost -> {
            existingPost.setTitle(updatedPost.getTitle());
            existingPost.setContent(updatedPost.getContent());
            existingPost.setAuthor(updatedPost.getAuthor());
            existingPost.setDate(updatedPost.getDate());
            return blogPostRepo.save(existingPost);
        }).orElse(null);
    }
}

