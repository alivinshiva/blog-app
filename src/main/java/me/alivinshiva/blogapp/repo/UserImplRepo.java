package me.alivinshiva.blogapp.repo;

import me.alivinshiva.blogapp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserImplRepo {

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<User> getUserFroNewsletter() {
        Query query = new Query();
//        query.addCriteria(Criteria.where("username").is("Alivin"));
        query.addCriteria(Criteria.where("email").exists(true).ne(null).ne(""));
        query.addCriteria(Criteria.where("optedInForNewsletter").is(true));

        List<User> users = mongoTemplate.find(query, User.class);
        System.out.println(users);
        return users;

    }

}
