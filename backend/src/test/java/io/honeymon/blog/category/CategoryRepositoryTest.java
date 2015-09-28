/**
 * 
 */
package io.honeymon.blog.category;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import io.honeymon.blog.BlogApplication;
import lombok.extern.slf4j.Slf4j;

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
@Slf4j
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BlogApplication.class)
@ActiveProfiles("test")
@WebAppConfiguration
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void testCategory저장() {
        // given
        String name = "Parent";
        Category parent = new Category(name);

        // when
        categoryRepository.save(parent);

        // then
        assertThat(parent.isNew(), is(false));
        assertThat(parent.getName(), is(notNullValue()));
        assertThat(parent.getName(), is(name));
    }

    @Test
    public void test자식Category저장() {
        // given
        String parentName = "Parent";
        Category parent = new Category(parentName);

        String childName = "Child";
        Category child = new Category(parent, childName);
        categoryRepository.save(child);

        // when
        log.debug("Category: {}", child);
        categoryRepository.findOne(child.getId());

        // then
        assertThat(child.isRoot(), is(false));
        assertThat(child.getName(), is(childName));
        assertThat(child.getParent(), is(parent));
        assertThat(parent.getChildren().contains(child), is(true));
    }

    @Test
    public void test자식카테고리삭제() {
        // given
        String parentName = "Parent";
        Category parent = new Category(parentName);
        categoryRepository.save(parent);

        String childName = "Child";
        Category child = new Category(parent, childName);
        categoryRepository.save(child);

        // then
        categoryRepository.delete(child);
        
        //then
        Category deletedCategory = categoryRepository.findOne(child.getId());
        assertThat(deletedCategory, is(nullValue()));
        assertThat(parent.getChildren().contains(child), is(false));
    }

}
