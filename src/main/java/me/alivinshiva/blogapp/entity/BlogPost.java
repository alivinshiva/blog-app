package me.alivinshiva.blogapp.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "blogposts")

@Data
public class BlogPost {

    @Id
    private ObjectId id;
    @NonNull
    private String title;
    private String content;
    private String author;
    private String User;
    private LocalDateTime date;

}
