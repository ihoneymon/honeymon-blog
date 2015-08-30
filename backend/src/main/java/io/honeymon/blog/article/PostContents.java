/**
 * 
 */
package io.honeymon.blog.article;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import org.springframework.util.Assert;

/**
 * 포스팅 본문
 * 
 * @author honeymon
 *
 */
@Entity
@ToString(of = { "id", "contents" })
@EqualsAndHashCode(of = { "post" })
public class PostContents {

    @Getter
    @Id
    @GeneratedValue
    private Long id;

    @Getter
    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    @Getter
    @Lob
    @Column(nullable = false)
    private String contents;

    protected PostContents() {
    }

    /**
     * @param post
     */
    public PostContents(Post post) {
        setPost(post);
    }

    /**
     * @param post
     * @param contents
     */
    public PostContents(Post post, String contents) {
        this(post);
        setContents(contents);

    }

    /**
     * @param post
     * @return
     */
    private PostContents setPost(Post post) {
        Assert.notNull(post, "error.postContents.required.post");
        this.post = post;
        return this;
    }

    private PostContents setContents(String contents) {
        Assert.hasText(contents, "error.postContents.required.contents");
        this.contents = contents;
        return this;
    }

    public PostContents changeContents(String contents) {
        return setContents(contents);
    }

}
