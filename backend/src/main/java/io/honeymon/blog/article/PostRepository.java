/**
 * 
 */
package io.honeymon.blog.article;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author honeymon
 *
 */
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

}
