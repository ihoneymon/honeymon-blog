/**
 * 
 */
package io.honeymon.blog.article;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Transactional(readOnly = true)
@Service
public class DefaultPostModifyService implements PostModifyService {

    @Autowired
    private PostRepository repository;

    @Transactional
    @Override
    public Post modify(Post post, PostDto request) {
        Assert.notNull(post);
        Assert.notNull(request);

        post.changeTitle(request.getTitle());
        post.changeContents(request.getContents());

        return repository.save(post);
    }

}
