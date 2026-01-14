package me.alivinshiva.blogapp.service;

import me.alivinshiva.blogapp.entity.BlogPost;
import me.alivinshiva.blogapp.entity.User;
import me.alivinshiva.blogapp.repo.UserImplRepo;
import me.alivinshiva.blogapp.repo.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserImplRepo userImplRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ListableBeanFactory listableBeanFactory;


    // Create new user with password encoding and default role
    public User createUser(User user) {
        try {
            if (userRepo.findByUsername(user.getUsername()) != null) {
                throw new IllegalArgumentException("Username already exists");
            }
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList("USER"));
            return userRepo.save(user);
        } catch (IllegalArgumentException e) {
            logger.info("User creation failed: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }


    // create admin user with password encoding and admin role
    public User createAdminUser(User user) {
        if (userRepo.findByUsername(user.getUsername()) != null) {
            throw new IllegalArgumentException("Username already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("ADMIN", "USER"));

        return userRepo.save(user);
    }

    public void saveUser(User user) {
        userRepo.save(user);
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    // Delete user by username
    // Working ✅
    public boolean deleteUserByUsername(String username) {
        User user = userRepo.findByUsername(username);
        if (user == null) {
            return false;
        }
        userRepo.delete(user);
        return true;
    }

    public User updateUser(String username, User updatedUser) {
        User existingUser = userRepo.findByUsername(username);
        if (existingUser != null) {
            // Only update fields that are provided (selective update)
            if (updatedUser.getName() != null && !updatedUser.getName().isEmpty()) {
                existingUser.setName(updatedUser.getName());
            }
            // For security: only update password if provided and valid
            if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
                existingUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
            }
            return userRepo.save(existingUser);
        }
        return null;
    }

    public User findByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    public List<BlogPost> getUserPosts(String name) {
        User user = userRepo.findByUsername(name);
        List<BlogPost> post = user.getUserBlogPost();
        return post;

    }

    public List<User> getUserForNewsletter() {
        return userImplRepo.getUserFroNewsletter();

    }
}
