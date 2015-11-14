/**
 * 
 */
package io.honeymon.blog.article;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import io.honeymon.blog.BlogApplication;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author honeymon
 *
 */
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BlogApplication.class)
@ActiveProfiles("test")
@WebAppConfiguration
public class PostDeleteServiceTest {

    @Autowired
    private PostCreateService postCreateService;
    @Autowired
    private PostDeleteService postDeleteService;
    @Autowired
    private PostService postService;

    private String title;
    private String contents;
    private Post createdPost;
    private PostDto requestPostDto;

    @Before
    public void setUp() {
        title = "Test post";
        contents = "Test contents";
        requestPostDto = new PostDto();
        requestPostDto.setTitle(title);
        requestPostDto.setContents(contents);
        createdPost = postCreateService.create(requestPostDto);
    }

    @Test
    public void testPostDelete() throws Exception {
        // given

        // when
        postDeleteService.delete(createdPost);

        // then
        Post findPost = postService.findOne(createdPost.getId());
        assertThat(findPost, is(nullValue()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPostDelete시_null을넣은경우() throws Exception {
        // given
        Post nullPost = null;

        // when
        postDeleteService.delete(nullPost);

        // then
        fail();
    }
}
