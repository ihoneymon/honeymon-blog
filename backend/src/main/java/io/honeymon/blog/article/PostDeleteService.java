/**
 * 
 */
package io.honeymon.blog.article;

import io.honeymon.blog.common.GlobalException;

/**
 * @author honeymon
 *
 */
public interface PostDeleteService {

    /**
     * Delete {@link Post}
     * 
     * @param post
     * @throws GlobalException 
     */
    void delete(Post post);
}
