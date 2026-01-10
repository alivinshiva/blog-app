package me.alivinshiva.blogapp.repo;

import me.alivinshiva.blogapp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

@Component
public interface UserRepo extends MongoRepository<User , ObjectId> {

    User findByUsername(String username);

}
