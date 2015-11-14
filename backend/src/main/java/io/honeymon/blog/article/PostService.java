/**
 * 
 */
package io.honeymon.blog.article;

/**
 * {@link Post}와 관련된 기본 기능제공
 * 
 * @author honeymon
 *
 */
public interface PostService {

    /**
     * Find {@link Post} by Id
     * 
     * @param id
     * @return {@link Post} or null
     */
    Post findOne(Long id);
}
