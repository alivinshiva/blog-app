package me.alivinshiva.blogapp.service;

import me.alivinshiva.blogapp.entity.User;
import me.alivinshiva.blogapp.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;


    // Create new user with password encoding and default role
    public User createUser(User user) {
        if (userRepo.findByUsername(user.getUsername()) != null) {
            throw new IllegalArgumentException("Username already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER"));
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
}
