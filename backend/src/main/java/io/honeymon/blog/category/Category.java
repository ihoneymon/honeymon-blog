/**
 * 
 */
package io.honeymon.blog.category;

import io.honeymon.blog.system.Account;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PreRemove;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import org.springframework.data.jpa.domain.AbstractAuditable;
import org.springframework.util.Assert;

/**
 * @author honeymon
 *
 */
@Entity
@Getter
@ToString(exclude = { "parent" }, callSuper = true)
@EqualsAndHashCode(of = { "parent", "name", "depth" }, callSuper = false)
public class Category extends AbstractAuditable<Account, Long> {
    private static final long serialVersionUID = -2825422148186092999L;

    private static final int DEPTH_STEP = 1;
    private static final int ROOT_DEPTH = 1;

    @OneToOne(optional = true)
    private Category parent;

    @Getter
    private String name;

    /**
     * 깊이: 카테고리의 특성상 3단계까지만 적용
     */
    private int depth;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Category> children;

    protected Category() {
        this.depth = ROOT_DEPTH;
        this.children = new ArrayList<>();
    }

    /**
     * @param object
     */
    public Category(String name) {
        this();
        Assert.hasText(name, "error.category.require.name");
        this.name = name;
    }

    /**
     * @param parent
     * @param name
     */
    public Category(Category parent, String name) {
        this(name);
        Assert.notNull(parent);

        this.parent = parent;
        this.depth = parent.getDepth() + DEPTH_STEP;
        parent.addChild(this);
    }

    /**
     * @return
     */
    public boolean isRoot() {
        return null == parent;
    }

    /**
     * @return
     */
    public boolean hasParent() {
        return null != parent;
    }

    /**
     * @param child
     * @return
     */
    public Category addChild(Category child) {
        Assert.notNull(child);

        if (hasChild(child)) {
            throw new IllegalArgumentException("category.exception.existSameName");
        }

        this.children.add(child);
        return this;
    }

    /**
     * @param child
     * @return
     */
    private boolean hasChild(Category child) {
        return children.stream().anyMatch(el -> el.getName().equals(child.getName()));
    }

    /**
     * @param parent
     *            {@link Category}
     * @return
     */
    public Category changeParent(Category parent) {
        Assert.notNull(parent);

        this.parent = parent;
        this.depth = parent.getDepth() + DEPTH_STEP;

        return this;
    }

    public List<Category> getChildren() {
        return Collections.unmodifiableList(this.children);
    }

    /**
     * @param child
     *            {@link Category}
     * @return
     */
    public Category removeChild(Category child) {
        Assert.notNull(child);

        child.parent = null;
        this.children.remove(child);

        return this;
    }

    @PreRemove
    public void preRemove() {
        removeThisAtParent();
    }

    private void removeThisAtParent() {
        if (hasParent()) {
            this.parent.removeChild(this);
        }
    }
}
