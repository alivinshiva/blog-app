package me.alivinshiva.blogapp.repo;

import me.alivinshiva.blogapp.entity.BlogPost;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BlogPostRepo extends MongoRepository<BlogPost, ObjectId> {


}
