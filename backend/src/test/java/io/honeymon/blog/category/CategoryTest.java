/**
 * 
 */
package io.honeymon.blog.category;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.junit.Test;

/**
 * @author honeymon
 *
 */
public class CategoryTest {

    @Test(expected = IllegalArgumentException.class)
    public void 카테고리이름이_null인경우_예외발생() {
        // when
        Category category = new Category(null);

        // then
        fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void 카테고리이름이_빈공백문자인경우_예외발생() {
        // when
        Category category = new Category("");

        // then
        fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void 카테고리이름이_공백문자열인경우_예외발생() {
        // when
        Category category = new Category("        ");

        // then
        fail();
    }

    @Test
    public void 카테고리최초생성시_루트카테고리다() {
        // when
        Category category = makeRootCategory("Root category");

        // then
        assertThat(category.isRoot(), is(true));
    }

    @Test
    public void 최초생성된_카테고리는_부모가없다() {
        // when
        Category category = makeRootCategory("Root category");

        // then
        assertThat(category.hasParent(), is(false));
    }

    /**
     * @return
     */
    private Category makeRootCategory(String categoryName) {
        return new Category(categoryName);
    }

    @Test
    public void test카테고리생성() {
        // given
        String categoryName = "Test category";
        Category category = new Category(categoryName);

        assertThat(category.getDepth(), is(1));
        assertThat(category.getName(), is(categoryName));
    }

    @Test
    public void test자식카테고리생성() {
        // given
        Category parent = new Category("Parent");

        // when
        Category child = new Category(parent, "Child");

        // then
        assertThat(child.getParent(), is(parent));
        assertThat(parent.getChildren().contains(child), is(true));
        assertThat(child.getDepth(), is(parent.getDepth() + 1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test동일한이름을가진_자식카테고리_추가시_예외발생() {
        // given
        Category parent = new Category("Parent");

        // when
        Category twinChild1 = new Category(parent, "Twin Child");
        Category twinChild2 = new Category(parent, "Twin Child");

        // then
        fail();
    }

    @Test
    public void test자식카테고리삭제() throws Exception {
        // given
        Category parent = new Category("Parent");
        Category child1 = new Category(parent, "Child1");
        Category child2 = new Category(parent, "Child2");
        assertThat(parent.getChildren().size(), is(2));

        // when
        parent.removeChild(child1);

        // then
        assertThat(parent.getChildren().size(), is(1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test자식삭제시null인경우예외발생() throws Exception {
        // given
        Category parent = new Category("Parent");

        // when
        parent.removeChild(null);

        // then
        fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void test자식카테고리의부모를null로변경시에외발생() {
        // given
        Category child = new Category("Child");

        // when
        child.changeParent(null);

        // then
        fail();
    }
}
