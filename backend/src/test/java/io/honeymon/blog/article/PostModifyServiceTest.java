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
public class PostModifyServiceTest {

    @Autowired
    private PostModifyService postModifyService;
    @Autowired
    private PostCreateService postCreateService;

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
    public void testPostModify() throws Exception {
        // given
        String modifyTitle = "Modify test post";
        String modifyContents = "Modify test contents";
        requestPostDto.setTitle(modifyTitle);
        requestPostDto.setContents(modifyContents);

        // when
        Post modifyPost = postModifyService.modify(createdPost, requestPostDto);

        // then
        assertThat(modifyPost.getId(), is(createdPost.getId()));
        assertThat(modifyPost.getTitle(), is(not(title)));
        assertThat(modifyPost.getTitle(), is(modifyTitle));
        assertThat(modifyPost.getContets(), is(not(contents)));
        assertThat(modifyPost.getContets(), is(modifyContents));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPost변경시_title이_null인경우_예외발생() throws Exception {
        // given
        String modifyTitle = null;
        String modifyContents = "Modify test contents";
        requestPostDto.setTitle(modifyTitle);
        requestPostDto.setContents(modifyContents);

        // when
        postModifyService.modify(createdPost, requestPostDto);

        // then
        fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPost변경시_title이_빈공백인경우_예외발생() throws Exception {
        // given
        String modifyTitle = "";
        String modifyContents = "Modify test contents";
        requestPostDto.setTitle(modifyTitle);
        requestPostDto.setContents(modifyContents);

        // when
        postModifyService.modify(createdPost, requestPostDto);

        // then
        fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPost변경시_content가_null인경우_예외발생() throws Exception {
        // given
        String modifyTitle = "Modify test title";
        String modifyContents = null;
        requestPostDto.setTitle(modifyTitle);
        requestPostDto.setContents(modifyContents);

        // when
        postModifyService.modify(createdPost, requestPostDto);

        // then
        fail();
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testPost변경시_content가_빈공백인경우_예외발생() throws Exception {
        // given
        String modifyTitle = "Modify test title";
        String modifyContents = "";
        requestPostDto.setTitle(modifyTitle);
        requestPostDto.setContents(modifyContents);

        // when
        postModifyService.modify(createdPost, requestPostDto);

        // then
        fail();
    }
}
