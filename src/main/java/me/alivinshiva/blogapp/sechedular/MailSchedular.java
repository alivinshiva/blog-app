package me.alivinshiva.blogapp.sechedular;


import me.alivinshiva.blogapp.entity.User;
import me.alivinshiva.blogapp.repo.UserImplRepo;
import me.alivinshiva.blogapp.service.EmailService;
import me.alivinshiva.blogapp.service.NewsletterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MailSchedular {

    @Autowired
    private UserImplRepo userImplRepo;

    @Autowired
    private EmailService emailService;

    @Autowired
    private NewsletterService newsLetter;

    @Scheduled(cron = "0 0 9 * * MON") // Every Monday at 9 AM
    public void sendMailToSubscribers() {
        List<User> users = userImplRepo.getUserFroNewsletter();
        for (User user : users) {
            String to = user.getEmail();
            String subject = "Newsletter of this week";
            String body = newsLetter.newsLetterContent();
            emailService.sendMail(to, subject, body);
        }
    }

}
