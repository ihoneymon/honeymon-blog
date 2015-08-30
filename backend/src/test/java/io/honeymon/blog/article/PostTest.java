package io.honeymon.blog.article;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.junit.Test;

/**
 * @author honeymon
 *
 */
public class PostTest {
    private static final String CHANGE_CONTENTS = "Change contents";
    private static final String CHANGE_TITLE = "Change title";
    private static final String EMPTY_SPACE_STRINGS = "            ";
    private static final String EMPTY_SPACE_STRING = "";
    private static final String RIGHT_CONTENTS = "Right contents";
    private static final String RIGHT_TITLE = "Right title";

    @Test(expected = IllegalArgumentException.class)
    public void 포스트생성시_제목이_null인경우_예외발생() {
        Post post = new Post(null, RIGHT_CONTENTS);

        fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void 포스트생성시_제목이_빈공백인경우_예외발생() {
        Post post = new Post(EMPTY_SPACE_STRING, RIGHT_CONTENTS);

        fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void 포스트생성시_제목이_빈공백으로만_채워진경우_예외발생() {
        Post post = new Post(EMPTY_SPACE_STRINGS, RIGHT_CONTENTS);

        fail();
    }

    @Test
    public void 포스트생성_제목가져오기() {
        Post post = generateGeneralPost();

        assertThat(RIGHT_TITLE, is(post.getTitle()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void 포스트생성시_본문이_null인경우_예외발생() {
        Post post = new Post(RIGHT_TITLE, null);

        fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void 포스트생성시_본문이_빈공백인경우_예외발생() {
        Post post = new Post(RIGHT_TITLE, EMPTY_SPACE_STRING);

        fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void 포스트생성시_본문이_빈공백으로만_채워진경우_예외발생() {
        Post post = new Post(RIGHT_CONTENTS, EMPTY_SPACE_STRINGS);

        fail();
    }

    @Test
    public void 포스트생성_본문가져오기() {
        Post post = generateGeneralPost();

        assertThat(RIGHT_CONTENTS, is(post.getContets()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void 포트스_타이틀변경시_null인경우_예외발생() {
        Post post = generateGeneralPost();

        post.changeTitle(null);

        fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void 포스트_타이틀변경시_빈공백인경우_예외발생() {
        Post post = generateGeneralPost();

        post.changeTitle(EMPTY_SPACE_STRING);

        fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void 포스트_타이틀변경시_빈문자열인경우_예외발생() {
        Post post = generateGeneralPost();

        post.changeTitle(EMPTY_SPACE_STRINGS);

        fail();
    }

    /**
     * @return
     */
    private Post generateGeneralPost() {
        return new Post(RIGHT_TITLE, RIGHT_CONTENTS);
    }

    @Test
    public void 포스트_타이틀변경() {
        Post post = generateGeneralPost();

        post.changeTitle(CHANGE_TITLE);

        assertThat(CHANGE_TITLE, is(post.getTitle()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void 포스트_본문변경_null인경우_예외발생() {
        Post post = generateGeneralPost();

        post.changeContents(null);

        fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void 포스트_본문변경_빈문자열인경우_예외발생() {
        Post post = generateGeneralPost();

        post.changeContents(EMPTY_SPACE_STRING);

        fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void 포스트_본문변경_공백문자열인경우_예외발생() {
        Post post = generateGeneralPost();

        post.changeContents(EMPTY_SPACE_STRINGS);

        fail();
    }

    @Test
    public void 포스트_본문변경() {
        Post post = generateGeneralPost();

        post.changeContents(CHANGE_CONTENTS);

        assertThat(CHANGE_CONTENTS, is(post.getContets()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void 태그변경시_null인경우_예외발생() {
        Post post = generateGeneralPost();

        post.changeTags(null);

        fail();
    }
}
