/**
 * 
 */
package io.honeymon.blog.system;

import java.util.List;

/**
 * 사용자관리 서비스
 * 
 * @author honeymon
 *
 */
public interface AccountService {

    /**
     * @param user
     *            new {@link Account}
     * @return
     */
    Account save(Account user);

    /**
     * FindAll {@link Account}
     * 
     * @return
     */
    List<Account> findAll();

    /**
     * Delete {@link Account}
     * 
     * @param user
     */
    void delete(Account user);

    /**
     * Find User by username
     * 
     * @param username
     * @return
     */
    Account findByUserame(String username);

}
