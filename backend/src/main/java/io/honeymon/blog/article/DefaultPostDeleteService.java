/**
 * 
 */
package io.honeymon.blog.article;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

/**
 * @author honeymon
 *
 */
@Transactional(readOnly = true)
@Service
public class DefaultPostDeleteService implements PostDeleteService {

    @Autowired
    private PostRepository repository;

    @Transactional
    @Override
    public void delete(Post post) {
        Assert.notNull(post);

        repository.delete(post);
    }

}
