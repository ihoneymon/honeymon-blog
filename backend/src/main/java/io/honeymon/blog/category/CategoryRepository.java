/**
 * 
 */
package io.honeymon.blog.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author honeymon
 *
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
