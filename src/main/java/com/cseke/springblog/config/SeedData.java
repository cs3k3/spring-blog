package com.cseke.springblog.config;

import com.cseke.springblog.models.Account;
import com.cseke.springblog.models.Authority;
import com.cseke.springblog.models.Post;
import com.cseke.springblog.repositories.AuthorityRepository;
import com.cseke.springblog.services.AccountService;
import com.cseke.springblog.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class SeedData implements CommandLineRunner {
    @Autowired
    private PostService postService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Override
    public void run(String... args) throws Exception {
        List<Post> posts = postService.getAll();

        if (posts.size() == 0){

            Authority user = new Authority();
            user.setName("ROLE_USER");
            authorityRepository.save(user);

            Authority admin = new Authority();
            admin.setName("ROLE_ADMIN");
            authorityRepository.save(admin);

            Account account1 = new Account();
            Account account2 = new Account();

            account1.setFirstName("user1");
            account1.setLastName("user1Last");
            account1.setEmail("user1@email.com");
            account1.setPassword("user1Password");
            Set<Authority> authorities1 = new HashSet<>();
            authorityRepository.findById("ROLE_USER").ifPresent(authorities1::add);
            account1.setAuthorities(authorities1);


            account2.setFirstName("admin");
            account2.setLastName("admin");
            account2.setEmail("admin@admin.com");
            account2.setPassword("admin");
            Set<Authority> authorities2 = new HashSet<>();
            authorityRepository.findById("ROLE_ADMIN").ifPresent(authorities2::add);
            authorityRepository.findById("ROLE_USER").ifPresent(authorities2::add);
            account2.setAuthorities(authorities2);

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
