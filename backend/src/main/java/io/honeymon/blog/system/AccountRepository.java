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
public interface AccountRepository extends JpaRepository<Account, Long> {

    /**
     * @param username
     * @return
     */
    Account findByUsername(String username);

}
