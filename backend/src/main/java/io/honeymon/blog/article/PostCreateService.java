/**
 * 
 */
package io.honeymon.blog.article;

import io.honeymon.blog.common.GlobalException;

/**
 * {@link Post} 생성 서비스
 * 
 * @author honeymon
 *
 */
public interface PostCreateService {

    /**
     * Create {@link Post} by {@link PostDto}
     * 
     * @param request
     * @return
     * @throws GlobalException
     */
    Post create(PostDto request) throws GlobalException;
}
