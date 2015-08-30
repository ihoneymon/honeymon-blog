/**
 * 
 */
package io.honeymon.blog.article;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import io.honeymon.blog.BlogApplication;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BlogApplication.class)
@ActiveProfiles("test")
@WebAppConfiguration
public class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @Test
    public void test() {
        Post post = new Post("Honeymon's First post", "Hello world, honeymon");

        postRepository.save(post);

        assertThat(false, is(post.isNew()));
    }

    @Test
    public void testLazyLoading() {
        Post post = new Post("Honeymon's First post", "Hello world, honeymon");
        Post post2 = new Post("Honeymon's Second post", "Hello world, honeymon");
        
        postRepository.save(post);
        postRepository.save(post2);
        
        List<Post> posts = postRepository.findAll();
        
        log.debug("Posts: {}", posts);
        
        assertThat(true, is(posts.contains(post)));
    }
}
