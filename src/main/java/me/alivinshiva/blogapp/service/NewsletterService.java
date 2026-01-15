package me.alivinshiva.blogapp.service;


import org.springframework.stereotype.Service;

@Service
public class NewsletterService {

    public String newsLetterContent() {
        return "This is the content of the newsletter.";
    }
}
