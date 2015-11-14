/**
 * 
 */
package io.honeymon.blog.article;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author honeymon
 *
 */
@Transactional(readOnly = true)
@Service
public class DefaultPostService implements PostService {

    @Autowired
    private PostRepository repository;
    
    @Override
    public Post findOne(Long id) {
        return repository.findOne(id);
    }

}
