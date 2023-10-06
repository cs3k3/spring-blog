package com.cseke.springblog.config;

import com.cseke.springblog.models.Post;
import com.cseke.springblog.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SeedData implements CommandLineRunner {
    @Autowired
    private PostService postService;

    @Override
    public void run(String... args) throws Exception {
        List<Post> posts = postService.getAll();

        if (posts.size() == 0){
            Post post1 = new Post();
            post1.setTitle("post1 title");
            post1.setBody("post1 body");

            Post post2 = new Post();
            post2.setTitle("post2 title");
            post2.setBody("post2 body");

            postService.save(post1);
            postService.save(post2);
        }
    }
}
