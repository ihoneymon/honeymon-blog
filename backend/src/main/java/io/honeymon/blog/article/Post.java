package io.honeymon.blog.article;

import io.honeymon.blog.system.Account;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import org.springframework.data.jpa.domain.AbstractAuditable;
import org.springframework.util.Assert;

/**
 * 
 * @author honeymon
 *
 */
@Entity
@EqualsAndHashCode(of = { "title", "contents" }, callSuper = false)
@ToString(of = { "title", "contents" }, callSuper = true)
public class Post extends AbstractAuditable<Account, Long> {
    private static final long serialVersionUID = -7081741681983185307L;

    @Getter
    private String title;

    @OneToOne(mappedBy = "post", cascade = CascadeType.ALL, optional = false, fetch = FetchType.LAZY)
    private PostContents contents;

    @ElementCollection
    private List<String> tags;

    protected Post() {
        this.tags = new ArrayList<>();
        this.contents = new PostContents(this);
    }

    /**
     * @param object
     * @param contents
     */
    public Post(String title, String contents) {
        this();
        setTitle(title);
        Assert.hasText(contents, "error.post.required.contents");
        this.contents = new PostContents(this, contents);
    }

    /**
     * @param title
     * @return
     */
    private Post setTitle(String title) {
        Assert.hasText(title, "error.post.required.title");
        this.title = title;
        return this;
    }

    public String getContets() {
        return this.contents.getContents();
    }

    public List<String> getTags() {
        return Collections.unmodifiableList(this.tags);
    }

    /**
     * @param changeTitle
     * @return
     */
    public Post changeTitle(String changeTitle) {
        setTitle(changeTitle);
        return this;
    }

    /**
     * @param changeContents
     * @return
     */
    public Post changeContents(String changeContents) {
        Assert.hasText(changeContents, "error.post.required.contents");
        this.contents.changeContents(changeContents);
        return this;
    }

    /**
     * @param object
     */
    public void changeTags(List<String> tags) {
        Assert.notNull(tags, "error.post.required.tags");
    }
}
