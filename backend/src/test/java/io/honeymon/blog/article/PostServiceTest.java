/**
 * 
 */
package io.honeymon.blog.article;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import io.honeymon.blog.BlogApplication;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.dao.InvalidDataAccessApiUsageException;
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
@WebAppConfiguration
@ActiveProfiles("test")
public class PostServiceTest {

    @Autowired
    private PostCreateService postCreateService;
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
    public void testFindOne() throws Exception {
        // given
        Long findId = createdPost.getId();

        // when
        Post findPost = postService.findOne(findId);

        // then
        assertThat(findPost.getId(), is(findId));
        assertThat(findPost, is(createdPost));
    }

    @Test
    public void test존재하지않는ID값을_조회하는경우() throws Exception {
        // given
        Long nonExistPostId = Long.MAX_VALUE;

        // when
        Post nonExistPost = postService.findOne(nonExistPostId);

        // then
        assertThat(nonExistPost, is(nullValue()));
    }

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void test조회id값으로_null을_전달한경우() throws Exception {
        // given
        Long nullId = null;

        // when
        postService.findOne(nullId);

        // then
        fail();
    }

}
