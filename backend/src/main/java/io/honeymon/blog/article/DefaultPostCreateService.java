/**
 * 
 */
package io.honeymon.blog.article;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.honeymon.blog.common.GlobalException;

/**
 * @author honeymon
 *
 */
@Transactional(readOnly = true)
@Service
public class DefaultPostCreateService implements PostCreateService {

    @Autowired
    private PostRepository repository;

    @Override
    public Post create(PostDto request) throws GlobalException {
        Post post = new Post(request.getTitle(), request.getContents());
        return repository.save(post);
    }

}
