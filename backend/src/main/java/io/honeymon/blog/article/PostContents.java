/**
 * 
 */
package io.honeymon.blog.article;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

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
@EqualsAndHashCode(of = { "post", "contents" })
public class PostContents {

    @Getter
    @Id
    @GeneratedValue
    private Long id;

    @Getter
    @OneToOne
    @JoinColumn(name = "POST_ID")
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
        this();
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
