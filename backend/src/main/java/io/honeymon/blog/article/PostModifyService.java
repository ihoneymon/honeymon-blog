/**
 * 
 */
package io.honeymon.blog.article;

/**
 * Post modify service
 * 
 * @author honeymon
 *
 */
public interface PostModifyService {

    /**
     * Modify {@link Post} by {@link PostDto}
     * 
     * @param post
     * @param request
     * @return
     */
    Post modify(Post post, PostDto request);
}
