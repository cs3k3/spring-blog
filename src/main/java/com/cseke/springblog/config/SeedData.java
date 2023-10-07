package com.cseke.springblog.config;

import com.cseke.springblog.models.Account;
import com.cseke.springblog.models.Post;
import com.cseke.springblog.services.AccountService;
import com.cseke.springblog.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SeedData implements CommandLineRunner {
    @Autowired
    private PostService postService;

    @Autowired
    private AccountService accountService;

    @Override
    public void run(String... args) throws Exception {
        List<Post> posts = postService.getAll();

        if (posts.size() == 0){

            Account account1 = new Account();
            Account account2 = new Account();

            account1.setFirstName("user1");
            account1.setLastName("user1Last");
            account1.setEmail("user1@email.com");
            account1.setPassword("user1Password");


            account2.setFirstName("admin");
            account2.setLastName("admin");
            account2.setEmail("admin@admin.com");
            account2.setPassword("admin");

            accountService.save(account1);
            accountService.save(account2);


            Post post1 = new Post();
            post1.setTitle("post1 title");
            post1.setBody("post1 body");
            post1.setAccount(account1);

            Post post2 = new Post();
            post2.setTitle("post2 title");
            post2.setBody("post2 body");
            post2.setAccount(account2);

            postService.save(post1);
            postService.save(post2);
        }
    }
}
