package me.alivinshiva.blogapp.service;

import me.alivinshiva.blogapp.entity.User;
import me.alivinshiva.blogapp.repo.UserRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserService {

    @Autowired
    private UserRepo userRepo;

//    public void login(User user) {
//      userRepo.save(user);
//    }

    public void saveUser(User user) {
        userRepo.save(user);
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public boolean deleteUserByUsername(String username) {
        User user = userRepo.findByUsername(username);
        if (user == null) {
            return false;
        }
        userRepo.delete(user);
        return true;
    }

    public User updateUser(String username, User updatedUser) {
        User ecistedUser = userRepo.findByUsername(username);
                if (ecistedUser != null) {
                    ecistedUser.setUsername(updatedUser.getUsername());
                    ecistedUser.setName(updatedUser.getName());
                    ecistedUser.setPassword(updatedUser.getPassword());
                    userRepo.save(ecistedUser);
                    return updatedUser;
                } else {
                    return null;
                }
    }


    public User findByUsername(String username) {
        return userRepo.findByUsername(username);
    }
}
