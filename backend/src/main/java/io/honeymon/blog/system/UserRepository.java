/**
 * 
 */
package io.honeymon.blog.system;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author honeymon
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * @param username
     * @return
     */
    User findByUsername(String username);

}
