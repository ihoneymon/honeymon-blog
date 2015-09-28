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
    public void 포스트_저장기능확인() {
        String title = "Honeymon's First post";
        String contents = "Hello world, honeymon";
        Post post = new Post(title, contents);

        postRepository.save(post);

        assertThat(post.isNew(), is(false));
        assertThat(post.getTitle(), is(title));
        assertThat(post.getContets(), is(contents));
        assertNotNull(post.getId());
    }

    @Test
    public void 저장된포스트목록가져오기() {
        Post post = new Post("Honeymon's First post", "Hello world, honeymon");
        Post post2 = new Post("Honeymon's Second post", "Hello world, honeymon");

        postRepository.save(post);
        postRepository.save(post2);

        List<Post> posts = postRepository.findAll();

        log.debug("Posts: {}", posts);

        assertThat(true, is(posts.contains(post)));
        assertThat(false, is(post.isNew()));
        assertThat(false, is(post2.isNew()));
    }

    @Test
    public void 포스트제목변경저장확인() {
        String originTitle = "Honeymon's First post";
        String originContents = "Hello world, honeymon";
        Post post = new Post(originTitle, originContents);

        postRepository.save(post);

        String changeTitle = "Change title";
        String changeContents = "Change contents";
        post.changeTitle(changeTitle);
        post.changeContents(changeContents);

        Post modifyPost = postRepository.findOne(post.getId());

        assertThat(changeTitle, is(modifyPost.getTitle()));
        assertThat(changeContents, is(modifyPost.getContets()));
    }
    
    @Test
    public void 포스트삭제확인() {
        Post post = new Post("Honeymon's First post", "Hello world, honeymon");
        Post post2 = new Post("Honeymon's Second post", "Hello world, honeymon");
        postRepository.save(post);
        postRepository.save(post2);
        
        postRepository.delete(post);
        
        Post findPost = postRepository.findOne(post.getId());
        
        assertNull(findPost);
    }
}