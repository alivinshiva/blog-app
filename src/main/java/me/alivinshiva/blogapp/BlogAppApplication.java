package me.alivinshiva.blogapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class BlogAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogAppApplication.class, args);
    }

    @Bean
    public PlatformTransactionManager add(MongoDatabaseFactory mongoDbFactory) {
        return new MongoTransactionManager(mongoDbFactory);
    }

}

// PlatformTransactionManager ->> This is the central interface in Spring's imperative transaction infrastructure. Applications can use this directly, but it is not primarily meant as an API: Typically, applications will work with either TransactionTemplate or declarative transaction demarcation through AOP.

// mongoTransactionManager

