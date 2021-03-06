/**
 * 
 */
package io.honeymon.blog.article;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import io.honeymon.blog.BlogApplication;

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
public class PostCreateServiceTest {
    @Autowired
    private PostCreateService postCreateService;

    @Test
    public void testPost생성() throws Exception {
        // given
        String title = "Test post";
        String contents = "Test contents";
        PostDto postDto = new PostDto();
        postDto.setTitle(title);
        postDto.setContents(contents);

        // when
        Post post = postCreateService.create(postDto);

        // then
        assertThat(post.isNew(), is(false));
        assertThat(post.getTitle(), is(title));
        assertThat(post.getContets(), is(contents));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPost제목이_null인경우_예외발생() throws Exception {
        // given
        String title = null;
        String contents = "Test contents";
        PostDto postDto = new PostDto();
        postDto.setTitle(title);
        postDto.setContents(contents);

        // when
        postCreateService.create(postDto);

        // then
        fail();
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testPost제목이_빈공백문자인경우_예외발생() throws Exception {
        // given
        String title = "";
        String contents = "Test contents";
        PostDto postDto = new PostDto();
        postDto.setTitle(title);
        postDto.setContents(contents);

        // when
        postCreateService.create(postDto);

        // then
        fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPost의contents가null인경우_예외발생() throws Exception {
        // given
        String title = "Test post";
        String contents = null;
        PostDto postDto = new PostDto();
        postDto.setTitle(title);
        postDto.setContents(contents);

        // when
        postCreateService.create(postDto);

        // then
        fail();
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testPost의contents가_빈문자인경우_예외발생() throws Exception {
        // given
        String title = "Test post";
        String contents = "";
        PostDto postDto = new PostDto();
        postDto.setTitle(title);
        postDto.setContents(contents);

        // when
        postCreateService.create(postDto);

        // then
        fail();
    }
}
